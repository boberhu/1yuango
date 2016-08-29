package com.wangyin.wepaypc.action;

import com.wangyin.wepaypc.model.BaseResponseDto;
import com.wangyin.wepaypc.model.RefundResultTradeEntity;
import com.wangyin.wepaypc.model.TradeRefundEntity;
import com.wangyin.wepaypc.model.TradeRefundReqDto;
import com.wangyin.wepaypc.util.ByteUtil;
import com.wangyin.wepaypc.util.FormatUtil;
import com.wangyin.wepaypc.util.HttpsClientUtil;
import com.wangyin.wepaypc.util.JsonUtil;
import com.wangyin.wepaypc.util.PropertyUtils;
import com.wangyin.wepaypc.util.RSACoder;
import com.wangyin.wepaypc.util.SHAUtil;
import com.wangyin.wepaypc.util.Sha256Util;
import com.wangyin.wepaypc.util.TDESUtil;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

@Controller
public class RefundOrderAction
{
  @RequestMapping({"/refundIndex.htm"})
  public String queryIndex(HttpServletRequest httpServletRequest)
  {
    String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
    httpServletRequest.setAttribute("merchantNum", merchantNum);
    httpServletRequest.setAttribute("nowTime", new Date());
    return "refundIndex";
  }
  
  @RequestMapping(value={"/refundOrder.htm"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String paySign(TradeRefundReqDto tradeRefundReqDto, HttpServletRequest httpServletRequest)
  {
    String tradeJsonData = "{\"tradeNum\": \"" + tradeRefundReqDto.getTradeNum() + "\",\"oTradeNum\": \"" + tradeRefundReqDto.getoTradeNum() + "\",\"tradeAmount\":\"" + tradeRefundReqDto.getTradeAmount() + "\",\"tradeCurrency\": \"" + tradeRefundReqDto.getTradeCurrency() + "\",\"tradeDate\": \"" + tradeRefundReqDto.getTradeDate() + "\",\"tradeTime\": \"" + tradeRefundReqDto.getTradeTime() + "\",\"tradeNotice\": \"" + tradeRefundReqDto.getTradeNotice() + "\",\"tradeNote\": \"" + tradeRefundReqDto.getTradeNote() + "\"}";
    try
    {
      String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
      String threeDesData = TDESUtil.encrypt2HexStr(RSACoder.decryptBASE64(deskey), tradeJsonData);
      

      String sha256Data = SHAUtil.Encrypt(threeDesData, null);
      String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
      byte[] rsaResult = RSACoder.encryptByPrivateKey(sha256Data.getBytes(), priKey);
      String merchantSign = RSACoder.encryptBASE64(rsaResult);
      

      TradeRefundEntity tradeRefundEntity = new TradeRefundEntity();
      tradeRefundEntity.setVersion(tradeRefundReqDto.getVersion());
      tradeRefundEntity.setMerchantNum(tradeRefundReqDto.getMerchantNum());
      tradeRefundEntity.setMerchantSign(FormatUtil.stringBlank(merchantSign));
      tradeRefundEntity.setData(threeDesData);
      
      String json = JsonUtil.write2JsonStr(tradeRefundEntity);
      

      String refundUrl = PropertyUtils.getProperty("wepay.server.refund.url");
      String resultJsonData = HttpsClientUtil.sendRequest(refundUrl, json);
      

      BaseResponseDto<Map<String, Object>> result = (BaseResponseDto)JsonUtil.json2Object(resultJsonData, BaseResponseDto.class);
      if (result.getResultCode().intValue() == 0)
      {
        Map<String, Object> mapResult = (Map)result.getResultData();
        if (mapResult != null)
        {
          String data = mapResult.get("data").toString();
          String sign = mapResult.get("sign").toString();
          
          byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
          String wyPubKey = PropertyUtils.getProperty("wepay.wangyin.rsaPublicKey");
          byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
          String decryptStr = ByteUtil.byte2HexString(decryptArr);
          

          String sha256SourceSignString = ByteUtil.byte2HexLowerCase(Sha256Util.encrypt(data.getBytes("UTF-8")));
          if (decryptStr.equals(sha256SourceSignString))
          {
            String decrypData = TDESUtil.decrypt4HexStr(RSACoder.decryptBASE64(deskey), data);
            

            RefundResultTradeEntity resultData = (RefundResultTradeEntity)JsonUtil.json2Object(decrypData, RefundResultTradeEntity.class);
            if (resultData == null) {
              httpServletRequest.setAttribute("errorMsg", decrypData);
            } else {
              httpServletRequest.setAttribute("resultData", resultData);
            }
          }
          else
          {
            return "";
          }
        }
      }
      else
      {
        httpServletRequest.setAttribute("errorMsg", result.getResultMsg());
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "refundResult";
  }
}

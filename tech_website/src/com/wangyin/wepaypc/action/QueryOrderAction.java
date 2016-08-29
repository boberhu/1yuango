package com.wangyin.wepaypc.action;

import com.wangyin.wepaypc.model.BaseResponseDto;
import com.wangyin.wepaypc.model.QueryResultTradeEntity;
import com.wangyin.wepaypc.model.TradeQueryEntity;
import com.wangyin.wepaypc.model.TradeQueryReqDto;
import com.wangyin.wepaypc.util.ByteUtil;
import com.wangyin.wepaypc.util.FormatUtil;
import com.wangyin.wepaypc.util.HttpsClientUtil;
import com.wangyin.wepaypc.util.JsonUtil;
import com.wangyin.wepaypc.util.PropertyUtils;
import com.wangyin.wepaypc.util.RSACoder;
import com.wangyin.wepaypc.util.SHAUtil;
import com.wangyin.wepaypc.util.Sha256Util;
import com.wangyin.wepaypc.util.TDESUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

@Controller
public class QueryOrderAction
{
  @RequestMapping({"/queryIndex.htm"})
  public String queryIndex(HttpServletRequest httpServletRequest)
  {
    String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
    httpServletRequest.setAttribute("merchantNum", merchantNum);
    return "queryIndex";
  }
  
  @RequestMapping(value={"/queryOrder.htm"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String paySign(TradeQueryReqDto tradeQueryReqDto, HttpServletRequest httpServletRequest)
  {
    String decrypData = "";
    String tradeJsonData = "{\"tradeNum\": \"" + tradeQueryReqDto.getTradeNum() + "\"}";
    try
    {
      String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
      String threeDesData = TDESUtil.encrypt2HexStr(RSACoder.decryptBASE64(deskey), tradeJsonData);
      

      String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
      String sha256Data = SHAUtil.Encrypt(threeDesData, null);
      byte[] rsaResult = RSACoder.encryptByPrivateKey(sha256Data.getBytes(), priKey);
      String merchantSign = RSACoder.encryptBASE64(rsaResult);
      

      TradeQueryEntity queryTradeDTO = new TradeQueryEntity();
      queryTradeDTO.setVersion(tradeQueryReqDto.getVersion());
      queryTradeDTO.setMerchantNum(tradeQueryReqDto.getMerchantNum());
      queryTradeDTO.setMerchantSign(FormatUtil.stringBlank(merchantSign));
      queryTradeDTO.setData(threeDesData);
      
      String json = JsonUtil.write2JsonStr(queryTradeDTO);
      

      String queryUrl = PropertyUtils.getProperty("wepay.server.query.url");
      String resultJsonData = HttpsClientUtil.sendRequest(queryUrl, json);
      


      BaseResponseDto<Map<String, Object>> result = (BaseResponseDto)JsonUtil
    		 .json2Object(resultJsonData, BaseResponseDto.class);
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
            decrypData = TDESUtil.decrypt4HexStr(RSACoder.decryptBASE64(deskey), data);
            List<Map<String, Object>> resultList = JsonUtil.jsonArray2List(decrypData);
            List<QueryResultTradeEntity> viewList = new ArrayList<QueryResultTradeEntity>();
            for (Map<String, Object> m : resultList)
            {
              QueryResultTradeEntity qrte = new QueryResultTradeEntity();
              qrte.setTradeCurrency(m.get("tradeCurrency")+"");
              qrte.setTradeDate(m.get("tradeDate")+"");
              qrte.setTradeTime(m.get("tradeTime")+"");
              qrte.setTradeAmount(Integer.parseInt(m.get("tradeAmount")+""));
              qrte.setTradeNote(m.get("tradeNote")+"");
              qrte.setTradeNum(m.get("tradeNum")+"");
              qrte.setTradeStatus(m.get("tradeStatus")+"");
              viewList.add(qrte);
            }
            httpServletRequest.setAttribute("viewList", viewList);
            if (resultList.size() < 1) {
              httpServletRequest.setAttribute("errorMsg", decrypData);
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
    return "queryResult";
  }
}

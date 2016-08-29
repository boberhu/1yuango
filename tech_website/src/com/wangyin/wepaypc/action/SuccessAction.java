package com.wangyin.wepaypc.action;

import com.wangyin.wepaypc.model.TradeQueryRes;
import com.wangyin.wepaypc.util.PropertyUtils;
import com.wangyin.wepaypc.util.RSACoder;
import com.wangyin.wepaypc.util.SHAUtil;
import com.wangyin.wepaypc.util.SignUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

@Controller
public class SuccessAction
{
  @RequestMapping({"/success.htm"})
  public String success(ModelMap map, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    TradeQueryRes tradeQueryRes = new TradeQueryRes();
    String token = request.getParameter("token");
    tradeQueryRes.setToken(token);
    
    String tradeNum = request.getParameter("tradeNum");
    tradeQueryRes.setTradeNum(tradeNum);
    
    String tradeAmount = request.getParameter("tradeAmount");
    tradeQueryRes.setTradeAmount(tradeAmount);
    
    String tradeCurrency = request.getParameter("tradeCurrency");
    tradeQueryRes.setTradeCurrency(tradeCurrency);
    

    String tradeDate = request.getParameter("tradeDate");
    tradeQueryRes.setTradeDate(tradeDate);
    
    String tradeTime = request.getParameter("tradeTime");
    tradeQueryRes.setTradeTime(tradeTime);
    String tradeNote = request.getParameter("tradeNote");
    tradeQueryRes.setTradeNote(tradeNote);
    String tradeStatus = request.getParameter("tradeStatus");
    tradeQueryRes.setTradeStatus(tradeStatus);
    String sign = request.getParameter("sign");
    

    String strSourceData = SignUtil.signString(tradeQueryRes, new ArrayList());
    
    byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
    String wyPubKey = PropertyUtils.getProperty("wepay.wangyin.rsaPublicKey");
    

    byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
    String decryptStr = new String(decryptArr, "UTF-8");
    
    String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);
    if (!decryptStr.equals(sha256SourceSignString)) {
      request.setAttribute("errorMsg", "验证签名失败！");
    } else {
      request.setAttribute("errorMsg", tradeQueryRes.getTradeNum() + ":status:" + tradeStatus);
    }
    return "queryResult";
  }
}

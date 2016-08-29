package com.wangyin.wepaypc.action;

import com.wangyin.wepaypc.client.SignHelper;
import com.wangyin.wepaypc.model.BasePayOrderInfo;
import com.wangyin.wepaypc.util.PropertyUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayOrderAction
{
  @RequestMapping({"/payStart.htm"})
  public String queryIndex(HttpServletRequest httpServletRequest)
  {
    String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
    httpServletRequest.setAttribute("merchantNum", merchantNum);
    return "payStart";
  }
  
  @RequestMapping({"/clientOrder.htm"})
  public String pay(ModelMap map, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String version = request.getParameter("version");
    String token = request.getParameter("token");
    String merchantRemark = request.getParameter("merchantRemark");
    String tradeNum = request.getParameter("tradeNum");
    

    String tradeName = request.getParameter("tradeName");
    String tradeDescription = request.getParameter("tradeDescription");
    String tradeTime = request.getParameter("tradeTime");
    
    String tradeAmount = request.getParameter("tradeAmount");
    String currency = request.getParameter("currency");
    String successCallbackUrl = request.getParameter("successCallbackUrl");
    String forPayLayerUrl = request.getParameter("forPayLayerUrl");
    String notifyUrl = request.getParameter("notifyUrl");
    
    String ip = request.getParameter("ip");
    String merchantNum = request.getParameter("merchantNum");
    
    BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
    basePayOrderInfo.setToken(token);
    basePayOrderInfo.setMerchantNum(merchantNum);
    basePayOrderInfo.setMerchantRemark(merchantRemark);
    basePayOrderInfo.setTradeNum(tradeNum);
    basePayOrderInfo.setTradeName(tradeName);
    basePayOrderInfo.setTradeDescription(tradeDescription);
    basePayOrderInfo.setTradeTime(tradeTime);
    basePayOrderInfo.setTradeAmount(tradeAmount);
    basePayOrderInfo.setCurrency(currency);
    basePayOrderInfo.setNotifyUrl(notifyUrl);
    basePayOrderInfo.setSuccessCallbackUrl(successCallbackUrl);
    basePayOrderInfo.setForPayLayerUrl(forPayLayerUrl);
    basePayOrderInfo.setVersion(version);
    basePayOrderInfo.setIp(ip);
    String serverUrl = PropertyUtils.getProperty("wepay.server.pay.url");
    String oriUrl = serverUrl + "/pay.htm";
    String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
    String url = SignHelper.getUrl(basePayOrderInfo, oriUrl, priKey);
    

    response.sendRedirect(url);
    return null;
  }
  
  public static String urlEncode(String input)
  {
    try
    {
      return URLEncoder.encode(input, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      throw new IllegalArgumentException("Unsupported Encoding Exception", e);
    }
  }
}

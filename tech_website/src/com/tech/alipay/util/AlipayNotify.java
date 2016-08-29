package com.tech.alipay.util;

import com.tech.alipay.config.AlipayConfig;
import com.tech.alipay.sign.MD5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AlipayNotify
{
  private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
  
  public static boolean verify(Map<String, String> params)
  {
    String responseTxt = "true";
    if (params.get("notify_id") != null)
    {
      String notify_id = (String)params.get("notify_id");
      responseTxt = verifyResponse(notify_id);
    }
    String sign = "";
    if (params.get("sign") != null) {
      sign = (String)params.get("sign");
    }
    boolean isSign = getSignVeryfy(params, sign);
    if ((isSign) && (responseTxt.equals("true"))) {
      return true;
    }
    return false;
  }
  
  private static boolean getSignVeryfy(Map<String, String> Params, String sign)
  {
    Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
    
    String preSignStr = AlipayCore.createLinkString(sParaNew);
    
    boolean isSign = false;
    if (AlipayConfig.sign_type.equals("MD5")) {
      isSign = MD5.verify(preSignStr, sign, AlipayConfig.key, AlipayConfig.input_charset);
    }
    return isSign;
  }
  
  private static String verifyResponse(String notify_id)
  {
    String partner = AlipayConfig.partner;
    String veryfy_url = HTTPS_VERIFY_URL+"partner=" + partner + "&notify_id=" + notify_id;
    
    return checkUrl(veryfy_url);
  }
  
  private static String checkUrl(String urlvalue)
  {
    String inputLine = "";
    HttpURLConnection urlConnection = null;
    try
    {
      URL url = new URL(urlvalue);
      urlConnection = (HttpURLConnection)url.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(
    		  urlConnection.getInputStream()));
      inputLine = in.readLine().toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      inputLine = "";
    }finally{
    	if(urlConnection != null){
    		urlConnection.disconnect();
    	}
    }
    return inputLine;
  }
}

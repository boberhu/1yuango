package com.tech.weixin.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler
{
  private String tokenUrl;
  private String gateUrl;
  private String notifyUrl;
  private String appid;
  private String appkey;
  private String partnerkey;
  private String appsecret;
  private String key;
  private SortedMap parameters;
  private String Token;
  private String charset;
  private String debugInfo;
  private String last_errcode;
  private HttpServletRequest request;
  private HttpServletResponse response;
  
  public RequestHandler(HttpServletRequest request, HttpServletResponse response)
  {
    last_errcode = "0";
    this.request = request;
    this.response = response;
    charset = "GBK";
    parameters = new TreeMap();
    
    notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
  }
  
  public void init(String app_id, String app_secret, String app_key, String partner_key)
  {
    last_errcode = "0";
    Token = "token_";
    debugInfo = "";
    appkey = app_key;
    appid = app_id;
    partnerkey = partner_key;
    appsecret = app_secret;
  }
  
  public void init() {}
  
  public String getLasterrCode()
  {
    return last_errcode;
  }
  
  public String getGateUrl()
  {
    return gateUrl;
  }
  
  public String getParameter(String parameter)
  {
    String s = (String)parameters.get(parameter);
    return s == null ? "" : s;
  }
  
  public void setKey(String key)
  {
    partnerkey = key;
  }
  
  public void setAppKey(String key)
  {
    appkey = key;
  }
  
  public String UrlEncode(String src)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(src, charset).replace("+", "%20");
  }
  
  public String genPackage(SortedMap<String, String> packageParams)
    throws UnsupportedEncodingException
  {
    String sign = createSign(packageParams);
    
    StringBuffer sb = new StringBuffer();
    Set<Map.Entry<String, String>> es = packageParams.entrySet();
    Iterator<Map.Entry<String,String>> it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry<String,String> entry = it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      sb.append(k + "=" + UrlEncode(v) + "&");
    }
    String packageValue = new StringBuilder("sign=").append(sign).toString();
    System.out.println("packageValue=" + packageValue);
    return packageValue;
  }
  
  public String createSign(SortedMap<String, String> packageParams)
  {
    StringBuffer sb = new StringBuffer();
    Set es = packageParams.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((v != null) && (!"".equals(v)) && (!"sign".equals(k)) && (!"key".equals(k))) {
        sb.append(k + "=" + v + "&");
      }
    }
    sb.append("key=" + getKey());
    System.out.println("md5 sb:" + sb);
    String sign = MD5Util.MD5Encode(sb.toString(), charset).toUpperCase();
    
    return sign;
  }
  
  public boolean createMd5Sign(String signParams)
  {
    StringBuffer sb = new StringBuffer();
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((!"sign".equals(k)) && (v != null) && (!"".equals(v))) {
        sb.append(k + "=" + v + "&");
      }
    }
    String enc = TenpayUtil.getCharacterEncoding(request, 
      response);
    String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
    
    String tenpaySign = getParameter("sign").toLowerCase();
    

    setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:" + 
      tenpaySign);
    
    return tenpaySign.equals(sign);
  }
  
  public String parseXML()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((v != null) && (!"".equals(v)) && (!"appkey".equals(k))) {
        sb.append("<" + k + ">" + getParameter(k) + "</" + k + ">\n");
      }
    }
    sb.append("</xml>");
    return sb.toString();
  }
  
  protected void setDebugInfo(String debugInfo)
  {
    this.debugInfo = debugInfo;
  }
  
  public void setPartnerkey(String partnerkey)
  {
    this.partnerkey = partnerkey;
  }
  
  public String getDebugInfo()
  {
    return debugInfo;
  }
  
  public String getKey()
  {
    return partnerkey;
  }
}

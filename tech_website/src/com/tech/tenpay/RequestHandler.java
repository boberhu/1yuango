package com.tech.tenpay;

import com.tech.tenpay.util.MD5Util;
import com.tech.tenpay.util.TenpayUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler
{
  private String gateUrl;
  private String key;
  private SortedMap parameters;
  private String debugInfo;
  private HttpServletRequest request;
  private HttpServletResponse response;
  
  public RequestHandler(HttpServletRequest request, HttpServletResponse response)
  {
    this.request = request;
    this.response = response;
    
    gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
    key = "";
    parameters = new TreeMap();
    debugInfo = "";
  }
  
  public void init() {}
  
  public String getGateUrl()
  {
    return gateUrl;
  }
  
  public void setGateUrl(String gateUrl)
  {
    this.gateUrl = gateUrl;
  }
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getParameter(String parameter)
  {
    String s = (String)parameters.get(parameter);
    return s == null ? "" : s;
  }
  
  public void setParameter(String parameter, String parameterValue)
  {
    String v = "";
    if (parameterValue != null) {
      v = parameterValue.trim();
    }
    parameters.put(parameter, v);
  }
  
  public SortedMap getAllParameters()
  {
    return parameters;
  }
  
  public String getDebugInfo()
  {
    return debugInfo;
  }
  
  public String getRequestURL()
    throws UnsupportedEncodingException
  {
    createSign();
    
    StringBuffer sb = new StringBuffer();
    String enc = TenpayUtil.getCharacterEncoding(request, response);
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
    }
    String reqPars = sb.substring(0, sb.lastIndexOf("&"));
    
    return getGateUrl() + "?" + reqPars;
  }
  
  public void doSend()
    throws UnsupportedEncodingException, IOException
  {
    response.sendRedirect(getRequestURL());
  }
  
  protected void createSign()
  {
    StringBuffer sb = new StringBuffer();
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((v != null) && (!"".equals(v)) && 
        (!"sign".equals(k)) && (!"key".equals(k))) {
        sb.append(k + "=" + v + "&");
      }
    }
    sb.append("key=" + getKey());
    
    String enc = TenpayUtil.getCharacterEncoding(request, response);
    String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
    
    setParameter("sign", sign);
    

    setDebugInfo(sb.toString() + " => sign:" + sign);
  }
  
  protected void setDebugInfo(String debugInfo)
  {
    this.debugInfo = debugInfo;
  }
  
  protected HttpServletRequest getHttpServletRequest()
  {
    return request;
  }
  
  protected HttpServletResponse getHttpServletResponse()
  {
    return response;
  }
}

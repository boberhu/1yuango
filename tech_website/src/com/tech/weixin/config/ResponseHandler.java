package com.tech.weixin.config;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.JDOMException;

public class ResponseHandler
{
  private static final String appkey = null;
  private String key;
  private SortedMap parameters;
  private String debugInfo;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private String uriEncoding;
  private Hashtable xmlMap;
  private String k;
  
  public ResponseHandler(HttpServletRequest request, HttpServletResponse response)
  {
    this.request = request;
    this.response = response;
    
    key = "";
    parameters = new TreeMap();
    debugInfo = "";
    
    uriEncoding = "";
    
    Map m = this.request.getParameterMap();
    Iterator it = m.keySet().iterator();
    while (it.hasNext())
    {
      String k = (String)it.next();
      String v = ((String[])m.get(k))[0];
      setParameter(k, v);
    }
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
  
  public void doParse(String xmlContent)
    throws JDOMException, IOException
  {
    parameters.clear();
    
    Map m = XMLUtil.doXMLParse(xmlContent);
    

    Iterator it = m.keySet().iterator();
    while (it.hasNext())
    {
      String k = (String)it.next();
      String v = (String)m.get(k);
      setParameter(k, v);
    }
  }
  
  public boolean isValidSign()
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
    sb.append("key=" + getKey());
    

    String enc = TenpayUtil.getCharacterEncoding(request, 
      response);
    String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
    
    String ValidSign = getParameter("sign").toLowerCase();
    

    setDebugInfo(sb.toString() + " => sign:" + sign + " ValidSign:" + 
      ValidSign);
    
    return ValidSign.equals(sign);
  }
  
  public boolean isWXsign()
  {
    StringBuffer sb = new StringBuffer();
    String keys = "";
    SortedMap<String, String> signParams = new TreeMap();
    Hashtable signMap = new Hashtable();
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((k != "SignMethod") && (k != "AppSignature")) {
        sb.append(k + "=" + v + "&");
      }
    }
    signMap.put("appkey", appkey);
    while (it.hasNext())
    {
      String v = this.k;
      if (sb.length() == 0) {
        sb.append(this.k + "=" + v);
      } else {
        sb.append("&" + this.k + "=" + v);
      }
    }
    String sign = Sha1Util.getSha1(sb.toString()).toString().toLowerCase();
    
    setDebugInfo(sb.toString() + " => SHA1 sign:" + sign);
    
    return sign.equals("AppSignature");
  }
  
  public boolean isWXsignfeedback()
  {
    StringBuffer sb = new StringBuffer();
    Hashtable signMap = new Hashtable();
    Set es = parameters.entrySet();
    Iterator it = es.iterator();
    while (it.hasNext())
    {
      Map.Entry entry = (Map.Entry)it.next();
      String k = (String)entry.getKey();
      String v = (String)entry.getValue();
      if ((k != "SignMethod") && (k != "AppSignature")) {
        sb.append(k + "=" + v + "&");
      }
    }
    signMap.put("appkey", appkey);
    while (it.hasNext())
    {
      String v = this.k;
      if (sb.length() == 0) {
        sb.append(this.k + "=" + v);
      } else {
        sb.append("&" + this.k + "=" + v);
      }
    }
    String sign = Sha1Util.getSha1(sb.toString()).toString().toLowerCase();
    
    setDebugInfo(sb.toString() + " => SHA1 sign:" + sign);
    
    return sign.equals("App    Signature");
  }
  
  public void sendToCFT(String msg)
    throws IOException
  {
    String strHtml = msg;
    PrintWriter out = getHttpServletResponse().getWriter();
    out.println(strHtml);
    out.flush();
    out.close();
  }
  
  public String getUriEncoding()
  {
    return uriEncoding;
  }
  
  public void setUriEncoding(String uriEncoding)
    throws UnsupportedEncodingException
  {
    if (!"".equals(uriEncoding.trim()))
    {
      this.uriEncoding = uriEncoding;
      

      String enc = TenpayUtil.getCharacterEncoding(request, response);
      Iterator it = parameters.keySet().iterator();
      while (it.hasNext())
      {
        String k = (String)it.next();
        String v = getParameter(k);
        v = new String(v.getBytes(uriEncoding.trim()), enc);
        setParameter(k, v);
      }
    }
  }
  
  public String getDebugInfo()
  {
    return debugInfo;
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

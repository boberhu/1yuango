package com.tech.tenpay.client;

import com.tech.tenpay.util.MD5Util;
import com.tech.tenpay.util.XMLUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;

public class ClientResponseHandler
{
  private String content;
  private SortedMap parameters;
  private String debugInfo;
  private String key;
  private String charset;
  
  public ClientResponseHandler()
  {
    content = "";
    parameters = new TreeMap();
    debugInfo = "";
    key = "";
    charset = "GBK";
  }
  
  public String getContent()
  {
    return content;
  }
  
  public void setContent(String content)
    throws Exception
  {
    this.content = content;
    
    doParse();
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
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getCharset()
  {
    return charset;
  }
  
  public void setCharset(String charset)
  {
    this.charset = charset;
  }
  
  public boolean isTenpaySign()
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
    

    String sign = MD5Util.MD5Encode(sb.toString(), charset).toLowerCase();
    
    String tenpaySign = getParameter("sign").toLowerCase();
    

    setDebugInfo(sb.toString() + " => sign:" + sign + 
      " tenpaySign:" + tenpaySign);
    
    return tenpaySign.equals(sign);
  }
  
  protected boolean isTenpaySign(String[] signParameterArray)
  {
    StringBuffer signPars = new StringBuffer();
    for (int index = 0; index < signParameterArray.length; index++)
    {
      String k = signParameterArray[index];
      String v = getParameter(k);
      if ((v != null) && (!"".equals(v))) {
        signPars.append(k + "=" + v + "&");
      }
    }
    signPars.append("key=" + getKey());
    

    String sign = MD5Util.MD5Encode(
      signPars.toString(), charset).toLowerCase();
    
    String tenpaySign = getParameter("sign").toLowerCase();
    

    setDebugInfo(signPars.toString() + " => sign:" + sign + 
      " tenpaySign:" + tenpaySign);
    
    return tenpaySign.equals(sign);
  }
  
  protected void setDebugInfo(String debugInfo)
  {
    this.debugInfo = debugInfo;
  }
  
  protected void doParse()
    throws JDOMException, IOException
  {
    String xmlContent = getContent();
    

    Map m = XMLUtil.doXMLParse(xmlContent);
    

    Iterator it = m.keySet().iterator();
    while (it.hasNext())
    {
      String k = (String)it.next();
      String v = (String)m.get(k);
      setParameter(k, v);
    }
  }
}

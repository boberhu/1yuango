package com.alipay.api.internal.util;

import com.alipay.api.AlipayResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AlipayLogger
{
  private static final Log clog = LogFactory.getLog("sdk.comm.err");
  private static final Log blog = LogFactory.getLog("sdk.biz.err");
  private static String osName = System.getProperties().getProperty("os.name");
  private static String ip = null;
  private static boolean needEnableLogger = true;
  
  public static void setNeedEnableLogger(boolean needEnableLogger)
  {
    needEnableLogger = needEnableLogger;
  }
  
  public static String getIp()
  {
    if (ip == null) {
      try
      {
        ip = InetAddress.getLocalHost().getHostAddress();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return ip;
  }
  
  public static void setIp(String ip)
  {
    ip = ip;
  }
  
  public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, byte[] content)
  {
    if (!needEnableLogger) {
      return;
    }
    String contentString = null;
    try
    {
      contentString = new String(content, "UTF-8");
      logCommError(e, conn, appKey, method, contentString);
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
  }
  
  public static void logCommError(Exception e, String url, String appKey, String method, byte[] content)
  {
    if (!needEnableLogger) {
      return;
    }
    String contentString = null;
    try
    {
      contentString = new String(content, "UTF-8");
      logCommError(e, url, appKey, method, contentString);
    }
    catch (UnsupportedEncodingException e1)
    {
      e1.printStackTrace();
    }
  }
  
  public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, Map<String, String> params)
  {
    if (!needEnableLogger) {
      return;
    }
    _logCommError(e, conn, null, appKey, method, params);
  }
  
  public static void logCommError(Exception e, String url, String appKey, String method, Map<String, String> params)
  {
    if (!needEnableLogger) {
      return;
    }
    _logCommError(e, null, url, appKey, method, params);
  }
  
  private static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, String content)
  {
    Map<String, String> params = parseParam(content);
    _logCommError(e, conn, null, appKey, method, params);
  }
  
  private static void logCommError(Exception e, String url, String appKey, String method, String content)
  {
    Map<String, String> params = parseParam(content);
    _logCommError(e, null, url, appKey, method, params);
  }
  
  private static void _logCommError(Exception e, HttpURLConnection conn, String url, String appKey, String method, Map<String, String> params)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    String sdkName = "alipay-sdk-java-dynamicVersionNo";
    String urlStr = null;
    String rspCode = "";
    if (conn != null)
    {
      try
      {
        urlStr = conn.getURL().toString();
        rspCode = "HTTP_ERROR_" + conn.getResponseCode();
      }
      catch (IOException localIOException) {}
    }
    else
    {
      urlStr = url;
      rspCode = "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(df.format(new Date()));
    sb.append("^_^");
    sb.append(method);
    sb.append("^_^");
    sb.append(appKey);
    sb.append("^_^");
    sb.append(getIp());
    sb.append("^_^");
    sb.append(osName);
    sb.append("^_^");
    sb.append(sdkName);
    sb.append("^_^");
    sb.append(urlStr);
    sb.append("^_^");
    sb.append(rspCode);
    sb.append("^_^");
    sb.append(e.getMessage().replaceAll("\r\n", " "));
    clog.error(sb.toString());
  }
  
  private static Map<String, String> parseParam(String contentString)
  {
    Map<String, String> params = new HashMap();
    if ((contentString == null) || (contentString.trim().equals(""))) {
      return params;
    }
    String[] paramsArray = contentString.split("\\&");
    if (paramsArray != null) {
      for (String param : paramsArray)
      {
        String[] keyValue = param.split("=");
        if ((keyValue != null) && (keyValue.length == 2)) {
          params.put(keyValue[0], keyValue[1]);
        }
      }
    }
    return params;
  }
  
  public static void logBizError(String rsp)
  {
    if (!needEnableLogger) {
      return;
    }
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    StringBuilder sb = new StringBuilder();
    sb.append(df.format(new Date()));
    sb.append("^_^");
    sb.append(rsp);
    blog.error(sb.toString());
  }
  
  public static void logErrorScene(Map<String, Object> rt, AlipayResponse tRsp, String appSecret)
  {
    if (!needEnableLogger) {
      return;
    }
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    StringBuilder sb = new StringBuilder();
    sb.append("ErrorScene");
    sb.append("^_^");
    sb.append(tRsp.getErrorCode());
    sb.append("^_^");
    sb.append(tRsp.getSubCode());
    sb.append("^_^");
    sb.append(ip);
    sb.append("^_^");
    sb.append(osName);
    sb.append("^_^");
    sb.append(df.format(new Date()));
    sb.append("^_^");
    sb.append("ProtocalMustParams:");
    appendLog((AlipayHashMap)rt.get("protocalMustParams"), sb);
    sb.append("^_^");
    sb.append("ProtocalOptParams:");
    appendLog((AlipayHashMap)rt.get("protocalOptParams"), sb);
    sb.append("^_^");
    sb.append("ApplicationParams:");
    appendLog((AlipayHashMap)rt.get("textParams"), sb);
    sb.append("^_^");
    sb.append("Body:");
    sb.append((String)rt.get("rsp"));
    blog.error(sb.toString());
  }
  
  private static void appendLog(AlipayHashMap map, StringBuilder sb)
  {
    boolean first = true;
    Set<Map.Entry<String, String>> set = map.entrySet();
    for (Map.Entry<String, String> entry : set)
    {
      if (!first) {
        sb.append("&");
      } else {
        first = false;
      }
      sb.append((String)entry.getKey()).append("=").append((String)entry.getValue());
    }
  }
}

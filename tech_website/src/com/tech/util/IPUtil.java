package com.tech.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public class IPUtil
{
  public static synchronized String getSourceFromIP(String str)
  {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    BufferedInputStream bis = null;
    StringBuffer sBuffer = new StringBuffer();
    try
    {
      URL url = new URL("http://www.ip.cn/getip.php?action=getip&ip_url=" + str);
      HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
      urlConn.setUseCaches(true);
      urlConn.setRequestMethod("GET");
      urlConn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
      bis = new BufferedInputStream(urlConn.getInputStream());
      
      byte[] tmp = new byte[2048];
      int l;
      while ((l = bis.read(tmp)) != -1)
      {
        sBuffer.append(new String(tmp, 0, l, "GB2312"));
      }
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (bis != null)
      {
        try
        {
          bis.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        bis = null;
      }
    }
    if (StringUtils.isNotBlank(sBuffer.toString())) {
      return StringUtils.substringBetween(sBuffer.toString(), "来自：", "</p>");
    }
    return null;
  }
  
  public static void main(String[] args)
  {
    System.err.println(getSourceFromIP("124.193.138.90"));
  }
}

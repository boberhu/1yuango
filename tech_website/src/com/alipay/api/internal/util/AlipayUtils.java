package com.alipay.api.internal.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.parser.json.ObjectJsonParser;
import com.alipay.api.internal.util.json.JSONReader;
import com.alipay.api.internal.util.json.JSONValidatingReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AlipayUtils
{
  private static String localIp;
  
  public static String getFileSuffix(byte[] bytes)
  {
    if ((bytes == null) || (bytes.length < 10)) {
      return null;
    }
    if ((bytes[0] == 71) && (bytes[1] == 73) && (bytes[2] == 70)) {
      return "GIF";
    }
    if ((bytes[1] == 80) && (bytes[2] == 78) && (bytes[3] == 71)) {
      return "PNG";
    }
    if ((bytes[6] == 74) && (bytes[7] == 70) && (bytes[8] == 73) && (bytes[9] == 70)) {
      return "JPG";
    }
    if ((bytes[0] == 66) && (bytes[1] == 77)) {
      return "BMP";
    }
    return null;
  }
  
  public static String getMimeType(byte[] bytes)
  {
    String suffix = getFileSuffix(bytes);
    String mimeType;
    if ("JPG".equals(suffix))
    {
      mimeType = "image/jpeg";
    }
    else
    {
      if ("GIF".equals(suffix))
      {
        mimeType = "image/gif";
      }
      else
      {
        if ("PNG".equals(suffix))
        {
          mimeType = "image/png";
        }
        else
        {
          if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
          } else {
            mimeType = "application/octet-stream";
          }
        }
      }
    }
    return mimeType;
  }
  
  public static <V> Map<String, V> cleanupMap(Map<String, V> map)
  {
    if ((map == null) || (map.isEmpty())) {
      return null;
    }
    Map<String, V> result = new HashMap(map.size());
    Set<Map.Entry<String, V>> entries = map.entrySet();
    for (Map.Entry<String, V> entry : entries) {
      if (entry.getValue() != null) {
        result.put((String)entry.getKey(), entry.getValue());
      }
    }
    return result;
  }
  
  public static Map<?, ?> parseJson(String body)
  {
    JSONReader jr = new JSONValidatingReader();
    Object obj = jr.read(body);
    if ((obj instanceof Map)) {
      return (Map)obj;
    }
    return null;
  }
  
  public static <T extends AlipayResponse> T parseResponse(String json, Class<T> clazz)
    throws AlipayApiException
  {
    ObjectJsonParser<T> parser = new ObjectJsonParser(clazz);
    return parser.parse(json);
  }
  
  public static String getLocalNetWorkIp()
  {
    if (localIp != null) {
      return localIp;
    }
    try
    {
      Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
      InetAddress ip = null;
      while (netInterfaces.hasMoreElements())
      {
        NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
        if ((!ni.isLoopback()) && (!ni.isVirtual()))
        {
          Enumeration<InetAddress> addresss = ni.getInetAddresses();
          while (addresss.hasMoreElements())
          {
            InetAddress address = (InetAddress)addresss.nextElement();
            if ((address instanceof Inet4Address))
            {
              ip = address;
              break;
            }
          }
          if (ip != null) {
            break;
          }
        }
      }
      if (ip != null) {
        localIp = ip.getHostAddress();
      } else {
        localIp = "127.0.0.1";
      }
    }
    catch (Exception e)
    {
      localIp = "127.0.0.1";
    }
    return localIp;
  }
}

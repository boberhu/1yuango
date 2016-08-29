package com.wangyin.wepaypc.util;

import java.io.ByteArrayOutputStream;

public class ByteUtil
{
  private static String hexString = "0123456789ABCDEF";
  
  public static String byte2HexUpperCase(byte[] bts)
  {
    String des = "";
    String tmp = null;
    for (int i = 0; i < bts.length; i++)
    {
      tmp = Integer.toHexString(bts[i] & 0xFF);
      if (tmp.length() == 1) {
        des = des + "0";
      }
      des = des + tmp;
    }
    return des.toUpperCase();
  }
  
  public static String byte2HexLowerCase(byte[] bts)
  {
    String des = "";
    String tmp = null;
    for (int i = 0; i < bts.length; i++)
    {
      tmp = Integer.toHexString(bts[i] & 0xFF);
      if (tmp.length() == 1) {
        des = des + "0";
      }
      des = des + tmp;
    }
    return des.toLowerCase();
  }
  
  public static String byte2HexString(byte[] src)
    throws Exception
  {
    StringBuilder stringBuilder = new StringBuilder("");
    if ((src == null) || (src.length <= 0)) {
      return null;
    }
    for (int i = 0; i < src.length; i++)
    {
      int v = src[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    String bytes = stringBuilder.toString();
    ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
    for (int i = 0; i < bytes.length(); i += 2) {
      baos.write(hexString.indexOf(bytes.charAt(i)) << 4 | 
        hexString.indexOf(bytes.charAt(i + 1)));
    }
    return new String(baos.toByteArray());
  }
}

package com.wangyin.wepaypc.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Sha256Util
{
  private static final String SHA256 = "SHA-256";
  
  public static byte[] encrypt(byte[] bts)
  {
    MessageDigest md = null;
    byte[] result = (byte[])null;
    
    byte[] bt = new byte[0];
    try
    {
      md = MessageDigest.getInstance("SHA-256");
      md.update(bts);
      result = md.digest();
    }
    catch (NoSuchAlgorithmException e)
    {
      return null;
    }
    return result;
  }
  
  public static void main(String[] args)
  {
    String str = "This is a test url:https://wangyin.com/wepay/web/pay";
    try
    {
      System.out.println(Arrays.toString(encrypt(str.getBytes("UTF-8"))));
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
  }
}

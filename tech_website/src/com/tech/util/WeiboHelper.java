package com.tech.util;

import java.io.PrintStream;

public class WeiboHelper
{
  private static String str62keys = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
  public static void main(String[] args)
  {
    System.err.println(Id2Mid("3564096450009560"));
    System.err.println(Mid2Id("zqTrn02uc"));
  }
  
  public static String IntToEnode62(Integer int10)
  {
    String s62 = "";
    int r = 0;
    while (int10.intValue() != 0)
    {
      r = Integer.parseInt(String.valueOf(int10.intValue() % 62));
      s62 = str62keys.charAt(r) + s62;
      String s = String.valueOf(Math.floor(int10.intValue() / 62));
      s = s.substring(0, s.length() - 2);
      
      int10 = Integer.valueOf(Integer.parseInt(s));
    }
    return s62;
  }
  
  public static Integer Encode62ToInt(String str62)
  {
    Integer i10 = Integer.valueOf(0);
    for (int i = 0; i < str62.length(); i++)
    {
      double n = str62.length() - i - 1;
      
      i10 = Integer.valueOf((int)(i10.intValue() + str62keys.indexOf(str62.charAt(i)) * Math.pow(62.0D, n)));
    }
    return i10;
  }
  
  public static String Mid2Id(String str62)
  {
    String id = "";
    for (int i = str62.length() - 4; i > -4; i -= 4)
    {
      int offset = i < 0 ? 0 : i;
      int len = i < 0 ? str62.length() % 4 : offset + 4;
      String str = Encode62ToInt(str62.substring(offset, len)).toString();
      if (offset > 0) {
        while (str.length() < 7) {
          str = '0' + str;
        }
      }
      id = str + id;
    }
    return id;
  }
  
  public static String Id2Mid(String str10)
  {
    String mid = "";
    for (int i = str10.length() - 7; i > -7; i -= 7)
    {
      int offset = i < 0 ? 0 : i;
      int len = i < 0 ? str10.length() % 7 : offset + 7;
      String str = IntToEnode62(Integer.valueOf(Integer.parseInt(str10.substring(offset, len))));
      if (offset > 0) {
        while (str.length() < 4) {
          str = '0' + str;
        }
      }
      mid = str + mid;
    }
    return mid;
  }
}

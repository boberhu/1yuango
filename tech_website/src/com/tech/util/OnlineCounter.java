package com.tech.util;

public class OnlineCounter
{
  private static long online = 0L;
  
  public static long getOnline()
  {
    return online;
  }
  
  public static void raise()
  {
    online += 1L;
  }
  
  public static void reduce()
  {
    online -= 1L;
  }
}

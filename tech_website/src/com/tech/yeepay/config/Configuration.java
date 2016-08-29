package com.tech.yeepay.config;

import java.util.ResourceBundle;

public class Configuration
{
  private static Object lock = new Object();
  private static Configuration config = null;
  private static ResourceBundle rb = null;
  private static final String CONFIG_FILE = "merchantInfo";
  
  private Configuration()
  {
    rb = ResourceBundle.getBundle("merchantInfo");
  }
  
  public static Configuration getInstance()
  {
    synchronized (lock)
    {
      if (config == null) {
        config = new Configuration();
      }
    }
    return config;
  }
  
  public String getValue(String key)
  {
    return rb.getString(key);
  }
}

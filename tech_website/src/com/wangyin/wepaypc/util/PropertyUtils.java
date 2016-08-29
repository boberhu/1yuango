package com.wangyin.wepaypc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class PropertyUtils
{
  private static Logger logger = Logger.getLogger(PropertyUtils.class);
  private static final String config = "conf.properties";
  private static Map<String, String> config_map = new HashMap();
  
  static
  {
    load("conf.properties");
  }
  
  public static String getProperty(String key)
  {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    return (String)config_map.get(key);
  }
  
  private static void load(String name)
  {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
    Properties p = new Properties();
    try
    {
      p.load(is);
      if ("conf.properties".equals(name)) {
        for (Map.Entry e : p.entrySet()) {
          config_map.put((String)e.getKey(), (String)e.getValue());
        }
      }
    }
    catch (IOException e)
    {
      logger.fatal("load property file failed. file name: " + name, e);
    }
  }
  
  public static void main(String[] args)
  {
    System.out.println(getProperty("wepay.des.key"));
  }
}

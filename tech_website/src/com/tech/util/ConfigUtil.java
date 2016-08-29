package com.tech.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class ConfigUtil
{
  static final Log log = LogFactory.getLog(ConfigUtil.class);
  private static Properties properties;
  
  static
  {
    InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(
      "config.properties");
    properties = new Properties();
    try
    {
      properties.load(is);
    }
    catch (IOException e)
    {
      log.error("配置文件config.properties加载失败!", e);
      try
      {
        if (is != null) {
          is.close();
        }
      }
      catch (IOException ioe)
      {
        log.error("关闭流失败!", ioe);
      }
    }
    finally
    {
      try
      {
        if (is != null) {
          is.close();
        }
      }
      catch (IOException e)
      {
        log.error("关闭流失败!", e);
      }
    }
  }
  
  public static String getValue(String key)
  {
    if (StringUtil.isBlank(key)) {
      return null;
    }
    String temp = properties.getProperty(key);
    if ((StringUtil.isBlank(temp)) || ("null".equals(temp))) {
      return "";
    }
    return temp;
  }
  
  public static String getValue(String key, String defaultValue)
  {
    if (StringUtil.isBlank(key)) {
      return null;
    }
    String temp = properties.getProperty(key);
    if ((StringUtil.isBlank(temp)) || ("null".equals(temp))) {
      return defaultValue;
    }
    return temp;
  }
  
  public static void setValue(String key, String value)
    throws IOException
  {
    if ((StringUtil.isNotBlank(key)) && (StringUtil.isNotBlank(value)))
    {
      String separator = System.getProperty("file.separator");
      String path = System.getProperty("user.dir") + separator + 
        "config.properties";
      System.err.println(path);
      OutputStream fos = new FileOutputStream(path);
      properties.setProperty(key, value);
      properties.store(fos, "Update'" + key + "'value");
    }
  }
  
  public static void main(String[] args) {}
  
  public static void readPropertiesFile(String filename)
  {
    Properties properties = new Properties();
    try
    {
      InputStream inputStream = new FileInputStream(filename);
      properties.load(inputStream);
      inputStream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String username = properties.getProperty("username");
    String passsword = properties.getProperty("password");
    String chinese = properties.getProperty("chinese");
    try
    {
      chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    System.out.println(username);
    System.out.println(passsword);
    System.out.println(chinese);
  }
  
  public static void readPropertiesFileFromXML(String filename)
  {
    Properties properties = new Properties();
    try
    {
      InputStream inputStream = new FileInputStream(filename);
      properties.loadFromXML(inputStream);
      inputStream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String username = properties.getProperty("username");
    String passsword = properties.getProperty("password");
    String chinese = properties.getProperty("chinese");
    System.out.println(username);
    System.out.println(passsword);
    System.out.println(chinese);
  }
  
  public static void writePropertiesFile(String filename)
  {
    Properties properties = new Properties();
    try
    {
      OutputStream outputStream = new FileOutputStream(filename);
      properties.setProperty("username", "myname");
      properties.setProperty("password", "mypassword");
      properties.setProperty("chinese", "中文");
      properties.store(outputStream, "author: shixing_11@sina.com");
      outputStream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void writePropertiesFileToXML(String filename)
  {
    Properties properties = new Properties();
    try
    {
      OutputStream outputStream = new FileOutputStream(filename);
      properties.setProperty("username", "myname");
      properties.setProperty("password", "mypassword");
      properties.setProperty("chinese", "中文");
      properties.storeToXML(outputStream, "author: shixing_11@sina.com");
      outputStream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

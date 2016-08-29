package com.tech.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeiboImg
{
  public static byte[] readStream(InputStream inStream)
    throws Exception
  {
    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len = -1;
    while ((len = inStream.read(buffer)) != -1) {
      outstream.write(buffer, 0, len);
    }
    outstream.close();
    inStream.close();
    

    return outstream.toByteArray();
  }
  
  public static void getImage(String urlPath, String filePath)
    throws Exception
  {
    URL url = new URL(urlPath);
    

    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    
    conn.setRequestMethod("GET");
    
    conn.setConnectTimeout(6000);
    if (conn.getResponseCode() == 200)
    {
      InputStream inputStream = conn.getInputStream();
      
      byte[] data = readStream(inputStream);
      
      File file = new File(filePath);
      
      FileOutputStream outStream = new FileOutputStream(file);
      
      outStream.write(data);
      
      outStream.close();
    }
  }
  
  public static void main(String[] args)
    throws Exception
  {
    WeiboImg testImg = new WeiboImg();
    getImage("http://tp1.sinaimg.cn/2328409452/50/5609062079", "e:\\weiboImg\\i.jpg");
  }
}

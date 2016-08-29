package com.tech.util;

import java.io.File;
import java.text.SimpleDateFormat;

public class UploadImagesUtil
{
  public static String getFolder(String path, String id)
  {
    SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
    
    path = path + "/" + id;
    File dir = new File(path);
    if (!dir.exists()) {
      try
      {
        dir.mkdirs();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return path;
  }
  
  public static void main(String[] args)
  {
    getFolder("d:\\123", "201211");
  }
}

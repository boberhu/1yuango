package com.alipay.api;

import com.alipay.api.internal.util.AlipayUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileItem
{
  private String fileName;
  private String mimeType;
  private byte[] content;
  private File file;
  
  public FileItem(File file)
  {
    this.file = file;
  }
  
  public FileItem(String filePath)
  {
    this(new File(filePath));
  }
  
  public FileItem(String fileName, byte[] content)
  {
    this.fileName = fileName;
    this.content = content;
  }
  
  public FileItem(String fileName, byte[] content, String mimeType)
  {
    this(fileName, content);
    this.mimeType = mimeType;
  }
  
  public String getFileName()
  {
    if ((fileName == null) && (file != null) && (file.exists())) {
      fileName = file.getName();
    }
    return fileName;
  }
  
  public String getMimeType()
    throws IOException
  {
    if (mimeType == null) {
      mimeType = AlipayUtils.getMimeType(getContent());
    }
    return mimeType;
  }
  
  public byte[] getContent()
    throws IOException
  {
    if ((content == null) && (file != null) && (file.exists()))
    {
      InputStream in = null;
      ByteArrayOutputStream out = null;
      try
      {
        in = new FileInputStream(file);
        out = new ByteArrayOutputStream();
        for (int ch; (ch = in.read()) != -1;)
        {
          out.write(ch);
        }
        content = out.toByteArray();
      }
      finally
      {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      }
    }
    return content;
  }
}

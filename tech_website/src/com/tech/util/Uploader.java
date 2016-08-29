package com.tech.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import sun.misc.BASE64Decoder;

public class Uploader
{
  private String url = "";
  private String fileName = "";
  private String state = "";
  private String type = "";
  private String originalName = "";
  private String size = "";
  private HttpServletRequest request = null;
  private String title = "";
  private String savePath = "/productImg/detail";
  private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
  private int maxSize = 10000;
  private HashMap<String, String> errorInfo = new HashMap<String, String>();
  
  public Uploader(HttpServletRequest request)
  {
    this.request = request;
    HashMap<String, String> tmp = errorInfo;
    tmp.put("SUCCESS", "SUCCESS");
    tmp.put("NOFILE", "未包含文件上传域");
    tmp.put("TYPE", "不允许的文件格式");
    tmp.put("SIZE", "文件大小超出限制");
    tmp.put("ENTYPE", "请求类型ENTYPE错误");
    tmp.put("REQUEST", "上传请求异常");
    tmp.put("IO", "IO异常");
    tmp.put("DIR", "目录创建失败");
    tmp.put("UNKNOWN", "未知错误");
  }
  
  public void upload()
    throws Exception
  {
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if (!isMultipart)
    {
      state = ((String)errorInfo.get("NOFILE"));
      return;
    }
    DiskFileItemFactory dff = new DiskFileItemFactory();
    System.err.println(Struts2Utils.getRequest().getServletPath());
    String savePath = getFolder(this.savePath);
    dff.setRepository(new File(savePath));
    try
    {
      ServletFileUpload sfu = new ServletFileUpload(dff);
      sfu.setSizeMax(maxSize * 1024);
      sfu.setHeaderEncoding("utf-8");
      FileItemIterator fii = sfu.getItemIterator(request);
      while (fii.hasNext())
      {
        FileItemStream fis = fii.next();
        if (!fis.isFormField())
        {
          originalName = fis.getName().substring(fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
          if (!checkFileType(originalName))
          {
            state = ((String)errorInfo.get("TYPE"));
          }
          else
          {
            fileName = getName(originalName);
            type = getFileExt(fileName);
            url = (savePath + "/" + fileName);
            BufferedInputStream in = new BufferedInputStream(fis.openStream());
            FileOutputStream out = new FileOutputStream(new File(getPhysicalPath(url)));
            BufferedOutputStream output = new BufferedOutputStream(out);
            Streams.copy(in, output, true);
            state = ((String)errorInfo.get("SUCCESS"));
            
            break;
          }
        }
        else
        {
          String fname = fis.getFieldName();
          if (fname.equals("pictitle"))
          {
            BufferedInputStream in = new BufferedInputStream(fis.openStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer result = new StringBuffer();
            while (reader.ready()) {
              result.append((char)reader.read());
            }
            title = new String(result.toString().getBytes(), "utf-8");
            reader.close();
          }
        }
      }
    }
    catch (FileUploadBase.SizeLimitExceededException e)
    {
      state = ((String)errorInfo.get("SIZE"));
    }
    catch (FileUploadBase.InvalidContentTypeException e)
    {
      state = ((String)errorInfo.get("ENTYPE"));
    }
    catch (FileUploadException e)
    {
      state = ((String)errorInfo.get("REQUEST"));
    }
    catch (Exception e)
    {
      state = ((String)errorInfo.get("UNKNOWN"));
    }
  }
  
  public void uploadBase64(String fieldName)
  {
    String savePath = getFolder(this.savePath);
    String base64Data = request.getParameter(fieldName);
    fileName = getName("test.png");
    url = (savePath + "/" + fileName);
    BASE64Decoder decoder = new BASE64Decoder();
    try
    {
      File outFile = new File(getPhysicalPath(url));
      OutputStream ro = new FileOutputStream(outFile);
      byte[] b = decoder.decodeBuffer(base64Data);
      for (int i = 0; i < b.length; i++) {
        if (b[i] < 0)
        {
          int tmp124_122 = i; byte[] tmp124_120 = b;tmp124_120[tmp124_122] = ((byte)(tmp124_120[tmp124_122] + 256));
        }
      }
      ro.write(b);
      ro.flush();
      ro.close();
      state = ((String)errorInfo.get("SUCCESS"));
    }
    catch (Exception e)
    {
      state = ((String)errorInfo.get("IO"));
    }
  }
  
  private boolean checkFileType(String fileName)
  {
    Iterator<String> type = Arrays.asList(allowFiles).iterator();
    while (type.hasNext())
    {
      String ext = (String)type.next();
      if (fileName.toLowerCase().endsWith(ext)) {
        return true;
      }
    }
    return false;
  }
  
  private String getFileExt(String fileName)
  {
    return fileName.substring(fileName.lastIndexOf("."));
  }
  
  private String getName(String fileName)
  {
    Random random = new Random();
    return this.fileName = random.nextInt(10000) + 
      System.currentTimeMillis() + getFileExt(fileName);
  }
  
  private String getFolder(String path)
  {
    SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
    path = path + "/" + formater.format(new Date());
    File dir = new File(getPhysicalPath(path));
    if (!dir.exists()) {
      try
      {
        dir.mkdirs();
      }
      catch (Exception e)
      {
        state = ((String)errorInfo.get("DIR"));
        return "";
      }
    }
    return path;
  }
  
  private String getPhysicalPath(String path)
  {
    String servletPath = request.getServletPath();
    String realPath = request.getSession().getServletContext()
      .getRealPath(servletPath);
    return new File(realPath).getParent() + "/" + path;
  }
  
  public void setSavePath(String savePath)
  {
    this.savePath = savePath;
  }
  
  public void setAllowFiles(String[] allowFiles)
  {
    this.allowFiles = allowFiles;
  }
  
  public void setMaxSize(int size)
  {
    maxSize = size;
  }
  
  public String getSize()
  {
    return size;
  }
  
  public String getUrl()
  {
    return url;
  }
  
  public String getFileName()
  {
    return fileName;
  }
  
  public String getState()
  {
    return state;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public String getType()
  {
    return type;
  }
  
  public String getOriginalName()
  {
    return originalName;
  }
}

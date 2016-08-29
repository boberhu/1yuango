package com.tech.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.tech.util.ConfigUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("FileUploadAction")
public class FileUploadAction extends BaseAction
{
  private static final long serialVersionUID = -6979064905632902960L;
  Log log = LogFactory.getLog(FileUploadAction.class);
  String folder;
  File filedata;
  String filedataContentType;
  String filedataFileName;
  String id;
  
  public String uploadImage()
  {
    if ((filedata == null) || (StringUtil.isBlank(filedataFileName))) {
      log.error("FileUploadAction.updateImage 文件上传失败!文件为空");
    }
    File savefile = null;
    try
    {
      String saveRealFilePath = ServletActionContext.getRequest()
        .getSession().getServletContext().getRealPath("/") + 
        ConfigUtil.getValue("upfilefolder");
      if (StringUtil.isNotBlank(folder)) {
        saveRealFilePath = saveRealFilePath + folder.replaceAll("[/\\\\]", "");
      }
      File fileDir = new File(saveRealFilePath);
      if (!fileDir.exists()) {
        fileDir.mkdirs();
      }
      savefile = new File(saveRealFilePath + filedataFileName);
      if (savefile.exists())
      {
        Struts2Utils.renderText("{\"err\":\"\",\"msg\":\"上传文件失败！文件已存在.请修改文件名后重新上传.}", new String[0]);
        return null;
      }
      FileUtils.copyFile(filedata, savefile);
      Struts2Utils.renderText("{\"success\":\"\",\"msg\":\"" + 
        savefile + "\"}", new String[0]);
      return null;
    }
    catch (IOException e)
    {
      log.error("上传文件失败!", e);
      Struts2Utils.renderJson("{\"err\":\"" + e.getMessage() + 
        "\",\"msg\":\"" + "上传文件失败!" + "\"}", new String[0]);
    }
    return null;
  }
  
  public File getFiledata()
  {
    return filedata;
  }
  
  public void setFiledata(File filedata)
  {
    this.filedata = filedata;
  }
  
  public String getFiledataContentType()
  {
    return filedataContentType;
  }
  
  public void setFiledataContentType(String filedataContentType)
  {
    this.filedataContentType = filedataContentType;
  }
  
  public String getFiledataFileName()
  {
    return filedataFileName;
  }
  
  public void setFiledataFileName(String filedataFileName)
  {
    this.filedataFileName = filedataFileName;
  }
  
  public String getFolder()
  {
    return folder;
  }
  
  public void setFolder(String folder)
  {
    this.folder = folder;
  }
}

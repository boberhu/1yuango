package com.alipay.api.request;

import com.alipay.api.AlipayUploadRequest;
import com.alipay.api.FileItem;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayTrustUserFileUploadResponse;
import java.util.HashMap;
import java.util.Map;

public class AlipayTrustUserFileUploadRequest
  implements AlipayUploadRequest<AlipayTrustUserFileUploadResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private FileItem fileContent;
  private String fileType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setFileContent(FileItem fileContent)
  {
    this.fileContent = fileContent;
  }
  
  public FileItem getFileContent()
  {
    return fileContent;
  }
  
  public void setFileType(String fileType)
  {
    this.fileType = fileType;
  }
  
  public String getFileType()
  {
    return fileType;
  }
  
  public String getApiVersion()
  {
    return apiVersion;
  }
  
  public void setApiVersion(String apiVersion)
  {
    this.apiVersion = apiVersion;
  }
  
  public void setTerminalType(String terminalType)
  {
    this.terminalType = terminalType;
  }
  
  public String getTerminalType()
  {
    return terminalType;
  }
  
  public void setTerminalInfo(String terminalInfo)
  {
    this.terminalInfo = terminalInfo;
  }
  
  public String getTerminalInfo()
  {
    return terminalInfo;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.trust.user.file.upload";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("file_type", fileType);
    if (udfParams != null) {
      txtParams.putAll(udfParams);
    }
    return txtParams;
  }
  
  public void putOtherTextParam(String key, String value)
  {
    if (udfParams == null) {
      udfParams = new AlipayHashMap();
    }
    udfParams.put(key, value);
  }
  
  public Map<String, FileItem> getFileParams()
  {
    Map<String, FileItem> params = new HashMap();
    params.put("file_content", fileContent);
    return params;
  }
  
  public Class<AlipayTrustUserFileUploadResponse> getResponseClass()
  {
    return AlipayTrustUserFileUploadResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPassCodeAddResponse;
import java.util.List;
import java.util.Map;

public class AlipayPassCodeAddRequest
  implements AlipayRequest<AlipayPassCodeAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String fileContent;
  private String recognitionInfo;
  private String recognitionType;
  private List<String> verifyType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setFileContent(String fileContent)
  {
    this.fileContent = fileContent;
  }
  
  public String getFileContent()
  {
    return fileContent;
  }
  
  public void setRecognitionInfo(String recognitionInfo)
  {
    this.recognitionInfo = recognitionInfo;
  }
  
  public String getRecognitionInfo()
  {
    return recognitionInfo;
  }
  
  public void setRecognitionType(String recognitionType)
  {
    this.recognitionType = recognitionType;
  }
  
  public String getRecognitionType()
  {
    return recognitionType;
  }
  
  public void setVerifyType(List<String> verifyType)
  {
    this.verifyType = verifyType;
  }
  
  public List<String> getVerifyType()
  {
    return verifyType;
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
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.pass.code.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("file_content", fileContent);
    txtParams.put("recognition_info", recognitionInfo);
    txtParams.put("recognition_type", recognitionType);
    txtParams.put("verify_type", verifyType);
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
  
  public Class<AlipayPassCodeAddResponse> getResponseClass()
  {
    return AlipayPassCodeAddResponse.class;
  }
}

package com.alipay.api;

import com.alipay.api.internal.util.AlipayHashMap;
import java.io.OutputStream;
import java.util.Map;

public class AlipayMobilePublicMultiMediaDownloadRequest
  implements AlipayRequest<AlipayMobilePublicMultiMediaDownloadResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private OutputStream outputStream;
  private String bizContent;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setBizContent(String bizContent)
  {
    this.bizContent = bizContent;
  }
  
  public String getBizContent()
  {
    return bizContent;
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
    return "alipay.mobile.public.multimedia.download";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("biz_content", bizContent);
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
  
  public OutputStream getOutputStream()
  {
    return outputStream;
  }
  
  public void setOutputStream(OutputStream outputStream)
  {
    this.outputStream = outputStream;
  }
  
  public Class<AlipayMobilePublicMultiMediaDownloadResponse> getResponseClass()
  {
    return AlipayMobilePublicMultiMediaDownloadResponse.class;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserFileUploadResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4173825332579379768L;
  @ApiField("file_identity")
  private String fileIdentity;
  
  public void setFileIdentity(String fileIdentity)
  {
    this.fileIdentity = fileIdentity;
  }
  
  public String getFileIdentity()
  {
    return fileIdentity;
  }
}

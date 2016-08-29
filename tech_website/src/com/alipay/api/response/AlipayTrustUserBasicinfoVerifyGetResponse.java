package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserBasicinfoVerifyGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5454536573378168436L;
  @ApiField("verify_info")
  private String verifyInfo;
  
  public void setVerifyInfo(String verifyInfo)
  {
    this.verifyInfo = verifyInfo;
  }
  
  public String getVerifyInfo()
  {
    return verifyInfo;
  }
}

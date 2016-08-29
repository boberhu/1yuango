package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserStandardVerifyGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4135914379863918718L;
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

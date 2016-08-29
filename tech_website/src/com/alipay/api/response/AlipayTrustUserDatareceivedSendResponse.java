package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserDatareceivedSendResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7395828961635834355L;
  @ApiField("is_successful")
  private String isSuccessful;
  
  public void setIsSuccessful(String isSuccessful)
  {
    this.isSuccessful = isSuccessful;
  }
  
  public String getIsSuccessful()
  {
    return isSuccessful;
  }
}

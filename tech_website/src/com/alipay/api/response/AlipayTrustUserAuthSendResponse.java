package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserAuthSendResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5591646862841948776L;
  @ApiField("result")
  private String result;
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getResult()
  {
    return result;
  }
}

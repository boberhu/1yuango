package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayZdatafrontDatatransferedSendResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8183632579799382418L;
  @ApiField("success")
  private String success;
  
  public void setSuccess(String success)
  {
    this.success = success;
  }
  
  public String getSuccess()
  {
    return success;
  }
}

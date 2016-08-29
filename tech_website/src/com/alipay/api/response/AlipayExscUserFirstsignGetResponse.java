package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayExscUserFirstsignGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8715442851544292563L;
  @ApiField("biz_type")
  private String bizType;
  @ApiField("success")
  private Boolean success;
  
  public void setBizType(String bizType)
  {
    this.bizType = bizType;
  }
  
  public String getBizType()
  {
    return bizType;
  }
  
  public void setSuccess(Boolean success)
  {
    this.success = success;
  }
  
  public Boolean getSuccess()
  {
    return success;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayPassCodeVerifyResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8352854133249995149L;
  @ApiField("biz_result")
  private String bizResult;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("success")
  private Boolean success;
  
  public void setBizResult(String bizResult)
  {
    this.bizResult = bizResult;
  }
  
  public String getBizResult()
  {
    return bizResult;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorCode()
  {
    return errorCode;
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

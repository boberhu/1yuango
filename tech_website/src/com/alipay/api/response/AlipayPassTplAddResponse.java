package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayPassTplAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2321334927539885271L;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("result")
  private String result;
  @ApiField("success")
  private String success;
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorCode()
  {
    return errorCode;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getResult()
  {
    return result;
  }
  
  public void setSuccess(String success)
  {
    this.success = success;
  }
  
  public String getSuccess()
  {
    return success;
  }
}

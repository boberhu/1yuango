package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMemberCardDeletecardResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8386417549371554143L;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("error_msg")
  private String errorMsg;
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
  
  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }
  
  public String getErrorMsg()
  {
    return errorMsg;
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

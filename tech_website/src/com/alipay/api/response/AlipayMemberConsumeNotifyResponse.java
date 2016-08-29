package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMemberConsumeNotifyResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1537261658484769387L;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("error_msg")
  private String errorMsg;
  @ApiField("ext_card_no")
  private String extCardNo;
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
  
  public void setExtCardNo(String extCardNo)
  {
    this.extCardNo = extCardNo;
  }
  
  public String getExtCardNo()
  {
    return extCardNo;
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

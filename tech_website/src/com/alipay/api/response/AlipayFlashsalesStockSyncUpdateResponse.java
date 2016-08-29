package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayFlashsalesStockSyncUpdateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6622568537267913237L;
  @ApiField("biz_result")
  private String bizResult;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("success")
  private String success;
  
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
  
  public void setSuccess(String success)
  {
    this.success = success;
  }
  
  public String getSuccess()
  {
    return success;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayPassCodeAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3517579882768182227L;
  @ApiListField("biz_result")
  @ApiField("string")
  private List<String> bizResult;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("success")
  private Boolean success;
  
  public void setBizResult(List<String> bizResult)
  {
    this.bizResult = bizResult;
  }
  
  public List<String> getBizResult()
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

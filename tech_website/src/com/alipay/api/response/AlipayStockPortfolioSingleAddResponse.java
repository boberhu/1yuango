package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayStockPortfolioSingleAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1492538846331618228L;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  @ApiField("success")
  private Boolean success;
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
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

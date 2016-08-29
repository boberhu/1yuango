package com.alipay.api;

import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicMultiMediaDownloadResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4500718209713594926L;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  
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
}

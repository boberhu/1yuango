package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicMessagePushResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5388466372547713232L;
  @ApiField("code")
  private String code;
  @ApiField("data")
  private String data;
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
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getData()
  {
    return data;
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

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicMessageTotalSendResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1429193978642469972L;
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

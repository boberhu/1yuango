package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicShortlinkCreateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4453153745396365692L;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  @ApiField("shortlink")
  private String shortlink;
  
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
  
  public void setShortlink(String shortlink)
  {
    this.shortlink = shortlink;
  }
  
  public String getShortlink()
  {
    return shortlink;
  }
}

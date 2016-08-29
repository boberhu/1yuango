package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeShopInfoDeleteResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2281172673887545127L;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
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

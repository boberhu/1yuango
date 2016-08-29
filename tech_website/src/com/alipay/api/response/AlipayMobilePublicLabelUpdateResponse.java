package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicLabelUpdateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7861642282312679892L;
  @ApiField("code")
  private String code;
  @ApiField("id")
  private Long id;
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
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public Long getId()
  {
    return id;
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

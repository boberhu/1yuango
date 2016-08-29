package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicMenuGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1244523866264877838L;
  @ApiField("code")
  private String code;
  @ApiField("menu_content")
  private String menuContent;
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
  
  public void setMenuContent(String menuContent)
  {
    this.menuContent = menuContent;
  }
  
  public String getMenuContent()
  {
    return menuContent;
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

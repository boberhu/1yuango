package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeShopPublicBindResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1726882282821574224L;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  @ApiField("public_logo")
  private String publicLogo;
  @ApiField("public_name")
  private String publicName;
  
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
  
  public void setPublicLogo(String publicLogo)
  {
    this.publicLogo = publicLogo;
  }
  
  public String getPublicLogo()
  {
    return publicLogo;
  }
  
  public void setPublicName(String publicName)
  {
    this.publicName = publicName;
  }
  
  public String getPublicName()
  {
    return publicName;
  }
}

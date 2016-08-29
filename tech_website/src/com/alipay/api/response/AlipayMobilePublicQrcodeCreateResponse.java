package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicQrcodeCreateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8514575666631561737L;
  @ApiField("code")
  private Long code;
  @ApiField("code_img")
  private String codeImg;
  @ApiField("expire_second")
  private Long expireSecond;
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
  
  public void setCodeImg(String codeImg)
  {
    this.codeImg = codeImg;
  }
  
  public String getCodeImg()
  {
    return codeImg;
  }
  
  public void setExpireSecond(Long expireSecond)
  {
    this.expireSecond = expireSecond;
  }
  
  public Long getExpireSecond()
  {
    return expireSecond;
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

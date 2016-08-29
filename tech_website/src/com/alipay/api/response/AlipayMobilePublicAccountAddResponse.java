package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicAccountAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1851814154776448771L;
  @ApiField("agreement_id")
  private String agreementId;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  
  public void setAgreementId(String agreementId)
  {
    this.agreementId = agreementId;
  }
  
  public String getAgreementId()
  {
    return agreementId;
  }
  
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

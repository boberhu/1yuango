package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.PublicBindAccount;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayMobilePublicAccountQueryResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6765281126572341397L;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  @ApiListField("public_bind_accounts")
  @ApiField("public_bind_account")
  private List<PublicBindAccount> publicBindAccounts;
  
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
  
  public void setPublicBindAccounts(List<PublicBindAccount> publicBindAccounts)
  {
    this.publicBindAccounts = publicBindAccounts;
  }
  
  public List<PublicBindAccount> getPublicBindAccounts()
  {
    return publicBindAccounts;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayAccount;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserAccountGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6339624987627534823L;
  @ApiField("alipay_account")
  private AlipayAccount alipayAccount;
  
  public void setAlipayAccount(AlipayAccount alipayAccount)
  {
    this.alipayAccount = alipayAccount;
  }
  
  public AlipayAccount getAlipayAccount()
  {
    return alipayAccount;
  }
}

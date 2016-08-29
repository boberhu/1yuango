package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserTestResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3351693575345999859L;
  @ApiField("ret1")
  private String ret1;
  
  public void setRet1(String ret1)
  {
    this.ret1 = ret1;
  }
  
  public String getRet1()
  {
    return ret1;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayContract;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserContractGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7882885133229738597L;
  @ApiField("alipay_contract")
  private AlipayContract alipayContract;
  
  public void setAlipayContract(AlipayContract alipayContract)
  {
    this.alipayContract = alipayContract;
  }
  
  public AlipayContract getAlipayContract()
  {
    return alipayContract;
  }
}

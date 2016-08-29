package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEbppBillPayurlGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3318842159862648945L;
  @ApiField("pay_url")
  private String payUrl;
  
  public void setPayUrl(String payUrl)
  {
    this.payUrl = payUrl;
  }
  
  public String getPayUrl()
  {
    return payUrl;
  }
}

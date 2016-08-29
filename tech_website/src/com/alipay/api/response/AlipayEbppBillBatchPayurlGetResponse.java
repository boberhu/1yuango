package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEbppBillBatchPayurlGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5825788367638414264L;
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

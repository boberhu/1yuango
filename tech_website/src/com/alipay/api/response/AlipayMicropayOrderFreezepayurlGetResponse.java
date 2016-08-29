package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderFreezepayurlGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7786895138151422164L;
  @ApiField("pay_freeze_url")
  private String payFreezeUrl;
  
  public void setPayFreezeUrl(String payFreezeUrl)
  {
    this.payFreezeUrl = payFreezeUrl;
  }
  
  public String getPayFreezeUrl()
  {
    return payFreezeUrl;
  }
}

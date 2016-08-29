package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.MicroPayOrderDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4413561557797817242L;
  @ApiField("micro_pay_order_detail")
  private MicroPayOrderDetail microPayOrderDetail;
  
  public void setMicroPayOrderDetail(MicroPayOrderDetail microPayOrderDetail)
  {
    this.microPayOrderDetail = microPayOrderDetail;
  }
  
  public MicroPayOrderDetail getMicroPayOrderDetail()
  {
    return microPayOrderDetail;
  }
}

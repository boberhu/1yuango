package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.MicroPayOrderDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderFreezeResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4877958176164315496L;
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

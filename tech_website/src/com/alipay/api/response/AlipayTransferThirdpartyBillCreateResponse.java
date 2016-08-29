package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTransferThirdpartyBillCreateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2539163178842564794L;
  @ApiField("order_id")
  private String orderId;
  @ApiField("order_type")
  private String orderType;
  @ApiField("payment_id")
  private String paymentId;
  
  public void setOrderId(String orderId)
  {
    this.orderId = orderId;
  }
  
  public String getOrderId()
  {
    return orderId;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setPaymentId(String paymentId)
  {
    this.paymentId = paymentId;
  }
  
  public String getPaymentId()
  {
    return paymentId;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEbppBillPayResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2492275488364723292L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("merchant_order_no")
  private String merchantOrderNo;
  @ApiField("order_status")
  private String orderStatus;
  @ApiField("order_type")
  private String orderType;
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setOrderStatus(String orderStatus)
  {
    this.orderStatus = orderStatus;
  }
  
  public String getOrderStatus()
  {
    return orderStatus;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
}

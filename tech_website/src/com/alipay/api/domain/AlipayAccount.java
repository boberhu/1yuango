package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAccount
  extends AlipayObject
{
  private static final long serialVersionUID = 1525411533315413994L;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("available_amount")
  private String availableAmount;
  @ApiField("freeze_amount")
  private String freezeAmount;
  @ApiField("total_amount")
  private String totalAmount;
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getAvailableAmount()
  {
    return availableAmount;
  }
  
  public void setAvailableAmount(String availableAmount)
  {
    this.availableAmount = availableAmount;
  }
  
  public String getFreezeAmount()
  {
    return freezeAmount;
  }
  
  public void setFreezeAmount(String freezeAmount)
  {
    this.freezeAmount = freezeAmount;
  }
  
  public String getTotalAmount()
  {
    return totalAmount;
  }
  
  public void setTotalAmount(String totalAmount)
  {
    this.totalAmount = totalAmount;
  }
}

package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AccountFreeze
  extends AlipayObject
{
  private static final long serialVersionUID = 1489769373575685399L;
  @ApiField("freeze_amount")
  private String freezeAmount;
  @ApiField("freeze_name")
  private String freezeName;
  @ApiField("freeze_type")
  private String freezeType;
  
  public String getFreezeAmount()
  {
    return freezeAmount;
  }
  
  public void setFreezeAmount(String freezeAmount)
  {
    this.freezeAmount = freezeAmount;
  }
  
  public String getFreezeName()
  {
    return freezeName;
  }
  
  public void setFreezeName(String freezeName)
  {
    this.freezeName = freezeName;
  }
  
  public String getFreezeType()
  {
    return freezeType;
  }
  
  public void setFreezeType(String freezeType)
  {
    this.freezeType = freezeType;
  }
}

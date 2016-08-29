package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class ChargeInstMode
  extends AlipayObject
{
  private static final long serialVersionUID = 8247287324226999197L;
  @ApiField("charge_inst")
  private String chargeInst;
  @ApiField("charge_inst_name")
  private String chargeInstName;
  @ApiField("city")
  private String city;
  @ApiField("province")
  private String province;
  
  public String getChargeInst()
  {
    return chargeInst;
  }
  
  public void setChargeInst(String chargeInst)
  {
    this.chargeInst = chargeInst;
  }
  
  public String getChargeInstName()
  {
    return chargeInstName;
  }
  
  public void setChargeInstName(String chargeInstName)
  {
    this.chargeInstName = chargeInstName;
  }
  
  public String getCity()
  {
    return city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getProvince()
  {
    return province;
  }
  
  public void setProvince(String province)
  {
    this.province = province;
  }
}

package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AreaInfo
  extends AlipayObject
{
  private static final long serialVersionUID = 3186378738424915737L;
  @ApiField("city")
  private String city;
  @ApiField("province")
  private String province;
  
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

package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class DeliverAddress
  extends AlipayObject
{
  private static final long serialVersionUID = 5118667694999699433L;
  @ApiField("address")
  private String address;
  @ApiField("address_code")
  private String addressCode;
  @ApiField("default_deliver_address")
  private String defaultDeliverAddress;
  @ApiField("deliver_area")
  private String deliverArea;
  @ApiField("deliver_city")
  private String deliverCity;
  @ApiField("deliver_fullname")
  private String deliverFullname;
  @ApiField("deliver_mobile")
  private String deliverMobile;
  @ApiField("deliver_phone")
  private String deliverPhone;
  @ApiField("deliver_province")
  private String deliverProvince;
  @ApiField("zip")
  private String zip;
  
  public String getAddress()
  {
    return address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getAddressCode()
  {
    return addressCode;
  }
  
  public void setAddressCode(String addressCode)
  {
    this.addressCode = addressCode;
  }
  
  public String getDefaultDeliverAddress()
  {
    return defaultDeliverAddress;
  }
  
  public void setDefaultDeliverAddress(String defaultDeliverAddress)
  {
    this.defaultDeliverAddress = defaultDeliverAddress;
  }
  
  public String getDeliverArea()
  {
    return deliverArea;
  }
  
  public void setDeliverArea(String deliverArea)
  {
    this.deliverArea = deliverArea;
  }
  
  public String getDeliverCity()
  {
    return deliverCity;
  }
  
  public void setDeliverCity(String deliverCity)
  {
    this.deliverCity = deliverCity;
  }
  
  public String getDeliverFullname()
  {
    return deliverFullname;
  }
  
  public void setDeliverFullname(String deliverFullname)
  {
    this.deliverFullname = deliverFullname;
  }
  
  public String getDeliverMobile()
  {
    return deliverMobile;
  }
  
  public void setDeliverMobile(String deliverMobile)
  {
    this.deliverMobile = deliverMobile;
  }
  
  public String getDeliverPhone()
  {
    return deliverPhone;
  }
  
  public void setDeliverPhone(String deliverPhone)
  {
    this.deliverPhone = deliverPhone;
  }
  
  public String getDeliverProvince()
  {
    return deliverProvince;
  }
  
  public void setDeliverProvince(String deliverProvince)
  {
    this.deliverProvince = deliverProvince;
  }
  
  public String getZip()
  {
    return zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
}

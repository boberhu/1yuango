package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicGisGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3325385974443832235L;
  @ApiField("accuracy")
  private String accuracy;
  @ApiField("city")
  private String city;
  @ApiField("code")
  private String code;
  @ApiField("latitude")
  private String latitude;
  @ApiField("longitude")
  private String longitude;
  @ApiField("msg")
  private String msg;
  @ApiField("province")
  private String province;
  
  public void setAccuracy(String accuracy)
  {
    this.accuracy = accuracy;
  }
  
  public String getAccuracy()
  {
    return accuracy;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCity()
  {
    return city;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public void setLatitude(String latitude)
  {
    this.latitude = latitude;
  }
  
  public String getLatitude()
  {
    return latitude;
  }
  
  public void setLongitude(String longitude)
  {
    this.longitude = longitude;
  }
  
  public String getLongitude()
  {
    return longitude;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
  
  public void setProvince(String province)
  {
    this.province = province;
  }
  
  public String getProvince()
  {
    return province;
  }
}

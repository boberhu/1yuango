package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeDeviceInfoGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7494387878464475675L;
  @ApiField("auth_id")
  private String authId;
  @ApiField("auth_type")
  private String authType;
  @ApiField("bssid")
  private String bssid;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  @ApiField("password")
  private String password;
  @ApiField("shop_id")
  private String shopId;
  @ApiField("ssid")
  private String ssid;
  @ApiField("status")
  private String status;
  
  public void setAuthId(String authId)
  {
    this.authId = authId;
  }
  
  public String getAuthId()
  {
    return authId;
  }
  
  public void setAuthType(String authType)
  {
    this.authType = authType;
  }
  
  public String getAuthType()
  {
    return authType;
  }
  
  public void setBssid(String bssid)
  {
    this.bssid = bssid;
  }
  
  public String getBssid()
  {
    return bssid;
  }
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
  {
    return code;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public void setShopId(String shopId)
  {
    this.shopId = shopId;
  }
  
  public String getShopId()
  {
    return shopId;
  }
  
  public void setSsid(String ssid)
  {
    this.ssid = ssid;
  }
  
  public String getSsid()
  {
    return ssid;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
}

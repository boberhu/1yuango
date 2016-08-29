package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAppTokenGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2114295952749855351L;
  @ApiField("app_access_token")
  private String appAccessToken;
  @ApiField("expires_in")
  private Long expiresIn;
  
  public void setAppAccessToken(String appAccessToken)
  {
    this.appAccessToken = appAccessToken;
  }
  
  public String getAppAccessToken()
  {
    return appAccessToken;
  }
  
  public void setExpiresIn(Long expiresIn)
  {
    this.expiresIn = expiresIn;
  }
  
  public Long getExpiresIn()
  {
    return expiresIn;
  }
}

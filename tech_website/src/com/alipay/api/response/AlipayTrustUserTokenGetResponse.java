package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserTokenGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3534379679729821838L;
  @ApiField("access_token")
  private String accessToken;
  @ApiField("refresh_token")
  private String refreshToken;
  
  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }
  
  public String getAccessToken()
  {
    return accessToken;
  }
  
  public void setRefreshToken(String refreshToken)
  {
    this.refreshToken = refreshToken;
  }
  
  public String getRefreshToken()
  {
    return refreshToken;
  }
}

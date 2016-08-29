package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySystemOauthTokenResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5578712872478859169L;
  @ApiField("access_token")
  private String accessToken;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("expires_in")
  private String expiresIn;
  @ApiField("re_expires_in")
  private String reExpiresIn;
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
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setExpiresIn(String expiresIn)
  {
    this.expiresIn = expiresIn;
  }
  
  public String getExpiresIn()
  {
    return expiresIn;
  }
  
  public void setReExpiresIn(String reExpiresIn)
  {
    this.reExpiresIn = reExpiresIn;
  }
  
  public String getReExpiresIn()
  {
    return reExpiresIn;
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

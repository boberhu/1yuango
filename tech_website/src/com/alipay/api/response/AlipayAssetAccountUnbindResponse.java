package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAssetAccountUnbindResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2432783925337719881L;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("provider_id")
  private String providerId;
  @ApiField("provider_user_id")
  private String providerUserId;
  @ApiField("provider_user_name")
  private String providerUserName;
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setProviderId(String providerId)
  {
    this.providerId = providerId;
  }
  
  public String getProviderId()
  {
    return providerId;
  }
  
  public void setProviderUserId(String providerUserId)
  {
    this.providerUserId = providerUserId;
  }
  
  public String getProviderUserId()
  {
    return providerUserId;
  }
  
  public void setProviderUserName(String providerUserName)
  {
    this.providerUserName = providerUserName;
  }
  
  public String getProviderUserName()
  {
    return providerUserName;
  }
}

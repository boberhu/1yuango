package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AssetAccountResult
  extends AlipayObject
{
  private static final long serialVersionUID = 1782471141874624678L;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("consumer_id")
  private String consumerId;
  @ApiField("provider_id")
  private String providerId;
  @ApiField("provider_user_id")
  private String providerUserId;
  @ApiField("provider_user_name")
  private String providerUserName;
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getConsumerId()
  {
    return consumerId;
  }
  
  public void setConsumerId(String consumerId)
  {
    this.consumerId = consumerId;
  }
  
  public String getProviderId()
  {
    return providerId;
  }
  
  public void setProviderId(String providerId)
  {
    this.providerId = providerId;
  }
  
  public String getProviderUserId()
  {
    return providerUserId;
  }
  
  public void setProviderUserId(String providerUserId)
  {
    this.providerUserId = providerUserId;
  }
  
  public String getProviderUserName()
  {
    return providerUserName;
  }
  
  public void setProviderUserName(String providerUserName)
  {
    this.providerUserName = providerUserName;
  }
}

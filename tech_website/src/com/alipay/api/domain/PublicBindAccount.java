package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class PublicBindAccount
  extends AlipayObject
{
  private static final long serialVersionUID = 1826482699227742432L;
  @ApiField("agreement_id")
  private String agreementId;
  @ApiField("app_id")
  private String appId;
  @ApiField("bind_account_no")
  private String bindAccountNo;
  @ApiField("display_name")
  private String displayName;
  @ApiField("from_user_id")
  private String fromUserId;
  @ApiField("real_name")
  private String realName;
  
  public String getAgreementId()
  {
    return agreementId;
  }
  
  public void setAgreementId(String agreementId)
  {
    this.agreementId = agreementId;
  }
  
  public String getAppId()
  {
    return appId;
  }
  
  public void setAppId(String appId)
  {
    this.appId = appId;
  }
  
  public String getBindAccountNo()
  {
    return bindAccountNo;
  }
  
  public void setBindAccountNo(String bindAccountNo)
  {
    this.bindAccountNo = bindAccountNo;
  }
  
  public String getDisplayName()
  {
    return displayName;
  }
  
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }
  
  public String getFromUserId()
  {
    return fromUserId;
  }
  
  public void setFromUserId(String fromUserId)
  {
    this.fromUserId = fromUserId;
  }
  
  public String getRealName()
  {
    return realName;
  }
  
  public void setRealName(String realName)
  {
    this.realName = realName;
  }
}

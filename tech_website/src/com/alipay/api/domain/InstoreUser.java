package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class InstoreUser
  extends AlipayObject
{
  private static final long serialVersionUID = 5317757196775347415L;
  @ApiField("is_birthday")
  private Boolean isBirthday;
  @ApiField("user_id")
  private String userId;
  
  public Boolean getIsBirthday()
  {
    return isBirthday;
  }
  
  public void setIsBirthday(Boolean isBirthday)
  {
    this.isBirthday = isBirthday;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
}

package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserDetail
  extends AlipayObject
{
  private static final long serialVersionUID = 1864167833824447442L;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("certified")
  private Boolean certified;
  @ApiField("logon_id")
  private String logonId;
  @ApiField("real_name")
  private String realName;
  @ApiField("sex")
  private String sex;
  @ApiField("user_status")
  private String userStatus;
  @ApiField("user_type")
  private String userType;
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public Boolean getCertified()
  {
    return certified;
  }
  
  public void setCertified(Boolean certified)
  {
    this.certified = certified;
  }
  
  public String getLogonId()
  {
    return logonId;
  }
  
  public void setLogonId(String logonId)
  {
    this.logonId = logonId;
  }
  
  public String getRealName()
  {
    return realName;
  }
  
  public void setRealName(String realName)
  {
    this.realName = realName;
  }
  
  public String getSex()
  {
    return sex;
  }
  
  public void setSex(String sex)
  {
    this.sex = sex;
  }
  
  public String getUserStatus()
  {
    return userStatus;
  }
  
  public void setUserStatus(String userStatus)
  {
    this.userStatus = userStatus;
  }
  
  public String getUserType()
  {
    return userType;
  }
  
  public void setUserType(String userType)
  {
    this.userType = userType;
  }
}

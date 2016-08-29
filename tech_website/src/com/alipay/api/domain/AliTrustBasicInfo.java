package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class AliTrustBasicInfo
  extends AlipayObject
{
  private static final long serialVersionUID = 2348158796921461359L;
  @ApiField("birthday")
  private Date birthday;
  @ApiField("gender")
  private String gender;
  @ApiField("is_certified")
  private String isCertified;
  @ApiField("message")
  private String message;
  @ApiField("name")
  private String name;
  
  public Date getBirthday()
  {
    return birthday;
  }
  
  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }
  
  public String getGender()
  {
    return gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getIsCertified()
  {
    return isCertified;
  }
  
  public void setIsCertified(String isCertified)
  {
    this.isCertified = isCertified;
  }
  
  public String getMessage()
  {
    return message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}

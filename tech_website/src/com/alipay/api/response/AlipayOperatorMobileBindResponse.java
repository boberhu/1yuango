package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayOperatorMobileBindResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3582537388619397152L;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("certificate")
  private String certificate;
  @ApiField("mobile_no")
  private String mobileNo;
  @ApiField("status")
  private String status;
  @ApiField("user_name")
  private String userName;
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setCertificate(String certificate)
  {
    this.certificate = certificate;
  }
  
  public String getCertificate()
  {
    return certificate;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public String getMobileNo()
  {
    return mobileNo;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getUserName()
  {
    return userName;
  }
}

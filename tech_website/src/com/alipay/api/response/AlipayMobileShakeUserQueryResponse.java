package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobileShakeUserQueryResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7311499449835579666L;
  @ApiField("bizdata")
  private String bizdata;
  @ApiField("logon_id")
  private String logonId;
  @ApiField("pass_id")
  private String passId;
  @ApiField("user_id")
  private String userId;
  
  public void setBizdata(String bizdata)
  {
    this.bizdata = bizdata;
  }
  
  public String getBizdata()
  {
    return bizdata;
  }
  
  public void setLogonId(String logonId)
  {
    this.logonId = logonId;
  }
  
  public String getLogonId()
  {
    return logonId;
  }
  
  public void setPassId(String passId)
  {
    this.passId = passId;
  }
  
  public String getPassId()
  {
    return passId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public String getUserId()
  {
    return userId;
  }
}

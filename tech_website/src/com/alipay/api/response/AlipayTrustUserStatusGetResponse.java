package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class AlipayTrustUserStatusGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4239759855353965226L;
  @ApiField("auth_time")
  private Date authTime;
  @ApiField("over_time")
  private Date overTime;
  @ApiField("request_time")
  private Date requestTime;
  @ApiField("status")
  private String status;
  
  public void setAuthTime(Date authTime)
  {
    this.authTime = authTime;
  }
  
  public Date getAuthTime()
  {
    return authTime;
  }
  
  public void setOverTime(Date overTime)
  {
    this.overTime = overTime;
  }
  
  public Date getOverTime()
  {
    return overTime;
  }
  
  public void setRequestTime(Date requestTime)
  {
    this.requestTime = requestTime;
  }
  
  public Date getRequestTime()
  {
    return requestTime;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserAdmissionGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2349859282662638946L;
  @ApiField("admission_info")
  private String admissionInfo;
  
  public void setAdmissionInfo(String admissionInfo)
  {
    this.admissionInfo = admissionInfo;
  }
  
  public String getAdmissionInfo()
  {
    return admissionInfo;
  }
}

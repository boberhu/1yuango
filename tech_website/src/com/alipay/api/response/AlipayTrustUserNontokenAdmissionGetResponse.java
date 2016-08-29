package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserNontokenAdmissionGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3666558139881249992L;
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

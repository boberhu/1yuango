package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySecurityRiskDetectResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3424651384192834269L;
  @ApiField("risk_code")
  private String riskCode;
  @ApiField("risk_level")
  private Long riskLevel;
  
  public void setRiskCode(String riskCode)
  {
    this.riskCode = riskCode;
  }
  
  public String getRiskCode()
  {
    return riskCode;
  }
  
  public void setRiskLevel(Long riskLevel)
  {
    this.riskLevel = riskLevel;
  }
  
  public Long getRiskLevel()
  {
    return riskLevel;
  }
}

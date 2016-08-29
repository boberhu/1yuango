package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AliTrustRiskIdentify
  extends AlipayObject
{
  private static final long serialVersionUID = 7579228932572266873L;
  @ApiField("is_risk")
  private String isRisk;
  @ApiField("risk_tag")
  private String riskTag;
  
  public String getIsRisk()
  {
    return isRisk;
  }
  
  public void setIsRisk(String isRisk)
  {
    this.isRisk = isRisk;
  }
  
  public String getRiskTag()
  {
    return riskTag;
  }
  
  public void setRiskTag(String riskTag)
  {
    this.riskTag = riskTag;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AliTrustRiskIdentify;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserRiskidentifyGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5113784932821756624L;
  @ApiField("ali_trust_risk_identify")
  private AliTrustRiskIdentify aliTrustRiskIdentify;
  
  public void setAliTrustRiskIdentify(AliTrustRiskIdentify aliTrustRiskIdentify)
  {
    this.aliTrustRiskIdentify = aliTrustRiskIdentify;
  }
  
  public AliTrustRiskIdentify getAliTrustRiskIdentify()
  {
    return aliTrustRiskIdentify;
  }
}

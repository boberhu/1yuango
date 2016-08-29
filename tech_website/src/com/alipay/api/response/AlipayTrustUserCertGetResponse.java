package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AliTrustCert;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserCertGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5323457488419883418L;
  @ApiField("ali_trust_cert")
  private AliTrustCert aliTrustCert;
  
  public void setAliTrustCert(AliTrustCert aliTrustCert)
  {
    this.aliTrustCert = aliTrustCert;
  }
  
  public AliTrustCert getAliTrustCert()
  {
    return aliTrustCert;
  }
}

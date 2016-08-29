package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AliTrustRiskIdentify;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserNontokenRiskidentifyGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6523257734928618555L;
  @ApiField("ali_trust_risk_identify")
  private AliTrustRiskIdentify aliTrustRiskIdentify;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  
  public void setAliTrustRiskIdentify(AliTrustRiskIdentify aliTrustRiskIdentify)
  {
    this.aliTrustRiskIdentify = aliTrustRiskIdentify;
  }
  
  public AliTrustRiskIdentify getAliTrustRiskIdentify()
  {
    return aliTrustRiskIdentify;
  }
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
  {
    return code;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AliTrustScore;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserScoreGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3268849389889347676L;
  @ApiField("ali_trust_score")
  private AliTrustScore aliTrustScore;
  
  public void setAliTrustScore(AliTrustScore aliTrustScore)
  {
    this.aliTrustScore = aliTrustScore;
  }
  
  public AliTrustScore getAliTrustScore()
  {
    return aliTrustScore;
  }
}

package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AliTrustScore
  extends AlipayObject
{
  private static final long serialVersionUID = 3372191883223541168L;
  @ApiField("score")
  private Long score;
  
  public Long getScore()
  {
    return score;
  }
  
  public void setScore(Long score)
  {
    this.score = score;
  }
}

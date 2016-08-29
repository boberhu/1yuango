package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayPointBalanceGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2257177569924483287L;
  @ApiField("point_amount")
  private Long pointAmount;
  
  public void setPointAmount(Long pointAmount)
  {
    this.pointAmount = pointAmount;
  }
  
  public Long getPointAmount()
  {
    return pointAmount;
  }
}

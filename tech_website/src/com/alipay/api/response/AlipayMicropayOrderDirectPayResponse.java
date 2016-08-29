package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.SinglePayDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderDirectPayResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3619841554628963437L;
  @ApiField("single_pay_detail")
  private SinglePayDetail singlePayDetail;
  
  public void setSinglePayDetail(SinglePayDetail singlePayDetail)
  {
    this.singlePayDetail = singlePayDetail;
  }
  
  public SinglePayDetail getSinglePayDetail()
  {
    return singlePayDetail;
  }
}

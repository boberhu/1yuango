package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.SinglePayDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderConfirmpayurlGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8885867117196173443L;
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

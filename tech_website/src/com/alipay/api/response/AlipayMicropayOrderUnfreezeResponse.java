package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.UnfreezeOrderDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMicropayOrderUnfreezeResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6333727845698493228L;
  @ApiField("unfreeze_order_detail")
  private UnfreezeOrderDetail unfreezeOrderDetail;
  
  public void setUnfreezeOrderDetail(UnfreezeOrderDetail unfreezeOrderDetail)
  {
    this.unfreezeOrderDetail = unfreezeOrderDetail;
  }
  
  public UnfreezeOrderDetail getUnfreezeOrderDetail()
  {
    return unfreezeOrderDetail;
  }
}

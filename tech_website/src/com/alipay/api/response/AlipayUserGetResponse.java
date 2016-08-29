package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayUserDetail;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5226955943668618565L;
  @ApiField("alipay_user_detail")
  private AlipayUserDetail alipayUserDetail;
  
  public void setAlipayUserDetail(AlipayUserDetail alipayUserDetail)
  {
    this.alipayUserDetail = alipayUserDetail;
  }
  
  public AlipayUserDetail getAlipayUserDetail()
  {
    return alipayUserDetail;
  }
}

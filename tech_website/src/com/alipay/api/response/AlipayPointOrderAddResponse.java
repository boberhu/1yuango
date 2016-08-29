package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayPointOrderAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3268884681479183471L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("result_code")
  private Boolean resultCode;
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setResultCode(Boolean resultCode)
  {
    this.resultCode = resultCode;
  }
  
  public Boolean getResultCode()
  {
    return resultCode;
  }
}

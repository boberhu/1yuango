package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMappprodAccountBindingSyncResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2272465743921813674L;
  @ApiField("result")
  private String result;
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getResult()
  {
    return result;
  }
}

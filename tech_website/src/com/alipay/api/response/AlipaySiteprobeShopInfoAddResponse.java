package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeShopInfoAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6219269152337613916L;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  @ApiField("shop_id")
  private String shopId;
  
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
  
  public void setShopId(String shopId)
  {
    this.shopId = shopId;
  }
  
  public String getShopId()
  {
    return shopId;
  }
}

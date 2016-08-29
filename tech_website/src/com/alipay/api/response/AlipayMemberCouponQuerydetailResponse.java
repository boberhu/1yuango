package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.Coupon;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMemberCouponQuerydetailResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1498225184312227364L;
  @ApiField("coupon")
  private Coupon coupon;
  @ApiField("error_code")
  private String errorCode;
  @ApiField("error_msg")
  private String errorMsg;
  @ApiField("success_code")
  private String successCode;
  
  public void setCoupon(Coupon coupon)
  {
    this.coupon = coupon;
  }
  
  public Coupon getCoupon()
  {
    return coupon;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorCode()
  {
    return errorCode;
  }
  
  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }
  
  public String getErrorMsg()
  {
    return errorMsg;
  }
  
  public void setSuccessCode(String successCode)
  {
    this.successCode = successCode;
  }
  
  public String getSuccessCode()
  {
    return successCode;
  }
}

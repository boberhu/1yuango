package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class Coupon
  extends AlipayObject
{
  private static final long serialVersionUID = 1655515848595515755L;
  @ApiField("available_amount")
  private String availableAmount;
  @ApiField("coupon_no")
  private String couponNo;
  @ApiField("deduct_amount")
  private String deductAmount;
  @ApiField("gmt_active")
  private String gmtActive;
  @ApiField("gmt_create")
  private String gmtCreate;
  @ApiField("gmt_expired")
  private String gmtExpired;
  @ApiField("instructions")
  private String instructions;
  @ApiField("memo")
  private String memo;
  @ApiField("merchant_uniq_id")
  private String merchantUniqId;
  @ApiField("multi_use_flag")
  private String multiUseFlag;
  @ApiField("name")
  private String name;
  @ApiField("refund_flag")
  private String refundFlag;
  @ApiField("status")
  private String status;
  @ApiField("template_no")
  private String templateNo;
  @ApiField("user_id")
  private String userId;
  
  public String getAvailableAmount()
  {
    return availableAmount;
  }
  
  public void setAvailableAmount(String availableAmount)
  {
    this.availableAmount = availableAmount;
  }
  
  public String getCouponNo()
  {
    return couponNo;
  }
  
  public void setCouponNo(String couponNo)
  {
    this.couponNo = couponNo;
  }
  
  public String getDeductAmount()
  {
    return deductAmount;
  }
  
  public void setDeductAmount(String deductAmount)
  {
    this.deductAmount = deductAmount;
  }
  
  public String getGmtActive()
  {
    return gmtActive;
  }
  
  public void setGmtActive(String gmtActive)
  {
    this.gmtActive = gmtActive;
  }
  
  public String getGmtCreate()
  {
    return gmtCreate;
  }
  
  public void setGmtCreate(String gmtCreate)
  {
    this.gmtCreate = gmtCreate;
  }
  
  public String getGmtExpired()
  {
    return gmtExpired;
  }
  
  public void setGmtExpired(String gmtExpired)
  {
    this.gmtExpired = gmtExpired;
  }
  
  public String getInstructions()
  {
    return instructions;
  }
  
  public void setInstructions(String instructions)
  {
    this.instructions = instructions;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMerchantUniqId()
  {
    return merchantUniqId;
  }
  
  public void setMerchantUniqId(String merchantUniqId)
  {
    this.merchantUniqId = merchantUniqId;
  }
  
  public String getMultiUseFlag()
  {
    return multiUseFlag;
  }
  
  public void setMultiUseFlag(String multiUseFlag)
  {
    this.multiUseFlag = multiUseFlag;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getRefundFlag()
  {
    return refundFlag;
  }
  
  public void setRefundFlag(String refundFlag)
  {
    this.refundFlag = refundFlag;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getTemplateNo()
  {
    return templateNo;
  }
  
  public void setTemplateNo(String templateNo)
  {
    this.templateNo = templateNo;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
}

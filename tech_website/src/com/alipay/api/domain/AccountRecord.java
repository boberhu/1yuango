package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class AccountRecord
  extends AlipayObject
{
  private static final long serialVersionUID = 5883182938457611331L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("balance")
  private String balance;
  @ApiField("business_type")
  private String businessType;
  @ApiField("create_time")
  private Date createTime;
  @ApiField("in_amount")
  private String inAmount;
  @ApiField("memo")
  private String memo;
  @ApiField("merchant_order_no")
  private String merchantOrderNo;
  @ApiField("opt_user_id")
  private String optUserId;
  @ApiField("out_amount")
  private String outAmount;
  @ApiField("self_user_id")
  private String selfUserId;
  @ApiField("type")
  private String type;
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getBalance()
  {
    return balance;
  }
  
  public void setBalance(String balance)
  {
    this.balance = balance;
  }
  
  public String getBusinessType()
  {
    return businessType;
  }
  
  public void setBusinessType(String businessType)
  {
    this.businessType = businessType;
  }
  
  public Date getCreateTime()
  {
    return createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getInAmount()
  {
    return inAmount;
  }
  
  public void setInAmount(String inAmount)
  {
    this.inAmount = inAmount;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getOptUserId()
  {
    return optUserId;
  }
  
  public void setOptUserId(String optUserId)
  {
    this.optUserId = optUserId;
  }
  
  public String getOutAmount()
  {
    return outAmount;
  }
  
  public void setOutAmount(String outAmount)
  {
    this.outAmount = outAmount;
  }
  
  public String getSelfUserId()
  {
    return selfUserId;
  }
  
  public void setSelfUserId(String selfUserId)
  {
    this.selfUserId = selfUserId;
  }
  
  public String getType()
  {
    return type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
}

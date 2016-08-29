package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class MicroPayOrderDetail
  extends AlipayObject
{
  private static final long serialVersionUID = 8562639951794434779L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("alipay_user_id")
  private String alipayUserId;
  @ApiField("available_amount")
  private String availableAmount;
  @ApiField("create_time")
  private Date createTime;
  @ApiField("expire_time")
  private Date expireTime;
  @ApiField("freeze_amount")
  private String freezeAmount;
  @ApiField("memo")
  private String memo;
  @ApiField("merchant_order_no")
  private String merchantOrderNo;
  @ApiField("modified_time")
  private Date modifiedTime;
  @ApiField("order_status")
  private String orderStatus;
  @ApiField("pay_amount")
  private String payAmount;
  @ApiField("pay_confirm")
  private String payConfirm;
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAlipayUserId()
  {
    return alipayUserId;
  }
  
  public void setAlipayUserId(String alipayUserId)
  {
    this.alipayUserId = alipayUserId;
  }
  
  public String getAvailableAmount()
  {
    return availableAmount;
  }
  
  public void setAvailableAmount(String availableAmount)
  {
    this.availableAmount = availableAmount;
  }
  
  public Date getCreateTime()
  {
    return createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public Date getExpireTime()
  {
    return expireTime;
  }
  
  public void setExpireTime(Date expireTime)
  {
    this.expireTime = expireTime;
  }
  
  public String getFreezeAmount()
  {
    return freezeAmount;
  }
  
  public void setFreezeAmount(String freezeAmount)
  {
    this.freezeAmount = freezeAmount;
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
  
  public Date getModifiedTime()
  {
    return modifiedTime;
  }
  
  public void setModifiedTime(Date modifiedTime)
  {
    this.modifiedTime = modifiedTime;
  }
  
  public String getOrderStatus()
  {
    return orderStatus;
  }
  
  public void setOrderStatus(String orderStatus)
  {
    this.orderStatus = orderStatus;
  }
  
  public String getPayAmount()
  {
    return payAmount;
  }
  
  public void setPayAmount(String payAmount)
  {
    this.payAmount = payAmount;
  }
  
  public String getPayConfirm()
  {
    return payConfirm;
  }
  
  public void setPayConfirm(String payConfirm)
  {
    this.payConfirm = payConfirm;
  }
}

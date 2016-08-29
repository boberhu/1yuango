package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class TradeRecord
  extends AlipayObject
{
  private static final long serialVersionUID = 1591718985468129397L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("create_time")
  private Date createTime;
  @ApiField("in_out_type")
  private String inOutType;
  @ApiField("merchant_order_no")
  private String merchantOrderNo;
  @ApiField("modified_time")
  private Date modifiedTime;
  @ApiField("opposite_logon_id")
  private String oppositeLogonId;
  @ApiField("opposite_name")
  private String oppositeName;
  @ApiField("opposite_user_id")
  private String oppositeUserId;
  @ApiField("order_from")
  private String orderFrom;
  @ApiField("order_status")
  private String orderStatus;
  @ApiField("order_title")
  private String orderTitle;
  @ApiField("order_type")
  private String orderType;
  @ApiField("owner_logon_id")
  private String ownerLogonId;
  @ApiField("owner_name")
  private String ownerName;
  @ApiField("owner_user_id")
  private String ownerUserId;
  @ApiField("partner_id")
  private String partnerId;
  @ApiField("service_charge")
  private String serviceCharge;
  @ApiField("total_amount")
  private String totalAmount;
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public Date getCreateTime()
  {
    return createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getInOutType()
  {
    return inOutType;
  }
  
  public void setInOutType(String inOutType)
  {
    this.inOutType = inOutType;
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
  
  public String getOppositeLogonId()
  {
    return oppositeLogonId;
  }
  
  public void setOppositeLogonId(String oppositeLogonId)
  {
    this.oppositeLogonId = oppositeLogonId;
  }
  
  public String getOppositeName()
  {
    return oppositeName;
  }
  
  public void setOppositeName(String oppositeName)
  {
    this.oppositeName = oppositeName;
  }
  
  public String getOppositeUserId()
  {
    return oppositeUserId;
  }
  
  public void setOppositeUserId(String oppositeUserId)
  {
    this.oppositeUserId = oppositeUserId;
  }
  
  public String getOrderFrom()
  {
    return orderFrom;
  }
  
  public void setOrderFrom(String orderFrom)
  {
    this.orderFrom = orderFrom;
  }
  
  public String getOrderStatus()
  {
    return orderStatus;
  }
  
  public void setOrderStatus(String orderStatus)
  {
    this.orderStatus = orderStatus;
  }
  
  public String getOrderTitle()
  {
    return orderTitle;
  }
  
  public void setOrderTitle(String orderTitle)
  {
    this.orderTitle = orderTitle;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOwnerLogonId()
  {
    return ownerLogonId;
  }
  
  public void setOwnerLogonId(String ownerLogonId)
  {
    this.ownerLogonId = ownerLogonId;
  }
  
  public String getOwnerName()
  {
    return ownerName;
  }
  
  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }
  
  public String getOwnerUserId()
  {
    return ownerUserId;
  }
  
  public void setOwnerUserId(String ownerUserId)
  {
    this.ownerUserId = ownerUserId;
  }
  
  public String getPartnerId()
  {
    return partnerId;
  }
  
  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }
  
  public String getServiceCharge()
  {
    return serviceCharge;
  }
  
  public void setServiceCharge(String serviceCharge)
  {
    this.serviceCharge = serviceCharge;
  }
  
  public String getTotalAmount()
  {
    return totalAmount;
  }
  
  public void setTotalAmount(String totalAmount)
  {
    this.totalAmount = totalAmount;
  }
}

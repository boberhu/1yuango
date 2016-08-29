package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEbppBillAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2677739949725589427L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("bank_bill_no")
  private String bankBillNo;
  @ApiField("bill_date")
  private String billDate;
  @ApiField("bill_key")
  private String billKey;
  @ApiField("charge_inst")
  private String chargeInst;
  @ApiField("charge_inst_name")
  private String chargeInstName;
  @ApiField("extend_field")
  private String extendField;
  @ApiField("merchant_order_no")
  private String merchantOrderNo;
  @ApiField("order_type")
  private String orderType;
  @ApiField("owner_name")
  private String ownerName;
  @ApiField("pay_amount")
  private String payAmount;
  @ApiField("service_amount")
  private String serviceAmount;
  @ApiField("sub_order_type")
  private String subOrderType;
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setBankBillNo(String bankBillNo)
  {
    this.bankBillNo = bankBillNo;
  }
  
  public String getBankBillNo()
  {
    return bankBillNo;
  }
  
  public void setBillDate(String billDate)
  {
    this.billDate = billDate;
  }
  
  public String getBillDate()
  {
    return billDate;
  }
  
  public void setBillKey(String billKey)
  {
    this.billKey = billKey;
  }
  
  public String getBillKey()
  {
    return billKey;
  }
  
  public void setChargeInst(String chargeInst)
  {
    this.chargeInst = chargeInst;
  }
  
  public String getChargeInst()
  {
    return chargeInst;
  }
  
  public void setChargeInstName(String chargeInstName)
  {
    this.chargeInstName = chargeInstName;
  }
  
  public String getChargeInstName()
  {
    return chargeInstName;
  }
  
  public void setExtendField(String extendField)
  {
    this.extendField = extendField;
  }
  
  public String getExtendField()
  {
    return extendField;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }
  
  public String getOwnerName()
  {
    return ownerName;
  }
  
  public void setPayAmount(String payAmount)
  {
    this.payAmount = payAmount;
  }
  
  public String getPayAmount()
  {
    return payAmount;
  }
  
  public void setServiceAmount(String serviceAmount)
  {
    this.serviceAmount = serviceAmount;
  }
  
  public String getServiceAmount()
  {
    return serviceAmount;
  }
  
  public void setSubOrderType(String subOrderType)
  {
    this.subOrderType = subOrderType;
  }
  
  public String getSubOrderType()
  {
    return subOrderType;
  }
}

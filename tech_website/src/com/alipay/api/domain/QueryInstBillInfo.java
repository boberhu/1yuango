package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class QueryInstBillInfo
  extends AlipayObject
{
  private static final long serialVersionUID = 6523355416237958826L;
  @ApiField("amount")
  private String amount;
  @ApiField("balance")
  private String balance;
  @ApiField("bill_date")
  private String billDate;
  @ApiListField("bill_detail")
  @ApiField("query_inst_bill_detail")
  private List<QueryInstBillDetail> billDetail;
  @ApiField("bill_fines")
  private String billFines;
  @ApiField("bill_key")
  private String billKey;
  @ApiField("bill_user_name")
  private String billUserName;
  @ApiField("charge_inst")
  private String chargeInst;
  @ApiField("chargeoff_inst")
  private String chargeoffInst;
  @ApiField("company_id")
  private String companyId;
  @ApiField("extend")
  private String extend;
  @ApiField("order_type")
  private String orderType;
  @ApiField("out_id")
  private String outId;
  @ApiField("return_message")
  private String returnMessage;
  @ApiField("sub_order_type")
  private String subOrderType;
  
  public String getAmount()
  {
    return amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
  
  public String getBalance()
  {
    return balance;
  }
  
  public void setBalance(String balance)
  {
    this.balance = balance;
  }
  
  public String getBillDate()
  {
    return billDate;
  }
  
  public void setBillDate(String billDate)
  {
    this.billDate = billDate;
  }
  
  public List<QueryInstBillDetail> getBillDetail()
  {
    return billDetail;
  }
  
  public void setBillDetail(List<QueryInstBillDetail> billDetail)
  {
    this.billDetail = billDetail;
  }
  
  public String getBillFines()
  {
    return billFines;
  }
  
  public void setBillFines(String billFines)
  {
    this.billFines = billFines;
  }
  
  public String getBillKey()
  {
    return billKey;
  }
  
  public void setBillKey(String billKey)
  {
    this.billKey = billKey;
  }
  
  public String getBillUserName()
  {
    return billUserName;
  }
  
  public void setBillUserName(String billUserName)
  {
    this.billUserName = billUserName;
  }
  
  public String getChargeInst()
  {
    return chargeInst;
  }
  
  public void setChargeInst(String chargeInst)
  {
    this.chargeInst = chargeInst;
  }
  
  public String getChargeoffInst()
  {
    return chargeoffInst;
  }
  
  public void setChargeoffInst(String chargeoffInst)
  {
    this.chargeoffInst = chargeoffInst;
  }
  
  public String getCompanyId()
  {
    return companyId;
  }
  
  public void setCompanyId(String companyId)
  {
    this.companyId = companyId;
  }
  
  public String getExtend()
  {
    return extend;
  }
  
  public void setExtend(String extend)
  {
    this.extend = extend;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOutId()
  {
    return outId;
  }
  
  public void setOutId(String outId)
  {
    this.outId = outId;
  }
  
  public String getReturnMessage()
  {
    return returnMessage;
  }
  
  public void setReturnMessage(String returnMessage)
  {
    this.returnMessage = returnMessage;
  }
  
  public String getSubOrderType()
  {
    return subOrderType;
  }
  
  public void setSubOrderType(String subOrderType)
  {
    this.subOrderType = subOrderType;
  }
}

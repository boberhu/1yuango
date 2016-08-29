package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayEbppBillAddResponse;
import java.util.Map;

public class AlipayEbppBillAddRequest
  implements AlipayRequest<AlipayEbppBillAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String bankBillNo;
  private String billDate;
  private String billKey;
  private String chargeInst;
  private String extendField;
  private String merchantOrderNo;
  private String mobile;
  private String orderType;
  private String ownerName;
  private String payAmount;
  private String serviceAmount;
  private String subOrderType;
  private String trafficLocation;
  private String trafficRegulations;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
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
  
  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }
  
  public String getMobile()
  {
    return mobile;
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
  
  public void setTrafficLocation(String trafficLocation)
  {
    this.trafficLocation = trafficLocation;
  }
  
  public String getTrafficLocation()
  {
    return trafficLocation;
  }
  
  public void setTrafficRegulations(String trafficRegulations)
  {
    this.trafficRegulations = trafficRegulations;
  }
  
  public String getTrafficRegulations()
  {
    return trafficRegulations;
  }
  
  public String getApiVersion()
  {
    return apiVersion;
  }
  
  public void setApiVersion(String apiVersion)
  {
    this.apiVersion = apiVersion;
  }
  
  public void setTerminalType(String terminalType)
  {
    this.terminalType = terminalType;
  }
  
  public String getTerminalType()
  {
    return terminalType;
  }
  
  public void setTerminalInfo(String terminalInfo)
  {
    this.terminalInfo = terminalInfo;
  }
  
  public String getTerminalInfo()
  {
    return terminalInfo;
  }
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.ebpp.bill.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("bank_bill_no", bankBillNo);
    txtParams.put("bill_date", billDate);
    txtParams.put("bill_key", billKey);
    txtParams.put("charge_inst", chargeInst);
    txtParams.put("extend_field", extendField);
    txtParams.put("merchant_order_no", merchantOrderNo);
    txtParams.put("mobile", mobile);
    txtParams.put("order_type", orderType);
    txtParams.put("owner_name", ownerName);
    txtParams.put("pay_amount", payAmount);
    txtParams.put("service_amount", serviceAmount);
    txtParams.put("sub_order_type", subOrderType);
    txtParams.put("traffic_location", trafficLocation);
    txtParams.put("traffic_regulations", trafficRegulations);
    if (udfParams != null) {
      txtParams.putAll(udfParams);
    }
    return txtParams;
  }
  
  public void putOtherTextParam(String key, String value)
  {
    if (udfParams == null) {
      udfParams = new AlipayHashMap();
    }
    udfParams.put(key, value);
  }
  
  public Class<AlipayEbppBillAddResponse> getResponseClass()
  {
    return AlipayEbppBillAddResponse.class;
  }
}

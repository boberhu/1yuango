package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayEbppBillSearchResponse;
import java.util.Map;

public class AlipayEbppBillSearchRequest
  implements AlipayRequest<AlipayEbppBillSearchResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String billKey;
  private String chargeInst;
  private String chargeoffInst;
  private String companyId;
  private String extend;
  private String orderType;
  private String subOrderType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
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
  
  public void setChargeoffInst(String chargeoffInst)
  {
    this.chargeoffInst = chargeoffInst;
  }
  
  public String getChargeoffInst()
  {
    return chargeoffInst;
  }
  
  public void setCompanyId(String companyId)
  {
    this.companyId = companyId;
  }
  
  public String getCompanyId()
  {
    return companyId;
  }
  
  public void setExtend(String extend)
  {
    this.extend = extend;
  }
  
  public String getExtend()
  {
    return extend;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setSubOrderType(String subOrderType)
  {
    this.subOrderType = subOrderType;
  }
  
  public String getSubOrderType()
  {
    return subOrderType;
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
    return "alipay.ebpp.bill.search";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("bill_key", billKey);
    txtParams.put("charge_inst", chargeInst);
    txtParams.put("chargeoff_inst", chargeoffInst);
    txtParams.put("company_id", companyId);
    txtParams.put("extend", extend);
    txtParams.put("order_type", orderType);
    txtParams.put("sub_order_type", subOrderType);
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
  
  public Class<AlipayEbppBillSearchResponse> getResponseClass()
  {
    return AlipayEbppBillSearchResponse.class;
  }
}

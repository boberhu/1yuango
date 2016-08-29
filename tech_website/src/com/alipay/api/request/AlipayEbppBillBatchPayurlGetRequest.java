package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayEbppBillBatchPayurlGetResponse;
import java.util.List;
import java.util.Map;

public class AlipayEbppBillBatchPayurlGetRequest
  implements AlipayRequest<AlipayEbppBillBatchPayurlGetResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String callbackUrl;
  private String orderType;
  private List<String> payBillList;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setCallbackUrl(String callbackUrl)
  {
    this.callbackUrl = callbackUrl;
  }
  
  public String getCallbackUrl()
  {
    return callbackUrl;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setPayBillList(List<String> payBillList)
  {
    this.payBillList = payBillList;
  }
  
  public List<String> getPayBillList()
  {
    return payBillList;
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
    return "alipay.ebpp.bill.batch.payurl.get";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("callback_url", callbackUrl);
    txtParams.put("order_type", orderType);
    txtParams.put("pay_bill_list", payBillList);
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
  
  public Class<AlipayEbppBillBatchPayurlGetResponse> getResponseClass()
  {
    return AlipayEbppBillBatchPayurlGetResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayDataBillDownloadurlGetResponse;
import java.util.Map;

public class AlipayDataBillDownloadurlGetRequest
  implements AlipayRequest<AlipayDataBillDownloadurlGetResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String billDate;
  private String billType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setBillDate(String billDate)
  {
    this.billDate = billDate;
  }
  
  public String getBillDate()
  {
    return billDate;
  }
  
  public void setBillType(String billType)
  {
    this.billType = billType;
  }
  
  public String getBillType()
  {
    return billType;
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
    return "alipay.data.bill.downloadurl.get";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("bill_date", billDate);
    txtParams.put("bill_type", billType);
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
  
  public Class<AlipayDataBillDownloadurlGetResponse> getResponseClass()
  {
    return AlipayDataBillDownloadurlGetResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayEbppPdeductBillPayStatusResponse;
import java.util.Map;

public class AlipayEbppPdeductBillPayStatusRequest
  implements AlipayRequest<AlipayEbppPdeductBillPayStatusResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String agreementId;
  private String outOrderNo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setAgreementId(String agreementId)
  {
    this.agreementId = agreementId;
  }
  
  public String getAgreementId()
  {
    return agreementId;
  }
  
  public void setOutOrderNo(String outOrderNo)
  {
    this.outOrderNo = outOrderNo;
  }
  
  public String getOutOrderNo()
  {
    return outOrderNo;
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
    return "alipay.ebpp.pdeduct.bill.pay.status";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("agreement_id", agreementId);
    txtParams.put("out_order_no", outOrderNo);
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
  
  public Class<AlipayEbppPdeductBillPayStatusResponse> getResponseClass()
  {
    return AlipayEbppPdeductBillPayStatusResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayTrustUserDatareceivedSendResponse;
import java.util.Map;

public class AlipayTrustUserDatareceivedSendRequest
  implements AlipayRequest<AlipayTrustUserDatareceivedSendResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String data;
  private String identity;
  private String typeId;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getData()
  {
    return data;
  }
  
  public void setIdentity(String identity)
  {
    this.identity = identity;
  }
  
  public String getIdentity()
  {
    return identity;
  }
  
  public void setTypeId(String typeId)
  {
    this.typeId = typeId;
  }
  
  public String getTypeId()
  {
    return typeId;
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
    return "alipay.trust.user.datareceived.send";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("data", data);
    txtParams.put("identity", identity);
    txtParams.put("type_id", typeId);
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
  
  public Class<AlipayTrustUserDatareceivedSendResponse> getResponseClass()
  {
    return AlipayTrustUserDatareceivedSendResponse.class;
  }
}

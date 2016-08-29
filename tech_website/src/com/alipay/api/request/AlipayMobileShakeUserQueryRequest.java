package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMobileShakeUserQueryResponse;
import java.util.Map;

public class AlipayMobileShakeUserQueryRequest
  implements AlipayRequest<AlipayMobileShakeUserQueryResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String dynamicId;
  private String dynamicIdType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setDynamicId(String dynamicId)
  {
    this.dynamicId = dynamicId;
  }
  
  public String getDynamicId()
  {
    return dynamicId;
  }
  
  public void setDynamicIdType(String dynamicIdType)
  {
    this.dynamicIdType = dynamicIdType;
  }
  
  public String getDynamicIdType()
  {
    return dynamicIdType;
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
    return "alipay.mobile.shake.user.query";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("dynamic_id", dynamicId);
    txtParams.put("dynamic_id_type", dynamicIdType);
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
  
  public Class<AlipayMobileShakeUserQueryResponse> getResponseClass()
  {
    return AlipayMobileShakeUserQueryResponse.class;
  }
}

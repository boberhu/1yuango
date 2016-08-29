package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayUserAccountFreezeGetResponse;
import java.util.Map;

public class AlipayUserAccountFreezeGetRequest
  implements AlipayRequest<AlipayUserAccountFreezeGetResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String freezeType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setFreezeType(String freezeType)
  {
    this.freezeType = freezeType;
  }
  
  public String getFreezeType()
  {
    return freezeType;
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
    return "alipay.user.account.freeze.get";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("freeze_type", freezeType);
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
  
  public Class<AlipayUserAccountFreezeGetResponse> getResponseClass()
  {
    return AlipayUserAccountFreezeGetResponse.class;
  }
}

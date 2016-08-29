package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayTrustUserTokenGetResponse;
import java.util.Map;

public class AlipayTrustUserTokenGetRequest
  implements AlipayRequest<AlipayTrustUserTokenGetResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String aliTrustUserInfo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setAliTrustUserInfo(String aliTrustUserInfo)
  {
    this.aliTrustUserInfo = aliTrustUserInfo;
  }
  
  public String getAliTrustUserInfo()
  {
    return aliTrustUserInfo;
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
    return "alipay.trust.user.token.get";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("ali_trust_user_info", aliTrustUserInfo);
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
  
  public Class<AlipayTrustUserTokenGetResponse> getResponseClass()
  {
    return AlipayTrustUserTokenGetResponse.class;
  }
}

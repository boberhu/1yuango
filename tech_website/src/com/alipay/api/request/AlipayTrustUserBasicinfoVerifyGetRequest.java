package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayTrustUserBasicinfoVerifyGetResponse;
import java.util.Map;

public class AlipayTrustUserBasicinfoVerifyGetRequest
  implements AlipayRequest<AlipayTrustUserBasicinfoVerifyGetResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String aliTrustUserInfo;
  private String encrypCode;
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
  
  public void setEncrypCode(String encrypCode)
  {
    this.encrypCode = encrypCode;
  }
  
  public String getEncrypCode()
  {
    return encrypCode;
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
    return "alipay.trust.user.basicinfo.verify.get";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("ali_trust_user_info", aliTrustUserInfo);
    txtParams.put("encryp_code", encrypCode);
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
  
  public Class<AlipayTrustUserBasicinfoVerifyGetResponse> getResponseClass()
  {
    return AlipayTrustUserBasicinfoVerifyGetResponse.class;
  }
}

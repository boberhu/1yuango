package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayUserTestResponse;
import java.util.List;
import java.util.Map;

public class AlipayUserTestRequest
  implements AlipayRequest<AlipayUserTestResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private List<String> userinfo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setUserinfo(List<String> userinfo)
  {
    this.userinfo = userinfo;
  }
  
  public List<String> getUserinfo()
  {
    return userinfo;
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
    return "alipay.user.test";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("userinfo", userinfo);
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
  
  public Class<AlipayUserTestResponse> getResponseClass()
  {
    return AlipayUserTestResponse.class;
  }
}

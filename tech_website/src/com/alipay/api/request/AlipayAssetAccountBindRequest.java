package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayAssetAccountBindResponse;
import java.util.Map;

public class AlipayAssetAccountBindRequest
  implements AlipayRequest<AlipayAssetAccountBindResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String bindScene;
  private String providerId;
  private String providerUserId;
  private String providerUserName;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setBindScene(String bindScene)
  {
    this.bindScene = bindScene;
  }
  
  public String getBindScene()
  {
    return bindScene;
  }
  
  public void setProviderId(String providerId)
  {
    this.providerId = providerId;
  }
  
  public String getProviderId()
  {
    return providerId;
  }
  
  public void setProviderUserId(String providerUserId)
  {
    this.providerUserId = providerUserId;
  }
  
  public String getProviderUserId()
  {
    return providerUserId;
  }
  
  public void setProviderUserName(String providerUserName)
  {
    this.providerUserName = providerUserName;
  }
  
  public String getProviderUserName()
  {
    return providerUserName;
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
    return "alipay.asset.account.bind";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("bind_scene", bindScene);
    txtParams.put("provider_id", providerId);
    txtParams.put("provider_user_id", providerUserId);
    txtParams.put("provider_user_name", providerUserName);
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
  
  public Class<AlipayAssetAccountBindResponse> getResponseClass()
  {
    return AlipayAssetAccountBindResponse.class;
  }
}

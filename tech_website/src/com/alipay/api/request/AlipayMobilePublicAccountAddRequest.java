package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMobilePublicAccountAddResponse;
import java.util.Map;

public class AlipayMobilePublicAccountAddRequest
  implements AlipayRequest<AlipayMobilePublicAccountAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String agreementId;
  private String bindAccountNo;
  private String bizContent;
  private String displayName;
  private String fromUserId;
  private String realName;
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
  
  public void setBindAccountNo(String bindAccountNo)
  {
    this.bindAccountNo = bindAccountNo;
  }
  
  public String getBindAccountNo()
  {
    return bindAccountNo;
  }
  
  public void setBizContent(String bizContent)
  {
    this.bizContent = bizContent;
  }
  
  public String getBizContent()
  {
    return bizContent;
  }
  
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }
  
  public String getDisplayName()
  {
    return displayName;
  }
  
  public void setFromUserId(String fromUserId)
  {
    this.fromUserId = fromUserId;
  }
  
  public String getFromUserId()
  {
    return fromUserId;
  }
  
  public void setRealName(String realName)
  {
    this.realName = realName;
  }
  
  public String getRealName()
  {
    return realName;
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
    return "alipay.mobile.public.account.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("agreement_id", agreementId);
    txtParams.put("bind_account_no", bindAccountNo);
    txtParams.put("biz_content", bizContent);
    txtParams.put("display_name", displayName);
    txtParams.put("from_user_id", fromUserId);
    txtParams.put("real_name", realName);
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
  
  public Class<AlipayMobilePublicAccountAddResponse> getResponseClass()
  {
    return AlipayMobilePublicAccountAddResponse.class;
  }
}

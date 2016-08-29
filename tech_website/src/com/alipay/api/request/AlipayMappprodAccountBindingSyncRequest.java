package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMappprodAccountBindingSyncResponse;
import java.util.Map;

public class AlipayMappprodAccountBindingSyncRequest
  implements AlipayRequest<AlipayMappprodAccountBindingSyncResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String extInfo;
  private String userAccountNo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setExtInfo(String extInfo)
  {
    this.extInfo = extInfo;
  }
  
  public String getExtInfo()
  {
    return extInfo;
  }
  
  public void setUserAccountNo(String userAccountNo)
  {
    this.userAccountNo = userAccountNo;
  }
  
  public String getUserAccountNo()
  {
    return userAccountNo;
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
    return "alipay.mappprod.account.binding.sync";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("ext_info", extInfo);
    txtParams.put("user_account_no", userAccountNo);
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
  
  public Class<AlipayMappprodAccountBindingSyncResponse> getResponseClass()
  {
    return AlipayMappprodAccountBindingSyncResponse.class;
  }
}

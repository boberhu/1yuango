package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPassCodeVerifyResponse;
import java.util.Map;

public class AlipayPassCodeVerifyRequest
  implements AlipayRequest<AlipayPassCodeVerifyResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String extInfo;
  private String operatorId;
  private String operatorType;
  private String verifyCode;
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
  
  public void setOperatorId(String operatorId)
  {
    this.operatorId = operatorId;
  }
  
  public String getOperatorId()
  {
    return operatorId;
  }
  
  public void setOperatorType(String operatorType)
  {
    this.operatorType = operatorType;
  }
  
  public String getOperatorType()
  {
    return operatorType;
  }
  
  public void setVerifyCode(String verifyCode)
  {
    this.verifyCode = verifyCode;
  }
  
  public String getVerifyCode()
  {
    return verifyCode;
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
    return "alipay.pass.code.verify";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("ext_info", extInfo);
    txtParams.put("operator_id", operatorId);
    txtParams.put("operator_type", operatorType);
    txtParams.put("verify_code", verifyCode);
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
  
  public Class<AlipayPassCodeVerifyResponse> getResponseClass()
  {
    return AlipayPassCodeVerifyResponse.class;
  }
}

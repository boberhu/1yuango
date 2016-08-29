package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPassSyncUpdateResponse;
import java.util.Map;

public class AlipayPassSyncUpdateRequest
  implements AlipayRequest<AlipayPassSyncUpdateResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String channelId;
  private String extInfo;
  private String pass;
  private String serialNumber;
  private String status;
  private String verifyCode;
  private String verifyType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setChannelId(String channelId)
  {
    this.channelId = channelId;
  }
  
  public String getChannelId()
  {
    return channelId;
  }
  
  public void setExtInfo(String extInfo)
  {
    this.extInfo = extInfo;
  }
  
  public String getExtInfo()
  {
    return extInfo;
  }
  
  public void setPass(String pass)
  {
    this.pass = pass;
  }
  
  public String getPass()
  {
    return pass;
  }
  
  public void setSerialNumber(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }
  
  public String getSerialNumber()
  {
    return serialNumber;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setVerifyCode(String verifyCode)
  {
    this.verifyCode = verifyCode;
  }
  
  public String getVerifyCode()
  {
    return verifyCode;
  }
  
  public void setVerifyType(String verifyType)
  {
    this.verifyType = verifyType;
  }
  
  public String getVerifyType()
  {
    return verifyType;
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
    return "alipay.pass.sync.update";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("channel_id", channelId);
    txtParams.put("ext_info", extInfo);
    txtParams.put("pass", pass);
    txtParams.put("serial_number", serialNumber);
    txtParams.put("status", status);
    txtParams.put("verify_code", verifyCode);
    txtParams.put("verify_type", verifyType);
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
  
  public Class<AlipayPassSyncUpdateResponse> getResponseClass()
  {
    return AlipayPassSyncUpdateResponse.class;
  }
}

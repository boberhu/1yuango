package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipaySiteprobeWifiUnconnectResponse;
import java.util.Map;

public class AlipaySiteprobeWifiUnconnectRequest
  implements AlipayRequest<AlipaySiteprobeWifiUnconnectResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String deviceId;
  private String deviceMac;
  private String merchantId;
  private String partnerId;
  private String userMac;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }
  
  public String getDeviceId()
  {
    return deviceId;
  }
  
  public void setDeviceMac(String deviceMac)
  {
    this.deviceMac = deviceMac;
  }
  
  public String getDeviceMac()
  {
    return deviceMac;
  }
  
  public void setMerchantId(String merchantId)
  {
    this.merchantId = merchantId;
  }
  
  public String getMerchantId()
  {
    return merchantId;
  }
  
  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }
  
  public String getPartnerId()
  {
    return partnerId;
  }
  
  public void setUserMac(String userMac)
  {
    this.userMac = userMac;
  }
  
  public String getUserMac()
  {
    return userMac;
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
    return "alipay.siteprobe.wifi.unconnect";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("device_id", deviceId);
    txtParams.put("device_mac", deviceMac);
    txtParams.put("merchant_id", merchantId);
    txtParams.put("partner_id", partnerId);
    txtParams.put("user_mac", userMac);
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
  
  public Class<AlipaySiteprobeWifiUnconnectResponse> getResponseClass()
  {
    return AlipaySiteprobeWifiUnconnectResponse.class;
  }
}

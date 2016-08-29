package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipaySecurityInfoAnalysisResponse;
import java.util.Map;

public class AlipaySecurityInfoAnalysisRequest
  implements AlipayRequest<AlipaySecurityInfoAnalysisResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String envClientBaseBand;
  private String envClientBaseStation;
  private String envClientCoordinates;
  private String envClientImei;
  private String envClientImsi;
  private String envClientIosUdid;
  private String envClientIp;
  private String envClientMac;
  private String envClientScreen;
  private String envClientUuid;
  private String jsTokenId;
  private String partnerId;
  private String sceneCode;
  private String userAccountNo;
  private String userBindBankcard;
  private String userBindBankcardType;
  private String userBindMobile;
  private String userIdentityType;
  private String userRealName;
  private String userRegDate;
  private String userRegEmail;
  private String userRegMobile;
  private String userrIdentityNo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setEnvClientBaseBand(String envClientBaseBand)
  {
    this.envClientBaseBand = envClientBaseBand;
  }
  
  public String getEnvClientBaseBand()
  {
    return envClientBaseBand;
  }
  
  public void setEnvClientBaseStation(String envClientBaseStation)
  {
    this.envClientBaseStation = envClientBaseStation;
  }
  
  public String getEnvClientBaseStation()
  {
    return envClientBaseStation;
  }
  
  public void setEnvClientCoordinates(String envClientCoordinates)
  {
    this.envClientCoordinates = envClientCoordinates;
  }
  
  public String getEnvClientCoordinates()
  {
    return envClientCoordinates;
  }
  
  public void setEnvClientImei(String envClientImei)
  {
    this.envClientImei = envClientImei;
  }
  
  public String getEnvClientImei()
  {
    return envClientImei;
  }
  
  public void setEnvClientImsi(String envClientImsi)
  {
    this.envClientImsi = envClientImsi;
  }
  
  public String getEnvClientImsi()
  {
    return envClientImsi;
  }
  
  public void setEnvClientIosUdid(String envClientIosUdid)
  {
    this.envClientIosUdid = envClientIosUdid;
  }
  
  public String getEnvClientIosUdid()
  {
    return envClientIosUdid;
  }
  
  public void setEnvClientIp(String envClientIp)
  {
    this.envClientIp = envClientIp;
  }
  
  public String getEnvClientIp()
  {
    return envClientIp;
  }
  
  public void setEnvClientMac(String envClientMac)
  {
    this.envClientMac = envClientMac;
  }
  
  public String getEnvClientMac()
  {
    return envClientMac;
  }
  
  public void setEnvClientScreen(String envClientScreen)
  {
    this.envClientScreen = envClientScreen;
  }
  
  public String getEnvClientScreen()
  {
    return envClientScreen;
  }
  
  public void setEnvClientUuid(String envClientUuid)
  {
    this.envClientUuid = envClientUuid;
  }
  
  public String getEnvClientUuid()
  {
    return envClientUuid;
  }
  
  public void setJsTokenId(String jsTokenId)
  {
    this.jsTokenId = jsTokenId;
  }
  
  public String getJsTokenId()
  {
    return jsTokenId;
  }
  
  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }
  
  public String getPartnerId()
  {
    return partnerId;
  }
  
  public void setSceneCode(String sceneCode)
  {
    this.sceneCode = sceneCode;
  }
  
  public String getSceneCode()
  {
    return sceneCode;
  }
  
  public void setUserAccountNo(String userAccountNo)
  {
    this.userAccountNo = userAccountNo;
  }
  
  public String getUserAccountNo()
  {
    return userAccountNo;
  }
  
  public void setUserBindBankcard(String userBindBankcard)
  {
    this.userBindBankcard = userBindBankcard;
  }
  
  public String getUserBindBankcard()
  {
    return userBindBankcard;
  }
  
  public void setUserBindBankcardType(String userBindBankcardType)
  {
    this.userBindBankcardType = userBindBankcardType;
  }
  
  public String getUserBindBankcardType()
  {
    return userBindBankcardType;
  }
  
  public void setUserBindMobile(String userBindMobile)
  {
    this.userBindMobile = userBindMobile;
  }
  
  public String getUserBindMobile()
  {
    return userBindMobile;
  }
  
  public void setUserIdentityType(String userIdentityType)
  {
    this.userIdentityType = userIdentityType;
  }
  
  public String getUserIdentityType()
  {
    return userIdentityType;
  }
  
  public void setUserRealName(String userRealName)
  {
    this.userRealName = userRealName;
  }
  
  public String getUserRealName()
  {
    return userRealName;
  }
  
  public void setUserRegDate(String userRegDate)
  {
    this.userRegDate = userRegDate;
  }
  
  public String getUserRegDate()
  {
    return userRegDate;
  }
  
  public void setUserRegEmail(String userRegEmail)
  {
    this.userRegEmail = userRegEmail;
  }
  
  public String getUserRegEmail()
  {
    return userRegEmail;
  }
  
  public void setUserRegMobile(String userRegMobile)
  {
    this.userRegMobile = userRegMobile;
  }
  
  public String getUserRegMobile()
  {
    return userRegMobile;
  }
  
  public void setUserrIdentityNo(String userrIdentityNo)
  {
    this.userrIdentityNo = userrIdentityNo;
  }
  
  public String getUserrIdentityNo()
  {
    return userrIdentityNo;
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
    return "alipay.security.info.analysis";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("env_client_base_band", envClientBaseBand);
    txtParams.put("env_client_base_station", envClientBaseStation);
    txtParams.put("env_client_coordinates", envClientCoordinates);
    txtParams.put("env_client_imei", envClientImei);
    txtParams.put("env_client_imsi", envClientImsi);
    txtParams.put("env_client_ios_udid", envClientIosUdid);
    txtParams.put("env_client_ip", envClientIp);
    txtParams.put("env_client_mac", envClientMac);
    txtParams.put("env_client_screen", envClientScreen);
    txtParams.put("env_client_uuid", envClientUuid);
    txtParams.put("js_token_id", jsTokenId);
    txtParams.put("partner_id", partnerId);
    txtParams.put("scene_code", sceneCode);
    txtParams.put("user_account_no", userAccountNo);
    txtParams.put("user_bind_bankcard", userBindBankcard);
    txtParams.put("user_bind_bankcard_type", userBindBankcardType);
    txtParams.put("user_bind_mobile", userBindMobile);
    txtParams.put("user_identity_type", userIdentityType);
    txtParams.put("user_real_name", userRealName);
    txtParams.put("user_reg_date", userRegDate);
    txtParams.put("user_reg_email", userRegEmail);
    txtParams.put("user_reg_mobile", userRegMobile);
    txtParams.put("userr_identity_no", userrIdentityNo);
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
  
  public Class<AlipaySecurityInfoAnalysisResponse> getResponseClass()
  {
    return AlipaySecurityInfoAnalysisResponse.class;
  }
}

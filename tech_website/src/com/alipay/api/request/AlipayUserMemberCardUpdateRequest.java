package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayUserMemberCardUpdateResponse;
import java.util.Map;

public class AlipayUserMemberCardUpdateRequest
  implements AlipayRequest<AlipayUserMemberCardUpdateResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String balance;
  private String bizCardNo;
  private String cardMerchantInfo;
  private String extInfo;
  private String externalCardNo;
  private String level;
  private String orrurTime;
  private Long point;
  private String requestFrom;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setBalance(String balance)
  {
    this.balance = balance;
  }
  
  public String getBalance()
  {
    return balance;
  }
  
  public void setBizCardNo(String bizCardNo)
  {
    this.bizCardNo = bizCardNo;
  }
  
  public String getBizCardNo()
  {
    return bizCardNo;
  }
  
  public void setCardMerchantInfo(String cardMerchantInfo)
  {
    this.cardMerchantInfo = cardMerchantInfo;
  }
  
  public String getCardMerchantInfo()
  {
    return cardMerchantInfo;
  }
  
  public void setExtInfo(String extInfo)
  {
    this.extInfo = extInfo;
  }
  
  public String getExtInfo()
  {
    return extInfo;
  }
  
  public void setExternalCardNo(String externalCardNo)
  {
    this.externalCardNo = externalCardNo;
  }
  
  public String getExternalCardNo()
  {
    return externalCardNo;
  }
  
  public void setLevel(String level)
  {
    this.level = level;
  }
  
  public String getLevel()
  {
    return level;
  }
  
  public void setOrrurTime(String orrurTime)
  {
    this.orrurTime = orrurTime;
  }
  
  public String getOrrurTime()
  {
    return orrurTime;
  }
  
  public void setPoint(Long point)
  {
    this.point = point;
  }
  
  public Long getPoint()
  {
    return point;
  }
  
  public void setRequestFrom(String requestFrom)
  {
    this.requestFrom = requestFrom;
  }
  
  public String getRequestFrom()
  {
    return requestFrom;
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
    return "alipay.user.member.card.update";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("balance", balance);
    txtParams.put("biz_card_no", bizCardNo);
    txtParams.put("card_merchant_info", cardMerchantInfo);
    txtParams.put("ext_info", extInfo);
    txtParams.put("external_card_no", externalCardNo);
    txtParams.put("level", level);
    txtParams.put("orrur_time", orrurTime);
    txtParams.put("point", point);
    txtParams.put("request_from", requestFrom);
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
  
  public Class<AlipayUserMemberCardUpdateResponse> getResponseClass()
  {
    return AlipayUserMemberCardUpdateResponse.class;
  }
}

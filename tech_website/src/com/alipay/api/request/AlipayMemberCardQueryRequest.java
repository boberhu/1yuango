package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMemberCardQueryResponse;
import java.util.Map;

public class AlipayMemberCardQueryRequest
  implements AlipayRequest<AlipayMemberCardQueryResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String bizCardNo;
  private String cardMerchantInfo;
  private String cardUserInfo;
  private String extInfo;
  private String requestFrom;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
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
  
  public void setCardUserInfo(String cardUserInfo)
  {
    this.cardUserInfo = cardUserInfo;
  }
  
  public String getCardUserInfo()
  {
    return cardUserInfo;
  }
  
  public void setExtInfo(String extInfo)
  {
    this.extInfo = extInfo;
  }
  
  public String getExtInfo()
  {
    return extInfo;
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
    return "alipay.member.card.query";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("biz_card_no", bizCardNo);
    txtParams.put("card_merchant_info", cardMerchantInfo);
    txtParams.put("card_user_info", cardUserInfo);
    txtParams.put("ext_info", extInfo);
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
  
  public Class<AlipayMemberCardQueryResponse> getResponseClass()
  {
    return AlipayMemberCardQueryResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMemberConsumeNotifyResponse;
import java.util.Map;

public class AlipayMemberConsumeNotifyRequest
  implements AlipayRequest<AlipayMemberConsumeNotifyResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String actPayAmount;
  private String bizCardNo;
  private String cardInfo;
  private String externalCardNo;
  private String gainBenefitList;
  private String memo;
  private String shopCode;
  private String swipeCertType;
  private String tradeAmount;
  private String tradeName;
  private String tradeNo;
  private String tradeTime;
  private String tradeType;
  private String useBenefitList;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setActPayAmount(String actPayAmount)
  {
    this.actPayAmount = actPayAmount;
  }
  
  public String getActPayAmount()
  {
    return actPayAmount;
  }
  
  public void setBizCardNo(String bizCardNo)
  {
    this.bizCardNo = bizCardNo;
  }
  
  public String getBizCardNo()
  {
    return bizCardNo;
  }
  
  public void setCardInfo(String cardInfo)
  {
    this.cardInfo = cardInfo;
  }
  
  public String getCardInfo()
  {
    return cardInfo;
  }
  
  public void setExternalCardNo(String externalCardNo)
  {
    this.externalCardNo = externalCardNo;
  }
  
  public String getExternalCardNo()
  {
    return externalCardNo;
  }
  
  public void setGainBenefitList(String gainBenefitList)
  {
    this.gainBenefitList = gainBenefitList;
  }
  
  public String getGainBenefitList()
  {
    return gainBenefitList;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setShopCode(String shopCode)
  {
    this.shopCode = shopCode;
  }
  
  public String getShopCode()
  {
    return shopCode;
  }
  
  public void setSwipeCertType(String swipeCertType)
  {
    this.swipeCertType = swipeCertType;
  }
  
  public String getSwipeCertType()
  {
    return swipeCertType;
  }
  
  public void setTradeAmount(String tradeAmount)
  {
    this.tradeAmount = tradeAmount;
  }
  
  public String getTradeAmount()
  {
    return tradeAmount;
  }
  
  public void setTradeName(String tradeName)
  {
    this.tradeName = tradeName;
  }
  
  public String getTradeName()
  {
    return tradeName;
  }
  
  public void setTradeNo(String tradeNo)
  {
    this.tradeNo = tradeNo;
  }
  
  public String getTradeNo()
  {
    return tradeNo;
  }
  
  public void setTradeTime(String tradeTime)
  {
    this.tradeTime = tradeTime;
  }
  
  public String getTradeTime()
  {
    return tradeTime;
  }
  
  public void setTradeType(String tradeType)
  {
    this.tradeType = tradeType;
  }
  
  public String getTradeType()
  {
    return tradeType;
  }
  
  public void setUseBenefitList(String useBenefitList)
  {
    this.useBenefitList = useBenefitList;
  }
  
  public String getUseBenefitList()
  {
    return useBenefitList;
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
    return "alipay.member.consume.notify";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("act_pay_amount", actPayAmount);
    txtParams.put("biz_card_no", bizCardNo);
    txtParams.put("card_info", cardInfo);
    txtParams.put("external_card_no", externalCardNo);
    txtParams.put("gain_benefit_list", gainBenefitList);
    txtParams.put("memo", memo);
    txtParams.put("shop_code", shopCode);
    txtParams.put("swipe_cert_type", swipeCertType);
    txtParams.put("trade_amount", tradeAmount);
    txtParams.put("trade_name", tradeName);
    txtParams.put("trade_no", tradeNo);
    txtParams.put("trade_time", tradeTime);
    txtParams.put("trade_type", tradeType);
    txtParams.put("use_benefit_list", useBenefitList);
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
  
  public Class<AlipayMemberConsumeNotifyResponse> getResponseClass()
  {
    return AlipayMemberConsumeNotifyResponse.class;
  }
}

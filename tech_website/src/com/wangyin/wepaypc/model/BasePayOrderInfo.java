package com.wangyin.wepaypc.model;

public class BasePayOrderInfo
{
  protected String merchantSign;
  protected String token;
  protected String version;
  protected String merchantNum;
  protected String merchantRemark;
  protected String tradeNum;
  protected String tradeName;
  protected String tradeDescription;
  protected String tradeTime;
  private String tradeAmount;
  protected String currency;
  protected String successCallbackUrl;
  protected String forPayLayerUrl;
  protected String notifyUrl;
  protected String ip;
  
  public String getIp()
  {
    return ip;
  }
  
  public void setIp(String ip)
  {
    this.ip = ip;
  }
  
  public String getToken()
  {
    return token;
  }
  
  public void setToken(String token)
  {
    this.token = token;
  }
  
  public String getMerchantSign()
  {
    return merchantSign;
  }
  
  public void setMerchantSign(String merchantSign)
  {
    this.merchantSign = merchantSign;
  }
  
  public String getVersion()
  {
    return version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getMerchantNum()
  {
    return merchantNum;
  }
  
  public void setMerchantNum(String merchantNum)
  {
    this.merchantNum = merchantNum;
  }
  
  public String getMerchantRemark()
  {
    return merchantRemark;
  }
  
  public void setMerchantRemark(String merchantRemark)
  {
    this.merchantRemark = merchantRemark;
  }
  
  public String getTradeNum()
  {
    return tradeNum;
  }
  
  public void setTradeNum(String tradeNum)
  {
    this.tradeNum = tradeNum;
  }
  
  public String getTradeName()
  {
    return tradeName;
  }
  
  public void setTradeName(String tradeName)
  {
    this.tradeName = tradeName;
  }
  
  public String getTradeDescription()
  {
    return tradeDescription;
  }
  
  public void setTradeDescription(String tradeDescription)
  {
    this.tradeDescription = tradeDescription;
  }
  
  public String getTradeTime()
  {
    return tradeTime;
  }
  
  public void setTradeTime(String tradeTime)
  {
    this.tradeTime = tradeTime;
  }
  
  public String getTradeAmount()
  {
    return tradeAmount;
  }
  
  public void setTradeAmount(String tradeAmount)
  {
    this.tradeAmount = tradeAmount;
  }
  
  public String getCurrency()
  {
    return currency;
  }
  
  public void setCurrency(String currency)
  {
    this.currency = currency;
  }
  
  public String getNotifyUrl()
  {
    return notifyUrl;
  }
  
  public void setNotifyUrl(String notifyUrl)
  {
    this.notifyUrl = notifyUrl;
  }
  
  public String getSuccessCallbackUrl()
  {
    return successCallbackUrl;
  }
  
  public void setSuccessCallbackUrl(String successCallbackUrl)
  {
    this.successCallbackUrl = successCallbackUrl;
  }
  
  public String getForPayLayerUrl()
  {
    return forPayLayerUrl;
  }
  
  public void setForPayLayerUrl(String forPayLayerUrl)
  {
    this.forPayLayerUrl = forPayLayerUrl;
  }
}

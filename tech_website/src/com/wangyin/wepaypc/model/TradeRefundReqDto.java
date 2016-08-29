package com.wangyin.wepaypc.model;

import java.io.Serializable;

public class TradeRefundReqDto
  implements Serializable
{
  private static final long serialVersionUID = -8666975318824787206L;
  public String version;
  private String tradeNum;
  private String oTradeNum;
  private int tradeAmount;
  private String tradeCurrency;
  private String tradeDate;
  private String tradeTime;
  private String tradeNotice;
  private String tradeNote;
  private String merchantNum;
  private String merchantSign;
  
  public String getTradeNum()
  {
    return tradeNum;
  }
  
  public void setTradeNum(String tradeNum)
  {
    this.tradeNum = tradeNum;
  }
  
  public String getoTradeNum()
  {
    return oTradeNum;
  }
  
  public void setoTradeNum(String oTradeNum)
  {
    this.oTradeNum = oTradeNum;
  }
  
  public String getVersion()
  {
    return version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public int getTradeAmount()
  {
    return tradeAmount;
  }
  
  public void setTradeAmount(int tradeAmount)
  {
    this.tradeAmount = tradeAmount;
  }
  
  public String getTradeCurrency()
  {
    return tradeCurrency;
  }
  
  public void setTradeCurrency(String tradeCurrency)
  {
    this.tradeCurrency = tradeCurrency;
  }
  
  public String getTradeDate()
  {
    return tradeDate;
  }
  
  public void setTradeDate(String tradeDate)
  {
    this.tradeDate = tradeDate;
  }
  
  public String getTradeTime()
  {
    return tradeTime;
  }
  
  public void setTradeTime(String tradeTime)
  {
    this.tradeTime = tradeTime;
  }
  
  public String getTradeNotice()
  {
    return tradeNotice;
  }
  
  public void setTradeNotice(String tradeNotice)
  {
    this.tradeNotice = tradeNotice;
  }
  
  public String getTradeNote()
  {
    return tradeNote;
  }
  
  public void setTradeNote(String tradeNote)
  {
    this.tradeNote = tradeNote;
  }
  
  public String getMerchantNum()
  {
    return merchantNum;
  }
  
  public void setMerchantNum(String merchantNum)
  {
    this.merchantNum = merchantNum;
  }
  
  public String getMerchantSign()
  {
    return merchantSign;
  }
  
  public void setMerchantSign(String merchantSign)
  {
    this.merchantSign = merchantSign;
  }
}

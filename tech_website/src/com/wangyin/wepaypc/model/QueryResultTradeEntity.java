package com.wangyin.wepaypc.model;

import java.io.Serializable;

public class QueryResultTradeEntity
  implements Serializable
{
  private static final long serialVersionUID = -7412587299753288219L;
  private String tradeNum;
  private int tradeAmount;
  private String tradeCurrency;
  private String tradeDate;
  private String tradeTime;
  private String tradeNote;
  private String tradeStatus;
  
  public String getTradeNum()
  {
    return tradeNum;
  }
  
  public void setTradeNum(String tradeNum)
  {
    this.tradeNum = tradeNum;
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
  
  public String getTradeNote()
  {
    return tradeNote;
  }
  
  public void setTradeNote(String tradeNote)
  {
    this.tradeNote = tradeNote;
  }
  
  public String getTradeStatus()
  {
    return tradeStatus;
  }
  
  public void setTradeStatus(String tradeStatus)
  {
    this.tradeStatus = tradeStatus;
  }
}

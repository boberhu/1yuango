package com.wangyin.wepaypc.model;

import java.io.Serializable;

public class TradeQueryReqDto
  implements Serializable
{
  private static final long serialVersionUID = 7573802052381870368L;
  public String version;
  private String tradeNum;
  private String merchantNum;
  private String merchantSign;
  
  public String getVersion()
  {
    return version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getTradeNum()
  {
    return tradeNum;
  }
  
  public void setTradeNum(String tradeNum)
  {
    this.tradeNum = tradeNum;
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

package com.tech.pojo;

import java.io.Serializable;

public class LotteryDetailJSON
  implements Serializable
{
  private Integer productId;
  private String productName;
  private String productTitle;
  private Integer productPeriod;
  private Integer productStatus;
  private Integer spellbuyProductId;
  private String userName;
  private Integer userId;
  private String buyTime;
  private Integer buyCount;
  private String buyDate;
  private String dateSum;
  
  public LotteryDetailJSON() {}
  
  public LotteryDetailJSON(Integer productId, String productName, String productTitle, Integer productPeriod, Integer productStatus, Integer spellbuyProductId, String userName, Integer userId, String buyTime, Integer buyCount, String buyDate, String dateSum)
  {
    this.productId = productId;
    this.productName = productName;
    this.productTitle = productTitle;
    this.productPeriod = productPeriod;
    this.productStatus = productStatus;
    this.spellbuyProductId = spellbuyProductId;
    this.userName = userName;
    this.userId = userId;
    this.buyTime = buyTime;
    this.buyCount = buyCount;
    this.buyDate = buyDate;
    this.dateSum = dateSum;
  }
  
  public Integer getProductId()
  {
    return productId;
  }
  
  public void setProductId(Integer productId)
  {
    this.productId = productId;
  }
  
  public String getProductName()
  {
    return productName;
  }
  
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  
  public String getProductTitle()
  {
    return productTitle;
  }
  
  public void setProductTitle(String productTitle)
  {
    this.productTitle = productTitle;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public Integer getSpellbuyProductId() {
	return spellbuyProductId;
}

public void setSpellbuyProductId(Integer spellbuyProductId) {
	this.spellbuyProductId = spellbuyProductId;
}

public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String getBuyTime()
  {
    return buyTime;
  }
  
  public void setBuyTime(String buyTime)
  {
    this.buyTime = buyTime;
  }
  
  public Integer getBuyCount()
  {
    return buyCount;
  }
  
  public void setBuyCount(Integer buyCount)
  {
    this.buyCount = buyCount;
  }
  
  public String getBuyDate()
  {
    return buyDate;
  }
  
  public void setBuyDate(String buyDate)
  {
    this.buyDate = buyDate;
  }
  
  public String getDateSum()
  {
    return dateSum;
  }
  
  public void setDateSum(String dateSum)
  {
    this.dateSum = dateSum;
  }
  
  public Integer getProductStatus()
  {
    return productStatus;
  }
  
  public void setProductStatus(Integer productStatus)
  {
    this.productStatus = productStatus;
  }
}

package com.tech.pojo;

import java.io.Serializable;

public class OrderListJSON
  implements Serializable
{
  private Integer productId;
  private String productName;
  private String productTitle;
  private String productImg;
  private Integer productPeriod;
  private Integer buyStatus;
  private Integer shareStatus;
  private Integer shareId;
  private String winUser;
  private String buyTime;
  private Long buyCount;
  private Integer historyId;
  private Integer winUserId;
  private Integer winId;
  private String winDate;
  private Integer spellbuyCount;
  private Integer productPrice;
  
  public String getBuyTime()
  {
    return buyTime;
  }
  
  public void setBuyTime(String buyTime)
  {
    this.buyTime = buyTime;
  }
  
  public Integer getSpellbuyCount()
  {
    return spellbuyCount;
  }
  
  public void setSpellbuyCount(Integer spellbuyCount)
  {
    this.spellbuyCount = spellbuyCount;
  }
  
  public Integer getProductPrice()
  {
    return productPrice;
  }
  
  public void setProductPrice(Integer productPrice)
  {
    this.productPrice = productPrice;
  }
  
  public Integer getWinUserId()
  {
    return winUserId;
  }
  
  public void setWinUserId(Integer winUserId)
  {
    this.winUserId = winUserId;
  }
  
  public Integer getWinId()
  {
    return winId;
  }
  
  public void setWinId(Integer winId)
  {
    this.winId = winId;
  }
  
  public String getWinDate()
  {
    return winDate;
  }
  
  public void setWinDate(String winDate)
  {
    this.winDate = winDate;
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
  
  public String getProductImg()
  {
    return productImg;
  }
  
  public void setProductImg(String productImg)
  {
    this.productImg = productImg;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public Integer getBuyStatus()
  {
    return buyStatus;
  }
  
  public void setBuyStatus(Integer buyStatus)
  {
    this.buyStatus = buyStatus;
  }
  
  public String getWinUser()
  {
    return winUser;
  }
  
  public void setWinUser(String winUser)
  {
    this.winUser = winUser;
  }
  
  public Long getBuyCount()
  {
    return buyCount;
  }
  
  public void setBuyCount(Object buyCount)
  {
    this.buyCount = Long.valueOf(Long.parseLong(buyCount.toString()));
  }
  
  public Integer getHistoryId()
  {
    return historyId;
  }
  
  public void setHistoryId(Integer historyId)
  {
    this.historyId = historyId;
  }
  
  public Integer getShareStatus()
  {
    return shareStatus;
  }
  
  public void setShareStatus(Integer shareStatus)
  {
    this.shareStatus = shareStatus;
  }
  
  public Integer getShareId()
  {
    return shareId;
  }
  
  public void setShareId(Integer shareId)
  {
    this.shareId = shareId;
  }
  
  public void setBuyCount(Long buyCount)
  {
    this.buyCount = buyCount;
  }
}

package com.tech.pojo;

import java.io.Serializable;

public class ProductJSON
  implements Serializable
{
  private static final long serialVersionUID = -8927491985109183983L;
  private Integer productId;
  private Integer spellbuyProductId;
  private String productName;
  private Integer productPrice;
  private Integer singlePrice;
  private Integer spellbuyLimit;
  private String productTitle;
  private String headImage;
  private Integer currentBuyCount;
  private String buyer;
  private String userId;
  private Integer productPeriod;
  private String buyDate;
  private Integer buyCount;
  private String productStyle;
  
  public ProductJSON() {}
  
  public ProductJSON(Integer productId, Integer spellbuyProductId, String productName, Integer productPrice, Integer singlePrice, Integer spellbuyLimit, String productTitle, String headImage, Integer currentBuyCount, String buyer, String userId, Integer productPeriod, String buyDate, Integer buyCount, String productStyle)
  {
    this.productId = productId;
    this.spellbuyProductId = spellbuyProductId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.singlePrice = singlePrice;
    this.spellbuyLimit = spellbuyLimit;
    this.productTitle = productTitle;
    this.headImage = headImage;
    this.currentBuyCount = currentBuyCount;
    this.buyer = buyer;
    this.userId = userId;
    this.productPeriod = productPeriod;
    this.buyDate = buyDate;
    this.buyCount = buyCount;
    this.productStyle = productStyle;
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
  
  public Integer getProductPrice()
  {
    return productPrice;
  }
  
  public void setProductPrice(Integer productPrice)
  {
    this.productPrice = productPrice;
  }
  
  public String getProductTitle()
  {
    return productTitle;
  }
  
  public void setProductTitle(String productTitle)
  {
    this.productTitle = productTitle;
  }
  
  public String getHeadImage()
  {
    return headImage;
  }
  
  public void setHeadImage(String headImage)
  {
    this.headImage = headImage;
  }
  
  public Integer getCurrentBuyCount()
  {
    return currentBuyCount;
  }
  
  public void setCurrentBuyCount(Integer currentBuyCount)
  {
    this.currentBuyCount = currentBuyCount;
  }
  
  public String getBuyer()
  {
    return buyer;
  }
  
  public void setBuyer(String buyer)
  {
    this.buyer = buyer;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public String getBuyDate()
  {
    return buyDate;
  }
  
  public void setBuyDate(String buyDate)
  {
    this.buyDate = buyDate;
  }
  
  public Integer getBuyCount()
  {
    return buyCount;
  }
  
  public void setBuyCount(Integer buyCount)
  {
    this.buyCount = buyCount;
  }
  
  public String getProductStyle()
  {
    return productStyle;
  }
  
  public void setProductStyle(String productStyle)
  {
    this.productStyle = productStyle;
  }
  
  public Integer getSinglePrice()
  {
    return singlePrice;
  }
  
  public void setSinglePrice(Integer singlePrice)
  {
    this.singlePrice = singlePrice;
  }
  
  public Integer getSpellbuyLimit()
  {
    return spellbuyLimit;
  }
  
  public void setSpellbuyLimit(Integer spellbuyLimit)
  {
    this.spellbuyLimit = spellbuyLimit;
  }
  
  public Integer getSpellbuyProductId()
  {
    return spellbuyProductId;
  }
  
  public void setSpellbuyProductId(Integer spellbuyProductId)
  {
    this.spellbuyProductId = spellbuyProductId;
  }
}

package com.tech.pojo;

import java.io.Serializable;

public class ProductCart implements Serializable
{
  private static final long serialVersionUID = 7721286969488307469L;
  
  private Integer pid;
  private Integer productId;
  private String productName;
  private String productTitle;
  private Integer productPrice;
  private Integer singlePrice;
  private Integer productLimit;
  private Integer productStyle;
  private Integer myLimitSales;
  private String headImage;
  private Integer productCount;
  private Integer count;
  private Integer moneyCount;
  private Integer currentBuyCount;
  private Integer productPeriod;
  
  public ProductCart() {}
  
  public ProductCart(Integer productId, String productName, String productTitle, 
		  Integer productPrice, Integer singlePrice, Integer productLimit, 
		  Integer myLimitSales, Integer productStyle, String headImage, Integer productCount, 
		  Integer count, Integer moneyCount, Integer currentBuyCount, Integer productPeriod)
  {
    this.productId = productId;
    this.productName = productName;
    this.productTitle = productTitle;
    this.productPrice = productPrice;
    this.singlePrice = singlePrice;
    this.productLimit = productLimit;
    this.myLimitSales = myLimitSales;
    this.productStyle = productStyle;
    this.headImage = headImage;
    this.productCount = productCount;
    this.count = count;
    this.moneyCount = moneyCount;
    this.currentBuyCount = currentBuyCount;
    this.productPeriod = productPeriod;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public Integer getCurrentBuyCount()
  {
    return currentBuyCount;
  }
  
  public void setCurrentBuyCount(Integer currentBuyCount)
  {
    this.currentBuyCount = currentBuyCount;
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
  
  public Integer getProductPrice()
  {
    return productPrice;
  }
  
  public void setProductPrice(Integer productPrice)
  {
    this.productPrice = productPrice;
  }
  
  public String getHeadImage()
  {
    return headImage;
  }
  
  public void setHeadImage(String headImage)
  {
    this.headImage = headImage;
  }
  
  public Integer getProductCount()
  {
    return productCount;
  }
  
  public void setProductCount(Integer productCount)
  {
    this.productCount = productCount;
  }
  
  public Integer getCount()
  {
    return count;
  }
  
  public void setCount(Integer count)
  {
    this.count = count;
  }
  
  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
  }
  
  public static long getSerialVersionUID()
  {
    return 7721286969488307469L;
  }
  
  public Integer getSinglePrice()
  {
    return singlePrice;
  }
  
  public void setSinglePrice(Integer singlePrice)
  {
    this.singlePrice = singlePrice;
  }
  
  public Integer getProductLimit()
  {
    return productLimit;
  }
  
  public void setProductLimit(Integer productLimit)
  {
    this.productLimit = productLimit;
  }
  
  public Integer getMyLimitSales()
  {
    return myLimitSales;
  }
  
  public void setMyLimitSales(Integer myLimitSales)
  {
    this.myLimitSales = myLimitSales;
  }
  
  public Integer getProductStyle()
  {
    return productStyle;
  }
  
  public void setProductStyle(Integer productStyle)
  {
    this.productStyle = productStyle;
  }
  
  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }
  
  public ProductCart init(final Product product, final Spellbuyproduct spellbuyproduct,
		  final int buyingCount, final int moneyCount){
    this.setCount(buyingCount);
    this.setHeadImage(product.getHeadImage());
    this.setMoneyCount(moneyCount);
    this.setProductCount(buyingCount);
    this.setPid(product.getProductId());
    this.setProductId(spellbuyproduct.getSpellbuyProductId());
    this.setProductName(product.getProductName());
    this.setProductPrice(spellbuyproduct.getSpellbuyPrice());
    this.setSinglePrice(spellbuyproduct.getSpSinglePrice());
    this.setProductLimit(spellbuyproduct.getSpellbuyLimit());
	if (product.getProductLimit().intValue() > 0) {
		this.setProductStyle(Integer.valueOf(3));
	} else {
		this.setProductStyle(Integer.valueOf(0));
	}
	this.setProductTitle(product.getProductTitle());
	this.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
	this.setProductPeriod(spellbuyproduct.getProductPeriod());
	return this;
  }
  
}

package com.tech.pojo;

import java.io.Serializable;

public class Spellbuyproduct implements Serializable
{
  private static final long serialVersionUID = -8674664939494195036L;
  // const
  /** 可购买的。 */
  public static final Integer STATUS_BUYABLE = Integer.valueOf(0);
  /** 可揭晓的。 */
  public static final Integer STATUS_ANNABLE = Integer.valueOf(1);
  /** 可开奖的。 */
  public static final Integer STATUS_LOTABLE = Integer.valueOf(2);
  
  /** 普通的。 */
  public static final Integer BUYTYPE_COMM = Integer.valueOf(0);
  /** 限时的。 */
  public static final Integer BUYTYPE_TIME = Integer.valueOf(1);
  /** 热门的。 */
  public static final Integer BUYTYPE_HOT  = Integer.valueOf(8);
  
  // props
  private Integer spellbuyProductId;
  private Integer fkProductId;
  private String spellbuyStartDate;
  private String spellbuyEndDate;
  private Integer spellbuyCount;
  private Integer spellbuyPrice;
  private Integer spSinglePrice;
  private Integer spellbuyLimit;
  private Integer productPeriod;
  private Integer spStatus;
  private Integer spellbuyType;
  private String attribute64;
  private String attribute65;
  
  public Spellbuyproduct(Integer spellbuyProductId, Integer fkProductId, String spellbuyStartDate, 
		  String spellbuyEndDate, Integer spellbuyCount, Integer spellbuyPrice, Integer spSinglePrice, 
		  Integer spellbuyLimit, Integer productPeriod, Integer spStatus, Integer spellbuyType, 
		  String attribute64, String attribute65)
  {
    this.spellbuyProductId = spellbuyProductId;
    this.fkProductId = fkProductId;
    this.spellbuyStartDate = spellbuyStartDate;
    this.spellbuyEndDate = spellbuyEndDate;
    this.spellbuyCount = spellbuyCount;
    this.spellbuyPrice = spellbuyPrice;
    this.spSinglePrice = spSinglePrice;
    this.spellbuyLimit = spellbuyLimit;
    this.productPeriod = productPeriod;
    this.spStatus = spStatus;
    this.spellbuyType = spellbuyType;
    this.attribute64 = attribute64;
    this.attribute65 = attribute65;
  }
  
  public Spellbuyproduct() {}
  
  public Spellbuyproduct(Integer fkProductId, String spellbuyStartDate, String spellbuyEndDate, 
		  Integer spellbuyCount, Integer spellbuyPrice, Integer spSinglePrice)
  {
    this.fkProductId = fkProductId;
    this.spellbuyStartDate = spellbuyStartDate;
    this.spellbuyEndDate = spellbuyEndDate;
    this.spellbuyCount = spellbuyCount;
    this.spellbuyPrice = spellbuyPrice;
    this.spSinglePrice = spSinglePrice;
  }
  
  public Integer getSpellbuyProductId()
  {
    return spellbuyProductId;
  }
  
  public void setSpellbuyProductId(Integer spellbuyProductId)
  {
    this.spellbuyProductId = spellbuyProductId;
  }
  
  public Integer getFkProductId()
  {
    return fkProductId;
  }
  
  public void setFkProductId(Integer fkProductId)
  {
    this.fkProductId = fkProductId;
  }
  
  public String getSpellbuyStartDate()
  {
    return spellbuyStartDate;
  }
  
  public void setSpellbuyStartDate(String spellbuyStartDate)
  {
    this.spellbuyStartDate = spellbuyStartDate;
  }
  
  public String getSpellbuyEndDate()
  {
    return spellbuyEndDate;
  }
  
  public void setSpellbuyEndDate(String spellbuyEndDate)
  {
    this.spellbuyEndDate = spellbuyEndDate;
  }
  
  public Integer getSpellbuyCount()
  {
    return spellbuyCount;
  }
  
  public void setSpellbuyCount(Integer spellbuyCount)
  {
    this.spellbuyCount = spellbuyCount;
  }
  
  public Integer getSpellbuyPrice()
  {
    return spellbuyPrice;
  }
  
  public void setSpellbuyPrice(Integer spellbuyPrice)
  {
    this.spellbuyPrice = spellbuyPrice;
  }
  
  public String getAttribute64()
  {
    return attribute64;
  }
  
  public void setAttribute64(String attribute64)
  {
    this.attribute64 = attribute64;
  }
  
  public String getAttribute65()
  {
    return attribute65;
  }
  
  public void setAttribute65(String attribute65)
  {
    this.attribute65 = attribute65;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public Integer getSpStatus()
  {
    return spStatus;
  }
  
  public void setSpStatus(Integer spStatus)
  {
    this.spStatus = spStatus;
  }
  
  public Integer getSpellbuyType()
  {
    return spellbuyType;
  }
  
  public void setSpellbuyType(Integer spellbuyType)
  {
    this.spellbuyType = spellbuyType;
  }
  
  public Integer getSpSinglePrice()
  {
    return spSinglePrice;
  }
  
  public void setSpSinglePrice(Integer spSinglePrice)
  {
    this.spSinglePrice = spSinglePrice;
  }
  
  public Integer getSpellbuyLimit()
  {
    return spellbuyLimit;
  }
  
  public void setSpellbuyLimit(Integer spellbuyLimit)
  {
    this.spellbuyLimit = spellbuyLimit;
  }
  
  public boolean isLotteryable(){
	  return (STATUS_LOTABLE.equals(getSpStatus()));
  }
  
  public boolean isBuyable(){
	  return (STATUS_BUYABLE.equals(getSpStatus()));
  }
  
  public boolean isAnnable(){
	  return (STATUS_ANNABLE.equals(getSpStatus()));
  }

  private Product product;
  
  public void setProduct(Product product) {
	this.product = product;
	
  }
  
  public Product getProduct(){
    return this.product;
  }
  
}

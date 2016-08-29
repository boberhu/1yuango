package com.tech.pojo;

import java.io.Serializable;

public class Lotteryproductutil implements Serializable
{
  private static final long serialVersionUID = 2148940961420931385L;
  
  private Integer lotteryId;
  private Integer lotteryProductId;
  private String lotteryProductName;
  private String lotteryProductTitle;
  private Integer lotteryProductPrice;
  private Integer lotteryProductPeriod;
  private String lotteryProductImg;
  private String lotteryProductEndDate;
  
  public Lotteryproductutil() {}
  
  public Lotteryproductutil(Integer lotteryProductId, String lotteryProductName, String lotteryProductTitle, Integer lotteryProductPrice, Integer lotteryProductPeriod, String lotteryProductImg, String lotteryProductEndDate)
  {
    this.lotteryProductId = lotteryProductId;
    this.lotteryProductName = lotteryProductName;
    this.lotteryProductTitle = lotteryProductTitle;
    this.lotteryProductPrice = lotteryProductPrice;
    this.lotteryProductPeriod = lotteryProductPeriod;
    this.lotteryProductImg = lotteryProductImg;
    this.lotteryProductEndDate = lotteryProductEndDate;
  }
  
  public Integer getLotteryId()
  {
    return lotteryId;
  }
  
  public void setLotteryId(Integer lotteryId)
  {
    this.lotteryId = lotteryId;
  }
  
  public Integer getLotteryProductId()
  {
    return lotteryProductId;
  }
  
  public void setLotteryProductId(Integer lotteryProductId)
  {
    this.lotteryProductId = lotteryProductId;
  }
  
  public String getLotteryProductName()
  {
    return lotteryProductName;
  }
  
  public void setLotteryProductName(String lotteryProductName)
  {
    this.lotteryProductName = lotteryProductName;
  }
  
  public String getLotteryProductTitle()
  {
    return lotteryProductTitle;
  }
  
  public void setLotteryProductTitle(String lotteryProductTitle)
  {
    this.lotteryProductTitle = lotteryProductTitle;
  }
  
  public Integer getLotteryProductPrice()
  {
    return lotteryProductPrice;
  }
  
  public void setLotteryProductPrice(Integer lotteryProductPrice)
  {
    this.lotteryProductPrice = lotteryProductPrice;
  }
  
  public Integer getLotteryProductPeriod()
  {
    return lotteryProductPeriod;
  }
  
  public void setLotteryProductPeriod(Integer lotteryProductPeriod)
  {
    this.lotteryProductPeriod = lotteryProductPeriod;
  }
  
  public String getLotteryProductImg()
  {
    return lotteryProductImg;
  }
  
  public void setLotteryProductImg(String lotteryProductImg)
  {
    this.lotteryProductImg = lotteryProductImg;
  }
  
  public String getLotteryProductEndDate()
  {
    return lotteryProductEndDate;
  }
  
  public void setLotteryProductEndDate(String lotteryProductEndDate)
  {
    this.lotteryProductEndDate = lotteryProductEndDate;
  }
}

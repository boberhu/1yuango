package com.tech.pojo;

import java.io.Serializable;

public class Randomnumber
  implements Serializable
{
  private Integer id;
  private Integer spellbuyrecordId;
  private Integer userId;
  private Integer productId;
  private String randomNumber;
  private String buyDate;
  
  public Randomnumber(Integer id, Integer spellbuyrecordId, Integer userId, Integer productId, String randomNumber, String buyDate)
  {
    this.id = id;
    this.spellbuyrecordId = spellbuyrecordId;
    this.userId = userId;
    this.productId = productId;
    this.randomNumber = randomNumber;
    this.buyDate = buyDate;
  }
  
  public Randomnumber() {}
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getSpellbuyrecordId()
  {
    return spellbuyrecordId;
  }
  
  public void setSpellbuyrecordId(Integer spellbuyrecordId)
  {
    this.spellbuyrecordId = spellbuyrecordId;
  }
  
  public String getRandomNumber()
  {
    return randomNumber;
  }
  
  public void setRandomNumber(String randomNumber)
  {
    this.randomNumber = randomNumber;
  }
  
  public Integer getProductId()
  {
    return productId;
  }
  
  public void setProductId(Integer productId)
  {
    this.productId = productId;
  }
  
  public String getBuyDate()
  {
    return buyDate;
  }
  
  public void setBuyDate(String buyDate)
  {
    this.buyDate = buyDate;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
}

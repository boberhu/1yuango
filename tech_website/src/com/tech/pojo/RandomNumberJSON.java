package com.tech.pojo;

public class RandomNumberJSON
{
  private String buyDate;
  private String buyCount;
  private String randomNumbers;
  
  public RandomNumberJSON() {}
  
  public RandomNumberJSON(String buyDate, String buyCount, String randomNumbers)
  {
    this.buyDate = buyDate;
    this.buyCount = buyCount;
    this.randomNumbers = randomNumbers;
  }
  
  public String getBuyCount()
  {
    return buyCount;
  }
  
  public void setBuyCount(String buyCount)
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
  
  public String getRandomNumbers()
  {
    return randomNumbers;
  }
  
  public void setRandomNumbers(String randomNumbers)
  {
    this.randomNumbers = randomNumbers;
  }
}

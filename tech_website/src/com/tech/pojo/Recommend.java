package com.tech.pojo;

import java.io.Serializable;

public class Recommend
  implements Serializable
{
  private Integer recommendId;
  private Integer spellbuyProductId;
  private String date;
  
  public Recommend() {}
  
  public Recommend(Integer recommendId, Integer spellbuyProductId, String date)
  {
    this.recommendId = recommendId;
    this.spellbuyProductId = spellbuyProductId;
    this.date = date;
  }
  
  public Integer getRecommendId()
  {
    return recommendId;
  }
  
  public void setRecommendId(Integer recommendId)
  {
    this.recommendId = recommendId;
  }
  
  public Integer getSpellbuyProductId()
  {
    return spellbuyProductId;
  }
  
  public void setSpellbuyProductId(Integer spellbuyProductId)
  {
    this.spellbuyProductId = spellbuyProductId;
  }
  
  public String getDate()
  {
    return date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
}

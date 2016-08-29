package com.tech.pojo;

import java.io.Serializable;

public class Collectproduct
  implements Serializable
{
  private Integer id;
  private Integer collectUserId;
  private Integer collectProductId;
  
  public Collectproduct() {}
  
  public Collectproduct(Integer collectUserId, Integer collectProductId)
  {
    this.collectUserId = collectUserId;
    this.collectProductId = collectProductId;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getCollectUserId()
  {
    return collectUserId;
  }
  
  public void setCollectUserId(Integer collectUserId)
  {
    this.collectUserId = collectUserId;
  }
  
  public Integer getCollectProductId()
  {
    return collectProductId;
  }
  
  public void setCollectProductId(Integer collectProductId)
  {
    this.collectProductId = collectProductId;
  }
}

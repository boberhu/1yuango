package com.tech.pojo;

import java.io.Serializable;

public class Auctionproduct
  implements Serializable
{
  private Integer auctionProductId;
  private Integer fkProductId;
  private String auctionStartDate;
  private String auctionEndDate;
  private String auctionCount;
  private String attribute62;
  private String attribute63;
  
  public Auctionproduct() {}
  
  public Auctionproduct(Integer fkProductId, String auctionStartDate, String auctionEndDate, String auctionCount)
  {
    this.fkProductId = fkProductId;
    this.auctionStartDate = auctionStartDate;
    this.auctionEndDate = auctionEndDate;
    this.auctionCount = auctionCount;
  }
  
  public Auctionproduct(Integer fkProductId, String auctionStartDate, String auctionEndDate, String auctionCount, String attribute62, String attribute63)
  {
    this.fkProductId = fkProductId;
    this.auctionStartDate = auctionStartDate;
    this.auctionEndDate = auctionEndDate;
    this.auctionCount = auctionCount;
    this.attribute62 = attribute62;
    this.attribute63 = attribute63;
  }
  
  public Integer getAuctionProductId()
  {
    return auctionProductId;
  }
  
  public void setAuctionProductId(Integer auctionProductId)
  {
    this.auctionProductId = auctionProductId;
  }
  
  public Integer getFkProductId()
  {
    return fkProductId;
  }
  
  public void setFkProductId(Integer fkProductId)
  {
    this.fkProductId = fkProductId;
  }
  
  public String getAuctionStartDate()
  {
    return auctionStartDate;
  }
  
  public void setAuctionStartDate(String auctionStartDate)
  {
    this.auctionStartDate = auctionStartDate;
  }
  
  public String getAuctionEndDate()
  {
    return auctionEndDate;
  }
  
  public void setAuctionEndDate(String auctionEndDate)
  {
    this.auctionEndDate = auctionEndDate;
  }
  
  public String getAuctionCount()
  {
    return auctionCount;
  }
  
  public void setAuctionCount(String auctionCount)
  {
    this.auctionCount = auctionCount;
  }
  
  public String getAttribute62()
  {
    return attribute62;
  }
  
  public void setAttribute62(String attribute62)
  {
    this.attribute62 = attribute62;
  }
  
  public String getAttribute63()
  {
    return attribute63;
  }
  
  public void setAttribute63(String attribute63)
  {
    this.attribute63 = attribute63;
  }
}

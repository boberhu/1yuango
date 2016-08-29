package com.tech.pojo;

import java.io.Serializable;

public class Commissionquery
  implements Serializable
{
  private Integer id;
  private String date;
  private String description;
  private Double buyMoney;
  private Double commission;
  private Integer toUserId;
  private Integer invitedId;
  
  public Commissionquery() {}
  
  public Commissionquery(String date, String description, Double buyMoney, Double commission, Integer toUserId, Integer invitedId)
  {
    this.date = date;
    this.description = description;
    this.buyMoney = buyMoney;
    this.commission = commission;
    this.toUserId = toUserId;
    this.invitedId = invitedId;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getDate()
  {
    return date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public Double getBuyMoney()
  {
    return buyMoney;
  }
  
  public void setBuyMoney(Double buyMoney)
  {
    this.buyMoney = buyMoney;
  }
  
  public Double getCommission()
  {
    return commission;
  }
  
  public void setCommission(Double commission)
  {
    this.commission = commission;
  }
  
  public Integer getToUserId()
  {
    return toUserId;
  }
  
  public void setToUserId(Integer toUserId)
  {
    this.toUserId = toUserId;
  }
  
  public Integer getInvitedId()
  {
    return invitedId;
  }
  
  public void setInvitedId(Integer invitedId)
  {
    this.invitedId = invitedId;
  }
}

package com.tech.pojo;

import java.io.Serializable;

public class ParticipateJSON
  implements Serializable
{
  private String userId;
  private String userName;
  private String userFace;
  private String ip_address;
  private String ip_location;
  private Integer buySource;
  private String buyCount;
  private String buyDate;
  private String buyId;
  
  public ParticipateJSON() {}
  
  public ParticipateJSON(String userId, String userName, String userFace, String ip_address, String ip_location, Integer buySource, String buyCount, String buyDate, String buyId)
  {
    this.userId = userId;
    this.userName = userName;
    this.userFace = userFace;
    this.ip_address = ip_address;
    this.ip_location = ip_location;
    this.buySource = buySource;
    this.buyCount = buyCount;
    this.buyDate = buyDate;
    this.buyId = buyId;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getUserFace()
  {
    return userFace;
  }
  
  public void setUserFace(String userFace)
  {
    this.userFace = userFace;
  }
  
  public String getIp_address()
  {
    return ip_address;
  }
  
  public void setIp_address(String ip_address)
  {
    this.ip_address = ip_address;
  }
  
  public String getIp_location()
  {
    return ip_location;
  }
  
  public void setIp_location(String ip_location)
  {
    this.ip_location = ip_location;
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
  
  public String getBuyId()
  {
    return buyId;
  }
  
  public void setBuyId(String buyId)
  {
    this.buyId = buyId;
  }
  
  public Integer getBuySource()
  {
    return buySource;
  }
  
  public void setBuySource(Integer buySource)
  {
    this.buySource = buySource;
  }

}

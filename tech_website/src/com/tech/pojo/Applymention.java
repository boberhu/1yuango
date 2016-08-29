package com.tech.pojo;

import java.io.Serializable;

public class Applymention
  implements Serializable
{
  private Integer id;
  private String date;
  private Double money;
  private Double fee;
  private String bankName;
  private String bankUser;
  private String bankSubbranch;
  private String bankNo;
  private String phone;
  private Integer userId;
  private String status;
  
  public Applymention(String date, Double money, Double fee, String bankName, String bankUser, String bankSubbranch, String bankNo, String phone, Integer userId, String status)
  {
    this.date = date;
    this.money = money;
    this.fee = fee;
    this.bankName = bankName;
    this.bankUser = bankUser;
    this.bankSubbranch = bankSubbranch;
    this.bankNo = bankNo;
    this.phone = phone;
    this.userId = userId;
    this.status = status;
  }
  
  public Applymention() {}
  
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
  
  public Double getMoney()
  {
    return money;
  }
  
  public void setMoney(Double money)
  {
    this.money = money;
  }
  
  public Double getFee()
  {
    return fee;
  }
  
  public void setFee(Double fee)
  {
    this.fee = fee;
  }
  
  public String getBankName()
  {
    return bankName;
  }
  
  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }
  
  public String getBankUser()
  {
    return bankUser;
  }
  
  public void setBankUser(String bankUser)
  {
    this.bankUser = bankUser;
  }
  
  public String getBankSubbranch()
  {
    return bankSubbranch;
  }
  
  public void setBankSubbranch(String bankSubbranch)
  {
    this.bankSubbranch = bankSubbranch;
  }
  
  public String getBankNo()
  {
    return bankNo;
  }
  
  public void setBankNo(String bankNo)
  {
    this.bankNo = bankNo;
  }
  
  public String getPhone()
  {
    return phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}

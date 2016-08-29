package com.tech.pojo;

import java.io.Serializable;

public class Orderdetail
  implements Serializable
{
  private Integer id;
  private Integer orderDetailId;
  private String date;
  private String detailText;
  private String userName;
  private Integer addressId;
  
  public Orderdetail() {}
  
  public Orderdetail(Integer orderDetailId, String date, String detailText, String userName, Integer addressId)
  {
    this.orderDetailId = orderDetailId;
    this.date = date;
    this.detailText = detailText;
    this.userName = userName;
    this.addressId = addressId;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getOrderDetailId()
  {
    return orderDetailId;
  }
  
  public void setOrderDetailId(Integer orderDetailId)
  {
    this.orderDetailId = orderDetailId;
  }
  
  public String getDate()
  {
    return date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public String getDetailText()
  {
    return detailText;
  }
  
  public void setDetailText(String detailText)
  {
    this.detailText = detailText;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public Integer getAddressId()
  {
    return addressId;
  }
  
  public void setAddressId(Integer addressId)
  {
    this.addressId = addressId;
  }
}

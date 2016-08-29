package com.tech.util;

public class IPEntry
{
  public String beginIp;
  public String endIp;
  public String country;
  public String area;
  
  public IPEntry()
  {
    beginIp = (this.endIp = this.country = this.area = "");
  }
  
  public String toString()
  {
    return 
      area + "  " + country + "IP范围:" + beginIp + "-" + endIp;
  }
  
  public String getBeginIp()
  {
    return beginIp;
  }
  
  public void setBeginIp(String beginIp)
  {
    this.beginIp = beginIp;
  }
  
  public String getEndIp()
  {
    return endIp;
  }
  
  public void setEndIp(String endIp)
  {
    this.endIp = endIp;
  }
  
  public String getCountry()
  {
    return country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getArea()
  {
    return area;
  }
  
  public void setArea(String area)
  {
    this.area = area;
  }
}

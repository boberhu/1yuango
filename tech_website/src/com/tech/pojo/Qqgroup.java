package com.tech.pojo;

import java.io.Serializable;

public class Qqgroup
  implements Serializable
{
  private Integer qqid;
  private String groupName;
  private Integer groupNo;
  private Integer groupProvince;
  private Integer groupCity;
  private Integer groupDistrict;
  private String groupShowName;
  
  public Qqgroup(String groupName, Integer groupNo, Integer groupProvince, Integer groupCity, Integer groupDistrict, String groupShowName)
  {
    this.groupName = groupName;
    this.groupNo = groupNo;
    this.groupProvince = groupProvince;
    this.groupCity = groupCity;
    this.groupDistrict = groupDistrict;
    this.groupShowName = groupShowName;
  }
  
  public Qqgroup() {}
  
  public Integer getQqid()
  {
    return qqid;
  }
  
  public void setQqid(Integer qqid)
  {
    this.qqid = qqid;
  }
  
  public String getGroupName()
  {
    return groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public Integer getGroupNo()
  {
    return groupNo;
  }
  
  public void setGroupNo(Integer groupNo)
  {
    this.groupNo = groupNo;
  }
  
  public Integer getGroupProvince()
  {
    return groupProvince;
  }
  
  public void setGroupProvince(Integer groupProvince)
  {
    this.groupProvince = groupProvince;
  }
  
  public Integer getGroupCity()
  {
    return groupCity;
  }
  
  public void setGroupCity(Integer groupCity)
  {
    this.groupCity = groupCity;
  }
  
  public Integer getGroupDistrict()
  {
    return groupDistrict;
  }
  
  public void setGroupDistrict(Integer groupDistrict)
  {
    this.groupDistrict = groupDistrict;
  }
  
  public String getGroupShowName()
  {
    return groupShowName;
  }
  
  public void setGroupShowName(String groupShowName)
  {
    this.groupShowName = groupShowName;
  }
}

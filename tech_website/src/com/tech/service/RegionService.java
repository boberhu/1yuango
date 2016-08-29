package com.tech.service;

import com.tech.pojo.Qqgroup;

import java.util.List;

public abstract interface RegionService
{
  public abstract List getProvinceList();
  
  public abstract List getCityListByProvinceId(String paramString);
  
  public abstract List getDistrictListByCityId(String paramString);
  
  public abstract void addQQGroup(Qqgroup paramQqgroup);
  
  public abstract void delQQGroup(Integer paramInteger);
  
  public abstract List qqGroupByList(String paramString1, String paramString2, String paramString3);
}

package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Qqgroup;
import com.tech.service.RegionService;
import com.tech.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl
  implements RegionService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public List getCityListByProvinceId(String provinceId)
  {
    StringBuffer hql = new StringBuffer("select * from s_city where pid = '" + provinceId + "'");
    return baseDao.querySQL(hql.toString());
  }
  
  public List getDistrictListByCityId(String cityId)
  {
    StringBuffer hql = new StringBuffer("select * from s_district where cid = '" + cityId + "'");
    return baseDao.querySQL(hql.toString());
  }
  
  public List getProvinceList()
  {
    StringBuffer hql = new StringBuffer("select * from s_province");
    return baseDao.querySQL(hql.toString());
  }
  
  public void addQQGroup(Qqgroup qqgroup)
  {
    baseDao.saveOrUpdate(qqgroup);
  }
  
  public List qqGroupByList(String pId, String cId, String dId)
  {
    StringBuffer hql = new StringBuffer("select * from qqgroup q where 1=1 ");
    if (StringUtil.isNotBlank(pId)) {
      if (pId.equals("0")) {
        hql.append(" and q.groupProvince is not null");
      } else {
        hql.append(" and q.groupProvince='" + pId + "'");
      }
    }
    if (StringUtil.isNotBlank(cId)) {
      if (cId.equals("0")) {
        hql.append(" and q.groupCity is not null");
      } else {
        hql.append(" and q.groupCity='" + cId + "'");
      }
    }
    if (StringUtil.isNotBlank(dId)) {
      if (dId.equals("0")) {
        hql.append(" and q.groupDistrict is not null");
      } else {
        hql.append(" and q.groupDistrict='" + dId + "'");
      }
    }
    hql.append(" order by q.qqid desc");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("q", Qqgroup.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public void delQQGroup(Integer id)
  {
    baseDao.delById(Qqgroup.class, id);
  }
}

package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Applymention;
import com.tech.pojo.User;
import com.tech.service.ApplymentionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplymentionServiceImpl
  implements ApplymentionService
{
  @Autowired
  BaseDAO baseDao;
  
  public void add(Applymention t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id) {}
  
  public Applymention findById(String id)
  {
	  return (Applymention)baseDao.loadObject("from Applymention mention where mention.id="+Integer.parseInt(id));
  }
  
  public List<Applymention> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public Pagination getApplymentionList(String userId, String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from applymention an where 1=1");
    if (StringUtils.isNotBlank(userId)) {
      hql.append(" and  an.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and an.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and an.date <='" + endDate + "'");
    }
    hql.append(" order by an.date desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("an", Applymention.class);
    List list = baseDao.sqlQueryBean(hql, entityMap, page);
    page.setList(list);
    return page;
  }
  
  public Integer getApplymentionListByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(*) from applymention an where 1=1");
    if (StringUtils.isNotBlank(userId)) {
      sql.append(" and  an.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and an.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and an.date <='" + endDate + "'");
    }
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
}

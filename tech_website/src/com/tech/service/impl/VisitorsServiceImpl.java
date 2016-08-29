package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.User;
import com.tech.pojo.Visitors;
import com.tech.service.VisitorsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VisitorsServiceImpl
  implements VisitorsService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDAO;
  
  public void add(Visitors t)
  {
    baseDAO.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDAO.delById(Visitors.class, id);
  }
  
  public Visitors findById(String id)
  {
    return null;
  }
  
  public List<Visitors> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public Pagination getVisitors(String userId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from visitors vs,user ur where vs.visitorsId = ur.userId and vs.uid = '" + userId + "' order by date desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("vs", Visitors.class);
    entityMap.put("ur", User.class);
    List list = baseDAO.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public List findVisitors(String userId, String visitorsId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select * from visitors vs where vs.uid = '" + userId + "' and vs.visitorsId = '" + visitorsId + "' and vs.date >= '" + startDate + "' and vs.date <= '" + endDate + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("vs", Visitors.class);
    List list = baseDAO.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  public Visitors findVisitorsByUserIdAndVisitorsId(String userId, String visitorsId)
  {
    StringBuffer hql = new StringBuffer("from Visitors vs  where vs.uid = '" + userId + "'  and vs.visitorsId = '" + visitorsId + "'");
    return (Visitors)baseDAO.loadObject(hql.toString());
  }
}

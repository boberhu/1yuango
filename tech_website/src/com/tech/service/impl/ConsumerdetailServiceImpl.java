package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Consumerdetail;
import com.tech.service.ConsumerdetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("consumerdetailService")
public class ConsumerdetailServiceImpl
  implements ConsumerdetailService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void add(Consumerdetail t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Consumerdetail.class, id);
  }
  
  public Consumerdetail findById(String id)
  {
    return null;
  }
  
  public List<Consumerdetail> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public Pagination userByConsumetableDetail(String id, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from consumerdetail cl where cl.consumetableId = '" + id + "'");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("cl", Consumerdetail.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer userByConsumetableDetailByCount(String id)
  {
    StringBuffer sql = new StringBuffer("select count(*) from consumerdetail cl where cl.consumetableId = '" + id + "'");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination orderInfo(String id, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from consumerdetail cl where cl.consumetableId = '" + id + "'");
    StringBuffer sql = new StringBuffer("select count(*) from consumerdetail where consumetableId = '" + id + "'");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("cl", Consumerdetail.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
}

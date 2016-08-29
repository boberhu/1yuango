package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.News;
import com.tech.service.NewsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl
  implements NewsService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void add(News t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(News.class, id);
  }
  
  public News findById(String id)
  {
    StringBuffer hql = new StringBuffer("from News n where 1=1");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and n.newsId='" + id + "'");
    }
    return (News)baseDao.loadObject(hql.toString());
  }
  
  public List<News> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public Pagination newsList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from news n order by postDate desc");
    StringBuffer sql = new StringBuffer("select count(*) from news");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("n", News.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public List indexNews()
  {
    StringBuffer sql = new StringBuffer("select * from news n order by postDate desc limit 3");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("n", News.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
}

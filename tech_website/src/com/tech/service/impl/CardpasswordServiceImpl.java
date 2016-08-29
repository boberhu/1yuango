package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Cardpassword;
import com.tech.service.CardpasswordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardpasswordServiceImpl
  implements CardpasswordService
{
  @Autowired
  BaseDAO baseDAO;
  
  public Cardpassword doCardRecharge(String randomNo, String cardPwd)
  {
    StringBuffer hql = new StringBuffer();
    if ((StringUtils.isNotBlank(randomNo)) && (StringUtils.isNotBlank(cardPwd))) {
      hql.append("from Cardpassword cd where cd.randomNo ='" + randomNo + "' and cd.cardPwd ='" + cardPwd + "'");
    }
    return (Cardpassword)baseDAO.loadObject(hql.toString());
  }
  
  public void delete(Integer id)
  {
    baseDAO.delById(Cardpassword.class, id);
  }
  
  public void deleteByID(Integer id)
  {
    baseDAO.delById(Cardpassword.class, id);
  }
  
  public List query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public void add(Cardpassword t)
  {
    baseDAO.saveOrUpdate(t);
  }
  
  public Cardpassword findById(String id)
  {
    return null;
  }
  
  public Pagination cardRechargeList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from cardpassword cd where 1=1 order by cd.date desc");
    StringBuffer sql = new StringBuffer("select count(*) from cardpassword ");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("cd", Cardpassword.class);
    List list = baseDAO.sqlQuery(hql, entityMap, page);
    int resultCount = baseDAO.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
}

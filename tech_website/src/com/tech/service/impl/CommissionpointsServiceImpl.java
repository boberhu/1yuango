package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Commissionpoints;
import com.tech.service.CommissionpointsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionpointsServiceImpl
  implements CommissionpointsService
{
  @Autowired
  BaseDAO baseDao;
  
  public Pagination CommissionPoints(String userId, String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from commissionpoints cs where cs.toUserid = '" + userId + "'");
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and cs.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and cs.date <='" + endDate + "'");
    }
    hql.append(" order by cs.date desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("cs", Commissionpoints.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    return page;
  }

  @Override
  public Double totalPay(String startDate, String endDate) {
	  StringBuffer hql = new StringBuffer("select sum(cs.pay) from commissionpoints cs where 1=1 ");
	    if (StringUtils.isNotBlank(startDate)) {
	      hql.append(" and cs.date >='" + startDate + "'");
	    }
	    if (StringUtils.isNotBlank(endDate)) {
	      hql.append(" and cs.date <='" + endDate + "'");
	    }
	    hql.append(" and cs.pay like '-%' ");
	    Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (Double)result;
	    }else{
	    	return new Double(0);
	    }
	   
  }
  
  @Override
  public Double totalAdd(String startDate, String endDate) {
	  StringBuffer hql = new StringBuffer("select sum(cs.pay) from commissionpoints cs where 1=1 ");
	    if (StringUtils.isNotBlank(startDate)) {
	      hql.append(" and cs.date >='" + startDate + "'");
	    }
	    if (StringUtils.isNotBlank(endDate)) {
	      hql.append(" and cs.date <='" + endDate + "'");
	    }
	    hql.append(" and cs.pay like '+%' ");
	    Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (Double)result;
	    }else{
	    	return new Double(0);
	    }
	   
  }
  
  public void add(Commissionpoints t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id) {}
  
  public Commissionpoints findById(String id)
  {
    return null;
  }
  
  public List<Commissionpoints> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public Integer getCommissionPointsListByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(*) from commissionpoints cs where cs.toUserid = '" + userId + "'");
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and cs.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and cs.date <='" + endDate + "'");
    }
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
}

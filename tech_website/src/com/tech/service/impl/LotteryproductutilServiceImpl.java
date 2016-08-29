package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Lotteryproductutil;
import com.tech.pojo.Spellbuyrecord;
import com.tech.service.LotteryproductutilService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
public class LotteryproductutilServiceImpl implements LotteryproductutilService
{
  @Autowired
  BaseDAO baseDAO;
  
  public void add(Lotteryproductutil t)
  {
    baseDAO.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDAO.delById(Lotteryproductutil.class, id);
  }
  
  public Lotteryproductutil findById(String id)
  {
    StringBuffer hql = new StringBuffer("from Lotteryproductutil ll where 1=1");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and ll.lotteryProductId='" + id + "'");
    }
    return (Lotteryproductutil)baseDAO.loadObject(hql.toString());
  }
  
  public List<Lotteryproductutil> query(String hql)
  {
    return (List<Lotteryproductutil>)baseDAO.query(hql);
  }
  
  public void update(String hql) {}
  
  public List<Lotteryproductutil> loadAll()
  {
    return (List<Lotteryproductutil>)baseDAO
    	.query("from Lotteryproductutil ll order by ll.lotteryProductEndDate desc");
  }
  
  @Override
  public List<Lotteryproductutil> loadAll(final Integer spellbuyProductStatus) {
	List<Lotteryproductutil> list = (List<Lotteryproductutil>)baseDAO
			.query("select lpu from Lotteryproductutil lpu, Spellbuyproduct sbp "+ 
					"where sbp.spellbuyProductId=lpu.lotteryProductId "+ 
					"and sbp.spStatus="+spellbuyProductStatus);
	Collections.sort(list, new java.util.Comparator<Lotteryproductutil>(){
		@Override
		public int compare(Lotteryproductutil a, Lotteryproductutil b) {
			return (b.getLotteryProductEndDate().compareTo(a.getLotteryProductEndDate()));
		}
	});
	return list;
  }
  
  @Override
  public List<Lotteryproductutil> findList(int pageNo, int pageSize)
  {
	StringBuffer hql = new StringBuffer("select ll.* from Lotteryproductutil ll where 1=1 order by ll.lotteryProductEndDate desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ll", Lotteryproductutil.class);
    List list =  (List<Lotteryproductutil>)this.baseDAO.sqlQuery(hql, entityMap, page);
    return list;
  }
}

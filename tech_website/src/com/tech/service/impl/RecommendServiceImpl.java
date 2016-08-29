package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Product;
import com.tech.pojo.Recommend;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.RecommendService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl
  implements RecommendService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void delete(Integer id) {}
  
  public Recommend findById(String id)
  {
    return null;
  }
  
  public List<Recommend> query(String hql)
  {
    return (List<Recommend>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public List getRecommend()
  {
    StringBuffer sql = new StringBuffer("select * from recommend rd,spellbuyproduct st,product pt where rd.spellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId order by rd.date desc limit 1");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("rd", Recommend.class);
    entityMap.put("st", Spellbuyproduct.class);
    entityMap.put("pt", Product.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  public void add(Recommend t)
  {
    baseDao.saveOrUpdate(t);
  }
}

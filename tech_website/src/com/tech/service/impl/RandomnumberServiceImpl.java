package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Randomnumber;
import com.tech.service.RandomnumberService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("RandomnumberService")
public class RandomnumberServiceImpl implements RandomnumberService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void add(Randomnumber t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Randomnumber.class, id);
  }
  
  public Randomnumber findById(String id)
  {
    return null;
  }
  
  public List<Randomnumber> query(String hql)
  {
    return (List<Randomnumber>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public List LotteryDetailByRandomnumber(Integer userId, Integer spellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from randomnumber rr where  rr.userId = '" + userId + "' and rr.productId = '" + spellbuyProductId + "' order by rr.buyDate desc");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public BigDecimal RandomNumberByUserBuyCount(String userId, Integer spellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord where buyer=" + 
    		Integer.parseInt(userId) + " and fkSpellbuyProductId=" + spellbuyProductId);
    return baseDao.getSelectSum(hql);
  }
  
  public Randomnumber findRandomnumberByspellbuyrecordId(Integer spellbuyrecordId)
  {
    StringBuffer hql = new StringBuffer("from Randomnumber randomnumber where randomnumber.spellbuyrecordId='" + spellbuyrecordId + "' ");
    return (Randomnumber)baseDao.loadObject(hql.toString());
  }
  
  public Randomnumber getUserBuyCodeByBuyid(String productId, String spellbuyrecordId)
  {
    StringBuffer hql = new StringBuffer("from Randomnumber randomnumber where randomnumber.productId='" + productId + "' and randomnumber.spellbuyrecordId='" + spellbuyrecordId + "' ");
    return (Randomnumber)baseDao.loadObject(hql.toString());
  }
  
  public Randomnumber getUserBuyCodeByBuyid(String spellbuyrecordId)
  {
    StringBuffer hql = new StringBuffer("from Randomnumber randomnumber where randomnumber.spellbuyrecordId='" + spellbuyrecordId + "' ");
    return (Randomnumber)baseDao.loadObject(hql.toString());
  }

  @Override
  public List<Randomnumber> query(String hql, String alias, boolean lock) {
	return (List<Randomnumber>)baseDao.query(hql, alias, lock);
  }
  
}

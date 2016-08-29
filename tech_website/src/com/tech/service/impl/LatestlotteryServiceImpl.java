package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Orderdetail;
import com.tech.pojo.Orderdetailaddress;
import com.tech.pojo.Producttype;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProducttypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service("latestlotteryService")
public class LatestlotteryServiceImpl implements LatestlotteryService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  @Autowired
  ProducttypeService protypeService;
  
  public Pagination LatestAnnounced(String productType, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly");
    StringBuffer sql = new StringBuffer("select count(*) from latestlottery ly");
    if ((StringUtils.isNotBlank(productType)) && (!productType.equals("index")))
    {
      StringBuilder types = new StringBuilder();
      types.append('(').append('\'').append(productType).append('\'');
      for(final Producttype type: protypeService.listSubs(productType)){
    	  types.append(',').append('\'').append(type.getTypeId()).append('\'');
      }
      types.append(')');
      final String tlist = types.toString();
      hql.append(" where ly.productType in").append(tlist);
      sql.append(" where ly.productType in").append(tlist);
    }
    hql.append(" order by AnnouncedTime desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public void add(Latestlottery t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id) {}
  
  public Latestlottery findById(String id)
  {
    StringBuffer hql = new StringBuffer("from Latestlottery ly where 1=1");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and ly.spellbuyProductId='" + id + "'");
    }
    return (Latestlottery)baseDao.loadObject(hql.toString());
  }
  
  public List<Latestlottery> query(String hql)
  {
    return (List<Latestlottery>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public List getBuyHistoryByDetail(Integer spellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + spellbuyProductId + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public List getByProductHistoryWin(Integer productId)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.productId = '" + productId + "'  order by ly.spellbuyProductId desc");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Pagination getProductByUser(String userId, String startDate, String endDate, String status, String shareStatus, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.userId = '" + userId + "'");
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and ly.announcedTime >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and ly.announcedTime <='" + endDate + "'");
    }
    if (StringUtils.isNotBlank(status)) {
      hql.append(" and ly.status ='" + status + "'");
    }
    if (StringUtils.isNotBlank(shareStatus)) {
      hql.append(" and ly.shareStatus ='" + shareStatus + "'");
    }
    hql.append(" order by ly.announcedTime desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer getProductByUserByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(*) from latestlottery ly where ly.userId = '" + userId + "'");
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and ly.announcedTime >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and ly.announcedTime <='" + endDate + "'");
    }
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public List indexWinningScroll()
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly order by ly.announcedTime desc limit 5");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public List getLotteryDetail(Integer spellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + spellbuyProductId + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Integer getCountByLotteryDetail(String spellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select count(DISTINCT buyer) from spellbuyrecord where fkSpellbuyProductId = '" + spellbuyProductId + "'");
    return Integer.valueOf(baseDao.getAllNum(hql));
  }
  
  public Pagination getLotteryDetailBybuyerList(Integer SpellbuyProductId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from user ur,randomnumber rr where rr.userId=ur.userId and rr.productId= '" + SpellbuyProductId + "' order by rr.buyDate desc ");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("rr", Randomnumber.class);
    entityMap.put("ur", User.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer getLotteryDetailBybuyerListByCount(Integer SpellbuyProductId)
  {
    StringBuffer sql = new StringBuffer("select count(*) from user ur,randomnumber rr where rr.userId=ur.userId and rr.productId = '" + SpellbuyProductId + "'");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination getProductOtherWinUser(String productId, String spellbuyProductId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.productId = '" + productId + "' and ly.spellbuyProductId <> '" + spellbuyProductId + "'");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    return page;
  }
  
  public List getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer SpellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery ly where ly.spellbuyProductId = '" + SpellbuyProductId + "' limit 1");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Latestlottery findLatestlotteryByspellbuyrecordId(Integer spellbuyrecordId)
  {
    StringBuffer hql = new StringBuffer("from Latestlottery latestlottery where latestlottery.spellbuyRecordId='" + spellbuyrecordId + "' ");
    return (Latestlottery)baseDao.loadObject(hql.toString());
  }
  
  public List orderdetailListById(String id)
  {
    StringBuffer hql = new StringBuffer("select * from orderdetail ol where ol.orderDetailId = '" + id + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ol", Orderdetail.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Orderdetailaddress orderdetailaddressFindByOrderdetailId(String id)
  {
    StringBuffer hql = new StringBuffer("from Orderdetailaddress os where os.orderDetailId = '" + id + "'");
    
    return (Orderdetailaddress)baseDao.loadObject(hql.toString());
  }
  
  public void addOrderdetailaddress(Orderdetailaddress orderdetailaddress)
  {
    baseDao.saveOrUpdate(orderdetailaddress);
  }
  
  public Integer getLotteryByCount()
  {
    StringBuffer sql = new StringBuffer("select count(*) from latestlottery");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination adminByLatestAnnounced(int pageNo, int pageSize, String status, String shareStatus, String orderName)
  {
    StringBuffer hql = new StringBuffer("select * from latestlottery where 1=1");
    StringBuffer sql = new StringBuffer("select count(*) from latestlottery where 1=1");
    if (StringUtils.isNotBlank(status)) {
      if (status.equals("2"))
      {
        hql.append(" and status='" + status + "'");
        sql.append(" and status='" + status + "'");
      }
      else if (status.equals("3"))
      {
        hql.append(" and status='" + status + "' or status='" + 4 + "'  or status='" + 10 + "'");
        sql.append(" and status='" + status + "' or status='" + 4 + "'  or status='" + 10 + "'");
      }
    }
    if (StringUtils.isNotBlank(shareStatus)) {
      if (shareStatus.equals("0"))
      {
        hql.append(" and shareStatus='" + shareStatus + "' or shareStatus='" + 1 + "' or shareStatus='" + 2 + "'");
        sql.append(" and shareStatus='" + shareStatus + "' or shareStatus='" + 1 + "' or shareStatus='" + 2 + "'");
      }
      else if (shareStatus.equals("-1"))
      {
        hql.append(" and shareStatus='" + shareStatus + "'");
        sql.append(" and shareStatus='" + shareStatus + "'");
      }
    }
    if("time".equals(orderName)) {
    	hql.append(" order by AnnouncedTime");
    }else if("timeDesc".equals(orderName)) {
    	hql.append(" order by AnnouncedTime desc");
    }else if("user".equals(orderName)) {
    	hql.append(" order by userName");
    }else if("userDesc".equals(orderName)) {
    	hql.append(" order by userName desc");
    }else if("product".equals(orderName)) {
    	hql.append(" order by productTitle");
    }else if("productDesc".equals(orderName)) {
    	hql.append(" order by productTitle desc");
    }else{
    	hql.append(" order by AnnouncedTime desc");
    }
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Pagination getLimitNewestLottery()
  {
    StringBuffer hql = new StringBuffer("select ly.*,st.* from latestlottery ly,spellbuyproduct st where ly.spellbuyProductId = st.spellbuyProductId and st.spellbuyLimit > 0 GROUP by st.spellbuyProductId order by ly.id desc limit 20");
    Pagination page = new Pagination();
    

    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ly", Latestlottery.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    return page;
  }
}

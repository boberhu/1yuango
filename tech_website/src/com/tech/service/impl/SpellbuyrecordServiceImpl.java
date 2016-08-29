package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.BuyHistoryJSON;
import com.tech.pojo.Product;
import com.tech.pojo.Producttype;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.SpellbuyrecordService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service("spellbuyrecordService")
public class SpellbuyrecordServiceImpl implements SpellbuyrecordService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public void add(Spellbuyrecord t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Spellbuyrecord.class, id);
  }
  
  public Spellbuyrecord findById(String id)
  {
    StringBuffer hql = new StringBuffer("from Spellbuyrecord spellbuyrecord where 1=1");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and spellbuyrecord.spellbuyRecordId='" + id + "'");
    }
    return (Spellbuyrecord)baseDao.loadObject(hql.toString());
  }
  
  public List<Spellbuyrecord> query(String hql)
  {
    return (List<Spellbuyrecord>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public Pagination findHotProductList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId and pt.status=1 and st.spStatus = 0  GROUP by pt.productId order by st.spellbuyCount desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Pagination indexNewProductList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId and pt.status=1 and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyStartDate desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Pagination indexHotProductList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId and pt.status=1 and st.spStatus <> 1  GROUP by pt.productId order by st.productPeriod desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Pagination getNowBuyList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId order by sd.spellbuyRecordId desc");
//StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ur", User.class);
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    
    return page;
  }
  
  public Pagination findProductByTypeId(String typeId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 0");
    if ((typeId != null) && (!typeId.equals("")))
    {
      hql.append(" and (1=2");
      hql.append(" or (1=1 and pt.productType= " + typeId + ")");
      StringBuffer _hql = new StringBuffer("from Producttype p where p.ftypeId ='" + typeId + "'");
      List<Producttype> objList = (List<Producttype>)baseDao.query(_hql.toString());
      if ((objList != null) && (objList.size() > 0)) {
        for (Producttype producttype : objList) {
          hql.append(" or (1=1 and pt.productType= '" + producttype.getTypeId() + "')");
        }
      }
      hql.append(")");
    }
    hql.append(" GROUP by pt.productId");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    page.setResultCount(8);
    return page;
  }
  
  public Pagination ProductByTypeIdList(String typeId, String brandId, String orderName, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and pt.status=1 and st.spStatus = 0");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and pt.status=1 and st.spStatus = 0");
    if (StringUtils.isNotBlank(typeId))
    {
      hql.append(" and (1=2");
      sql.append(" and (1=2");
      hql.append(" or (1=1 and pt.productType= '" + typeId + "')");
      sql.append(" or (1=1 and pt.productType= '" + typeId + "')");
      StringBuffer _hql = new StringBuffer("from Producttype p where p.ftypeId ='" + typeId + "'");
      List<Producttype> objList = (List<Producttype>)baseDao.query(_hql.toString());
      if ((objList != null) && (objList.size() > 0)) {
        for (Producttype producttype : objList)
        {
          hql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
          sql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
        }
      }
      hql.append(")");
      sql.append(")");
    }
    if (StringUtils.isNotBlank(brandId))
    {
      hql.append(" and pt.productBrand='" + brandId + "'");
      sql.append(" and pt.productBrand='" + brandId + "'");
    }
    if (orderName.equals("hot")) {
      hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
    }
    if (orderName.equals("date")) {
      hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
    }
    if (orderName.equals("price")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice desc");
    }
    if (orderName.equals("priceAsc")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice asc");
    }
    if (orderName.equals("about"))
    {
    	hql.append(" GROUP by pt.productId order by st.spellbuyCount desc");
      //hql.append(" and (st.spellbuyCount > (pt.productPrice/1.5)) GROUP by pt.productId order by st.spellbuyCount desc");
      //sql.append(" and (st.spellbuyCount > (pt.productPrice/1.5))");
    }
    if (orderName.equals("surplus")) {
      hql.append("  GROUP by pt.productId order by (pt.productPrice -st.spellbuyCount) asc");
    }
    if (orderName.equals("limit"))
    {
      hql.append(" and pt.productLimit > 0");
      sql.append(" and pt.productLimit > 0");
    }
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Pagination searchProduct(String keyword, String orderName, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and pt.status=1 and st.spStatus <> 1 and pt.productName like '%" + keyword + "%'");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and pt.status=1 and st.spStatus <> 1 and pt.productName like '%" + keyword + "%'");
    if (orderName.equals("hot")) {
      hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
    }
    if (orderName.equals("date")) {
      hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
    }
    if (orderName.equals("price")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice desc");
    }
    if (orderName.equals("priceAsc")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice asc");
    }
    if (orderName.equals("about"))
    {
      hql.append(" and (st.spellbuyCount > (pt.productPrice/1.5)) GROUP by pt.productId order by st.spellbuyCount desc");
      sql.append(" and (st.spellbuyCount > (pt.productPrice/1.5))");
    }
    if (orderName.equals("surplus")) {
      hql.append("  GROUP by pt.productId order by (pt.productPrice -st.spellbuyCount) asc");
    }
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Pagination LatestParticipate(String spellbuyProductId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select ur.*, sd.* from user ur, spellbuyrecord sd where sd.buyer=ur.userId and sd.fkSpellbuyProductId = '" + spellbuyProductId + "'  order by sd.buyDate desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ur", User.class);
    entityMap.put("sd", Spellbuyrecord.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer LatestParticipateByCount(String spellbuyProductId)
  {
    StringBuffer sql = new StringBuffer("select count(*) from user ur, spellbuyrecord sd where sd.buyer=ur.userId and sd.fkSpellbuyProductId ='" + spellbuyProductId + "'");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public List getRandomNumberList(String spellbuyProductId, String userId)
  {
    StringBuffer hql = new StringBuffer("SELECT * from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = " + userId);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Pagination getRandomNumberListPage(String spellbuyProductId, String userId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("SELECT * from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = '" + userId + "' order by rr.buyDate desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer getRandomNumberListPageByCount(String spellbuyProductId, String userId)
  {
    StringBuffer sql = new StringBuffer("select count(*) from randomnumber rr where rr.productId = '" + spellbuyProductId + "' and rr.userId = '" + userId + "'");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination buyHistoryByUser(String userId, String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select st.fkProductId as productId, st.spellbuyProductId as spellbuyProductId, pt.productName as productName,pt.productTitle as productTitle,pt.headImage as productImg,st.productPeriod as productPeriod,st.spStatus as buyStatus,sd.buyDate as buyTime,sum(sd.buyPrice) as buyCount,sd.spellbuyRecordId as historyId,st.spellbuyCount as spellbuyCount,st.spellbuyPrice as productPrice from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId  and sd.buyer='" + userId + "'");
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and sd.buyDate >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and sd.buyDate <='" + endDate + "'");
    }
    hql.append(" group by st.spellbuyProductId  order by sd.buyDate desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("buyCount", BuyHistoryJSON.class);
    List list = baseDao.sqlQueryBean(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer buyHistoryByUserByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(*) from (select count(*) from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId and sd.buyer='" + userId + "' ");
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and sd.buyDate >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and sd.buyDate <='" + endDate + "'");
    }
    sql.append(" group by st.spellbuyProductId) as counts");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public List getBuyHistoryByDetail(String productId, String userId)
  {
    StringBuffer hql = new StringBuffer("select st.spellbuyProductId as spellbuyProductId,pt.productId as productId,pt.productName as productName,pt.productTitle as productTitle,pt.headImage as productImg,st.productPeriod as productPeriod,st.spStatus as buyStatus,sd.buyDate as buyTime,sum(sd.buyPrice) as buyCount,sd.spellbuyRecordId as historyId,st.spellbuyCount as spellbuyCount,st.spellbuyPrice as productPrice from spellbuyrecord sd,spellbuyproduct st,product pt,user ur where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.buyer=ur.userId  and sd.buyer='" + userId + "' and st.spellbuyProductId='" + productId + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("buyCount", BuyHistoryJSON.class);
    List list = baseDao.sqlQueryEntityBean(hql, entityMap);
    return list;
  }
  
  public Pagination nowUpProducts(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus <> 1  GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    return page;
  }
  
  public List randomByBuyHistoryByspellbuyProductId(Integer productId, String randomNumber)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,user ur,randomnumber rr where sd.buyer=ur.userId and sd.spellbuyRecordId = rr.spellbuyrecordId and sd.fkSpellbuyProductId=" + productId + " and rr.randomNumber like '%" + randomNumber + "%'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("ur", User.class);
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public List randomByBuyHistoryByspellbuyProductIdV2(Integer productId, String randomNumber)
  {
	//先去除所有记录
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,user ur,randomnumber rr where sd.buyer=ur.userId and sd.spellbuyRecordId = rr.spellbuyrecordId and sd.fkSpellbuyProductId=" + productId );
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("ur", User.class);
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    //去除不符合条件的记录
    List resList = new ArrayList();
    Iterator<Object[]> itr = list.iterator();
    Object[] objs =null;
    Randomnumber rr =null;
    while(itr.hasNext()){
    	objs = itr.next();
    	for(Object obj:objs){
    		if(obj instanceof Randomnumber){
    			rr = (Randomnumber)obj;
    		}
    		if(rr!=null && rr.getRandomNumber()!=null && rr.getRandomNumber().indexOf(randomNumber)!=-1){
    			resList.add(objs);
    		}
    	}
    }
    return resList;
  }
  
  public List WinRandomNumber(Integer productId, Integer randomNumber)
  {
    StringBuffer hql = new StringBuffer("select * from user ur,randomnumber rr where ur.userId = rr.userId and ur.userPwd = '"+ User.getMagic() +"' and  rr.productId=" + productId + " and rr.randomNumber like '%" + randomNumber + "%'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ur", User.class);
    entityMap.put("rr", Randomnumber.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public BigDecimal getAllByCount()
  {
    StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord");
    BigDecimal sum = baseDao.getSelectSum(hql);
    if(sum==null){
    	sum = new BigDecimal(0);
    }
    return sum;
  }
  
  public Pagination getAllBuyRecord(String startDate, String endDate, int pageNo, int pageSize)
  {
	// 1. 排序查找！
	StringBuffer hql = new StringBuffer("select sd.* from spellbuyrecord sd where 1=1");
	// 1.1 先单表排序查找id列表（避免多表关联引起的长时间排序！！）
	if (StringUtils.isNotBlank(startDate))
	{
		 hql.append(" and sd.buyDate >='" + startDate + "'");
	}
	if (StringUtils.isNotBlank(endDate))
	{
		hql.append(" and sd.buyDate <='" + endDate + "'");
	}
	hql.append(" order by sd.buyDate desc");
	Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap(4);
    entityMap.put("sd", Spellbuyrecord.class);
    List<Spellbuyrecord> records = baseDao.sqlQuery(hql, entityMap, page);
    if(records.size() == 0){
    	return page;
    }
    // 1.2 按id查询关联实体
    hql.setLength(0);
    hql.append("select pt.*,sd.*,st.* from spellbuyrecord sd,spellbuyproduct st,product pt "
    		+ "where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId "
    		+ "and sd.spellbuyrecordId in")
    .append('(');
    for(int i = 0, size = records.size(); i < size; ++i){
    	final Spellbuyrecord record = records.get(i);
    	hql.append(i==0?"":',').append(record.getSpellbuyRecordId());
    }
    records = null;
    hql.append(')');
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("st", Spellbuyproduct.class);
    entityMap.put("pt", Product.class);
    List<Object[]> list = baseDao.sqlQuery(hql, entityMap, page);
    Collections.sort(list, new java.util.Comparator<Object[]>(){
		@Override
		public int compare(Object[] a, Object[] b) {
			final Spellbuyrecord ra = (Spellbuyrecord)(a[1]);
			final Spellbuyrecord rb = (Spellbuyrecord)(b[1]);
			return (rb.getBuyDate().compareTo(ra.getBuyDate()));
		}
    });
    page.setList(list);
    // 2. 查询total
    hql.setLength(0);
    hql.append("select count(*) from spellbuyrecord sd where 1=1");
    if (StringUtils.isNotBlank(startDate))
    {
      hql.append(" and sd.buyDate >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate))
    {
      hql.append(" and sd.buyDate <='" + endDate + "'");
    }
    final int resultCount = baseDao.getAllNum(hql);
    hql = null;
    page.setResultCount(resultCount);
    return page;
  }
  
  public Pagination getlotteryDetail(String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where 1=1 ");
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and sd.buyDate >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and sd.buyDate <'" + endDate + "'");
    }
    hql.append(" order by sd.buyDate desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap(6);
    entityMap.put("sd", Spellbuyrecord.class);
    List<Spellbuyrecord> list = baseDao.sqlQuery(hql, entityMap, page);
    if(list.size() == 0){
    	page.setList(list);
    	return page;
    }
    hql.setLength(0);
    hql.append("select pt.*,sd.*,ur.*,st.*from spellbuyrecord sd,spellbuyproduct st,product pt,user ur "
    		+ "where sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId "
    		+ "and sd.buyer=ur.userId and sd.spellbuyrecordId in")
    .append('(');
    for(int i = 0, size = list.size(); i < size; ++i){
    	final Spellbuyrecord record = list.get(i);
    	hql.append(i==0?"":',').append(record.getSpellbuyRecordId());
    }
    list = null;
    hql.append(')');
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("st", Spellbuyproduct.class);
    entityMap.put("pt", Product.class);
    entityMap.put("ur", User.class);
    List<Object[]> lists = baseDao.sqlQuery(hql, entityMap, page);
    Collections.sort(lists, new java.util.Comparator<Object[]>(){
		@Override
		public int compare(Object[] a, Object[] b) {
			Spellbuyrecord ra=null, rb=null;
			for(Object obj:a){
	    		if(obj instanceof Spellbuyrecord){
	    			ra = (Spellbuyrecord)obj;
	    		}
	    	}
			for(Object obj:b){
	    		if(obj instanceof Spellbuyrecord){
	    			rb = (Spellbuyrecord)obj;
	    		}
	    	}
			return (rb.getBuyDate().compareTo(ra.getBuyDate()));
		}
    });
    page.setList(lists);
    return page;
  }
  
  public List getSpellbuyRecordByLast100(String startDate, String endDate, int size)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where 1=1");
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and sd.buyDate >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and sd.buyDate <'" + endDate + "'");
    }
    hql.append(" order by sd.buyDate desc limit " + size);
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("sd", Spellbuyrecord.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public List getEndBuyDateByProduct(Integer fkSpellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where fkSpellbuyProductId=" + fkSpellbuyProductId + " order by buyDate desc limit 1");
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("sd", Spellbuyrecord.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public BigDecimal findSumByBuyPriceByUserId(String userId)
  {
    StringBuffer hql = new StringBuffer("select sum(buyPrice) from spellbuyrecord where buyer = '" + userId + "'");
    return baseDao.getSelectSum(hql);
  }
  
  public List getUserByHistory(String userId, String fkSpellbuyProductId)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd where sd.buyer = '" + userId + "' and sd.fkSpellbuyProductId = '" + fkSpellbuyProductId + "' order by sd.buyDate desc limit 8");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("sd", Spellbuyrecord.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Pagination getNowBuyAjaxList(int pageNo, int pageSize, int spellbuyRecordId)
  {
    //StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.spellbuyRecordId > " + spellbuyRecordId + " order by sd.buyDate desc");
	  StringBuffer hql = new StringBuffer("select ur.*,sd.*,pt.*,st.* from User ur, Spellbuyrecord sd,Product pt,Spellbuyproduct st where 1=1 and sd.buyer=ur.userId and sd.fkSpellbuyProductId=st.spellbuyProductId and st.fkProductId=pt.productId and sd.spellbuyRecordId > " + spellbuyRecordId + " ");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ur", User.class);
    entityMap.put("sd", Spellbuyrecord.class);
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  /*
   * 限购专区
   * yzp 易购OS taobaodianpuq   qq30221336
  */
  public Pagination ProductBylimitBuyList(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 0 and pt.status=1 and pt.productLimit > 0");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }

  public List getRandomByProductAndUser(Integer productId, String userId)
  {
	  StringBuffer hql = new StringBuffer("select * from spellbuyrecord sd,randomnumber rr where sd.spellbuyRecordId = rr.spellbuyrecordId and sd.fkSpellbuyProductId=" + productId + " and rr.userId= " + userId);
	    Map<String, Class> entityMap = new HashMap();
	    entityMap.put("sd", Spellbuyrecord.class);
	    entityMap.put("rr", Randomnumber.class);
	    List list = baseDao.sqlQueryEntity(hql, entityMap);
	    return list;
  }
  
}

package com.tech.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Consumetable;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.User;
import com.tech.service.ConsumetableService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.UserService;
import com.tech.util.ApplicationListenerImpl;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service("consumetableService")
public class ConsumetableServiceImpl implements ConsumetableService
{
  private static final Logger LOG = LoggerFactory.getLogger(ConsumetableServiceImpl.class);	

  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  @Autowired
  SpellbuyproductService buyProductService;
  @Autowired
  UserService userService;
  
  public void add(Consumetable t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Consumetable.class, id);
  }
  
  public Consumetable findById(String id)
  {
    StringBuffer hql = new StringBuffer("from Consumetable consumetable where 1=1");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and consumetable.id='" + id + "'");
    }
    return (Consumetable)baseDao.loadObject(hql.toString());
  }
  
  public Consumetable findByOutTradeNo(String id)
  {
	return findByOutTradeNo(id, false);
  }
  
  @Override
  public Consumetable findByOutTradeNo(String id, final boolean lock)
  {
	if(id.indexOf('\'') != -1){
		throw new IllegalArgumentException("out-trade-no: "+id);
	}
    final StringBuffer hql = 
    	new StringBuffer("from Consumetable consumetable where consumetable.outTradeNo=");
    hql.append('\'').append(id).append('\'');
    return (Consumetable)baseDao.loadObject(hql.toString(), "consumetable", lock);
  }
  
  public List<Consumetable> query(String hql)
  {
    return (List<Consumetable>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public Pagination userByConsumetableByDelta(String userId, String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from consumetable ce where 1=1");
    if (StringUtils.isNotBlank(userId)) {
      hql.append(" and ce.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and ce.date >= '" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and ce.date <='" + endDate + "'");
    }
    hql.append(" and ce.transactionId is not null order by ce.date desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("ce", Consumetable.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }

  @Override
  public Double totalRecharge(String startDate, String endDate) {
	  StringBuffer hql = new StringBuffer("select sum(ce.money) from consumetable ce where 1=1");
	    if (StringUtils.isNotBlank(startDate)) {
	      hql.append(" and ce.date >= '" + startDate + "'");
	    }
	    if (StringUtils.isNotBlank(endDate)) {
	      hql.append(" and ce.date <='" + endDate + "'");
	    }
	    hql.append(" and ce.transactionId is not null ");

	    Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (Double)result;
	    }else{
	    	return new Double(0);
	    }
  }

  @Override
  public Double totalBuy(String startDate, String endDate) {
	  StringBuffer hql = new StringBuffer("select sum(cl.buyMoney) from consumetable ce,consumerdetail cl where 1=1 ");
	    if (StringUtils.isNotBlank(startDate)) {
	      hql.append(" and ce.date >= '" + startDate + "'");
	    }
	    if (StringUtils.isNotBlank(endDate)) {
	      hql.append(" and ce.date <='" + endDate + "'");
	    }
	    hql.append("   and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null)) and ce.outTradeNo =cl.consumetableId  ");
	   
	    Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (Double)result;
	    }else{
	    	return new Double(0);
	    }
	    
  }

  @Override
  public Double totalPay(String startDate, String endDate) {
	  StringBuffer hql = new StringBuffer("select sum(ce.money) from consumetable ce,consumerdetail cl where 1=1 ");
	    if (StringUtils.isNotBlank(startDate)) {
	      hql.append(" and ce.date >= '" + startDate + "'");
	    }
	    if (StringUtils.isNotBlank(endDate)) {
	      hql.append(" and ce.date <='" + endDate + "'");
	    }
	    hql.append("   and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null)) and ce.outTradeNo =cl.consumetableId  ");

	    Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (Double)result;
	    }else{
	    	return new Double(0);
	    }
  }
  
  public Integer userByConsumetableByDeltaByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(*) from consumetable ce where 1=1 ");
    if (StringUtils.isNotBlank(userId)) {
      sql.append(" and ce.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and ce.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and ce.date <='" + endDate + "'");
    }
    sql.append(" and ce.transactionId is not null");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination userByConsumetable(String userId, String startDate, String endDate, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select ce.*,cl.* from consumetable ce,consumerdetail cl where 1=1 ");
    if (StringUtils.isNotBlank(userId)) {
      hql.append(" and ce.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      hql.append(" and ce.date >= '" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      hql.append(" and ce.date <='" + endDate + "'");
    }
    hql.append("   and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null)) and ce.outTradeNo =cl.consumetableId group by ce.outTradeNo order by ce.date desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("ce", Consumetable.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer userByConsumetableByCount(String userId, String startDate, String endDate)
  {
    StringBuffer sql = new StringBuffer("select count(DISTINCT(ce.outTradeNo))  from  consumetable ce,consumerdetail cl where 1=1");
    if (StringUtils.isNotBlank(userId)) {
      sql.append(" and ce.userId = '" + userId + "'");
    }
    if (StringUtils.isNotBlank(startDate)) {
      sql.append(" and ce.date >='" + startDate + "'");
    }
    if (StringUtils.isNotBlank(endDate)) {
      sql.append(" and ce.date <='" + endDate + "'");
    }
    sql.append("  and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null))   and ce.outTradeNo =cl.consumetableId");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Pagination orderList(String userKey, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select ce.*  from consumetable ce where 1=1  and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null))");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(ce.id))  from consumetable ce  where 1=1  and (ce.transactionId is not null or (ce.interfaceType = '余额支付' and ce.transactionId is null) or (ce.interfaceType = '积分抵扣' and ce.transactionId is null) or (ce.interfaceType = '积分+余额' and ce.transactionId is null))");
    if (StringUtils.isNotBlank(userKey))
    {
      hql.append(" and ce.userId='" + userKey.trim() + "'");
      sql.append(" and ce.userId='" + userKey.trim() + "'");
    }
    hql.append(" group by ce.id  order by ce.date desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("ce", Consumetable.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }

  @Override
  public int restoreTimeoutOrders(final long timeout, final int nr) {
	final long ts = System.currentTimeMillis();
	Date end = new Date(ts-timeout);
	final String ends = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
	end = null;
	final StringBuilder sbuf = new StringBuilder();
	sbuf.append("from Consumetable bean where bean.date<")
	.append('\'').append(ends).append('\'')
	.append(" and bean.payStatus=").append(Consumetable.PAY_STAT_NPAID)
	.append(" order by bean.date desc");
	final List<Object> orders = baseDao.query(sbuf.toString(), 1, nr, "bean", true);
	int k = 0;
	for(final Object o: orders){
		final Consumetable cons = (Consumetable)o;
		cons.setPayStatus(Consumetable.PAY_STAT_TMO);
		final String withold = cons.getWithold();
		if(StringUtil.isBlank(withold)){
			continue;
		}
		final JSONObject json = new JSONObject(withold);
		final JSONArray pros  = json.names();
		if(pros == null){
			// withold: {}!
			continue;
		}
		int valid = 0;
		for(int i = pros.length()-1; i >= 0; --i){
			final String pro  = pros.getString(i);
			// 跳过微信端添加的版本键！
			// pzp - 2015-09-25
			if("v".equals(pro)){
				continue;
			}
			final int num = json.getInt(pro);
			final Spellbuyproduct sbp = buyProductService
					.getByProductId(Integer.parseInt(pro), true);
			if(sbp == null){
				// product-or-spellbuy-product deleted!
				continue;
			}
			sbp.setSpellbuyCount(sbp.getSpellbuyCount()-num);
			++valid;
		}
		if(valid > 0){
			++k;
		}
	}
	LOG.info("Order withold restore, items: {}, time: {}ms", 
		k, (System.currentTimeMillis()-ts));
	return k;
  }
  
  @Override
  public int countNotPaid(final Integer productId){
	  return (baseDao.countQuery("select count(*) from Consumetable bean "
	  		+ "where bean.payStatus=0 and bean.withold like '%'||'\""+productId+"\"'||'%'"));
  }

  @Override
  public Consumetable paid(final String tradeno, final String xid) {
	  final Consumetable cons = findByOutTradeNo(tradeno, true);
	  if(cons.isPaid() || cons.isTimeoPaid()){
		  LOG.warn("已支付过了");
		  return cons;
	  }
	  cons.setTransactionId(xid);
	  final String userId = cons.getUserId()+"";
	  final User user = userService.findById(userId, true);
	  if("".equals(cons.getWithold())){
		  // 充-值！
		  //final double ba = user.getBalance();
		  //user.setBalance(cons.getMoney() + ba);
		  double recMoney = cons.getMoney();
		  if (cons.getMoney() >= ApplicationListenerImpl.sysConfigureJson.getRecMoney()) {
			  int count = (int)(cons.getMoney()/ApplicationListenerImpl.sysConfigureJson.getRecMoney());
			  recMoney = recMoney + (count * ApplicationListenerImpl.sysConfigureJson.getRecBalance());
		  }
		  double ba = user.getBalance()+recMoney;
		  user.setBalance(ba);
		  // 更新订单 - 支付状态
		  cons.setPayStatus(Consumetable.PAY_STAT_PAID);
		  return cons;
	  }
	  // 拍-购！
	  final boolean notPaid = cons.isNotPaid();
	  if(notPaid){
		  final Integer userPayType= Consumetable.UPAY_TYPE_CYBBANK;
		  final String integral    = cons.getIntegral()+"";
		  final String useBalance  = cons.getBalance()+"";
		  final Integer bankMoney  = cons.getBankMoney();
		  // 伪装购物车 - 调用统一接口处理
		  JSONObject withold = new JSONObject(cons.getWithold());
		  JSONArray products = withold.names();
		  final int prosize = products.length();
		  List<ProductCart> carts  = new ArrayList<ProductCart>(prosize);
		  for(int i = 0, moneyCount = 0; i < prosize; ++i){
			  final String pids = products.getString(i);
			  final int pid = Integer.parseInt(pids);
			  final int num = withold.getInt(pids);
			  moneyCount += num;
			  final Spellbuyproduct buyProduct = buyProductService.getByProductId(pid);
			  final ProductCart cart = new ProductCart();
			  cart.init(buyProduct.getProduct(), buyProduct, num, moneyCount);
			  carts.add(cart);
		  }
		  withold  = null;
		  products = null;
		  // 支付成功 - 后续处理
		  userService.payOrPaid(userId, carts, new ArrayList<ProductJSON>(prosize),
				  userPayType, integral, bankMoney, useBalance, tradeno);
		  carts = null; 
	  }
	  // 更新订单 - 支付状态
	  if(notPaid){
		  cons.setPayStatus(Consumetable.PAY_STAT_PAID);
	  }else{
		  cons.setPayStatus(Consumetable.PAY_STAT_TMO_PAID);
	  }
	  // ok
	  return cons;
  }
  
}

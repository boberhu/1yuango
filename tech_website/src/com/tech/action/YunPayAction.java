package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
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

import com.tech.alipay.config.AlipayConfig;
import com.tech.alipay.util.AlipayNotify;
import com.tech.alipay.util.AlipaySubmit;
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
import com.tech.pojo.SysConfigure;
import com.tech.pojo.User;
import com.tech.service.CommissionpointsService;
import com.tech.service.CommissionqueryService;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.LatestlotteryService;
import com.tech.service.LotteryproductutilService;
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
import com.tech.util.MD5Util;
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("YunPayAction")
public class YunPayAction extends BaseAction {

	private static final long serialVersionUID = -1325199950734231356L;
	private static final Logger LOG = LoggerFactory.getLogger(YunPayAction.class);
	private static String secret = ConfigUtil.getValue("secret", "1yyg1234567890");
	
	private static final String ENCODING  = "UTF-8";
	private static final String YUNPAY_GATEWAY = "http://pay.yunpay.net.cn/i2eorder/yunpay/";
	
	RandomUtil randomUtil = new RandomUtil();
	@Autowired
	ConsumetableService consumetableService;
	@Autowired
	ConsumerdetailService consumerdetailService;
	@Autowired
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	private RandomnumberService randomnumberService;
	@Autowired
	private UserService userService;
	@Autowired
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private LatestlotteryService latestlotteryService;
	@Autowired
	private ProductService productService;
	@Autowired
	CommissionqueryService commissionqueryService;
	@Autowired
	CommissionpointsService commissionpointsService;
	@Autowired
	LotteryproductutilService lotteryproductutilService;
	private User user;
	private String userId;
	private Consumetable consumetable;
	private ProductCart productCart;
	private Product product;
	private Spellbuyproduct spellbuyproduct;
	private Spellbuyrecord spellbuyrecord;
	private Randomnumber randomnumber;
	private Latestlottery latestlottery;
	private Consumerdetail consumerdetail;
	private List<ProductCart> productCartList;
	private List<ProductJSON> successCartList;
	private ProductJSON productJSON;
	private String paymentStatus;
	private Commissionquery commissionquery;
	private Commissionpoints commissionpoints;
	private Lotteryproductutil lotteryproductutil;
	private String key;
	private String tradeNo;
	private String money;
	private String title;
	private String memo;
	private String alipay_account;
	private String tenpay_account;
	private String gateway;
	private String sign;
	private String token;
	private String payName;
	private Integer moneyCount;
	private String integral;
	private String currTime = TenpayUtil.getCurrTime();
	private String out_trade_no;
	Random random = new Random();
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public String goPay() {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("moneyCount", moneyCount.toString());
		map.put("out_trade_no", out_trade_no);
		String mysign="";
		try {
			mysign = getSign(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(!StringUtil.equals(token, mysign)){
			Struts2Utils.render("text/html",
					"<script>alert(\"验证失败，请联系客服！\");window.location.href=\"/index.html\";</script>",
					StringUtil.ENCA_UTF8);
			return null;
		}
		
		final SysConfigure conf = ApplicationListenerImpl.sysConfigureJson;
		String notify_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yunpay/notifyUrl.action";
		String return_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yunpay/returnUrl.action";
		String seller_email = ApplicationListenerImpl.sysConfigureJson.getYunPayMail();
		key = ApplicationListenerImpl.sysConfigureJson.getYunPayKey();
		String subject = conf.getSaitName();
		String total_fee = moneyCount.toString();
		String body = conf.getSaitName();
		String anti_phishing_key = currTime;
		String exter_invoke_ip = Struts2Utils.getRemoteIp();
		Map<String, String> sParaTemp = new LinkedHashMap<String, String>();
		List<String> keys = new ArrayList<String>();
		sParaTemp.put("partner", ApplicationListenerImpl.sysConfigureJson.getYunPayPartner());
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", (total_fee) + "");
		sParaTemp.put("body", body);
		sParaTemp.put("nourl", notify_url);
		sParaTemp.put("reurl", return_url);
		//sParaTemp.put("orurl", "");
		//sParaTemp.put("orimg", "");
		sParaTemp.put("orurl", ApplicationListenerImpl.sysConfigureJson.getWwwUrl());
		sParaTemp.put("orimg", ApplicationListenerImpl.sysConfigureJson.getImgUrl()+ApplicationListenerImpl.sysConfigureJson.getSaitLogo());
		//sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		final String sign = getSign(sParaTemp, key);
		sParaTemp.put("sign", sign);
		for(String s : sParaTemp.keySet()){
			keys.add(s);
		}
		String sHtmlText = buildRequest(sParaTemp, keys, "get", "确认");
		LOG.debug(sHtmlText);
		Struts2Utils.render("text/html", sHtmlText, StringUtil.ENCA_UTF8);
		// Struts2Utils.render("text/html", sHtmlText);
		return null;
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

	private final String getSign(final Map<String, String> params, final String key){
		StringBuilder sbuf = new StringBuilder();
		for (final Iterator<String> i = params.keySet().iterator(); i.hasNext();) {
			final String name = i.next();
			sbuf.append(params.get(name));
		}
		sbuf.append("i2eapi").append(key);
		System.out.println(sbuf.toString());
		return (MD5Util.encode(sbuf.toString(), ENCODING).toLowerCase());
	}
	
	public static String buildRequest(Map<String, String> sPara, List<String> keys, String strMethod, String strButtonName)
	  {
	    //List<String> keys = new ArrayList<String>(sPara.keySet());
	    
	    StringBuffer sbHtml = new StringBuffer();
	    
	    sbHtml.append("<form id=\"yunsubmit\" name=\"yunsubmit\" action=\""+YUNPAY_GATEWAY + "\" accept-charset='utf-8' method=\"" + strMethod + 
	      "\">");
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String name = (String)keys.get(i);
	      String value = (String)sPara.get(name);
	      
	      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
	    }
	    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
	    sbHtml.append("<script>document.forms['yunsubmit'].submit();</script>");
	    
	    return sbHtml.toString();
	  }
	
	public String returnUrl() throws UnsupportedEncodingException {
		if (afterPaying()) {
			Struts2Utils.render("text/html",
					"<script>window.location.href=\"/mycart/shopok.html?id="
							+ out_trade_no + "\";</script>",
					StringUtil.ENCA_UTF8);
		}else{
			Struts2Utils
			.render("text/html",
					"<script>alert(\"验证失败，请联系客服！\");window.location.href=\"/index.html\";</script>",
					StringUtil.ENCA_UTF8);
		}
		return null;
	}

	public String notifyUrl() throws Exception {
		if (afterPaying()) {
			Struts2Utils.render("text/html", "success", StringUtil.ENCA_UTF8);
		}else{
			Struts2Utils.render("text/html", "fail", StringUtil.ENCA_UTF8);
		}
		return null;
	}

	private final boolean afterPaying() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		/*Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			params.put(name, values[0]);
		}*/
		//商户订单号
		out_trade_no = request.getParameter("i2");
		//云支付交易号
		String trade_no = request.getParameter("i4");
		//价格
		String yunprice=request.getParameter("i1");
		String sign=request.getParameter("i3");
		
		String partner = ApplicationListenerImpl.sysConfigureJson.getYunPayPartner();
		String key = ApplicationListenerImpl.sysConfigureJson.getYunPayKey();
		String prestr = yunprice+out_trade_no+partner+key;
		String mysgin = MD5Util.encode(prestr, ENCODING).toLowerCase();
		if(mysgin.equals(sign)){
			final Consumetable cons = consumetableService.paid(out_trade_no, trade_no);
			return true;
		}else{
			return false;
		}
	}


	public Integer getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(Integer moneyCount) {
		this.moneyCount = moneyCount;
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

	public Consumetable getConsumetable() {
		return consumetable;
	}

	public void setConsumetable(Consumetable consumetable) {
		this.consumetable = consumetable;
	}

	public ProductCart getProductCart() {
		return productCart;
	}

	public void setProductCart(ProductCart productCart) {
		this.productCart = productCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Spellbuyproduct getSpellbuyproduct() {
		return spellbuyproduct;
	}

	public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
		this.spellbuyproduct = spellbuyproduct;
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

	public Consumerdetail getConsumerdetail() {
		return consumerdetail;
	}

	public void setConsumerdetail(Consumerdetail consumerdetail) {
		this.consumerdetail = consumerdetail;
	}

	public List<ProductCart> getProductCartList() {
		return productCartList;
	}

	public void setProductCartList(List<ProductCart> productCartList) {
		this.productCartList = productCartList;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Commissionquery getCommissionquery() {
		return commissionquery;
	}

	public void setCommissionquery(Commissionquery commissionquery) {
		this.commissionquery = commissionquery;
	}

	public Commissionpoints getCommissionpoints() {
		return commissionpoints;
	}

	public void setCommissionpoints(Commissionpoints commissionpoints) {
		this.commissionpoints = commissionpoints;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public Lotteryproductutil getLotteryproductutil() {
		return lotteryproductutil;
	}

	public void setLotteryproductutil(Lotteryproductutil lotteryproductutil) {
		this.lotteryproductutil = lotteryproductutil;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAlipay_account() {
		return alipay_account;
	}

	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
	}

	public String getTenpay_account() {
		return tenpay_account;
	}

	public void setTenpay_account(String tenpay_account) {
		this.tenpay_account = tenpay_account;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

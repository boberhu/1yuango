package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.iapppay.Order;
import com.tech.iapppay.config.IAppPaySDKConfig;
import com.tech.iapppay.sign.SignHelper;
import com.tech.iapppay.util.HttpUtils;
import com.tech.iapppay.util.SignUtils;
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
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.ConfigUtil;
import com.tech.util.MD5Util;
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("IapppayAction")
public class IapppayAction extends BaseAction {

	private static final long serialVersionUID = 3474052694421219510L;
	private static final Logger LOG = LoggerFactory.getLogger(IapppayAction.class);
	private static String secret = ConfigUtil.getValue("secret", "1yyg1234567890");
	
	private static final String ENCODING  = "UTF-8";
	private static final String IAPPPAY_ORDER = "http://ipay.iapppay.com:9999/payapi/order";
	private static final String IAPPPAY_GATEWAY = "https://web.iapppay.com/pc/exbegpay";
	
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
	private String tradeNo;
	private String money;
	private String title;
	private String memo;
	private String alipay_account;
	private String tenpay_account;
	private String gateway;
	private String token;
	private String payName;
	private Integer moneyCount;
	private String integral;
	private String out_trade_no;
	Random random = new Random();
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public String goPay() throws Exception {
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
		String notify_url = conf.getWwwUrl() + "/iapppay/notifyUrl.action";
		String return_url = conf.getWwwUrl() + "/iapppay/returnUrl.action";
		String subject = conf.getSaitName();
		float total_fee = moneyCount;
		//float total_fee = 0.01f;
		String exter_invoke_ip = Struts2Utils.getRemoteIp();
		
		String appid = conf.getIapppayAppId();
		int waresid = 1;
		String reqData = Order.ReqData(appid, waresid, out_trade_no, subject, total_fee, userId, "", notify_url);
		System.out.println("reqData="+reqData);
		LOG.debug(reqData);
		String respData = HttpUtils.sentPost(IAPPPAY_ORDER, reqData,"UTF-8"); // 请求验证服务端
		System.out.println("respData="+respData);
		Map<String, String> reslutMap = SignUtils.getParmters(respData);
		String transdata = reslutMap.get("transdata");
		JSONObject jsonTrans= new JSONObject(transdata);
		String transid = jsonTrans.getString("transid");
		reqData = buildReqData(transid,return_url,ApplicationListenerImpl.sysConfigureJson.getWwwUrl());
		String testreqData = buildReqData(transid,return_url,"http://www.egouos.com");//易购OS
		System.out.println(testreqData);
		Struts2Utils.render("text/html", "<script>location.href='"+IAPPPAY_GATEWAY+"?"+reqData+"';</script>", StringUtil.ENCA_UTF8);
		return null;
	}
	
	/**
	 * 
	 * @param transid  向爱贝服务端下单获取的交易流水号
	 * @param redirecturl  支付成功后支付回调URL地址
	 * @param cpurl  返回商户URL地址
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String buildReqData(String transid,String redirecturl,String cpurl) throws UnsupportedEncodingException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("transid", transid);
		jsonObject.put("redirecturl", redirecturl);
		jsonObject.put("cpurl", cpurl);
		String content = jsonObject.toString();// 组装成 json格式数据
		// 调用签名函数      重点注意： 请一定要阅读  sdk 包中的爱贝AndroidSDK3.4.4\03-接入必看-服务端接口说明及范例\爱贝服务端接入指南及示例0311\IApppayCpSyncForJava \接入必看.txt 
		String sign = SignHelper.sign(content, IAppPaySDKConfig.APPV_KEY);
		String data = "transdata=" + UrlEncode(content,ENCODING) + "&sign=" + UrlEncode(sign,ENCODING)+ "&signtype=RSA";// 组装请求参数
		System.out.println("data:"+data);
		return data;
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
	
	public String UrlEncode(String src, String charset)
			throws UnsupportedEncodingException
	{
		return URLEncoder.encode(src, charset).replace("+", "%20");
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
			Struts2Utils.render("text/html", "SUCCESS", StringUtil.ENCA_UTF8);
		}else{
			Struts2Utils.render("text/html", "FAIL", StringUtil.ENCA_UTF8);
		}
		return null;
	}

	private final boolean afterPaying() throws UnsupportedEncodingException {
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			params.put(name, values[0]);
		}
		String transdata = params.get("transdata");
		JSONObject json= new JSONObject(transdata);
		String sign=params.get("sign");
		//商户订单号
		out_trade_no = json.getString("cporderid");
		//爱贝支付交易号
		String trade_no = json.getString("transid");

		/*
		 * 调用验签接口
		 * 主要 目的 确定 收到的数据是我们 发的数据，是没有被非法改动的
		 */
		if (SignHelper.verify(transdata, sign, IAppPaySDKConfig.PLATP_KEY)) {
			final Consumetable cons = consumetableService.paid(out_trade_no, trade_no);
			return true;
		} else {
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
}

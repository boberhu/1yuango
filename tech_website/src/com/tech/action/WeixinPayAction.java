package com.tech.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.pojo.Commissionpoints;
import com.tech.pojo.Commissionquery;
import com.tech.pojo.Consumerdetail;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.SysConfigure;
import com.tech.pojo.User;
import com.tech.proto.http.Proxy;
import com.tech.service.CommissionpointsService;
import com.tech.service.CommissionqueryService;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.tenpay.util.TenpayUtil;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.ConfigUtil;
import com.tech.util.MD5Util;
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.TwoDimensionCode;

@Component("WeixinPayAction")
public class WeixinPayAction extends BaseAction
{
  private static final long serialVersionUID = -5950877186944646693L;
  private static final Logger LOG = LoggerFactory.getLogger(WeixinPayAction.class);
  private static String secret = ConfigUtil.getValue("secret", "1yyg1234567890");
  private static final String UNIFO_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
  private static final String ENCODING  = "UTF-8";
  
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
  Proxy httproxy;
  
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
  private String currTime = TenpayUtil.getCurrTime();
  private String out_trade_no;
  private Integer moneyCount;
  private String productBody = "";
  private String productName;
  private String bank_type;
  private String hidUseBalance;
  private String integral;
  private String requestUrl;
  private String orderError;
  private String token;

  Random random = new Random();
  Calendar calendar = Calendar.getInstance();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String goPay() throws Exception
  {
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
	
    try{
    	if(getOrderError() == null){
        	// OK
        	StringBuilder sbuf = new StringBuilder();
        	sbuf.setLength(0);
        	sbuf.append(ServletActionContext.getServletContext().getRealPath("/uploadImages"))
        	.append('/').append(getOut_trade_no()).append(".png");
            productBody = sbuf.toString();
        	// 统一下单 - 返回code_url
        	final String curl = unifiedorder(sbuf);
        	sbuf = null;
        	// 二维码  - 生成 - 返回
            final  TwoDimensionCode qcode = new TwoDimensionCode();
            qcode.encoderQRCode(curl, productBody, "png", 10);
        	
        }
    }catch(final RuntimeException e){
		LOG.error("{}", e.getMessage());
		throw e;
	}
    return "weixinpay";
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
  
  private String unifiedorder(final StringBuilder sbuf) throws Exception{
	  // check - otno!
	  if(StringUtil.isBlank(getOut_trade_no())){
		  throw new IllegalArgumentException("out_trade_no null");
	  }
	  final SysConfigure conf = ApplicationListenerImpl.sysConfigureJson;
	  // params
	  final SortedMap<String, String> params = new TreeMap<String, String>();
	  params.put("appid", conf.getWeixinAppId());
	  params.put("mch_id", conf.getWeixinPayPartner());
	  params.put("nonce_str", getNonceStr(sbuf));
	  params.put("body", conf.getSaitName());
	  params.put("out_trade_no", getOut_trade_no());
	  params.put("total_fee", (getMoneyCount()*100)+"");
	  params.put("spbill_create_ip", request.getLocalAddr());
	  params.put("notify_url", getNotifyAction(sbuf, conf));
	  params.put("trade_type", "NATIVE");
	  params.put("product_id", getOut_trade_no());
	  // - sign
	  final String key  = conf.getWeixinPayKey();
	  final String sign = getSign(sbuf, params, key);
	  LOG.debug("sign: {}", sign);
	  // xml - build
	  sbuf.setLength(0);
	  sbuf.append("<xml>").append('\r').append('\n');
	  for(final Iterator<String> i = params.keySet().iterator(); i.hasNext();){
		  final String name = i.next();
		  sbuf.append(' ').append(' ')
		  .append('<').append(name).append('>')
		  .append(params.get(name)).append('<').append('/').append(name).append('>')
		  .append('\r').append('\n');
	  }
	  sbuf.append(' ').append(' ')
	  .append("<sign>").append(sign).append("</sign>")
	  .append('\r').append('\n');
	  sbuf.append("</xml>");
	  final String xml = sbuf.toString();
	  LOG.debug("unifiedorder request: {}", xml);
	  // request
	  final String rxml = httproxy.post(UNIFO_URL, xml, ENCODING);
	  LOG.debug("unifiedorder return: {}", rxml);
	  // result - ha
	  final Document doc = DocumentHelper.parseText(rxml);
	  final Element root = doc.getRootElement();
	  // check - return
	  final String ret   = root.element("return_code").getText();
	  final String msg   = root.element("return_msg").getText();
	  if("SUCCESS".equals(ret) == false){
		  LOG.error("return_msg: {}", msg);
		  throw new IllegalArgumentException("return code: fail");
	  }
	  // check - result
	  final String res = root.element("result_code").getText();
	  if("SUCCESS".equals(res) == false){
		  LOG.error("result_code: {}", msg);
		  throw new IllegalArgumentException("result code: fail");
	  }
	  // - sign verify
	  final String rsign = root.element("sign").getText();
	  if(verifySign(sbuf, params, root, rsign, key) == false){
		  LOG.error("return sign error");
		  throw new IllegalArgumentException("illegal sign");
	  }
	  return (root.element("code_url").getText());
  }
  
  private final String getSign(final StringBuilder sbuf, final SortedMap<String, String> params,
		  final String key){
	  sbuf.setLength(0);
	  int k = 0;
	  for(final Iterator<String> i = params.keySet().iterator(); i.hasNext();++k){
		  final String name = i.next();
		  sbuf.append(k==0?"":'&').append(name).append('=').append(params.get(name));
	  }
	  sbuf.append('&').append("key").append('=').append(key);
	  return (MD5Util.encode(sbuf.toString(), ENCODING).toUpperCase());
  }
  
  private final boolean verifySign(final StringBuilder sbuf, final SortedMap<String, String> params,
		  final Element root, final String rsign, final String key){
	  params.clear();
	  final List<?> elems = root.elements();
	  for(final Iterator<?> i = elems.iterator(); i.hasNext();){
		 final Element elem =  (Element)i.next();
		 final String name = elem.getName();
		 if("sign".equals(name)){
			 continue;
		 }
		 params.put(name, elem.getText());
	  }
	  final String sign = getSign(sbuf, params, key);
	  return (sign.equals(rsign));
  }
  
  private String getNotifyAction(final StringBuilder sbuf, final SysConfigure conf){
	  sbuf.setLength(0);
	  /*sbuf.append("http://");
	  if(conf.getDomain().indexOf('.') == 0){
		  sbuf.append("www");
	  }*/
	  sbuf.append(conf.getWwwUrl()).append("/weixinpay/notifyUrl.action");
	  return sbuf.toString();
  }
  
  private String getNonceStr(final StringBuilder sbuf){
	  sbuf.setLength(0);
	  final String uuid = UUID.randomUUID().toString();
	  for(int i = 0, size = uuid.length(); i < size; ++i){
		  final char c = uuid.charAt(i);
		  if(c != '-'){
			  sbuf.append(c);
		  }
	  }
	  return sbuf.toString();
  }
  
  public String notifyUrl() throws Exception
  {
    request = Struts2Utils.getRequest();
    response= Struts2Utils.getResponse();
    try{
    	final SysConfigure conf = ApplicationListenerImpl.sysConfigureJson;
    	 // parse - request
        final String notify = readNotify(request);
        final Document doc  = DocumentHelper.parseText(notify);
        final Element  root = doc.getRootElement();
        // check - return_code
        final String ret = root.element("return_code").getText();
        if("SUCCESS".equals(ret) == false){
        	final String msg = root.element("return_msg").getText();
        	LOG.error("weixin notify: return - {}", msg);
        	return null;
        }
        // check - result_code
        final String res = root.element("result_code").getText();
        if("SUCCESS".equals(res) == false){
        	final String msg = root.element("return_msg").getText();
        	LOG.error("weixin notify: result - {}", msg);
        	return null;
        }
        // check - sign
        final String sign = root.element("sign").getText();
        if(verifySign(new StringBuilder(), new TreeMap<String, String>(), 
        		root, sign, conf.getWeixinPayKey()) == false){
        	LOG.error("weixin notify: sign error");
        	return null;
        }
        // xid
        final String txno = root.element("out_trade_no").getText();
        final String xid = root.element("transaction_id").getText();
        consumetableService.paid(txno, xid);
        // ok
        final PrintWriter out = response.getWriter();
        out.println("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
        out.flush();
        return null;
    }catch(final RuntimeException e){
    	LOG.error("weixin notify: {}", e.getMessage());
    	throw e;
    }
  }
  
  private String readNotify(final HttpServletRequest request) throws IOException {
	  LOG.debug("request content-type: {}, content-length: {}", 
			  request.getContentType(), request.getContentLength());
	  final InputStream in= request.getInputStream();
	  final Reader reader = new InputStreamReader(in, ENCODING);
	  try{
		  final int MAX = 1<<12, xmlsz = "</xml>".length(); // read max 4k!
		  StringBuilder sbuf = new StringBuilder();
		  for(int c = reader.read(), i = 0, k = 0; ; c = reader.read(), ++i){
			  if(c == -1 || i >= MAX){
				  if(LOG.isDebugEnabled()){
					  LOG.debug("notify after reading {} times: {}", 
							  i, sbuf.toString());
				  }
				  throw new IOException("weixin notify: messy");
			  }
			  sbuf.append((char)c); // bugfix-0: c cast to char!
			  switch(c){
			  case '<':
				  ++k;
				  if(k != 1){       // bugfix-1: test current k!
					  k = 0;
				  }
				  break;
			  case '/':
				  ++k;
				  if(k != 2){
					  k = 0;
				  }
				  break;
			  case 'x':
				  ++k;
				  if(k != 3){
					  k = 0;
				  }
				  break;
			  case 'm':
				  ++k;
				  if(k != 4){
					  k = 0;
				  }
				  break;
			  case 'l':
				  ++k;
				  if(k != 5){
					  k = 0;
				  }
				  break;
			  case '>':
				  ++k;
				  if(k != 6){
					  k = 0;
				  }
				  break;
			  default:
				  k = 0;
			  }
			  if(k == xmlsz){
				  break;
			  }
		  }
		  final String notify = sbuf.toString();
		  sbuf = null;
		  LOG.debug("notify content: {}", notify);
		  return notify;
	  }finally{
		  reader.close();
	  }
  }
  
  public String poll(){
	  Consumetable cons = consumetableService
			 .findByOutTradeNo(getOut_trade_no());
	  final int tt = (StringUtil.isBlank(cons.getWithold())?1/*充值*/:0/*购物*/);
	  final int ps = cons.getPayStatus();
	  cons = null;
	  final StringBuilder sbuf = new StringBuilder();
	  sbuf.append('{')
	  .append('\"').append("ps").append('\"').append(':').append(ps)
	  .append(',')
	  .append('\"').append("tt").append('\"').append(':').append(tt)
	  .append('}');
	  Struts2Utils.renderJson(sbuf.toString(), StringUtil.EARR_STRING);
	  return null;
  }

  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
  }
  
  public String getProductBody()
  {
    return productBody;
  }
  
  public void setProductBody(String productBody)
  {
    this.productBody = productBody;
  }
  
  public String getProductName()
  {
    return productName;
  }
  
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  
  public String getBank_type()
  {
    return bank_type;
  }
  
  public void setBank_type(String bank_type)
  {
    this.bank_type = bank_type;
  }
  
  public String getCurrTime()
  {
    return currTime;
  }
  
  public void setCurrTime(String currTime)
  {
    this.currTime = currTime;
  }
  
  public String getOut_trade_no()
  {
    return out_trade_no;
  }
  
  public void setOut_trade_no(String out_trade_no)
  {
    this.out_trade_no = out_trade_no;
  }
  
  public String getRequestUrl()
  {
    return requestUrl;
  }
  
  public void setRequestUrl(String requestUrl)
  {
    this.requestUrl = requestUrl;
  }
  
  public String getHidUseBalance()
  {
    return hidUseBalance;
  }
  
  public void setHidUseBalance(String hidUseBalance)
  {
    this.hidUseBalance = hidUseBalance;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Consumetable getConsumetable()
  {
    return consumetable;
  }
  
  public void setConsumetable(Consumetable consumetable)
  {
    this.consumetable = consumetable;
  }
  
  public List<ProductCart> getProductCartList()
  {
    return productCartList;
  }
  
  public void setProductCartList(List<ProductCart> productCartList)
  {
    this.productCartList = productCartList;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public Consumerdetail getConsumerdetail()
  {
    return consumerdetail;
  }
  
  public void setConsumerdetail(Consumerdetail consumerdetail)
  {
    this.consumerdetail = consumerdetail;
  }
  
  public ProductCart getProductCart()
  {
    return productCart;
  }
  
  public void setProductCart(ProductCart productCart)
  {
    this.productCart = productCart;
  }
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public Spellbuyproduct getSpellbuyproduct()
  {
    return spellbuyproduct;
  }
  
  public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct)
  {
    this.spellbuyproduct = spellbuyproduct;
  }
  
  public String getPaymentStatus()
  {
    return paymentStatus;
  }
  
  public void setPaymentStatus(String paymentStatus)
  {
    this.paymentStatus = paymentStatus;
  }
  
  public Spellbuyrecord getSpellbuyrecord()
  {
    return spellbuyrecord;
  }
  
  public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord)
  {
    this.spellbuyrecord = spellbuyrecord;
  }
  
  public Randomnumber getRandomnumber()
  {
    return randomnumber;
  }
  
  public void setRandomnumber(Randomnumber randomnumber)
  {
    this.randomnumber = randomnumber;
  }
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public List<ProductJSON> getSuccessCartList()
  {
    return successCartList;
  }
  
  public void setSuccessCartList(List<ProductJSON> successCartList)
  {
    this.successCartList = successCartList;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public Commissionquery getCommissionquery()
  {
    return commissionquery;
  }
  
  public void setCommissionquery(Commissionquery commissionquery)
  {
    this.commissionquery = commissionquery;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }
  
  public String getIntegral()
  {
    return integral;
  }
  
  public void setIntegral(String integral)
  {
    this.integral = integral;
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

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
  
}

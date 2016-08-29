package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

@Component("AlipayAction")
public class AlipayAction extends BaseAction
{
  private static final long serialVersionUID = -8970081284102469306L;
  private static final Logger LOG = LoggerFactory.getLogger(AlipayAction.class);
  private static String secret = ConfigUtil.getValue("secret", "1yyg1234567890");
  // consts
  private static final int VF_FAIL      = -1;
  private static final int PS_FAID_BUY  = 0x1;
  private static final int PS_FAID_REC  = 0x11;
  private static final int PS_TIMEO_BUY = 0x3;
  
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
  
  public String goPay()
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
	
    final SysConfigure conf = ApplicationListenerImpl.sysConfigureJson;
    String notify_url = AlipayConfig.notify_url;
    String return_url = AlipayConfig.return_url;
    String seller_email = AlipayConfig.mail;
    String subject = conf.getSaitName();
    String total_fee = moneyCount.toString();
    String body = conf.getSaitName();
    String anti_phishing_key = currTime;
    String exter_invoke_ip = Struts2Utils.getRemoteIp();
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "create_direct_pay_by_user");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", "1");
    sParaTemp.put("notify_url", notify_url);
    sParaTemp.put("return_url", return_url);
    sParaTemp.put("seller_email", seller_email);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", (total_fee)+"");
    sParaTemp.put("body", body);
    sParaTemp.put("anti_phishing_key", anti_phishing_key);
    sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
    sParaTemp.put("extra_common_param", integral);
    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    LOG.debug(sHtmlText);
    Struts2Utils.render("text/html", sHtmlText, StringUtil.ENCA_UTF8);
    //Struts2Utils.render("text/html", sHtmlText);
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
  
  public String returnUrl() throws UnsupportedEncodingException
  {
	  switch(afterPaying()){
		case VF_FAIL:
			Struts2Utils.render("text/html", 
			  		  "<script>alert(\"验证失败，请联系客服！\");window.location.href=\"/index.html\";</script>", 
			  		  StringUtil.ENCA_UTF8);
			return null;
		case PS_FAID_BUY:
			Struts2Utils.render("text/html", 
      			  "<script>window.location.href=\"/mycart/shopok.html?id=" + out_trade_no + "\";</script>", 
      			  StringUtil.ENCA_UTF8);
			return null;
		case PS_TIMEO_BUY:
			Struts2Utils.render("text/html", 
			  		  "<script>alert(\"夺宝超时，请联系客服！\");window.location.href=\"/index.html\";</script>", 
			  		  StringUtil.ENCA_UTF8);
			return null;
		case PS_FAID_REC:
			Struts2Utils.render("text/html", 
	          		  "<script>window.location.href=\"/user/UserBalance.html\";</script>", 
	          		  StringUtil.ENCA_UTF8);
			return null;
		default:
			LOG.warn("Ooh");
			return null;
	  }
  }
  
  public String notifyUrl() throws Exception
  {
    switch(afterPaying()){
	case VF_FAIL:
		Struts2Utils.render("text/html", "fail", StringUtil.ENCA_UTF8);
		break;
	case PS_FAID_BUY:
	case PS_TIMEO_BUY:
	case PS_FAID_REC:
	default:
		Struts2Utils.render("text/html", "success", StringUtil.ENCA_UTF8);
    }
    return null;
  }
  
  private final int afterPaying() throws UnsupportedEncodingException{
	request = Struts2Utils.getRequest();
	response = Struts2Utils.getResponse();
	Map<String, String> params = new HashMap<String, String>();
    Map<?,?> requestParams = request.getParameterMap();
    for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();)
    {
      String name = (String)iter.next();
      String[] values = (String[])requestParams.get(name);
      params.put(name, values[0]);
    }
    out_trade_no = new String(request.getParameter("out_trade_no")
    		.getBytes("ISO-8859-1"), "UTF-8");
    final String trade_no = new String(request.getParameter("trade_no")
    		.getBytes("ISO-8859-1"), "UTF-8");
    final String trade_status = new String(request.getParameter("trade_status")
    		.getBytes("ISO-8859-1"), "UTF-8");
    
    if (AlipayNotify.verify(params))
    {
      if (("TRADE_FINISHED".equals(trade_status)) || ("TRADE_SUCCESS".equals(trade_status)))
      {
        LOG.info("Alipay: 验证成功");
    	final Consumetable cons = consumetableService.paid(out_trade_no, trade_no);
    	if (StringUtil.isBlank(cons.getWithold()))
        {
    		return (PS_FAID_REC);
        }
        else
        {
        	
        	return (cons.isPaid()?PS_FAID_BUY:PS_TIMEO_BUY);
        }
	  }
    }
    return VF_FAIL; 
  }
  
  public String noKeyRecharge()
  {
    String str = ApplicationListenerImpl.sysConfigureJson.getAliPaySignId() + ApplicationListenerImpl.sysConfigureJson.getAliPaySignKey() + tradeNo + money + title + memo;

    String md5sign = MD5Util.encode(str);
    if (StringUtil.isNotBlank(sign)) {
      if (sign.equals(md5sign))
      {
        if (AliyunOcsSampleHelp.getIMemcachedCache().get(sign) == null) {
          try
          {
            consumetable = consumetableService.findByOutTradeNo(title);
            if (consumetable != null)
            {
              double m = Double.parseDouble(money);
              if ((consumetable.getMoney().equals(Double.valueOf(m))) && (consumetable.getTransactionId() == null))
              {
                consumetable.setTransactionId(tradeNo);
                if (gateway.equals("alipay")) {
                  consumetable.setInterfaceType("aliPay");
                } else if (gateway.equals("tenpay")) {
                  consumetable.setInterfaceType("tenPay");
                }
                consumetable.setPayStatus(Consumetable.PAY_STAT_PAID);
                consumetableService.add(consumetable);
                
                user = ((User)userService.findById(String.valueOf(consumetable.getUserId())));
                user.setBalance(Double.valueOf(m + user.getBalance().doubleValue()));
                userService.add(user);
                AliyunOcsSampleHelp.getIMemcachedCache().set(sign, 43200, "y");
                Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
              }
              else
              {
                Struts2Utils.render("text/html", "Fail", new String[] { "encoding:UTF-8" });
              }
            }
            else
            {
              Struts2Utils.render("text/html", "IncorrectOrder", new String[] { "encoding:UTF-8" });
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        } else {
          Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
        }
      }
      else {
        Struts2Utils.render("text/html", "Fail", new String[] { "encoding:UTF-8" });
      }
    }
    return null;
  }
  
  public void noKeyRechargeReturn()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(tradeNo) == null) {
      try
      {
        consumetable = consumetableService.findByOutTradeNo(tradeNo);
        if ((consumetable != null) && 
          (consumetable.getTransactionId() != null))
        {
          System.err.println("++++++++++" + payName);
          if ((payName.equals("aliPayUser")) || (payName.equals("tenPayUser")))
          {
            try
            {
              if (AliyunOcsSampleHelp.getIMemcachedCache().get(tradeNo) != null) {
                return;
              }
              productCartList = new ArrayList();
              successCartList = new ArrayList();
              try
              {
                JSONArray payInfo2 = (JSONArray)AliyunOcsSampleHelp.getIMemcachedCache().get("doPay" + tradeNo);
                JSONObject object = payInfo2.getJSONObject(0);
                int userPayType = object.getInt("userPayType");
                userId = object.getString("userId");
                moneyCount = Integer.valueOf(object.getInt("moneyCount"));
                integral = object.getString("integral");
                int bankMoney = object.getInt("bankMoney");
                String hidUseBalance = object.getString("hidUseBalance");
                out_trade_no = object.getString("out_trade_no");
                productCartList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("doPayList" + tradeNo));
                
                successCartList = BuyCartdo(productCartList, Integer.valueOf(userPayType), userId, moneyCount, integral, Integer.valueOf(bankMoney), hidUseBalance, out_trade_no);
                if (successCartList.size() > 0)
                {
                  if (request.isRequestedSessionIdFromCookie())
                  {
                    for (int i = 0; i < cookies.length; i++)
                    {
                      Cookie cookie = cookies[i];
                      if (cookie.getName().equals("products"))
                      {
                        new StringUtil();String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
                        if ((product != null) && (!product.equals(""))) {
                          array = JSONArray.fromObject(product);
                        }
                      }
                    }
                    for (int i = 0; i < successCartList.size(); i++)
                    {
                      ProductJSON productJSON = (ProductJSON)successCartList.get(i);
                      if ((array != null) && (!array.toString().equals("[{}]"))) {
                        for (int j = 0; j < array.size();) {
                          try
                          {
                            JSONObject obj = (JSONObject)array.get(j);
                            Integer pid = Integer.valueOf(Integer.parseInt(obj.getString("pId")));
                            boolean f = false;
                            if (productJSON.getProductId().equals(pid))
                            {
                              f = true;
                              array.remove(j);
                              break;
                            }
                            if (f) {}
                            j++;
                          }
                          catch (Exception localException1) {}
                        }
                      }
                    }
                    new StringUtil();String product = StringUtil.getUTF8URLEncoder(array.toString());
                    Cookie cookie = new Cookie("products", product);
                    cookie.setMaxAge(-1);
                    cookie.setPath("/");
                    cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                    response.addCookie(cookie);
                  }
                  AliyunOcsSampleHelp.getIMemcachedCache().set(out_trade_no, 300, successCartList);
                  Struts2Utils.render("text/html", "buySuccess", new String[] { "encoding:UTF-8" });
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
              System.err.println("即时到账处理业务完毕");
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
          }
          else
          {
            AliyunOcsSampleHelp.getIMemcachedCache().set(tradeNo, 7200, "y");
            Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
          }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public synchronized List<ProductJSON> BuyCartdo(List<ProductCart> productCartList, Integer userPayType, String userId, Integer moneyCount, String integral, Integer bankMoney, String hidUseBalance, String out_trade_no)
  {
    boolean flag = false;
    List<ProductJSON> successCartList = new ArrayList();
    Integer fb = Integer.valueOf(0);
    Integer fi = Integer.valueOf(0);
    try
    {
      fb = Integer.valueOf(Integer.parseInt(hidUseBalance) + bankMoney.intValue());
      fi = Integer.valueOf(Integer.parseInt(integral));
    }
    catch (Exception e)
    {
      flag = false;
      return successCartList;
    }
    User user = null;
    if (StringUtil.isNotBlank(userId)) {
      user = (User)userService.findById(userId);
    }
    if (productCartList.size() > 0)
    {
      Integer points;
      try
      {
        if (userPayType.intValue() == 1)
        {
          if (Integer.parseInt(hidUseBalance) > 0) {
            if (user.getBalance().doubleValue() >= Integer.parseInt(hidUseBalance))
            {
              Double temp = Double.valueOf(user.getBalance().doubleValue() - Integer.parseInt(hidUseBalance));
              points = user.getCommissionPoints();
              user.setBalance(temp);
              user.setCommissionPoints(Integer.valueOf(points.intValue() + Integer.parseInt(hidUseBalance) * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue()));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"余额不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
        }
        else if (userPayType.intValue() == 2)
        {
          if (user.getCommissionPoints().intValue() >= Integer.parseInt(integral))
          {
            points = user.getCommissionPoints();
            user.setCommissionPoints(Integer.valueOf(points.intValue() - Integer.parseInt(integral)));
            flag = true;
          }
          else
          {
            flag = false;
            Struts2Utils.render("text/html", "<script>alert(\"积分不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
          }
        }
        else if (userPayType.intValue() == 3)
        {
          if (Integer.parseInt(hidUseBalance) > 0) {
            if (user.getBalance().doubleValue() >= Integer.parseInt(hidUseBalance))
            {
              Double temp = Double.valueOf(user.getBalance().doubleValue() - Integer.parseInt(hidUseBalance));
              points = user.getCommissionPoints();
              user.setBalance(temp);
              user.setCommissionPoints(Integer.valueOf(points.intValue() + Integer.parseInt(hidUseBalance) * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue()));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"余额不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
          if (Integer.parseInt(integral) >= 100) {
            if (user.getCommissionPoints().intValue() >= Integer.parseInt(integral))
            {
              points = user.getCommissionPoints();
              user.setCommissionPoints(Integer.valueOf(points.intValue() - Integer.parseInt(integral)));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"积分不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
        }
        else if (userPayType.intValue() == 4)
        {
          if (Integer.parseInt(hidUseBalance) > 0) {
            if (user.getBalance().doubleValue() >= Integer.parseInt(hidUseBalance))
            {
              Double temp = Double.valueOf(user.getBalance().doubleValue() - Integer.parseInt(hidUseBalance));
              points = user.getCommissionPoints();
              user.setBalance(temp);
              user.setCommissionPoints(Integer.valueOf(points.intValue() + Integer.parseInt(hidUseBalance) * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue()));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"余额不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
          if (Integer.parseInt(integral) >= 100) {
            if (user.getCommissionPoints().intValue() >= Integer.parseInt(integral))
            {
              points = user.getCommissionPoints();
              user.setCommissionPoints(Integer.valueOf(points.intValue() - Integer.parseInt(integral)));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"积分不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
          if (bankMoney.intValue() > 0) {
            if (user.getBalance().doubleValue() >= bankMoney.intValue())
            {
              Double temp = Double.valueOf(user.getBalance().doubleValue() - bankMoney.intValue());
              points = user.getCommissionPoints();
              user.setBalance(temp);
              user.setCommissionPoints(Integer.valueOf(points.intValue() + bankMoney.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue()));
              flag = true;
            }
            else
            {
              flag = false;
              Struts2Utils.render("text/html", "<script>alert(\"余额不足，支付失败！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
            }
          }
        }
      }
      catch (Exception e)
      {
        flag = false;
        System.err.println("扣款处理出错");
      }
      if (flag) {
        for (ProductCart productCart : productCartList)
        {
          Integer count = Integer.valueOf(0);
          try
          {
            Consumerdetail consumerdetail = new Consumerdetail();
            Spellbuyrecord spellbuyrecord = new Spellbuyrecord();
            ProductJSON productJSON = new ProductJSON();
            Commissionquery commissionquery = new Commissionquery();
            Spellbuyproduct spellbuyproduct = (Spellbuyproduct)spellbuyproductService.findById(productCart.getProductId().toString());
            if (spellbuyproduct.getSpellbuyLimit().intValue() > 0)
            {
              if (StringUtil.isNotBlank(userId))
              {
                Integer limitNum = Integer.valueOf(0);
                try
                {
                  List<Randomnumber> dataList = spellbuyrecordService.getRandomNumberList(spellbuyproduct.getSpellbuyProductId().toString(), userId);
                  for (Randomnumber randomnumber : dataList) {
                    try
                    {
                      String[] randoms = randomnumber.getRandomNumber().split(",");
                      limitNum = Integer.valueOf(limitNum.intValue() + randoms.length);
                    }
                    catch (Exception e)
                    {
                      e.printStackTrace();
                    }
                  }
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }
                Integer limit = Integer.valueOf(spellbuyproduct.getSpellbuyLimit().intValue() - limitNum.intValue());
                if (productCart.getCount().intValue() > limit.intValue()) {
                  count = limit;
                } else {
                  count = productCart.getCount();
                }
                productCart.setMyLimitSales(limitNum);
              }
              else
              {
                Integer limit = spellbuyproduct.getSpellbuyLimit();
                if (productCart.getCount().intValue() > limit.intValue()) {
                  count = limit;
                } else {
                  count = productCart.getCount();
                }
                productCart.setMyLimitSales(Integer.valueOf(0));
              }
            }
            else
            {
              Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
              if (spellbuyproduct.getSpellbuyCount().intValue() + productCart.getCount().intValue() > productCart.getProductPrice().intValue()) {
                count = Integer.valueOf(productCart.getProductPrice().intValue() - spellbuyproduct.getSpellbuyCount().intValue());
              } else {
                count = productCart.getCount();
              }
              productCart.setMyLimitSales(Integer.valueOf(0));
            }
            if (count.intValue() > 0)
            {
              try
              {
                if (userPayType.intValue() == 1)
                {
                  consumerdetail.setBuyCount(count);
                  consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                  consumerdetail.setConsumetableId(out_trade_no);
                  consumerdetail.setProductId(productCart.getProductId());
                  consumerdetail.setProductName(productCart.getProductName());
                  consumerdetail.setProductPeriod(productCart.getProductPeriod());
                  consumerdetail.setProductTitle(productCart.getProductTitle());
                  consumerdetailService.add(consumerdetail);
                  if (user.getInvite() != null)
                  {
                    User userCommission = (User)userService.findById(String.valueOf(user.getInvite()));
                    double tempCommissionCount = userCommission.getCommissionCount().doubleValue();
                    double commissionBalance = userCommission.getCommissionBalance().doubleValue();
                    tempCommissionCount += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    commissionBalance += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    userCommission.setCommissionCount(Double.valueOf(tempCommissionCount));
                    userCommission.setCommissionBalance(Double.valueOf(commissionBalance));
                    userService.add(userCommission);
                    commissionquery.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                    commissionquery.setCommission(Double.valueOf(Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                    commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                    commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                    commissionquery.setInvitedId(user.getInvite());
                    commissionquery.setToUserId(user.getUserId());
                    commissionqueryService.add(commissionquery);
                  }
                  Commissionpoints commissionpoints = new Commissionpoints();
                  commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                  commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得积分");
                  commissionpoints.setPay("+" + count.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue());
                  commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                  commissionpointsService.add(commissionpoints);
                  flag = true;
                }
                else if (userPayType.intValue() == 2)
                {
                  consumerdetail.setBuyCount(count);
                  consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                  consumerdetail.setConsumetableId(out_trade_no);
                  consumerdetail.setProductId(productCart.getProductId());
                  consumerdetail.setProductName(productCart.getProductName());
                  consumerdetail.setProductPeriod(productCart.getProductPeriod());
                  consumerdetail.setProductTitle(productCart.getProductTitle());
                  consumerdetailService.add(consumerdetail);
                  
                  Commissionpoints commissionpoints = new Commissionpoints();
                  commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                  commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")积分抵扣");
                  commissionpoints.setPay("-" + count.intValue() * 100);
                  commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                  commissionpointsService.add(commissionpoints);
                  flag = true;
                }
                else if (userPayType.intValue() == 3)
                {
                  Commissionpoints commissionpoints = new Commissionpoints();
                  commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                  if (fb.intValue() >= count.intValue())
                  {
                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得积分");
                    commissionpoints.setPay("+" + count.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue());
                    fb = Integer.valueOf(fb.intValue() - count.intValue());
                  }
                  else if (fi.intValue() / 100 >= count.intValue())
                  {
                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")积分抵扣");
                    commissionpoints.setPay("-" + count.intValue() * 100);
                    fi = Integer.valueOf(fi.intValue() - count.intValue() * 100);
                  }
                  commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                  commissionpointsService.add(commissionpoints);
                  flag = true;
                  
                  consumerdetail.setBuyCount(count);
                  consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                  consumerdetail.setConsumetableId(out_trade_no);
                  consumerdetail.setProductId(productCart.getProductId());
                  consumerdetail.setProductName(productCart.getProductName());
                  consumerdetail.setProductPeriod(productCart.getProductPeriod());
                  consumerdetail.setProductTitle(productCart.getProductTitle());
                  consumerdetailService.add(consumerdetail);
                  if (user.getInvite() != null)
                  {
                    User userCommission = (User)userService.findById(String.valueOf(user.getInvite()));
                    double tempCommissionCount = userCommission.getCommissionCount().doubleValue();
                    double commissionBalance = userCommission.getCommissionBalance().doubleValue();
                    tempCommissionCount += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    commissionBalance += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    userCommission.setCommissionCount(Double.valueOf(tempCommissionCount));
                    userCommission.setCommissionBalance(Double.valueOf(commissionBalance));
                    userService.add(userCommission);
                    commissionquery.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                    commissionquery.setCommission(Double.valueOf(Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                    commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                    commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                    commissionquery.setInvitedId(user.getInvite());
                    commissionquery.setToUserId(user.getUserId());
                    commissionqueryService.add(commissionquery);
                  }
                }
                else if (userPayType.intValue() == 4)
                {
                  Commissionpoints commissionpoints = new Commissionpoints();
                  commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                  if (fb.intValue() >= count.intValue())
                  {
                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得积分");
                    commissionpoints.setPay("+" + count.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue());
                    fb = Integer.valueOf(fb.intValue() - count.intValue());
                  }
                  else if (fi.intValue() / 100 >= count.intValue())
                  {
                    commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")积分抵扣");
                    commissionpoints.setPay("-" + count.intValue() * 100);
                    fi = Integer.valueOf(fi.intValue() - count.intValue() * 100);
                  }
                  commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                  commissionpointsService.add(commissionpoints);
                  flag = true;
                  if (user.getInvite() != null)
                  {
                    User userCommission = (User)userService.findById(String.valueOf(user.getInvite()));
                    double tempCommissionCount = userCommission.getCommissionCount().doubleValue();
                    double commissionBalance = userCommission.getCommissionBalance().doubleValue();
                    tempCommissionCount += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    commissionBalance += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue();
                    userCommission.setCommissionCount(Double.valueOf(tempCommissionCount));
                    userCommission.setCommissionBalance(Double.valueOf(commissionBalance));
                    userService.add(userCommission);
                    commissionquery.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                    commissionquery.setCommission(Double.valueOf(Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                    commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                    commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                    commissionquery.setInvitedId(user.getInvite());
                    commissionquery.setToUserId(user.getUserId());
                    commissionqueryService.add(commissionquery);
                  }
                  consumerdetail.setBuyCount(count);
                  consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                  consumerdetail.setConsumetableId(out_trade_no);
                  consumerdetail.setProductId(productCart.getProductId());
                  consumerdetail.setProductName(productCart.getProductName());
                  consumerdetail.setProductPeriod(productCart.getProductPeriod());
                  consumerdetail.setProductTitle(productCart.getProductTitle());
                  consumerdetailService.add(consumerdetail);
                }
              }
              catch (Exception e)
              {
                flag = false;
                break;
              }
              if (flag)
              {
                spellbuyproduct.setSpellbuyCount(Integer.valueOf(spellbuyproduct.getSpellbuyCount().intValue() + count.intValue()));
                if (spellbuyproduct.getSpellbuyCount().intValue() >= productCart.getProductPrice().intValue())
                {
                  spellbuyproduct.setSpellbuyCount(productCart.getProductPrice());
                  
                  spellbuyproduct.setSpStatus(Integer.valueOf(2));
                  spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                  try
                  {
                    Lotteryproductutil lotteryproductutil = new Lotteryproductutil();
                    lotteryproductutil.setLotteryProductEndDate(DateUtil.DateTimeToStr(DateUtil.subMinute(new Date(), -3)));
                    lotteryproductutil.setLotteryProductId(spellbuyproduct.getSpellbuyProductId());
                    lotteryproductutil.setLotteryProductImg(productCart.getHeadImage());
                    lotteryproductutil.setLotteryProductName(productCart.getProductName());
                    lotteryproductutil.setLotteryProductPeriod(spellbuyproduct.getProductPeriod());
                    lotteryproductutil.setLotteryProductPrice(spellbuyproduct.getSpellbuyPrice());
                    lotteryproductutil.setLotteryProductTitle(productCart.getProductTitle());
                    lotteryproductutilService.add(lotteryproductutil);
                  }
                  catch (Exception localException1) {}
                }
                spellbuyproductService.add(spellbuyproduct);
                try
                {
                  Product product = (Product)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_product_" + spellbuyproduct.getFkProductId());
                  if (product == null)
                  {
                    product = (Product)productService.findById(String.valueOf(spellbuyproduct.getFkProductId()));
                    AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_product_" + spellbuyproduct.getFkProductId(), 1800, product);
                  }
                  if (product.getStatus().intValue() == 1)
                  {
                    int productPeriod = spellbuyproduct.getProductPeriod().intValue();
                    List<Spellbuyproduct> spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
                    if (spellbuyproductOld.size() == 0) {
                      try
                      {
                        Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
                        spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
                        spellbuyproduct2.setProductPeriod(Integer.valueOf(++productPeriod));
                        spellbuyproduct2.setSpellbuyCount(Integer.valueOf(0));
                        spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
                        spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpellbuyPrice(product.getProductPrice());
                        spellbuyproduct2.setSpSinglePrice(product.getSinglePrice());
                        spellbuyproduct2.setSpellbuyLimit(product.getProductLimit());
                        spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
                        spellbuyproduct2.setSpStatus(Integer.valueOf(0));
                        if (product.getAttribute71().equals("hot")) {
                          spellbuyproduct2.setSpellbuyType(Integer.valueOf(8));
                        } else {
                          spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
                        }
                        spellbuyproductService.add(spellbuyproduct2);
                      }
                      catch (Exception e)
                      {
                        e.printStackTrace();
                      }
                    }
                  }
                  spellbuyrecord.setFkSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }
                spellbuyrecord.setBuyer(user.getUserId());
                spellbuyrecord.setBuyPrice(count);
                spellbuyrecord.setBuyDate(DateUtil.DateTimeToStrBySSS(new Date()));
                spellbuyrecord.setSpWinningStatus("0");
                spellbuyrecord.setBuyStatus("0");
                spellbuyrecord.setSpRandomNo("");
                String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
                if (ip == null) {
                  ip = "127.0.0.1";
                }
                spellbuyrecord.setBuyIp(ip);
                String buyLocal = RegisterAction.seeker.getAddress(ip);
                spellbuyrecord.setBuyLocal(buyLocal);
                spellbuyrecord.setBuySource(Integer.valueOf(0));
                spellbuyrecordService.add(spellbuyrecord);
                Randomnumber randomnumber = new Randomnumber();
                randomnumber.setProductId(productCart.getProductId());
                
                List<Randomnumber> RandomnumberList = randomnumberService.query(" from Randomnumber where productId='" + spellbuyproduct.getSpellbuyProductId() + "'");
                List<String> oldRandomList = new ArrayList<String>();
                for (Randomnumber rand : RandomnumberList) {
                  if (rand.getRandomNumber().contains(","))
                  {
                    String[] rs = rand.getRandomNumber().split(",");
                    for (String string : rs) {
                      oldRandomList.add(string);
                    }
                  }
                  else
                  {
                    oldRandomList.add(rand.getRandomNumber());
                  }
                }
                randomnumber.setRandomNumber(RandomUtil.newRandom(count.intValue(), spellbuyproduct.getSpellbuyPrice().intValue(), oldRandomList));
                
                randomnumber.setSpellbuyrecordId(spellbuyrecord.getSpellbuyRecordId());
                randomnumber.setBuyDate(spellbuyrecord.getBuyDate());
                randomnumber.setUserId(Integer.valueOf(Integer.parseInt(userId)));
                randomnumberService.add(randomnumber);
                
                Integer experience = user.getExperience();
                experience = Integer.valueOf(experience.intValue() + count.intValue() * 10);
                user.setExperience(experience);
                userService.add(user);
                

                productJSON.setBuyDate(spellbuyrecord.getBuyDate());
                productJSON.setProductId(spellbuyproduct.getFkProductId());
                productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(productCart.getProductName());
                productJSON.setProductPeriod(productCart.getProductPeriod());
                productJSON.setProductTitle(productCart.getProductTitle());
                productJSON.setBuyCount(count);
                successCartList.add(productJSON);
              }
            }
          }
          catch (Exception e)
          {
            flag = false;
            e.printStackTrace();
          }
        }
      }
    }
    return successCartList;
  }
  
  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public Consumetable getConsumetable()
  {
    return consumetable;
  }
  
  public void setConsumetable(Consumetable consumetable)
  {
    this.consumetable = consumetable;
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
  
  public Consumerdetail getConsumerdetail()
  {
    return consumerdetail;
  }
  
  public void setConsumerdetail(Consumerdetail consumerdetail)
  {
    this.consumerdetail = consumerdetail;
  }
  
  public List<ProductCart> getProductCartList()
  {
    return productCartList;
  }
  
  public void setProductCartList(List<ProductCart> productCartList)
  {
    this.productCartList = productCartList;
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
  
  public String getPaymentStatus()
  {
    return paymentStatus;
  }
  
  public void setPaymentStatus(String paymentStatus)
  {
    this.paymentStatus = paymentStatus;
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
  
  public Lotteryproductutil getLotteryproductutil()
  {
    return lotteryproductutil;
  }
  
  public void setLotteryproductutil(Lotteryproductutil lotteryproductutil)
  {
    this.lotteryproductutil = lotteryproductutil;
  }
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getTradeNo()
  {
    return tradeNo;
  }
  
  public void setTradeNo(String tradeNo)
  {
    this.tradeNo = tradeNo;
  }
  
  public String getMoney()
  {
    return money;
  }
  
  public void setMoney(String money)
  {
    this.money = money;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getAlipay_account()
  {
    return alipay_account;
  }
  
  public void setAlipay_account(String alipay_account)
  {
    this.alipay_account = alipay_account;
  }
  
  public String getTenpay_account()
  {
    return tenpay_account;
  }
  
  public void setTenpay_account(String tenpay_account)
  {
    this.tenpay_account = tenpay_account;
  }
  
  public String getGateway()
  {
    return gateway;
  }
  
  public void setGateway(String gateway)
  {
    this.gateway = gateway;
  }
  
  public String getSign()
  {
    return sign;
  }
  
  public void setSign(String sign)
  {
    this.sign = sign;
  }
  
  public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public String getOut_trade_no()
  {
    return out_trade_no;
  }
  
  public void setOut_trade_no(String out_trade_no)
  {
    this.out_trade_no = out_trade_no;
  }
  
  public String getPayName()
  {
    return payName;
  }
  
  public void setPayName(String payName)
  {
    this.payName = payName;
  }
  
}

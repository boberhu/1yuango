package com.tech.action;

import com.tech.pojo.Consumerdetail;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.User;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.OrderIdService;
import com.tech.service.UserService;
import com.tech.tenpay.RequestHandler;
import com.tech.tenpay.ResponseHandler;
import com.tech.tenpay.client.ClientResponseHandler;
import com.tech.tenpay.client.TenpayHttpClient;
import com.tech.tenpay.config.TenpayConfig;
import com.tech.tenpay.util.TenpayUtil;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.ConfigUtil;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("BalanceAction")
public class BalanceAction extends BaseAction
{
  private static final long serialVersionUID = 8197993150697915816L;
  private static final Logger LOG = LoggerFactory.getLogger(BalanceAction.class);
  
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
	
  @Autowired
  ConsumetableService consumetableService;
  @Autowired
  ConsumerdetailService consumerdetailService;
  @Autowired
  UserService userService;
  @Autowired
  OrderIdService orderIdService;
  
  private User user;
  private String userId;
  private Consumetable consumetable;
  private ProductCart productCart;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private Consumerdetail consumerdetail;
  private List<ProductCart> productCartList;
  private String paymentStatus;
  private String currTime = TenpayUtil.getCurrTime();
  private String out_trade_no;
  private Integer moneyCount;
  private String productBody = "";
  private String productName;
  private String bank_type;
  private String hidUseBalance;
  private String requestUrl;
  private String payName;
  private String payBank;
  private Integer bankMoney;
  private String integral;
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String goBalance() throws IOException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    boolean flag = false;
    Cookie[] cookies = request.getCookies();
    productCartList = new ArrayList<ProductCart>();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          userId = cookie.getValue();
          flag = true;
        }
      }
    }
    if (StringUtil.isNotBlank(userId))
    {
      if (flag)
      {
    	out_trade_no = orderIdService.addOxid();
        consumetable = new Consumetable();
        double money = Double.parseDouble(String.valueOf(moneyCount));
        consumetable.setBuyCount(moneyCount);
        consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
        consumetable.setInterfaceType("网银支付");
        consumetable.setMoney(Double.valueOf(money));
        consumetable.setOutTradeNo(out_trade_no);
        consumetable.setUserId(Integer.valueOf(userId));
        consumetable.setWithold("");
        consumetable.setPayStatus(Consumetable.PAY_STAT_NPAID);
        consumetable.setBankMoney(moneyCount);
        consumetable.setBuyIp(Struts2Utils.getRemoteIp());
        consumetableService.add(consumetable);
      }
      
      TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("moneyCount", moneyCount.toString());
		map.put("out_trade_no", out_trade_no);
		String sign="";
		try {
			sign = getSign(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
      if (flag)
      {
        LOG.debug("payName: {}, payBank: {}", payName, payBank);
        final String action = PPLATS.get(payName);
        if (payBank.equals("0"))
        {
          String body = "<form method=\"post\" action=\""+action+"\" name=\"toPayForm\" id=\"toPayForm\">";
          body = body + "<input type=\"hidden\" value=\"" + bankMoney + "\" id=\"moneyCount\" name=\"moneyCount\">";
          body = body + "<input type=\"hidden\" value=\"" + hidUseBalance + "\" name=\"hidUseBalance\" id=\"hidUseBalance\">";
          body = body + "<input type=\"hidden\" value=\"" + out_trade_no + "\" name=\"out_trade_no\" id=\"out_trade_no\">";
          body = body + "<input type=\"hidden\" value=\"" + integral + "\" name=\"integral\" id=\"hidIntegral\">";
          body = body + "<input type=\"hidden\" value=\"" + payName + "\" name=\"payName\" id=\"payName\">";
          body = body + "<input type=\"hidden\" value=\"" + payBank + "\" name=\"payBank\" id=\"payBank\">";
          body = body + "<input type=\"hidden\" value=\"" + userId + "\" name=\"userId\" id=\"userId\">";
          body = body + "<input type=\"hidden\" value=\"" + sign + "\" name=\"token\" id=\"token\">";
          body = body + "</form>";
          body = body + "<script>document.forms['toPayForm'].submit();</script>";
          
          StringBuilder sb = new StringBuilder();
          sb.append('[');
          sb.append('{');
          sb.append("\"userPayType\":\"").append(0).append("\",");
          sb.append("\"userId\":\"").append(userId).append("\",");
          sb.append("\"moneyCount\":\"").append(moneyCount).append("\",");
          sb.append("\"integral\":\"").append(integral).append("\",");
          sb.append("\"bankMoney\":\"").append(bankMoney).append("\",");
          sb.append("\"hidUseBalance\":\"").append(hidUseBalance).append("\",");
          sb.append("\"out_trade_no\":\"").append(out_trade_no).append("\"");
          sb.append('}');
          sb.append(']');
          
          JSONArray payInfo = JSONArray.fromObject(sb.toString());
          AliyunOcsSampleHelp.getIMemcachedCache().set("doPayList" + out_trade_no, 900, productCartList);
          AliyunOcsSampleHelp.getIMemcachedCache().set("doPay" + out_trade_no, 900, payInfo);
          
          Struts2Utils.render("text/html", body, new String[] { "encoding:UTF-8" });
        }
      }
    }
    else
    {
      Struts2Utils.render("text/html", "<script>alert(\"您还未登录，请登录。\");</script>", new String[] { "encoding:UTF-8" });
    }
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
  
  public String returnUrl()
  {
    HttpServletRequest request = Struts2Utils.getRequest();
    HttpServletResponse response = Struts2Utils.getResponse();
    
    ResponseHandler resHandler = new ResponseHandler(request, response);
    resHandler.setKey(TenpayConfig.key);
    System.out.println("前台回调返回参数:" + resHandler.getAllParameters());
    if (resHandler.isTenpaySign())
    {
      String notify_id = resHandler.getParameter("notify_id");
      
      String out_trade_no = resHandler.getParameter("out_trade_no");
      
      String transaction_id = resHandler.getParameter("transaction_id");
      
      String total_fee = resHandler.getParameter("total_fee");
      
      String discount = resHandler.getParameter("discount");
      
      String trade_state = resHandler.getParameter("trade_state");
      
      String trade_mode = resHandler.getParameter("trade_mode");
      
      String hidUseBalance = resHandler.getParameter("attach");
      if ("1".equals(trade_mode))
      {
        if ("0".equals(trade_state))
        {
          try
          {
            consumetable = consumetableService.findByOutTradeNo(out_trade_no);
            double money = Double.parseDouble(String.valueOf(total_fee));
            money *= 0.01D;
            System.err.println(consumetable.getMoney());
            System.err.println(money);
            if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId().equals(transaction_id))) {
              paymentStatus = "success";
            } else {
              paymentStatus = "error";
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
          System.out.println("即时到帐付款成功");
        }
        else
        {
          System.out.println("即时到帐付款失败");
        }
      }
      else if ("2".equals(trade_mode)) {
        if ("0".equals(trade_state)) {
          System.out.println("中介担保付款成功");
        } else {
          System.out.println("trade_state=" + trade_state);
        }
      }
    }
    else
    {
      System.out.println("认证签名失败");
    }
    String debuginfo = resHandler.getDebugInfo();
    System.out.println("debuginfo:" + debuginfo);
    return "success";
  }
  
  public String notifyUrl()
    throws Exception
  {
    HttpServletRequest request = Struts2Utils.getRequest();
    HttpServletResponse response = Struts2Utils.getResponse();
    

    ResponseHandler resHandler = new ResponseHandler(request, response);
    resHandler.setKey(TenpayConfig.key);
    System.out.println("后台回调返回参数:" + resHandler.getAllParameters());
    if (resHandler.isTenpaySign())
    {
      String notify_id = resHandler.getParameter("notify_id");
      
      RequestHandler queryReq = new RequestHandler(null, null);
      
      TenpayHttpClient httpClient = new TenpayHttpClient();
      
      ClientResponseHandler queryRes = new ClientResponseHandler();
      

      queryReq.init();
      queryReq.setKey(TenpayConfig.key);
      queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
      queryReq.setParameter("partner", TenpayConfig.partner);
      queryReq.setParameter("notify_id", notify_id);
      
      httpClient.setTimeOut(5);
      
      httpClient.setReqContent(queryReq.getRequestURL());
      System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());
      if (httpClient.call())
      {
        queryRes.setContent(httpClient.getResContent());
        System.out.println("验证ID返回字符串:" + httpClient.getResContent());
        queryRes.setKey(TenpayConfig.key);
        
        String retcode = queryRes.getParameter("retcode");
        
        String out_trade_no = resHandler.getParameter("out_trade_no");
        
        String transaction_id = resHandler.getParameter("transaction_id");
        
        String total_fee = resHandler.getParameter("total_fee");
        
        String discount = resHandler.getParameter("discount");
        
        String trade_state = resHandler.getParameter("trade_state");
        
        String trade_mode = resHandler.getParameter("trade_mode");
        if ((queryRes.isTenpaySign()) && ("0".equals(retcode)))
        {
          System.out.println("id验证成功");
          if ("1".equals(trade_mode))
          {
            if ("0".equals(trade_state))
            {
              try
              {
                consumetable = consumetableService.findByOutTradeNo(out_trade_no);
                double money = Double.parseDouble(String.valueOf(total_fee));
                money *= 0.01D;
                System.err.println(consumetable.getMoney());
                System.err.println(money);
                if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId() == null))
                {
                  consumetable.setTransactionId(transaction_id);
                  consumetableService.add(consumetable);
                  userId = String.valueOf(consumetable.getUserId());
                  if ((userId != null) && (!userId.equals(""))) {
                    try
                    {
                      String key = MD5Util.encode(transaction_id);
                      if (AliyunOcsSampleHelp.getIMemcachedCache().get(key) == null)
                      {
                        user = ((User)userService.findById(userId));
                        Double recMoney = consumetable.getMoney();
                        if (recMoney.doubleValue() >= ApplicationListenerImpl.sysConfigureJson.getRecMoney().doubleValue()) {
                          recMoney = Double.valueOf(recMoney.doubleValue() + ApplicationListenerImpl.sysConfigureJson.getRecBalance().doubleValue());
                        }
                        Double temp = Double.valueOf(user.getBalance().doubleValue() + recMoney.doubleValue());
                        System.err.println("user.getBalance()" + user.getBalance());
                        System.err.println("consumetable.getMoney()" + consumetable.getMoney());
                        System.err.println("temp:" + temp);
                        user.setBalance(temp);
                        userService.add(user);
                        AliyunOcsSampleHelp.getIMemcachedCache().set(key, 43200, "y");
                      }
                    }
                    catch (Exception e)
                    {
                      e.printStackTrace();
                    }
                  }
                  resHandler.sendToCFT("success");
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
              resHandler.sendToCFT("success");
            }
            else
            {
              System.out.println("即时到账支付失败");
              resHandler.sendToCFT("fail");
            }
          }
          else if ("2".equals(trade_mode))
          {
            int iStatus = TenpayUtil.toInt(trade_state);
            switch (iStatus)
            {
            case 0: 
              break;
            case 1: 
              break;
            case 2: 
              break;
            case 4: 
              break;
            case 5: 
              break;
            case 6: 
              break;
            case 7: 
              break;
            case 8: 
              break;
            case 9: 
              break;
            }
            System.out.println("trade_state = " + trade_state);
            
            resHandler.sendToCFT("success");
          }
        }
        else
        {
          System.out.println("查询验证签名失败或id验证失败,retcode:" + queryRes.getParameter("retcode"));
        }
      }
      else
      {
        System.out.println("后台调用通信失败");
        System.out.println(httpClient.getResponseCode());
        System.out.println(httpClient.getErrInfo());
      }
    }
    else
    {
      System.out.println("通知签名验证失败");
    }
    return null;
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
  
  public String getPaymentStatus()
  {
    return paymentStatus;
  }
  
  public void setPaymentStatus(String paymentStatus)
  {
    this.paymentStatus = paymentStatus;
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
  
  public String getHidUseBalance()
  {
    return hidUseBalance;
  }
  
  public void setHidUseBalance(String hidUseBalance)
  {
    this.hidUseBalance = hidUseBalance;
  }
  
  public String getRequestUrl()
  {
    return requestUrl;
  }
  
  public void setRequestUrl(String requestUrl)
  {
    this.requestUrl = requestUrl;
  }
  
  public String getPayName()
  {
    return payName;
  }
  
  public void setPayName(String payName)
  {
    this.payName = payName;
  }
  
  public String getPayBank()
  {
    return payBank;
  }
  
  public void setPayBank(String payBank)
  {
    this.payBank = payBank;
  }
  
  public Integer getBankMoney()
  {
    return bankMoney;
  }
  
  public void setBankMoney(Integer bankMoney)
  {
    this.bankMoney = bankMoney;
  }
  
  public String getIntegral()
  {
    return integral;
  }
  
  public void setIntegral(String integral)
  {
    this.integral = integral;
  }
}

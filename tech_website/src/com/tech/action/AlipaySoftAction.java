package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tech.alipay.config.AlipayConfig;
import com.tech.alipay.util.AlipayNotify;
import com.tech.alipay.util.AlipaySubmit;
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
import com.tech.pojo.User;
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
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.RandomUtil;
import com.tech.util.Struts2Utils;

public class AlipaySoftAction extends BaseAction
{
  private static final long serialVersionUID = -8970081284102469306L;
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
  private Integer moneyCount;
  private String integral;
  private String currTime = TenpayUtil.getCurrTime();
  private String strTime = currTime.substring(8, currTime.length());
  private String strRandom = TenpayUtil.buildRandom(4)+"";
  private String strReq = strTime + strRandom;
  Random random = new Random();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String goPay()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    boolean flag = false;
    
    String payment_type = "1";
    


    String notify_url = notify_url = "http://test.hahsun.net/buySoft/notifyUrl.action";
    


    String return_url = "http://test.hahsun.net/buySoft/returnUrl.action";
    

    String seller_email = AlipayConfig.mail;
    

    String out_trade_no = strReq;
    

    String subject = "软件授权费";
    

    String total_fee = "5000";
    

    String body = "软件授权费";
    
    String show_url = "http://test.hahsun.net/buySoft/index.html";
    

    String anti_phishing_key = currTime;
    


    String exter_invoke_ip = request.getHeader("X-Real-IP");
    


    Map<String, String> sParaTemp = new HashMap();
    sParaTemp.put("service", "create_direct_pay_by_user");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", payment_type);
    sParaTemp.put("notify_url", notify_url);
    sParaTemp.put("return_url", return_url);
    sParaTemp.put("seller_email", seller_email);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", total_fee);
    sParaTemp.put("body", body);
    sParaTemp.put("show_url", show_url);
    sParaTemp.put("anti_phishing_key", anti_phishing_key);
    sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
    sParaTemp.put("extra_common_param", integral);
    


























































































    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    System.out.println(sHtmlText);
    
    Struts2Utils.render("text/html", sHtmlText, new String[] { "encoding:UTF-8" });
    


    return null;
  }
  
  public String returnUrl()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    boolean flag = false;
    
    Map<String, String> params = new HashMap();
    Map requestParams = request.getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
    {
      String name = (String)iter.next();
      String[] values = (String[])requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = 
          valueStr + values[i] + ",";
      }
      params.put(name, valueStr);
    }
    String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
    
    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
    
    String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
    
    String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
    
    integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8");
    

    boolean verify_result = AlipayNotify.verify(params);
    if (verify_result)
    {
      if ((trade_status.equals("TRADE_FINISHED")) || (trade_status.equals("TRADE_SUCCESS"))) {
        try
        {
          String key = MD5Util.encode(trade_no);
          if (AliyunOcsSampleHelp.getIMemcachedCache().get(key) == null)
          {
            AliyunOcsSampleHelp.getIMemcachedCache().set(key, 43200, "y");
            







            System.out.println("即时到帐付款成功");
            paymentStatus = "success";
            String s = UUID.randomUUID().toString().toUpperCase();
            s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
            String md5s = MD5Util.encode(s);
            String softKey = s + md5s;
            userId = softKey;
          }
          else
          {
            paymentStatus = "ok";
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      System.out.println("验证成功<br />");
    }
    else
    {
      System.out.println("验证失败");
      paymentStatus = "error";
    }
    return "success";
  }
  
  public String notifyUrl()
    throws Exception
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    
    Map<String, String> params = new HashMap();
    Map requestParams = request.getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
    {
      String name = (String)iter.next();
      String[] values = (String[])requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = 
          valueStr + values[i] + ",";
      }
      params.put(name, valueStr);
    }
    String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
    
    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
    String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
    
    String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
    
    String integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8");
    if (AlipayNotify.verify(params))
    {
      if (!trade_status.equals("TRADE_FINISHED")) {
        trade_status.equals("TRADE_SUCCESS");
      }
      System.out.println("success");
      Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
    }
    else
    {
      Struts2Utils.render("text/html", "fail", new String[] { "encoding:UTF-8" });
      System.out.println("fail");
    }
    return null;
  }
  
  public String index()
  {
    integral = DateUtil.DateTimeToStr(new Date());
    return "index";
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
}

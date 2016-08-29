package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.tenpay.util.TenpayUtil;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.yeepay.config.PaymentForOnlineService;

public class CartPayAction extends BaseAction
{
  private static final long serialVersionUID = 2516892117051908997L;
  @Autowired
  @Qualifier("spellbuyproductService")
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
  RandomUtil randomUtil = new RandomUtil();
  private String currTime = TenpayUtil.getCurrTime();
  private String strTime = currTime.substring(8, currTime.length());
  private String strRandom = TenpayUtil.buildRandom(4)+"";
  private String strReq = strTime + strRandom;
  private String out_trade_no = strReq;
  private String paymentStatus;
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
  private static String nodeAuthorizationURL = "https://www.yeepay.com/app-merchant-proxy/node";
  Random random = new Random();
  Calendar calendar = Calendar.getInstance();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String index()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    productCartList = new ArrayList();
    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("buyProduct"))
        {
          new StringUtil();String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
          if ((product != null) && (!product.equals(""))) {
            array = JSONArray.fromObject(product);
          }
        }
      }
    }
    Integer productCount = Integer.valueOf(0);
    if ((array != null) && (!array.toString().equals("[{}]"))) {
      for (int i = 0; i < array.size(); i++) {
        try
        {
          JSONObject obj = (JSONObject)array.get(i);
          productCart = new ProductCart();
          this.product = ((Product)productService.findById(obj.getString("pId")));
          productCount = Integer.valueOf(productCount.intValue() + 1);
          productCart.setMoneyCount(Integer.valueOf(Integer.parseInt(obj.getString("num"))));
          productCart.setHeadImage(this.product.getHeadImage());
          productCart.setProductCount(productCount);
          productCart.setProductId(this.product.getProductId());
          productCart.setProductName(this.product.getProductName());
          productCart.setProductPrice(this.product.getProductPrice());
          productCart.setProductTitle(this.product.getProductTitle());
          productCartList.add(productCart);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "index";
  }
  
  public String payment()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    productCartList = new ArrayList();
    JSONArray array = null;
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          userId = cookie.getValue();
          if ((userId != null) && (!userId.equals(""))) {
            user = ((User)userService.findById(userId));
          }
        }
        if (cookie.getName().equals("buyProduct"))
        {
          new StringUtil();String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
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
        try
        {
          JSONObject obj = (JSONObject)array.get(i);
          productCart = new ProductCart();
          this.product = ((Product)productService.findById(obj.getString("pId")));
          moneyCount = Integer.valueOf(moneyCount.intValue() + Integer.parseInt(obj.getString("num")));
          productCount = Integer.valueOf(productCount.intValue() + 1);
          productCart.setMoneyCount(moneyCount);
          productCart.setHeadImage(this.product.getHeadImage());
          productCart.setProductCount(productCount);
          productCart.setProductId(this.product.getProductId());
          productCart.setProductName(this.product.getProductName());
          productCart.setProductPrice(this.product.getProductPrice());
          productCart.setProductTitle(this.product.getProductTitle());
          productCartList.add(productCart);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "payment";
  }
  
  String formatString(String text)
  {
    if (text == null) {
      return "";
    }
    return text;
  }
  
  public String goPay()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    boolean flag = false;
    String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();
    
    String p0_Cmd = formatString("Buy");
    String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();
    String p2_Order = strReq;
    String p3_Amt = moneyCount.toString();
    String p4_Cur = formatString("CNY");
    String p5_Pid = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();
    String p6_Pcat = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();
    String p7_Pdesc = ApplicationListenerImpl.sysConfigureJson.getWwwUrl();
    String p8_Url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/notifyUrl.action";
    String p9_SAF = "0";
    String pa_MP = integral;
    String pd_FrpId = "";
    
    pd_FrpId = pd_FrpId.toUpperCase();
    String pr_NeedResponse = formatString("1");
    String hmac = formatString("");
    

    hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd, 
      p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, 
      p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
    

    Map<String, String> sParaTemp = new HashMap();
    sParaTemp.put("p0_Cmd", p0_Cmd);
    sParaTemp.put("p1_MerId", p1_MerId);
    sParaTemp.put("p2_Order", p2_Order);
    sParaTemp.put("p3_Amt", p3_Amt);
    sParaTemp.put("p4_Cur", p4_Cur);
    sParaTemp.put("p5_Pid", p5_Pid);
    sParaTemp.put("p6_Pcat", p6_Pcat);
    sParaTemp.put("p7_Pdesc", p7_Pdesc);
    sParaTemp.put("p8_Url", p8_Url);
    sParaTemp.put("p9_SAF", p9_SAF);
    sParaTemp.put("pa_MP", pa_MP);
    sParaTemp.put("pd_FrpId", pd_FrpId);
    sParaTemp.put("pr_NeedResponse", pr_NeedResponse);
    sParaTemp.put("hmac", hmac);
    



    productCartList = new ArrayList();
    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
        if (cookie.getName().equals("buyProduct"))
        {
          new StringUtil();String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
          if ((product != null) && (!product.equals(""))) {
            array = JSONArray.fromObject(product);
          }
        }
      }
    }
    if (StringUtil.isNotBlank(userId))
    {
      Integer buyConut = Integer.valueOf(0);
      Integer productCount = Integer.valueOf(0);
      if ((array != null) && (!array.toString().equals("[{}]"))) {
        for (int i = 0; i < array.size(); i++) {
          try
          {
            JSONObject obj = (JSONObject)array.get(i);
            productCart = new ProductCart();
            this.product = ((Product)productService.findById(obj.getString("pId")));
            moneyCount = Integer.valueOf(moneyCount.intValue() + Integer.parseInt(obj.getString("num")));
            productCount = Integer.valueOf(productCount.intValue() + 1);
            productCart.setMoneyCount(moneyCount);
            productCart.setHeadImage(this.product.getHeadImage());
            productCart.setProductCount(productCount);
            productCart.setProductId(this.product.getProductId());
            productCart.setProductName(this.product.getProductName());
            productCart.setProductPrice(this.product.getProductPrice());
            productCart.setProductTitle(this.product.getProductTitle());
            productCartList.add(productCart);
            flag = true;
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
      if (flag)
      {
        try
        {
          consumetable = new Consumetable();
          double money = Double.parseDouble(String.valueOf(moneyCount));
          consumetable.setBuyCount(buyConut);
          consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
          consumetable.setInterfaceType("yeePay");
          consumetable.setMoney(Double.valueOf(money));
          consumetable.setOutTradeNo(p2_Order);
          consumetable.setUserId(Integer.valueOf(Integer.parseInt(userId)));
          consumetableService.add(consumetable);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          flag = false;
        }
      }
      else
      {
        flag = false;
        Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
      }
    }
    String sHtmlText = buildRequest(sParaTemp, nodeAuthorizationURL, "POST", "确认");
    System.out.println(sHtmlText);
    if (flag) {
      Struts2Utils.render("text/html", sHtmlText, new String[] { "encoding:UTF-8" });
    } else {
      Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请该商品的选择下一期！\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
    }
    return null;
  }
  
  public String returnUrl()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();
    String r0_Cmd = formatString(request.getParameter("r0_Cmd"));
    String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();
    String r1_Code = formatString(request.getParameter("r1_Code"));
    String r2_TrxId = formatString(request.getParameter("r2_TrxId"));
    String r3_Amt = formatString(request.getParameter("r3_Amt"));
    String r4_Cur = formatString(request.getParameter("r4_Cur"));
    String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");
    String r6_Order = formatString(request.getParameter("r6_Order"));
    String r7_Uid = formatString(request.getParameter("r7_Uid"));
    String integral = new String(formatString(request.getParameter("integral")).getBytes("iso-8859-1"), "gbk");
    String r9_BType = formatString(request.getParameter("r9_BType"));
    String hmac = formatString(request.getParameter("hmac"));
    
    boolean flag = false;
    
    boolean isOK = false;
    
    isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, 
      r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
    if (isOK)
    {
      if (r1_Code.equals("1"))
      {
        if (r9_BType.equals("1")) {
          System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
        } else if (r9_BType.equals("2")) {
          System.out.println("SUCCESS");
        }
        System.out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
        try
        {
          String key = MD5Util.encode(r2_TrxId);
          if (AliyunOcsSampleHelp.getIMemcachedCache().get(key) != null) {
        	 return "success";
          }
          AliyunOcsSampleHelp.getIMemcachedCache().set(key, 43200, "y");
          productCartList = new ArrayList();
          successCartList = new ArrayList();
          try
          {
            consumetable = consumetableService.findByOutTradeNo(r6_Order);
            double money = Double.parseDouble(String.valueOf(r3_Amt));
            System.err.println(consumetable.getMoney());
            System.err.println(money);
            if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId().equals(r2_TrxId)))
            {
              Cookie[] cookies = request.getCookies();
              JSONArray array = null;
              if (request.isRequestedSessionIdFromCookie()) {
                for (int i = 0; i < cookies.length; i++)
                {
                  Cookie cookie = cookies[i];
                  if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                  }
                  if (cookie.getName().equals("buyProduct"))
                  {
                    new StringUtil();String product = StringUtil.getUTF8URLDecoder(cookie.getValue());
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
                  try
                  {
                    JSONObject obj = (JSONObject)array.get(i);
                    this.productCart = new ProductCart();
                    this.product = ((Product)productService.findById(obj.getString("pId")));
                    moneyCount = Integer.valueOf(moneyCount.intValue() + Integer.parseInt(obj.getString("num")));
                    productCount = Integer.valueOf(productCount.intValue() + 1);
                    this.productCart.setMoneyCount(moneyCount);
                    this.productCart.setHeadImage(this.product.getHeadImage());
                    this.productCart.setProductCount(productCount);
                    this.productCart.setProductId(this.product.getProductId());
                    this.productCart.setProductName(this.product.getProductName());
                    this.productCart.setProductPrice(this.product.getProductPrice());
                    this.productCart.setProductTitle(this.product.getProductTitle());
                    productCartList.add(this.productCart);
                  }
                  catch (Exception e)
                  {
                    e.printStackTrace();
                  }
                }
              }
              if (StringUtil.isNotBlank(userId)) {
                user = ((User)userService.findById(userId));
              }
              if (flag) {
                for (ProductCart productCart : productCartList) {
                  try
                  {
                    Integer count = productCart.getProductPrice();
                    if (count.intValue() > 0)
                    {
                      if ((StringUtil.isNotBlank(integral)) && (!integral.equals("0")))
                      {
                        if (user.getBalance().doubleValue() >= count.intValue() - Integer.parseInt(integral) / 100)
                        {
                          Double temp = Double.valueOf(user.getBalance().doubleValue() - (count.intValue() - Integer.parseInt(integral) / 100));
                          user.setBalance(temp);
                          

                          consumerdetail = new Consumerdetail();
                          consumerdetail.setBuyCount(count);
                          consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                          consumerdetail.setConsumetableId(r6_Order);
                          consumerdetail.setProductId(productCart.getProductId());
                          consumerdetail.setProductName(productCart.getProductName());
                          consumerdetail.setProductTitle(productCart.getProductTitle());
                          consumerdetailService.add(consumerdetail);
                          if (user.getInvite() != null)
                          {
                            User userCommission = (User)userService.findById(String.valueOf(user.getInvite()));
                            double tempCommissionCount = userCommission.getCommissionCount().doubleValue();
                            double commissionBalance = user.getCommissionBalance().doubleValue();
                            userCommission.setCommissionCount(Double.valueOf(tempCommissionCount += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                            userCommission.setCommissionBalance(Double.valueOf(commissionBalance += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                            userService.add(userCommission);
                            commissionquery = new Commissionquery();
                            commissionquery.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                            commissionquery.setCommission(Double.valueOf(Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                            commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                            commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                            commissionquery.setInvitedId(user.getInvite());
                            commissionquery.setToUserId(user.getUserId());
                            commissionqueryService.add(commissionquery);
                          }
                        }
                        else
                        {
                          Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
                        }
                      }
                      else if (user.getBalance().doubleValue() >= count.intValue())
                      {
                        Double temp = Double.valueOf(user.getBalance().doubleValue() - count.intValue());
                        user.setBalance(temp);
                        
                        consumerdetail = new Consumerdetail();
                        consumerdetail.setBuyCount(count);
                        consumerdetail.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                        consumerdetail.setConsumetableId(r6_Order);
                        consumerdetail.setProductId(productCart.getProductId());
                        consumerdetail.setProductName(productCart.getProductName());
                        consumerdetail.setProductTitle(productCart.getProductTitle());
                        consumerdetailService.add(consumerdetail);
                        if (user.getInvite() != null)
                        {
                          User userCommission = (User)userService.findById(String.valueOf(user.getInvite()));
                          double tempCommissionCount = userCommission.getCommissionCount().doubleValue();
                          double commissionBalance = user.getCommissionBalance().doubleValue();
                          userCommission.setCommissionCount(Double.valueOf(tempCommissionCount += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                          userCommission.setCommissionBalance(Double.valueOf(commissionBalance += Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                          userService.add(userCommission);
                          commissionquery = new Commissionquery();
                          commissionquery.setBuyMoney(Double.valueOf(Double.parseDouble(String.valueOf(count))));
                          commissionquery.setCommission(Double.valueOf(Double.parseDouble(String.valueOf(count)) * ApplicationListenerImpl.sysConfigureJson.getCommission().doubleValue()));
                          commissionquery.setDate(DateUtil.DateTimeToStr(new Date()));
                          commissionquery.setDescription(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + productCart.getProductId() + ")获得佣金");
                          commissionquery.setInvitedId(user.getInvite());
                          commissionquery.setToUserId(user.getUserId());
                          commissionqueryService.add(commissionquery);
                        }
                      }
                      else
                      {
                        Struts2Utils.render("text/html", "<script>alert(\"您的余额不足，无法完成支付\");window.location.href=\"/mycart/index.html\";</script>", new String[] { "encoding:UTF-8" });
                      }
                      if ((StringUtil.isNotBlank(integral)) && (!integral.equals("0")))
                      {
                        commissionpoints = new Commissionpoints();
                        commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                        commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")积分抵扣");
                        commissionpoints.setPay("-" + integral);
                        commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                        commissionpointsService.add(commissionpoints);
                        Integer points = user.getCommissionPoints();
                        user.setCommissionPoints(Integer.valueOf(points.intValue() - Integer.parseInt(integral)));
                      }
                      commissionpoints = new Commissionpoints();
                      commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                      commissionpoints.setDetailed(ApplicationListenerImpl.sysConfigureJson.getShortName() + "商品编码(" + spellbuyproduct.getSpellbuyProductId() + ")支付" + count + "元获得积分");
                      commissionpoints.setPay("+" + (count.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue() - Integer.parseInt(integral) / 100));
                      commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                      commissionpointsService.add(commissionpoints);
                      
                      Integer points = user.getCommissionPoints();
                      user.setCommissionPoints(Integer.valueOf(points.intValue() + (count.intValue() * ApplicationListenerImpl.sysConfigureJson.getBuyProduct().intValue() - Integer.parseInt(integral) / 100)));
                      
                      Integer experience = user.getExperience();
                      experience = Integer.valueOf(experience.intValue() + count.intValue() * 10);
                      user.setExperience(experience);
                      userService.add(user);
                      
                      productJSON = new ProductJSON();
                      productJSON.setBuyDate(DateUtil.DateTimeToStr(new Date()));
                      productJSON.setProductId(productCart.getProductId());
                      productJSON.setProductName(productCart.getProductName());
                      productJSON.setProductTitle(productCart.getProductTitle());
                      productJSON.setBuyCount(count);
                      successCartList.add(productJSON);
                    }
                  }
                  catch (Exception e)
                  {
                    e.printStackTrace();
                    flag = false;
                  }
                }
              }
              if (flag) {
                if (request.isRequestedSessionIdFromCookie())
                {
                  Cookie cookie = new Cookie("buyProduct", null);
                  cookie.setMaxAge(0);
                  cookie.setPath("/");
                  cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie);
                }
              }
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
          System.out.println("即时到帐付款成功");
          paymentStatus = "success";
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    else
    {
      paymentStatus = "error";
      System.out.println("交易签名被篡改!");
    }
    return "success";
  }
  
  public String notifyUrl()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    boolean flag = false;
    String keyValue = ApplicationListenerImpl.sysConfigureJson.getYeepayKey();
    String r0_Cmd = formatString(request.getParameter("r0_Cmd"));
    String p1_MerId = ApplicationListenerImpl.sysConfigureJson.getYeepayPartner();
    String r1_Code = formatString(request.getParameter("r1_Code"));
    String r2_TrxId = formatString(request.getParameter("r2_TrxId"));
    String r3_Amt = formatString(request.getParameter("r3_Amt"));
    String r4_Cur = formatString(request.getParameter("r4_Cur"));
    String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");
    String r6_Order = formatString(request.getParameter("r6_Order"));
    String r7_Uid = formatString(request.getParameter("r7_Uid"));
    String integral = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");
    String r9_BType = formatString(request.getParameter("r9_BType"));
    String hmac = formatString(request.getParameter("hmac"));
    

    Map<String, String> sParaTemp = new HashMap();
    sParaTemp.put("r0_Cmd", r0_Cmd);
    sParaTemp.put("p1_MerId", p1_MerId);
    sParaTemp.put("r1_Code", r1_Code);
    sParaTemp.put("r2_TrxId", r2_TrxId);
    sParaTemp.put("r3_Amt", r3_Amt);
    sParaTemp.put("r4_Cur", r4_Cur);
    sParaTemp.put("r5_Pid", r5_Pid);
    sParaTemp.put("r6_Order", r6_Order);
    sParaTemp.put("r7_Uid", r7_Uid);
    sParaTemp.put("integral", integral);
    sParaTemp.put("r9_BType", r9_BType);
    sParaTemp.put("hmac", hmac);
    

    boolean isOK = false;
    
    isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, 
      r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, integral, r9_BType, keyValue);
    if (isOK)
    {
      if (r1_Code.equals("1"))
      {
        if (r9_BType.equals("1"))
        {
          System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
        }
        else if (r9_BType.equals("2"))
        {
          System.out.println("SUCCESS");
          Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
        }
        System.out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
        try
        {
          consumetable = consumetableService.findByOutTradeNo(r6_Order);
          double money = Double.parseDouble(String.valueOf(r3_Amt));
          System.err.println(consumetable.getMoney());
          System.err.println(money);
          if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId() == null))
          {
            consumetable.setTransactionId(r2_TrxId);
            consumetableService.add(consumetable);
            
            user = ((User)userService.findById(String.valueOf(consumetable.getUserId())));
            user.setBalance(Double.valueOf(money + user.getBalance().doubleValue()));
            userService.add(user);
            flag = true;
          }
        }
        catch (Exception e)
        {
          flag = false;
          e.printStackTrace();
        }
        String sHtmlText = buildRequest(sParaTemp, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/yeepay/returnUrl.html", "POST", "确认");
        System.out.println(sHtmlText);
        Struts2Utils.render("text/html", sHtmlText, new String[] { "encoding:UTF-8" });
        System.out.println("success");
        Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
      }
    }
    else {
      System.out.println("交易签名被篡改!");
    }
    return null;
  }
  
  public static String buildRequest(Map<String, String> sParaTemp, String postUrl, String strMethod, String strButtonName)
  {
    Map<String, String> sPara = sParaTemp;
    List<String> keys = new ArrayList((Collection)sPara.keySet());
    
    StringBuffer sbHtml = new StringBuffer();
    
    sbHtml.append("<form id=\"yeepaysubmit\" name=\"yeepaysubmit\" action=\"" + postUrl + "\" method=\"" + strMethod + "\">");
    for (int i = 0; i < keys.size(); i++)
    {
      String name = (String)keys.get(i);
      String value = (String)sPara.get(name);
      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
    }
    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
    sbHtml.append("<script>document.forms['yeepaysubmit'].submit();</script>");
    
    return sbHtml.toString();
  }
    
  public String getCurrTime()
  {
    return currTime;
  }
  
  public void setCurrTime(String currTime)
  {
    this.currTime = currTime;
  }
  
  public String getStrTime()
  {
    return strTime;
  }
  
  public void setStrTime(String strTime)
  {
    this.strTime = strTime;
  }
  
  public String getStrRandom()
  {
    return strRandom;
  }
  
  public void setStrRandom(String strRandom)
  {
    this.strRandom = strRandom;
  }
  
  public String getStrReq()
  {
    return strReq;
  }
  
  public void setStrReq(String strReq)
  {
    this.strReq = strReq;
  }
  
  public String getOut_trade_no()
  {
    return out_trade_no;
  }
  
  public void setOut_trade_no(String out_trade_no)
  {
    this.out_trade_no = out_trade_no;
  }
  
  public Consumetable getConsumetable()
  {
    return consumetable;
  }
  
  public void setConsumetable(Consumetable consumetable)
  {
    this.consumetable = consumetable;
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
  
  public ProductCart getProductCart()
  {
    return productCart;
  }
  
  public void setProductCart(ProductCart productCart)
  {
    this.productCart = productCart;
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
  
  public Commissionquery getCommissionquery()
  {
    return commissionquery;
  }
  
  public void setCommissionquery(Commissionquery commissionquery)
  {
    this.commissionquery = commissionquery;
  }
  
  public Lotteryproductutil getLotteryproductutil()
  {
    return lotteryproductutil;
  }
  
  public void setLotteryproductutil(Lotteryproductutil lotteryproductutil)
  {
    this.lotteryproductutil = lotteryproductutil;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
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
  
  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
  }
  
  public Integer getUserPayType()
  {
    return userPayType;
  }
  
  public void setUserPayType(Integer userPayType)
  {
    this.userPayType = userPayType;
  }
  
  public String getIntegral()
  {
    return integral;
  }
  
  public void setIntegral(String integral)
  {
    this.integral = integral;
  }
  
  public String getHidUseBalance()
  {
    return hidUseBalance;
  }
  
  public void setHidUseBalance(String hidUseBalance)
  {
    this.hidUseBalance = hidUseBalance;
  }
  
  public String getPaymentStatus()
  {
    return paymentStatus;
  }
  
  public void setPaymentStatus(String paymentStatus)
  {
    this.paymentStatus = paymentStatus;
  }
}

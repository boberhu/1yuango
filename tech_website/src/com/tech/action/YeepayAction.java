package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.tech.util.RandomUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.yeepay.config.PaymentForOnlineService;

public class YeepayAction extends BaseAction
{
  private static final long serialVersionUID = -4161171772234804569L;
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
  private Integer moneyCount;
  private String integral;
  private String currTime = TenpayUtil.getCurrTime();
  private String strTime = currTime.substring(8, currTime.length());
  private String strRandom = TenpayUtil.buildRandom(4)+"";
  private String strReq = strTime + strRandom;
  private static String nodeAuthorizationURL = "https://www.yeepay.com/app-merchant-proxy/node";
  Random random = new Random();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
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
    

    String sHtmlText = buildRequest(sParaTemp, nodeAuthorizationURL, "POST", "确认");
    Struts2Utils.render("text/html", sHtmlText, new String[] { "encoding:UTF-8" });
    
    return null;
  }
  
  public String returnUrl()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
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
        

        String transaction_id = r2_TrxId;
        String out_trade_no = r6_Order;
        try
        {
          if (AliyunOcsSampleHelp.getIMemcachedCache().get(transaction_id) == null)
          {
            productCartList = new ArrayList();
            successCartList = new ArrayList();
            try
            {
              consumetable = consumetableService.findByOutTradeNo(out_trade_no);
              double money = Double.parseDouble(String.valueOf(r3_Amt));
              money *= 0.01D;
              if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId().equals(transaction_id)))
              {
                JSONArray payInfo2 = (JSONArray)AliyunOcsSampleHelp.getIMemcachedCache().get("doPay" + out_trade_no);
                JSONObject object = payInfo2.getJSONObject(0);
                int userPayType = object.getInt("userPayType");
                userId = object.getString("userId");
                moneyCount = Integer.valueOf(object.getInt("moneyCount"));
                integral = object.getString("integral");
                int bankMoney = object.getInt("bankMoney");
                String hidUseBalance = object.getString("hidUseBalance");
                out_trade_no = object.getString("out_trade_no");
                productCartList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("doPayList" + out_trade_no));
                
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
                  Struts2Utils.render("text/html", "<script>window.location.href=\"/mycart/shopok.html?id=" + out_trade_no + "\";</script>", new String[] { "encoding:UTF-8" });
                }
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
            System.out.println("即时到帐付款成功");
            AliyunOcsSampleHelp.getIMemcachedCache().set(transaction_id, 7200, "y");
          }
          else
          {
            Struts2Utils.render("text/html", "<script>window.location.href=\"/mycart/shopok.html?id=" + out_trade_no + "\";</script>", new String[] { "encoding:UTF-8" });
          }
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
                
                List<Randomnumber> RandomnumberList = randomnumberService
                	.query(" from Randomnumber where productId='" + spellbuyproduct.getSpellbuyProductId() + "'");
                List<String> oldRandomList = new ArrayList();
                for (Randomnumber randomnumber1 : RandomnumberList) {
                  if (randomnumber1.getRandomNumber().contains(","))
                  {
                    String[] rs = randomnumber1.getRandomNumber().split(",");
                    for (String string : rs) {
                      oldRandomList.add(string);
                    }
                  }
                  else
                  {
                    oldRandomList.add(randomnumber1.getRandomNumber());
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
  
  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
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
}

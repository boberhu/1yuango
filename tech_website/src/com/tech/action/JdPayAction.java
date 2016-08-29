package com.tech.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.wangyin.wepaypc.client.SignHelper;
import com.wangyin.wepaypc.model.BasePayOrderInfo;

@Component("JdPayAction")
public class JdPayAction extends BaseAction
{
  private static final long serialVersionUID = -5292311449365282821L;
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
  private String currTime = TenpayUtil.getCurrTime();
  private String strTime = currTime.substring(8, currTime.length());
  private String strRandom = TenpayUtil.buildRandom(4)+"";
  private String strReq = strTime + strRandom;
  private String out_trade_no = strReq;
  private Integer moneyCount;
  private String productBody = "";
  private String productName;
  private String bank_type;
  private String hidUseBalance;
  private String integral;
  private String requestUrl;
  private String payBank;
  private String token;
  Random random = new Random();
  Calendar calendar = Calendar.getInstance();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String goPay()
    throws Exception
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    
    String version = "1.1";
    String tokens = token;
    String merchantRemark = "备注";
    String tradeNum = out_trade_no;
    String tradeName = ApplicationListenerImpl.sysConfigureJson.getSaitName();
    
    String tradeTime = DateUtil.DateTimeToStr(new Date());
    String tradeAmount = String.valueOf(moneyCount);
    String currency = "CNY";
    String successCallbackUrl = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/jdpay/returnUrl.action";
    String forPayLayerUrl = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/jdpay/forPayLayerUrl.action";
    String notifyUrl = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/jdpay/notifyUrl.action";
    String ip = request.getHeader("X-Real-IP");
    String merchantNum = "22294531";
    

    BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
    basePayOrderInfo.setToken(token);
    basePayOrderInfo.setMerchantNum(merchantNum);
    basePayOrderInfo.setMerchantRemark(merchantRemark);
    basePayOrderInfo.setTradeNum(tradeNum);
    basePayOrderInfo.setTradeName(tradeName);
    
    basePayOrderInfo.setTradeTime(tradeTime);
    basePayOrderInfo.setTradeAmount(tradeAmount);
    basePayOrderInfo.setCurrency(currency);
    basePayOrderInfo.setNotifyUrl(notifyUrl);
    basePayOrderInfo.setSuccessCallbackUrl(successCallbackUrl);
    basePayOrderInfo.setForPayLayerUrl(forPayLayerUrl);
    basePayOrderInfo.setVersion(version);
    basePayOrderInfo.setIp(ip);
    String serverUrl = "https://plus.jdpay.com";
    String oriUrl = serverUrl + "/pay.htm";
    String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALXf6twUqul1TATO+5nA66p2wjnRd+g96IXpfV6Sf8WXxwizGj+L19LQYRBXpZHmRh82prJ48d0FcHboCiN8pKutnuZrrKYhvORysOc5bVli0hcCn1TfYDoUWJ1UhjUQloqZKWjUz6LV9QY6bIZ1W4+Hmw6HK1bfFwUq0WzIGkJNAgMBAAECgYBlIFQeev9tP+M86TnMjBB9f/sO2wGpCIM5slIbO6n/3By3IZ7+pmsitOrDg3h0X22t/V1C7yzMkDGwa+T3Rl7ogwc4UNVj0ZQorOTx3OEPx3nP1yT3zmJ9djKaHKAmee4XmhQHdqqIuMT2XQaqatBzcsnP+Jnw/WVOsIJIqMeFAQJBAP9yq4hE+UfM/YSXZ5JR33k9RolUUq8S/elmeJIDo/3N2qDmzLjOr9iEZHxioc8JOxubtZ0BxA+NdfKz4v0BSpkCQQC2RIrAPRj9vOk6GfT9W1hbJ4GdnzTb+4vp3RDQQ3x9JGXzWFlg8xJT1rNgM8R95Gkxn3KGnYHJQTLlCsIy2FnVAkAWXolM3pVhxz6wHL4SHx9Ns6L4payz7hrUFIgcaTs0H5G0o2FsEZVuhXFzPwPiaHGHomQOAriTkBSzEzOeaj2JAkEAtYUFefZfETQ2QbrgFgIGuKFboJKRnhOif8G9oOvU6vx43CS8vqTVN9G2yrRDl+0GJnlZIV9zhe78tMZGKUT2EQJAHQawBKGlXlMe49Fo24yOy5DvKeohobjYqzJAtbqaAH7iIQTpOZx91zUcL/yG4dWS6r+wGO7Z1RKpupOJLKG3lA==";
    String url = SignHelper.getUrl(basePayOrderInfo, oriUrl, priKey);
    
    response.sendRedirect(url);
    
    return null;
  }
  
  public String forPayLayerUrl()
  {
    return "forPayLayerUrl";
  }
  
  public String returnUrl()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
    

    String v_oid = request.getParameter("v_oid");
    String v_pmode = request.getParameter("v_pmode");
    String v_pstatus = request.getParameter("v_pstatus");
    String v_pstring = request.getParameter("v_pstring");
    String v_amount = request.getParameter("v_amount");
    String v_moneytype = request.getParameter("v_moneytype");
    String v_md5str = request.getParameter("v_md5str");
    String remark1 = request.getParameter("remark1");
    String remark2 = request.getParameter("remark2");
    

    String text = v_oid + v_pstatus + v_amount + v_moneytype + ApplicationListenerImpl.sysConfigureJson.getChinabankKey();
    String v_md5text = MD5Util.encode(text);
    System.err.println("v_md5str:******" + v_md5str);
    System.err.println("v_md5text:*****" + v_md5text);
    if (v_md5str.equals(v_md5text))
    {
      if ("30".equals(v_pstatus))
      {
        System.err.println("支付失败");
      }
      else if ("20".equals(v_pstatus))
      {
        out_trade_no = v_oid;
        try
        {
          if (AliyunOcsSampleHelp.getIMemcachedCache().get(v_md5str) == null)
          {
            productCartList = new ArrayList();
            successCartList = new ArrayList();
            try
            {
              consumetable = consumetableService.findByOutTradeNo(out_trade_no);
              double money = Double.parseDouble(String.valueOf(v_amount));
              if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId().equals(v_md5str)))
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
            AliyunOcsSampleHelp.getIMemcachedCache().set(v_md5str, 7200, "y");
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
    else {
      System.err.println("校验失败,数据可疑");
    }
    return "success";
  }
  
  public String notifyUrl()
    throws Exception
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    
    String v_oid = request.getParameter("v_oid");
    String v_pmode = request.getParameter("v_pmode");
    String v_pstatus = request.getParameter("v_pstatus");
    String v_pstring = request.getParameter("v_pstring");
    String v_amount = request.getParameter("v_amount");
    String v_moneytype = request.getParameter("v_moneytype");
    String v_md5str = request.getParameter("v_md5str");
    String remark1 = request.getParameter("remark1");
    String remark2 = request.getParameter("remark2");
    
    String text = v_oid + v_pstatus + v_amount + v_moneytype + ApplicationListenerImpl.sysConfigureJson.getChinabankKey();
    String v_md5text = MD5Util.encode(text);
    System.err.println("v_md5str:" + v_md5str);
    System.err.println("v_md5text:" + v_md5text);
    if (v_md5str.equals(v_md5text))
    {
      Struts2Utils.renderText("ok", new String[0]);
      if ("20".equals(v_pstatus)) {
        try
        {
          consumetable = consumetableService.findByOutTradeNo(v_oid);
          double money = Double.parseDouble(String.valueOf(v_amount));
          System.err.println(consumetable.getMoney());
          System.err.println(money);
          if ((!consumetable.getMoney().equals(Double.valueOf(money))) || (consumetable.getTransactionId() != null)) {
        	  return null;
          }
          consumetable.setTransactionId(v_md5str);
          consumetableService.add(consumetable);
          
          user = ((User)userService.findById(String.valueOf(consumetable.getUserId())));
          user.setBalance(Double.valueOf(money + user.getBalance().doubleValue()));
          userService.add(user);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    else
    {
      Struts2Utils.renderText("error", new String[0]);
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
  
  public Lotteryproductutil getLotteryproductutil()
  {
    return lotteryproductutil;
  }
  
  public void setLotteryproductutil(Lotteryproductutil lotteryproductutil)
  {
    this.lotteryproductutil = lotteryproductutil;
  }
  
  public String getPayBank()
  {
    return payBank;
  }
  
  public void setPayBank(String payBank)
  {
    this.payBank = payBank;
  }
  
  public String getToken()
  {
    return token;
  }
  
  public void setToken(String token)
  {
    this.token = token;
  }
}

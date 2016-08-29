package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.alipay.config.AlipayConfig;
import com.tech.alipay.util.AlipayNotify;
import com.tech.alipay.util.AlipaySubmit;
import com.tech.pojo.Consumetable;
import com.tech.pojo.User;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.UserService;
import com.tech.tenpay.util.TenpayUtil;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("AlipayBalanceAction")
public class AlipayBalanceAction extends BaseAction
{
  private static final long serialVersionUID = 2055647485775038604L;
  @Autowired
  ConsumetableService consumetableService;
  @Autowired
  ConsumerdetailService consumerdetailService;
  @Autowired
  private UserService userService;
  private User user;
  private String userId;
  private Consumetable consumetable;
  private Integer moneyCount;
  private String hidUseBalance;
  private String paymentStatus;
  private String tradeNo;
  private String money;
  private String title;
  private String memo;
  private String alipay_account;
  private String tenpay_account;
  private String gateway;
  private String sign;
  private String currTime = TenpayUtil.getCurrTime();
  private String strTime = currTime.substring(8, currTime.length());
  private String strRandom = TenpayUtil.buildRandom(4)+"";
  private String strReq = strTime + strRandom;
  Random random = new Random();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String index()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    
    boolean flag = false;
    
    String payment_type = "1";
    


    String notify_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/alipayBalance/notifyUrl.action";
    


    String return_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/alipayBalance/returnUrl.action";
    

    String seller_email = AlipayConfig.mail;
    

    String out_trade_no = strReq;
    

    String subject = "商品购买";
    

    String total_fee = moneyCount.toString();
    

    String body = "商品购买";
    
    String show_url = "https://www.alipay.com/?src=alipay.com";
    

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
    

    Cookie[] cookies = request.getCookies();
    JSONArray array = null;
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    try
    {
      consumetable = new Consumetable();
      int buyConut = moneyCount.intValue();
      if ((hidUseBalance != null) && (!hidUseBalance.equals(""))) {
        buyConut += Integer.parseInt(hidUseBalance);
      }
      double money = Double.parseDouble(String.valueOf(moneyCount));
      consumetable.setBuyCount(Integer.valueOf(buyConut));
      consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
      consumetable.setInterfaceType("aliPay");
      consumetable.setMoney(Double.valueOf(money));
      consumetable.setOutTradeNo(out_trade_no);
      consumetable.setUserId(Integer.valueOf(Integer.parseInt(userId)));
      consumetableService.add(consumetable);
      








      flag = true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      flag = false;
    }
    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    System.out.println(sHtmlText);
    if (flag) {
      Struts2Utils.render("text/html", sHtmlText, new String[] { "encoding:UTF-8" });
    } else {
      Struts2Utils.render("text/html", "<script>alert(\"充值失败，请联系管理员！\");window.location.href=\"/index.html\";</script>", new String[] { "encoding:UTF-8" });
    }
    return null;
  }
  
  public String returnUrl()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    String buyproduct = "";
    
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
    


    boolean verify_result = AlipayNotify.verify(params);
    if (verify_result)
    {
      if ((trade_status.equals("TRADE_FINISHED")) || (trade_status.equals("TRADE_SUCCESS"))) {
        try
        {
          try
          {
            consumetable = consumetableService.findByOutTradeNo(out_trade_no);
            double money = Double.parseDouble(String.valueOf(total_fee));
            System.err.println(consumetable.getMoney());
            System.err.println(money);
            if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId().equals(trade_no))) {
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
    if (AlipayNotify.verify(params))
    {
      if (!trade_status.equals("TRADE_FINISHED")) {
        if (trade_status.equals("TRADE_SUCCESS")) {
          try
          {
            consumetable = consumetableService.findByOutTradeNo(out_trade_no);
            double money = Double.parseDouble(String.valueOf(total_fee));
            System.err.println(consumetable.getMoney());
            System.err.println(money);
            if ((consumetable.getMoney().equals(Double.valueOf(money))) && (consumetable.getTransactionId() == null))
            {
              consumetable.setTransactionId(trade_no);
              consumetableService.add(consumetable);
              userId = String.valueOf(consumetable.getUserId());
              if ((userId != null) && (!userId.equals(""))) {
                try
                {
                  String key = MD5Util.encode(trade_no);
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
            }
            System.out.println("success");
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
      Struts2Utils.render("text/html", "success", new String[] { "encoding:UTF-8" });
    }
    else
    {
      Struts2Utils.render("text/html", "fail", new String[] { "encoding:UTF-8" });
      System.out.println("fail");
    }
    return null;
  }
  
  public String noKeyRecharge()
  {
    String str = ApplicationListenerImpl.sysConfigureJson.getAliPaySignId() + ApplicationListenerImpl.sysConfigureJson.getAliPaySignKey() + tradeNo + money + title + memo;
    
    System.err.println(str);
    String md5sign = MD5Util.encode(str);
    System.err.println("md5sign:" + md5sign);
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
              System.err.println(consumetable.getMoney());
              System.err.println(m);
              if ((consumetable.getMoney().equals(Double.valueOf(m))) && (consumetable.getTransactionId() == null))
              {
                consumetable.setTransactionId(tradeNo);
                if (gateway.equals("alipay")) {
                  consumetable.setInterfaceType("aliPay");
                } else if (gateway.equals("tenpay")) {
                  consumetable.setInterfaceType("tenPay");
                }
                consumetableService.add(consumetable);
                
                user = ((User)userService.findById(String.valueOf(consumetable.getUserId())));
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
  
  public Integer getMoneyCount()
  {
    return moneyCount;
  }
  
  public void setMoneyCount(Integer moneyCount)
  {
    this.moneyCount = moneyCount;
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
}

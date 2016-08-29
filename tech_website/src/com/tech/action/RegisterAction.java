package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mteams.commons.encrypt.digest.MessageDigestUtils;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.User;
import com.tech.service.CommissionpointsService;
import com.tech.service.UserService;
import com.tech.sms.SmsSenderFactory;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Base64;
import com.tech.util.DateUtil;
import com.tech.util.EmailUtil;
import com.tech.util.HTMLFilter;
import com.tech.util.IPSeeker;
import com.tech.util.MD5Util;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("RegsiterAction")
public class RegisterAction extends BaseAction
{
  private static final long serialVersionUID = 5054777863371691520L;
  @Autowired
  @Qualifier("userService")
  private UserService userService;
  @Autowired
  CommissionpointsService commissionpointsService;
  private User user;
  private String phone;
  private String mail;
  private String userPwd;
  private String userName;
  private String forward;
  private String str;
  private String isVerify;
  private String key;
  private String date;
  private String openId;
  private String userFace;
  private Commissionpoints commissionpoints;
  public static IPSeeker seeker = new IPSeeker();
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  static HTMLFilter htmlFilter = new HTMLFilter();
  
  public String index()
  {
    /*if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元拍购开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.591jx.net\">www.591jx.net</a></div></body></html>", new String[0]);
      return null;
    }*/
    if (StringUtil.isNotBlank(forward)) {
      forward = htmlFilter.filter(forward);
    }
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          String userId = cookie.getValue();
          if ((userId != null) && (!userId.equals(""))) {
            return "index_index";
          }
        }
      }
    }
    return "index";
  }
  
  public String regsiter()
  {
    str = htmlFilter.filter(str);
    userPwd = htmlFilter.filter(userPwd);
    try {
		userPwd = URLDecoder.decode(userPwd, "UTF-8");
	} catch (UnsupportedEncodingException e1) {
		e1.printStackTrace();
	}
    if ((userPwd != null) && (!userPwd.equals("")))
    {
      str = (Base64.getEncode(str) + "*" + Base64.getEncode(userPwd));
      System.err.println(str);
      Struts2Utils.renderText(str, new String[0]);
    }
    else
    {
      Struts2Utils.renderText("false", new String[0]);
    }
    return null;
  }
  
  public String registercheck()
    throws Exception
  {
    isVerify = str;
    userName = str.split("\\*")[0];
    userName = Base64.getDecode(userName);
    if (userName.indexOf("@") != -1)
    {
      String[] u = userName.split("@");
      String u1 = u[0].substring(0, 2) + "***";
      key = (u1 + "@" + u[1]);
      date = "1";
    }
    else
    {
      key = (userName.substring(0, 4) + "***" + userName.substring(7));
      date = "0";
    }
    return "registercheck";
  }
  
  public void saveregsiter()
    throws Exception
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    Cookie[] cookies = request.getCookies();
    try
    {
      if (StringUtil.isNotBlank(isVerify))
      {
        if (AliyunOcsSampleHelp.getIMemcachedCache().get(isVerify.split("\\*")[0]).equals(key))
        {
          userName = isVerify.split("\\*")[0];
          userName = Base64.getDecode(userName);
          userPwd = isVerify.split("\\*")[1];
          userPwd = Base64.getDecode(userPwd);
          try {
      		userPwd = URLDecoder.decode(userPwd, "UTF-8");
      	  } catch (UnsupportedEncodingException e1) {
      		e1.printStackTrace();
      	  }
          String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
          if (ip == null) {
            ip = "127.0.0.1";
          }
          String date = DateUtil.DateTimeToStr(new Date());
          user = userService.userByName(userName);
          if (user == null)
          {
            user = new User();
            if (userName.indexOf("@") != -1) {
              try
              {
                user.setMail(userName);
                user.setMailCheck("0");
                user.setMobileCheck("3");
                if ((userPwd != null) && (!userPwd.equals(""))) {
                	userPwd = MessageDigestUtils.sha256Hex(userPwd);
                	user.setUserPwd(userPwd);
                }
                user.setIpAddress(ip);
                String ipLocation = seeker.getAddress(ip);
                user.setIpLocation(ipLocation);
                user.setOldDate(date);
                user.setNewDate(date);
                user.setBalance(ApplicationListenerImpl.sysConfigureJson.getRegBalance());
                user.setCommissionBalance(Double.valueOf(0.0D));
                user.setCommissionCount(Double.valueOf(0.0D));
                user.setCommissionMention(Double.valueOf(0.0D));
                user.setCommissionPoints(Integer.valueOf(0));
                user.setFaceImg("/Images/defaultUserFace.png");
                user.setUserType("0");
                user.setExperience(Integer.valueOf(0));
                if (request.isRequestedSessionIdFromCookie()) {
                  for (int i = 0; i < cookies.length; i++)
                  {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("inviteId"))
                    {
                      String inviteId = cookie.getValue();
                      if ((inviteId != null) && (!inviteId.equals("")))
                      {
                        user.setInvite(Integer.valueOf(Integer.parseInt(inviteId)));
                        User user2 = (User)userService.findById(inviteId);
                        int commissionPoints = user2.getCommissionPoints().intValue();
                        commissionPoints += ApplicationListenerImpl.sysConfigureJson.getInvite().intValue();
                        user2.setCommissionPoints(Integer.valueOf(commissionPoints));
                        userService.add(user2);
                        commissionpoints = new Commissionpoints();
                        commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                        commissionpoints.setDetailed("邀请好友获得" + ApplicationListenerImpl.sysConfigureJson.getInvite() + "积分");
                        commissionpoints.setPay("+" + ApplicationListenerImpl.sysConfigureJson.getBuyProduct());
                        commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(inviteId)));
                        commissionpointsService.add(commissionpoints);
                      }
                    }
                  }
                }
                userService.add(user);
                if (request.isRequestedSessionIdFromCookie())
                {
                  Cookie cookie = new Cookie("userName", user.getMail());
                  cookie.setMaxAge(-1);
                  cookie.setPath("/");
                  cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie);
                  Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
                  cookie2.setMaxAge(-1);
                  cookie2.setPath("/");
                  cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie2);
                  Cookie cookie3 = new Cookie("face", URLEncoder.encode(user.getFaceImg(), "UTF-8"));
                  cookie3.setMaxAge(-1);
                  cookie3.setPath("/");
                  cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie3);
                  Cookie cookie4 = new Cookie("loginUser", URLEncoder.encode(user.getMail(), "UTF-8"));
                  cookie4.setMaxAge(31536000);
                  cookie4.setPath("/");
                  cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie4);
                }
                Struts2Utils.renderText("0", new String[0]);
              }
              catch (Exception e)
              {
                e.printStackTrace();
                Struts2Utils.renderText("false", new String[0]);
              }
            } else {
              try
              {
                user.setPhone(userName);
                user.setMailCheck("3");
                user.setMobileCheck("0");
                if ((userPwd != null) && (!userPwd.equals(""))) {
                	userPwd = MessageDigestUtils.sha256Hex(userPwd);
                	user.setUserPwd(userPwd);
                }
                user.setIpAddress(ip);
                String ipLocation = seeker.getAddress(ip);
                user.setIpLocation(ipLocation);
                user.setOldDate(date);
                user.setNewDate(date);
                user.setBalance(ApplicationListenerImpl.sysConfigureJson.getRegBalance());
                user.setCommissionBalance(Double.valueOf(0.0D));
                user.setCommissionCount(Double.valueOf(0.0D));
                user.setCommissionMention(Double.valueOf(0.0D));
                user.setCommissionPoints(Integer.valueOf(0));
                user.setFaceImg("/Images/defaultUserFace.png");
                user.setUserType("0");
                user.setExperience(Integer.valueOf(0));
                if (request.isRequestedSessionIdFromCookie()) {
                  for (int i = 0; i < cookies.length; i++)
                  {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("inviteId"))
                    {
                      String inviteId = cookie.getValue();
                      if ((inviteId != null) && (!inviteId.equals("")))
                      {
                        user.setInvite(Integer.valueOf(Integer.parseInt(inviteId)));
                        User user2 = (User)userService.findById(inviteId);
                        int commissionPoints = user2.getCommissionPoints().intValue();
                        commissionPoints += ApplicationListenerImpl.sysConfigureJson.getInvite().intValue();
                        user2.setCommissionPoints(Integer.valueOf(commissionPoints));
                        userService.add(user2);
                        commissionpoints = new Commissionpoints();
                        commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                        commissionpoints.setDetailed("邀请好友获得" + ApplicationListenerImpl.sysConfigureJson.getInvite() + "积分");
                        commissionpoints.setPay("+" + ApplicationListenerImpl.sysConfigureJson.getBuyProduct());
                        commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(inviteId)));
                        commissionpointsService.add(commissionpoints);
                      }
                    }
                  }
                }
                userService.add(user);
                if (request.isRequestedSessionIdFromCookie())
                {
                  Cookie cookie = new Cookie("userName", user.getPhone());
                  cookie.setMaxAge(-1);
                  cookie.setPath("/");
                  cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie);
                  Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
                  cookie2.setMaxAge(-1);
                  cookie2.setPath("/");
                  cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie2);
                  Cookie cookie3 = new Cookie("face", URLEncoder.encode(user.getFaceImg(), "UTF-8"));
                  cookie3.setMaxAge(-1);
                  cookie3.setPath("/");
                  cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie3);
                  Cookie cookie4 = new Cookie("loginUser", URLEncoder.encode(user.getPhone(), "UTF-8"));
                  cookie4.setMaxAge(31536000);
                  cookie4.setPath("/");
                  cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                  response.addCookie(cookie4);
                }
                Struts2Utils.renderText("0", new String[0]);
              }
              catch (Exception e)
              {
                e.printStackTrace();
                Struts2Utils.renderText("false", new String[0]);
              }
            }
          }
          else
          {
            Struts2Utils.renderText("3", new String[0]);
          }
        }
        else
        {
          Struts2Utils.renderText("1", new String[0]);
        }
      }
      else {
        Struts2Utils.renderText("false", new String[0]);
      }
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public String registerok()
    throws Exception
  {
    if (StringUtil.isNotBlank(isVerify))
    {
      userName = isVerify.split("\\*")[0];
      userName = Base64.getDecode(userName);
    }
    return "registerok";
  }
  
  public String mobilecheck()
  {
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(str) == null)
    {
      Struts2Utils.render("text/html", "<script>alert(\"该手机号没有注册,请注册！\");window.location.href=\"/register/index.html\";</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    isVerify = Base64.getEncode(str);
    
    return "mobilecheck";
  }
  
  public void regSendMes()
    throws Exception
  {
    Random random = new Random();
    String ran = "";
    for (int i = 0; i < 6; i++) {
      ran = ran + random.nextInt(9);
    }
    String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
    if (ip == null) {
      ip = "127.0.0.1";
    }
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(Base64.getEncode(ip)) == null)
    {
      if (isVerify.split("\\*")[0].equals(Base64.getEncode(phone)))
      {
        if (AliyunOcsSampleHelp.getIMemcachedCache().get(Base64.getEncode(phone)) == null) {
          try
          {
        	String content = ApplicationListenerImpl.sysConfigureJson.getVerifyMsgTpl().replace("{000000}", ran);
        	boolean success = SmsSenderFactory.create().send(phone, content);
        	success = true;
        	if(success){
        		AliyunOcsSampleHelp.getIMemcachedCache().set(Base64.getEncode(phone), 120, ran);
                Struts2Utils.renderText("0", new String[0]);
        	}else{
        		Struts2Utils.renderText("false", new String[0]);
                return;
        	}
          }
          catch (Exception e)
          {
            e.printStackTrace();
            Struts2Utils.renderText("error", new String[0]);
          }
        } else {
          Struts2Utils.renderText("2", new String[0]);
        }
      }
      else {
        Struts2Utils.renderText("error", new String[0]);
      }
      AliyunOcsSampleHelp.getIMemcachedCache().set(Base64.getEncode(ip), 120, ip);
    }
    else
    {
      Struts2Utils.renderText("2", new String[0]);
    }
  }
  
  public void checkMobileCode()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    user = new User();
    
    String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
    if (ip == null) {
      ip = "127.0.0.1";
    }
    String date = DateUtil.DateTimeToStr(new Date());
    try
    {
      if (AliyunOcsSampleHelp.getIMemcachedCache().get(isVerify) != null) {
        if (AliyunOcsSampleHelp.getIMemcachedCache().get(isVerify).equals(key))
        {
          phone = Base64.getDecode(isVerify);
          user.setPhone(phone);
          user.setMailCheck("3");
          user.setMobileCheck("0");
          userPwd = ((String)AliyunOcsSampleHelp.getIMemcachedCache().get(phone));
          if ((userPwd != null) && (!userPwd.equals("")))
          {
        	  userPwd = MessageDigestUtils.sha256Hex(userPwd);
        	  user.setUserPwd(userPwd);
          }
          else
          {
            Struts2Utils.renderText("timeOut", new String[0]);
            return;
          }
          user.setIpAddress(ip);
          String ipLocation = seeker.getAddress(ip);
          user.setIpLocation(ipLocation);
          user.setOldDate(date);
          user.setNewDate(date);
          user.setBalance(ApplicationListenerImpl.sysConfigureJson.getRegBalance());
          user.setCommissionBalance(Double.valueOf(0.0D));
          user.setCommissionCount(Double.valueOf(0.0D));
          user.setCommissionMention(Double.valueOf(0.0D));
          user.setCommissionPoints(Integer.valueOf(0));
          user.setFaceImg("/Images/defaultUserFace.png");
          user.setUserType("0");
          user.setExperience(Integer.valueOf(0));
          if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++)
            {
              Cookie cookie = cookies[i];
              if (cookie.getName().equals("inviteId"))
              {
                String inviteId = cookie.getValue();
                if ((inviteId != null) && (!inviteId.equals(""))) {
                  user.setInvite(Integer.valueOf(Integer.parseInt(inviteId)));
                }
              }
            }
          }
          try
          {
            userService.add(user);
          }
          catch (Exception e)
          {
            e.printStackTrace();
            Struts2Utils.renderText("false", new String[0]);
          }
          Struts2Utils.renderText("0", new String[0]);
        }
        else
        {
          Struts2Utils.renderText("1", new String[0]);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public String mobileok()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    user = userService.userByName(phone);
    if (user != null)
    {
      if (user.getMobileCheck().equals("0"))
      {
        isVerify = "0";
        if (request.isRequestedSessionIdFromCookie())
        {
          Cookie cookie = new Cookie("userName", user.getPhone());
          cookie.setMaxAge(-1);
          cookie.setPath("/");
          cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
          response.addCookie(cookie);
          Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
          cookie2.setMaxAge(-1);
          cookie2.setPath("/");
          cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
          response.addCookie(cookie2);
          Cookie cookie3 = new Cookie("face", URLEncoder.encode(user.getFaceImg(), "UTF-8"));
          cookie3.setMaxAge(-1);
          cookie3.setPath("/");
          cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
          response.addCookie(cookie3);
          Cookie cookie4 = new Cookie("loginUser", URLEncoder.encode(user.getPhone(), "UTF-8"));
          cookie4.setMaxAge(31536000);
          cookie4.setPath("/");
          cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
          response.addCookie(cookie4);
        }
      }
      else
      {
        isVerify = "1";
      }
    }
    else {
      isVerify = "1";
    }
    return "mobileok";
  }
  
  public String emailcheck()
  {
    user = userService.userByName(str);
    if (user == null)
    {
      Struts2Utils.render("text/html", "<script>alert(\"该邮箱没有注册,请注册！\");window.location.href=\"/register/index.html\";</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    isVerify = user.getMailCheck();
    
    return "emailcheck";
  }
  
  public void SendRegisterMail()
  {
    Random random = new Random();
    String ran = "";
    for (int i = 0; i < 6; i++) {
      ran = ran + random.nextInt(9);
    }
    key = (MD5Util.encode(mail) + MD5Util.encode(DateUtil.dateTimeToStr(new Date())) + Base64.getEncode(mail));
    String html = "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border: #dddddd 1px solid; padding: 20px 0;\"><tbody><tr><td><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"border-bottom: #ff6600 2px solid; padding-bottom: 12px;\"><tbody><tr><td style=\"line-height: 22px; padding-left: 20px;\"><a target=\"_blank\" title=\"" + 
    



      ApplicationListenerImpl.sysConfigureJson.getSaitName() + "\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\"><img width=\"230px\" border=\"0\" height=\"52\" src=\"" + ApplicationListenerImpl.sysConfigureJson.getImgUrl() + "/Images/mail_logo.gif\"></a></td>" + 
      "<td align=\"right\" style=\"font-size: 12px; padding-right: 20px; padding-top: 30px;\"><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">首页</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b>" + 
      "<a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/index.html\">我的" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/help/index.html\">帮助</a></td>" + 
      "</tr>" + 
      "</tbody></table>" + 
      "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"padding: 0 20px;\">" + 
      "<tbody><tr>" + 
      "<td style=\"font-size: 14px; color: #333333; height: 40px; line-height: 40px; padding-top: 10px;\">亲爱的 <b style=\"color: #333333; font-family: Arial;\"><a href=\"mailto:" + mail + "\" target=\"_blank\">" + mail + "</a></b>：</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！感谢您注册" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "。</p></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您本次验证码为：<b style=\"color: blue; font-family: Arial;\"><span data=\"" + ran + "\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\" isout=\"1\">" + ran + "</span></b>，请及时输入。</p></td>" + 
      "</tr>" + 
      
      "</tbody></table>" + 
      "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top: 60px;\">" + 
      "<tbody><tr>" + 
      "<td style=\"font-size: 12px; color: #777777; line-height: 22px; border-bottom: #22aaff 2px solid; padding-bottom: 8px; padding-left: 20px;\">此邮件由系统自动发出，请勿回复！</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 8px 20px 0;\">感谢您对" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "（<a style=\"color: #22aaff; font-family: Arial;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "</a>）的支持，祝您好运！</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 0 20px;\">客服热线：<b style=\"color: #ff6600; font-family: Arial;\">" + ApplicationListenerImpl.sysConfigureJson.getServiceTel() + "</b></td>" + 
      "</tr>" + 
      "</tbody></table>" + 
      "</td>" + 
      "</tr>" + 
      "</tbody></table>" + 
      "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\">" + 
      "<tbody><tr>" + 
      "<td align=\"center\" style=\"font-size:12px; color:#bbbbbb; padding-top:10px;\">" + ApplicationListenerImpl.sysConfigureJson.getIcp() + "</td>" + 
      "</tr>" + 
      "</tbody></table>";
    if (isVerify.split("\\*")[0].equals(Base64.getEncode(mail))) {
      if (AliyunOcsSampleHelp.getIMemcachedCache().get(Base64.getEncode(mail)) == null) {
        try
        {
          boolean flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), mail, ApplicationListenerImpl.sysConfigureJson.getSaitName() + "验证注册邮箱", html);
          if (flag)
          {
            AliyunOcsSampleHelp.getIMemcachedCache().set(Base64.getEncode(mail), 120, ran);
            Struts2Utils.renderText("2", new String[0]);
          }
          else
          {
            Struts2Utils.renderText("false", new String[0]);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
          Struts2Utils.renderText("false", new String[0]);
        }
      } else {
        Struts2Utils.renderText("3", new String[0]);
      }
    }
  }
  
  public static void main(String[] args)
  {
    Random random = new Random();
    String ran = "";
    for (int i = 0; i < 6; i++) {
      ran = ran + random.nextInt(9);
    }
    System.err.println(ran);
  }
  
  public String emailok()
    throws UnsupportedEncodingException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    if (StringUtil.isNotBlank(key))
    {
      key = key.substring(64, key.length());
      mail = Base64.getDecode(key);
      if (AliyunOcsSampleHelp.getIMemcachedCache().get(MD5Util.encode(mail)) != null)
      {
        if (StringUtil.isNotBlank(mail))
        {
          user = userService.userByName(mail);
          if (user != null)
          {
            if (!user.getMailCheck().equals("0"))
            {
              if (request.isRequestedSessionIdFromCookie())
              {
                Cookie cookie = new Cookie("userName", user.getMail());
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
                cookie2.setMaxAge(-1);
                cookie2.setPath("/");
                cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie2);
                Cookie cookie3 = new Cookie("face", URLEncoder.encode(user.getFaceImg(), "UTF-8"));
                cookie3.setMaxAge(-1);
                cookie3.setPath("/");
                cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie3);
                user.setMailCheck("0");
                Cookie cookie4 = new Cookie("loginUser", URLEncoder.encode(user.getMail(), "UTF-8"));
                cookie4.setMaxAge(31536000);
                cookie4.setPath("/");
                cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
                response.addCookie(cookie4);
                userService.add(user);
                isVerify = "0";
              }
            }
            else {
              isVerify = "0";
            }
          }
          else {
            isVerify = "1";
          }
        }
        else
        {
          isVerify = "1";
        }
      }
      else {
        isVerify = "1";
      }
    }
    return "emailok";
  }
  
  public void authorizeRegsiter()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    Cookie[] cookies = request.getCookies();
    user = new User();
    
    String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
    String date = DateUtil.DateTimeToStr(new Date());
    if ((userName != null) && (!userName.equals(""))) {
      user.setUserName(userName.trim());
    }
    user.setUserPwd(openId);
    user.setMobileCheck("3");
    user.setMailCheck("3");
    user.setAttribute22(openId);
    if (ip == null) {
      ip = "127.0.0.1";
    }
    user.setIpAddress(ip);
    String ipLocation = seeker.getAddress(ip);
    user.setIpLocation(ipLocation);
    user.setOldDate(date);
    
    user.setBalance(ApplicationListenerImpl.sysConfigureJson.getRegBalance());
    user.setCommissionBalance(Double.valueOf(0.0D));
    user.setCommissionCount(Double.valueOf(0.0D));
    user.setCommissionMention(Double.valueOf(0.0D));
    user.setCommissionPoints(Integer.valueOf(0));
    user.setFaceImg(userFace);
    user.setUserType("0");
    user.setExperience(Integer.valueOf(0));
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("inviteId"))
        {
          String inviteId = cookie.getValue();
          if ((inviteId != null) && (!inviteId.equals("")))
          {
            user.setInvite(Integer.valueOf(Integer.parseInt(inviteId)));
            User user2 = (User)userService.findById(inviteId);
            int commissionPoints = user2.getCommissionPoints().intValue();
            commissionPoints += ApplicationListenerImpl.sysConfigureJson.getInvite().intValue();
            user2.setCommissionPoints(Integer.valueOf(commissionPoints));
            userService.add(user2);
            commissionpoints = new Commissionpoints();
            commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
            commissionpoints.setDetailed("邀请好友获得" + ApplicationListenerImpl.sysConfigureJson.getInvite() + "积分");
            commissionpoints.setPay("+" + ApplicationListenerImpl.sysConfigureJson.getBuyProduct());
            commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(inviteId)));
            commissionpointsService.add(commissionpoints);
          }
        }
      }
    }
    try
    {
      userService.add(user);
      Struts2Utils.renderJson(user, new String[0]);
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("false", new String[0]);
      e.printStackTrace();
    }
  }
  
  public void authorizeIsExists()
  {
    user = userService.isNotOpenId(openId);
    if (user == null)
    {
      Struts2Utils.renderText("true", new String[0]);
    }
    else
    {
      if (user.getUserPwd().length() > 30)
      {
        user.setAttribute22(user.getUserPwd());
        userService.add(user);
      }
      Struts2Utils.renderJson(user, new String[0]);
    }
  }
  
  public String isExists()
  {
    user = userService.userByName(userName);
    if (user == null) {
      Struts2Utils.renderText("true", new String[0]);
    } else {
      Struts2Utils.renderText("false", new String[0]);
    }
    return null;
  }
  
  public String qqUserInfoAuth()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          String userId = cookie.getValue();
          if ((userId != null) && (!userId.equals("")))
          {
            user = ((User)userService.findById(userId));
            if ((user.getMailCheck().equals("0")) || (user.getMailCheck().equals("0"))) {
              return "index_index";
            }
            return "qqUserInfoAuth";
          }
        }
      }
    }
    return null;
  }
  
  public String wxUserInfoAuth()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          String userId = cookie.getValue();
          if ((userId != null) && (!userId.equals("")))
          {
            user = ((User)userService.findById(userId));
            if (("0".equals(user.getMobileCheck())) || ("0".equals(user.getMailCheck()))) {
              return "index_index";
            }
            return "wxUserInfoAuth";
          }
        }
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
  
  public String getPhone()
  {
    return phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getMail()
  {
    return mail;
  }
  
  public void setMail(String mail)
  {
    this.mail = mail;
  }
  
  public String getUserPwd()
  {
    return userPwd;
  }
  
  public void setUserPwd(String userPwd)
  {
    this.userPwd = userPwd;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getForward()
  {
    return forward;
  }
  
  public void setForward(String forward)
  {
    this.forward = forward;
  }
  
  public String getStr()
  {
    return str;
  }
  
  public void setStr(String str)
  {
    this.str = str;
  }
  
  public String getIsVerify()
  {
    return isVerify;
  }
  
  public void setIsVerify(String isVerify)
  {
    this.isVerify = isVerify;
  }
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getDate()
  {
    return date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public String getOpenId()
  {
    return openId;
  }
  
  public void setOpenId(String openId)
  {
    this.openId = openId;
  }
  
  public String getUserFace()
  {
    return userFace;
  }
  
  public void setUserFace(String userFace)
  {
    this.userFace = userFace;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }
}

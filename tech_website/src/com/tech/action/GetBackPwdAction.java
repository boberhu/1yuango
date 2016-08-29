package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mteams.commons.encrypt.digest.MessageDigestUtils;
import com.shcm.bean.SendResultBean;
import com.tech.pojo.User;
import com.tech.service.UserService;
import com.tech.sms.SmsSenderFactory;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Base64;
import com.tech.util.DateUtil;
import com.tech.util.EmailUtil;
import com.tech.util.MD5Util;
import com.tech.util.RandomValidateCode;
import com.tech.util.Sampler;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("GetBackPwdAction")
public class GetBackPwdAction extends BaseAction
{
  private static final long serialVersionUID = 686157280940778943L;
  @Autowired
  @Qualifier("userService")
  private UserService userService;
  private String rnd;
  private String mail;
  private String key;
  private User user;
  private String newPwd;
  private String forward;
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String index()
  {
    return "index";
  }
  
  public void getBackPwd()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("rndCode"))
        {
          String rndCode = cookie.getValue();
          System.err.println(rndCode);
          if (rnd.trim().equalsIgnoreCase(rndCode))
          {
            System.err.println(mail);
            user = userService.userByName(mail);
            if (user == null) {
              Struts2Utils.renderText("false", new String[0]);
            } else {
              Struts2Utils.renderText("1", new String[0]);
            }
          }
          else
          {
            Struts2Utils.renderText("3", new String[0]);
          }
        }
      }
    }
  }
  
  public void getRandomCode()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    response.setContentType("image/jpeg");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expire", 0L);
    RandomValidateCode randomValidateCode = new RandomValidateCode();
    try
    {
      randomValidateCode.getRandcode(request, response);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String findemailcheck()
  {
    return "findemailcheck";
  }
  
  public String findmobilecheck()
  {
    String mobileStr = (String)AliyunOcsSampleHelp.getIMemcachedCache().get(mail);
    key = (MD5Util.encode(mail) + MD5Util.encode(DateUtil.dateTimeToStr(new Date())) + Base64.getEncode(mail));
    if (mobileStr == null)
    {
      Random random = new Random();
      String ran = "";
      for (int i = 0; i < 6; i++) {
        ran = ran + random.nextInt(9);
      }
      try
      {
    	String content = ApplicationListenerImpl.sysConfigureJson.getVerifyMsgTpl().replace("{000000}", ran);
    	boolean success = SmsSenderFactory.create().send(mail, content);
    	if(success){
    		AliyunOcsSampleHelp.getIMemcachedCache().set(mail, 120, ran);
    	}else{
    		Struts2Utils.render("text/html", "<script>alert(\"发送失败，请稍后再试！\");\";</script>", new String[] { "encoding:UTF-8" });
    	}
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return "findmobilecheck";
  }
  
  public void findMobileReset()
    throws UnsupportedEncodingException
  {
    String mobileStr = (String)AliyunOcsSampleHelp.getIMemcachedCache().get(mail);
    if (mobileStr != null)
    {
      if (mobileStr.equals(rnd)) {
        Struts2Utils.renderText("0", new String[0]);
      } else {
        Struts2Utils.renderText("2", new String[0]);
      }
    }
    else {
      Struts2Utils.renderText("1", new String[0]);
    }
  }
  
  public void regSendMes()
    throws Exception
  {
    Random random = new Random();
    String ran = "";
    for (int i = 0; i < 6; i++) {
      ran = ran + random.nextInt(9);
    }
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(mail) == null) {
      try
      {
    	String content = ApplicationListenerImpl.sysConfigureJson.getVerifyMsgTpl().replace("{000000}", ran);
    	boolean success = SmsSenderFactory.create().send(mail, content);
    	if(success){
    		AliyunOcsSampleHelp.getIMemcachedCache().set(mail, 120, ran);
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
  
  public static void main(String[] args)
    throws UnsupportedEncodingException
  {
    String key = MD5Util.encode("127.0.0.11000");
    AliyunOcsSampleHelp.getIMemcachedCache().delete(key);
  }
  
  public void sendFindPwdMail()
  {
    request = Struts2Utils.getRequest();
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
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！请点击下面的按钮，重新设置您的密码：</p></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"padding-top: 15px; padding-left: 28px;\"><a title=\"重设密码\" style=\"display: inline-block; padding: 0 25px; height: 28px; line-height: 28px; text-align: center; color: #ffffff; background: #ff7700; font-size: 12px; cursor: pointer; border-radius: 2px; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "\">重设密码</a></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td width=\"525\" style=\"font-size: 12px; color: #333333; line-height: 22px; padding-top: 20px;\">如果上面按钮不能点击或点击后没有反应，您还可以将以下链接复制到浏览器地址栏中访问完成重设密码。</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td width=\"525\" style=\"font-size: 12px; padding-top: 5px; word-break: break-all; word-wrap: break-word;\"><a style=\"font-family: Arial; color: #22aaff;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/getbackpwd/findreset.html?key=" + key + "</a></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size:12px; color:#333333; line-height:22px; padding-top:30px;\"><p style=\"text-indent:2em; padding:0; margin:0;\"><b>如果您现在想起了您的密码：</b></p></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size:12px; color:#333333; line-height:22px;\"><p style=\"text-indent:2em; padding:0; margin:0;\">可不必重设密码，继续用原来的密码登录。</p></td>" + 
      "</tr>" + 
      "</tbody></table>" + 
      "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top: 60px;\">" + 
      "<tbody><tr>" + 
      "<td style=\"font-size: 12px; color: #777777; line-height: 22px; border-bottom: #22aaff 2px solid; padding-bottom: 8px; padding-left: 20px;\">此邮件由系统自动发出，请勿回复！</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 8px 20px 0;\">感谢您对" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "（<a style=\"color: #22aaff; font-family: Arial;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "</a>）的支持，祝您好运！</td>" + 
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
    
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("rndCode"))
        {
          String rndCode = cookie.getValue();
          if (rnd.trim().equalsIgnoreCase(rndCode))
          {
            if (AliyunOcsSampleHelp.getIMemcachedCache().get(MD5Util.encode(mail)) == null)
            {
              boolean flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), mail, ApplicationListenerImpl.sysConfigureJson.getSaitName() + "取回密码", html);
              if (flag)
              {
                AliyunOcsSampleHelp.getIMemcachedCache().set(MD5Util.encode(mail), 600, mail);
                Struts2Utils.renderText("0", new String[0]);
              }
              else
              {
                Struts2Utils.renderText("false", new String[0]);
              }
            }
            else
            {
              Struts2Utils.renderText("3", new String[0]);
            }
          }
          else {
            Struts2Utils.renderText("2", new String[0]);
          }
        }
      }
    }
  }
  
  public String findreset()
    throws UnsupportedEncodingException
  {
    if (StringUtil.isNotBlank(key))
    {
      key = key.substring(64, key.length());
      mail = Base64.getDecode(key);
      if (StringUtil.isNotBlank(mail))
      {
        user = userService.userByName(mail);
        if (user != null) {
          return "findreset";
        }
      }
    }
    return "index_index";
  }
  
  public void updatePwd()
  {
    user = userService.userByName(mail);
    if (user != null)
    {
	  if ((newPwd != null) && (!newPwd.equals(""))) {
		  newPwd = MessageDigestUtils.sha256Hex(newPwd);
      }
      user.setUserPwd(newPwd);
      userService.add(user);
      Struts2Utils.renderText("0", new String[0]);
    }
    else
    {
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public String findok()
  {
    return "findok";
  }
  
  public String getRnd()
  {
    return rnd;
  }
  
  public void setRnd(String rnd)
  {
    this.rnd = rnd;
  }
  
  public String getMail()
  {
    return mail;
  }
  
  public void setMail(String mail)
  {
    this.mail = mail;
  }
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getNewPwd()
  {
    return newPwd;
  }
  
  public void setNewPwd(String newPwd)
  {
    this.newPwd = newPwd;
  }
  
  public String getForward()
  {
    return forward;
  }
  
  public void setForward(String forward)
  {
    this.forward = forward;
  }
}

package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mteams.commons.encrypt.digest.MessageDigestUtils;
import com.tech.pojo.User;
import com.tech.service.UserService;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.HTMLFilter;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@Component("LoginAction")
public class LoginAction extends BaseAction
{
  private static final long serialVersionUID = -6356307819518359036L;
  private String userName;
  private String pwd;
  private String forward;
  private String shareId;
  private String account;
  private String wxAppId;
  private Integer wxAppStatus = 0;
  
  @Autowired
  @Qualifier("userService")
  private UserService userService;
  HttpServletRequest request = null;
  static HTMLFilter htmlFilter = new HTMLFilter();
  
  public String index()
  {
    /*if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元拍购开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.51openos.com\">www.51openos.com</a></div></body></html>", new String[0]);
      return null;
    }*/
	//wxAppId = ConfigUtil.getValue("wxAppID");
	//wxAppStatus = Integer.valueOf(ConfigUtil.getValue("wxAppStatus","1"));
    try
    {
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
      } else {
        Struts2Utils.render("text/html", "<script>alert(\"您的浏览器未开启Cookie功能,无法保存购物信息,请先开启Cookie功能后继续购物！\");window.location.href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/help/openCookie.html\";</script>", new String[] { "encoding:UTF-8" });
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "index";
  }
  
  public String login()
  {
    userName = htmlFilter.filter(userName);
    pwd = htmlFilter.filter(pwd);
    try {
		pwd = URLDecoder.decode(pwd, "UTF-8");
	} catch (UnsupportedEncodingException e1) {
		e1.printStackTrace();
	}
    pwd = MessageDigestUtils.sha256Hex(pwd);
    User user = userService.userByName(userName);
    
    String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
    if (ip == null) {
      ip = "127.0.0.1";
    }
    String date = DateUtil.DateTimeToStr(new Date());
    if (user != null)
    {
      if (userName.indexOf("@") != -1)
      {
        User u1 = userService.mailLogin(userName, pwd);
        if (u1 != null) {
          try
          {
            if (u1.getMailCheck().equals("0"))
            {
              Struts2Utils.renderJson(u1, new String[0]);
              user.setIpAddress(ip);
              String ipLocation = RegisterAction.seeker.getAddress(ip);
              user.setIpLocation(ipLocation);
              
              user.setNewDate(date);
              userService.add(user);
            }
            else
            {
              Struts2Utils.renderText("check", new String[0]);
            }
          }
          catch (Exception e)
          {
            Struts2Utils.renderText("check", new String[0]);
            e.printStackTrace();
          }
        } else {
          Struts2Utils.renderText("pwdError", new String[0]);
        }
      }
      else
      {
        User u2 = userService.phoneLogin(userName, pwd);
        if (u2 != null) {
          try
          {
            if (u2.getMobileCheck().equals("0"))
            {
              Struts2Utils.renderJson(u2, new String[0]);
              user.setIpAddress(ip);
              String ipLocation = RegisterAction.seeker.getAddress(ip);
              user.setIpLocation(ipLocation);
              
              user.setNewDate(date);
              userService.add(user);
            }
            else
            {
              Struts2Utils.renderText("check", new String[0]);
            }
          }
          catch (Exception e)
          {
            Struts2Utils.renderText("check", new String[0]);
            e.printStackTrace();
          }
        } else {
          Struts2Utils.renderText("pwdError", new String[0]);
        }
      }
    }
    else {
      Struts2Utils.renderText("userError", new String[0]);
    }
    return null;
  }
  
  public String fastLogin()
  {
    return "fastLogin";
  }
  
  public String popLogin()
  {
    return "popLogin";
  }
  
  public String shopLogin()
  {
    return "shopLogin";
  }
  
  public String buyCartLogin()
  {
    return "buyCartLogin";
  }
  
  public String postCommentLogin()
  {
    return "postCommentLogin";
  }
  
  public String miniLogin()
  {
    return "miniLogin";
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPwd()
  {
    return pwd;
  }
  
  public void setPwd(String pwd)
  {
    this.pwd = pwd;
  }
  
  public String getForward()
  {
    return forward;
  }
  
  public void setForward(String forward)
  {
    this.forward = forward;
  }
  
  public String getShareId()
  {
    return shareId;
  }
  
  public void setShareId(String shareId)
  {
    this.shareId = shareId;
  }
  
  public String getAccount()
  {
    return account;
  }
  
  public void setAccount(String account)
  {
    this.account = account;
  }

	public String getWxAppId() {
		return wxAppId;
	}
	
	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}
	
	public Integer getWxAppStatus() {
		return wxAppStatus;
	}
	
	public void setWxAppStatus(Integer wxAppStatus) {
		this.wxAppStatus = wxAppStatus;
	}
}

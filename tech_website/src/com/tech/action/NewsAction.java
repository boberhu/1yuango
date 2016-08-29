package com.tech.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.pojo.News;
import com.tech.service.NewsService;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Struts2Utils;

@Component("NewsAction")
public class NewsAction extends BaseAction
{
  private static final long serialVersionUID = 1889272927204740730L;
  @Autowired
  private NewsService newsService;
  private Integer id;
  private News news;
  
  public String index()
  {
    if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      

      return null;
    }
    news = ((News)newsService.findById(id.toString()));
    return "index";
  }
  
  public String toAdd()
  {
    return "toAddOrUpdate";
  }
  
  public String add()
  {
    return "success";
  }
  
  public String toUpdate()
  {
    return "toAddOrUpdate";
  }
  
  public String update()
  {
    return "success";
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public News getNews()
  {
    return news;
  }
  
  public void setNews(News news)
  {
    this.news = news;
  }
}

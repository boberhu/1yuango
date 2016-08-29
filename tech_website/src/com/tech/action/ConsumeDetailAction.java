package com.tech.action;

import com.tech.dao.Pagination;
import com.tech.pojo.Consumerdetail;
import com.tech.service.ConsumerdetailService;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Struts2Utils;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ConsumeDetailAction")
public class ConsumeDetailAction extends BaseAction
{
  private static final long serialVersionUID = 3910394002303639758L;
  @Autowired
  private ConsumerdetailService consumerdetailService;
  private String id;
  private String userId;
  private int pageNo;
  private int pageSize = 10;
  private int pageCount;
  private int resultCount;
  private List<Consumerdetail> consumerdetailList;
  
  public String index()
  {
    if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      

      return null;
    }
    HttpServletRequest request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          userId = cookie.getValue();
          if ((userId != null) && (!userId.equals("")))
          {
            resultCount = consumerdetailService.userByConsumetableDetailByCount(id).intValue();
            return "ConsumeDetail";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String ConsumeDetailAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Pagination datePage = consumerdetailService.userByConsumetableDetail(id, pageNo, pageSize);
    List<Consumerdetail> dataList = (List<Consumerdetail>)datePage.getList();
    Struts2Utils.renderJson(dataList, new String[0]);
    return null;
  }
  
  public void ConsumeDetailAjaxPageByCount()
  {
    resultCount = consumerdetailService.userByConsumetableDetailByCount(id).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public List<Consumerdetail> getConsumerdetailList()
  {
    return consumerdetailList;
  }
  
  public void setConsumerdetailList(List<Consumerdetail> consumerdetailList)
  {
    this.consumerdetailList = consumerdetailList;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public int getPageNo()
  {
    return pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public int getPageSize()
  {
    return pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getPageCount()
  {
    return pageCount;
  }
  
  public void setPageCount(int pageCount)
  {
    this.pageCount = pageCount;
  }
  
  public int getResultCount()
  {
    return resultCount;
  }
  
  public void setResultCount(int resultCount)
  {
    this.resultCount = resultCount;
  }
}

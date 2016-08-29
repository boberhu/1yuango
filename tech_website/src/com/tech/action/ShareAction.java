package com.tech.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Product;
import com.tech.pojo.ShareCommentJSON;
import com.tech.pojo.ShareJSON;
import com.tech.pojo.Sharecomments;
import com.tech.pojo.Shareimage;
import com.tech.pojo.Shareinfo;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.ShareService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.PaginationUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@Component("ShareAction")
public class ShareAction extends BaseAction
{
  private static final long serialVersionUID = -8202280107475087499L;
  @Autowired
  ShareService shareService;
  private Shareinfo shareinfo;
  private Shareimage shareimage;
  private Latestlottery latestlottery;
  private List<ShareJSON> ShareJSONList;
  private List<ShareCommentJSON> shareCommentJSONList;
  private User user;
  private Sharecomments sharecomments;
  private ShareCommentJSON shareCommentJSON;
  private ShareJSON shareJSON;
  private String id;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private static List<ShareJSON> ShareJSONByIndexList;
  private static Long nowDateByShareJSONByIndex = Long.valueOf(System.currentTimeMillis());
  private static Long beginDateByShareJSONByIndex;
  
  public String index()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (pages != null) {
      pageNo = Integer.parseInt(pages.split("_")[1]);
    }
    
    if (id.equals("new20"))
    {
      Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("share_new20_page" + pageNo + "_" + pageSize);
      if (page == null)
      {
        page = shareService.loadPageShare("new20", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("share_new20_page" + pageNo + "_" + pageSize, 5, page);
      }
      List<?>  pageList = page.getList();
      ShareJSONList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
    	  for(Object obj:objs){
    		if(obj instanceof Shareinfo){
    			shareinfo = (Shareinfo)obj;
    		}
    		if(obj instanceof Shareimage){
    			shareimage = (Shareimage)obj;
    		}
    		if(obj instanceof Latestlottery){
    			latestlottery = (Latestlottery)obj;
    		}
    	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userName = "";
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userName = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userName = latestlottery.getBuyUser();
          if (userName.indexOf("@") != -1)
          {
            String[] u = userName.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userName = u1 + "@" + u[1];
          }
          else
          {
            userName = userName.substring(0, 4) + "*** " + userName.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(DateUtil.getTime(DateUtil.SDateTimeToDate(shareinfo.getShareDate())));
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userName);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONList.add(shareJSON);
      }
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
    }
    if (id.equals("hot20"))
    {
      Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("share_hot20_page_" + pageNo + "_" + pageSize);
      if (page == null)
      {
        page = shareService.loadPageShare("hot20", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("share_hot20_page_" + pageNo + "_" + pageSize, 5, page);
      }
      List<?>  pageList = page.getList();
      ShareJSONList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Shareimage){
  			shareimage = (Shareimage)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userName = "";
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userName = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userName = latestlottery.getBuyUser();
          if (userName.indexOf("@") != -1)
          {
            String[] u = userName.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userName = u1 + "@" + u[1];
          }
          else
          {
            userName = userName.substring(0, 4) + "*** " + userName.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(shareinfo.getShareDate());
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userName);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONList.add(shareJSON);
      }
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
    }
    if (id.equals("reply20"))
    {
      Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("share_reply20_page_" + pageNo + "_" + pageSize);
      if (page == null)
      {
        page = shareService.loadPageShare("reply20", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("share_reply20_page_" + pageNo + "_" + pageSize, 5, page);
      }
      List<?>  pageList = page.getList();
      ShareJSONList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Shareimage){
  			shareimage = (Shareimage)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userName = "";
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userName = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userName = latestlottery.getBuyUser();
          if (userName.indexOf("@") != -1)
          {
            String[] u = userName.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userName = u1 + "@" + u[1];
          }
          else
          {
            userName = userName.substring(0, 4) + "*** " + userName.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(shareinfo.getShareDate());
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userName);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONList.add(shareJSON);
      }
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
    }
    if (id.equals("elite20"))
    {
      Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("share_elite20_page_" + pageNo + "_" + pageSize);
      if (page == null)
      {
        page = shareService.loadPageShare("reply20", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("share_elite20_page_" + pageNo + "_" + pageSize, 5, page);
      }
      List<?>  pageList = page.getList();
      ShareJSONList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Shareimage){
  			shareimage = (Shareimage)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userName = "";
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userName = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userName = latestlottery.getBuyUser();
          if (userName.indexOf("@") != -1)
          {
            String[] u = userName.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userName = u1 + "@" + u[1];
          }
          else
          {
            userName = userName.substring(0, 4) + "*** " + userName.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(shareinfo.getShareDate());
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userName);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONList.add(shareJSON);
      }
      Collections.shuffle(ShareJSONList);
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
    }
    return "index";
  }
  
  public String ajaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    nowDateByShareJSONByIndex = Long.valueOf(System.currentTimeMillis());
    if (beginDateByShareJSONByIndex == null)
    {
      Pagination page = shareService.loadPageShare("new20", pageNo, pageSize);
      List<?>  pageList = page.getList();
      ShareJSONByIndexList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Shareimage){
  			shareimage = (Shareimage)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userer = null;
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userer = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userer = latestlottery.getBuyUser();
          if (userer.indexOf("@") != -1)
          {
            String[] u = userer.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userer = u1 + "@" + u[1];
          }
          else
          {
            userer = userer.substring(0, 4) + "*** " + userer.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(shareinfo.getShareDate());
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userer);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONByIndexList.add(shareJSON);
      }
      beginDateByShareJSONByIndex = Long.valueOf(System.currentTimeMillis());
      Struts2Utils.renderJson(ShareJSONByIndexList, new String[0]);
    }
    else if (nowDateByShareJSONByIndex.longValue() - beginDateByShareJSONByIndex.longValue() < 5000L)
    {
      Struts2Utils.renderJson(ShareJSONByIndexList, new String[0]);
    }
    else
    {
      beginDateByShareJSONByIndex = Long.valueOf(System.currentTimeMillis());
      Pagination page = shareService.loadPageShare("new20", pageNo, pageSize);
      List<?>  pageList = page.getList();
      ShareJSONByIndexList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        Object[] objs = (Object[])pageList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Shareimage){
  			shareimage = (Shareimage)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //shareimage = ((Shareimage)((Object[])pageList.get(i))[1]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[2]);
        String userer = null;
        if ((latestlottery.getUserName() != null) && (!latestlottery.getUserName().equals("")))
        {
          userer = latestlottery.getUserName();
        }
        else if ((latestlottery.getBuyUser() != null) && (!latestlottery.getBuyUser().equals("")))
        {
          userer = latestlottery.getBuyUser();
          if (userer.indexOf("@") != -1)
          {
            String[] u = userer.split("@");
            String u1 = u[0].substring(0, 2) + "***";
            userer = u1 + "@" + u[1];
          }
          else
          {
            userer = userer.substring(0, 4) + "*** " + userer.substring(7);
          }
        }
        shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        shareJSON.setReplyCount(shareinfo.getReplyCount());
        shareJSON.setReward(shareinfo.getReward());
        shareJSON.setShareContent(shareinfo.getShareContent());
        shareJSON.setShareDate(shareinfo.getShareDate());
        shareJSON.setShareImages(shareimage.getImages());
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userer);
        shareJSON.setUserFace(latestlottery.getUserFace());
        shareJSON.setUserId(latestlottery.getUserId()+"");
        ShareJSONByIndexList.add(shareJSON);
      }
      Struts2Utils.renderJson(ShareJSONByIndexList, new String[0]);
    }
    return null;
  }
  
  public String shareShow()
  {
    return null;
  }
  
  public void indexSharecommentsList()
  {
    List<Object[]> objectList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("share_objectList");
    if (objectList == null)
    {
      objectList = shareService.getIndexSharecommentsList();
      AliyunOcsSampleHelp.getIMemcachedCache().set("share_objectList", 5, objectList);
    }
    shareCommentJSONList = new ArrayList();
    for (Object[] objects : objectList)
    {
      user = ((User)objects[0]);
      sharecomments = ((Sharecomments)objects[1]);
      shareCommentJSON = new ShareCommentJSON();
      shareCommentJSON.setContent(sharecomments.getContent());
      shareCommentJSON.setCreateDate(sharecomments.getCreateDate());
      shareCommentJSON.setUid(sharecomments.getUid());
      shareCommentJSON.setUserFace(user.getFaceImg());
      shareCommentJSON.setShareInfoId(sharecomments.getShareInfoId());
      String userName = UserNameUtil.userName(user);
      shareCommentJSON.setReCount(sharecomments.getReCount());
      shareCommentJSON.setUserName(userName);
      shareCommentJSON.setUserId(user.getUserId());
      shareCommentJSONList.add(shareCommentJSON);
    }
    Struts2Utils.renderJson(shareCommentJSONList, new String[0]);
  }
  
  public Shareinfo getShareinfo()
  {
    return shareinfo;
  }
  
  public void setShareinfo(Shareinfo shareinfo)
  {
    this.shareinfo = shareinfo;
  }
  
  public Shareimage getShareimage()
  {
    return shareimage;
  }
  
  public void setShareimage(Shareimage shareimage)
  {
    this.shareimage = shareimage;
  }
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public List<ShareJSON> getShareJSONList()
  {
    return ShareJSONList;
  }
  
  public void setShareJSONList(List<ShareJSON> shareJSONList)
  {
    ShareJSONList = shareJSONList;
  }
  
  public ShareJSON getShareJSON()
  {
    return shareJSON;
  }
  
  public void setShareJSON(ShareJSON shareJSON)
  {
    this.shareJSON = shareJSON;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public int getPageNo()
  {
    return pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public String getPages()
  {
    return pages;
  }
  
  public void setPages(String pages)
  {
    this.pages = pages;
  }
  
  public String getPageString()
  {
    return pageString;
  }
  
  public void setPageString(String pageString)
  {
    this.pageString = pageString;
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
  
  public List<ShareCommentJSON> getShareCommentJSONList()
  {
    return shareCommentJSONList;
  }
  
  public void setShareCommentJSONList(List<ShareCommentJSON> shareCommentJSONList)
  {
    this.shareCommentJSONList = shareCommentJSONList;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public Sharecomments getSharecomments()
  {
    return sharecomments;
  }
  
  public void setSharecomments(Sharecomments sharecomments)
  {
    this.sharecomments = sharecomments;
  }
  
  public ShareCommentJSON getShareCommentJSON()
  {
    return shareCommentJSON;
  }
  
  public void setShareCommentJSON(ShareCommentJSON shareCommentJSON)
  {
    this.shareCommentJSON = shareCommentJSON;
  }
  
}

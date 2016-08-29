package com.tech.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.ShareCommentJSON;
import com.tech.pojo.ShareInfoJSON;
import com.tech.pojo.ShareInfoPro;
import com.tech.pojo.Sharecomments;
import com.tech.pojo.Shareimage;
import com.tech.pojo.Shareinfo;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.User;
import com.tech.pojo.Visitors;
import com.tech.service.LatestlotteryService;
import com.tech.service.ShareService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.UserService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.HTMLFilter;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@Component("ShareShowAction")
public class ShareShowAction extends BaseAction
{
  private static final long serialVersionUID = -5418862771252833639L;
  @Autowired
  private ShareService shareService;
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private UserService userService;
  private ShareInfoPro shareInfoPro;
  private Sharecomments sharecomments;
  private ShareCommentJSON shareCommentJSON;
  private List<ShareCommentJSON> shareCommentJSONList;
  private Shareinfo shareinfo;
  private User user;
  private List<Shareimage> shareimageList;
  private Latestlottery latestlottery;
  private List<Latestlottery> latestlotteryList;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private ProductJSON productJSON;
  private List<ShareInfoJSON> ShareJSONList;
  private ShareInfoJSON ShareInfoJSON;
  private Shareimage shareimage;
  private String id;
  private int pageNo;
  private int pageSize = 5;
  private int pageCount;
  private int resultCount;
  private String productId;
  private String shareId;
  private String userId;
  private String shareCommentId;
  private String commentText;
  private String reCommentId;
  HttpServletRequest request = null;
  static HTMLFilter htmlFilter = new HTMLFilter();
  
  public String index()
  {
    if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      

      return null;
    }
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
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
      }
    }
    shareInfoPro = new ShareInfoPro();
    this.shareimageList = new ArrayList();
    List<Object[]> objectList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_index_objectList_" + id);
    if (objectList == null)
    {
      objectList = shareService.shareShow(Integer.parseInt(id));
      AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_index_objectList_" + id, 5, objectList);
    }
    Object[] objs = (Object[])objectList.get(0);
	  for(Object obj:objs){
		if(obj instanceof Shareinfo){
			shareinfo = (Shareinfo)obj;
		}
		if(obj instanceof Latestlottery){
			latestlottery = (Latestlottery)obj;
		}
	  }
    //shareinfo = ((Shareinfo)((Object[])objectList.get(0))[0]);
    //latestlottery = ((Latestlottery)((Object[])objectList.get(0))[1]);
    shareInfoPro.setAnnouncedTime(DateUtil.getTime(DateUtil.SDateTimeToDate(latestlottery.getAnnouncedTime())));
    shareInfoPro.setBuyNumberCount(String.valueOf(latestlottery.getBuyNumberCount()));
    shareInfoPro.setProductId(String.valueOf(latestlottery.getProductId()));
    shareInfoPro.setProductImg(latestlottery.getProductImg());
    shareInfoPro.setProductName(latestlottery.getProductName());
    shareInfoPro.setProductPeriod(String.valueOf(latestlottery.getProductPeriod()));
    shareInfoPro.setProductPrice(String.valueOf(latestlottery.getProductPrice()));
    shareInfoPro.setProductTitle(latestlottery.getProductTitle());
    shareInfoPro.setReplyCount(shareinfo.getReplyCount().intValue());
    shareInfoPro.setReward(shareinfo.getReward().intValue());
    shareInfoPro.setShareContent(shareinfo.getShareContent());
    shareInfoPro.setShareDate(DateUtil.getTime(DateUtil.SDateTimeToDate(shareinfo.getShareDate())));
    shareInfoPro.setShareId(String.valueOf(shareinfo.getUid()));
    shareInfoPro.setUserId(String.valueOf(shareinfo.getUserId()));
    this.shareimageList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_index_shareimageList_" + shareinfo.getUid()));
    if (this.shareimageList == null)
    {
      this.shareimageList = shareService.getShareimage(String.valueOf(shareinfo.getUid()));
      AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_index_shareimageList_" + shareinfo.getUid(), 3600, this.shareimageList);
    }
    shareInfoPro.setShareTitle(shareinfo.getShareTitle());
    shareInfoPro.setSpellbuyProductId(String.valueOf(latestlottery.getSpellbuyProductId()));
    shareInfoPro.setUpCount(shareinfo.getUpCount().intValue());
    shareInfoPro.setWinRandomNumber(String.valueOf(latestlottery.getRandomNumber()));
    shareInfoPro.setWinUserFace(latestlottery.getUserFace());
    shareInfoPro.setWinUserName(latestlottery.getUserName());
    
    Pagination datePage = shareService.shareByComment(id, pageNo, pageSize);
    resultCount = datePage.getResultCount();
    try
    {
      List list = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(latestlottery.getProductId());
      if (list.size() > 0) {
        spellbuyproduct = ((Spellbuyproduct)list.get(0));
      }
    }
    catch (Exception localException1) {}
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_index_page_new20_" + pageNo + "_" + pageSize);
    if (page == null)
    {
      page = shareService.loadShareInfoByNew("new20", null, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_index_page_new20_" + pageNo + "_" + pageSize, 3600, page);
    }
    List<?>  pageList = page.getList();
    ShareJSONList = new ArrayList();
    for (int i = 0; i < pageList.size(); i++) {
      try
      {
        ShareInfoJSON = new ShareInfoJSON();
        Object[] objects = (Object[])pageList.get(i);
  	  for(Object obj:objects){
  		if(obj instanceof Shareinfo){
  			shareinfo = (Shareinfo)obj;
  		}
  		if(obj instanceof Latestlottery){
  			latestlottery = (Latestlottery)obj;
  		}
  	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[1]);
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
        ShareInfoJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        ShareInfoJSON.setReplyCount(shareinfo.getReplyCount());
        ShareInfoJSON.setReward(shareinfo.getReward());
        if (shareinfo.getShareContent().length() >= 35) {
          ShareInfoJSON.setShareContent(shareinfo.getShareContent().substring(0, 35));
        } else {
          ShareInfoJSON.setShareContent(shareinfo.getShareContent());
        }
        ShareInfoJSON.setShareDate(DateUtil.getTime(DateUtil.SDateTimeToDate(shareinfo.getShareDate())));
        List<Shareimage> shareimageList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_index_shareimageList_page_" + shareinfo.getUid());
        if (shareimageList == null)
        {
          shareimageList = shareService.getShareimage(String.valueOf(shareinfo.getUid()));
          AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_index_shareimageList_page_" + shareinfo.getUid(), 3600, shareimageList);
        }
        ShareInfoJSON.setShareimageList(shareimageList);
        ShareInfoJSON.setShareTitle(shareinfo.getShareTitle());
        ShareInfoJSON.setUid(shareinfo.getUid());
        ShareInfoJSON.setUpCount(shareinfo.getUpCount());
        ShareInfoJSON.setUserName(userName);
        ShareInfoJSON.setUserFace(latestlottery.getUserFace());
        ShareInfoJSON.setUserId(latestlottery.getUserId()+"");
        ShareInfoJSON.setProductPeriod(latestlottery.getProductPeriod());
        ShareJSONList.add(ShareInfoJSON);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return "index";
  }
  
  public void productInfoShareListByProductId()
  {
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("productInfoShareListByProductId_" + id + "_" + pageNo + "_" + pageSize);
    if (page == null)
    {
      page = shareService.loadShareInfoByNew("new20", id, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("productInfoShareListByProductId_" + id + "_" + pageNo + "_" + pageSize, 3600, page);
    }
    List<?>  pageList = page.getList();
    ShareJSONList = new ArrayList();
    for (int i = 0; i < pageList.size(); i++) {
      try
      {
        ShareInfoJSON = new ShareInfoJSON();
        Object[] objects = (Object[])pageList.get(i);
    	  for(Object obj:objects){
    		if(obj instanceof Shareinfo){
    			shareinfo = (Shareinfo)obj;
    		}
    		if(obj instanceof Latestlottery){
    			latestlottery = (Latestlottery)obj;
    		}
    	  }
        //shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        //latestlottery = ((Latestlottery)((Object[])pageList.get(i))[1]);
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
        ShareInfoJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
        ShareInfoJSON.setReplyCount(shareinfo.getReplyCount());
        ShareInfoJSON.setReward(shareinfo.getReward());
        ShareInfoJSON.setShareContent(shareinfo.getShareContent());
        ShareInfoJSON.setShareDate(DateUtil.getTime(DateUtil.SDateTimeToDate(shareinfo.getShareDate())));
        List<Shareimage> shareimageList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_index_shareimageList_page_" + shareinfo.getUid());
        if (shareimageList == null)
        {
          shareimageList = shareService.getShareimage(String.valueOf(shareinfo.getUid()));
          AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_index_shareimageList_page_" + shareinfo.getUid(), 3600, shareimageList);
        }
        ShareInfoJSON.setShareimageList(shareimageList);
        ShareInfoJSON.setShareTitle(shareinfo.getShareTitle());
        ShareInfoJSON.setUid(shareinfo.getUid());
        ShareInfoJSON.setUpCount(shareinfo.getUpCount());
        ShareInfoJSON.setUserName(userName);
        ShareInfoJSON.setUserFace(latestlottery.getUserFace());
        ShareInfoJSON.setUserId(latestlottery.getUserId()+"");
        ShareInfoJSON.setProductPeriod(latestlottery.getProductPeriod());
        ShareJSONList.add(ShareInfoJSON);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(ShareJSONList, new String[0]);
  }
  
  public String productOtherWinUser()
  {
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("productOtherWinUser_" + shareId + "_" + pageNo + "_" + 5);
    if (page == null)
    {
      page = latestlotteryService.getProductOtherWinUser(productId, shareId, pageNo, 5);
      AliyunOcsSampleHelp.getIMemcachedCache().set("productOtherWinUser_" + shareId + "_" + pageNo + "_" + 5, 3600, page);
    }
    latestlotteryList = (List<Latestlottery>)page.getList();
    Struts2Utils.renderJson(latestlotteryList, new String[0]);
    return null;
  }
  
  public String shareCommentListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_shareComment_" + shareId + "_" + pageNo + "_" + pageSize);
    if (datePage == null)
    {
      datePage = shareService.shareByComment(shareId, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_shareComment_" + shareId + "_" + pageNo + "_" + pageSize, 5, datePage);
    }
    List<Object[]>  dataList = (List<Object[]>)datePage.getList();
    shareCommentJSONList = new ArrayList();
    for (Object[] objects : dataList) {
      try
      {
    	  for(Object obj:objects){
    		if(obj instanceof User){
    			user = (User)obj;
    		}
    		if(obj instanceof Sharecomments){
    			sharecomments = (Sharecomments)obj;
    		}
    	  }
        //user = ((User)objects[0]);
        //sharecomments = ((Sharecomments)objects[1]);
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
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(shareCommentJSONList, new String[0]);
    return null;
  }
  
  public void getReCommentList()
  {
    List<Object[]> objectList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("shareShow_shareComment_objectList_" + shareCommentId);
    if (objectList == null)
    {
      objectList = shareService.getReCommentList(shareCommentId);
      AliyunOcsSampleHelp.getIMemcachedCache().set("shareShow_shareComment_objectList_" + shareCommentId, 5, objectList);
    }
    shareCommentJSONList = new ArrayList();
    for (Object[] objects : objectList) {
      try
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
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(shareCommentJSONList, new String[0]);
  }
  
  public void postComment()
  {
    try
    {
      commentText = htmlFilter.filter(commentText);
      shareId = htmlFilter.filter(shareId);
      userId = htmlFilter.filter(userId);
      this.sharecomments = new Sharecomments();
      this.sharecomments.setContent(commentText);
      this.sharecomments.setCreateDate(DateUtil.DateTimeToStr(new Date()));
      if (reCommentId != null)
      {
        this.sharecomments.setReCommentId(Integer.valueOf(Integer.parseInt(reCommentId)));
        Sharecomments sharecomments = shareService.findBySharecommentsId(reCommentId);
        Integer reCount = sharecomments.getReCount();
        sharecomments.setReCount(Integer.valueOf(reCount.intValue() + 1));
        shareService.createComment(sharecomments);
      }
      this.sharecomments.setShareInfoId(Integer.valueOf(Integer.parseInt(shareId)));
      this.sharecomments.setUserId(Integer.valueOf(Integer.parseInt(userId)));
      this.sharecomments.setReCount(Integer.valueOf(0));
      shareService.createComment(this.sharecomments);
      shareinfo = ((Shareinfo)shareService.findById(shareId));
      Integer replyCount = shareinfo.getReplyCount();
      shareinfo.setReplyCount(Integer.valueOf(replyCount.intValue() + 1));
      shareService.add(shareinfo);
      Struts2Utils.renderText("true", new String[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public void upShareInfo()
  {
    try
    {
      shareinfo = ((Shareinfo)shareService.findById(shareId));
      Integer upCount = shareinfo.getUpCount();
      shareinfo.setUpCount(Integer.valueOf(upCount.intValue() + 1));
      shareService.add(shareinfo);
      Struts2Utils.renderText("true", new String[0]);
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("false", new String[0]);
      e.printStackTrace();
    }
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
  
  public ShareInfoPro getShareInfoPro()
  {
    return shareInfoPro;
  }
  
  public void setShareInfoPro(ShareInfoPro shareInfoPro)
  {
    this.shareInfoPro = shareInfoPro;
  }
  
  public Sharecomments getSharecomments()
  {
    return sharecomments;
  }
  
  public void setSharecomments(Sharecomments sharecomments)
  {
    this.sharecomments = sharecomments;
  }
  
  public List<ShareCommentJSON> getShareCommentJSONList()
  {
    return shareCommentJSONList;
  }
  
  public void setShareCommentJSONList(List<ShareCommentJSON> shareCommentJSONList)
  {
    this.shareCommentJSONList = shareCommentJSONList;
  }
  
  public Shareinfo getShareinfo()
  {
    return shareinfo;
  }
  
  public void setShareinfo(Shareinfo shareinfo)
  {
    this.shareinfo = shareinfo;
  }
  
  public List<Shareimage> getShareimageList()
  {
    return shareimageList;
  }
  
  public void setShareimageList(List<Shareimage> shareimageList)
  {
    this.shareimageList = shareimageList;
  }
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public List<Latestlottery> getLatestlotteryList()
  {
    return latestlotteryList;
  }
  
  public void setLatestlotteryList(List<Latestlottery> latestlotteryList)
  {
    this.latestlotteryList = latestlotteryList;
  }
  
  public String getProductId()
  {
    return productId;
  }
  
  public void setProductId(String productId)
  {
    this.productId = productId;
  }
  
  public String getShareId()
  {
    return shareId;
  }
  
  public void setShareId(String shareId)
  {
    this.shareId = shareId;
  }
  
  public ShareCommentJSON getShareCommentJSON()
  {
    return shareCommentJSON;
  }
  
  public void setShareCommentJSON(ShareCommentJSON shareCommentJSON)
  {
    this.shareCommentJSON = shareCommentJSON;
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
  
  public String getShareCommentId()
  {
    return shareCommentId;
  }
  
  public void setShareCommentId(String shareCommentId)
  {
    this.shareCommentId = shareCommentId;
  }
  
  public String getCommentText()
  {
    return commentText;
  }
  
  public void setCommentText(String commentText)
  {
    this.commentText = commentText;
  }
  
  public String getReCommentId()
  {
    return reCommentId;
  }
  
  public void setReCommentId(String reCommentId)
  {
    this.reCommentId = reCommentId;
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
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public List<ShareInfoJSON> getShareJSONList()
  {
    return ShareJSONList;
  }
  
  public void setShareJSONList(List<ShareInfoJSON> shareJSONList)
  {
    ShareJSONList = shareJSONList;
  }
  
  public ShareInfoJSON getShareInfoJSON()
  {
    return ShareInfoJSON;
  }
  
  public void setShareInfoJSON(ShareInfoJSON shareInfoJSON)
  {
    ShareInfoJSON = shareInfoJSON;
  }
  
  public Shareimage getShareimage()
  {
    return shareimage;
  }
  
  public void setShareimage(Shareimage shareimage)
  {
    this.shareimage = shareimage;
  }
}

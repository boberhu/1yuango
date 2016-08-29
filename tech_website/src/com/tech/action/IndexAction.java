package com.tech.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.json.JSONException;
import org.json.JSONObject;

import com.tech.dao.Pagination;
import com.tech.pojo.BuyHistoryJSON;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.IndexImg;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.News;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Producttype;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.pojo.UserJSON;
import com.tech.proto.http.Proxy;
import com.tech.service.CommissionpointsService;
import com.tech.service.LatestlotteryService;
import com.tech.service.NewsService;
import com.tech.service.RecommendService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.CheckMobile;
import com.tech.util.ConfigUtil;
import com.tech.util.DateUtil;
import com.tech.util.EscapeUtil;
import com.tech.util.IPSeeker;
import com.tech.util.RequestUtils;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@Component("IndexAction")
public class IndexAction extends BaseAction
{
  private static final long serialVersionUID = -7084752934617098983L;
  private static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);
  
  @Autowired
  @Qualifier("spellbuyrecordService")
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  @Qualifier("latestlotteryService")
  private LatestlotteryService latestlotteryService;
  @Autowired
  @Qualifier("spellbuyproductService")
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private NewsService newsService;
  @Autowired
  private RecommendService recommendService;
  @Autowired
  private UserService userService;
  @Autowired
  private CommissionpointsService commissionpointsService;
  @Autowired
  Proxy httproxy;
  
  private List<ProductJSON> hotProductList;
  private List<ProductJSON> newProductList;
  private List<ProductJSON> indexPopProductList;
  private List<ProductJSON> productList;
  private List<BuyHistoryJSON> buyHistoryJSONList;
  public static List<News> newsList;
  private BuyHistoryJSON buyHistoryJSON;
  private ProductJSON productJSON;
  private ProductJSON recommendJSON;
  private UserJSON userJSON;
  private Product product;
  private Spellbuyrecord spellbuyrecord;
  private Spellbuyproduct spellbuyproduct;
  private Latestlottery latestlottery;
  private List<Latestlottery> latestlotteryList;
  private User user;
  private String id;
  private String tId;
  private int pageNo;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private String startDate;
  private String endDate;
  private int lotteryCount;
  private List<IndexImg> indexImgList;
  private Commissionpoints commissionpoints;
  private String uid;
  private List<ProductJSON> nowBuyProductList;
  private List<ProductJSON> newRecordList;
  private Long allBuyCount;
  private String code;
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  HttpClient httpClient = null;
  GetMethod getMethod = null;
  
  public static IPSeeker seeker = new IPSeeker();
  
  public String index()
  {
    /*if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      return null;
    }*/
	  /*
	   * 首页显示
	   * 易购OS   易购OS
	   * 判断是否手机访问 自动跳转
	  */
	HttpServletRequest req = ServletActionContext.getRequest();
	String userAgent = req.getHeader("user-agent");
	if(null == userAgent){
		userAgent = "";  
	}else{
		userAgent = userAgent.toLowerCase();
	}
	boolean isFromMobile=CheckMobile.check(userAgent);
	if(isFromMobile){
		Struts2Utils.render("text/html", "<script>window.location.href=\"" + ApplicationListenerImpl.sysConfigureJson.getWeixinUrl() + "\";</script>", new String[] { "encoding:UTF-8" });
		return null;
	}
    List<Latestlottery> objList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("index_objList");
    if (objList == null)
    {
      objList = latestlotteryService.indexWinningScroll();
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_objList", 5, objList);
    }
    latestlotteryList = new ArrayList();
    if ((objList != null) && (objList.size() > 0)) {
      for (int i = 0; i < objList.size(); i++)
      {
        latestlottery = new Latestlottery();
        latestlottery = ((Latestlottery)objList.get(i));
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
        latestlottery.setBuyUser(userName);
        latestlotteryList.add(latestlottery);
      }
    }
    String lCount = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("index_lotteryCount");
    if (lCount == null)
    {
      lotteryCount = latestlotteryService.getLotteryByCount().intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_lotteryCount", 5, String.valueOf(lotteryCount));
    }
    else
    {
      lotteryCount = Integer.parseInt(lCount);
    }
    List<Object[]> objectList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("index_newProduct");
    if (objectList == null)
    {
      objectList = spellbuyproductService.getIndexNewProduct();
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_newProduct", 5, objectList);
    }
    newProductList = new ArrayList();
    for (int i = 0; i < objectList.size(); i++)
    {
    	Object[] objs = (Object[])objectList.get(i);
    	if(objs[0] instanceof Product){
    		product = (Product)objs[0];
    		spellbuyproduct = (Spellbuyproduct)objs[1];
    	}else{
    		product = (Product)objs[1];
    		spellbuyproduct = (Spellbuyproduct)objs[0];
    	}
      productJSON = new ProductJSON();
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(product.getProductName());
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
      newProductList.add(productJSON);
    }
    if (newsList == null) {
      newsList = newsService.indexNews();
    }
    indexImgList = JSONArray.fromObject(ApplicationListenerImpl.indexImgAll);
    return "index";
  }
  
  public void getIndexSoonGoodsList()
  {
    Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("index_datePage");
    if (datePage == null)
    {
      datePage = spellbuyproductService.upcomingAnnounced(pageNo, 24);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_datePage", 5, datePage);
    }
    List<Object[]> dateList = (List<Object[]>)datePage.getList();
    productList = new ArrayList<ProductJSON>();
    StringBuilder sbuf = new StringBuilder();
    for (int i = 0; i < dateList.size(); i++)
    {
    	Object[] objs = (Object[])dateList.get(i);
    	if(objs[0] instanceof Product){
    		product = (Product)objs[0];
    		spellbuyproduct = (Spellbuyproduct)objs[1];
    	}else{
    		product = (Product)objs[1];
    		spellbuyproduct = (Spellbuyproduct)objs[0];
    	}
      productJSON = new ProductJSON();
      spellbuyproduct.setProduct(product);
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      productList.add(productJSON);
    }
    Struts2Utils.renderJson(productList, new String[0]);
  }
  
  public void getIndexHotProductList()
  {
    Pagination hotPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("index_hotPage");
    if (hotPage == null)
    {
      hotPage = spellbuyrecordService.findHotProductList(1, 8);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_hotPage", 5, hotPage);
    }
    List<Object[]> HotList = (List<Object[]>)hotPage.getList();
    hotProductList = new ArrayList();
    StringBuilder sbuf = new StringBuilder();
    for (int i = 0; i < HotList.size(); i++)
    {
    	Object[] objs = (Object[])HotList.get(i);
    	if(objs[0] instanceof Product){
    		product = (Product)objs[0];
    		spellbuyproduct = (Spellbuyproduct)objs[1];
    	}else{
    		product = (Product)objs[1];
    		spellbuyproduct = (Spellbuyproduct)objs[0];
    	}
      productJSON = new ProductJSON();
      spellbuyproduct.setProduct(product);
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      hotProductList.add(productJSON);
    }
    Struts2Utils.renderJson(hotProductList, new String[0]);
  }
  
  public void getIndexNewProductList()
  {
    Pagination phoneDigitalPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("index_phoneDigitalPage");
    if (phoneDigitalPage == null)
    {
      phoneDigitalPage = spellbuyrecordService.indexNewProductList(pageNo, 12);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_phoneDigitalPage", 5, phoneDigitalPage);
    }
    List<Object[]> phoneDigitalList = (List<Object[]>)phoneDigitalPage.getList();
    newProductList = new ArrayList();
    StringBuilder sbuf = new StringBuilder();
    for (int i = 0; i < phoneDigitalList.size(); i++)
    {
    	Object[] objs = (Object[])phoneDigitalList.get(i);
    	if(objs[0] instanceof Product){
    		product = (Product)objs[0];
    		spellbuyproduct = (Spellbuyproduct)objs[1];
    	}else{
    		product = (Product)objs[1];
    		spellbuyproduct = (Spellbuyproduct)objs[0];
    	}
      productJSON = new ProductJSON();
      spellbuyproduct.setProduct(product);
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      newProductList.add(productJSON);
    }
    Struts2Utils.renderJson(newProductList, new String[0]);
  }
  
  public void getIndexPopProductList()
  {
    Pagination ComputerOfficePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("index_ComputerOfficePage");
    if (ComputerOfficePage == null)
    {
      ComputerOfficePage = spellbuyrecordService.indexHotProductList(pageNo, 8);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_ComputerOfficePage", 5, ComputerOfficePage);
    }
    List<?> ComputerOfficeList = ComputerOfficePage.getList();
    indexPopProductList = new ArrayList<ProductJSON>();
    StringBuilder sbuf = new StringBuilder();
    for (int i = 0; i < ComputerOfficeList.size(); i++)
    {
    	Object[] objs = (Object[])ComputerOfficeList.get(i);
    	if(objs[0] instanceof Product){
    		product = (Product)objs[0];
    		spellbuyproduct = (Spellbuyproduct)objs[1];
    	}else{
    		product = (Product)objs[1];
    		spellbuyproduct = (Spellbuyproduct)objs[0];
    	}
      productJSON = new ProductJSON();
      spellbuyproduct.setProduct(product);
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      indexPopProductList.add(productJSON);
    }
    Struts2Utils.renderJson(indexPopProductList, new String[0]);
  }
  
  public void getIndexUserInfo()
  {
    user = ((User)userService.findById(id));
    if (user != null)
    {
      userJSON = new UserJSON();
      userJSON.setUserBalance(user.getBalance().doubleValue());
      userJSON.setUserExperience(user.getExperience().intValue());
      userJSON.setUserFace(user.getFaceImg());
      userJSON.setUserId(user.getUserId().toString());
      if (user.getExperience().intValue() < 10000)
      {
        userJSON.setUserLevel(1);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "小将");
      }
      else if (user.getExperience().intValue() < 50000)
      {
        userJSON.setUserLevel(2);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "少将");
      }
      else if (user.getExperience().intValue() < 100000)
      {
        userJSON.setUserLevel(3);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "中将");
      }
      else if (user.getExperience().intValue() < 500000)
      {
        userJSON.setUserLevel(4);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "上将");
      }
      else if (user.getExperience().intValue() < 1000000)
      {
        userJSON.setUserLevel(5);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "少校");
      }
      else if (user.getExperience().intValue() < 2000000)
      {
        userJSON.setUserLevel(6);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "中校");
      }
      else if (user.getExperience().intValue() < 5000000)
      {
        userJSON.setUserLevel(7);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "上校");
      }
      else if (user.getExperience().intValue() < 10000000)
      {
        userJSON.setUserLevel(7);
        userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName() + "元帅");
      }
      userJSON.setUserName(UserNameUtil.userName(user));
      Struts2Utils.renderJson(userJSON, new String[0]);
    }
  }
  
  public void getNowBuyProduct()
  {
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("index_getNowBuyProduct");
    if (page == null)
    {
      page = spellbuyrecordService.getNowBuyList(pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_getNowBuyProduct", 5, page);
    }
    List<?> newBuyList = page.getList();
    nowBuyProductList = new ArrayList<ProductJSON>();
    for (int i = 0; i < newBuyList.size(); i++)
    {
      productJSON = new ProductJSON();
      Object[] objs = (Object[])newBuyList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Product){
  			product = (Product)obj;
  		}
  		if(obj instanceof Spellbuyrecord){
  			spellbuyrecord = (Spellbuyrecord)obj;
  		}
  		if(obj instanceof User){
  			user = (User)obj;
  		}
  		if(obj instanceof Spellbuyproduct){
  			spellbuyproduct = (Spellbuyproduct)obj;
  		}
  	  }
  	
      //product = ((Product)((Object[])newBuyList.get(i))[0]);
      //spellbuyrecord = ((Spellbuyrecord)((Object[])newBuyList.get(i))[1]);
      //user = ((User)((Object[])newBuyList.get(i))[2]);
      //spellbuyproduct = ((Spellbuyproduct)((Object[])newBuyList.get(i))[3]);
      String userName = UserNameUtil.userName(user);
      productJSON.setBuyer(userName);
      productJSON.setUserId(String.valueOf(user.getUserId()));
      productJSON.setHeadImage(user.getFaceImg());
      productJSON.setProductId(product.getProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      /*if (spellbuyproduct.getSpStatus().intValue() == 1) {
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
      } else {
        productJSON.setProductId(product.getProductId());
      }*/
      productJSON.setProductName(product.getProductName());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setBuyDate(spellbuyrecord.getBuyDate());
      nowBuyProductList.add(productJSON);
    }
    Collections.sort(nowBuyProductList, new Comparator<ProductJSON>()
    {
      public int compare(ProductJSON arg0, ProductJSON arg1)
      {
        return arg1.getBuyDate().compareTo(arg0.getBuyDate());
      }
    });
    Struts2Utils.renderJson(nowBuyProductList, new String[0]);
  }
  
  public void typeList()
  {
	  List<Producttype> list = getProtypeList();
	  for(Producttype type:list){
		  type.setSubtypes(protypeService.listSubs(type.getTypeId().toString()));
	  }
	  Struts2Utils.renderJson(list);
  }
  
  public void getProductsByType()
  {
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("getProductsByType_"+tId);
    if (page == null)
    {
      page = spellbuyrecordService.ProductByTypeIdList(tId, null, "surplus", pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("getProductsByType_"+tId, 5, page);
    }
    List<?> newBuyList = page.getList();
    List<ProductJSON> typeProductList = new ArrayList<ProductJSON>();
    for (int i = 0; i < newBuyList.size(); i++)
    {
      productJSON = new ProductJSON();
      Object[] objs = (Object[])newBuyList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Product){
  			product = (Product)obj;
  		}
  		if(obj instanceof Spellbuyproduct){
  			spellbuyproduct = (Spellbuyproduct)obj;
  		}
  	  }
  	  spellbuyproduct.setProduct(product);
  	  productJSON.setProductId(product.getProductId());
	  productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
	  productJSON.setProductTitle(product.getProductTitle());
	  productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
  	  productJSON.setHeadImage(product.getHeadImage());
  	  productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
  	  productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
  	  productJSON.setProductStyle(product.getStyle());
  	  productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      productJSON.setProductName(product.getProductName());
      productJSON.setProductTitle(product.getProductTitle());
      typeProductList.add(productJSON);
    }
    Struts2Utils.renderJson(typeProductList, new String[0]);
  }
  
  public void getServerTime()
  {
    Long endDate = Long.valueOf(DateUtil.SDateTimeToDate(id).getTime());
    Long nowDate = Long.valueOf(System.currentTimeMillis());
    if (endDate.longValue() > nowDate.longValue()) {
      Struts2Utils.renderText(((endDate.longValue() - nowDate.longValue()) / 1000L)+"", new String[0]);
    } else {
      Struts2Utils.renderText(((nowDate.longValue() - endDate.longValue()) / 1000L)+"", new String[0]);
    }
  }
  
  public static void main(String[] args)
  {
    Date date = new Date();
    
    date = DateUtil.SDateTimeToDate("2014-10-23 15:42:47");
    System.err.println(date.getTime());
    System.err.println(DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime());
    System.err.println(DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime() - date.getTime());
    System.err.println((DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime() - date.getTime()) / 60L);
    System.err.println(System.currentTimeMillis());
  }
  
  public String referAuth()
  {
    HttpServletRequest request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          uid = cookie.getValue();
        }
      }
    }
    return "referAuthLogin";
  }
  
  public void getshorturl()
  {
    try
    {
      httpClient = new HttpClient();
      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
      httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
      getMethod = new GetMethod("https://api.weibo.com/2/short_url/shorten.json?source=1681459862&url_long=" + uid);
      getMethod.addRequestHeader("Connection", "close");
      
      getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
      
      getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(5000));
      getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, true));
      httpClient.getParams().setParameter("http.protocol.content-charset", "gb2312");
      httpClient.getParams().setContentCharset("gb2312");
      httpClient.getParams().setCredentialCharset("gb2312");
      int statusCode = httpClient.executeMethod(getMethod);
      String html = getMethod.getResponseBodyAsString();
      Struts2Utils.renderJson(html, new String[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String share()
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    if (request.isRequestedSessionIdFromCookie())
    {
      Cookie cookie = new Cookie("inviteId", uid);
      cookie.setMaxAge(43200);
      cookie.setPath("/");
      cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
      response.addCookie(cookie);
    }
    String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
    if (ip == null) {
      ip = "127.0.0.1";
    }
    Struts2Utils.render("text/html", "<script>window.location.href=\"/?share=" + uid + "\";</script>", new String[] { "encoding:UTF-8" });
    return null;
  }
  
	public String wxback() throws JSONException, HttpException, UnsupportedEncodingException
	{
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		Cookie[] cookies = request.getCookies();
		if(StringUtil.isEmpty(code)){
			return null;
		}
		String userAgent = request.getHeader("user-agent");
		if(null == userAgent){
			userAgent = "";  
		}else{
			userAgent = userAgent.toLowerCase();
		}
		boolean isFromMobile=CheckMobile.check(userAgent);
		if(isFromMobile){
			StringBuffer sb = new StringBuffer(ApplicationListenerImpl.sysConfigureJson.getWeixinUrl());
			if (request.getQueryString() != null) {
				sb.append("?").append(request.getQueryString());
			}
			Struts2Utils.render("text/html", "<script>window.location.href=\"" + sb.toString() + "\";</script>", new String[] { "encoding:UTF-8" });
			return null;
		}
		String appid = ApplicationListenerImpl.sysConfigureJson.getWxAppID();
		String secret = ApplicationListenerImpl.sysConfigureJson.getWxAppSecret();
		//String appid = ConfigUtil.getValue("wxAppID");
		//String secret = ConfigUtil.getValue("wxAppSecret");
		String grantType = "authorization_code";
		JSONObject jsonObject = new JSONObject(httproxy.get("https://api.weixin.qq.com/sns/oauth2/access_token?"+ 
				"appid="+ appid + "&secret=" + secret + "&code=" + code+
				"&grant_type=" + grantType, null));
		String token = jsonObject.getString("access_token");
		String openid = jsonObject.getString("openid");
		String unionid = null;
		if(jsonObject.has("unionid")){
			unionid = jsonObject.getString("unionid");
		}
		if (token == null || openid == null) {
			return null;
		}
		String ip = request.getHeader("X-Real-IP");
		if (ip == null) {
			ip = "127.0.0.1";
		}
		if(unionid!=null){
			user = userService.userByWeixinUnionId(unionid);
		}else{
			user = userService.userByWebWxOpenId(openid);
		}
		if(user!=null){
			//System.out.println("wxu!=null");
			if(!"0".equals(user.getMailCheck()) && !"0".equals(user.getMobileCheck())){
				String ipLocation = RegisterAction.seeker.getAddress(ip);
				String date = DateUtil.DateTimeToStr(new Date());
				user.setIpAddress(ip);
				user.setIpLocation(ipLocation);
				user.setNewDate(date);
				userService.add(user);
				
				Struts2Utils.render("text/html", "<script>window.location.href=\"/register/wxUserInfoAuth.html\";</script>", new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils.render("text/html", "<script>window.location.href=\"/\";</script>", new String[] { "encoding:UTF-8" });
			}
		}else{
			JSONObject result = new JSONObject(httproxy.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + token+ 
					"&openid=" + openid, null));
			String nickName = result.get("nickname").toString();
			if(result.has("unionid")){
				unionid = result.getString("unionid");
			}
			user = new User();
			user.setWebWxOpenId(openid);
			user.setWeixinUnionId(unionid);
		    String date = DateUtil.DateTimeToStr(new Date());
		    if ((nickName != null) && (!nickName.equals(""))) {
		      user.setUserName(nickName.trim());
		    }
		    user.setUserPwd(openid);
		    user.setMobileCheck("3");
		    user.setMailCheck("3");
		    //user.setAttribute22(openId);
		    user.setIpAddress(ip);
		    String ipLocation = seeker.getAddress(ip);
		    user.setIpLocation(ipLocation);
		    user.setOldDate(date);
		    
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
		    Struts2Utils.render("text/html", "<script>window.location.href=\"/register/wxUserInfoAuth.html\";</script>", new String[] { "encoding:UTF-8" });
		}
		if (request.isRequestedSessionIdFromCookie() && user!=null)
	    {
	      Cookie cookie = new Cookie("userName", EscapeUtil.escape(user.getUserName()));
	      cookie.setMaxAge(31536000);
	      cookie.setPath("/");
	      cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie);
	      Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
	      cookie2.setMaxAge(31536000);
	      cookie2.setPath("/");
	      cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie2);
	      Cookie cookie3 = new Cookie("face", EscapeUtil.escape(user.getFaceImg()));
	      //Cookie cookie3 = new Cookie("face", user.getFaceImg());
	      cookie3.setMaxAge(31536000);
	      cookie3.setPath("/");
	      cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie3);
	      Cookie cookie4 = new Cookie("loginUser", EscapeUtil.escape(user.getUserName()));
	      cookie4.setMaxAge(31536000);
	      cookie4.setPath("/");
	      cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie4);
	    }
	    return null;
	}
	
	public String qcback() 
	{
		try{
		
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		Cookie[] cookies = request.getCookies();
		System.out.println("====qcback============="+code);
		if(StringUtil.isEmpty(code)){
			return null;
		}
		String userAgent = request.getHeader("user-agent");
		if(null == userAgent){
			userAgent = "";  
		}else{
			userAgent = userAgent.toLowerCase();
		}
		boolean isFromMobile=CheckMobile.check(userAgent);
		if(isFromMobile){
			StringBuffer sb = new StringBuffer(ApplicationListenerImpl.sysConfigureJson.getWeixinUrl());
			if (request.getQueryString() != null) {
				sb.append("?").append(request.getQueryString());
			}
			Struts2Utils.render("text/html", "<script>window.location.href=\"" + sb.toString() + "\";</script>", new String[] { "encoding:UTF-8" });
			return null;
		}
		String clientId = ApplicationListenerImpl.sysConfigureJson.getQqAppId();
		String appKey = ApplicationListenerImpl.sysConfigureJson.getQqAppKey();
		//String appid = ConfigUtil.getValue("wxAppID");
		//String secret = ConfigUtil.getValue("wxAppSecret");
		String grantType = "authorization_code";
		System.out.println("=====jsonObject="+"https://graph.qq.com/oauth2.0/token?"+ 
					"client_id="+ clientId + "&client_secret=" + appKey + "&code=" + code+
					"&grant_type=" + grantType);
			JSONObject jsonObject = new JSONObject(httproxy.get("https://graph.qq.com/oauth2.0/token?"+ 
					"client_id="+ clientId + "&client_secret=" + appKey + "&code=" + code+
					"&grant_type=" + grantType, null));
			String token = jsonObject.getString("access_token");
			
			String result = httproxy.get("https://graph.qq.com/oauth2.0/me?"+ "access_token=" +token, null);
			System.out.println("result="+result);
			JSONObject js = new JSONObject(result.substring("callback(".length(), result.length()-2).trim());
			String openid= js.getString("openid");

			if (token == null || openid == null) {
				return null;
			}
			String ip = request.getHeader("X-Real-IP");
			if (ip == null) {
				ip = "127.0.0.1";
			}
			user = userService.isNotOpenId(openid);
		
		
		if(user!=null){
			//System.out.println("wxu!=null");
			if(!"0".equals(user.getMailCheck()) && !"0".equals(user.getMobileCheck())){
				String ipLocation = RegisterAction.seeker.getAddress(ip);
				String date = DateUtil.DateTimeToStr(new Date());
				user.setIpAddress(ip);
				user.setIpLocation(ipLocation);
				user.setNewDate(date);
				userService.add(user);
				
				Struts2Utils.render("text/html", "<script>window.location.href=\"/register/wxUserInfoAuth.html\";</script>", new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils.render("text/html", "<script>window.location.href=\"/\";</script>", new String[] { "encoding:UTF-8" });
			}
		}else{
			JSONObject json = new JSONObject(httproxy.get("https://graph.qq.com/user/get_user_info?access_token=" + token
					+"&oauth_consumer_key="+clientId+"&openid=" + openid, null));
			System.out.println("json="+json.toString());
			String nickName = json.get("nickname").toString();
			/*if(result.has("unionid")){
				unionid = result.getString("unionid");
			}*/
			user = new User();
		    user.setAttribute22(openid);
		    String date = DateUtil.DateTimeToStr(new Date());
		    if ((nickName != null) && (!nickName.equals(""))) {
		      user.setUserName(nickName.trim());
		    }
		    user.setUserPwd(openid);
		    user.setMobileCheck("3");
		    user.setMailCheck("3");
		    //user.setAttribute22(openId);
		    user.setIpAddress(ip);
		    String ipLocation = seeker.getAddress(ip);
		    user.setIpLocation(ipLocation);
		    user.setOldDate(date);
		    
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
		    Struts2Utils.render("text/html", "<script>window.location.href=\"/register/qqUserInfoAuth.html\";</script>", new String[] { "encoding:UTF-8" });
		}
		if (request.isRequestedSessionIdFromCookie() && user!=null)
	    {
	      Cookie cookie = new Cookie("userName", EscapeUtil.escape(user.getUserName()));
	      cookie.setMaxAge(31536000);
	      cookie.setPath("/");
	      cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie);
	      Cookie cookie2 = new Cookie("userId", String.valueOf(user.getUserId()));
	      cookie2.setMaxAge(31536000);
	      cookie2.setPath("/");
	      cookie2.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie2);
	      Cookie cookie3 = new Cookie("face", EscapeUtil.escape(user.getFaceImg()));
	      //Cookie cookie3 = new Cookie("face", user.getFaceImg());
	      cookie3.setMaxAge(31536000);
	      cookie3.setPath("/");
	      cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie3);
	      Cookie cookie4 = new Cookie("loginUser", EscapeUtil.escape(user.getUserName()));
	      cookie4.setMaxAge(31536000);
	      cookie4.setPath("/");
	      cookie4.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
	      this.response.addCookie(cookie4);
	    }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	    return null;
	}
  
  public void getAllBuyCount()
  {
    try
    {
      allBuyCount = ((Long)AliyunOcsSampleHelp.getIMemcachedCache().get("allBuyCount"));
      if (allBuyCount == null)
      {
        allBuyCount = Long.valueOf(spellbuyrecordService.getAllByCount().toString());
        AliyunOcsSampleHelp.getIMemcachedCache().set("allBuyCount", 20, allBuyCount);
      }
      Struts2Utils.renderText(String.valueOf(allBuyCount), new String[0]);
      LOG.debug("allBuyCount: {}", allBuyCount);
    }
    catch (Exception e) {
    	LOG.debug("{}", e);
    }
  }
  
  /*
   * 获取正在购买记录
   * 杨振朋
   * 易购OS
  */
  public String getNewRecord()
  {
	  System.out.println("===========获取正在购买记录---------");
    //newRecordList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("recordList"));
    //if (newRecordList != null) {
      //return "newRecord";
    //}
   // System.out.println("===========获取正在购买222---------"+newRecordList);
    Pagination page = spellbuyrecordService.getNowBuyList(pageNo, 100);
    List<?> newBuyList = page.getList();
    newRecordList = new ArrayList<ProductJSON>();
    for (int i = 0; i < newBuyList.size(); i++)
    {
      productJSON = new ProductJSON();
      Object[] objs = (Object[])newBuyList.get(i);
  	  for(Object obj:objs){
  		if(obj instanceof Product){
  			product = (Product)obj;
  		}
  		if(obj instanceof Spellbuyrecord){
  			spellbuyrecord = (Spellbuyrecord)obj;
  		}
  		if(obj instanceof User){
  			user = (User)obj;
  		}
  		if(obj instanceof Spellbuyproduct){
  			spellbuyproduct = (Spellbuyproduct)obj;
  		}
  	  }
      //product = ((Product)((Object[])newBuyList.get(i))[0]);
      //spellbuyrecord = ((Spellbuyrecord)((Object[])newBuyList.get(i))[1]);
      //user = ((User)((Object[])newBuyList.get(i))[2]);
      //spellbuyproduct = ((Spellbuyproduct)((Object[])newBuyList.get(i))[3]);
      String userName = UserNameUtil.userName(user);
      productJSON.setBuyer(userName);
      productJSON.setUserId(String.valueOf(user.getUserId()));
      productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
      /*if (spellbuyproduct.getSpStatus().intValue() == 1) {
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
      } else {
        productJSON.setProductId(product.getProductId());
      }*/
      if (product.getProductName().length() > 35) {
        productJSON.setProductName(product.getProductName().substring(0, 35) + "...");
      } else {
        productJSON.setProductName(product.getProductName());
      }
      productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
      productJSON.setBuyCount(spellbuyrecord.getBuyPrice());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setBuyDate(spellbuyrecord.getBuyDate());
      productJSON.setProductStyle(String.valueOf(spellbuyrecord.getSpellbuyRecordId()));
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      newRecordList.add(productJSON);
    }
    Collections.sort(newRecordList, new Comparator<ProductJSON>()
    {
      public int compare(ProductJSON arg0, ProductJSON arg1)
      {
        return arg1.getBuyDate().compareTo(arg0.getBuyDate());
      }
    });
   // AliyunOcsSampleHelp.getIMemcachedCache().set("recordList", 5, newRecordList);
    
    return "newRecord";
  }
  
  public void getNewRecordAjax()
  {
    newRecordList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("recordList"));
    if (newRecordList != null)
    {
      Struts2Utils.renderJson(newRecordList, new String[0]);
    }
    else
    {
      Pagination page = spellbuyrecordService.getNowBuyList(pageNo, 100);
      List<?> newBuyList = page.getList();
      newRecordList = new ArrayList<ProductJSON>();
      for (int i = 0; i < newBuyList.size(); i++)
      {
        productJSON = new ProductJSON();
        Object[] objs = (Object[])newBuyList.get(i);
		  for(Object obj:objs){
			if(obj instanceof Product){
				product = (Product)obj;
			}
			if(obj instanceof Spellbuyrecord){
				spellbuyrecord = (Spellbuyrecord)obj;
			}
			if(obj instanceof User){
				user = (User)obj;
			}
			if(obj instanceof Spellbuyproduct){
				spellbuyproduct = (Spellbuyproduct)obj;
			}
		  }
        //product = ((Product)((Object[])newBuyList.get(i))[0]);
        //spellbuyrecord = ((Spellbuyrecord)((Object[])newBuyList.get(i))[1]);
        //user = ((User)((Object[])newBuyList.get(i))[2]);
        //spellbuyproduct = ((Spellbuyproduct)((Object[])newBuyList.get(i))[3]);
        String userName = UserNameUtil.userName(user);
        productJSON.setBuyer(userName);
        productJSON.setUserId(String.valueOf(user.getUserId()));
        if (spellbuyproduct.getSpStatus().intValue() == 1) {
          productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        } else {
          productJSON.setProductId(product.getProductId());
        }
        if (product.getProductName().length() > 35) {
          productJSON.setProductName(product.getProductName().substring(0, 35) + "...");
        } else {
          productJSON.setProductName(product.getProductName());
        }
        productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
        productJSON.setBuyCount(spellbuyrecord.getBuyPrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setBuyDate(spellbuyrecord.getBuyDate());
        productJSON.setProductStyle(String.valueOf(spellbuyrecord.getSpellbuyRecordId()));
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        newRecordList.add(productJSON);
      }
      Collections.sort(newRecordList, new Comparator<ProductJSON>()
      {
        public int compare(ProductJSON arg0, ProductJSON arg1)
        {
          return arg1.getBuyDate().compareTo(arg0.getBuyDate());
        }
      });
      AliyunOcsSampleHelp.getIMemcachedCache().set("recordList", 5, newRecordList);
      Struts2Utils.renderJson(newRecordList, new String[0]);
    }
  }
  
  public String map()
  {
    String lCount = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("index_lotteryCount");
    if (lCount == null)
    {
      lotteryCount = latestlotteryService.getLotteryByCount().intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_lotteryCount", 5, String.valueOf(lotteryCount));
    }
    else
    {
      lotteryCount = Integer.parseInt(lCount);
    }
    return "map";
  }
  
  public String sincerity()
  {
    return "sincerity";
  }
  
  public String lotteryDraw()
  {
    return "lotteryDraw";
  }
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public Spellbuyrecord getSpellbuyrecord()
  {
    return spellbuyrecord;
  }
  
  public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord)
  {
    this.spellbuyrecord = spellbuyrecord;
  }
  
  public Spellbuyproduct getSpellbuyproduct()
  {
    return spellbuyproduct;
  }
  
  public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct)
  {
    this.spellbuyproduct = spellbuyproduct;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public List<ProductJSON> getHotProductList()
  {
    return hotProductList;
  }
  
  public void setHotProductList(List<ProductJSON> hotProductList)
  {
    this.hotProductList = hotProductList;
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
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public List<News> getNewsList()
  {
    return newsList;
  }
  
  public void setNewsList(List<News> newsList)
  {
    newsList = newsList;
  }
  
  public ProductJSON getRecommendJSON()
  {
    return recommendJSON;
  }
  
  public void setRecommendJSON(ProductJSON recommendJSON)
  {
    this.recommendJSON = recommendJSON;
  }
  
  public String getUid()
  {
    return uid;
  }
  
  public void setUid(String uid)
  {
    this.uid = uid;
  }
  
  public List<ProductJSON> getProductList()
  {
    return productList;
  }
  
  public void setProductList(List<ProductJSON> productList)
  {
    this.productList = productList;
  }
  
  public String getStartDate()
  {
    return startDate;
  }
  
  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }
  
  public String getEndDate()
  {
    return endDate;
  }
  
  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }
  
  public List<BuyHistoryJSON> getBuyHistoryJSONList()
  {
    return buyHistoryJSONList;
  }
  
  public void setBuyHistoryJSONList(List<BuyHistoryJSON> buyHistoryJSONList)
  {
    this.buyHistoryJSONList = buyHistoryJSONList;
  }
  
  public BuyHistoryJSON getBuyHistoryJSON()
  {
    return buyHistoryJSON;
  }
  
  public void setBuyHistoryJSON(BuyHistoryJSON buyHistoryJSON)
  {
    this.buyHistoryJSON = buyHistoryJSON;
  }
  
  public List<IndexImg> getIndexImgList()
  {
    return indexImgList;
  }
  
  public void setIndexImgList(List<IndexImg> indexImgList)
  {
    this.indexImgList = indexImgList;
  }
  
  public List<ProductJSON> getNewProductList()
  {
    return newProductList;
  }
  
  public void setNewProductList(List<ProductJSON> newProductList)
  {
    this.newProductList = newProductList;
  }
  
  public void setIndexPopProductList(List<ProductJSON> indexPopProductList)
  {
    this.indexPopProductList = indexPopProductList;
  }
  
  public UserJSON getUserJSON()
  {
    return userJSON;
  }
  
  public void setUserJSON(UserJSON userJSON)
  {
    this.userJSON = userJSON;
  }
  
  public int getLotteryCount()
  {
    return lotteryCount;
  }
  
  public void setLotteryCount(int lotteryCount)
  {
    this.lotteryCount = lotteryCount;
  }
  
  public List<ProductJSON> getNowBuyProductList()
  {
    return nowBuyProductList;
  }
  
  public void setNowBuyProductList(List<ProductJSON> nowBuyProductList)
  {
    this.nowBuyProductList = nowBuyProductList;
  }
  
  public List<ProductJSON> getNewRecordList()
  {
    return newRecordList;
  }
  
  public void setNewRecordList(List<ProductJSON> newRecordList)
  {
    this.newRecordList = newRecordList;
  }
  
  public void setAllBuyCount(Long allBuyCount)
  {
    this.allBuyCount = allBuyCount;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getTId() {
		return tId;
	}

	public void setTId(String tId) {
		this.tId = tId;
	}

}

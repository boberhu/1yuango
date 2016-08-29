package com.tech.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.exception.RuleViolationException;
import com.tech.pojo.Collectproduct;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.LotteryDetailJSON;
import com.tech.pojo.ParticipateJSON;
import com.tech.pojo.Product;
import com.tech.pojo.ProductInfo;
import com.tech.pojo.Productimage;
import com.tech.pojo.RandomNumberJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProductImageService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.ShareService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@Component("ProductsAction")
public class ProductsAction extends BaseAction
{
  private static final long serialVersionUID = 1626790673064716640L;
  @Autowired
  @Qualifier("spellbuyrecordService")
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  @Qualifier("spellbuyproductService")
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private ProductImageService productImageService;
  @Autowired
  private ShareService shareService;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private RandomnumberService randomnumberService;
  
  private ProductInfo productInfo;
  private List<ProductInfo> productInfoList;
  private Product product;
  private List<ParticipateJSON> ParticipateJSONList;
  private Spellbuyrecord spellbuyrecord;
  private List<Spellbuyrecord> spellbuyrecordList;
  private Spellbuyproduct spellbuyproduct;
  private Latestlottery latestlottery;
  private TreeMap<Integer, Integer> productPeriodList;
  private List<Productimage> productimageList;
  private LotteryDetailJSON lotteryDetailJSON;
  private List<LotteryDetailJSON> lotteryDetailJSONList;
  private List<RandomNumberJSON> randomNumberJSONList;
  private RandomNumberJSON randomNumberJSON;
  private User user;
  private String id;
  private String userId;
  private int pageNo;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private String startDate;
  private Long DateSUM = Long.valueOf(0L);
  private String isLotteryJSON;
  private Long endDate;
  private Long nowDate;
  HttpServletRequest request = null;
  Calendar calendar = Calendar.getInstance();
  
  public String index()
    throws ServletException, IOException
  {
    /*if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      return null;
    }*/
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    ParticipateJSONList = new ArrayList();
    List<Object[]> proList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_proList_" + id);
    if (proList == null)
    {
      proList = spellbuyproductService.findBySpellbuyProductId(Integer.parseInt(id));
      /*if (proList.size() == 0)
      {
        proList = spellbuyproductService.findBySpellbuyProductId(Integer.parseInt(id));
        if (proList.size() == 0) {
          proList = spellbuyproductService.findBylotteryDetailByProductId(Integer.parseInt(id));
        }
      }*/
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_proList_" + id, 2, proList);
    }
    productInfo = new ProductInfo();
    Object[] objs = (Object[])proList.get(0);
	if(objs[0] instanceof Product){
		product = (Product)objs[0];
		spellbuyproduct = (Spellbuyproduct)objs[1];
	}else{
		product = (Product)objs[1];
		spellbuyproduct = (Spellbuyproduct)objs[0];
	}
    if(product.getStatus()!=1){
    	throw new RuleViolationException("产品已经下架！");
    }
    productInfo.setProductPeriod(spellbuyproduct.getProductPeriod());
    productInfo.setStatus(spellbuyproduct.getSpStatus());
    productInfo.setHeadImage(product.getHeadImage());
    productInfo.setProductDetail(product.getProductDetail());
    productInfo.setProductName(product.getProductName());
    productInfo.setProductPrice(spellbuyproduct.getSpellbuyPrice());
    productInfo.setSinglePrice(spellbuyproduct.getSpSinglePrice());
    productInfo.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
    productInfo.setProductTitle(product.getProductTitle());
    productInfo.setSpellbuyCount(spellbuyproduct.getSpellbuyCount());
    productInfo.setProductId(product.getProductId());
    productInfo.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());

    Pagination periodPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_periodPage_" + spellbuyproduct.getFkProductId());
    if (periodPage == null)
    {
      periodPage = spellbuyproductService.productPeriodList(spellbuyproduct.getFkProductId(), 1, 10);
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_periodPage_" + spellbuyproduct.getFkProductId(), 2, periodPage);
    }
    productPeriodList = new TreeMap(new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        return o2.hashCode() - o1.hashCode();
      }
    });
    List<Object[]> objectList = (List<Object[]>)periodPage.getList();
    for (Object[] objects : objectList)
    {
    	if(objects[0] instanceof Spellbuyproduct){
    		spellbuyproduct = (Spellbuyproduct)objects[0];
    	}else{
    		spellbuyproduct = (Spellbuyproduct)objects[1];
    	}
        productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
      /*if (spellbuyproduct.getSpStatus().intValue() == 0) {
        productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getFkProductId());
      } else {
        productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
      }*/
    }
    if (this.productPeriodList.size() > 1) {
    	List list = this.latestlotteryService.getByProductHistoryWin(this.productInfo.getProductId());
    	if(list.size()>0){
    		latestlottery = ((Latestlottery)list.get(0));
    	}
      }
    productimageList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_productimageList_" + product.getProductId() + "_show"));
    if (productimageList == null)
    {
      productimageList = productImageService.findByProductId(String.valueOf(product.getProductId()), "show");
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_productimageList_" + product.getProductId() + "_show", 3600, productimageList);
    }
    Pagination pagination = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_pagination_" + productInfo.getSpellbuyProductId() + "_" + pageNo);
    if (pagination == null)
    {
      pagination = spellbuyrecordService.LatestParticipate(productInfo.getSpellbuyProductId()+"", pageNo, 20);
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_pagination_" + productInfo.getSpellbuyProductId() + "_" + pageNo, 2, pagination);
    }
    List<?> list = pagination.getList();
    for (int i = 0; i < list.size(); i++)
    {
      ParticipateJSON participateJSON = new ParticipateJSON();
      Object[] objects = (Object[])list.get(i);
  	if(objects[0] instanceof Spellbuyrecord){
  		spellbuyrecord = (Spellbuyrecord)objects[0];
  		user = (User)objects[1];
  	}else{
  		spellbuyrecord = (Spellbuyrecord)objects[1];
  		user = (User)objects[0];
  	}
      //spellbuyrecord = ((Spellbuyrecord)((Object[])list.get(i))[0]);
      //user = ((User)((Object[])list.get(i))[1]);
      String userName = UserNameUtil.userName(user);
      participateJSON.setBuyCount(spellbuyrecord.getBuyPrice().toString());
      participateJSON.setBuyDate(DateUtil.getTime(DateUtil.SDateTimeToDate(spellbuyrecord.getBuyDate())));
      participateJSON.setBuyId(spellbuyrecord.getSpellbuyRecordId().toString());
      /*participateJSON.setIp_address(user.getIpAddress());
      participateJSON.setIp_location(user.getIpLocation());*/
      participateJSON.setIp_address(spellbuyrecord.getBuyIp());
      participateJSON.setIp_location(spellbuyrecord.getBuyLocal());
      participateJSON.setUserName(userName);
      participateJSON.setUserId(String.valueOf(user.getUserId()));
      participateJSON.setUserFace(user.getFaceImg());
      ParticipateJSONList.add(participateJSON);
    }
    String rCount = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_resultCount_" + productInfo.getSpellbuyProductId());
    if (rCount == null)
    {
      resultCount = spellbuyrecordService.LatestParticipateByCount(productInfo.getSpellbuyProductId()+"").intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_resultCount_" + productInfo.getSpellbuyProductId(), 2, String.valueOf(resultCount));
    }
    else
    {
      resultCount = Integer.parseInt(rCount);
    }
    String pCount = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_pageCount_" + productInfo.getProductId());
    if (pCount == null)
    {
      pageCount = shareService.loadShareInfoByNewByCount(productInfo.getProductId()+"").intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_pageCount_" + productInfo.getProductId(), 2, String.valueOf(pageCount));
    }
    else
    {
      pageCount = Integer.parseInt(pCount);
    }
    if (productInfo.getStatus().intValue() == 2) {
      return "lottery";
    }
    if (productInfo.getStatus().intValue() == 1)
    {
      HttpServletResponse response = Struts2Utils.getResponse();
      response.sendRedirect("/lotteryDetail/" + productInfo.getSpellbuyProductId() + ".html");
      
      return null;
    }
    return "index";
  }
  
  public void checkUserLimitBuy()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    int code = 0;
    int codeLimitBuy = 0;
    int buyNum = 0;
    int codeState = 0;
    int codeRemainNum = 0;
    Double userMoney = Double.valueOf(0.0D);
    List<Object[]> proList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("product_spellbuyproduct_" + id);
    if (proList == null)
    {
      proList = spellbuyproductService.findByProductId(Integer.parseInt(id));
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_spellbuyproduct_" + id, 2, proList);
    }
    try
    {
    	Object[] objects = (Object[])proList.get(0);
      	if(objects[0] instanceof Spellbuyproduct){
      		spellbuyproduct = (Spellbuyproduct)objects[0];
      	}else{
      		spellbuyproduct = (Spellbuyproduct)objects[1];
      	}
      //spellbuyproduct = ((Spellbuyproduct)((Object[])proList.get(0))[1]);
      if (spellbuyproduct != null)
      {
        if (spellbuyproduct.getSpStatus().intValue() != 0)
        {
          code = -5;
        }
        else
        {
          if (spellbuyproduct.getSpellbuyLimit().intValue() > 0)
          {
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
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
              buyNum = limitNum.intValue();
              userMoney = user.getBalance();
              if (buyNum == spellbuyproduct.getSpellbuyLimit().intValue()) {
                code = -6;
              }
            }
            else
            {
              code = -10;
            }
            codeLimitBuy = spellbuyproduct.getSpellbuyLimit().intValue();
          }
          codeRemainNum = spellbuyproduct.getSpellbuyPrice().intValue() - spellbuyproduct.getSpellbuyCount().intValue();
        }
        codeState = spellbuyproduct.getSpStatus().intValue();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    sb.append('{');
    sb.append("\"code\":").append(code).append(",");
    sb.append("\"codeLimitBuy\":").append(codeLimitBuy).append(",");
    sb.append("\"buyNum\":").append(buyNum).append(",");
    sb.append("\"codeState\":").append(codeState).append(",");
    sb.append("\"codeRemainNum\":").append(codeRemainNum).append(",");
    sb.append("\"userMoney\":").append(userMoney).append("");
    sb.append('}');
    

    Struts2Utils.renderJson(sb.toString(), new String[0]);
  }
  
  public void getGoodsPeriodPage()
  {
    Pagination periodPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_periodPage_" + id + "_" + pageNo);
    if (periodPage == null)
    {
      periodPage = spellbuyproductService.productPeriodList(Integer.valueOf(Integer.parseInt(id)), pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_periodPage_" + id + "_" + pageNo, 2, periodPage);
    }
    productInfoList = new ArrayList();
    Integer count = spellbuyproductService.productPeriodListByCount(Integer.valueOf(Integer.parseInt(id)));
    List<Object[]> objectList = (List<Object[]>)periodPage.getList();
    if (objectList != null) {
      for (Object[] objects : objectList)
      {
        spellbuyproduct = ((Spellbuyproduct)objects[1]);
        productInfo = new ProductInfo();
        
        productInfo.setStatus(spellbuyproduct.getSpStatus());
        productInfo.setProductPeriod(spellbuyproduct.getProductPeriod());
        productInfo.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productInfo.setProductId(spellbuyproduct.getFkProductId());
        productInfo.setSpellbuyCount(count);
        productInfoList.add(productInfo);
      }
    }
    Struts2Utils.renderJson(productInfoList, new String[0]);
  }
  
  public void getGoodsPeriodInfo()
  {
    List list = spellbuyproductService.getGoodsPeriodInfo(Integer.valueOf(Integer.parseInt(id)), Integer.valueOf(Integer.parseInt(startDate)));
    if (list.size() > 0) {
      Struts2Utils.renderJson(list, new String[0]);
    } else {
      Struts2Utils.renderText("-1", new String[0]);
    }
  }
  
  public void checkCollectGoods()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    if (StringUtil.isNotBlank(userId))
    {
      List list = spellbuyproductService.checkCollectGoods(Integer.valueOf(Integer.parseInt(id)), userId);
      if (list.size() > 0) {
        Struts2Utils.renderText("0", new String[0]);
      } else {
        Struts2Utils.renderText("-1", new String[0]);
      }
    }
    else
    {
      Struts2Utils.renderText("10", new String[0]);
    }
  }
  
  public void addCollectGoods()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    if (StringUtil.isNotBlank(userId)) {
      try
      {
        Collectproduct collectproduct = new Collectproduct();
        collectproduct.setCollectProductId(Integer.valueOf(Integer.parseInt(id)));
        collectproduct.setCollectUserId(Integer.valueOf(Integer.parseInt(userId)));
        spellbuyproductService.addCollectGoods(collectproduct);
        Struts2Utils.renderText("0", new String[0]);
      }
      catch (Exception e)
      {
        Struts2Utils.renderText("-1", new String[0]);
      }
    } else {
      Struts2Utils.renderText("10", new String[0]);
    }
  }
  
  public void delCollectGoods()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
        }
      }
    }
    if (StringUtil.isNotBlank(userId))
    {
      List list = spellbuyproductService.checkCollectGoods(Integer.valueOf(Integer.parseInt(id)), userId);
      if (list.size() > 0)
      {
        Collectproduct collectproduct = (Collectproduct)list.get(0);
        spellbuyproductService.delCollectGoods(collectproduct.getId());
        Struts2Utils.renderText("0", new String[0]);
      }
      else
      {
        Struts2Utils.renderText("-1", new String[0]);
      }
    }
    else
    {
      Struts2Utils.renderText("10", new String[0]);
    }
  }
  
  public String ajaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    ParticipateJSONList = new ArrayList();
    Pagination pagination = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("product_getProductNewList_pagination_" + id + "_" + pageNo + "_" + pageSize);
    if (pagination == null)
    {
      pagination = spellbuyrecordService.LatestParticipate(id, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_getProductNewList_pagination_" + id + "_" + pageNo + "_" + pageSize, 5, pagination);
    }
    List<?> list = pagination.getList();
    for (int i = 0; i < list.size(); i++)
    {
      ParticipateJSON participateJSON = new ParticipateJSON();
      Object[] objects = (Object[])list.get(i);
    	if(objects[0] instanceof Spellbuyrecord){
    		spellbuyrecord = (Spellbuyrecord)objects[0];
    		user = (User)objects[1];
    	}else{
    		spellbuyrecord = (Spellbuyrecord)objects[1];
    		user = (User)objects[0];
    	}
      //spellbuyrecord = ((Spellbuyrecord)((Object[])list.get(i))[0]);
      //user = ((User)((Object[])list.get(i))[1]);
      String userName = UserNameUtil.userName(user);
      participateJSON.setBuyCount(spellbuyrecord.getBuyPrice().toString());
      participateJSON.setBuyDate(spellbuyrecord.getBuyDate());
      participateJSON.setBuyId(spellbuyrecord.getSpellbuyRecordId().toString());
      //participateJSON.setSpellProductId(spellbuyrecord.getFkSpellbuyProductId().toString());
      /*participateJSON.setIp_address(user.getIpAddress());
      participateJSON.setIp_location(user.getIpLocation());*/
      participateJSON.setIp_address(spellbuyrecord.getBuyIp());
      participateJSON.setIp_location(spellbuyrecord.getBuyLocal());
      participateJSON.setBuySource(spellbuyrecord.getBuySource());
      participateJSON.setUserName(userName);
      participateJSON.setUserId(String.valueOf(user.getUserId()));
      participateJSON.setUserFace(user.getFaceImg());
      ParticipateJSONList.add(participateJSON);
    }
    Struts2Utils.renderJson(ParticipateJSONList, new String[0]);
    return null;
  }
  
  public void getUserByHistory()
  {
    request = Struts2Utils.getRequest();
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
            spellbuyrecordList = spellbuyrecordService.getUserByHistory(userId, id);
            Struts2Utils.renderJson(spellbuyrecordList, new String[0]);
          }
        }
      }
    }
  }
  
  public void isLottery()
  {
    String key = MD5Util.encode(id + "status");
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(key) == null)
    {
      spellbuyproduct = ((Spellbuyproduct)spellbuyproductService.findById(id));
      endDate = Long.valueOf(DateUtil.SDateTimeToDate(spellbuyproduct.getSpellbuyEndDate()).getTime());
      nowDate = Long.valueOf(System.currentTimeMillis());
      isLotteryJSON = ("{\"spStatus\":\"" + spellbuyproduct.getSpStatus() + "\",\"date\":\"" + (endDate.longValue() - nowDate.longValue()) / 1000L + "\"}");
      AliyunOcsSampleHelp.getIMemcachedCache().set(key, 900, String.valueOf(endDate));
      Struts2Utils.renderJson(isLotteryJSON, new String[0]);
    }
    else
    {
      endDate = Long.valueOf(Long.parseLong((String)AliyunOcsSampleHelp.getIMemcachedCache().get(key)));
      nowDate = Long.valueOf(System.currentTimeMillis());
      isLotteryJSON = ("{\"spStatus\":\"2\",\"date\":\"" + (endDate.longValue() - nowDate.longValue()) / 1000L + "\"}");
      Struts2Utils.renderJson(isLotteryJSON, new String[0]);
    }
  }
  
  public void getRandomNumberListPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    Pagination datePage = spellbuyrecordService.getRandomNumberListPage(id, userId, pageNo, 50);
    List<Randomnumber> dataList = (List<Randomnumber>)datePage.getList();
    randomNumberJSONList = new ArrayList();
    for (Randomnumber randomnumber : dataList) {
      try
      {
        randomNumberJSON = new RandomNumberJSON();
        String[] randoms = randomnumber.getRandomNumber().split(",");
        String numbers = "";
        for (String string : randoms) {
          numbers = numbers + "<dd>" + string + "</dd>";
        }
        randomNumberJSON.setRandomNumbers(numbers);
        randomNumberJSON.setBuyCount(String.valueOf(randoms.length));
        randomNumberJSON.setBuyDate(randomnumber.getBuyDate());
        randomNumberJSONList.add(randomNumberJSON);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(randomNumberJSONList, new String[0]);
  }
  
  public void getUserBuyCodeByBuyid()
  {
	  try
	    {
		  Randomnumber randomnumber = ((Randomnumber)AliyunOcsSampleHelp.getIMemcachedCache().get("products_getUserBuyCodeByBuyid_randomnumber_" + id));
	      if (randomnumber == null)
	      {
	        randomnumber = randomnumberService.getUserBuyCodeByBuyid(id);
	        AliyunOcsSampleHelp.getIMemcachedCache().set("products_getUserBuyCodeByBuyid_randomnumber_" + id, 0, randomnumber);
	      }
	      if (randomnumber != null)
	      {
	        List<String> numberList = new ArrayList();
	        String[] randoms = randomnumber.getRandomNumber().split(",");
	        for (String string : randoms) {
	          numberList.add(string);
	        }
	        Struts2Utils.renderJson(numberList, new String[0]);
	      }
	    }
	    catch (Exception e)
	    {
	      Struts2Utils.renderText("false", new String[0]);
	      e.printStackTrace();
	    }
  }
  
  public static void main(String[] args)
  {
    int lotteryId = 123155425;
    String a = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("test_" + lotteryId);
    System.err.println(a);
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public ProductInfo getProductInfo()
  {
    return productInfo;
  }
  
  public void setProductInfo(ProductInfo productInfo)
  {
    this.productInfo = productInfo;
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
  
  public List<ParticipateJSON> getParticipateJSONList()
  {
    return ParticipateJSONList;
  }
  
  public void setParticipateJSONList(List<ParticipateJSON> participateJSONList)
  {
    ParticipateJSONList = participateJSONList;
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
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public TreeMap<Integer, Integer> getProductPeriodList()
  {
    return productPeriodList;
  }
  
  public void setProductPeriodList(TreeMap<Integer, Integer> productPeriodList)
  {
    this.productPeriodList = productPeriodList;
  }
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public List<Productimage> getProductimageList()
  {
    return productimageList;
  }
  
  public void setProductimageList(List<Productimage> productimageList)
  {
    this.productimageList = productimageList;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public List<Spellbuyrecord> getSpellbuyrecordList()
  {
    return spellbuyrecordList;
  }
  
  public void setSpellbuyrecordList(List<Spellbuyrecord> spellbuyrecordList)
  {
    this.spellbuyrecordList = spellbuyrecordList;
  }
  
  public LotteryDetailJSON getLotteryDetailJSON()
  {
    return lotteryDetailJSON;
  }
  
  public void setLotteryDetailJSON(LotteryDetailJSON lotteryDetailJSON)
  {
    this.lotteryDetailJSON = lotteryDetailJSON;
  }
  
  public List<LotteryDetailJSON> getLotteryDetailJSONList()
  {
    return lotteryDetailJSONList;
  }
  
  public void setLotteryDetailJSONList(List<LotteryDetailJSON> lotteryDetailJSONList)
  {
    this.lotteryDetailJSONList = lotteryDetailJSONList;
  }
  
  public String getStartDate()
  {
    return startDate;
  }
  
  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }
  
  public Long getDateSUM()
  {
    return DateSUM;
  }
  
  public void setDateSUM(Long dateSUM)
  {
    DateSUM = dateSUM;
  }
  
  public List<RandomNumberJSON> getRandomNumberJSONList()
  {
    return randomNumberJSONList;
  }
  
  public void setRandomNumberJSONList(List<RandomNumberJSON> randomNumberJSONList)
  {
    this.randomNumberJSONList = randomNumberJSONList;
  }
  
  public RandomNumberJSON getRandomNumberJSON()
  {
    return randomNumberJSON;
  }
  
  public void setRandomNumberJSON(RandomNumberJSON randomNumberJSON)
  {
    this.randomNumberJSON = randomNumberJSON;
  }
}

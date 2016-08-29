package com.tech.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mteams.commons.encrypt.digest.MessageDigestUtils;
import com.tech.dao.Pagination;
import com.tech.pojo.Applymention;
import com.tech.pojo.BuyHistoryJSON;
import com.tech.pojo.Cardpassword;
import com.tech.pojo.Collectproduct;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.Commissionquery;
import com.tech.pojo.CommissionqueryJSON;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.News;
import com.tech.pojo.OrderListJSON;
import com.tech.pojo.Orderdetail;
import com.tech.pojo.Orderdetailaddress;
import com.tech.pojo.Product;
import com.tech.pojo.ProductInfo;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.RandomNumberJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.SCity;
import com.tech.pojo.SDistrict;
import com.tech.pojo.SProvince;
import com.tech.pojo.ShareJSON;
import com.tech.pojo.Shareimage;
import com.tech.pojo.Shareinfo;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.pojo.Userbyaddress;
import com.tech.service.ApplymentionService;
import com.tech.service.CardpasswordService;
import com.tech.service.CommissionpointsService;
import com.tech.service.CommissionqueryService;
import com.tech.service.ConsumetableService;
import com.tech.service.LatestlotteryService;
import com.tech.service.NewsService;
import com.tech.service.OrderdetailService;
import com.tech.service.RegionService;
import com.tech.service.ShareService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Base64;
import com.tech.util.CutImages;
import com.tech.util.DateUtil;
import com.tech.util.EmailUtil;
import com.tech.util.HTMLInputFilter;
import com.tech.util.MD5Util;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@SuppressWarnings({"rawtypes","unchecked"})
@Component("UserAction")
public class UserAction extends BaseAction
{
  private static final long serialVersionUID = 6146740235643445087L;
  @Autowired
  private UserService userService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private ShareService shareService;
  @Autowired
  private ConsumetableService consumetableService;
  @Autowired
  private NewsService newsService;
  @Autowired
  private RegionService regionService;
  @Autowired
  private CommissionqueryService commissionqueryService;
  @Autowired
  private CommissionpointsService commissionpointsService;
  @Autowired
  private ApplymentionService applymentionService;
  @Autowired
  private CardpasswordService cardpasswordService;
  @Autowired
  private OrderdetailService orderdetailService;
  private String forward;
  private List<ProductJSON> productList;
  private List<ProductJSON> newDateList;
  private List<ProductInfo> productInfoList;
  private ProductInfo productInfo;
  private ProductJSON productJSON;
  private BuyHistoryJSON buyHistoryJSON;
  private OrderListJSON orderListJSON;
  private ShareJSON shareJSON;
  private List<BuyHistoryJSON> buyHistoryJSONList;
  private List<OrderListJSON> orderListJSONList;
  private List<RandomNumberJSON> randomNumberJSONList;
  private RandomNumberJSON randomNumberJSON;
  private List<ShareJSON> shareJSONList;
  private List<Shareimage> shareimageList;
  private List<Userbyaddress> userbyaddressList;
  private List<News> newsList;
  private List<SProvince> sProvinceList;
  private List<SCity> sCityList;
  private List<SDistrict> sDistrictList;
  private List<User> userList;
  private List<Commissionquery> commissionqueryList;
  private List<Commissionpoints> commissionpointsList;
  private List<Applymention> applymentionList;
  private List<CommissionqueryJSON> commissionqueryJSONList;
  private List<Orderdetail> orderdetailList;
  private Orderdetail orderdetail;
  private Orderdetailaddress orderdetailaddress;
  private CommissionqueryJSON commissionqueryJSON;
  private Applymention applymention;
  private Commissionquery commissionquery;
  private Commissionpoints commissionpoints;
  private Userbyaddress userbyaddress;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private Spellbuyrecord spellbuyrecord;
  private Randomnumber randomnumber;
  private Latestlottery latestlottery;
  private Shareinfo shareinfo;
  private User user;
  private String userJSON;
  private String userId;
  private String id;
  private int pageNo;
  private int pageSize = 12;
  private int pageCount;
  private int resultCount;
  private String startDate;
  private String endDate;
  private String selectTime;
  private String postTitle;
  private String postContent;
  private String postAllPic;
  private File myFile;
  private String myFileFileName;
  private String myFileContentType;
  private String imageFileName;
  private static final int BUFFER_SIZE = 102400;
  private int x1;
  private int y1;
  private int w;
  private int h;
  private String hidPicUrl;
  private String key;
  private String userName;
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  static HTMLInputFilter htmlFilter = new HTMLInputFilter();
  
  private static void copy(File src, File dst)
  {
    try
    {
      InputStream in = null;
      OutputStream out = null;
      try
      {
        in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
        out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
        byte[] buffer = new byte[BUFFER_SIZE];
        while (in.read(buffer) > 0) {
          out.write(buffer);
        }
      }
      finally
      {
        if (in != null) {
          in.close();
        }
        if (out != null) {
          out.close();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String index()
  {
    if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元拍购开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://夺宝\">夺宝</a></div></body></html>", new String[0]);
      return null;
    }
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
          userId = cookie.getValue();
          if ((userId != null) && (!userId.equals("")))
          {
            user = ((User)userService.findById(userId));
            
            Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("user_datePage_" + pageNo);
            if (datePage == null)
            {
              datePage = spellbuyproductService.upcomingAnnounced(pageNo, 5);
              AliyunOcsSampleHelp.getIMemcachedCache().set("user_datePage_" + pageNo, 10, datePage);
            }
            List<?> dataList = datePage.getList();
            productList = new ArrayList();
            for (int j = 0; j < dataList.size(); j++)
            {
              productJSON = new ProductJSON();
              Object[] objects = (Object[])dataList.get(j);
	          	if(objects[0] instanceof Product){
	          		product = (Product)objects[0];
	          		spellbuyproduct = (Spellbuyproduct)objects[1];
	          	}else{
	          		product = (Product)objects[1];
	          		spellbuyproduct = (Spellbuyproduct)objects[0];
	          	}
              //product = ((Product)((Object[])dataList.get(j))[0]);
              //spellbuyproduct = ((Spellbuyproduct)((Object[])dataList.get(j))[1]);
              productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
              productJSON.setHeadImage(product.getHeadImage());
              productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
              productJSON.setProductName(product.getProductName());
              productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
              productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
              productJSON.setProductTitle(product.getProductTitle());
              productList.add(productJSON);
            }
            newsList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("user_newsList"));
            if (newsList == null)
            {
              newsList = newsService.indexNews();
              AliyunOcsSampleHelp.getIMemcachedCache().set("user_newsList", 600, newsList);
            }
            return "index";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String UserBuyList()
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
            user = ((User)userService.findById(userId));
            if (pageNo == 0) {
              pageNo = 1;
            } else {
              pageNo += 1;
            }
            resultCount = spellbuyrecordService.buyHistoryByUserByCount(userId, startDate, endDate).intValue();
            return "UserBuyList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void getuserBuyListAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = spellbuyrecordService.buyHistoryByUserByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String userBuyListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = spellbuyrecordService.buyHistoryByUser(userId, startDate, endDate, pageNo, 5);
    List<?> dataList = datePage.getList();
    buyHistoryJSONList = new ArrayList();
    if ((dataList != null) && (dataList.size() > 0))
    {
      for (int j = 0; j < dataList.size(); j++) {
        try
        {
          buyHistoryJSON = ((BuyHistoryJSON)dataList.get(j));
          if (buyHistoryJSON.getBuyStatus().intValue() == 1)
          {
            latestlottery = ((Latestlottery)latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getSpellbuyProductId()).get(0));
            buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
            buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
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
            buyHistoryJSON.setWinUser(userer);
            buyHistoryJSON.setWinUserId(latestlottery.getUserId());
          }
          buyHistoryJSONList.add(buyHistoryJSON);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      Struts2Utils.renderJson(buyHistoryJSONList, new String[0]);
    }
    return null;
  }
  
  public String UserBuyDetail()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
              buyHistoryJSON = ((BuyHistoryJSON)spellbuyrecordService.getBuyHistoryByDetail(id, userId).get(0));
              if (buyHistoryJSON.getBuyStatus().intValue() == 1)
              {
                latestlottery = ((Latestlottery)latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getSpellbuyProductId()).get(0));
                buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
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
                buyHistoryJSON.setWinUser(userer);
                buyHistoryJSON.setWinUserId(latestlottery.getUserId());
              }
              resultCount = spellbuyrecordService.getRandomNumberListPageByCount(id, userId).intValue();
              return "UserBuyDetail";
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "login_index";
  }
  
  public void getRandomNumberList()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    List<Randomnumber> dataList = spellbuyrecordService.getRandomNumberList(id, userId);
    randomNumberJSONList = new ArrayList();
    String numbers = "";
    for (Iterator<Randomnumber> localIterator = dataList.iterator(); localIterator.hasNext();)
    {
      final Randomnumber randomnumber = localIterator.next();
      final String[] randoms = randomnumber.getRandomNumber().split(",");
      for(int i = 0, j = randoms.length; i < j; ++i){
    	  numbers = numbers + "<li>" + randoms[i] + "</li>";
      }
    }
    Struts2Utils.renderText(numbers, new String[0]);
  }
  
  public void getRandomNumberListPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
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
          numbers = numbers + "<span>" + string + "</span>";
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
  
  public String OrderList()
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
            user = ((User)userService.findById(userId));
            resultCount = latestlotteryService.getProductByUserByCount(userId, startDate, endDate).intValue();
            return "OrderList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String OrderListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    if (postAllPic != null)
    {
      startDate = null;
      endDate = null;
    }
    Pagination datePage = latestlotteryService.getProductByUser(userId, startDate, endDate, hidPicUrl, postAllPic, pageNo, 5);
    List<?> dataList = datePage.getList();
    orderListJSONList = new ArrayList();
    for (int j = 0; j < dataList.size(); j++) {
      try
      {
        orderListJSON = new OrderListJSON();
        latestlottery = ((Latestlottery)dataList.get(j));
        orderListJSON.setProductName(latestlottery.getProductName());
        orderListJSON.setProductTitle(latestlottery.getProductTitle());
        orderListJSON.setProductImg(latestlottery.getProductImg());
        orderListJSON.setProductId(latestlottery.getSpellbuyProductId());
        orderListJSON.setProductPrice(latestlottery.getProductPrice());
        orderListJSON.setProductPeriod(latestlottery.getProductPeriod());
        orderListJSON.setWinId(latestlottery.getRandomNumber());
        orderListJSON.setWinDate(latestlottery.getAnnouncedTime());
        orderListJSON.setBuyStatus(latestlottery.getStatus());
        orderListJSON.setShareStatus(latestlottery.getShareStatus());
        orderListJSON.setShareId(latestlottery.getShareId());
        orderListJSONList.add(orderListJSON);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(orderListJSONList, new String[0]);
    return null;
  }
  
  public void OrderListAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = latestlotteryService.getProductByUserByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String OrderDetail()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
              latestlottery = ((Latestlottery)latestlotteryService.findById(id));
              if(user==null || latestlottery.getUserId().intValue()!=user.getUserId()){
            	  return "login_index";
              }
              if (latestlottery.getStatus().intValue() == 1)
              {
                userbyaddressList = userService.getUserbyaddress(userId);
                sProvinceList = regionService.getProvinceList();
              }
              else
              {
                orderdetailList = latestlotteryService.orderdetailListById(id);
                orderdetailaddress = latestlotteryService.orderdetailaddressFindByOrderdetailId(id);
              }
              return "OrderDetail";
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "login_index";
  }
  
  public void OrderDetailAddAddress()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              JSONObject object = JSONObject.fromObject(userJSON);
              String orderRemarks = object.getString("orderRemarks");
              String postDate = object.getString("postDate");
              String hidOrderNO = object.getString("hidOrderNO");
              id = object.getString("id");
              userbyaddress = userService.findAddressById(Integer.valueOf(Integer.parseInt(id)));
              orderdetailaddress = new Orderdetailaddress();
              orderdetailaddress.setAddress(userbyaddress.getProvince() + " " + userbyaddress.getCity() + " " + userbyaddress.getDistrict() + " " + userbyaddress.getAddress());
              orderdetailaddress.setConsignee(userbyaddress.getConsignee());
              orderdetailaddress.setOrderRemarks(orderRemarks);
              orderdetailaddress.setPostDate(postDate);
              orderdetailaddress.setPhone(userbyaddress.getPhone());
              orderdetailaddress.setOrderDetailId(Integer.valueOf(Integer.parseInt(hidOrderNO)));
              latestlotteryService.addOrderdetailaddress(orderdetailaddress);
              orderdetail = new Orderdetail();
              orderdetail.setDate(DateUtil.DateTimeToStr(new Date()));
              orderdetail.setDetailText("会员已确认配送地址信息，等待商城发货");
              orderdetail.setOrderDetailId(Integer.valueOf(Integer.parseInt(hidOrderNO)));
              orderdetail.setUserName("会员本人");
              orderdetailService.add(orderdetail);
              latestlottery = ((Latestlottery)latestlotteryService.findById(hidOrderNO));
              latestlottery.setStatus(Integer.valueOf(2));
              latestlotteryService.add(latestlottery);
              Struts2Utils.renderText("success", new String[0]);
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void confirmOrderDetail()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              latestlottery = ((Latestlottery)latestlotteryService.findById(id));
              latestlottery.setStatus(Integer.valueOf(4));
              latestlottery.setShareStatus(Integer.valueOf(-1));
              latestlotteryService.add(latestlottery);
              orderdetail = new Orderdetail();
              orderdetail.setDate(DateUtil.DateTimeToStr(new Date()));
              orderdetail.setDetailText("会员已确认收到商品。");
              orderdetail.setOrderDetailId(latestlottery.getSpellbuyProductId());
              orderdetail.setUserName("会员本人");
              orderdetailService.add(orderdetail);
              Struts2Utils.renderText("success", new String[0]);
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void hasCollectGoods()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie())
    {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId")) {
            userId = cookie.getValue();
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      if (StringUtil.isNotBlank(userId)) {
        Struts2Utils.renderText("0", new String[0]);
      } else {
        Struts2Utils.renderText("10", new String[0]);
      }
    }
  }
  
  public void getCollectGoodsList()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie())
    {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId")) {
            userId = cookie.getValue();
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      if (StringUtil.isNotBlank(userId))
      {
        Pagination datePage = userService.getCollectGoodsList(userId, pageNo, pageSize);
        List<?> dataList = datePage.getList();
        productInfoList = new ArrayList();
        for (int j = 0; j < dataList.size(); j++)
        {
          productInfo = new ProductInfo();
          Collectproduct collectproduct = null;
          Object[] objs = (Object[])dataList.get(j);
      	  for(Object obj:objs){
      		if(obj instanceof Product){
      			product = (Product)obj;
      		}
      		if(obj instanceof Spellbuyproduct){
      			spellbuyproduct = (Spellbuyproduct)obj;
      		}
      		if(obj instanceof Collectproduct){
      			collectproduct = (Collectproduct)obj;
      		}
      	  }
          //product = ((Product)((Object[])dataList.get(j))[0]);
          //spellbuyproduct = ((Spellbuyproduct)((Object[])dataList.get(j))[1]);
          //Collectproduct collectproduct = (Collectproduct)((Object[])dataList.get(j))[2];
          productInfo.setSpellbuyCount(spellbuyproduct.getSpellbuyCount());
          productInfo.setHeadImage(product.getHeadImage());
          productInfo.setProductId(product.getProductId());
          productInfo.setSpellbuyProductId(collectproduct.getId());
          productInfo.setProductName(product.getProductName());
          productInfo.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          productInfo.setProductPeriod(spellbuyproduct.getProductPeriod());
          productInfo.setProductTitle(product.getProductTitle());
          productInfo.setStatus(product.getStatus());
          productInfoList.add(productInfo);
        }
        Struts2Utils.renderJson(productInfoList, new String[0]);
      }
      else
      {
        Struts2Utils.renderText("10", new String[0]);
      }
    }
  }
  
  public void delCollectGoods()
  {
    try
    {
      userService.delCollectGoods(Integer.valueOf(Integer.parseInt(id)));
      Struts2Utils.renderText("0", new String[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Struts2Utils.renderText("-1", new String[0]);
    }
  }
  /*
   * 晒单
   * 易购OS 易购OS
   * 杨振朋
  */
  public String PostSingleAdd()
  {
	  System.out.println("===编辑晒单----");
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
              latestlottery = latestlotteryService.findById(id);
              if (latestlottery.getUserId().intValue() != user.getUserId().intValue()) {
            	  latestlottery = null;
              }
              return "PostSingleAdd";
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "login_index";
  }
  
  /*
   * 晒单编辑
   * 易购OS 易购OS
  */
  public String PostSingleEdit()
  {
	  System.out.println("===编辑晒单----");
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
              shareinfo = ((Shareinfo)shareService.findById(id));
              if (shareinfo.getUserId().intValue() == user.getUserId().intValue()) {
            	  shareimageList = shareService.getShareimage(id);
                  StringBuffer picstr = new StringBuffer();
                  for (Shareimage shareimage : shareimageList) {
                    picstr.append(shareimage.getImages()).append(",");
                  }
                  picstr.deleteCharAt(picstr.length() - 1);
                  hidPicUrl = picstr.toString();
              } else {
            	  shareinfo = null;
              }
              return "PostSingleEdit";
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "login_index";
  }
  
  public String PostSingleDetail()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++) {
        try
        {
          Cookie cookie = cookies[i];
          if (cookie.getName().equals("userId"))
          {
            userId = cookie.getValue();
            if ((userId != null) && (!userId.equals("")))
            {
              user = ((User)userService.findById(userId));
              shareinfo = ((Shareinfo)shareService.findById(id));
              shareimageList = shareService.getShareimage(id);
              return "PostSingleDetail";
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    return "login_index";
  }
  
  public void PostSingleAddUpLoadImg()
  {
    try
    {
      myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
      imageFileName = (new Date().getTime() + myFileFileName.toLowerCase());
      String filePath = ServletActionContext.getServletContext().getRealPath("/uploadImages") + "/" + imageFileName;
      File imageFile = new File(filePath);
      if (myFile != null)
      {
        copy(myFile, imageFile);
        
        CutImages.scale2(filePath, filePath, 525, 700, true);
        Struts2Utils.renderText(imageFileName, new String[0]);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void PostSingleAddShare()
    throws Exception
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
          if ((userId != null) && (!userId.equals(""))) {
            try
            {
              shareinfo = shareService.findByProductId(id);
              if (shareinfo == null)
              {
                shareinfo = new Shareinfo();
                shareinfo.setSproductId(Integer.valueOf(Integer.parseInt(id)));
                shareinfo.setReplyCount(Integer.valueOf(0));
                shareinfo.setReward(Integer.valueOf(0));
                shareinfo.setShareContent(postContent);
                shareinfo.setShareDate(DateUtil.DateTimeToStr(new Date()));
                shareinfo.setShareTitle(postTitle);
                shareinfo.setStatus(Integer.valueOf(0));
                shareinfo.setUpCount(Integer.valueOf(0));
                shareinfo.setUserId(Integer.valueOf(Integer.parseInt(userId)));
                shareService.add(shareinfo);
                
                latestlottery = ((Latestlottery)latestlotteryService.findById(id));
                latestlottery.setStatus(Integer.valueOf(10));
                latestlottery.setShareStatus(Integer.valueOf(0));
                latestlottery.setShareId(shareinfo.getUid());
                latestlotteryService.add(latestlottery);
                
                String[] sfile = postAllPic.split(",");
                String sfilePath = "/uploadImages";
                List<File> Filedata = new ArrayList();
                for (int j = 0; j < sfile.length; j++)
                {
                  String f = ServletActionContext.getServletContext().getRealPath(sfilePath) + "/" + sfile[j];
                  
                  Filedata.add(new File(f));
                }
                String productImgPath = "/UserPost";
                String biGImagePath = null;
                String smallImagePath = null;
                String listImgPath = null;
                if ((Filedata == null) || (Filedata.size() == 0)) {
                  Struts2Utils.renderText("false", new String[0]);
                }
                for (int j = 0; j < Filedata.size(); j++)
                {
                  myFile = ((File)Filedata.get(j));
                  if (myFile != null)
                  {
                    myFileFileName = ((File)Filedata.get(j)).getName();
                    

                    imageFileName = myFileFileName;
                    biGImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Big/";
                    smallImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Small/";
                    listImgPath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/220/";
                    File bigImageFile = new File(biGImagePath + imageFileName);
                    File smallImageFile = new File(smallImagePath + imageFileName);
                    File listImageFile = new File(smallImagePath + imageFileName);
                    copy(myFile, bigImageFile);
                    copy(myFile, smallImageFile);
                    copy(myFile, listImageFile);
                    CutImages.equimultipleConvert(700, 525, bigImageFile, new File(biGImagePath + myFileFileName));
                    CutImages.equimultipleConvert(220, 220, listImageFile, new File(listImgPath + myFileFileName));
                    CutImages.equimultipleConvert(100, 100, smallImageFile, new File(smallImagePath + myFileFileName));
                    Shareimage shareimage = new Shareimage();
                    shareimage.setShareInfoId(shareinfo.getUid());
                    shareimage.setImages(myFileFileName);
                    shareService.addShareImage(shareimage);
                  }
                }
                Struts2Utils.renderText("true", new String[0]);
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
              Struts2Utils.renderText("false", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public void PostSingleEditShare()
    throws Exception
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
          if ((userId != null) && (!userId.equals(""))) {
            try
            {
              shareinfo = shareService.findByProductId(id);
              if (shareinfo != null)
              {
                shareinfo.setSproductId(Integer.valueOf(Integer.parseInt(id)));
                shareinfo.setReplyCount(Integer.valueOf(0));
                shareinfo.setReward(Integer.valueOf(0));
                shareinfo.setShareContent(postContent);
                shareinfo.setShareDate(DateUtil.DateTimeToStr(new Date()));
                shareinfo.setShareTitle(postTitle);
                shareinfo.setStatus(Integer.valueOf(0));
                shareinfo.setUpCount(Integer.valueOf(0));
                shareinfo.setUserId(Integer.valueOf(Integer.parseInt(userId)));
                shareService.add(shareinfo);
                
                latestlottery = ((Latestlottery)latestlotteryService.findById(id));
                latestlottery.setStatus(Integer.valueOf(10));
                latestlottery.setShareStatus(Integer.valueOf(0));
                latestlottery.setShareId(shareinfo.getUid());
                latestlotteryService.add(latestlottery);
                
                String[] sfile = postAllPic.split(",");
                String sfilePath = "/uploadImages";
                List<File> Filedata = new ArrayList();
                for (int j = 0; j < sfile.length; j++)
                {
                  String f = ServletActionContext.getServletContext().getRealPath(sfilePath) + "/" + sfile[j];
                  
                  Filedata.add(new File(f));
                }
                String productImgPath = "/UserPost";
                String biGImagePath = null;
                String smallImagePath = null;
                String listImgPath = null;
                if ((Filedata == null) || (Filedata.size() == 0))
                {
                  Struts2Utils.renderText("false", new String[0]);
                }
                else
                {
                  try
                  {
                    shareimageList = shareService.getShareimage(String.valueOf(shareinfo.getUid()));
                    for (Shareimage shareimage : shareimageList) {
                      shareService.delShareImageByShareId(shareimage.getUid());
                    }
                    for (int j = 0; j < Filedata.size(); j++) {
                      try
                      {
                        myFile = ((File)Filedata.get(j));
                        if (myFile != null)
                        {
                          myFileFileName = ((File)Filedata.get(j)).getName();
                          

                          imageFileName = myFileFileName;
                          biGImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Big/";
                          smallImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Small/";
                          listImgPath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/220/";
                          File bigImageFile = new File(biGImagePath + imageFileName);
                          File smallImageFile = new File(smallImagePath + imageFileName);
                          File listImageFile = new File(smallImagePath + imageFileName);
                          copy(myFile, bigImageFile);
                          copy(myFile, smallImageFile);
                          copy(myFile, listImageFile);
                          CutImages.equimultipleConvert(700, 525, bigImageFile, new File(biGImagePath + myFileFileName));
                          CutImages.equimultipleConvert(220, 220, listImageFile, new File(listImgPath + myFileFileName));
                          CutImages.equimultipleConvert(100, 100, smallImageFile, new File(smallImagePath + myFileFileName));
                          
                          Shareimage shareimage = new Shareimage();
                          shareimage.setShareInfoId(shareinfo.getUid());
                          shareimage.setImages(imageFileName);
                          shareService.addShareImage(shareimage);
                        }
                      }
                      catch (Exception localException2) {}
                    }
                  }
                  catch (Exception e)
                  {
                    Struts2Utils.renderText("false", new String[0]);
                    e.printStackTrace();
                  }
                  Struts2Utils.renderText("true", new String[0]);
                }
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
              Struts2Utils.renderText("false", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public String PostSingleList()
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
            user = ((User)userService.findById(userId));
            Pagination datePage = shareService.shareByUser(userId, startDate, endDate, pageNo, 5);
            resultCount = datePage.getResultCount();
            return "PostSingleList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String PostSingleListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = shareService.shareByUser(userId, null, null, pageNo, 5);
    List<?> pageList = datePage.getList();
    orderListJSONList = new ArrayList();
    for (int j = 0; j < pageList.size(); j++) {
      try
      {
        orderListJSON = new OrderListJSON();
        Shareimage shareimage=null;
        Object[] objects = (Object[])pageList.get(j);
      	if(objects[0] instanceof Shareinfo){
      		shareinfo = (Shareinfo)objects[0];
      		shareimage = (Shareimage)objects[1];
      	}else{
      		shareinfo = (Shareinfo)objects[1];
      		shareimage = (Shareimage)objects[0];
      	}
        //shareinfo = ((Shareinfo)((Object[])pageList.get(j))[0]);
        //Shareimage shareimage = (Shareimage)((Object[])pageList.get(j))[1];
        orderListJSON.setProductTitle(shareinfo.getShareTitle());
        orderListJSON.setProductImg(shareimage.getImages());
        orderListJSON.setProductId(shareinfo.getSproductId());
        orderListJSON.setShareStatus(shareinfo.getStatus());
        orderListJSON.setShareId(shareinfo.getUid());
        orderListJSON.setBuyTime(shareinfo.getShareDate());
        if (shareinfo.getShareContent().length() > 95) {
          orderListJSON.setProductName(shareinfo.getShareContent().substring(0, 95) + "...");
        } else {
          orderListJSON.setProductName(shareinfo.getShareContent());
        }
        orderListJSONList.add(orderListJSON);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    Struts2Utils.renderJson(orderListJSONList, new String[0]);
    return null;
  }
  
  public void PostSingleListAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = shareService.getShareByUserByCount(userId, hidPicUrl, postAllPic, null, null).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String InvitedList()
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
            this.user = ((User)userService.findById(userId));
            resultCount = userService.getInvitedListByCount(userId).intValue();
            userList = userService.getInvitedListByData(userId);
            for (User user : userList) {
              if (user.getExperience().intValue() > 0)
              {
                w += 1;
                h += ApplicationListenerImpl.sysConfigureJson.getInvite().intValue();
              }
            }
            return "InvitedList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String InvitedListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Pagination datePage = userService.getInvitedList(userId, pageNo, pageSize);
    userList = (List<User>)datePage.getList();
    for (User user : userList) {
      user.setUserName(UserNameUtil.userName(user));
    }
    Struts2Utils.renderJson(userList, new String[0]);
    return null;
  }
  
  public String CommissionQuery()
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
            user = ((User)userService.findById(userId));
            resultCount = commissionqueryService.getCommissionQueryListByCount(userId, startDate, endDate).intValue();
            return "CommissionQuery";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String CommissionQueryAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = commissionqueryService.getCommissionQueryList(userId, startDate, endDate, pageNo, pageSize);
    List<?> dateList = datePage.getList();
    if (dateList.size() > 0)
    {
      commissionqueryJSONList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        commissionqueryJSON = new CommissionqueryJSON();
        Object[] objects = (Object[])dateList.get(i);
      	if(objects[0] instanceof User){
      		user = (User)objects[0];
      		commissionquery = (Commissionquery)objects[1];
      	}else{
      		user = (User)objects[1];
      		commissionquery = (Commissionquery)objects[0];
      	}
        //user = ((User)((Object[])dateList.get(i))[0]);
        //commissionquery = ((Commissionquery)((Object[])dateList.get(i))[1]);
        commissionqueryJSON.setBuyMoney(commissionquery.getBuyMoney());
        commissionqueryJSON.setCommission(commissionquery.getCommission());
        commissionqueryJSON.setDate(commissionquery.getDate());
        commissionqueryJSON.setDescription(commissionquery.getDescription());
        commissionqueryJSON.setUserId(String.valueOf(user.getUserId()));
        commissionqueryJSON.setUserName(UserNameUtil.userName(user));
        commissionqueryJSONList.add(commissionqueryJSON);
      }
    }
    Struts2Utils.renderJson(commissionqueryJSONList, new String[0]);
    return null;
  }
  
  public void getCommissionQueryAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = commissionqueryService.getCommissionQueryListByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String MemberPoints()
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
            user = ((User)userService.findById(userId));
            resultCount = commissionpointsService.getCommissionPointsListByCount(userId, startDate, endDate).intValue();
            return "CommissionPoints";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String CommissionPointsAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Pagination datePage = commissionpointsService.CommissionPoints(userId, startDate, endDate, pageNo, pageSize);
    commissionpointsList = (List<Commissionpoints>)datePage.getList();
    Struts2Utils.renderJson(commissionpointsList, new String[0]);
    return null;
  }
  
  public String ApplyMention()
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
            user = ((User)userService.findById(userId));
            return "ApplyMention";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void ApplyMentionAdd()
  {
    try
    {
      JSONObject object = JSONObject.fromObject(userJSON);
      userId = object.getString("userId");
      user = ((User)userService.findById(userId));
      double money = Double.parseDouble(object.getString("money"));
      double commissionBalance = user.getCommissionBalance().doubleValue();
      double commissionMention = user.getCommissionMention().doubleValue();
      if (commissionBalance < money)
      {
        Struts2Utils.renderText("moneyError", new String[0]);
      }
      else
      {
        user.setCommissionBalance(Double.valueOf(commissionBalance - money));
        user.setCommissionMention(Double.valueOf(commissionMention + money));
        userService.add(user);
        applymention = new Applymention();
        applymention.setBankName(object.getString("bankName"));
        applymention.setBankNo(object.getString("bankNo"));
        applymention.setBankSubbranch(object.getString("bankSubbranch"));
        applymention.setBankUser(object.getString("bankUser"));
        applymention.setDate(DateUtil.DateTimeToStr(new Date()));
        applymention.setFee(Double.valueOf(0.0D));
        applymention.setMoney(Double.valueOf(money));
        applymention.setPhone(object.getString("phone"));
        applymention.setStatus("审核中");
        applymention.setUserId(Integer.valueOf(Integer.parseInt(userId)));
        applymentionService.add(applymention);
        Struts2Utils.renderText("success", new String[0]);
      }
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("error", new String[0]);
      e.printStackTrace();
    }
  }
  
  public void ApplyMentionAddToUser()
  {
    try
    {
      JSONObject object = JSONObject.fromObject(userJSON);
      double recharge = Double.parseDouble(object.getString("recharge"));
      userId = object.getString("userId");
      user = ((User)userService.findById(userId));
      double tempCommissionBalance = user.getCommissionBalance().doubleValue();
      double commissionMention = user.getCommissionMention().doubleValue();
      if (tempCommissionBalance < recharge)
      {
        Struts2Utils.renderText("moneyError", new String[0]);
      }
      else
      {
        user.setCommissionMention(Double.valueOf(commissionMention + recharge));
        user.setCommissionBalance(Double.valueOf(tempCommissionBalance - recharge));
        double tempBalance = user.getBalance().doubleValue();
        user.setBalance(Double.valueOf(tempBalance + recharge));
        userService.add(user);
        Struts2Utils.renderText("success", new String[0]);
      }
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("error", new String[0]);
      e.printStackTrace();
    }
  }
  
  public String MentionList()
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
            user = ((User)userService.findById(userId));
            resultCount = applymentionService.getApplymentionListByCount(userId, startDate, endDate).intValue();
            return "MentionList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String MentionListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = applymentionService.getApplymentionList(userId, startDate, endDate, pageNo, pageSize);
    List<Applymention> dataList = (List<Applymention>)datePage.getList();
    Struts2Utils.renderJson(dataList, new String[0]);
    return null;
  }
  
  public void getMentionListAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = applymentionService.getApplymentionListByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String UserRecharge()
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
            user = ((User)userService.findById(userId));
            return "UserRecharge";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String userCardRecharge()
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
            user = ((User)userService.findById(userId));
            return "userCardRecharge";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void doCardRecharge()
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
            user = ((User)userService.findById(userId));
            try
            {
              if (StringUtil.isNotBlank(id))
              {
                String randomNo = id;
                String cardPwd = MD5Util.encode(id);
                Cardpassword cardpassword = cardpasswordService.doCardRecharge(randomNo, cardPwd);
                if (cardpassword != null)
                {
                  Double temp = user.getBalance();
                  cardpasswordService.deleteByID(cardpassword.getId());
                  user.setBalance(Double.valueOf(temp.doubleValue() + cardpassword.getMoney().doubleValue()));
                  userService.add(user);
                  Consumetable consumetable = new Consumetable();
                  consumetable.setBuyCount(Integer.valueOf(Integer.parseInt(new DecimalFormat("0").format(cardpassword.getMoney()))));
                  consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
                  consumetable.setInterfaceType("卡密充值");
                  consumetable.setMoney(cardpassword.getMoney());
                  consumetable.setOutTradeNo(randomNo);
                  consumetable.setTransactionId(cardPwd);
                  consumetable.setUserId(Integer.valueOf(Integer.parseInt(userId)));
                  consumetable.setWithold("");
                  consumetable.setPayStatus(Consumetable.PAY_STAT_PAID);
                  consumetableService.add(consumetable);
                  Struts2Utils.renderText("yes", new String[0]);
                }
                else
                {
                  Struts2Utils.renderText("no", new String[0]);
                }
              }
              else
              {
                Struts2Utils.renderText("no", new String[0]);
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
              Struts2Utils.renderText("no", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public String UserBalance()
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
            user = ((User)userService.findById(userId));
            resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate).intValue();
            return "UserBalance";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String ConsumeList()
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
            user = ((User)userService.findById(userId));
            resultCount = consumetableService.userByConsumetableByCount(userId, startDate, endDate).intValue();
            return "ConsumeList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String ConsumeListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = consumetableService.userByConsumetable(userId, startDate, endDate, pageNo, pageSize);
    List<Consumetable> dataList = (List<Consumetable>)datePage.getList();
    Struts2Utils.renderJson(dataList, new String[0]);
    return null;
  }
  
  public void ConsumeListAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = consumetableService.userByConsumetableByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String RechargeList()
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
            user = ((User)userService.findById(userId));
            resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate).intValue();
            return "RechargeList";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String ConsumeListByDeltaAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    Pagination datePage = consumetableService.userByConsumetableByDelta(userId, startDate, endDate, pageNo, pageSize);
    List<Consumetable> dataList = (List<Consumetable>)datePage.getList();
    Struts2Utils.renderJson(dataList, new String[0]);
    return null;
  }
  
  public void ConsumeListByDeltaAjaxPageResultCount()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Date date = new Date();
    if (StringUtil.isNotBlank(selectTime))
    {
      if (selectTime.equals("0"))
      {
        startDate = null;
        endDate = null;
      }
      else if (selectTime.equals("1"))
      {
        startDate = (DateUtil.DateToStr(date) + " 00:00:00");
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
      }
      else if (selectTime.equals("2"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -7)) + " 00:00:00");
      }
      else if (selectTime.equals("3"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -30)) + " 00:00:00");
      }
      else if (selectTime.equals("4"))
      {
        endDate = (DateUtil.DateToStr(date) + " 23:59:59");
        startDate = (DateUtil.DateToStr(DateUtil.addDate(date, -90)) + " 00:00:00");
      }
    }
    else
    {
      startDate += " 00:00:00";
      endDate += " 23:59:59";
    }
    resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate).intValue();
    Struts2Utils.renderText(resultCount+"", new String[0]);
  }
  
  public String Address()
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
            user = ((User)userService.findById(userId));
            return "Address";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void getAddressList()
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
            userbyaddressList = userService.getUserbyaddress(userId);
            Struts2Utils.renderJson(userbyaddressList, new String[0]);
          }
        }
      }
    }
  }
  
  public String addAddress()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId"))
        {
          this.userId = cookie.getValue();
          if ((this.userId != null) && (!this.userId.equals(""))) {
            try
            {
              JSONObject object = JSONObject.fromObject(userJSON);
              int userId = object.getInt("userId");
              userbyaddressList = userService.getUserbyaddress(userId+"");
              if (userbyaddressList.size() < 4)
              {
                String province = object.getString("province");
                String city = object.getString("city");
                String district = object.getString("district");
                String address = object.getString("address");
                int zipCode = object.getInt("zipCode");
                String consignee = object.getString("consignee");
                String phone = object.getString("phone");
                userbyaddress = new Userbyaddress();
                userbyaddress.setAddress(address);
                userbyaddress.setCity(city);
                userbyaddress.setConsignee(consignee);
                userbyaddress.setDistrict(district);
                userbyaddress.setPhone(phone);
                userbyaddress.setProvince(province);
                userbyaddress.setStatus(Integer.valueOf(1));
                userbyaddress.setUserId(Integer.valueOf(userId));
                userbyaddress.setZipCode(Integer.valueOf(zipCode));
                userService.addAddress(userbyaddress);
                if (userbyaddress.getId() != null) {
                  userService.setDefaultAddress(String.valueOf(userId), userbyaddress.getId());
                }
                Struts2Utils.renderText("success", new String[0]);
              }
              else
              {
                Struts2Utils.renderText("sizeError", new String[0]);
              }
            }
            catch (Exception e)
            {
              Struts2Utils.renderText("false", new String[0]);
              e.printStackTrace();
            }
          }
        }
      }
    }
    return null;
  }
  
  public void setDefaultAddress()
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
          if ((userId != null) && (!userId.equals(""))) {
            try
            {
              userService.defaultAddress(userId, Integer.valueOf(Integer.parseInt(id)));
              userService.setDefaultAddress(String.valueOf(userId), Integer.valueOf(Integer.parseInt(id)));
              Struts2Utils.renderText("success", new String[0]);
            }
            catch (Exception e)
            {
              Struts2Utils.renderText("false", new String[0]);
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  public void delAddress()
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
          if ((userId != null) && (!userId.equals(""))) {
            try
            {
              userService.delAddress(Integer.valueOf(Integer.parseInt(id)));
              Struts2Utils.renderText("success", new String[0]);
            }
            catch (Exception e)
            {
              e.printStackTrace();
              Struts2Utils.renderText("false", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public void getProvinceList()
  {
    sProvinceList = regionService.getProvinceList();
    Struts2Utils.renderJson(sProvinceList, new String[0]);
  }
  
  public void getCityList()
  {
    sCityList = regionService.getCityListByProvinceId(id);
    Struts2Utils.renderJson(sCityList, new String[0]);
  }
  
  public void getDistrictList()
  {
    sDistrictList = regionService.getDistrictListByCityId(id);
    Struts2Utils.renderJson(sDistrictList, new String[0]);
  }
  
  public String MemberModify()
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
            user = ((User)userService.findById(userId));
            return "MemberModify";
          }
        }
      }
    }
    return "login_index";
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
          userId = cookie.getValue();
          if ((userId != null) && (!userId.equals("")))
          {
            user = ((User)userService.findById(userId));
            return "qqUserInfoAuth";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String MobileChecking()
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
            user = ((User)userService.findById(userId));
            return "MobileChecking";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void MobileReturn()
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
            user = ((User)userService.findById(userId));
            String mobileStr = (String)AliyunOcsSampleHelp.getIMemcachedCache().get(userJSON);
            if (mobileStr != null)
            {
              if (mobileStr.equals(id))
              {
                user.setMobileCheck("0");
                user.setPhone(userJSON);
                userService.add(user);
                Struts2Utils.renderText("0", new String[0]);
              }
              else
              {
                Struts2Utils.renderText("2", new String[0]);
              }
            }
            else {
              Struts2Utils.renderText("1", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public String MobileReturnSuccess()
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
            user = ((User)userService.findById(userId));
            return "MobileReturnSuccess";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String EmailChecking()
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
            user = ((User)userService.findById(userId));
            return "EmailChecking";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void EmailSending()
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
            user = ((User)userService.findById(userId));
            request = Struts2Utils.getRequest();
            String key = MD5Util.encode(userJSON) + MD5Util.encode(DateUtil.dateTimeToStr(new Date())) + Base64.getEncode(userJSON);
            String html = "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border: #dddddd 1px solid; padding: 20px 0;\"><tbody><tr><td><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"border-bottom: #ff6600 2px solid; padding-bottom: 12px;\"><tbody><tr><td style=\"line-height: 22px; padding-left: 20px;\"><a target=\"_blank\" title=\"" + 
            



              ApplicationListenerImpl.sysConfigureJson.getSaitName() + "\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\"><img width=\"230px\" border=\"0\" height=\"52\" src=\"" + ApplicationListenerImpl.sysConfigureJson.getImgUrl() + "/Images/mail_logo.gif\"></a></td>" + 
              "<td align=\"right\" style=\"font-size: 12px; padding-right: 20px; padding-top: 30px;\"><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "\">首页</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b>" + 
              "<a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/index.html\">我的" + ApplicationListenerImpl.sysConfigureJson.getSaitName() + "</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/help/index.html\">帮助</a></td>" + 
              "</tr>" + 
              "</tbody></table>" + 
              "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"padding: 0 20px;\">" + 
              "<tbody><tr>" + 
              "<td style=\"font-size: 14px; color: #333333; height: 40px; line-height: 40px; padding-top: 10px;\">亲爱的 <b style=\"color: #333333; font-family: Arial;\"><a href=\"mailto:" + userJSON + "\" target=\"_blank\">" + userJSON + "</a></b>：</td>" + 
              "</tr>" + 
              "<tr>" + 
              "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！请点击下面的按钮，完成邮箱验证：</p></td>" + 
              "</tr>" + 
              "<tr>" + 
              "<td style=\"padding-top: 15px; padding-left: 28px;\"><a title=\"邮箱验证\" style=\"display: inline-block; padding: 0 25px; height: 28px; line-height: 28px; text-align: center; color: #ffffff; background: #ff7700; font-size: 12px; cursor: pointer; border-radius: 2px; text-decoration: none;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/EmailReturnSuccess.html?key=" + key + "\">邮箱验证</a></td>" + 
              "</tr>" + 
              "<tr>" + 
              "<td width=\"525\" style=\"font-size: 12px; color: #333333; line-height: 22px; padding-top: 20px;\">如果上面按钮不能点击或点击后没有反应，您还可以将以下链接复制到浏览器地址栏中访问完成邮箱验证。</td>" + 
              "</tr>" + 
              "<tr>" + 
              "<td width=\"525\" style=\"font-size: 12px; padding-top: 5px; word-break: break-all; word-wrap: break-word;\"><a style=\"font-family: Arial; color: #22aaff;\" target=\"_blank\" href=\"" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/EmailReturnSuccess.html?key=" + key + "\">" + ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/user/EmailReturnSuccess.html?key=" + key + "</a></td>" + 
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
            if (AliyunOcsSampleHelp.getIMemcachedCache().get(MD5Util.encode(userJSON)) == null)
            {
              boolean flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), userJSON, ApplicationListenerImpl.sysConfigureJson.getSaitName() + "验证新绑定邮箱", html);
              if (flag)
              {
                user.setMail(userJSON);
                userService.add(user);
                AliyunOcsSampleHelp.getIMemcachedCache().set(MD5Util.encode(userJSON), 120, userJSON);
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
        }
      }
    }
  }
  
  public String EmailSendSuccess()
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
            user = ((User)userService.findById(userId));
            return "EmailSendSuccess";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String EmailReturnSuccess()
    throws UnsupportedEncodingException
  {
    if (StringUtil.isNotBlank(key))
    {
      key = key.substring(64, key.length());
      userJSON = Base64.getDecode(key);
      if (StringUtil.isNotBlank(userJSON))
      {
        user = userService.userByName(userJSON);
        if (user != null)
        {
          user.setMailCheck("0");
          userService.add(user);
          return "EmailReturnSuccess";
        }
      }
    }
    return null;
  }
  
  public String isCheckName()
  {
    user = userService.isCheckName(userName);
    if (user == null) {
      Struts2Utils.renderText("true", new String[0]);
    } else {
      Struts2Utils.renderText("false", new String[0]);
    }
    return null;
  }
  
  public void updateUser()
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
            user = ((User)userService.findById(userId));
            if (user != null) {
              try
              {
                JSONObject object = JSONObject.fromObject(userJSON);
                if (user.getUserName() == null)
                {
                  Integer m = user.getCommissionPoints();
                  m = Integer.valueOf(m.intValue() + ApplicationListenerImpl.sysConfigureJson.getUserData().intValue());
                  user.setCommissionPoints(m);
                  commissionpoints = new Commissionpoints();
                  commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
                  commissionpoints.setDetailed("完善会员资料获得" + ApplicationListenerImpl.sysConfigureJson.getUserData() + "积分");
                  commissionpoints.setPay("+" + ApplicationListenerImpl.sysConfigureJson.getUserData());
                  commissionpoints.setToUserId(Integer.valueOf(Integer.parseInt(userId)));
                  commissionpointsService.add(commissionpoints);
                }
                user.setUserName(htmlFilter.filter(object.getString("userName")));
                String mobilePhone = object.getString("mobilePhone");
                if (!mobilePhone.equals("undefined")) {
                  user.setMobilePhone(mobilePhone);
                }
                String qq = object.getString("qq");
                if (!qq.equals("undefined"))
                {
                  qq = htmlFilter.filter(qq);
                  user.setQq(qq);
                }
                String userSign = object.getString("userSign");
                if ((!userSign.equals("undefined")) && (!userSign.equals("")))
                {
                  userSign = htmlFilter.filter(userSign);
                  user.setSignature(userSign);
                }
                userService.add(user);
                Struts2Utils.renderJson(user, new String[0]);
              }
              catch (Exception e)
              {
                e.printStackTrace();
                Struts2Utils.renderText("false", new String[0]);
              }
            } else {
              Struts2Utils.renderText("false", new String[0]);
            }
          }
        }
      }
    }
  }
  
  public void isUserNameExists()
  {
    User user = userService.isUserName(id, userId);
    if (user == null) {
      Struts2Utils.renderText("true", new String[0]);
    } else {
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public String UserPhoto()
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
            user = ((User)userService.findById(userId));
            return "UserPhoto";
          }
        }
      }
    }
    return "login_index";
  }
  
  public String updateFaceFile()
    throws Exception
  {
    try
    {
      myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
      imageFileName = (userId + "_" + new Date().getTime() + myFileFileName);
      String filePath = ServletActionContext.getServletContext().getRealPath("/uploadImages") + "/" + imageFileName;
      File imageFile = new File(filePath);
      if (myFile != null)
      {
        copy(myFile, imageFile);
        
        CutImages.scale2(filePath, filePath, 300, 300, true);
        Struts2Utils.renderText("/uploadImages/" + imageFileName, new String[0]);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public void updateFace() throws IOException
  {
    request = Struts2Utils.getRequest();
    response = Struts2Utils.getResponse();
    hidPicUrl = hidPicUrl.replace("/", "\\");
    hidPicUrl = hidPicUrl.substring(hidPicUrl.lastIndexOf("\\") + 1, hidPicUrl.length());
    final String fileName = ServletActionContext.getServletContext().getRealPath("uploadImages") + "/" + hidPicUrl;
    final String faceName = ServletActionContext.getServletContext().getRealPath("faceImages") + "/" + hidPicUrl;
    try
    {
      CutImages cutImages = new CutImages(x1, y1, w, h, fileName, faceName);
      cutImages.cut();
      final String faceImg = ("/faceImages"+"/"+hidPicUrl).replace("\\", "/");
      user = ((User)userService.findById(userId));
      user.setFaceImg(faceImg);
      userService.add(user);
      Cookie cookie3 = new Cookie("face", URLEncoder.encode(user.getFaceImg(), "UTF-8"));
      cookie3.setMaxAge(-1);
      cookie3.setPath("/");
      cookie3.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
      response.addCookie(cookie3);
      Struts2Utils.renderText("success", new String[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Struts2Utils.renderText("false", new String[0]);
    }
  }
  
  public String UpdatePassWord()
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
            user = ((User)userService.findById(userId));
            return "UpdatePassWord";
          }
        }
      }
    }
    return "login_index";
  }
  
  public void updatePwd()
  {
    if (StringUtil.isNotBlank(userId)) {
      try
      {
        user = ((User)userService.findById(userId));
        if (user != null) {
        	try {
        		id = URLDecoder.decode(id, "UTF-8");
        	} catch (UnsupportedEncodingException e1) {
        		e1.printStackTrace();
        	}
        	id = MessageDigestUtils.sha256Hex(id);
          if (!user.getUserPwd().equals(id))
          {
            Struts2Utils.renderText("pwdError", new String[0]);
          }
          else
          {
        	  try {
        			userJSON = URLDecoder.decode(userJSON, "UTF-8");
        		} catch (UnsupportedEncodingException e1) {
        			e1.printStackTrace();
        		}
        	  userJSON = MessageDigestUtils.sha256Hex(userJSON);
        	  user.setUserPwd(userJSON);
            userService.add(user);
            Struts2Utils.renderText("success", new String[0]);
          }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void lotteryDrawGo()
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
      user = ((User)userService.findById(userId));
      int number = 360;
      Random random = new Random();
      random.nextInt(number);
      int round = random.nextInt(number);
      
      System.err.println(round);
      if ((round < 10) || ((round < 57) && (round > 47)) || ((round < 109) && (round > 99)) || ((round < 160) && (round > 150)) || ((round < 211) && (round > 201)) || ((round < 261) && (round > 251)) || ((round < 313) && (round > 303)))
      {
        round += random.nextInt(10);
        System.err.println(round);
      }
      StringBuilder sb = new StringBuilder();
      
      sb.append('{');
      sb.append("\"count\":\"").append(0).append("\",");
      sb.append("\"text\":\"").append(0).append("\",");
      sb.append("\"round\":\"").append(round).append("\"");
      sb.append('}');
      sb.append(",");
      sb.deleteCharAt(sb.length() - 1);
      
      Struts2Utils.renderJson(sb.toString(), new String[0]);
    }
    else
    {
      StringBuilder sb = new StringBuilder();
      
      sb.append('{');
      sb.append("\"count\":\"").append(-1).append("\",");
      sb.append("\"text\":\"").append(0).append("\",");
      sb.append("\"round\":\"").append(0).append("\"");
      sb.append('}');
      sb.append(",");
      sb.deleteCharAt(sb.length() - 1);
      
      Struts2Utils.renderJson(sb.toString(), new String[0]);
    }
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getForward()
  {
    return forward;
  }
  
  public void setForward(String forward)
  {
    this.forward = forward;
  }
  
  public List<ProductJSON> getProductList()
  {
    return productList;
  }
  
  public void setProductList(List<ProductJSON> productList)
  {
    this.productList = productList;
  }
  
  public List<ProductJSON> getNewDateList()
  {
    return newDateList;
  }
  
  public void setNewDateList(List<ProductJSON> newDateList)
  {
    this.newDateList = newDateList;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
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
  
  public Spellbuyrecord getSpellbuyrecord()
  {
    return spellbuyrecord;
  }
  
  public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord)
  {
    this.spellbuyrecord = spellbuyrecord;
  }
  
  public BuyHistoryJSON getBuyHistoryJSON()
  {
    return buyHistoryJSON;
  }
  
  public void setBuyHistoryJSON(BuyHistoryJSON buyHistoryJSON)
  {
    this.buyHistoryJSON = buyHistoryJSON;
  }
  
  public List<BuyHistoryJSON> getBuyHistoryJSONList()
  {
    return buyHistoryJSONList;
  }
  
  public void setBuyHistoryJSONList(List<BuyHistoryJSON> buyHistoryJSONList)
  {
    this.buyHistoryJSONList = buyHistoryJSONList;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
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
  
  public String getSelectTime()
  {
    return selectTime;
  }
  
  public void setSelectTime(String selectTime)
  {
    this.selectTime = selectTime;
  }
  
  public Randomnumber getRandomnumber()
  {
    return randomnumber;
  }
  
  public void setRandomnumber(Randomnumber randomnumber)
  {
    this.randomnumber = randomnumber;
  }
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public Shareinfo getShareinfo()
  {
    return shareinfo;
  }
  
  public void setShareinfo(Shareinfo shareinfo)
  {
    this.shareinfo = shareinfo;
  }
  
  public ShareJSON getShareJSON()
  {
    return shareJSON;
  }
  
  public void setShareJSON(ShareJSON shareJSON)
  {
    this.shareJSON = shareJSON;
  }
  
  public List<ShareJSON> getShareJSONList()
  {
    return shareJSONList;
  }
  
  public void setShareJSONList(List<ShareJSON> shareJSONList)
  {
    this.shareJSONList = shareJSONList;
  }
  
  public List<Userbyaddress> getUserbyaddressList()
  {
    return userbyaddressList;
  }
  
  public void setUserbyaddressList(List<Userbyaddress> userbyaddressList)
  {
    this.userbyaddressList = userbyaddressList;
  }
  
  public Userbyaddress getUserbyaddress()
  {
    return userbyaddress;
  }
  
  public void setUserbyaddress(Userbyaddress userbyaddress)
  {
    this.userbyaddress = userbyaddress;
  }
  
  public String getUserJSON()
  {
    return userJSON;
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
  
  public void setUserJSON(String userJSON)
  {
    this.userJSON = userJSON;
  }
  
  public File getMyFile()
  {
    return myFile;
  }
  
  public void setMyFile(File myFile)
  {
    this.myFile = myFile;
  }
  
  public String getMyFileFileName()
  {
    return myFileFileName;
  }
  
  public void setMyFileFileName(String myFileFileName)
  {
    this.myFileFileName = myFileFileName;
  }
  
  public String getMyFileContentType()
  {
    return myFileContentType;
  }
  
  public void setMyFileContentType(String myFileContentType)
  {
    this.myFileContentType = myFileContentType;
  }
  
  public String getImageFileName()
  {
    return imageFileName;
  }
  
  public void setImageFileName(String imageFileName)
  {
    this.imageFileName = imageFileName;
  }
  
  public int getX1()
  {
    return x1;
  }
  
  public void setX1(int x1)
  {
    this.x1 = x1;
  }
  
  public int getY1()
  {
    return y1;
  }
  
  public void setY1(int y1)
  {
    this.y1 = y1;
  }
  
  public String getHidPicUrl()
  {
    return hidPicUrl;
  }
  
  public void setHidPicUrl(String hidPicUrl)
  {
    this.hidPicUrl = hidPicUrl;
  }
  
  public int getW()
  {
    return w;
  }
  
  public void setW(int w)
  {
    this.w = w;
  }
  
  public int getH()
  {
    return h;
  }
  
  public void setH(int h)
  {
    this.h = h;
  }
  
  public List<News> getNewsList()
  {
    return newsList;
  }
  
  public void setNewsList(List<News> newsList)
  {
    this.newsList = newsList;
  }
  
  public List<SProvince> getSProvinceList()
  {
    return sProvinceList;
  }
  
  public void setSProvinceList(List<SProvince> provinceList)
  {
    sProvinceList = provinceList;
  }
  
  public List<SCity> getSCityList()
  {
    return sCityList;
  }
  
  public void setSCityList(List<SCity> cityList)
  {
    sCityList = cityList;
  }
  
  public List<SDistrict> getSDistrictList()
  {
    return sDistrictList;
  }
  
  public void setSDistrictList(List<SDistrict> districtList)
  {
    sDistrictList = districtList;
  }
  
  public List<User> getUserList()
  {
    return userList;
  }
  
  public void setUserList(List<User> userList)
  {
    this.userList = userList;
  }
  
  public List<Commissionquery> getCommissionqueryList()
  {
    return commissionqueryList;
  }
  
  public void setCommissionqueryList(List<Commissionquery> commissionqueryList)
  {
    this.commissionqueryList = commissionqueryList;
  }
  
  public List<Applymention> getApplymentionList()
  {
    return applymentionList;
  }
  
  public void setApplymentionList(List<Applymention> applymentionList)
  {
    this.applymentionList = applymentionList;
  }
  
  public Applymention getApplymention()
  {
    return applymention;
  }
  
  public void setApplymention(Applymention applymention)
  {
    this.applymention = applymention;
  }
  
  public Commissionquery getCommissionquery()
  {
    return commissionquery;
  }
  
  public void setCommissionquery(Commissionquery commissionquery)
  {
    this.commissionquery = commissionquery;
  }
  
  public List<CommissionqueryJSON> getCommissionqueryJSONList()
  {
    return commissionqueryJSONList;
  }
  
  public void setCommissionqueryJSONList(List<CommissionqueryJSON> commissionqueryJSONList)
  {
    this.commissionqueryJSONList = commissionqueryJSONList;
  }
  
  public CommissionqueryJSON getCommissionqueryJSON()
  {
    return commissionqueryJSON;
  }
  
  public void setCommissionqueryJSON(CommissionqueryJSON commissionqueryJSON)
  {
    this.commissionqueryJSON = commissionqueryJSON;
  }
  
  public List<Commissionpoints> getCommissionpointsList()
  {
    return commissionpointsList;
  }
  
  public void setCommissionpointsList(List<Commissionpoints> commissionpointsList)
  {
    this.commissionpointsList = commissionpointsList;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }
  
  public List<Orderdetail> getOrderdetailList()
  {
    return orderdetailList;
  }
  
  public void setOrderdetailList(List<Orderdetail> orderdetailList)
  {
    this.orderdetailList = orderdetailList;
  }
  
  public Orderdetailaddress getOrderdetailaddress()
  {
    return orderdetailaddress;
  }
  
  public void setOrderdetailaddress(Orderdetailaddress orderdetailaddress)
  {
    this.orderdetailaddress = orderdetailaddress;
  }
  
  public Orderdetail getOrderdetail()
  {
    return orderdetail;
  }
  
  public void setOrderdetail(Orderdetail orderdetail)
  {
    this.orderdetail = orderdetail;
  }
  
  public String getPostTitle()
  {
    return postTitle;
  }
  
  public void setPostTitle(String postTitle)
  {
    this.postTitle = postTitle;
  }
  
  public String getPostContent()
  {
    return postContent;
  }
  
  public void setPostContent(String postContent)
  {
    this.postContent = postContent;
  }
  
  public String getPostAllPic()
  {
    return postAllPic;
  }
  
  public void setPostAllPic(String postAllPic)
  {
    this.postAllPic = postAllPic;
  }
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public List<ProductInfo> getProductInfoList()
  {
    return productInfoList;
  }
  
  public void setProductInfoList(List<ProductInfo> productInfoList)
  {
    this.productInfoList = productInfoList;
  }
  
  public ProductInfo getProductInfo()
  {
    return productInfo;
  }
  
  public void setProductInfo(ProductInfo productInfo)
  {
    this.productInfo = productInfo;
  }
  
  public List<Shareimage> getShareimageList()
  {
    return shareimageList;
  }
  
  public void setShareimageList(List<Shareimage> shareimageList)
  {
    this.shareimageList = shareimageList;
  }
}

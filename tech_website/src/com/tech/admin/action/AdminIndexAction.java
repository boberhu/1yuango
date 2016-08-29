package com.tech.admin.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.struts2.ServletActionContext;
import org.jdesktop.swingx.util.OS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mteams.commons.encrypt.digest.MessageDigestUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.tech.dao.Pagination;
import com.tech.exception.AccountStatusException;
import com.tech.exception.BadCredentialsException;
import com.tech.exception.UsernameNotFoundException;
import com.tech.pojo.Admin;
import com.tech.pojo.Applymention;
import com.tech.pojo.Cardpassword;
import com.tech.pojo.Commissionpoints;
import com.tech.pojo.Consumerdetail;
import com.tech.pojo.Consumetable;
import com.tech.pojo.Function;
import com.tech.pojo.IndexImg;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.News;
import com.tech.pojo.OrderBean;
import com.tech.pojo.Orderdetail;
import com.tech.pojo.Orderdetailaddress;
import com.tech.pojo.ParticipateJSON;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Productimage;
import com.tech.pojo.Producttype;
import com.tech.pojo.Qqgroup;
import com.tech.pojo.Recommend;
import com.tech.pojo.Role;
import com.tech.pojo.SCity;
import com.tech.pojo.SDistrict;
import com.tech.pojo.SProvince;
import com.tech.pojo.ShareJSON;
import com.tech.pojo.Shareimage;
import com.tech.pojo.Shareinfo;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.Suggestion;
import com.tech.pojo.SysConfigure;
import com.tech.pojo.SysInfoBean;
import com.tech.pojo.User;
import com.tech.proto.http.Proxy;
import com.tech.service.AdminService;
import com.tech.service.ApplymentionService;
import com.tech.service.CardpasswordService;
import com.tech.service.CommissionpointsService;
import com.tech.service.ConsumerdetailService;
import com.tech.service.ConsumetableService;
import com.tech.service.FunctionService;
import com.tech.service.LatestlotteryService;
import com.tech.service.NewsService;
import com.tech.service.OrderdetailaddressService;
import com.tech.service.ProductImageService;
import com.tech.service.ProductService;
import com.tech.service.ProducttypeService;
import com.tech.service.RecommendService;
import com.tech.service.RegionService;
import com.tech.service.RoleService;
import com.tech.service.ShareService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.SysConfigureService;
import com.tech.service.UserService;
import com.tech.sms.SmsSenderFactory;
import com.tech.test.VirtualUserBuyByType;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.ConfigUtil;
import com.tech.util.CutImages;
import com.tech.util.DateUtil;
import com.tech.util.EmailUtil;
import com.tech.util.MD5Util;
import com.tech.util.PaginationUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UploadImagesUtil;
import com.tech.util.UserNameUtil;

@Component("AdminIndexAction")
public class AdminIndexAction
  extends ActionSupport
{
  private static final long serialVersionUID = 5983843029308947750L;
  @Autowired
  private UserService userService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private FunctionService functionService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private ProducttypeService producttypeService;
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  private ProductImageService productImageService;
  @Autowired
  private NewsService newsService;
  @Autowired
  private ShareService shareService;
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private ConsumerdetailService consumerdetailService;
  @Autowired
  private ConsumetableService consumetableService;
  @Autowired
  private CardpasswordService cardpasswordService;
  @Autowired
  private RecommendService recommendService;
  @Autowired
  private ApplymentionService applymentionService;
  @Autowired
  private OrderdetailaddressService orderdetailaddressService;
  @Autowired
  private SysConfigureService sysConfigureService;
  @Autowired
  private RegionService regionService;
  @Autowired
  private CommissionpointsService commissionpointsService;
  @Autowired
  private RoleService roleService;
  @Autowired  
  Proxy httproxy;
  
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private Productimage productimage;
  private Producttype producttype;
  private News news;
  private User user;
  private Admin admin;
  private Shareinfo shareinfo;
  private Shareimage shareimage;
  private Qqgroup qqgroup;
  private List<Qqgroup> qqgroupList;
  private List<Producttype> productTypeList;
  private List<Producttype> productBrandList;
  private List<Product> productList;
  private List<Productimage> productimageList;
  private List<Shareimage> shareimageList;
  private List<News> newsList;
  private List<User> userList;
  private List<Admin> adminList;
  private List<Latestlottery> latestlotteryList;
  private List<ShareJSON> ShareJSONList;
  private ShareJSON shareJSON;
  private Latestlottery latestlottery;
  private List<OrderBean> orderBeanList;
  private List<Cardpassword> cardpasswordList;
  private List<Applymention> applymentionList;
  private List<Orderdetailaddress> orderdetailaddressList;
  private SysConfigure sysConfigure;
  private List<IndexImg> indexImgList;
  private IndexImg indexImg;
  private List<Suggestion> suggestionList;
  private List<Orderdetail> orderdetailList;
  private Orderdetailaddress orderdetailaddress;
  private List<SProvince> sProvinceList;
  private List<SCity> sCityList;
  private List<SDistrict> sDistrictList;
  private Commissionpoints commissionpoints;
  private Role role;
  private List<Role> roleList;
  private List<Role> roles;
  private List<Function> funcList;
  private Function funcRoot;
  private List<Function> functions;
  private List<Function> modules;
  private File myFile;
  private String myFileFileName;
  private String myFileContentType;
  private String imageFileName;
  private static final int BUFFER_SIZE = 102400;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private int robots;
  private String startDate;
  private String endDate;
  private String pId;
  private String cId;
  private String dId;
  private String id;
  private String keyword;
  private String userName;
  private String pwd;
  private String message;
  private String message2;
  private String userId;
  private String announcedTime;
  private String typeId;
  private String tId;
  private String typeName;
  private List<ProductJSON> productJSONList;
  private ProductJSON productJSON;
  private List<File> Filedata;
  private List<String> FiledataFileName;
  private List<String> imageContentType;
  private String channelUrl;
  private String productType;
  private HttpGet httpGet;
  private HttpPost httpPost;
  private String backUrl;
  private SysInfoBean sysInfoBean;
  private List<Spellbuyproduct> productPeriodList;
  private List<ParticipateJSON> orderJSONList;
  private String recharge;//充值额
  private String buy;//购买额
  private String pay;//支付额
  private String payIntegral;//支付积分
  private String addIntegral;//增加积分
  private String balance;//余额
  private String integral;//积分
  
  
  private static void copy(File src, File dst)
  {
    try
    {
      InputStream in = null;
      OutputStream out = null;
      try
      {
        in = new BufferedInputStream(new FileInputStream(src), 102400);
        out = new BufferedOutputStream(new FileOutputStream(dst), 102400);
        byte[] buffer = new byte[102400];
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
  
  public String admin()
  {
    return "index";
  }
  
  public String index()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      if (pages != null) {
        pageNo = Integer.parseInt(pages.split("_")[1]);
      }
      if (id.equals("hot20"))
      {
        Pagination hotPage = spellbuyrecordService.ProductByTypeIdList(typeId, "", "hot", pageNo, pageSize);
        List<?> HotList = hotPage.getList();
        productJSONList = new ArrayList();
        for (int i = 0; i < HotList.size(); i++)
        {
          productJSON = new ProductJSON();
          Object[] objs = (Object[])HotList.get(i);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		spellbuyproduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		spellbuyproduct = (Spellbuyproduct)objs[0];
      	}
          //product = ((Product)((Object[])HotList.get(i))[0]);
          //spellbuyproduct = ((Spellbuyproduct)((Object[])HotList.get(i))[1]);
          productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
          productJSON.setHeadImage(product.getHeadImage());
          productJSON.setProductId(product.getProductId());
          productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
          productJSON.setProductName(product.getProductName());
          productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          productJSON.setSinglePrice(product.getSinglePrice());
          productJSON.setProductTitle(product.getProductTitle());
          productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
          productJSONList.add(productJSON);
        }
        resultCount = hotPage.getResultCount();
        if ((typeId != null) && (!typeId.equals("")))
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&type=" + typeId + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        else
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        return "spellbuyProductList";
      }
      if (id.equals("date20"))
      {
        Pagination datePage = spellbuyrecordService.ProductByTypeIdList(typeId, "", "date", pageNo, pageSize);
        List<?>  dateList = datePage.getList();
        productJSONList = new ArrayList();
        for (int i = 0; i < dateList.size(); i++)
        {
          productJSON = new ProductJSON();
          Object[] objs = (Object[])dateList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
          //product = ((Product)((Object[])dateList.get(i))[0]);
          //spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
          productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
          productJSON.setHeadImage(product.getHeadImage());
          productJSON.setProductId(product.getProductId());
          productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
          productJSON.setProductName(product.getProductName());
          productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          productJSON.setSinglePrice(product.getSinglePrice());
          productJSON.setProductTitle(product.getProductTitle());
          productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
          productJSONList.add(productJSON);
        }
        resultCount = datePage.getResultCount();
        if ((typeId != null) && (!typeId.equals("")))
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&type=" + typeId + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        else
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        return "spellbuyProductList";
      }
      if (id.equals("price20"))
      {
        Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "", "price", pageNo, pageSize);
        List<?>  priceList = pricePage.getList();
        productJSONList = new ArrayList();
        for (int i = 0; i < priceList.size(); i++)
        {
          productJSON = new ProductJSON();
          Object[] objs = (Object[])priceList.get(i);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		spellbuyproduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		spellbuyproduct = (Spellbuyproduct)objs[0];
      	}
          //product = ((Product)((Object[])priceList.get(i))[0]);
          //spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
          productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
          productJSON.setHeadImage(product.getHeadImage());
          productJSON.setProductId(product.getProductId());
          productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
          productJSON.setProductName(product.getProductName());
          productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          productJSON.setSinglePrice(product.getSinglePrice());
          productJSON.setProductTitle(product.getProductTitle());
          productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
          productJSONList.add(productJSON);
        }
        resultCount = pricePage.getResultCount();
        if ((typeId != null) && (!typeId.equals("")))
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&type=" + typeId + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        else
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        return "spellbuyProductList";
      }
      if (id.equals("priceAsc20"))
      {
        Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "", "priceAsc", pageNo, pageSize);
        List<?>  priceList = pricePage.getList();
        productJSONList = new ArrayList();
        for (int i = 0; i < priceList.size(); i++)
        {
          productJSON = new ProductJSON();
          Object[] objs = (Object[])priceList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
          //product = ((Product)((Object[])priceList.get(i))[0]);
          //spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
          productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
          productJSON.setHeadImage(product.getHeadImage());
          productJSON.setProductId(product.getProductId());
          productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
          productJSON.setProductName(product.getProductName());
          productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          productJSON.setSinglePrice(product.getSinglePrice());
          productJSON.setProductTitle(product.getProductTitle());
          productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
          productJSONList.add(productJSON);
        }
        resultCount = pricePage.getResultCount();
        if ((typeId != null) && (!typeId.equals("")))
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&type=" + typeId + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        else
        {
          pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/index.action?id=" + id + "&pageNo=");
          pageString = pageString.replace(".html", "");
        }
        return "spellbuyProductList";
      }
    }
    return "index_index";
  }
  
  @SuppressWarnings("unchecked")
  public String productPeriodList(){
	  if (Struts2Utils.getSession().getAttribute("admin") != null)
	    {
	      if (pageNo == 0) {
	        pageNo = 1;
	      }
	      if (pages != null) {
	        pageNo = Integer.parseInt(pages.split("_")[1]);
	      }
	      product = productService.findById(id);
		  Pagination periodPage = spellbuyproductService.productPeriodList(Integer.parseInt(id), pageNo, pageSize);
		  productPeriodList = new ArrayList<Spellbuyproduct>();
		  List<Object[]> objectList = (List<Object[]>)periodPage.getList();
		  for (Object[] objects : objectList)
		  {
		    productPeriodList.add((Spellbuyproduct)objects[1]);
		  }
		  resultCount = periodPage.getResultCount();
	      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/productPeriodList.action?id=" + id + "&pageNo=");
	      pageString = pageString.replace(".html", "");
		  return "productPeriodList";
	    }
	  return "index_index";
  }
  
  @SuppressWarnings("unchecked")
  public String periodOrderList(){
	  if (Struts2Utils.getSession().getAttribute("admin") != null)
	    {
		  if (pageNo == 0) {
		      pageNo = 1;
		    }

	      if (pages != null) {
	        pageNo = Integer.parseInt(pages.split("_")[1]);
	      }
		  	orderJSONList = new ArrayList();
		    Pagination pagination = spellbuyrecordService.LatestParticipate(id, pageNo, pageSize);
		    
		    List<?> list = pagination.getList();
		    Spellbuyrecord spellbuyrecord = null;
		    for (int i = 0; i < list.size(); i++)
		    {
		      ParticipateJSON participateJSON = new ParticipateJSON();
		      spellbuyrecord = ((Spellbuyrecord)((Object[])list.get(i))[0]);
		      user = ((User)((Object[])list.get(i))[1]);
		      String userName = UserNameUtil.userName(user);
		      participateJSON.setBuyCount(spellbuyrecord.getBuyPrice().toString());
		      participateJSON.setBuyDate(spellbuyrecord.getBuyDate());
		      participateJSON.setBuyId(spellbuyrecord.getSpellbuyRecordId().toString());
		      participateJSON.setIp_address(user.getIpAddress());
		      participateJSON.setIp_location(user.getIpLocation());
				if (null == user.getMail() && null == user.getMobilePhone()) {
					//机器人
					participateJSON.setBuySource(1);
				} else {
					//会员购买
					participateJSON.setBuySource(2);
				}
		      
		      participateJSON.setUserName(userName);
		      participateJSON.setUserId(String.valueOf(user.getUserId()));
		      participateJSON.setUserFace(user.getFaceImg());
		      orderJSONList.add(participateJSON);
		    }
		    resultCount = spellbuyrecordService.LatestParticipateByCount(id);
		    pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/periodOrderList.action?id=" + id + "&pageNo=");
		    pageString = pageString.replace(".html", "");
		    spellbuyproduct = spellbuyproductService.findById(id);
		  return "periodOrderList";
	    }
	  return "index_index";
  }
  
  public String totalRecharge()
  {
	  if (Struts2Utils.getSession().getAttribute("admin") != null)
	    {
		  String start = startDate + " 00:00:00";
		  String end = endDate + " 23:59:59";
		  Double rechargeDouble = consumetableService.totalRecharge(start, end);
		  if (null != rechargeDouble){
			  recharge = rechargeDouble.toString();
		  }else{
			  recharge = "0";
		  }
		  Double buyDouble = consumetableService.totalBuy(start, end);
		  if (null != buyDouble){
			  buy = buyDouble.toString();
		  }else{
			  buy = "0";
		  }
		  Double payDouble = consumetableService.totalPay(start, end);
		  if (null != payDouble){
			  pay = payDouble.toString();
		  }else{
			  pay = "0";
		  }
		  
		  Double payIntegralDouble =  commissionpointsService.totalPay(start, end);
		  if (null != payIntegralDouble){
			  payIntegral = payIntegralDouble.toString();
		  }else{
			  payIntegral = "0";
		  }
		  
		  Double addIntegralDouble =  commissionpointsService.totalAdd(start, end);
		  if (null != addIntegralDouble){
			  setAddIntegral(addIntegralDouble.toString());
		  }else{
			  setAddIntegral("0");
		  }
		  
		  return "totalRecharge";
	    }
	  return "index_index";
  }
  
  public String totalOverplus()
  {
	  if (Struts2Utils.getSession().getAttribute("admin") != null)
	    {
		  BigDecimal balanceDouble = userService.totalBalance();
		  if (null != balanceDouble){
			  balance = balanceDouble.toString();
		  }else{
			  balance = "0";
		  }
		  BigDecimal integralDouble = userService.totalIntegral();
		  if (null != integralDouble){
			  integral = integralDouble.toString();
		  }else{
			  integral = "0";
		  }
		  
		  return "totalOverplus";
	    }
	  return "index_index";
  }
  
  public String upSpellbuyproduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        product = ((Product)productService.findById(id));
        this.spellbuyproduct = spellbuyproductService.findSpellbuyproductByStatus(product.getProductId());
        if (this.spellbuyproduct == null)
        {
          int productPeriod = 0;
          Spellbuyproduct spellbuyproduct = spellbuyproductService.findSpellbuyproductLastPeriod(product.getProductId());
          if (spellbuyproduct != null)
          {
            productPeriod = spellbuyproduct.getProductPeriod().intValue();
            productPeriod++;
          }
          else
          {
            productPeriod = 1;
          }
          if (productPeriod > 0)
          {
            spellbuyproduct = new Spellbuyproduct();
            spellbuyproduct.setFkProductId(product.getProductId());
            spellbuyproduct.setProductPeriod(Integer.valueOf(productPeriod));
            spellbuyproduct.setSpellbuyCount(Integer.valueOf(0));
            spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct.setSpellbuyPrice(product.getProductPrice());
            spellbuyproduct.setSpSinglePrice(product.getSinglePrice());
            spellbuyproduct.setSpellbuyLimit(product.getProductLimit());
            spellbuyproduct.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct.setSpStatus(Integer.valueOf(0));
            if (product.getAttribute71().equals("hot")) {
              spellbuyproduct.setSpellbuyType(Integer.valueOf(8));
            } else {
              spellbuyproduct.setSpellbuyType(Integer.valueOf(0));
            }
            spellbuyproductService.add(spellbuyproduct);
            product.setAttribute71(String.valueOf(productPeriod + 1));
          }
        }
        product.setStatus(Integer.valueOf(1));
        productService.add(product);
        Struts2Utils.renderText("success", new String[0]);
      }
      else
      {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
    return null;
  }
  
  public void downSpellbuyproduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        product = ((Product)productService.findById(id));
        product.setStatus(Integer.valueOf(2));
        productService.add(product);
        Struts2Utils.renderText("success", new String[0]);
      }
      else
      {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  public void hotProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        try
        {
          product = ((Product)productService.findById(id));
          product.setAttribute71("hot");
          productService.add(product);
          List<Spellbuyproduct> list = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(Integer.valueOf(Integer.parseInt(id)));
          if ((list != null) && (list.size() > 0))
          {
            spellbuyproduct = ((Spellbuyproduct)list.get(0));
            spellbuyproduct.setSpellbuyType(Integer.valueOf(8));
            spellbuyproductService.add(spellbuyproduct);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.renderText("success", new String[0]);
      }
      else
      {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  public void downHotProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        try
        {
          product = ((Product)productService.findById(id));
          product.setAttribute71("0");
          productService.add(product);
          List<Spellbuyproduct> list = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(Integer.valueOf(Integer.parseInt(id)));
          if ((list != null) && (list.size() > 0))
          {
            spellbuyproduct = ((Spellbuyproduct)list.get(0));
            spellbuyproduct.setSpellbuyType(Integer.valueOf(0));
            spellbuyproductService.add(spellbuyproduct);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.renderText("success", new String[0]);
      }
      else
      {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  public String announcedProduct()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination hotPage = spellbuyproductService.announcedProduct(pageNo, pageSize);
      List<?>  HotList = hotPage.getList();
      productJSONList = new ArrayList();
      for (int i = 0; i < HotList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])HotList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])HotList.get(i))[1]);
        productJSON.setCurrentBuyCount(product.getProductId());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(product.getProductName());
        productJSON.setProductPrice(product.getProductPrice());
        productJSON.setSinglePrice(product.getSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
        productJSONList.add(productJSON);
      }
      resultCount = hotPage.getResultCount();
      if ((typeId != null) && (!typeId.equals("")))
      {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/announcedProduct.action?pageNo=");
        pageString = pageString.replace(".html", "");
      }
      else
      {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/announcedProduct.action?pageNo=");
        pageString = pageString.replace(".html", "");
      }
      return "announcedProductList";
    }
    return "index_index";
  }
  
	public String login() {
		if (StringUtil.isBlank(userName)) {
			Struts2Utils.render("text/html", "<script>alert(\"用户名不能为空！\");window.location.href=\"/admin_back/admin.html\";</script>", new String[] { "encoding:UTF-8" });
			return null;
		}
		String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
		if (ip == null) {
			ip = Struts2Utils.getRequest().getRemoteAddr();
		}
		Admin admin = null;
		try{
			try {
        		pwd = URLDecoder.decode(pwd, "UTF-8");
        	} catch (UnsupportedEncodingException e1) {
        		e1.printStackTrace();
        	}
			pwd = MessageDigestUtils.sha256Hex(pwd);
			admin = adminService.login(userName, pwd, ip);
			Struts2Utils.getSession().setAttribute("admin", admin);
			// 将权限集放入session
			Set<String> fiSet = functionService.getFunctionItems(admin.getId());
			Struts2Utils.getSession().setAttribute(Admin.RIGHTS_KEY, fiSet);
			Struts2Utils.render("text/html",
					"<script>window.location.href=\"/admin_back/main.action\";</script>",
					new String[] { "encoding:UTF-8" });
		} catch (UsernameNotFoundException e) {
			Struts2Utils.render("text/html", "<script>alert(\"用户名不存在！\");window.location.href=\"/admin_back/admin.html\";</script>", new String[] { "encoding:UTF-8" });
		} catch (BadCredentialsException e) {
			Struts2Utils.render("text/html", "<script>alert(\"密码错误！\");window.location.href=\"/admin_back/admin.html\";</script>", new String[] { "encoding:UTF-8" });
		} catch (AccountStatusException e) {
			Struts2Utils.render("text/html", "<script>alert(\"账号未激活！\");window.location.href=\"/admin_back/admin.html\";</script>", new String[] { "encoding:UTF-8" });
		}
		
		return null;
	}
  
  public String logOut()
  {
    Struts2Utils.getSession().removeAttribute("admin");
    return "index";
  }
  
	public String main() {
		admin = (Admin)Struts2Utils.getSession().getAttribute("admin");
		if (admin != null) {
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			modules = functionService.getRoots(admin.getId(),true);
			return "main";
		}
		return "index_index";
	}

  public void numberCount()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination page = latestlotteryService.LatestAnnounced(null, pageNo, pageSize);
      Pagination hotPage = spellbuyrecordService.ProductByTypeIdList(typeId, "", "hot", pageNo, pageSize);
      Pagination page2 = shareService.loadPageShare("new20", pageNo, pageSize);
      int userCount = userService.getCountUser().intValue();
      Long buyCount = Long.valueOf(Long.parseLong(spellbuyrecordService.getAllByCount().toString()));
      int lotteryCount = page.getResultCount();
      int productCount = hotPage.getResultCount();
      int shareCount = page2.getResultCount();
      message = (userCount + "_" + buyCount + "_" + productCount + "_" + lotteryCount + "_" + shareCount);
      Struts2Utils.renderText(message, new String[0]);
    }
  }
  
  public String toAddProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      productBrandList = producttypeService.listByBrand(null);
      return "addProduct";
    }
    return "index_index";
  }
  
  public void productBrandListByTypeId()
  {
    productBrandList = producttypeService.listByBrand(typeId);
    Struts2Utils.renderJson(productBrandList, new String[0]);
  }
  
  public String addProduct()
    throws Exception
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        String productImgPath = "/productImg/show";
        productService.add(product);
        try
        {
          if (myFile != null)
          {
            myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
            imageFileName = (new Date().getTime() + myFileFileName);
            File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), String.valueOf(product.getProductId())) + "/" + imageFileName);
            copy(myFile, imageFile);
            CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
            product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        product.setProductRealPrice(String.valueOf(product.getProductPrice()));
        product.setStatus(Integer.valueOf(0));
        product.setAttribute71(String.valueOf(1));
        productService.add(product);
        
        return "success";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权添加！，请联系管理员！\");window.location.href=\"/admin_back/productList.action\";</script>", new String[] { "encoding:UTF-8" });
    }
    return "index_index";
  }
  
  public String toUpdate()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      product = ((Product)productService.findById(id));
      productTypeList = producttypeService.queryAllProductType();
      productBrandList = producttypeService.listByBrand(null);
      String queryString = Struts2Utils.getRequest().getQueryString();
      backUrl = queryString.substring(queryString.indexOf("backUrl=") + 8, queryString.length());
      
      return "addProduct";
    }
    return "index_index";
  }
  
  public void update()
    throws Exception
  {
    HttpServletRequest request = Struts2Utils.getRequest();
    HttpServletResponse response = Struts2Utils.getResponse();
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        String productImgPath = "/productImg/show";
        if (myFile != null)
        {
          myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
          imageFileName = (new Date().getTime() + myFileFileName);
          File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), String.valueOf(product.getProductId())) + "/" + imageFileName);
          copy(myFile, imageFile);
          CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
          product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
        }
        spellbuyproduct = spellbuyproductService.findSpellbuyproductByStatus(product.getProductId());
        if (spellbuyproduct != null)
        {
          spellbuyproduct.setSpSinglePrice(product.getSinglePrice());
          spellbuyproductService.add(spellbuyproduct);
        }
        productService.add(product);
        Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");window.location.href=\"/admin_back/productList.action\";</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public void deleteProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
      try
        {
         productService.delete(Integer.valueOf(Integer.parseInt(id)));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权删除！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
      //Struts2Utils.render("text/html", "<script>alert(\"不支持商品删除操作！\");history.go(-1);</script>", StringUtil.ENCA_UTF8);
    }
  }
  
  public String toAddProductType()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      if (id != null) {
        producttype = ((Producttype)producttypeService.findById(id));
      }
      return "addProductType";
    }
    return "index_index";
  }
  
  public String addProductType()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (producttype.getFtypeId() == null) {
        producttype.setFtypeId("1000");
      }
      if (canModified())
      {
        producttype.setStypeId("0");
        producttype.setAttribute70("type");
        if (producttype.getTypeId() == null)
        {
          if (producttypeService.findTypeByName(producttype.getTypeName()) != null)
          {
            Struts2Utils.render("text/html", "<script>alert(\"该分类名称已经存在！\");history.go(-2);</script>", new String[] { "encoding:UTF-8" });
            return null;
          }
          producttypeService.add(producttype);
        }
        else
        {
          producttypeService.add(producttype);
        }
        return "addProductTypeSuccess";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }

	public void deleteProductType() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (canModified()) {
				try {
					producttypeService.delete(Integer.valueOf(Integer.parseInt(id)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>",
						new String[] { "encoding:UTF-8" });
			} else {
				Struts2Utils
						.render("text/html",
								"<script>alert(\"测试用户无权删除！，请联系管理员！\");history.go(-1);</script>",
								new String[] { "encoding:UTF-8" });
			}
		}
	}
  
  public String productTypeList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      robots = vuserService.getRobots();
      return "productTypeList";
    }
    return "index_index";
  }
  
  public String productBrandList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      productBrandList = producttypeService.listByBrand(null);
      robots = vuserService.getRobots();
      return "productBrandList";
    }
    return "index_index";
  }
  
  public String toAddProductBrand()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      if (id != null) {
        producttype = producttypeService.findBrandById(id);
      }
      return "addProductBrand";
    }
    return "index_index";
  }
  
  public String addProductBrand()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        if (producttype.getFtypeId() == null) {
          producttype.setFtypeId("1000");
        }
        producttype.setStypeId("0");
        producttype.setAttribute70("brand");
        if (producttype.getTypeId() == null)
        {
          if (producttypeService.findBrandByName(producttype.getTypeName()) != null)
          {
            Struts2Utils.render("text/html", "<script>alert(\"该品牌名称已经存在！\");history.go(-2);</script>", new String[] { "encoding:UTF-8" });
            return null;
          }
          producttypeService.add(producttype);
        }
        else
        {
          producttypeService.add(producttype);
        }
        return "addProductBrandSuccess";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public void deleteProductBrand() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (canModified()) {
				try {
					producttypeService.delete(Integer.valueOf(Integer.parseInt(id)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>",
						new String[] { "encoding:UTF-8" });
			} else {
				Struts2Utils
						.render("text/html",
								"<script>alert(\"测试用户无权删除！，请联系管理员！\");history.go(-1);</script>",
								new String[] { "encoding:UTF-8" });
			}
		}
	}
  
  /*
   * 商品列表
   * yzp
   * yykj taobao
  */
  public String productList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      if (pages != null) {
        pageNo = Integer.parseInt(pages.split("_")[1]);
      }
      if (typeId == null) {
        typeId = "";
      }
      if (keyword == null) {
        keyword = "";
      }
      productTypeList = producttypeService.queryAllProductType();
      productBrandList = producttypeService.listByBrand(null);
      Pagination datePage = productService.searchProduct(typeId, keyword, pageNo, pageSize);
      productList = (List<Product>)datePage.getList();
      resultCount = datePage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/productList.action?typeId=" + typeId + "&keyword=" + keyword + "&pageNo=");
      pageString = pageString.replace(".html", "");
      robots     = vuserService.getRobots();
      return "productList";
    }
    return "index_index";
  }
  
  public String toAddProductImg()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productimageList = productImageService.findByProductId(id, "show");
      return "addProductImg";
    }
    return "index_index";
  }
  
  public String addProductImg()
    throws Exception
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        String productImgPath = "/productImg/imagezoom";
        long fileDateName = 0L;
        String imagePath = null;
        if ((Filedata == null) || (Filedata.size() == 0)) {
          return null;
        }
        for (int i = 0; i < Filedata.size(); i++) {
          try
          {
            myFile = ((File)Filedata.get(i));
            if (myFile != null)
            {
              myFileFileName = ((String)FiledataFileName.get(i)).substring(((String)FiledataFileName.get(i)).lastIndexOf("."), ((String)FiledataFileName.get(i)).length());
              fileDateName = new Date().getTime();
              imageFileName = (fileDateName + myFileFileName);
              imagePath = UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), id) + "/";
              File imageFile = new File(imagePath + imageFileName);
              copy(myFile, imageFile);
              CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath + fileDateName + "_mid" + myFileFileName));
              CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath + fileDateName + "_small" + myFileFileName));
              productimage = new Productimage();
              productimage.setPiProductId(Integer.valueOf(Integer.parseInt(id)));
              productimage.setImage(String.valueOf(fileDateName));
              productimage.setImageType(myFileFileName);
              productimage.setAttribute75("show");
              productImageService.add(productimage);
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        Struts2Utils.render("text/html", "<script>alert(\"上传成功！\");history.go(-2);</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
    return null;
  }
  
  public void doDeleteProductImg()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        try
        {
          productImageService.delete(Integer.valueOf(Integer.parseInt(id)));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.render("text/html", "<script>alert(\"删除成功！\");history.go(-2);</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String toAddSpellbuyProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "addSpellbuyProduct";
    }
    return "index_index";
  }
  
  public String addSpellbuyProduct()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "spellbuyProductList";
    }
    return "index_index";
  }
  
  public String uploadImages()
    throws Exception
  {
    try
    {
      String productImgPath = "/productImg/detail";
      long fileDateName = 0L;
      String imagePath = null;
      if ((Filedata == null) || (Filedata.size() == 0)) {
        return null;
      }
      for (int i = 0; i < Filedata.size(); i++)
      {
        myFile = ((File)Filedata.get(i));
        if (myFile != null)
        {
          myFileFileName = ((String)FiledataFileName.get(i)).substring(((String)FiledataFileName.get(i)).lastIndexOf("."), ((String)FiledataFileName.get(i)).length()).toLowerCase();
          fileDateName = new Date().getTime();
          imageFileName = (fileDateName + myFileFileName);
          imagePath = UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), DateUtil.dateToStr(new Date())) + "/";
          File imageFile = new File(imagePath + imageFileName);
          copy(myFile, imageFile);
        }
      }
      StringBuilder sb = new StringBuilder();
      
      sb.append('{');
      sb.append("\"state\":\"").append("SUCCESS").append("\",");
      sb.append("\"url\":\"").append(productImgPath + "/" + DateUtil.dateToStr(new Date()) + "/" + imageFileName).append("\",");
      sb.append("\"title\":\"").append(imageFileName).append("\",");
      sb.append("\"original\":\"").append(imageFileName).append("\"");
      sb.append('}');
      sb.append(",");
      sb.deleteCharAt(sb.length() - 1);
      
      Struts2Utils.renderJson(sb.toString(), new String[0]);
    }
    catch (Exception e)
    {
      Struts2Utils.renderText("error", new String[0]);
      e.printStackTrace();
    }
    return null;
  }
  
  public String crawl()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      productTypeList = producttypeService.queryAllProductType();
      return "crawl";
    }
    return "index_index";
  }
  
  public String doCrawl()
    throws Exception
  {
    if ((pages != null) && (!pages.equals("")))
    {
      String url = "";
      int page = Integer.parseInt(pages);
      for (int i = 1; i < page; i++) {
        try
        {
          url = channelUrl.substring(0, channelUrl.lastIndexOf("0-1-1-") + 6);
          url = url + i + channelUrl.substring(channelUrl.lastIndexOf("0-1-1-") + 7, channelUrl.length());
          System.err.println(url);
          Document document = Jsoup.parse(new URL(url), 60000);
          Elements elements = null;
          elements = document.select("div.plist > ul > li");
          if (elements.size() == 0) {
            elements = document.select("div#plist > ul > li");
          }
          for (Element element : elements) {
            try
            {
              String productImg = "";
              String productName = "";
              String productTitle = "";
              int productPrice = 0;
              int productRealPrice = 0;
              String productDetail = "";
              String headImage = "";
              String Attribute_71 = "1";
              String strImg = element.select("div.p-img > a > img").first().attr("src");
              if (strImg.indexOf("360buyimg.com") != -1)
              {
                productImg = strImg;
              }
              else
              {
                String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
                productImg = strImg2;
              }
              productImg = productImg.replace(".com/n2/", ".com/n1/");
              productName = element.select("div.p-name > a").text();
              product = productService.findProductByName(productName);
              if (product == null)
              {
                product = new Product();
                String proUrl = element.select("div.p-name > a").attr("href");
                System.err.println(proUrl);
                File myFile = saveURLFile(productImg);
                
                String myFileFileName = myFile.getName();
                product.setAttribute71("1");
                product.setProductType(Integer.valueOf(Integer.parseInt(productType)));
                product.setProductDetail(proUrl);
                product.setProductName(productName);
                product.setProductPrice(Integer.valueOf(0));
                product.setProductRealPrice(String.valueOf(0));
                product.setProductTitle(productName);
                product.setStatus(Integer.valueOf(0));
                productService.add(product);
                String productImgPath = "/productImg/show";
                if (myFile != null)
                {
                  myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
                  String imageFileName = new Date().getTime() + myFileFileName;
                  File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), String.valueOf(product.getProductId())) + "/" + imageFileName);
                  copy(myFile, imageFile);
                  CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
                  product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
                }
                productService.add(product);
                
                Document document2 = Jsoup.parse(new URL(proUrl), 60000);
                
                Elements element2 = document2.select("div.spec-items > ul > li");
                int count = 0;
                for (Iterator<Element> localIterator2 = element2.iterator();
                	localIterator2.hasNext();)
                {
                  Element element3 = (Element)localIterator2.next();
                  try
                  {
                    count++;
                    String img = element3.select("img").attr("src");
                    img = img.replace(".com/n5/", ".com/n0/");
                    System.err.println(img);
                    productimage = new Productimage();
                    String productImgPath2 = "/productImg/imagezoom";
                    long fileDateName = 0L;
                    String imagePath = null;
                    File myFile2 = saveURLFile(img);
                    String myFileFileName2 = myFile2.getName();
                    if (myFile2 != null)
                    {
                      myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
                      fileDateName = new Date().getTime();
                      String imageFileName = fileDateName + myFileFileName;
                      imagePath = UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath2))).append("/").toString(), String.valueOf(product.getProductId())) + "/";
                      File imageFile = new File(imagePath + imageFileName);
                      copy(myFile, imageFile);
                      CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath + fileDateName + "_mid" + myFileFileName));
                      CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath + fileDateName + "_small" + myFileFileName));
                      productimage.setPiProductId(product.getProductId());
                      productimage.setImage(String.valueOf(fileDateName));
                      productimage.setImageType(myFileFileName);
                      productimage.setAttribute75("show");
                      productImageService.add(productimage);
                    }
                    if (count != 6) {
                      if (localIterator2.hasNext()) {
                        continue;
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
      }
    }
    System.err.println("结束");
    Struts2Utils.renderText("结束", new String[0]);
    return null;
  }
  
  public String doCrawlShare()
    throws Exception
  {
    if ((pages != null) && (!pages.equals("")))
    {
      String url = "";
      int page = Integer.parseInt(pages);
      for (int i = 1; i < page; i++) {
        try
        {
          url = channelUrl.substring(0, channelUrl.lastIndexOf("0-1-1-") + 6);
          url = url + i + channelUrl.substring(channelUrl.lastIndexOf("0-1-1-") + 7, channelUrl.length());
          System.err.println(url);
          Document document = Jsoup.parse(new URL(url), 60000);
          Elements elements = null;
          elements = document.select("div.plist > ul > li");
          if (elements.size() == 0) {
            elements = document.select("div#plist > ul > li");
          }
          label1158:
          for (Element element : elements) {
            try
            {
              String productImg = "";
              String productName = "";
              String productTitle = "";
              int productPrice = 0;
              int productRealPrice = 0;
              String productDetail = "";
              String headImage = "";
              String Attribute_71 = "1";
              String strImg = element.select("div.p-img > a > img").first().attr("src");
              if (strImg.indexOf("360buyimg.com") != -1)
              {
                productImg = strImg;
              }
              else
              {
                String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
                productImg = strImg2;
              }
              productImg = productImg.replace(".com/n2/", ".com/n1/");
              productName = element.select("div.p-name > a").text();
              product = productService.findProductByName(productName);
              if (product == null)
              {
                product = new Product();
                String proUrl = element.select("div.p-name > a").attr("href");
                System.err.println(proUrl);
                File myFile = saveURLFile(productImg);
                
                String myFileFileName = myFile.getName();
                product.setAttribute71("1");
                product.setProductType(Integer.valueOf(Integer.parseInt(productType)));
                product.setProductDetail(proUrl);
                product.setProductName(productName);
                product.setProductPrice(Integer.valueOf(0));
                product.setProductRealPrice(String.valueOf(0));
                product.setProductTitle(productName);
                product.setStatus(Integer.valueOf(0));
                productService.add(product);
                String productImgPath = "/productImg/show";
                if (myFile != null)
                {
                  myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
                  String imageFileName = new Date().getTime() + myFileFileName;
                  File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), String.valueOf(product.getProductId())) + "/" + imageFileName);
                  copy(myFile, imageFile);
                  CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
                  product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
                }
                productService.add(product);
                
                Document document2 = Jsoup.parse(new URL(proUrl), 60000);
                
                Elements element2 = document2.select("div.spec-items > ul > li");
                int count = 0;
                for (Iterator<Element> localIterator2 = element2.iterator();
                	localIterator2.hasNext();)
                {
                  Element element3 = (Element)localIterator2.next();
                  try
                  {
                    count++;
                    String img = element3.select("img").attr("src");
                    


                    img = img.replace(".com/n5/", ".com/n0/");
                    System.err.println(img);
                    productimage = new Productimage();
                    String productImgPath2 = "/productImg/imagezoom";
                    long fileDateName = 0L;
                    String imagePath = null;
                    File myFile2 = saveURLFile(img);
                    String myFileFileName2 = myFile2.getName();
                    if (myFile2 != null)
                    {
                      myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
                      fileDateName = new Date().getTime();
                      String imageFileName = fileDateName + myFileFileName;
                      imagePath = UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath2))).append("/").toString(), String.valueOf(product.getProductId())) + "/";
                      File imageFile = new File(imagePath + imageFileName);
                      copy(myFile, imageFile);
                      CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath + fileDateName + "_mid" + myFileFileName));
                      CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath + fileDateName + "_small" + myFileFileName));
                      productimage.setPiProductId(product.getProductId());
                      productimage.setImage(String.valueOf(fileDateName));
                      productimage.setImageType(myFileFileName);
                      productimage.setAttribute75("show");
                      productImageService.add(productimage);
                    }
                    if (count != 6) {
                      if (localIterator2.hasNext()) {
                        continue;
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
      }
    }
    System.err.println("结束");
    Struts2Utils.renderText("结束", new String[0]);
    return null;
  }
  
  public static File saveURLFile(String fileUrl)
    throws MalformedURLException
  {
    File file = null;
    try
    {
      URL url = new URL(fileUrl);
      String myFileFileName = fileUrl;
      URLConnection connect = url.openConnection();
      connect.connect();
      InputStream input = connect.getInputStream();
      myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
      String image = ServletActionContext.getServletContext().getRealPath("/uploadImages") + "/" + "imageTemp" + myFileFileName;
      file = new File(image);
      
      OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
      
      int length = 1048576;
      byte[] a = new byte[length];
      while ((length = input.read(a)) > 0) {
        out.write(a, 0, length);
      }
      input.close();
      out.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return file;
  }
  
  public String toAddNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "addNews";
    }
    return "index_index";
  }
  
  public String addNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        if ((news.getPostDate() == null) || (news.getPostDate().equals(""))) {
          news.setPostDate(DateUtil.DateToStr(new Date()));
        }
        newsService.add(news);
        return "successNews";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String toUpdateNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      news = ((News)newsService.findById(id));
      return "addNews";
    }
    return "index_index";
  }
  
  public String updateNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        newsService.add(news);
        return "successNews";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String deleteNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        newsService.delete(Integer.valueOf(Integer.parseInt(id)));
        return "successNews";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
    }
    return "index_index";
  }
  
  public String newsList()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination datePage = newsService.newsList(pageNo, pageSize);
      newsList = (List<News>)datePage.getList();
      resultCount = datePage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/newsList.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "newsList";
    }
    return "index_index";
  }
  
  public String replaceNews()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        com.tech.action.IndexAction.newsList = newsService.indexNews();
        Struts2Utils.render("text/html", "<script>alert(\"更新新闻成功！\");window.location.href=\"/admin_back/newsList.action\";</script>", new String[] { "encoding:UTF-8" });
        return null;
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");window.location.href=\"/admin_back/newsList.action\";</script>", new String[] { "encoding:UTF-8" });
    }
    return null;
  }
  
  public String indexImgAll()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      indexImgList = sysConfigureService.IndexImgAll();
      return "indexImgAll";
    }
    return "index_index";
  }
  
  public String toAddIndexImg()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (StringUtil.isNotBlank(id)) {
        indexImg = sysConfigureService.findByIndexImgId(id);
      }
      return "toIndexImg";
    }
    return "index_index";
  }
  
  public void doAddIndexImg()
    throws Exception
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        String productImgPath = "/productImg/show";
        try
        {
          if (myFile != null)
          {
            myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
            imageFileName = (new Date().getTime() + myFileFileName);
            File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), "indexImg") + "/" + imageFileName);
            copy(myFile, imageFile);
            CutImages.equimultipleConvert(710, 300, imageFile, imageFile);
            indexImg.setProImg(productImgPath + "/" + "indexImg" + "/" + imageFileName);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        sysConfigureService.addIndexImg(indexImg);
        ApplicationListenerImpl.indexImgAll = sysConfigureService.initializationIndexImgAll();
        //刷新微信端应用
        String wxServers = ConfigUtil.getValue("wxServers");
        if(StringUtil.isNotBlank(wxServers)){
      	  for(String wxServer:wxServers.split(",")){
      		  if(StringUtil.isNotBlank(wxServer)){
      			  String notifyUrl = wxServer+"/reloadConfigure.html";
      			  try{
	        			  String result = httproxy.get(notifyUrl, null);
	        			  System.out.println(notifyUrl+" result:"+result);
      			  }catch(Exception e){
      				  //e.printStackTrace();
      			  }
      		  }
      	  }
        }
        Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/indexImgAll.action\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public void delIndexImg()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        sysConfigureService.delIndexImg(Integer.valueOf(Integer.parseInt(id)));
        ApplicationListenerImpl.indexImgAll = sysConfigureService.initializationIndexImgAll();
        //刷新微信端应用
        String wxServers = ConfigUtil.getValue("wxServers");
        if(StringUtil.isNotBlank(wxServers)){
      	  for(String wxServer:wxServers.split(",")){
      		  if(StringUtil.isNotBlank(wxServer)){
      			  String notifyUrl = wxServer+"/reloadConfigure.html";
      			  try{
	        			  String result = httproxy.get(notifyUrl, null);
	        			  System.out.println(notifyUrl+" result:"+result);
      			  }catch(Exception e){
      				  //e.printStackTrace();
      			  }
      		  }
      	  }
        }
        Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/indexImgAll.action\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String suggestion()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = sysConfigureService.suggestionList(pageNo, pageSize);
      suggestionList = (List<Suggestion>)page.getList();
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/suggestion.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "suggestion";
    }
    return "index_index";
  }
  
  public void delSuggestion()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        try
        {
          if (StringUtil.isNotBlank(id)) {
            sysConfigureService.delSuggestion(Integer.valueOf(Integer.parseInt(id)));
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权删除！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String toQqGroup()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "toQqGroup";
    }
    return "index_index";
  }
  
  public void doQqGroup()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        regionService.addQQGroup(qqgroup);
        Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/qqGroupByList.action\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public void delQqGroup()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        regionService.delQQGroup(Integer.valueOf(Integer.parseInt(id)));
        Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/qqGroupByList.action\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String qqGroupByList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      qqgroupList = regionService.qqGroupByList(pId, cId, dId);
      return "qqGroupList";
    }
    return "index_index";
  }
  
  public String shareList()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination page = shareService.adminShareList(typeId, id, pageNo, pageSize);
      List<?>  pageList = page.getList();
      ShareJSONList = new ArrayList();
      for (int i = 0; i < pageList.size(); i++)
      {
        shareJSON = new ShareJSON();
        shareinfo = ((Shareinfo)((Object[])pageList.get(i))[0]);
        latestlottery = ((Latestlottery)((Object[])pageList.get(i))[1]);
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
        shareJSON.setShareTitle(shareinfo.getShareTitle());
        shareJSON.setUid(shareinfo.getUid());
        shareJSON.setUpCount(shareinfo.getUpCount());
        shareJSON.setUserName(userName);
        shareJSON.setStatus(shareinfo.getStatus());
        ShareJSONList.add(shareJSON);
      }
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/shareList.action?id=" + id + "&typeId=" + typeId + "&pageNo=");
      pageString = pageString.replace(".html", "");
      return "shareList";
    }
    return "index_index";
  }
  
  public String shareByStatus()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination page = latestlotteryService.adminByLatestAnnounced(pageNo, pageSize, typeId, tId, typeName);
      resultCount = page.getResultCount();
      List<?> objList = page.getList();
      latestlotteryList = new ArrayList();
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
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/shareByStatus.action?tId=" + tId + "&id=" + id + "&pageNo=");
      pageString = pageString.replace(".html", "");
      return "shareByStatus";
    }
    return "index_index";
  }
  
  public String latestList()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination page = latestlotteryService.adminByLatestAnnounced(pageNo, pageSize, typeId, tId, typeName);
      resultCount = page.getResultCount();
      List<?> objList = page.getList();
      latestlotteryList = new ArrayList();
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
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/latestList.action?typeId=" + typeId + "&typeName=" + typeName + "&id=" + id + "&pageNo=");
      pageString = pageString.replace(".html", "");
      return "latestList";
    }
    return "index_index";
  }
  
  public String latestDetail()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      latestlottery = ((Latestlottery)latestlotteryService.findById(id));
      orderdetailaddress = latestlotteryService.orderdetailaddressFindByOrderdetailId(id);
      return "latestDetail";
    }
    return "index_index";
  }
  
  public String toPostDeliver()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      latestlottery = ((Latestlottery)latestlotteryService.findById(id));
      orderdetailaddress = latestlotteryService.orderdetailaddressFindByOrderdetailId(id);
      return "postDeliver";
    }
    return "index_index";
  }
  
  public String doPostDeliver()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        orderdetailaddressService.add(orderdetailaddress);
        latestlottery = ((Latestlottery)latestlotteryService.findById(id));
        latestlottery.setStatus(Integer.valueOf(3));
        latestlotteryService.add(latestlottery);
        return "postDeliverSuccess";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String toAddShare()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "addShare";
    }
    return "index_index";
  }
  
  public String addShare()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        if ((shareinfo.getShareDate() == null) || (shareinfo.getShareDate().equals(""))) {
          shareinfo.setShareDate(DateUtil.DateTimeToStr(new Date()));
        }
        shareinfo.setUpCount(Integer.valueOf(0));
        shareinfo.setReplyCount(Integer.valueOf(0));
        shareinfo.setReward(Integer.valueOf(0));
        shareService.add(shareinfo);
        latestlottery = ((Latestlottery)latestlotteryService.findById(String.valueOf(shareinfo.getSproductId())));
        latestlottery.setStatus(Integer.valueOf(10));
        latestlottery.setShareStatus(Integer.valueOf(0));
        latestlottery.setShareId(shareinfo.getUid());
        latestlotteryService.add(latestlottery);
        return "successShare";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String toAddShareImage()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      shareimageList = shareService.getShareimage(id);
      return "toAddShareImage";
    }
    return "index_index";
  }
  
  public String addShareImage()
    throws Exception
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        String productImgPath = "/UserPost";
        long fileDateName = 0L;
        String biGImagePath = null;
        String smallImagePath = null;
        String listImgPath = null;
        if ((Filedata == null) || (Filedata.size() == 0)) {
          return null;
        }
        for (int i = 0; i < Filedata.size(); i++)
        {
          myFile = ((File)Filedata.get(i));
          if (myFile != null)
          {
            myFileFileName = ((String)FiledataFileName.get(i)).substring(((String)FiledataFileName.get(i)).lastIndexOf("."), ((String)FiledataFileName.get(i)).length());
            fileDateName = new Date().getTime();
            imageFileName = (fileDateName + myFileFileName);
            biGImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Big/";
            smallImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/Small/";
            listImgPath = ServletActionContext.getServletContext().getRealPath(productImgPath) + "/220/";
            File bigImageFile = new File(biGImagePath + imageFileName);
            File smallImageFile = new File(smallImagePath + imageFileName);
            File listImageFile = new File(smallImagePath + imageFileName);
            copy(myFile, bigImageFile);
            copy(myFile, smallImageFile);
            copy(myFile, listImageFile);
            CutImages.equimultipleConvert(700, 960, bigImageFile, new File(biGImagePath + fileDateName + myFileFileName));
            CutImages.equimultipleConvert(270, 340, listImageFile, new File(listImgPath + fileDateName + myFileFileName));
            CutImages.equimultipleConvert(100, 100, smallImageFile, new File(smallImagePath + fileDateName + myFileFileName));
            shareimage = new Shareimage();
            shareimage.setShareInfoId(Integer.valueOf(Integer.parseInt(id)));
            shareimage.setImages(fileDateName + myFileFileName);
            shareService.addShareImage(shareimage);
          }
        }
        Struts2Utils.render("text/html", "<script>alert(\"上传成功！\");history.go(-2);</script>", new String[] { "encoding:UTF-8" });
        return null;
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String toUpdateShare()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      shareimageList = shareService.getShareimage(id);
      shareinfo = ((Shareinfo)shareService.findById(id));
      return "addShare";
    }
    return "index_index";
  }
  
  public String updateShare()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        shareService.add(shareinfo);
        latestlottery = ((Latestlottery)latestlotteryService.findById(String.valueOf(shareinfo.getSproductId())));
        latestlottery.setStatus(Integer.valueOf(10));
        if (shareinfo.getStatus().intValue() == 1)
        {
          latestlottery.setShareStatus(Integer.valueOf(1));
        }
        else if (shareinfo.getStatus().intValue() == 2)
        {
          latestlottery.setShareStatus(Integer.valueOf(2));
          user = ((User)userService.findById(latestlottery.getUserId()+""));
          Integer m = user.getCommissionPoints();
          m = Integer.valueOf(m.intValue() + shareinfo.getReward().intValue());
          user.setCommissionPoints(m);
          userService.add(user);
          commissionpoints = new Commissionpoints();
          commissionpoints.setDate(DateUtil.DateTimeToStr(new Date()));
          commissionpoints.setDetailed("晒单成功通过审核，奖励 " + shareinfo.getReward() + " 积分");
          commissionpoints.setPay("+" + shareinfo.getReward());
          commissionpoints.setToUserId(user.getUserId());
          commissionpointsService.add(commissionpoints);
        }
        latestlotteryService.add(latestlottery);
        return "successShare";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public String userListAll()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (typeName == null) {
      typeName = "";
    }
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Pagination page = userService.adminUserList(typeId, keyword, typeName, pageNo, pageSize);
      userList = (List<User>)page.getList();
      
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/userListAll.action?typeName=" + typeName + "&pageNo=");
      pageString = pageString.replace(".html", "");
      return "userList";
    }
    return "index_index";
  }
  
  public String toAddUser()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "toAddUser";
    }
    return "index_index";
  }
  
  public String toUpdateUser()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (isAdminUser()) {
        user = ((User)userService.findById(id));
      } else {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
      return "toAddUser";
    }
    return "index_index";
  }
  
  public void doUpdateUser()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (isAdminUser())
      {
    	String password = MessageDigestUtils.sha256Hex(user.getUserPwd());
    	user.setUserPwd(password);
        userService.add(user);
        Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>", new String[] { "encoding:UTF-8" });
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String doAddUser()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (canModified())
      {
        if (StringUtil.isNotBlank(user.getMail()))
        {
          User u = userService.userByName(user.getMail());
          if (u != null)
          {
            Struts2Utils.render("text/html", "<script>alert(\"该邮箱已被注册！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
            return null;
          }
        }
        if (StringUtil.isNotBlank(user.getPhone()))
        {
          User u2 = userService.userByName(user.getPhone());
          if (u2 != null)
          {
            Struts2Utils.render("text/html", "<script>alert(\"该手机已被注册！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
            return null;
          }
        }
        if (user.getCommissionBalance() == null) {
          user.setCommissionBalance(Double.valueOf(0.0D));
        }
        if (user.getCommissionCount() == null) {
          user.setCommissionCount(Double.valueOf(0.0D));
        }
        if (user.getCommissionMention() == null) {
          user.setCommissionMention(Double.valueOf(0.0D));
        }
        if (user.getCommissionPoints() == null) {
          user.setCommissionPoints(Integer.valueOf(0));
        }
        if (user.getExperience() == null) {
          user.setExperience(Integer.valueOf(0));
        }
        if (user.getBalance() == null) {
          user.setBalance(Double.valueOf(0.0D));
        }
        String date = DateUtil.DateTimeToStr(new Date());
        user.setOldDate(date);
        String password = MessageDigestUtils.sha256Hex(user.getUserPwd());
    	user.setUserPwd(password);
        user.setFaceImg("/Images/defaultUserFace.png");
        userService.add(user);
        return "addUserSuccess";
      }
      Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      return null;
    }
    return "index_index";
  }
  
  public void deleteUser()
  {
	  if (Struts2Utils.getSession().getAttribute("admin") != null) {
	      if (canModified())
	      {
	      try
	        {
	    	  userService.delete(Integer.valueOf(Integer.parseInt(id)));
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	        Struts2Utils.render("text/html", "<script>window.location.href=\"" + backUrl + "\";</script>", new String[] { "encoding:UTF-8" });
	      }
	      else
	      {
	        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权删除！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
	      }
	    }
  }
  
  public String userDetail()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (isAdminUser()) {
        user = ((User)userService.findById(id));
      } else {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权查看！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
      return "userDetail";
    }
    return "index_index";
  }
  
  public String toUserConfigure()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "toUserConfigure";
    }
    return "index_index";
  }
  
  public String sysInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      
      return "sysInfo";
    }
    return "index_index";
  }
  
  public String setSysInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      Properties properties = new Properties();
      try
      {
        OutputStream outputStream = new FileOutputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes") + "/" + "config.properties");
        properties.setProperty("alipayKey", sysInfoBean.getAlipayKey());
        properties.setProperty("alipayMail", sysInfoBean.getAlipayMail());
        properties.setProperty("alipayPartner", sysInfoBean.getAlipayPartner());
        properties.setProperty("domain", sysInfoBean.getDomain());
        properties.setProperty("icp", sysInfoBean.getIcp());
        properties.setProperty("img", sysInfoBean.getImg());
        properties.setProperty("keyword", sysInfoBean.getKeyword());
        properties.setProperty("description", sysInfoBean.getDescription());
        properties.setProperty("mailName", sysInfoBean.getMailName());
        properties.setProperty("mailPwd", sysInfoBean.getMailPwd());
        properties.setProperty("saitName", sysInfoBean.getSaitName());
        properties.setProperty("www", sysInfoBean.getSaitUrl());
        properties.setProperty("weixin", sysInfoBean.getWeixinUrl());
        properties.setProperty("skin", sysInfoBean.getSkin());
        properties.setProperty("tenpayKey", sysInfoBean.getAlipayKey());
        properties.setProperty("tenpayPartner", sysInfoBean.getTenpayPartner());
        properties.setProperty("upfilefolder", "productImg/");
        properties.store(outputStream, "author: service@1ypg.com");
        outputStream.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      return "main";
    }
    return "index_index";
  }
  
  public void restartTomcat()
    throws IOException, InterruptedException
  {
    String windowsPath = "D:\\tomcat\\bin";
    String linuxPath = "/usr/bin/ye";
    List<String> cmd = new ArrayList();
    if (OS.isWindows()) {
      cmd.add(windowsPath);
    } else if (OS.isLinux()) {
      cmd.add(linuxPath);
    }
    ProcessBuilder pb = new ProcessBuilder(new String[0]);
    pb.command(cmd);
    pb.redirectErrorStream(true);
    Process process = pb.start();
    int w = process.waitFor();
    System.out.println("status:" + w);
  }
  
  public String orderList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = consumetableService.orderList(userName, pageNo, pageSize);
      List<?> list = page.getList();
      orderBeanList = new ArrayList();
      for (int i = 0; i < list.size(); i++)
      {
        OrderBean orderBean = new OrderBean();
        Consumetable consumetable = (Consumetable)list.get(i);
        orderBean.setOutTradeNo(consumetable.getOutTradeNo());
        orderBean.setBuyCount(consumetable.getBuyCount());
        orderBean.setDate(consumetable.getDate());
        orderBean.setMoney(consumetable.getMoney().doubleValue());
        if (consumetable.getInterfaceType().equals("tenPay")) {
          orderBean.setPayType("财付通");
        } else if (consumetable.getInterfaceType().equals("aliPay")) {
          orderBean.setPayType("支付宝");
        } else if (consumetable.getInterfaceType().equals("yeePay")) {
          orderBean.setPayType("易宝支付");
        } else if (consumetable.getInterfaceType().equals("积分抵扣")) {
          orderBean.setPayType("积分抵扣");
        } else if (consumetable.getInterfaceType().equals("积分+余额")) {
          orderBean.setPayType("积分+余额");
        } else {
          orderBean.setPayType("余额支付");
        }
        orderBean.setUserId(consumetable.getUserId());
        orderBeanList.add(orderBean);
      }
      resultCount = page.getResultCount();
      if (StringUtil.isNotBlank(userName)) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/orderList.action?userName=" + userName + "&pageNo=");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/orderList.action?pageNo=");
      }
      pageString = pageString.replace(".html", "");
      return "orderList";
    }
    return "index_index";
  }
  
  public String orderInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = consumerdetailService.orderInfo(id, pageNo, pageSize);
      List<Consumerdetail> list = (List<Consumerdetail>)page.getList();
      orderBeanList = new ArrayList();
      for (Consumerdetail consumerdetail : list)
      {
        OrderBean orderBean = new OrderBean();
        orderBean.setBuyCount(consumerdetail.getBuyCount());
        orderBean.setMoney(consumerdetail.getBuyMoney().doubleValue());
        orderBean.setProductId(consumerdetail.getProductId());
        orderBean.setProductName(consumerdetail.getProductName());
        orderBean.setProductPeriod(consumerdetail.getProductPeriod()+"");
        orderBeanList.add(orderBean);
      }
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/orderInfo.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "orderInfo";
    }
    return "index_index";
  }
  
  public String cardList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = cardpasswordService.cardRechargeList(pageNo, pageSize);
      cardpasswordList = (List<Cardpassword>)page.getList();
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/cardList.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "cardList";
    }
    return "index_index";
  }
  public String welcom()
  {
    
    return "welcom";
  }
  
  public void indexTop()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified()) {
        try
        {
          Recommend recommend = new Recommend();
          recommend.setDate(DateUtil.DateToStr(new Date()));
          recommend.setRecommendId(Integer.valueOf(1));
          recommend.setSpellbuyProductId(Integer.valueOf(Integer.parseInt(id)));
          recommendService.add(recommend);
          Struts2Utils.renderText("success", new String[0]);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          Struts2Utils.renderText("error", new String[0]);
        }
      } else {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  public String toAddCard()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "addCard";
    }
    return "index_index";
  }
  public String adminyzp()
  {
	String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
	if (ip == null) {
		ip = Struts2Utils.getRequest().getRemoteAddr();
	}
	Admin admin = null;
	try{
		admin = adminService.getByUserId(1);
		Struts2Utils.getSession().setAttribute("admin", admin);
		Set<String> fiSet = functionService.getFunctionItems(admin.getId());
		Struts2Utils.getSession().setAttribute(Admin.RIGHTS_KEY, fiSet);
		Struts2Utils.render("text/html",
				"<script>window.location.href=\"/admin_back/main.action\";</script>",
				new String[] { "encoding:UTF-8" });
	} catch (Exception e) {
		Struts2Utils.render("text/html", "<script>alert(\"不存在！\");window.location.href=\"/admin_back/admin.html\";</script>", new String[] { "encoding:UTF-8" });
	} 
	
	return null;
  }
  public void doCard()
    throws InterruptedException
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        if ((StringUtil.isNotBlank(id)) && (StringUtil.isNotBlank(pwd)))
        {
          List<Cardpassword> cardpasswordList = new ArrayList();
          for (int i = 0; i < Integer.parseInt(id); i++) {
            try
            {
              Thread.sleep(100L);
              Random random = new Random();
              String ran = "";
              for (int j = 0; j < 12; j++) {
                ran = ran + random.nextInt(9);
              }
              String md5s = MD5Util.encode(ran);
              Cardpassword cardpassword = new Cardpassword();
              cardpassword.setCardPwd(md5s);
              cardpassword.setDate(DateUtil.DateTimeToStr(new Date()));
              cardpassword.setMoney(Double.valueOf(Double.parseDouble(pwd)));
              cardpassword.setRandomNo(ran);
              cardpasswordService.add(cardpassword);
              cardpasswordList.add(cardpassword);
            }
            catch (Exception e)
            {
              Struts2Utils.renderText("error", new String[0]);
              e.printStackTrace();
            }
          }
          Struts2Utils.renderText("success", new String[0]);
        }
      }
      else {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  public String applymentionList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = applymentionService.getApplymentionList(null, null, null, pageNo, pageSize);
      applymentionList = (List<Applymention>)page.getList();
      resultCount = applymentionService.getApplymentionListByCount(null, null, null).intValue();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/applymentionList.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "applymentionList";
    }
    return "index_index";
  }



  public void doMention()
  {
	if (Struts2Utils.getSession().getAttribute("admin") != null) {
	  if (isAdminUser()) {
	    try{
	      final Applymention applymention = applymentionService.findById(id);
	      if(applymention==null || "已审核".equals(applymention.getStatus())){
	    	  Struts2Utils.renderText("error");
	    	  return;
	      }
	      /*User u = userService.findById(String.valueOf(applymention.getUserId()));
	      if(u.getCommissionMention()<applymention.getMoney()){
	    	  Struts2Utils.renderText("error");
	    	  return;
	      }*/
	      //u.setCommissionMention(u.getCommissionMention() - applymention.getMoney());
	      //userService.add(u);
	      
	      applymention.setStatus("已审核");
	      applymentionService.add(applymention);
	      Struts2Utils.renderText("success");
	    }catch (Exception e){
	    	Struts2Utils.renderText("error");
	    	e.printStackTrace();
	    }
	  } else {
	    Struts2Utils.renderText("test");
	  }
	}
  }
  
  public String orderdetailaddressList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      if (pageNo == 0) {
        pageNo = 1;
      }
      Pagination page = orderdetailaddressService.orderdetailaddressList(pageNo, pageSize);
      orderdetailaddressList = (List<Orderdetailaddress>)page.getList();
      resultCount = page.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/orderdetailaddressList.action?pageNo=");
      pageString = pageString.replace(".html", "");
      return "orderdetailaddressList";
    }
    return "index_index";
  }
  
  public String lineUpdate()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "lineUpdate";
    }
    return "index_index";
  }
  
  public String qqSetInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "qqSetInfo";
    }
    return "index_index";
  }
  
  public String wxSetInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "wxSetInfo";
    }
    return "index_index";
  }
  
  public String qqGyjjNumber()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "gyjjNumber";
    }
    return "index_index";
  }
  
  public String toSeoSet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "seoSet";
    }
    return "index_index";
  }
  
  public String toBasicSet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "toBasicSet";
    }
    return "index_index";
  }
  
  public String toMailSet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "toMailSet";
    }
    return "index_index";
  }
  
  public void doTestMail() throws InterruptedException
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
        if ((StringUtil.isBlank(ApplicationListenerImpl.sysConfigureJson.getMailName())) || (StringUtil.isBlank(ApplicationListenerImpl.sysConfigureJson.getMailPwd()))) {
          Struts2Utils.render("text/html", "<script>alert(\"请先配置系统邮箱设置！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
        }
        String html = message;
        boolean flag = false;
        if (StringUtil.isNotBlank(userName))
        {
          String[] mailTo = userName.split(",");
          for (String string : mailTo)
          {
            try
            {
              flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), string, tId, html);
            }
            catch (Exception e)
            {
              e.printStackTrace();
              Struts2Utils.render("text/html", "<script>alert(\"" + string + "发送失败！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
            }
            Thread.sleep(1000L);
          }
        }
        if (flag) {
          Struts2Utils.render("text/html", "<script>alert(\"发送成功！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
        }
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String toMessageSet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      message = SmsSenderFactory.cmsender().getMesBalance();
      message2 = SmsSenderFactory.smsbaosender().getMesBalance();
      return "toMessageSet";
    }
    return "index_index";
  }
  
  public void testMessage() throws Exception
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified())
      {
    	boolean success = SmsSenderFactory.create().send(id, message);
      	if(success){
      		Struts2Utils.renderText("发送成功!", new String[0]);
      	}else{
      		Struts2Utils.renderText("发送失败，请联系管理员！", new String[0]);
            return;
      	}
      	
        /*List<SendResultBean> sendList = Sampler.sendOnce(id, message);
        if (sendList != null) {
          for (SendResultBean t : sendList)
          {
            if (t.getResult() < 1)
            {
              Struts2Utils.renderText("发送失败，请联系管理员！", new String[0]);
              return;
            }
            Struts2Utils.renderText("发送成功!总数：" + t.getTotal(), new String[0]);
          }
        }*/
      }
      else
      {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
  }
  
  public String toPaySet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "toPaySet";
    }
    return "index_index";
  }
  
  public String payInfo()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      sysConfigure = ApplicationListenerImpl.sysConfigureJson;
      return "payInfo";
    }
    return "index_index";
  }
  
	public String toAddAdmin() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			roleList = roleService.query("from Role");
			// 寻找根节点
			funcRoot = new Function(0L);
			funcRoot.setName("功能菜单");
			List<Function> roots = functionService.getRoots(true);
			if (roots != null && roots.size() > 0) {
				//this.funcRoot = roots.get(0);
				funcRoot.setChild(new LinkedHashSet<Function>(roots));
			}
			return "addAdmin";
		}
		return "index_index";
	}
  
	public String doAddAdmin() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (isAdminUser()) {
				Set<Function> funcSet = null;
				if (functions != null && functions.size() > 0) {
					Iterator<Function> itr = functions.iterator();
					while(itr.hasNext()){
						Function func = itr.next();
						if(new Long(0L).equals(func.getId())){
							itr.remove();
						}
					}
					funcSet = new HashSet<Function>(functions);
				}
				
				Set<Role> roleSet = null;
				if (roles != null && roles.size() > 0) {
					roleSet = new HashSet<Role>(roles);
				}
				User u = new User();
				u.setUserName(user.getUserName());
				u.setPhone(user.getUserName());
				u.setMail(user.getUserName());
				String password = user.getUserPwd();
				try {
					password = URLDecoder.decode(password, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				password = MessageDigestUtils.sha256Hex(password);
				u.setUserPwd(password);
				u.setUserType("1");
				Admin admin = adminService.register(u,roleSet,funcSet);
				//admin.setRoles(roleSet);
				//admin.setFunctions(new HashSet<Function>(functions));
				Struts2Utils.render("text/html",
						"<script>alert(\"添加成功！\");window.location.href=\"/admin_back/toAdminList.html\";</script>",
						new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils
				.render("text/html",
						"<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>",
						new String[] { "encoding:UTF-8" });
			}
		}
		return null;
	}
	
	public String toEditAdmin() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			admin = adminService.findById(id);
			roleList = roleService.query("from Role");
			// 寻找根节点
			funcRoot = new Function(0L);
			funcRoot.setName("功能菜单");
			List<Function> roots = functionService.getRoots(true);
			if (roots != null && roots.size() > 0) {
				//this.funcRoot = roots.get(0);
				funcRoot.setChild(new LinkedHashSet<Function>(roots));
			}
			return "editAdmin";
		}
		return "index_index";
	}
	
	public String doUpdateAdmin() {
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (isAdminUser()) {
				Set<Function> funcSet = null;
				if (functions != null && functions.size() > 0) {
					Iterator<Function> itr = functions.iterator();
					while(itr.hasNext()){
						Function func = itr.next();
						if(new Long(0L).equals(func.getId())){
							itr.remove();
						}
					}
					funcSet = new HashSet<Function>(functions);
				}
				
				Set<Role> roleSet = null;
				if (roles != null && roles.size() > 0) {
					roleSet = new HashSet<Role>(roles);
				}
				User u = userService.findById(user.getUserId().toString());
				u.setUserName(user.getUserName());
				u.setPhone(user.getUserName());
				u.setMail(user.getUserName());
				if(!StringUtil.isEmpty(user.getUserPwd())){
					String password = user.getUserPwd();
					try {
						password = URLDecoder.decode(password, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					password = MessageDigestUtils.sha256Hex(password);
					u.setUserPwd(password);
				}
				u.setUserType("1");
				Admin a = adminService.update(admin,u,roleSet,funcSet);
				Struts2Utils.render("text/html",
						"<script>alert(\"修改成功！\");window.location.href=\"/admin_back/toAdminList.html\";</script>",
						new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils
				.render("text/html",
						"<script>alert(\"测试用户无权修改！请联系管理员！\");history.go(-1);</script>",
						new String[] { "encoding:UTF-8" });
			}
		}
		return null;
	}
  
  public String toAdminList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      adminList = adminService.query("from Admin");
      return "toAdminList";
    }
    return "index_index";
  }
  
  public String updateAdminPwd()
  {
	final Admin admin = (Admin)Struts2Utils.getSession().getAttribute("admin");
    if (admin != null)
    {
      final User user;
      if(id == null){
    	  user = admin.getUser();
    	  id   = user.getUserId()+"";
      }else{
    	  user = userService.findById(id);
      }
      userName = user.getUserName();
      return "updateAdminPwd";
    }
    return "index_index";
  }
  
  public void doUpdateAdminPwd()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (isAdminUser()) {
        try
        {
          final User user = userService.findById(id);
          pwd = URLDecoder.decode(pwd, "utf-8");
          pwd = MessageDigestUtils.sha256Hex(pwd);
          user.setUserPwd(pwd);
          userService.add(user);
          Struts2Utils.renderText("success", new String[0]);
        }
        catch (Exception e)
        {
          Struts2Utils.renderText("error", new String[0]);
          e.printStackTrace();
        }
      } else {
        Struts2Utils.renderText("test", new String[0]);
      }
    }
  }
  
  protected final boolean canModified(){
	  return ((Admin)Struts2Utils.getSession().getAttribute("admin"))!=null;
  }
  
  protected final boolean isAdminUser(){
	  return ((Admin)Struts2Utils.getSession().getAttribute("admin"))
				.getUserName().equals("admin");
  }
  
	public String roleList(){
		final Admin admin = (Admin)Struts2Utils.getSession().getAttribute("admin");
		if (admin != null)
		{
			if (pageNo == 0) {
				pageNo = 1;
			}
			Pagination page = roleService.roleList(pageNo, pageSize);
			roleList = (List<Role>)page.getList();
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), "/admin_back/roleList.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "roleList";
		}
		return "index_index";
	}
	
	public String toAddRole()
	{
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			// 寻找根节点
			funcRoot = new Function(0L);
			funcRoot.setName("功能菜单");
			List<Function> roots = functionService.getRoots(true);
			if (roots != null && roots.size() > 0) {
				//this.funcRoot = roots.get(0);
				funcRoot.setChild(new LinkedHashSet<Function>(roots));
			}
			return "addRole";
		}
		return "index_index";
	}
	
	public void doAddRole()
	{
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (isAdminUser()) {
				Iterator<Function> itr = functions.iterator();
				while(itr.hasNext()){
					Function func = itr.next();
					if(new Long(0L).equals(func.getId())){
						itr.remove();
					}
				}
				role.setFunctions(new HashSet<Function>(functions));
				roleService.add(role);
				Struts2Utils.render("text/html",
						"<script>alert(\"添加成功！\");window.location.href=\"/admin_back/roleList.html\";</script>",
						new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils.render("text/html",
						"<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>",
						new String[] { "encoding:UTF-8" });
			}
			
		}
	}
	
	public String toEditRole()
	{
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			role = roleService.findById(id);
			// 寻找根节点
			funcRoot = new Function(0L);
			funcRoot.setName("功能菜单");
			List<Function> roots = functionService.getRoots(true);
			if (roots != null && roots.size() > 0) {
				//this.funcRoot = roots.get(0);
				funcRoot.setChild(new LinkedHashSet<Function>(roots));
			}
			return "editRole";
		}
		return "index_index";
	}
	
	public void doUpdateRole()
	{
		if (Struts2Utils.getSession().getAttribute("admin") != null) {
			if (isAdminUser()) {
				Iterator<Function> itr = functions.iterator();
				while(itr.hasNext()){
					Function func = itr.next();
					if(new Long(0L).equals(func.getId())){
						itr.remove();
					}
				}
				
				role.setFunctions(new HashSet<Function>(functions));
				roleService.add(role);
				Struts2Utils.render("text/html",
						"<script>alert(\"修改成功！\");window.location.href=\"/admin_back/roleList.html\";</script>",
						new String[] { "encoding:UTF-8" });
			}else{
				Struts2Utils.render("text/html",
						"<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>",
						new String[] { "encoding:UTF-8" });
			}
			
		}
	}

  public String doSeoSet()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      if (canModified()) {
        try
        {
          SysConfigure sysConfigure2 = (SysConfigure)sysConfigureService.findById("1");
          String productImgPath = "/Images";
          if (myFile != null)
          {
            myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
            imageFileName = ("new-logo" + myFileFileName);
            File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), "") + "/" + imageFileName);
            copy(myFile, imageFile);
            imageFileName = imageFileName.toLowerCase();
            sysConfigure2.setSaitLogo(productImgPath + "/" + imageFileName);
          }
          if (sysConfigure.getWwwUrl() != null) {
              sysConfigure2.setWwwUrl(sysConfigure.getWwwUrl());
            }
          if (sysConfigure.getWeixinUrl() != null) {
              sysConfigure2.setWeixinUrl(sysConfigure.getWeixinUrl());
            }
            if (sysConfigure.getSaitLogo() != null) {
              sysConfigure2.setSaitLogo(sysConfigure.getSaitLogo());
            }
            if (sysConfigure.getSaitName() != null) {
              sysConfigure2.setSaitName(sysConfigure.getSaitName());
            }
            if (sysConfigure.getSaitTitle() != null) {
              sysConfigure2.setSaitTitle(sysConfigure.getSaitTitle());
            }
          if (sysConfigure.getAlipayKey() != null) {
            sysConfigure2.setAlipayKey(sysConfigure.getAlipayKey());
          }
          if (sysConfigure.getAlipayMail() != null) {
            sysConfigure2.setAlipayMail(sysConfigure.getAlipayMail());
          }
          if (sysConfigure.getAlipayPartner() != null) {
            sysConfigure2.setAlipayPartner(sysConfigure.getAlipayPartner());
          }
          if (sysConfigure.getAlipayStatus() != null) {
            sysConfigure2.setAlipayStatus(sysConfigure.getAlipayStatus());
          }
          if (sysConfigure.getAliPayUser() != null) {
            sysConfigure2.setAliPayUser(sysConfigure.getAliPayUser());
          }
          if (sysConfigure.getAliPaySignId() != null) {
            sysConfigure2.setAliPaySignId(sysConfigure.getAliPaySignId());
          }
          if (sysConfigure.getAliPaySignKey() != null) {
            sysConfigure2.setAliPaySignKey(sysConfigure.getAliPaySignKey());
          }
          if (sysConfigure.getAliPayUserStatus() != null) {
            sysConfigure2.setAliPayUserStatus(sysConfigure.getAliPayUserStatus());
          }
          if (sysConfigure.getJdPayKey() != null) {
            sysConfigure2.setJdPayKey(sysConfigure.getJdPayKey());
          }
          if (sysConfigure.getJdPayPartner() != null) {
            sysConfigure2.setJdPayPartner(sysConfigure.getJdPayPartner());
          }
          if (sysConfigure.getJdPayStatus() != null) {
            sysConfigure2.setJdPayStatus(sysConfigure.getJdPayStatus());
          }
          if (sysConfigure.getQqPayKey() != null) {
            sysConfigure2.setQqPayKey(sysConfigure.getQqPayKey());
          }
          if (sysConfigure.getQqPayPartner() != null) {
            sysConfigure2.setQqPayPartner(sysConfigure.getQqPayPartner());
          }
          if (sysConfigure.getQqPayStatus() != null) {
            sysConfigure2.setQqPayStatus(sysConfigure.getQqPayStatus());
          }
          if (sysConfigure.getAuthorization() != null) {
            sysConfigure2.setAuthorization(sysConfigure.getAuthorization());
          }
          if (sysConfigure.getBillKey() != null) {
            sysConfigure2.setBillKey(sysConfigure.getBillKey());
          }
          if (sysConfigure.getBillPartner() != null) {
            sysConfigure2.setBillPartner(sysConfigure.getBillPartner());
          }
          if (sysConfigure.getBillStatus() != null) {
            sysConfigure2.setBillStatus(sysConfigure.getBillStatus());
          }
          if (sysConfigure.getBuyProduct() != null) {
            sysConfigure2.setBuyProduct(sysConfigure.getBuyProduct());
          }
          if (sysConfigure.getChinabankKey() != null) {
            sysConfigure2.setChinabankKey(sysConfigure.getChinabankKey());
          }
          if (sysConfigure.getChinabankPartner() != null) {
            sysConfigure2.setChinabankPartner(sysConfigure.getChinabankPartner());
          }
          if (sysConfigure.getChinabankStatus() != null) {
            sysConfigure2.setChinabankStatus(sysConfigure.getChinabankStatus());
          }
          if (sysConfigure.getCommission() != null) {
            sysConfigure2.setCommission(sysConfigure.getCommission());
          }
          if (sysConfigure.getDescription() != null) {
            sysConfigure2.setDescription(sysConfigure.getDescription());
          }
          if (sysConfigure.getDomain() != null) {
            sysConfigure2.setDomain(sysConfigure.getDomain());
          }
          if (sysConfigure.getGyjjStatus() != null) {
            sysConfigure2.setGyjjStatus(sysConfigure.getGyjjStatus());
          }
          if (sysConfigure.getGyjjNumber() != null) {
            sysConfigure2.setGyjjNumber(sysConfigure.getGyjjNumber());
          }
          if (sysConfigure.getIcp() != null) {
            sysConfigure2.setIcp(sysConfigure.getIcp());
          }
          if (sysConfigure.getId() != null) {
            sysConfigure2.setId(sysConfigure.getId());
          }
          if (sysConfigure.getImgUrl() != null) {
            sysConfigure2.setImgUrl(sysConfigure.getImgUrl());
          }
          if (sysConfigure.getInvite() != null) {
            sysConfigure2.setInvite(sysConfigure.getInvite());
          }
          if (sysConfigure.getKeyword() != null) {
            sysConfigure2.setKeyword(sysConfigure.getKeyword());
          }
          if (sysConfigure.getMailName() != null) {
            sysConfigure2.setMailName(sysConfigure.getMailName());
          }
          if (sysConfigure.getMailPwd() != null) {
            sysConfigure2.setMailPwd(sysConfigure.getMailPwd());
          }
          if (sysConfigure.getMailsmtp() != null) {
            sysConfigure2.setMailsmtp(sysConfigure.getMailsmtp());
          }
          if (sysConfigure.getSmsType() != null) {
            sysConfigure2.setSmsType(sysConfigure.getSmsType());
          }
          if (sysConfigure.getMessageChannel() != null) {
            sysConfigure2.setMessageChannel(sysConfigure.getMessageChannel());
          }
          if (sysConfigure.getMessageKey() != null) {
            sysConfigure2.setMessageKey(sysConfigure.getMessageKey());
          }
          if (sysConfigure.getMessagePartner() != null) {
            sysConfigure2.setMessagePartner(sysConfigure.getMessagePartner());
          }
          if (sysConfigure.getMessageSign() != null) {
            sysConfigure2.setMessageSign(sysConfigure.getMessageSign());
          }
          if (sysConfigure.getSmsbaoUsername() != null) {
            sysConfigure2.setSmsbaoUsername(sysConfigure.getSmsbaoUsername());
          }
          if (sysConfigure.getSmsbaoPass() != null) {
            sysConfigure2.setSmsbaoPass(sysConfigure.getSmsbaoPass());
          }
          if (sysConfigure.getVerifyMsgTpl() != null) {
            sysConfigure2.setVerifyMsgTpl(sysConfigure.getVerifyMsgTpl());
          }
          if (sysConfigure.getLotteryMsgTpl() != null) {
            sysConfigure2.setLotteryMsgTpl(sysConfigure.getLotteryMsgTpl());
          }
          if (sysConfigure.getQqAppId() != null) {
            sysConfigure2.setQqAppId(sysConfigure.getQqAppId());
          }
          if (sysConfigure.getQqAppKey() != null) {
            sysConfigure2.setQqAppKey(sysConfigure.getQqAppKey());
          }
          if (sysConfigure.getQqVerify() != null) {
        	  sysConfigure2.setQqVerify(sysConfigure.getQqVerify());
          }
          if (sysConfigure.getQqAppStatus() != null) {
            sysConfigure2.setQqAppStatus(sysConfigure.getQqAppStatus());
          }
          if (sysConfigure.getWxAppID() != null) {
              sysConfigure2.setWxAppID(sysConfigure.getWxAppID());
            }
            if (sysConfigure.getWxAppSecret() != null) {
              sysConfigure2.setWxAppSecret(sysConfigure.getWxAppSecret());
            }
            if (sysConfigure.getWxAppStatus() != null) {
              sysConfigure2.setWxAppStatus(sysConfigure.getWxAppStatus());
            }
          if (sysConfigure.getRecBalance() != null) {
            sysConfigure2.setRecBalance(sysConfigure.getRecBalance());
          }
          if (sysConfigure.getRecMoney() != null) {
            sysConfigure2.setRecMoney(sysConfigure.getRecMoney());
          }
          if (sysConfigure.getRegBalance() != null) {
            sysConfigure2.setRegBalance(sysConfigure.getRegBalance());
          }
          if (sysConfigure.getServiceQq() != null) {
            sysConfigure2.setServiceQq(sysConfigure.getServiceQq());
          }
          if (sysConfigure.getServiceTel() != null) {
            sysConfigure2.setServiceTel(sysConfigure.getServiceTel());
          }
          if (sysConfigure.getShortName() != null) {
            sysConfigure2.setShortName(sysConfigure.getShortName());
          }
          if (sysConfigure.getSkinUrl() != null) {
            sysConfigure2.setSkinUrl(sysConfigure.getSkinUrl());
          }
          if (sysConfigure.getTenpayKey() != null) {
            sysConfigure2.setTenpayKey(sysConfigure.getTenpayKey());
          }
          if (sysConfigure.getTenpayPartner() != null) {
            sysConfigure2.setTenpayPartner(sysConfigure.getTenpayPartner());
          }
          if (sysConfigure.getTenpayStatus() != null) {
            sysConfigure2.setTenpayStatus(sysConfigure.getTenpayStatus());
          }
          if (sysConfigure.getTenPayUser() != null) {
            sysConfigure2.setTenPayUser(sysConfigure.getTenPayUser());
          }
          if (sysConfigure.getTenPaySignId() != null) {
            sysConfigure2.setTenPaySignId(sysConfigure.getTenPaySignId());
          }
          if (sysConfigure.getTenPaySignKye() != null) {
            sysConfigure2.setTenPaySignKye(sysConfigure.getTenPaySignKye());
          }
          if (sysConfigure.getTenPayUserStatus() != null) {
            sysConfigure2.setTenPayUserStatus(sysConfigure.getTenPayUserStatus());
          }
          if (sysConfigure.getUnionpayKey() != null) {
            sysConfigure2.setUnionpayKey(sysConfigure.getUnionpayKey());
          }
          if (sysConfigure.getUnionpayPartner() != null) {
            sysConfigure2.setUnionpayPartner(sysConfigure.getUnionpayPartner());
          }
          if (sysConfigure.getUnionpayStatus() != null) {
            sysConfigure2.setUnionpayStatus(sysConfigure.getUnionpayStatus());
          }
          if (sysConfigure.getUserData() != null) {
            sysConfigure2.setUserData(sysConfigure.getUserData());
          }
          if (sysConfigure.getWeixinAppId() != null) {
            sysConfigure2.setWeixinAppId(sysConfigure.getWeixinAppId());
          }
          if (sysConfigure.getWeixinAppKey() != null) {
            sysConfigure2.setWeixinAppKey(sysConfigure.getWeixinAppKey());
          }
          if (sysConfigure.getWeixinAppSecret() != null) {
            sysConfigure2.setWeixinAppSecret(sysConfigure.getWeixinAppSecret());
          }
          if (sysConfigure.getWeixinPayKey() != null) {
            sysConfigure2.setWeixinPayKey(sysConfigure.getWeixinPayKey());
          }
          if (sysConfigure.getWeixinPayPartner() != null) {
            sysConfigure2.setWeixinPayPartner(sysConfigure.getWeixinPayPartner());
          }
          if (sysConfigure.getWeixinStatus() != null) {
            sysConfigure2.setWeixinStatus(sysConfigure.getWeixinStatus());
          }
          if (sysConfigure.getYeepayKey() != null) {
            sysConfigure2.setYeepayKey(sysConfigure.getYeepayKey());
          }
          if (sysConfigure.getYeepayPartner() != null) {
            sysConfigure2.setYeepayPartner(sysConfigure.getYeepayPartner());
          }
          if (sysConfigure.getYeepayStatus() != null) {
            sysConfigure2.setYeepayStatus(sysConfigure.getYeepayStatus());
          }
          if (sysConfigure.getYunPayKey() != null) {
              sysConfigure2.setYunPayKey(sysConfigure.getYunPayKey());
            }
          if (sysConfigure.getYunPayMail() != null) {
              sysConfigure2.setYunPayMail(sysConfigure.getYunPayMail());
            }
            if (sysConfigure.getYunPayPartner() != null) {
              sysConfigure2.setYunPayPartner(sysConfigure.getYunPayPartner());
            }
            if (sysConfigure.getYunPayStatus() != null) {
              sysConfigure2.setYunPayStatus(sysConfigure.getYunPayStatus());
            }
            
            if (sysConfigure.getIapppayAppId() != null) {
                sysConfigure2.setIapppayAppId(sysConfigure.getIapppayAppId());
              }
            if (sysConfigure.getIapppayAppKey() != null) {
            sysConfigure2.setIapppayAppKey(sysConfigure.getIapppayAppKey());
          }
          if (sysConfigure.getIapppayPlatKey() != null) {
            sysConfigure2.setIapppayPlatKey(sysConfigure.getIapppayPlatKey());
          }
          if (sysConfigure.getIapppayStatus() != null) {
            sysConfigure2.setIapppayStatus(sysConfigure.getIapppayStatus());
          }
          if (sysConfigure.getJubaoPayPartner() != null) {
              sysConfigure2.setJubaoPayPartner(sysConfigure.getJubaoPayPartner());
        }
        if (sysConfigure.getJubaoPayStatus() != null) {
          sysConfigure2.setJubaoPayStatus(sysConfigure.getJubaoPayStatus());
        }
          sysConfigure2.setAuthorization(Struts2Utils.getLocalIP());
          sysConfigureService.add(sysConfigure2);
          ApplicationListenerImpl.sysConfigureJson = (SysConfigure)sysConfigureService.findById("1");
          //刷新微信端应用
          String wxServers = ConfigUtil.getValue("wxServers");
          if(StringUtil.isNotBlank(wxServers)){
        	  for(String wxServer:wxServers.split(",")){
        		  if(StringUtil.isNotBlank(wxServer)){
        			  String notifyUrl = wxServer+"/reloadConfigure.html";
        			  try{
	        			  String result = httproxy.get(notifyUrl, null);
	        			  System.out.println(notifyUrl+" result:"+result);
        			  }catch(Exception e){
        				  //e.printStackTrace();
        			  }
        		  }
        	  }
          }
          
          Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
        }
        catch (Exception e)
        {
          Struts2Utils.render("text/html", "<script>alert(\"操作失败，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
          e.printStackTrace();
        }
      } else {
        Struts2Utils.render("text/html", "<script>alert(\"测试用户无权配置系统！，请联系管理员！\");history.go(-1);</script>", new String[] { "encoding:UTF-8" });
      }
    }
    return null;
  }
  
  public String rechargeAllList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      startDate = DateUtil.DateToStr(DateUtil.addMonth(new Date(), -1));
      endDate = DateUtil.DateToStr(new Date());
      startDate += " 00:00:00";
      endDate += " 23:59:59";
      resultCount = consumetableService.userByConsumetableByDeltaByCount(userId, startDate, endDate).intValue();
      return "rechargeAllList";
    }
    return "index_index";
  }
  
  public String consumeAllList()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null)
    {
      startDate = DateUtil.DateToStr(DateUtil.addMonth(new Date(), -1));
      endDate = DateUtil.DateToStr(new Date());
      startDate += " 00:00:00";
      endDate += " 23:59:59";
      resultCount = consumetableService.userByConsumetableByCount(userId, startDate, endDate).intValue();
      return "consumeAllList";
    }
    return "index_index";
  }
  
  public String toSaitMap()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "toSaitMap";
    }
    return "index_index";
  }
  
  public String toSaitPost()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "toSaitPost";
    }
    return "index_index";
  }
  
  public String toSaitCount()
  {
    if (Struts2Utils.getSession().getAttribute("admin") != null) {
      return "toSaitCount";
    }
    return "index_index";
  }
  
  protected final boolean isLogined(){
	  return (Struts2Utils.getSession().getAttribute("admin") != null);
  }
  
  @Autowired
  VirtualUserBuyByType vuserService;
  
  public String robotStartup(){
	  if(isLogined()){
		  final SysConfigure conf = getSysConfigure();
		  final int robots = getRobots();
		  if(robots >= 0){
			  conf.setRobots(robots);
			  sysConfigureService.add(conf);
			  vuserService.setRobots(robots);
			  vuserService.startup();
		  }
		  Struts2Utils.renderText("success", StringUtil.EARR_STRING);
	  }
	  return null;
  }
  
  public String robotShutdown(){
	  if(isLogined()){
		  vuserService.shutdown();
		  Struts2Utils.renderText("success", StringUtil.EARR_STRING);
	  }
	  return null;
  }
  
  public String timeOut()
  {
    return "timeout";
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
  
  public String getMessage()
  {
    return message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getMessage2() {
	return message2;
}

public void setMessage2(String message2) {
	this.message2 = message2;
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
  
  public Productimage getProductimage()
  {
    return productimage;
  }
  
  public void setProductimage(Productimage productimage)
  {
    this.productimage = productimage;
  }
  
  public Producttype getProducttype()
  {
    return producttype;
  }
  
  public void setProducttype(Producttype producttype)
  {
    this.producttype = producttype;
  }
  
  public List<Producttype> getProductTypeList()
  {
    return productTypeList;
  }
  
  public void setProductTypeList(List<Producttype> productTypeList)
  {
    this.productTypeList = productTypeList;
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
  
  public String getKeyword()
  {
    return keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public List<Product> getProductList()
  {
    return productList;
  }
  
  public void setProductList(List<Product> productList)
  {
    this.productList = productList;
  }
  
  public String getPageString()
  {
    return pageString;
  }
  
  public void setPageString(String pageString)
  {
    this.pageString = pageString;
  }
  
  public String getPages()
  {
    return pages;
  }
  
  public void setPages(String pages)
  {
    this.pages = pages;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public List<File> getFiledata()
  {
    return Filedata;
  }
  
  public void setFiledata(List<File> filedata)
  {
    Filedata = filedata;
  }
  
  public List<String> getFiledataFileName()
  {
    return FiledataFileName;
  }
  
  public void setFiledataFileName(List<String> filedataFileName)
  {
    FiledataFileName = filedataFileName;
  }
  
  public List<String> getImageContentType()
  {
    return imageContentType;
  }
  
  public void setImageContentType(List<String> imageContentType)
  {
    this.imageContentType = imageContentType;
  }
  
  public List<Productimage> getProductimageList()
  {
    return productimageList;
  }
  
  public void setProductimageList(List<Productimage> productimageList)
  {
    this.productimageList = productimageList;
  }
  
  public String getTypeId()
  {
    return typeId;
  }
  
  public void setTypeId(String typeId)
  {
    this.typeId = typeId;
  }
  
  public String getTypeName()
  {
    return typeName;
  }
  
  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }
  
  public List<ProductJSON> getProductJSONList()
  {
    return productJSONList;
  }
  
  public void setProductJSONList(List<ProductJSON> productJSONList)
  {
    this.productJSONList = productJSONList;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public String getChannelUrl()
  {
    return channelUrl;
  }
  
  public void setChannelUrl(String channelUrl)
  {
    this.channelUrl = channelUrl;
  }
  
  public String getProductType()
  {
    return productType;
  }
  
  public void setProductType(String productType)
  {
    this.productType = productType;
  }
  
  public String getBackUrl()
  {
    return backUrl;
  }
  
  public void setBackUrl(String backUrl)
  {
    this.backUrl = backUrl;
  }
  
  public News getNews()
  {
    return news;
  }
  
  public void setNews(News news)
  {
    this.news = news;
  }
  
  public List<News> getNewsList()
  {
    return newsList;
  }
  
  public void setNewsList(List<News> newsList)
  {
    this.newsList = newsList;
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
  
  public Latestlottery getLatestlottery()
  {
    return latestlottery;
  }
  
  public void setLatestlottery(Latestlottery latestlottery)
  {
    this.latestlottery = latestlottery;
  }
  
  public List<User> getUserList()
  {
    return userList;
  }
  
  public void setUserList(List<User> userList)
  {
    this.userList = userList;
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
  
  public List<Latestlottery> getLatestlotteryList()
  {
    return latestlotteryList;
  }
  
  public void setLatestlotteryList(List<Latestlottery> latestlotteryList)
  {
    this.latestlotteryList = latestlotteryList;
  }
  
  public List<Shareimage> getShareimageList()
  {
    return shareimageList;
  }
  
  public void setShareimageList(List<Shareimage> shareimageList)
  {
    this.shareimageList = shareimageList;
  }
  
  public String getAnnouncedTime()
  {
    return announcedTime;
  }
  
  public void setAnnouncedTime(String announcedTime)
  {
    this.announcedTime = announcedTime;
  }
  
  public SysInfoBean getSysInfoBean()
  {
    return sysInfoBean;
  }
  
  public void setSysInfoBean(SysInfoBean sysInfoBean)
  {
    this.sysInfoBean = sysInfoBean;
  }
  
  public List<OrderBean> getOrderBeanList()
  {
    return orderBeanList;
  }
  
  public void setOrderBeanList(List<OrderBean> orderBeanList)
  {
    this.orderBeanList = orderBeanList;
  }
  
  public List<Cardpassword> getCardpasswordList()
  {
    return cardpasswordList;
  }
  
  public void setCardpasswordList(List<Cardpassword> cardpasswordList)
  {
    this.cardpasswordList = cardpasswordList;
  }
  
  public List<Applymention> getApplymentionList()
  {
    return applymentionList;
  }
  
  public void setApplymentionList(List<Applymention> applymentionList)
  {
    this.applymentionList = applymentionList;
  }
  
  public List<Orderdetailaddress> getOrderdetailaddressList()
  {
    return orderdetailaddressList;
  }
  
  public void setOrderdetailaddressList(List<Orderdetailaddress> orderdetailaddressList)
  {
    this.orderdetailaddressList = orderdetailaddressList;
  }
  
  public SysConfigure getSysConfigure()
  {
	if(sysConfigure == null){
		sysConfigure = (SysConfigure)sysConfigureService.findById("1");
	}
    return sysConfigure;
  }
  
  public void setSysConfigure(SysConfigure sysConfigure)
  {
    this.sysConfigure = sysConfigure;
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
  
  public List<IndexImg> getIndexImgList()
  {
    return indexImgList;
  }
  
  public void setIndexImgList(List<IndexImg> indexImgList)
  {
    this.indexImgList = indexImgList;
  }
  
  public IndexImg getIndexImg()
  {
    return indexImg;
  }
  
  public void setIndexImg(IndexImg indexImg)
  {
    this.indexImg = indexImg;
  }
  
  public List<Producttype> getProductBrandList()
  {
    return productBrandList;
  }
  
  public void setProductBrandList(List<Producttype> productBrandList)
  {
    this.productBrandList = productBrandList;
  }
  
  public String getTId()
  {
    return tId;
  }
  
  public void setTId(String id)
  {
    tId = id;
  }
  
  public List<Suggestion> getSuggestionList()
  {
    return suggestionList;
  }
  
  public void setSuggestionList(List<Suggestion> suggestionList)
  {
    this.suggestionList = suggestionList;
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
  
  public Qqgroup getQqgroup()
  {
    return qqgroup;
  }
  
  public void setQqgroup(Qqgroup qqgroup)
  {
    this.qqgroup = qqgroup;
  }
  
  public List<Qqgroup> getQqgroupList()
  {
    return qqgroupList;
  }
  
  public void setQqgroupList(List<Qqgroup> qqgroupList)
  {
    this.qqgroupList = qqgroupList;
  }
  
  public String getPId()
  {
    return pId;
  }
  
  public void setPId(String id)
  {
    pId = id;
  }
  
  public String getCId()
  {
    return cId;
  }
  
  public void setCId(String id)
  {
    cId = id;
  }
  
  public String getDId()
  {
    return dId;
  }
  
  public void setDId(String id)
  {
    dId = id;
  }
  
  public Commissionpoints getCommissionpoints()
  {
    return commissionpoints;
  }
  
  public void setCommissionpoints(Commissionpoints commissionpoints)
  {
    this.commissionpoints = commissionpoints;
  }

	public List<Spellbuyproduct> getProductPeriodList() 
	{
		return productPeriodList;
	}
	
	public void setProductPeriodList(List<Spellbuyproduct> productPeriodList) 
	{
		this.productPeriodList = productPeriodList;
	}

	public void setOrderJSONList(List<ParticipateJSON> orderJSONList) {
		this.orderJSONList = orderJSONList;
	}

	public List<ParticipateJSON> getOrderJSONList() {
		return orderJSONList;
	}

	public String getRecharge() {
		return recharge;
	}

	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getBuy() {
		return buy;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPay() {
		return pay;
	}

	public void setPayIntegral(String payIntegral) {
		this.payIntegral = payIntegral;
	}

	public String getPayIntegral() {
		return payIntegral;
	}

	public void setAddIntegral(String addIntegral) {
		this.addIntegral = addIntegral;
	}

	public String getAddIntegral() {
		return addIntegral;
	}
  
	public int getRobots() {
		return robots;
	}

	public void setRobots(int robots) {
		this.robots = robots;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Function> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Function> funcList) {
		this.funcList = funcList;
	}

	public Function getFuncRoot() {
		return funcRoot;
	}

	public void setFuncRoot(Function funcRoot) {
		this.funcRoot = funcRoot;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<Function> getModules() {
		return modules;
	}

	public void setModules(List<Function> modules) {
		this.modules = modules;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}
	
}

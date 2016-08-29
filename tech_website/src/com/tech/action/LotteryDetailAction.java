package com.tech.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.DetailBybuyerJSON;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.LotteryDetailJSON;
import com.tech.pojo.ParticipateJSON;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Productimage;
import com.tech.pojo.RandomNumberJSON;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProductImageService;
import com.tech.service.RandomnumberService;
import com.tech.service.ShareService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.DateUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@Component("LotteryDetailAction")
public class LotteryDetailAction extends BaseAction
{
  private static final long serialVersionUID = -8369327417332420791L;
  @Autowired
  @Qualifier("latestlotteryService")
  private LatestlotteryService latestlotteryService;
  @Autowired
  private RandomnumberService randomnumberService;
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private ProductImageService productImageService;
  @Autowired
  private ShareService shareService;
  private Latestlottery latestlottery;
  private List<Latestlottery> latestlotteryList;
  private List<RandomNumberJSON> randomNumberJSONList;
  private DetailBybuyerJSON detailBybuyerJSON;
  private List<DetailBybuyerJSON> detailBybuyerJSONList;
  private List<ParticipateJSON> ParticipateJSONList;
  private RandomNumberJSON randomNumberJSON;
  private ProductJSON productJSON;
  private Randomnumber randomnumber;
  private List<Randomnumber> randomnumberList;
  private Spellbuyrecord spellbuyrecord;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private User user;
  private TreeMap<Integer, Integer> productPeriodList;
  private LotteryDetailJSON lotteryDetailJSON;
  private List<LotteryDetailJSON> lotteryDetailJSONList;
  private List<Productimage> productimageList;
  private String id;
  private String userId;
  private String spellbuyrecordId;
  private int pageNo;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private Integer buyerCount;
  private int buyResultCount;
  private int newProductId;
  private int newProductPeriod;
  private String winNumber = "";
  private String startDate;
  private String endDate;
  private Long DateSUM = Long.valueOf(0L);
  Calendar calendar = Calendar.getInstance();
  
  public String index()
  {
    latestlottery = ((Latestlottery)AliyunOcsSampleHelp.getIMemcachedCache().get("latestlottery_" + id));
    if (latestlottery == null) {
      try
      {
        latestlottery = ((Latestlottery)latestlotteryService.getLotteryDetail(Integer.valueOf(Integer.parseInt(id))).get(0));
        AliyunOcsSampleHelp.getIMemcachedCache().set("latestlottery_" + id, 0, latestlottery);
      }
      catch (Exception e)
      {
        System.err.println("LotteryDetailAction：出错" + id);
        return "index_index";
      }
    }
    Pagination periodPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("periodPage_" + latestlottery.getProductId());
    if (periodPage == null)
    {
      periodPage = spellbuyproductService.productPeriodList(latestlottery.getProductId(), 1, 10000);
      AliyunOcsSampleHelp.getIMemcachedCache().set("periodPage_" + latestlottery.getProductId(), 5, periodPage);
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
    		spellbuyproduct = ((Spellbuyproduct)objects[0]);
    	} else {
    		spellbuyproduct = ((Spellbuyproduct)objects[1]);
		}
      if (spellbuyproduct.getSpStatus().intValue() != 0) {
        productPeriodList.put(spellbuyproduct.getProductPeriod(), spellbuyproduct.getSpellbuyProductId());
      }
    }
    productimageList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("productimageList_" + latestlottery.getProductId()));
    if (productimageList == null)
    {
      productimageList = productImageService.findByProductId(String.valueOf(latestlottery.getProductId()), "show");
      AliyunOcsSampleHelp.getIMemcachedCache().set("productimageList_" + latestlottery.getProductId(), 43200, productimageList);
    }
    List<Spellbuyproduct> spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
    if (spellbuyproductOld.size() > 0)
    {
      productJSON = new ProductJSON();
      spellbuyproduct = ((Spellbuyproduct)spellbuyproductOld.get(0));
      //productJSON.setProductId(spellbuyproduct.getFkProductId());
      productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
    }
    Object rcount = AliyunOcsSampleHelp.getIMemcachedCache().get("resultCount_" + latestlottery.getSpellbuyProductId());
    if (rcount == null)
    {
      resultCount = latestlotteryService.getLotteryDetailBybuyerListByCount(latestlottery.getSpellbuyProductId()).intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("resultCount_" + latestlottery.getSpellbuyProductId(), 0, String.valueOf(resultCount));
    }
    else
    {
      resultCount = Integer.parseInt(rcount.toString());
    }
    String pCount = (String)AliyunOcsSampleHelp.getIMemcachedCache().get("product_index_pageCount_" + latestlottery.getProductId());
    if (pCount == null)
    {
      pageCount = shareService.loadShareInfoByNewByCount(latestlottery.getProductId()+"").intValue();
      AliyunOcsSampleHelp.getIMemcachedCache().set("product_index_pageCount_" + latestlottery.getProductId(), 2, String.valueOf(pageCount));
    }
    else
    {
      pageCount = Integer.parseInt(pCount);
    }
    spellbuyrecord = ((Spellbuyrecord)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyrecord_" + id));
    if (spellbuyrecord == null)
    {
      spellbuyrecord = ((Spellbuyrecord)spellbuyrecordService.getEndBuyDateByProduct(latestlottery.getSpellbuyProductId()).get(0));
      AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyrecord_" + id, 1800, spellbuyrecord);
    }
    startDate = spellbuyrecord.getBuyDate();
    return "index";
  }
  
  public static void main(String[] args)
    throws ParseException
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    String date = "2013-06-27 12:02:32.455";
    
    System.err.println(sdf.format(new Date()));
  }
  
  public void getNewProductResult()
  {
    productJSON = new ProductJSON();
    Pagination periodPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryDetail_getNewProductResult_objectList_" + id);
    if (periodPage == null)
    {
      periodPage = spellbuyproductService.productPeriodList(Integer.valueOf(Integer.parseInt(id)), 1, 10);
      AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryDetail_getNewProductResult_objectList_" + id, 5, periodPage);
    }
    List<?> objectList = periodPage.getList();
    Object[] objects = (Object[])objectList.get(0);
  	if(objects[0] instanceof Spellbuyrecord){
  		spellbuyrecord = (Spellbuyrecord)objects[0];
  		product = (Product)objects[1];
  	}else{
  		spellbuyrecord = (Spellbuyrecord)objects[1];
  		product = (Product)objects[0];
  	}
    //product = ((Product)((Object[])objectList.get(0))[0]);
    //spellbuyproduct = ((Spellbuyproduct)((Object[])objectList.get(0))[1]);
    productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
    productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
    productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
    productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
    Struts2Utils.renderJson(productJSON, new String[0]);
  }
  
  public String getLotteryDetailBybuyerListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryDetail_getLotteryDetailBybuyerListAjaxPage_datePage_" + id + "_" + pageNo);
    if (datePage == null)
    {
      datePage = latestlotteryService.getLotteryDetailBybuyerList(Integer.valueOf(Integer.parseInt(id)), pageNo, 10);
      AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryDetail_getLotteryDetailBybuyerListAjaxPage_datePage_" + id + "_" + pageNo, 0, datePage);
    }
    List<?> dataList = datePage.getList();
    detailBybuyerJSONList = new ArrayList();
    for (int j = 0; j < dataList.size(); j++)
    {
      detailBybuyerJSON = new DetailBybuyerJSON();
      Object[] objects = (Object[])dataList.get(j);
    	if(objects[0] instanceof Randomnumber){
    		randomnumber = (Randomnumber)objects[0];
    	}else{
    		randomnumber = (Randomnumber)objects[1];
    	}
      //randomnumber = ((Randomnumber)((Object[])dataList.get(j))[0]);
      user = ((User)((Object[])dataList.get(j))[1]);
      String[] randoms = randomnumber.getRandomNumber().split(",");
      String numbers = "";
      for (String string : randoms) {
        numbers = numbers + "<b>" + string + "</b>";
      }
      detailBybuyerJSON.setBuyCount(randoms.length+"");
      detailBybuyerJSON.setBuyTime(randomnumber.getBuyDate());
      detailBybuyerJSON.setFaceImg(user.getFaceImg());
      detailBybuyerJSON.setRandomNumber(numbers);
      detailBybuyerJSON.setUserId(user.getUserId()+"");
      String userName = UserNameUtil.userName(user);
      detailBybuyerJSON.setUserName(userName);
      detailBybuyerJSONList.add(detailBybuyerJSON);
    }
    Struts2Utils.renderJson(detailBybuyerJSONList, new String[0]);
    return null;
  }
  
  public void getLotteryRecords()
  {
	// query last end-date
	final MemcachedClient memc = AliyunOcsSampleHelp.getIMemcachedCache();
    spellbuyrecord = ((Spellbuyrecord)memc.get("lotteryUtil_spellbuyrecord_" + id));
    if (spellbuyrecord == null)
    {
      spellbuyrecord = (Spellbuyrecord)spellbuyrecordService
    		  .getEndBuyDateByProduct(Integer.valueOf(id)).get(0);
      memc.set("lotteryUtil_spellbuyrecord_" + id, 1800, spellbuyrecord);
    }
    startDate = spellbuyrecord.getBuyDate();
    // query last page
    Pagination page = (Pagination)memc.get("page_" + 
    		DateUtil.SDateTimeToDateBySSS(startDate).getTime());
    final int num = 100, delta = 5;
    if (page == null)
    {
      page = spellbuyrecordService.getlotteryDetail(null, startDate, 0, num+delta);
      memc.set("page_" + DateUtil.SDateTimeToDateBySSS(startDate).getTime(), 0, page);
    }
    List<?> dataList = page.getList();
    final int size = dataList.size();
    lotteryDetailJSONList = new ArrayList<LotteryDetailJSON>();
    for (int j = 0, n = Math.min(size, num); j < n; j++) {
    	lotteryDetailJSON = new LotteryDetailJSON();
    	Object[] objs = (Object[])dataList.get(j);
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
        //product = ((Product)((Object[])dataList.get(j))[0]);
        //spellbuyrecord = ((Spellbuyrecord)((Object[])dataList.get(j))[1]);
        //user = ((User)((Object[])dataList.get(j))[2]);
        //spellbuyproduct = ((Spellbuyproduct)((Object[])dataList.get(j))[3]);
        lotteryDetailJSON.setBuyCount(spellbuyrecord.getBuyPrice());
        lotteryDetailJSON.setProductId(spellbuyproduct.getFkProductId());
        lotteryDetailJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        /*if (spellbuyproduct.getSpStatus().intValue() == 1) {
          lotteryDetailJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        } else {
          lotteryDetailJSON.setProductId(spellbuyproduct.getFkProductId());
        }*/
        lotteryDetailJSON.setProductName(product.getProductName());
        lotteryDetailJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
        lotteryDetailJSON.setProductStatus(spellbuyproduct.getSpStatus());
        lotteryDetailJSON.setProductTitle(product.getProductTitle());
        lotteryDetailJSON.setBuyDate(spellbuyrecord.getBuyDate());
        lotteryDetailJSON.setBuyTime(spellbuyrecord.getBuyDate().split(" ")[1]);
        
        calendar.setTime(DateUtil.SDateTimeToDateBySSS(spellbuyrecord.getBuyDate()));

        Integer h = Integer.valueOf(calendar.get(11));
        Integer m = Integer.valueOf(calendar.get(12));
        Integer s1 = Integer.valueOf(calendar.get(13));
        Integer ss1 = Integer.valueOf(calendar.get(14));
        String sh = "";
        String sm = "";
        String ss = "";
        String sss = "";
        if (h.intValue() < 10) {
          sh = "0" + h;
        } else {
          sh = h.toString();
        }
        if (m.intValue() < 10) {
          sm = "0" + m;
        } else {
          sm = m.toString();
        }
        if (s1.intValue() < 10) {
          ss = "0" + s1;
        } else {
          ss = s1.toString();
        }
        if (ss1.intValue() < 10) {
          sss = "00" + ss1;
        } else if (ss1.intValue() < 100) {
          sss = "0" + ss1;
        } else {
          sss = ss1.toString();
        }
        lotteryDetailJSON.setDateSum(sh + sm + ss + sss);
        
        String userName = UserNameUtil.userName(user);
        lotteryDetailJSON.setUserName(userName);
        lotteryDetailJSON.setUserId(user.getUserId());
        lotteryDetailJSONList.add(lotteryDetailJSON);
    }
    for (int j = num; j < size; j++)
    {
      lotteryDetailJSON = new LotteryDetailJSON();
      Object[] objs = (Object[])dataList.get(j);
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
      //product = ((Product)((Object[])dataList.get(j))[0]);
      //spellbuyrecord = ((Spellbuyrecord)((Object[])dataList.get(j))[1]);
      //user = ((User)((Object[])dataList.get(j))[2]);
      //spellbuyproduct = ((Spellbuyproduct)((Object[])dataList.get(j))[3]);
      lotteryDetailJSON.setBuyCount(spellbuyrecord.getBuyPrice());
      /*if (spellbuyproduct.getSpStatus().intValue() == 1) {
        lotteryDetailJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
      } else {
        lotteryDetailJSON.setProductId(spellbuyproduct.getFkProductId());
      }*/
      lotteryDetailJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      lotteryDetailJSON.setProductId(spellbuyproduct.getFkProductId());
      lotteryDetailJSON.setProductName(product.getProductName());
      lotteryDetailJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
      lotteryDetailJSON.setProductStatus(spellbuyproduct.getSpStatus());
      lotteryDetailJSON.setProductTitle(product.getProductTitle());
      lotteryDetailJSON.setBuyDate(spellbuyrecord.getBuyDate());
      lotteryDetailJSON.setBuyTime(spellbuyrecord.getBuyDate().split(" ")[1]);
      
      String userName = UserNameUtil.userName(user);
      lotteryDetailJSON.setUserName(userName);
      lotteryDetailJSON.setUserId(user.getUserId());
      lotteryDetailJSONList.add(lotteryDetailJSON);
    }
    dataList = null;
    Struts2Utils.renderJson(lotteryDetailJSONList, StringUtil.EARR_STRING);
  }
  
  public String buyListAjaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    ParticipateJSONList = new ArrayList();
    Pagination pagination = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryDetail_buyListAjaxPage_pagination_" + id + "_" + pageNo + "_" + pageSize);
    if (pagination == null)
    {
      pagination = spellbuyrecordService.LatestParticipate(id, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryDetail_buyListAjaxPage_pagination_" + id + "_" + pageNo + "_" + pageSize, 0, pagination);
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
      /*participateJSON.setIp_address(user.getIpAddress());
      participateJSON.setIp_location(user.getIpLocation());*/
      participateJSON.setIp_address(spellbuyrecord.getBuyIp());
      participateJSON.setIp_location(spellbuyrecord.getBuyLocal());
      participateJSON.setUserName(userName);
      participateJSON.setUserId(String.valueOf(user.getUserId()));
      participateJSON.setUserFace(user.getFaceImg());
      ParticipateJSONList.add(participateJSON);
    }
    Struts2Utils.renderJson(ParticipateJSONList, new String[0]);
    return null;
  }
  
  public void getUserBuyGoodsCodeInfo()
  {
    randomNumberJSONList = new ArrayList();
    randomnumberList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("randomnumberList_" + userId + "_" + id));
    if (randomnumberList == null)
    {
      randomnumberList = randomnumberService.LotteryDetailByRandomnumber(Integer.valueOf(Integer.parseInt(userId)), Integer.valueOf(Integer.parseInt(id)));
      AliyunOcsSampleHelp.getIMemcachedCache().set("randomnumberList_" + userId + "_" + id, 0, randomnumberList);
    }
    for (Randomnumber randomnumber : randomnumberList)
    {
      randomNumberJSON = new RandomNumberJSON();
      randomNumberJSON.setRandomNumbers(randomnumber.getRandomNumber());
      randomNumberJSON.setBuyDate(randomnumber.getBuyDate());
      randomNumberJSONList.add(randomNumberJSON);
    }
    Struts2Utils.renderJson(randomNumberJSONList, new String[0]);
  }
  
  public void getUserBuyCodeByBuyid()
  {
    try
    {
      randomnumber = ((Randomnumber)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryDetail_getUserBuyCodeByBuyid_randomnumber_" + id + "_" + spellbuyrecordId));
      if (randomnumber == null)
      {
        randomnumber = randomnumberService.getUserBuyCodeByBuyid(id, spellbuyrecordId);
        AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryDetail_getUserBuyCodeByBuyid_randomnumber_" + id + "_" + spellbuyrecordId, 0, randomnumber);
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
  
  public List<RandomNumberJSON> getRandomNumberJSONList()
  {
    return randomNumberJSONList;
  }
  
  public void setRandomNumberJSONList(List<RandomNumberJSON> randomNumberJSONList)
  {
    this.randomNumberJSONList = randomNumberJSONList;
  }
  
  public Randomnumber getRandomnumber()
  {
    return randomnumber;
  }
  
  public void setRandomnumber(Randomnumber randomnumber)
  {
    this.randomnumber = randomnumber;
  }
  
  public Spellbuyrecord getSpellbuyrecord()
  {
    return spellbuyrecord;
  }
  
  public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord)
  {
    this.spellbuyrecord = spellbuyrecord;
  }
  
  public RandomNumberJSON getRandomNumberJSON()
  {
    return randomNumberJSON;
  }
  
  public void setRandomNumberJSON(RandomNumberJSON randomNumberJSON)
  {
    this.randomNumberJSON = randomNumberJSON;
  }
  
  public List<Randomnumber> getRandomnumberList()
  {
    return randomnumberList;
  }
  
  public void setRandomnumberList(List<Randomnumber> randomnumberList)
  {
    this.randomnumberList = randomnumberList;
  }
  
  public Integer getBuyerCount()
  {
    return buyerCount;
  }
  
  public void setBuyerCount(Integer buyerCount)
  {
    this.buyerCount = buyerCount;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public DetailBybuyerJSON getDetailBybuyerJSON()
  {
    return detailBybuyerJSON;
  }
  
  public void setDetailBybuyerJSON(DetailBybuyerJSON detailBybuyerJSON)
  {
    this.detailBybuyerJSON = detailBybuyerJSON;
  }
  
  public List<DetailBybuyerJSON> getDetailBybuyerJSONList()
  {
    return detailBybuyerJSONList;
  }
  
  public void setDetailBybuyerJSONList(List<DetailBybuyerJSON> detailBybuyerJSONList)
  {
    this.detailBybuyerJSONList = detailBybuyerJSONList;
  }
  
  public int getBuyResultCount()
  {
    return buyResultCount;
  }
  
  public void setBuyResultCount(int buyResultCount)
  {
    this.buyResultCount = buyResultCount;
  }
  
  public List<ParticipateJSON> getParticipateJSONList()
  {
    return ParticipateJSONList;
  }
  
  public void setParticipateJSONList(List<ParticipateJSON> participateJSONList)
  {
    ParticipateJSONList = participateJSONList;
  }
  
  public int getNewProductId()
  {
    return newProductId;
  }
  
  public void setNewProductId(int newProductId)
  {
    this.newProductId = newProductId;
  }
  
  public int getNewProductPeriod()
  {
    return newProductPeriod;
  }
  
  public void setNewProductPeriod(int newProductPeriod)
  {
    this.newProductPeriod = newProductPeriod;
  }
  
  public Spellbuyproduct getSpellbuyproduct()
  {
    return spellbuyproduct;
  }
  
  public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct)
  {
    this.spellbuyproduct = spellbuyproduct;
  }
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public TreeMap<Integer, Integer> getProductPeriodList()
  {
    return productPeriodList;
  }
  
  public void setProductPeriodList(TreeMap<Integer, Integer> productPeriodList)
  {
    this.productPeriodList = productPeriodList;
  }
  
  public String getWinNumber()
  {
    return winNumber;
  }
  
  public void setWinNumber(String winNumber)
  {
    this.winNumber = winNumber;
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
  
  public String getEndDate()
  {
    return endDate;
  }
  
  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }
  
  public Long getDateSUM()
  {
    return DateSUM;
  }
  
  public void setDateSUM(Long dateSUM)
  {
    DateSUM = dateSUM;
  }
  
  public List<Productimage> getProductimageList()
  {
    return productimageList;
  }
  
  public void setProductimageList(List<Productimage> productimageList)
  {
    this.productimageList = productimageList;
  }
  
  public String getSpellbuyrecordId()
  {
    return spellbuyrecordId;
  }
  
  public void setSpellbuyrecordId(String spellbuyrecordId)
  {
    this.spellbuyrecordId = spellbuyrecordId;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
}

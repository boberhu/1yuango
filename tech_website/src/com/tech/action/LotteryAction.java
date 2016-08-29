package com.tech.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Lotteryproductutil;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.LotteryproductutilService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.CaipiaoUtil;
import com.tech.util.DateUtil;
import com.tech.util.MD5Util;
import com.tech.util.PaginationUtil;
import com.tech.util.Struts2Utils;
import com.tech.util.UserNameUtil;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component("LotteryAction")
public class LotteryAction extends BaseAction
{
  private static final long serialVersionUID = 2321693841189871589L;
  private static final Logger LOG = LoggerFactory.getLogger(LotteryAction.class);
  
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  private ProductService productService;
  @Autowired
  private RandomnumberService randomnumberService;
  @Autowired
  private LotteryproductutilService lotteryproductutilService;
  private Latestlottery latestlottery;
  private Latestlottery latestlotteryByAjax;
  private List<Latestlottery> latestlotteryList;
  private ProductJSON productJSON;
  private ProductCart productCart;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private Spellbuyrecord spellbuyrecord;
  private static List<Lotteryproductutil> LotteryproductutilList;
  private User user;
  private String id;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 24;
  private int pageCount;
  private int resultCount;
  private long time;
  Calendar calendar = Calendar.getInstance();
  private static List<ProductJSON> upcomingAnnouncedList;
  private static List<ProductJSON> upcomingAnnouncedByTopList;
  private static Long nowDateByUpcomingAnnounced = Long.valueOf(System.currentTimeMillis());
  private static Long beginDateByUpcomingAnnounced;
  private static Long nowDateByUpcomingAnnouncedByTop = Long.valueOf(System.currentTimeMillis());
  private static Long beginDateByUpcomingAnnouncedByTop;
  
  /*
   * 已经揭晓商品
   * 夺宝
   * 夺宝
  */
  public String index()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (pages != null) {
      pageNo = Integer.parseInt(pages.split("_")[1]);
    }
    LOG.debug("id: {}, pages: {}", id, pages);
    LOG.debug("pageNo: {}, pageSize: {}", pageNo, pageSize);
    Pagination page = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("lottery_index_page_" + id + "_" + pageNo + "_" + pageSize);
    if (page == null)
    {
      LOG.debug("latestd lotterys not in cache");
      page = latestlotteryService.LatestAnnounced(id, pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("lottery_index_page_" + id + "_" + pageNo + "_" + pageSize, 5, page);
    }
    resultCount = page.getResultCount();
    latestlotteryList = (List<Latestlottery>)page.getList();
    for (int i = 0, size = latestlotteryList.size(); i < size; i++)
    {
      latestlottery = latestlotteryList.get(i);
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
      latestlottery.setAnnouncedTime(DateUtil.getShortTime(latestlottery.getAnnouncedTime()));
      latestlottery.setBuyUser(userName);
    }
    if (StringUtils.isNotBlank(id)) {
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/lottery/" + id + "/p_");
    } else {
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/lottery/index/p_");
    }
    protypeList = protypeService.listByProductList();
    return "index";
  }
  
  public void lotteryproductutilList()
  {
    LotteryproductutilList = ((List<Lotteryproductutil>)AliyunOcsSampleHelp
    		.getIMemcachedCache().get("LotteryproductutilList"));
    if (LotteryproductutilList == null)
    {
      LotteryproductutilList = lotteryproductutilService.loadAll(2);
    	//LotteryproductutilList = lotteryproductutilService.findList(1,5);
      if (LotteryproductutilList.size() > 0)
      {
        for (Lotteryproductutil lotteryproductutil : LotteryproductutilList)
        {
          Long nowDate = Long.valueOf(System.currentTimeMillis());
          Long endDate = Long.valueOf(DateUtil.SDateTimeToDate(lotteryproductutil.getLotteryProductEndDate()).getTime());
          lotteryproductutil.setLotteryProductEndDate(((endDate.longValue() - nowDate.longValue()) / 1000L)+"");
        }
        Struts2Utils.renderJson(LotteryproductutilList, new String[0]);
        AliyunOcsSampleHelp.getIMemcachedCache().set("LotteryproductutilList", 2, LotteryproductutilList);
      }
    }
    else
    {
      Struts2Utils.renderJson(LotteryproductutilList, new String[0]);
    }
	  Struts2Utils.renderJson(new ArrayList<Lotteryproductutil>(0), new String[0]);
  }
  
  public void LatestlotteryByProductId()
  {
    latestlotteryByAjax = ((Latestlottery)AliyunOcsSampleHelp.getIMemcachedCache().get("latestlotteryByAjax_" + id));
    if (latestlotteryByAjax == null)
    {
      latestlotteryByAjax = ((Latestlottery)latestlotteryService.findById(id));
      if (latestlotteryByAjax != null)
      {
        Struts2Utils.renderJson(latestlotteryByAjax, new String[0]);
        AliyunOcsSampleHelp.getIMemcachedCache().set("latestlotteryByAjax_" + id, 1800, latestlotteryByAjax);
      }
      else
      {
        Struts2Utils.renderText("false", new String[0]);
      }
    }
    else
    {
      Struts2Utils.renderJson(latestlotteryByAjax, new String[0]);
    }
  }
  
  public synchronized void lotteryUtil()
    throws NumberFormatException, IOException
  {
    List listbyLatest = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_listbyLatest_" + id);
    if (listbyLatest == null)
    {
      listbyLatest = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer.valueOf(Integer.parseInt(id)));
      if (listbyLatest.size() > 0)
      {
        Struts2Utils.renderText("true", new String[0]);
        AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_listbyLatest_" + id, 1800, listbyLatest);
      }
      else
      {
        Struts2Utils.renderText("false", new String[0]);
      }
    }
    else
    {
      Struts2Utils.renderText("true", new String[0]);
    }
  }
  
  public synchronized boolean lotteryUtilBy(String id)
    throws NumberFormatException, IOException
  {
    boolean flag = false;
    String lotteryId = MD5Util.encode(id);
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(lotteryId) == null)
    {
      AliyunOcsSampleHelp.getIMemcachedCache().set(lotteryId, 1800, "y");
      spellbuyproduct = ((Spellbuyproduct)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyproduct_" + id));
      if (spellbuyproduct == null)
      {
        spellbuyproduct = ((Spellbuyproduct)spellbuyproductService.findById(id));
        AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyproduct_" + id, 1, spellbuyproduct);
      }
      if (spellbuyproduct.getSpStatus().intValue() == 2)
      {
        this.spellbuyrecord = ((Spellbuyrecord)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyrecord_" + spellbuyproduct.getSpellbuyProductId()));
        if (this.spellbuyrecord == null)
        {
          this.spellbuyrecord = ((Spellbuyrecord)spellbuyrecordService.getEndBuyDateByProduct(spellbuyproduct.getSpellbuyProductId()).get(0));
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyrecord_" + spellbuyproduct.getSpellbuyProductId(), 1800, this.spellbuyrecord);
        }
        String newDate = this.spellbuyrecord.getBuyDate();
        List<Spellbuyrecord> dataList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_dataList_" + DateUtil.SDateTimeToDateBySSS(newDate).getTime());
        if (dataList == null)
        {
          dataList = spellbuyrecordService.getSpellbuyRecordByLast100(null, newDate, 60);
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_dataList_" + DateUtil.SDateTimeToDateBySSS(newDate).getTime(), 1800, dataList);
        }
        Long DateSUM = Long.valueOf(0L);
        Integer buyId = ((Spellbuyrecord)dataList.get(0)).getFkSpellbuyProductId();
        int i100 = 0;
        for (int k = 0; k < dataList.size(); k++) {
          if ((k <= 0) || 
          
            (!newDate.equals(((Spellbuyrecord)dataList.get(k)).getBuyDate())) || (((Spellbuyrecord)dataList.get(k)).getFkSpellbuyProductId() == buyId))
          {
            if (i100++ == 100) {
              break;
            }
            this.spellbuyrecord = ((Spellbuyrecord)dataList.get(k));
            calendar.setTime(DateUtil.SDateTimeToDate(this.spellbuyrecord.getBuyDate()));
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
            DateSUM = Long.valueOf(DateSUM.longValue() + Long.parseLong(sh + sm + ss + sss));
          }
        }
        System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + id);
        


        String str = CaipiaoUtil.caiNumber();
        String sscNumber = str.split("\\|")[1];
        Long sscPeriod = Long.valueOf(Long.parseLong(str.split("\\|")[0]));
        Integer winNumber = Integer.valueOf(Integer.parseInt(String.valueOf((DateSUM.longValue() + Integer.parseInt(sscNumber)) % spellbuyproduct.getSpellbuyPrice().intValue() + 10000001L)));
        
        System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + spellbuyproduct.getSpellbuyProductId());

        List<Object[]> objList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_objList_" + spellbuyproduct.getSpellbuyProductId() + "_" + winNumber);
        if (objList == null)
        {
          objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(spellbuyproduct.getSpellbuyProductId(), String.valueOf(winNumber));
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_objList_" + spellbuyproduct.getSpellbuyProductId() + "_" + winNumber, 1800, objList);
        }
        Spellbuyrecord spellbuyrecord = (Spellbuyrecord)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyrecord_add_" + id);
        if (spellbuyrecord == null)
        {
          spellbuyrecord = (Spellbuyrecord)((Object[])objList.get(0))[1];
          spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
          spellbuyrecord.setSpWinningStatus("1");
          spellbuyrecord.setBuyStatus("1"); 
          spellbuyrecordService.add(spellbuyrecord);
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyrecord_add_" + id, 1800, spellbuyrecord);
        }
        user = ((User)((Object[])objList.get(0))[2]);
        int productPeriod = spellbuyproduct.getProductPeriod().intValue();

        spellbuyproduct = ((Spellbuyproduct)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyproduct_add_" + id));
        if (spellbuyproduct == null)
        {
          spellbuyproduct.setSpStatus(Integer.valueOf(1));
          spellbuyproductService.add(spellbuyproduct);
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyproduct_add_" + id, 1800, spellbuyproduct);
        }
        product = ((Product)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_product_" + spellbuyproduct.getFkProductId()));
        if (product == null)
        {
          product = ((Product)productService.findById(String.valueOf(spellbuyproduct.getFkProductId())));
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_product_" + spellbuyproduct.getFkProductId(), 1800, product);
        }
        if (product.getStatus().intValue() == 1)
        {
          List<Spellbuyproduct> spellbuyproductOld = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_spellbuyproductOld_" + spellbuyproduct.getFkProductId());
          if (spellbuyproductOld == null)
          {
            spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
            System.err.println("spellbuyproductOld:" + spellbuyproductOld);
            System.err.println("spellbuyproductOld-size:" + spellbuyproductOld.size());
            if (spellbuyproductOld.size() == 0)
            {
              Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
              spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
              spellbuyproduct2.setProductPeriod(Integer.valueOf(++productPeriod));
              spellbuyproduct2.setSpellbuyCount(Integer.valueOf(0));
              spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
              spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
              spellbuyproduct2.setSpellbuyPrice(product.getProductPrice());
              spellbuyproduct2.setSpSinglePrice(product.getSinglePrice());
              spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
              spellbuyproduct2.setSpStatus(Integer.valueOf(0));
              if (product.getAttribute71().equals("hot")) {
                spellbuyproduct2.setSpellbuyType(Integer.valueOf(8));
              } else {
                spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
              }
              spellbuyproductService.add(spellbuyproduct2);
            }
            AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_spellbuyproductOld_" + spellbuyproduct.getFkProductId(), 1800, spellbuyproductOld);
          }
        }
        List list = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_list_" + spellbuyproduct.getSpellbuyProductId());
        if (list == null)
        {
          list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(spellbuyproduct.getSpellbuyProductId());
          if (list.size() == 0)
          {
            latestlottery = new Latestlottery();
            latestlottery.setProductId(spellbuyproduct.getFkProductId());
            latestlottery.setProductName(product.getProductName());
            latestlottery.setProductTitle(product.getProductTitle());
            latestlottery.setProductPrice(spellbuyproduct.getSpellbuyPrice());
            latestlottery.setProductImg(product.getHeadImage());
            latestlottery.setProductPeriod(spellbuyproduct.getProductPeriod());
            latestlottery.setAnnouncedTime(DateUtil.DateTimeToStrBySSS(new Date()));
            latestlottery.setAnnouncedType(spellbuyproduct.getSpellbuyType());
            latestlottery.setDateSum(DateSUM);
            latestlottery.setSscNumber(sscNumber);
            latestlottery.setSscPeriod(sscPeriod);
            latestlottery.setBuyTime(spellbuyrecord.getBuyDate());
            latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId());
            latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId());
            BigDecimal buyNumberCount = randomnumberService.RandomNumberByUserBuyCount(String.valueOf(user.getUserId()), spellbuyproduct.getSpellbuyProductId());
            latestlottery.setBuyNumberCount(Integer.valueOf(Integer.parseInt(String.valueOf(buyNumberCount))));
            latestlottery.setRandomNumber(winNumber);
            /*latestlottery.setLocation(user.getIpLocation());
            latestlottery.setUserId(user.getUserId());*/
            latestlottery.setLocation(spellbuyrecord.getBuyLocal());
            latestlottery.setUserId(user.getUserId());
            latestlottery.setUserName(UserNameUtil.userName(user));
            latestlottery.setUserFace(user.getFaceImg());
            latestlottery.setStatus(Integer.valueOf(1));
            latestlottery.setShareStatus(Integer.valueOf(-1));
            latestlottery.setShareId(null);
            String dingdanhao=spellbuyproduct.getFkProductId()+""+winNumber+""+user.getUserId()+"-"+Math.abs(new Random().nextInt());
            latestlottery.setDingdanhao(dingdanhao);
            latestlotteryService.add(latestlottery);
          }
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_list_" + spellbuyproduct.getSpellbuyProductId(), 1800, list);
        }
        Struts2Utils.renderText("true", new String[0]);
        Lotteryproductutil lotteryproductutil = (Lotteryproductutil)AliyunOcsSampleHelp.getIMemcachedCache().get("lotteryUtil_lotteryproductutil_" + id);
        if (lotteryproductutil == null)
        {
          lotteryproductutil = (Lotteryproductutil)lotteryproductutilService.findById(id);
          if (lotteryproductutil != null) {
            lotteryproductutilService.delete(lotteryproductutil.getLotteryId());
          }
          AliyunOcsSampleHelp.getIMemcachedCache().set("lotteryUtil_lotteryproductutil_" + id, 1800, lotteryproductutil);
        }
        flag = true;
      }
    }
    return flag;
  }
  
  public void lotteryUtilAjax()
  {
    String lotteryId = MD5Util.encode(id);
    if (AliyunOcsSampleHelp.getIMemcachedCache().get(lotteryId) == null)
    {
      AliyunOcsSampleHelp.getIMemcachedCache().set(lotteryId, 43200, "y");
      spellbuyproduct = ((Spellbuyproduct)spellbuyproductService.findById(id));
      if (spellbuyproduct.getSpStatus().intValue() == 2)
      {
        this.spellbuyrecord = ((Spellbuyrecord)spellbuyrecordService.getEndBuyDateByProduct(spellbuyproduct.getSpellbuyProductId()).get(0));
        String newDate = this.spellbuyrecord.getBuyDate();
        List<Spellbuyrecord> dataList = spellbuyrecordService.getSpellbuyRecordByLast100(null, newDate, 60);
        Long DateSUM = Long.valueOf(0L);
        Integer buyId = ((Spellbuyrecord)dataList.get(0)).getFkSpellbuyProductId();
        int i100 = 0;
        for (int k = 0; k < dataList.size(); k++) {
          if ((k <= 0) || 
          
            (!newDate.equals(((Spellbuyrecord)dataList.get(k)).getBuyDate())) || (((Spellbuyrecord)dataList.get(k)).getFkSpellbuyProductId() == buyId))
          {
            if (i100++ == 100) {
              break;
            }
            this.spellbuyrecord = ((Spellbuyrecord)dataList.get(k));
            
            calendar.setTime(DateUtil.SDateTimeToDate(this.spellbuyrecord.getBuyDate()));
            


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
            DateSUM = Long.valueOf(DateSUM.longValue() + Long.parseLong(sh + sm + ss + sss));
          }
        }
        System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + id);
        


        Integer winNumber = Integer.valueOf(Integer.parseInt(String.valueOf(DateSUM.longValue() % spellbuyproduct.getSpellbuyPrice().intValue() + 10000001L)));
        
        System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + spellbuyproduct.getSpellbuyProductId());

        List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(spellbuyproduct.getSpellbuyProductId(), String.valueOf(winNumber));
        Spellbuyrecord spellbuyrecord = (Spellbuyrecord)((Object[])objList.get(0))[1];
        user = ((User)((Object[])objList.get(0))[2]);
        

        spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
        spellbuyrecord.setSpWinningStatus("1");
        spellbuyrecord.setBuyStatus("1");
        spellbuyrecordService.add(spellbuyrecord);
        
        int productPeriod = spellbuyproduct.getProductPeriod().intValue();

        spellbuyproduct.setSpStatus(Integer.valueOf(1));
        spellbuyproductService.add(spellbuyproduct);
        
        product = ((Product)productService.findById(String.valueOf(spellbuyproduct.getFkProductId())));
        if (product.getStatus().intValue() == 1)
        {
          List<Spellbuyproduct> spellbuyproductOld = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(spellbuyproduct.getFkProductId());
          System.err.println("spellbuyproductOld:" + spellbuyproductOld);
          System.err.println("spellbuyproductOld-size:" + spellbuyproductOld.size());
          if (spellbuyproductOld.size() == 0)
          {
            Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
            spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
            spellbuyproduct2.setProductPeriod(Integer.valueOf(++productPeriod));
            spellbuyproduct2.setSpellbuyCount(Integer.valueOf(0));
            spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
            spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct2.setSpellbuyPrice(product.getProductPrice());
            spellbuyproduct2.setSpSinglePrice(product.getSinglePrice());
            spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
            spellbuyproduct2.setSpStatus(Integer.valueOf(0));
            if (product.getAttribute71().equals("hot")) {
              spellbuyproduct2.setSpellbuyType(Integer.valueOf(8));
            } else {
              spellbuyproduct2.setSpellbuyType(Integer.valueOf(0));
            }
            spellbuyproductService.add(spellbuyproduct2);
          }
        }
        List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(spellbuyproduct.getSpellbuyProductId());
        if (list.size() == 0)
        {
          latestlottery = new Latestlottery();
          latestlottery.setProductId(spellbuyproduct.getFkProductId());
          latestlottery.setProductName(product.getProductName());
          latestlottery.setProductTitle(product.getProductTitle());
          latestlottery.setProductPrice(spellbuyproduct.getSpellbuyPrice());
          latestlottery.setProductImg(product.getHeadImage());
          latestlottery.setProductPeriod(spellbuyproduct.getProductPeriod());
          latestlottery.setAnnouncedTime(newDate);
          latestlottery.setAnnouncedType(spellbuyproduct.getSpellbuyType());
          latestlottery.setDateSum(DateSUM);
          latestlottery.setBuyTime(spellbuyrecord.getBuyDate());
          latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId());
          latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId());
          BigDecimal buyNumberCount = randomnumberService.RandomNumberByUserBuyCount(String.valueOf(user.getUserId()), spellbuyproduct.getSpellbuyProductId());
          latestlottery.setBuyNumberCount(Integer.valueOf(Integer.parseInt(String.valueOf(buyNumberCount))));
          latestlottery.setRandomNumber(winNumber);
          //latestlottery.setLocation(user.getIpLocation());
          latestlottery.setLocation(spellbuyrecord.getBuyLocal());
          latestlottery.setUserId(user.getUserId());
          latestlottery.setUserName(UserNameUtil.userName(user));
          latestlottery.setUserFace(user.getFaceImg());
          latestlottery.setStatus(Integer.valueOf(1));
          latestlottery.setShareStatus(Integer.valueOf(-1));
          latestlottery.setShareId(null);
          latestlotteryService.add(latestlottery);
        }
        Struts2Utils.renderText("true", new String[0]);
      }
    }
    else
    {
      List list = latestlotteryService.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer.valueOf(Integer.parseInt(id)));
      if (list.size() > 0) {
        Struts2Utils.renderText("true", new String[0]);
      } else {
        Struts2Utils.renderText("false", new String[0]);
      }
    }
  }
  
  public String upcomingAnnounced()
  {
    nowDateByUpcomingAnnounced = Long.valueOf(System.currentTimeMillis());
    StringBuilder sbuf = new StringBuilder();
    if (beginDateByUpcomingAnnounced == null)
    {
      Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, pageSize);
      List<?> dateList = datePage.getList();
      upcomingAnnouncedList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setProductTitle(product.getProductTitle());
        upcomingAnnouncedList.add(productJSON);
      }
      beginDateByUpcomingAnnounced = Long.valueOf(System.currentTimeMillis());
      Struts2Utils.renderJson(upcomingAnnouncedList, new String[0]);
    }
    else if (nowDateByUpcomingAnnounced.longValue() - beginDateByUpcomingAnnounced.longValue() < 5000L)
    {
      Struts2Utils.renderJson(upcomingAnnouncedList, new String[0]);
    }
    else
    {
      beginDateByUpcomingAnnounced = Long.valueOf(System.currentTimeMillis());
      Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, pageSize);
      List<?> dateList = datePage.getList();
      upcomingAnnouncedList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setProductTitle(product.getProductTitle());
        upcomingAnnouncedList.add(productJSON);
      }
      Struts2Utils.renderJson(upcomingAnnouncedList, new String[0]);
    }
    return null;
  }
  
  public String upcomingAnnouncedByTop()
  {
    nowDateByUpcomingAnnouncedByTop = Long.valueOf(System.currentTimeMillis());
    StringBuilder sbuf = new StringBuilder();
    if (beginDateByUpcomingAnnouncedByTop == null)
    {
      Pagination datePage = spellbuyproductService.upcomingAnnouncedByTop(pageNo, pageSize);
      List<?> dateList = datePage.getList();
      upcomingAnnouncedByTopList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setProductTitle(product.getProductTitle());
        upcomingAnnouncedByTopList.add(productJSON);
      }
      beginDateByUpcomingAnnouncedByTop = Long.valueOf(System.currentTimeMillis());
      Struts2Utils.renderJson(upcomingAnnouncedByTopList, new String[0]);
    }
    else if (nowDateByUpcomingAnnouncedByTop.longValue() - beginDateByUpcomingAnnouncedByTop.longValue() < 5000L)
    {
      Struts2Utils.renderJson(upcomingAnnouncedByTopList, new String[0]);
    }
    else
    {
      beginDateByUpcomingAnnouncedByTop = Long.valueOf(System.currentTimeMillis());
      Pagination datePage = spellbuyproductService.upcomingAnnouncedByTop(pageNo, pageSize);
      List<?> dateList = datePage.getList();
      upcomingAnnouncedByTopList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setProductTitle(product.getProductTitle());
        upcomingAnnouncedByTopList.add(productJSON);
      }
      Struts2Utils.renderJson(upcomingAnnouncedByTopList, new String[0]);
    }
    return null;
  }
  
  public void updateLatest()
  {
    List<Latestlottery> latestLotteryList = latestlotteryService.query(" from Latestlottery where 1=1");
    for (Latestlottery latestlottery : latestLotteryList) {
      try
      {
        product = ((Product)productService.findById(latestlottery.getProductId()+""));
        Integer productTypeId = product.getProductType();
        System.err.println(latestlottery.getProductId() + "   " + productTypeId);
        latestlottery.setProductType(productTypeId);
        latestlotteryService.add(latestlottery);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
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
  
  public Spellbuyrecord getSpellbuyrecord()
  {
    return spellbuyrecord;
  }
  
  public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord)
  {
    this.spellbuyrecord = spellbuyrecord;
  }
  
  public User getUser()
  {
    return user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
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
  
  public ProductCart getProductCart()
  {
    return productCart;
  }
  
  public void setProductCart(ProductCart productCart)
  {
    this.productCart = productCart;
  }
  
  public long getTime()
  {
    return time;
  }
  
  public void setTime(long time)
  {
    this.time = time;
  }
  
}

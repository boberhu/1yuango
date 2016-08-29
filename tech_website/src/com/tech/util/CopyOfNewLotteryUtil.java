package com.tech.util;

import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProductService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
@Service("CopyOfNewLotteryUtil")
public class CopyOfNewLotteryUtil
{
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private ProductService productService;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private Latestlottery latestlottery;
  private Spellbuyrecord spellbuyrecord;
  private ProductCart productCart;
  Calendar calendar = Calendar.getInstance();
  
  @Test
  public void go()
    throws InterruptedException
  {
    productCart = new ProductCart();
    List<Object[]> proList = spellbuyproductService.findByProductId(149);
    product = ((Product)((Object[])proList.get(0))[0]);
    spellbuyproduct = ((Spellbuyproduct)((Object[])proList.get(0))[1]);
    productCart.setHeadImage(product.getHeadImage());
    productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
    productCart.setProductName(product.getProductName());
    productCart.setProductPrice(product.getProductPrice());
    productCart.setProductTitle(product.getProductTitle());
    productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
    productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
    
    lottery(productCart);
  }
  
  public void lottery(ProductCart productCart)
    throws InterruptedException
  {
    Thread.sleep(10000L);
    


    this.spellbuyrecord = ((Spellbuyrecord)spellbuyrecordService.getEndBuyDateByProduct(productCart.getProductId()).get(0));
    String startDate = this.spellbuyrecord.getBuyDate();
    System.err.println("NewLotteryUtil startDate:" + startDate + "   " + productCart.getProductId());
    


    Pagination page = spellbuyrecordService.getlotteryDetail(null, startDate, 0, 100);
    List<?> dataList = page.getList();
    Long DateSUM = Long.valueOf(0L);
    for (int k = 0; k < dataList.size(); k++)
    {
      product = ((Product)((Object[])dataList.get(k))[0]);
      this.spellbuyrecord = ((Spellbuyrecord)((Object[])dataList.get(k))[1]);
      final User user = ((User)((Object[])dataList.get(k))[2]);
      spellbuyproduct = ((Spellbuyproduct)((Object[])dataList.get(k))[3]);
      
      calendar.setTime(DateUtil.SDateTimeToDate(this.spellbuyrecord.getBuyDate()));
      


      Integer h = Integer.valueOf(calendar.get(11));
      Integer m = Integer.valueOf(calendar.get(12));
      Integer s1 = Integer.valueOf(calendar.get(13));
      String shs = "";
      String sms = "";
      String sss = "";
      if (h.intValue() < 10) {
        shs = "0" + h;
      } else {
        shs = h.toString();
      }
      if (m.intValue() < 10) {
        sms = "0" + m;
      } else {
        sms = m.toString();
      }
      if (s1.intValue() < 10) {
        sss = "0" + s1;
      } else {
        sss = s1.toString();
      }
      DateSUM = Long.valueOf(DateSUM.longValue() + Long.parseLong(shs + sms + sss));
    }
    System.err.println("NewLotteryUtil DateSUM:" + DateSUM + "    " + productCart.getProductId());
    


    Integer winNumber = Integer.valueOf(Integer.parseInt(String.valueOf(DateSUM.longValue() % productCart.getProductPrice().intValue() + 10000001L)));
    System.err.println("NewLotteryUtil winNmuber:" + winNumber + "    " + productCart.getProductId());
    
    System.err.println("winNmuber:" + winNumber);
    boolean flag = false;
    Integer productPrice = productCart.getProductPrice();
    int count = productPrice.intValue();
    while (!flag)
    {
      List<Object[]> objList = spellbuyrecordService.WinRandomNumber(productCart.getProductId(), winNumber);
      if (objList.size() == 0)
      {
        winNumber = Integer.valueOf(winNumber.intValue() + 1);
        if (winNumber.intValue() > 10000000 + productPrice.intValue()) {
          winNumber = Integer.valueOf(10000001);
        }
      }
      else
      {
        flag = true;
      }
      System.err.println("++" + winNumber);
      count--;
      if (count == 0)
      {
        winNumber = Integer.valueOf(Integer.parseInt(String.valueOf(DateSUM.longValue() % productCart.getProductPrice().intValue() + 10000001L)));
        break;
      }
    }
    System.err.println("++" + winNumber);
    List<Object[]> objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(productCart.getProductId(), String.valueOf(winNumber));
    Randomnumber randomnumber = (Randomnumber)((Object[])objList.get(0))[0];
    Spellbuyrecord spellbuyrecord = (Spellbuyrecord)((Object[])objList.get(0))[1];
    User user2 = (User)((Object[])objList.get(0))[2];
    
    spellbuyproduct = ((Spellbuyproduct)spellbuyproductService.findById(productCart.getProductId().toString()));
    


    Thread.sleep(30000L);
    
    latestlottery = new Latestlottery();
    latestlottery.setProductId(spellbuyproduct.getFkProductId());
    latestlottery.setProductName(productCart.getProductName());
    latestlottery.setProductTitle(productCart.getProductTitle());
    latestlottery.setProductPrice(productCart.getProductPrice());
    latestlottery.setProductImg(productCart.getHeadImage());
    latestlottery.setProductPeriod(productCart.getProductPeriod());
    latestlottery.setAnnouncedTime(startDate);
    latestlottery.setBuyTime(spellbuyrecord.getBuyDate());
    latestlottery.setSpellbuyRecordId(spellbuyrecord.getSpellbuyRecordId());
    latestlottery.setSpellbuyProductId(spellbuyrecord.getFkSpellbuyProductId());
    latestlottery.setBuyNumberCount(spellbuyrecord.getBuyPrice());
    latestlottery.setRandomNumber(winNumber);
    latestlottery.setLocation(user2.getIpLocation());
    latestlottery.setUserId(user2.getUserId());
    if (StringUtils.isNotEmpty(user2.getUserName())) {
      latestlottery.setUserName(user2.getUserName());
    }
    if (StringUtils.isNotEmpty(user2.getPhone())) {
      latestlottery.setBuyUser(user2.getPhone());
    }
    if (StringUtils.isNotEmpty(user2.getMail())) {
      latestlottery.setBuyUser(user2.getMail());
    }
    latestlottery.setUserFace(user2.getFaceImg());
    latestlottery.setStatus(Integer.valueOf(1));
    latestlottery.setShareStatus(Integer.valueOf(-1));
    latestlottery.setShareId(null);
    latestlotteryService.add(latestlottery);
    
    spellbuyrecord.setSpRandomNo(String.valueOf(winNumber));
    spellbuyrecord.setSpWinningStatus("1");
    spellbuyrecord.setBuyStatus("1");
    spellbuyrecordService.add(spellbuyrecord);
    
    int productPeriod = productCart.getProductPeriod().intValue();
    Spellbuyproduct spellbuyproduct2 = new Spellbuyproduct();
    spellbuyproduct2.setFkProductId(spellbuyproduct.getFkProductId());
    spellbuyproduct2.setProductPeriod(Integer.valueOf(++productPeriod));
    spellbuyproduct2.setSpellbuyCount(Integer.valueOf(0));
    spellbuyproduct2.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
    spellbuyproduct2.setSpellbuyPrice(productCart.getProductPrice());
    spellbuyproduct2.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
    spellbuyproduct2.setSpStatus(Integer.valueOf(0));
    spellbuyproductService.add(spellbuyproduct2);
    
    product = ((Product)productService.findById(String.valueOf(spellbuyproduct.getFkProductId())));
    int Period = Integer.parseInt(product.getAttribute71());
    Period++;
    product.setAttribute71(String.valueOf(Period));
    product.setStatus(Integer.valueOf(1));
    productService.add(product);
    
    spellbuyproduct.setSpStatus(Integer.valueOf(1));
    spellbuyproductService.add(spellbuyproduct);
  }
}

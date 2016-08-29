package com.tech.util;

import com.tech.pojo.Latestlottery;
import com.tech.pojo.Product;
import com.tech.pojo.ProductCart;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.ProductService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.List;

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
@Service("TempLotteryUtil")
public class TempLotteryUtil
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
  private User user;
  private Spellbuyproduct spellbuyproduct;
  private Latestlottery latestlottery;
  private Spellbuyrecord spellbuyrecord;
  private ProductCart productCart;
  Calendar calendar = Calendar.getInstance();
  
  @Test
  public void go()
    throws InterruptedException
  {
    List<Latestlottery> list = latestlotteryService.query("from Latestlottery latestlottery where 1=1");
    System.err.println("size:" + list.size());
    for (Latestlottery latestlottery : list)
    {
      Long l = latestlottery.getDateSum();
      l = Long.valueOf(l.longValue() - 1L);
      latestlottery.setDateSum(l);
      System.err.println("default:" + latestlottery.getDateSum() + "   update:" + l);
      latestlotteryService.add(latestlottery);
    }
  }
}

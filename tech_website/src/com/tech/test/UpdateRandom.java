package com.tech.test;

import com.tech.dao.Pagination;
import com.tech.pojo.Latestlottery;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.RandomnumberService;

import java.io.PrintStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class UpdateRandom
{
  @Autowired
  @Qualifier("latestlotteryService")
  private LatestlotteryService latestlotteryService;
  @Autowired
  private RandomnumberService randomnumberService;
  
  @Test
  public void go()
  {
    for (int i = 0; i < 20; i++)
    {
      Pagination page = latestlotteryService.LatestAnnounced("", i, 20);
      List<Latestlottery> objList = (List<Latestlottery>)page.getList();
      System.err.println(objList.size() + "********");
      for (Latestlottery latestlottery : objList) {
        for (int j = 0; j < 100; j++)
        {
          System.err.println(latestlottery.getSpellbuyProductId() + "&&&&&&&&&&&&&&&&");
          Pagination datePage = latestlotteryService.getLotteryDetailBybuyerList(latestlottery.getSpellbuyProductId(), j, 20);
          List<?> dataList = datePage.getList();
          if (dataList.size() == 0) {
            break;
          }
          System.err.println(dataList.size() + "%%%%%%%%%%%%%%%");
          for (int k = 0; k < dataList.size(); k++)
          {
            Spellbuyrecord spellbuyrecord = (Spellbuyrecord)((Object[])dataList.get(k))[0];
            Randomnumber randomnumber = (Randomnumber)((Object[])dataList.get(k))[1];
            User user = (User)((Object[])dataList.get(k))[2];
            System.err.println(randomnumber.getId() + "++++++++++++++++++++");
            System.err.println(spellbuyrecord.getFkSpellbuyProductId() + "==================");
            randomnumber.setProductId(spellbuyrecord.getFkSpellbuyProductId());
            randomnumberService.add(randomnumber);
          }
        }
      }
    }
  }
}

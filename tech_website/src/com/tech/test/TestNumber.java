package com.tech.test;

import com.tech.pojo.Latestlottery;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.LatestlotteryService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;

import java.util.Iterator;
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
@Service("TestNumber")
public class TestNumber
{
  @Autowired
  private LatestlotteryService latestlotteryService;
  @Autowired
  private RandomnumberService randomnumberService;
  @Autowired
  private SpellbuyproductService spellbuyproductService;
  private List<Randomnumber> randomnumberList;
  private List<Latestlottery> latestlotteryList;
  
  @Test
  public void go()
  {
    List<Spellbuyproduct> spellbuyproductList = spellbuyproductService.query("from Spellbuyproduct s where s.spStatus=0");
    Iterator localIterator2;
    for (Iterator localIterator1 = spellbuyproductList.iterator(); localIterator1.hasNext(); localIterator2.hasNext())
    {
      Spellbuyproduct spellbuyproduct = (Spellbuyproduct)localIterator1.next();
      int price = spellbuyproduct.getSpellbuyPrice().intValue();
      randomnumberList = randomnumberService.query("from Randomnumber r where r.productId='" + spellbuyproduct.getSpellbuyProductId() + "'");
      localIterator2 = randomnumberList.iterator(); 
      Randomnumber randomnumber = (Randomnumber)localIterator2.next();
      String[] randoms = randomnumber.getRandomNumber().split(",");
      String numbers = "";
      int count = 0;
      for (String string : randoms)
      {
        int num = Integer.parseInt(string);
        num -= 10000000;
        if (num > price) {
          if (num > 1000) {
            num -= 1000;
          } else if (num > 100) {
            num -= 100;
          }
        }
        num += 10000000;
        
        numbers = numbers + num + ",";
      }
      numbers = numbers.substring(0, numbers.length() - 1);
      System.err.println("numbers:" + numbers);
      randomnumber.setRandomNumber(numbers);
      randomnumberService.add(randomnumber);
    }
  }
}

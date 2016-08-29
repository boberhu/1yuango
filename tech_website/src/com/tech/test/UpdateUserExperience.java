package com.tech.test;

import com.tech.pojo.User;
import com.tech.service.SpellbuyrecordService;
import com.tech.service.UserService;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class UpdateUserExperience
{
  @Autowired
  private UserService userService;
  @Autowired
  private SpellbuyrecordService spellbuyrecordService;
  
  @Test
  public void go()
  {
    List<User> userList = userService.loadAll();
    for (User user : userList) {
      if (user.getExperience().intValue() == 0)
      {
        BigDecimal i = spellbuyrecordService.findSumByBuyPriceByUserId(String.valueOf(user.getUserId()));
        if (i != null)
        {
          user.setExperience(Integer.valueOf(Integer.parseInt(String.valueOf(i)) * 10));
          userService.add(user);
        }
      }
    }
  }
}

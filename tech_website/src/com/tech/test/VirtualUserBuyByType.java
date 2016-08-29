package com.tech.test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tech.exception.RuleViolationException;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.SysConfigure;
import com.tech.pojo.User;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SysConfigureService;
import com.tech.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Lazy(false)
@Repository
public class VirtualUserBuyByType
{
  private static final Logger LOG = LoggerFactory.getLogger(VirtualUserBuyByType.class);
  private static final int[] RAND_BUYC_TBL = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
  /*private static final int[] RAND_BUYC_TBL = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 5, 1, 2, 4, 3, 5, 5, 1, 3, 2, 4, 5, 1, 1, 2, 2, 2, 8, 8, 6, 7, 10, 1, 
      2, 10, 2, 1, 5, 10, 2, 1, 20, 5, 20, 1, 2, 1, 15, 2, 1, 10, 1, 2, 15, 5, 1, 2, 16, 1, 6, 8, 4, 1, 2, 1, 2, 1, 2, 30, 2, 1, 
      30, 1, 2, 50, 1, 2, 1, 50, 1, 2, 50, 1, 2, 5, 1, 2, 1, 5, 2, 1, 100, 2, 22, 1, 11, 2, 5, 1, 2, 2, 1, 2, 3, 1, 4, 40, 5, 1, 
      2, 2, 4, 5, 6, 1, 1, 2, 1, 2, 2, 4, 3, 5, 6, 1, 1, 2, 2, 25, 6, 2, 5, 3, 2, 1, 2, 4, 7, 8, 1, 2, 3, 1, 2, 55, 1, 2, 1, 2, 3, 2, 
      1, 2, 1, 23, 2, 3, 7, 8, 7, 3, 3, 4, 1, 2, 2, 3, 1, 2, 2, 2, 2, 3, 2, 1, 2, 5, 1, 2, 500, 2, 3, 3, 2, 1, 1, 2, 3, 3, 2, 1, 2, 5, 6, 7, 
      2, 20, 3, 1, 2, 3, 1, 2, 2, 100, 1, 2, 6, 23, 8, 25, 1, 25, 6, 1, 2, 3, 2, 1, 1, 23, 3, 4, 1, 2, 2, 24, 4, 2, 1, 21, 2, 12, 10, 
      1, 2, 2, 1, 2, 3, 5, 6, 10, 1, 1, 2, 2, 1, 1, 2, 2, 20, 10, 5, 3, 1, 5, 1, 12, 6, 4, 5, 6, 1, 2, 3, 6, 10, 8, 1, 23, 4, 7, 2, 2 };
*/      
  private static volatile List<User> userList = null;
  
  @Autowired
  private UserService userService;
  @Autowired
  private SpellbuyproductService sbpService;
  @Autowired
  private SysConfigureService    confService;
  
  // props
  private volatile int buyTimes   = 50;
  private volatile boolean runned = true;
  
  @PostConstruct
  public void init()
  {
	  try{
		  final SysConfigure conf = confService.findById("1");
		  buyTimes = conf.getRobots();
		  LOG.info("Robots: {}", buyTimes);
	  }catch(final RuntimeException e){
		  LOG.error("{}", e);
	  }
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void goBuy()
  {
	if(runned == false){
		LOG.info("Robot sleeping!");
		return;
	}
    final Random random = new Random();
    //final int rand = random.nextInt(15);
    //if (rand != 5) {
    //  LOG.info("Don't buy for random {}, not 5", rand);
    //  return;
    //}
    // load all hot & buy-able product
    List<Spellbuyproduct> sbpList = sbpService.loadAllByType();
    final int pros = sbpList.size();
    if (pros > 0)
    {
      for (int i = 0; i < 2; i++) {
        Collections.shuffle(sbpList);
      }
      if (userList == null) {
    	// load all robots 
        userList = userService.loadAll();
        LOG.info("robots count: {}", userList.size());
      }
      // do-buying
      for (int i = 0, size = Math.min(pros, buyTimes); i < size; ++i) {
    	final Spellbuyproduct sbp = (Spellbuyproduct)sbpList.get(i);
    	final Integer pid = sbp.getFkProductId();
        Collections.shuffle(userList);
        for (int k = 1; k <= 1; ++k) {
          try
          {
            final User user = userList.get(random.nextInt(userList.size()));
            // buy-request
            final int[] buyNumbers = RAND_BUYC_TBL;
            int buyCount = buyNumbers[random.nextInt(buyNumbers.length)];
            // do-buy
            userService.buyVirtually(user, pid, buyCount);
            LOG.info("v-user buy: {}, {}", pid, buyCount);
          }
          catch (final RuleViolationException e)
          {
            LOG.warn("{}", e.getMessage());
          }catch (final RuntimeException e)
          {
         	LOG.error("{}", e);
      	  }
        }
      }
    }
  }

  public void setRobots(int robots) {
	  buyTimes = robots;
  }
  
  public int getRobots(){
	  return buyTimes;
  }

  public void startup() {
	  runned = true;
  }

  public void shutdown() {
	  runned = false;
  }
  
}

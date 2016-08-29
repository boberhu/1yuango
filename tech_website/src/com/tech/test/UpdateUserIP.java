package com.tech.test;

import com.tech.pojo.User;
import com.tech.service.UserService;
import com.tech.util.IPSeeker;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class UpdateUserIP
{
  @Autowired
  private UserService userService;
  public static IPSeeker seeker = new IPSeeker();
  Random random = new Random();
  
  @Test
  public void go()
  {
    List<User> userList = userService.loadAll();
    List<String> ipList = new ArrayList();
    ipList.add("221.13.152.");
    ipList.add("61.168.248.");
    ipList.add("222.40.36.");
    ipList.add("222.140.64.");
    ipList.add("222.140.127.");
    ipList.add("222.40.39.");
    int updateCount = 0;
    for (User user : userList) {
      if (user.getIpLocation().indexOf("河南省") == -1)
      {
        Collections.shuffle(ipList);
        String ip = (String)ipList.get(random.nextInt(ipList.size()));
        ip = ip + random.nextInt(255);
        String ipLocation = seeker.getAddress(ip);
        System.err.println(ip);
        System.err.println(ipLocation);
        user.setIpAddress(ip);
        user.setIpLocation(ipLocation);
        userService.add(user);
        updateCount++;
      }
    }
    System.err.println("updateCount:" + updateCount);
  }
}

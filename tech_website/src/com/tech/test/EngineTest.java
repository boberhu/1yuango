package com.tech.test;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EngineTest
{
  public static void main(String[] args)
  {
    new EngineTest().run();
  }
  
  public void run()
  {
    try
    {
      String separator = System.getProperty("file.separator");
      
      String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "account.txt";
      
      List<String> engineList = new ArrayList();
      
      engineList.add("http://www.baidu.com/s?wd=1%E5%85%83%E6%8B%8D%E8%B4%AD&rsv_bp=0&ch=&tn=baidu&bar=&rsv_spt=3&ie=utf-8&rsv_sug3=4&rsv_sug=0&rsv_sug1=4&rsv_sug4=51&inputT=5488");
      
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E6%8B%8D&f=8&rsv_bp=1&rsv_spt=3&wd=1%E5%85%83%E6%8B%8D&inputT=0");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&rsv_spt=3&wd=1%E5%85%83%E6%8B%8D&rsv_sug3=1&rsv_sug1=1&rsv_sug4=20&inputT=3520");
      engineList.add("http://www.baidu.com/link?url=jGs2GJqjJ4zBBpC8yDF8xDh8vibiRE26EyoEbodO");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E6%8B%8D%E8%B4%AD%E7%BD%91&f=8&rsv_bp=1&wd=%E6%8B%8D%E8%B4%AD&rsv_sug3=1&rsv_sug=0&rsv_sug1=1&rsv_sug4=17&inputT=590");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&wd=1%E5%85%83%E6%8B%8D%E8%B4%AD%E7%BD%91&rsv_n=2&rsv_sug3=1&rsv_sug1=1&rsv_sug4=12&inputT=710");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E6%8B%8D%E8%B4%AD&f=8&rsv_bp=1&wd=%E4%B8%80%E5%85%83%E6%8B%8D%E8%B4%AD+1ypg.com&rsv_sug3=7&rsv_sug1=6&rsv_sug4=61&inputT=3199");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&inputT=0");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=%E4%B8%80%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=1%E5%85%83%E8%B4%AD%E7%89%A9&rsv_sug3=1&rsv_sug1=1&rsv_sug4=9&inputT=224");
      engineList.add("http://www.baidu.com/s?ie=utf-8&bs=1%E5%85%83%E8%B4%AD%E7%89%A9&f=8&rsv_bp=1&wd=1%E5%85%83%E8%B4%AD%E7%89%A9&inputT=0");
      















      String ip = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "ip.txt";
      List<Proxy> proxyList = FollowTools.readProxyIP(ip);
      Collections.shuffle(proxyList);
      System.err.println("proxyList:" + proxyList.size());
      

      CountDownLatch countDownLatch = new CountDownLatch(engineList.size());
      
      EngineContainer engineContainer = new EngineContainer(engineList);
      
      ProxyContainer proxyContainer = new ProxyContainer(proxyList, countDownLatch);
      
      System.out.println(engineList.size());
      
      ExecutorService exec = Executors.newFixedThreadPool(10);
      for (int i = 0; i < 10; i++) {
        exec.submit(new EngineThread("抓取详细线程" + i, engineContainer, proxyContainer));
      }
      try
      {
        countDownLatch.await();
      }
      catch (Throwable e)
      {
        e.printStackTrace();
      }
      exec.shutdownNow();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}

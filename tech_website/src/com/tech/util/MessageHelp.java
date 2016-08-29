package com.tech.util;

import cn.emay.sdk.client.api.Client;
import java.io.PrintStream;

public class MessageHelp
{
  static final String SERIALNUMBER = "3SDK-EMY-0130-MCXOO";
  static final String KEY = "123456";
  static final String PASSWORD = "854141";
  
  public static int checkBalance()
  {
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    int a = 1000;
    try
    {
      Client sdkclient = new Client("3SDK-EMY-0130-MCXOO", "123456");
      sdkclient.registEx("854141");
      double balance = sdkclient.getBalance();
      System.out.println("余额:" + balance);
      sdkclient.closeChannel();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    return a;
  }
  
  public static int send(String[] mobile, String text)
  {
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    int a = 1000;
    try
    {
      Client sdkclient = new Client("3SDK-EMY-0130-MCXOO", "123456");
      int i = sdkclient.registEx("854141");
      System.out.println("注册函数结果:" + i);
      

      a = sdkclient.sendSMS(mobile, text, 3);
      System.out.println("短信发送结果:" + a);
      double balance = sdkclient.getBalance();
      System.out.println("余额:" + balance);
      double eachFee = sdkclient.getEachFee();
      System.out.println("每条短信费用:" + eachFee);
      sdkclient.closeChannel();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    return a;
  }
  
  public static void main(String[] args)
  {
    checkBalance();
  }
}

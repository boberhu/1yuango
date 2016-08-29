package com.tech.test;

import com.tech.util.EmailUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class TestEmail
{
  public static void main(String[] args)
  {
    String mail = "65615609@qq.com";
    String key = "";
    String html = "<table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border: #dddddd 1px solid; padding: 20px 0;\"><tbody><tr><td><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"border-bottom: #ff6600 2px solid; padding-bottom: 12px;\"><tbody><tr><td style=\"line-height: 22px; padding-left: 20px;\"><a target=\"_blank\" title=\"1元夺宝\" href=\"http://www.1ypg.com/\"><img width=\"230px\" border=\"0\" height=\"52\" src=\"http://img.1ypg.com/Images/mail_logo.gif\"></a></td><td align=\"right\" style=\"font-size: 12px; padding-right: 20px; padding-top: 30px;\"><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"http://www.1ypg.com/\">首页</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"http://www.1ypg.com/user/index.html\">我的1元夺宝</a><b style=\"width: 1px; height: 10px; vertical-align: -1px; font-size: 1px; background: #CACACA; display: inline-block; margin: 0 5px;\"></b><a style=\"color: #22aaff; text-decoration: none;\" target=\"_blank\" href=\"http://www.1ypg.com/help/index.html\">帮助</a></td></tr></tbody></table><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"padding: 0 20px;\"><tbody><tr><td style=\"font-size: 14px; color: #333333; height: 40px; line-height: 40px; padding-top: 10px;\">亲爱的 <b style=\"color: #333333; font-family: Arial;\"><a href=\"mailto:" + 
    










      mail + "\" target=\"_blank\">" + mail + "</a></b>：</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">您好！感谢您注册1元夺宝。</p></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px;\"><p style=\"text-indent: 2em; padding: 0; margin: 0;\">请点击下面的按钮，完成邮箱验证。</p></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"padding-top: 15px; padding-left: 28px;\"><a title=\"邮箱验证\" style=\"display: inline-block; padding: 0 25px; height: 28px; line-height: 28px; text-align: center; color: #ffffff; background: #ff7700; font-size: 12px; cursor: pointer; border-radius: 2px; text-decoration: none;\" target=\"_blank\" href=\"http://www.1ypg.com/emailok.html?key=" + key + "\">邮箱验证</a></td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td width=\"525\" style=\"font-size: 12px; color: #333333; line-height: 22px; padding-top: 20px;\">如果上面按钮不能点击或点击后没有反应，您还可以将以下链接复制到浏览器地址栏中访问完成验证。</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td width=\"525\" style=\"font-size: 12px; padding-top: 5px; word-break: break-all; word-wrap: break-word;\"><a style=\"font-family: Arial; color: #22aaff;\" target=\"_blank\" href=\"http://www.1ypg.com/emailok.html?key=" + key + "\">http://www.1ypg.com/emailok.html?key=" + key + "</a></td>" + 
      "</tr>" + 
      "</tbody></table>" + 
      "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"margin-top: 60px;\">" + 
      "<tbody><tr>" + 
      "<td style=\"font-size: 12px; color: #777777; line-height: 22px; border-bottom: #22aaff 2px solid; padding-bottom: 8px; padding-left: 20px;\">此邮件由系统自动发出，请勿回复！</td>" + 
      "</tr>" + 
      "<tr>" + 
      "<td style=\"font-size: 12px; color: #333333; line-height: 22px; padding: 8px 20px 0;\">感谢您对1元夺宝（<a style=\"color: #22aaff; font-family: Arial;\" target=\"_blank\" href=\"http://www.1ypg.com/\">http://www.1ypg.com</a>）的支持，祝您好运！</td>" + 
      "</tr>" + 
      


      "</tbody></table>" + 
      "</td>" + 
      "</tr>" + 
      "</tbody></table>";
    EmailUtil.sendEmail("service@1ypg.com", "epgwyc11", mail, "1元夺宝用户注册激活", html, null);
  }
  
  @Test
  public void go()
  {
    EmailUtil.sendEmail("service@1ypg.com", "epgwyc11", "65615609@qq.com", "1元夺宝用户注册激活", "<a href='http://www.1ypg.com' target='_blank'>点击这里激活帐户</a>", null);
  }
  
  public static void testBatchEmail()
  {
    EmailUtil.batchSendEmail("C:\\src.txt", "C:\\des.txt", "0411邮件测试主题", 
      "邮件内容,html格式邮件.<a href=\"www.baidu.com\">百度</a> 图片测试<img src=\"http://www.cardcmb.com/8/czyemail/20120331sqsm/edm_05.jpg\"><img>", 
      null);
  }
  
  public static void testSendMailByJavaMail()
  {
    long begin = System.currentTimeMillis();
    try
    {
      Map<String, String> mailMap = readFile2Map("C:\\1250+2500appKeyByUser.txt", "[|]");
      String str;
      for (Iterator localIterator = mailMap.keySet().iterator(); localIterator.hasNext(); str = (String)localIterator.next()) {}
    }
    catch (Exception e)
    {
      System.err.println("发送邮件失败:" + e.getMessage());
    }
    long end = System.currentTimeMillis();
    long tmp = (end - begin) / 1000L;
    System.err.println("==================>>>>>>>>>>>>>>>>>>>发送邮件共用" + tmp + "秒");
  }
  
  public static void testSendMailBySpring() {}
  
  public static Map<String, String> readFile2Map(String path, String regular)
    throws Exception
  {
    if (StringUtils.isBlank(regular)) {
      return null;
    }
    if (StringUtils.isBlank(path)) {
      return null;
    }
    Map<String, String> map = new HashMap();
    File file = new File(path);
    FileReader fReader = new FileReader(file);
    BufferedReader bReader = new BufferedReader(fReader);
    String temp = null;
    String[] tempArr = (String[])null;
    while ((temp = bReader.readLine()) != null)
    {
      if (StringUtils.isNotBlank(temp))
      {
        tempArr = temp.split(regular);
        if (tempArr.length > 1)
        {
          String username = tempArr[0];
          String password = tempArr[1];
          if ((StringUtils.isNotBlank(username)) && (StringUtils.isNotBlank(password))) {
            map.put(username.trim(), password.trim());
          }
        }
      }
      temp = null;
      tempArr = (String[])null;
    }
    bReader.close();
    fReader.close();
    file = null;
    return map;
  }
}

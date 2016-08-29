package com.tech.util;

import com.tech.util.email.EmailProtocol;
import com.tech.util.email.SendMail;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailUtil
{
  private static ApplicationContext ctx = null;
  public static JavaMailSenderImpl sender = null;
  public static ExecutorService pool = null;
  private static final Log log = LogFactory.getLog(EmailUtil.class);
  
  public static void sendEmail(String mailFrom, String password, String mailto, String subject, String content, List<String> attachList)
  {
    JavaMail javaMail = new JavaMail(mailFrom, password, mailto, subject, content, attachList);
    pool.execute(javaMail);
    pool.shutdown();
  }
  
  public static boolean sendEmail(String mailFrom, String password, String mailto, String subject, String content)
  {
    EmailProtocol eProtocol = new EmailProtocol(mailFrom);
    SendMail sendMail = new SendMail(mailFrom, password, eProtocol.getSmtpProtocol(), mailto, subject, content, null);
    return sendMail.sendMail();
  }
  
  public static void batchSendEmail(String srcFilePath, String desFilePath, String subject, String content, List<String> attachList)
  {
    if (StringUtil.isBlank(srcFilePath))
    {
      log.error("群发邮件时srcFilePath为空!");
      return;
    }
    if (StringUtil.isBlank(desFilePath))
    {
      log.error("群发邮件时desFilePath为空!");
      return;
    }
    Map<String, String> srcMap = null;
    Map<String, String> desMap = null;
    int i = 0;
    try
    {
      srcMap = FileIOUtil.readFile2Map(srcFilePath, "[|]");
      desMap = FileIOUtil.readFile2Map(desFilePath, "[|]");
      if ((srcMap != null) && (srcMap.size() > 0) && (desMap != null) && (desMap.size() > 0))
      {
        String mailFrom = null;
        String password = null;
        String[] srcArray = new String[srcMap.size()];
        srcMap.keySet().toArray(srcArray);
        for (String mailto : desMap.keySet())
        {
          if (i >= srcMap.size()) {
            i = 0;
          }
          mailFrom = srcArray[i];
          password = (String)srcMap.get(mailFrom);
          JavaMail javaMail = new JavaMail(mailFrom, password, mailto, subject, content, attachList);
          pool.execute(javaMail);
          i++;
        }
        pool.shutdown();
      }
      else
      {
        log.error("群发邮件时发件人或者收件人为空!");
      }
    }
    catch (IOException e)
    {
      log.error("群发邮件时读取文件失败!", e);
    }
  }
}

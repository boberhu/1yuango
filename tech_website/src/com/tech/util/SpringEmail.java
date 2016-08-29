package com.tech.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

class SpringEmail
  implements Runnable
{
  private final Log log = LogFactory.getLog(SpringEmail.class);
  JavaMailSenderImpl sender = null;
  String mailto = null;
  String subject = null;
  String content = null;
  List<String> attachList = null;
  
  public SpringEmail(JavaMailSenderImpl sender, String mailto, String subject, String content, List<String> attachList)
  {
    this.sender = sender;
    this.mailto = mailto;
    this.subject = subject;
    this.content = content;
    this.attachList = attachList;
  }
  
  public void run()
  {
    sendMailBySpring(sender, mailto, subject, content, attachList);
  }
  
  private synchronized boolean sendMailBySpring(JavaMailSenderImpl sender, String mailto, String subject, String content, List<String> attachList)
  {
    MimeMessage msg = sender.createMimeMessage();
    try
    {
      MimeMessageHelper helper = new MimeMessageHelper(msg, true, "GBK");
      helper.setFrom(sender.getUsername());
      helper.setTo(mailto);
      helper.setSubject(MimeUtility.encodeWord(subject));
      helper.setText("<META http-equiv=Content-Type content='text/html; charset=GBK'>" + content, true);
      if ((attachList != null) && (attachList.size() > 0)) {
        for (String str : attachList) {
          helper.addAttachment(MimeUtility.encodeWord(str), new File(str));
        }
      }
      sender.send(msg);
    }
    catch (MessagingException e)
    {
      log.error("邮件发送失败!!! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername(), e);
      return false;
    }
    catch (UnsupportedEncodingException e)
    {
      log.error("邮件发送失败!!! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername(), e);
      return false;
    }
    log.info("邮件发送成功! 标题:" + subject + "  收件人:" + mailto + "  发件人:" + sender.getUsername());
    return true;
  }
}

package com.tech.util.email;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveMail
{
  String host = "";
  String username = "";
  String password = "";
  
  public ReceiveMail(String host, String username, String password)
  {
    this.host = host;
    this.username = username;
    this.password = password;
  }
  
  public Folder receiveMail()
    throws MessagingException
  {
    Properties props = new Properties();
    
    props.put("mail.smtp.port", "25");
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.store.protocol", "pop3");
    
    Session session = Session.getDefaultInstance(props, null);
    
    Store store = session.getStore("pop3");
    store.connect(host, username, password);
    
    Folder folder = store.getFolder("inbox");
    folder.open(1);
    
    return folder;
  }
}

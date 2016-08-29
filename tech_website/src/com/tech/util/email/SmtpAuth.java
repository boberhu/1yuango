package com.tech.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuth
  extends Authenticator
{
  private String user;
  private String password;
  
  public void setUserinfo(String getuser, String getpassword)
  {
    user = getuser;
    password = getpassword;
  }
  
  protected PasswordAuthentication getPasswordAuthentication()
  {
    return new PasswordAuthentication(user, password);
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getUser()
  {
    return user;
  }
  
  public void setUser(String user)
  {
    this.user = user;
  }
}

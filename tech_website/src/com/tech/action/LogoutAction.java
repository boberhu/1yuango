package com.tech.action;

import org.springframework.stereotype.Component;

@Component("LogoutAction")
public class LogoutAction extends BaseAction
{
  private static final long serialVersionUID = 5411610776024806651L;
  
  public String index()
  {
    return "logout";
  }
}

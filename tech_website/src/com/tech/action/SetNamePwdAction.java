package com.tech.action;

import org.springframework.stereotype.Component;

@Component("SetNamePwdAction")
public class SetNamePwdAction extends BaseAction
{
  private static final long serialVersionUID = -3714908489142137768L;
  
  public String index()
  {
    return "index";
  }
  
  public String setNewPwd()
  {
    return null;
  }
}

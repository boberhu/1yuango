package com.tech.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCounterListener
  implements HttpSessionListener
{
  public void sessionCreated(HttpSessionEvent hse) {}
  
  public void sessionDestroyed(HttpSessionEvent hse) {}
}

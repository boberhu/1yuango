package com.tech.util;

import java.io.IOException;
import java.io.PrintStream;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

public class AliyunOcsSampleHelp
{
  static final String host = "127.0.0.1";
  static final String port = "11211";
  static final String username = "f652646d6f0b11e4";
  static final String password = "983e_d0d7";
  public static MemcachedClient iMemcachedCache = null;
  
  public static MemcachedClient getIMemcachedCache()
  {
    if (iMemcachedCache != null) {
      return iMemcachedCache;
    }
    try
    {
      //AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler("f652646d6f0b11e4", "983e_d0d7"));
      String addr = ConfigUtil.getValue("memcached", "127.0.0.1:11211");
      iMemcachedCache = new MemcachedClient(new ConnectionFactoryBuilder().setProtocol(ConnectionFactoryBuilder.Protocol.BINARY).build(), 
      AddrUtil.getAddresses(addr));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return iMemcachedCache;
  }
  
  public static void main(String[] args)
  {
    getIMemcachedCache().set("ocs111", 5, "test111");
    System.err.println(getIMemcachedCache().get("ocs111"));
  }
}

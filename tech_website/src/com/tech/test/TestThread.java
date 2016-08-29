package com.tech.test;

import java.io.PrintStream;

public class TestThread
{
  static final ThreadBean tb = new ThreadBean();
  
  public static void main(String[] args)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          TestThread.tb.makeStart();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }.start();
    System.err.println("end");
  }
}

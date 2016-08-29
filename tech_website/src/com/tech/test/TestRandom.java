package com.tech.test;

import com.tech.util.RandomUtil;

import java.io.PrintStream;

public class TestRandom
{
  public static void main(String[] args)
  {
    String strList = RandomUtil.random(5);
    System.err.println(strList);
  }
}

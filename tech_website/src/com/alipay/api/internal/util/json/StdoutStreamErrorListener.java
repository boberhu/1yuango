package com.alipay.api.internal.util.json;

import java.io.PrintStream;

public class StdoutStreamErrorListener
  extends BufferErrorListener
{
  public void end()
  {
    System.out.print(buffer.toString());
  }
}

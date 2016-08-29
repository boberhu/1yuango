package com.tech.util;

import com.blogspot.radialmind.html.HTMLParser;
import com.blogspot.radialmind.html.HandlingException;
import com.blogspot.radialmind.xss.XSSFilter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XssprotectUtil
{
  public static String protectAgainstXSS(String html)
  {
    StringReader reader = new StringReader(html);
    StringWriter writer = new StringWriter();
    String text = null;
    try
    {
      HTMLParser.process(reader, writer, new XSSFilter(), true);
      
      text = writer.toString();
    }
    catch (HandlingException localHandlingException)
    {
      try
      {
        writer.close();
        reader.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    finally
    {
      try
      {
        writer.close();
        reader.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return text;
  }
}

package com.tech.test;

import com.blogspot.radialmind.html.HTMLParser;
import com.blogspot.radialmind.html.HandlingException;
import com.blogspot.radialmind.xss.XSSFilter;
import com.tech.util.HTMLFilter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;

public class TestHtml
{
  public static void main(String[] args)
  {
    String html = "gego";
    
    System.err.println(new HTMLFilter().filter(html));
  }
  
  public static String protectAgainstXSS(String html)
  {
    StringReader reader = new StringReader(html);
    System.err.println(reader);
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

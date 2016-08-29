package com.tech.util;

import java.io.IOException;
import java.io.PrintStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CaipiaoUtil
{
  public static String caiNumber()
    throws IOException
  {
    String str = "";
    Document document = Jsoup.connect("http://f.apiplus.cn/cqssc-1.json").timeout(60000).get();
    JSONObject array = JSONObject.fromObject(document.text());
    JSONArray date = (JSONArray)array.get("data");
    array = JSONObject.fromObject(date.get(0));
    str = str + array.getString("expect") + "|" + array.getString("opencode").replaceAll(",", "");
    return str;
  }
  
  public static void main(String[] args)
    throws IOException
  {
    String str = "";
    Document document = Jsoup.connect("http://f.apiplus.cn/cqssc-1.json").timeout(60000).get();
    JSONObject array = JSONObject.fromObject(document.text());
    JSONArray date = (JSONArray)array.get("data");
    array = JSONObject.fromObject(date.get(0));
    str = str + array.getString("expect") + "|" + array.getString("opencode").replaceAll(",", "");
    System.err.println(str);
    System.err.println(str.split("\\|")[1]);
    System.err.println(str.split("\\|")[0]);
  }
}

package com.tech.test;

import java.io.IOException;
import java.io.PrintStream;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientTest
{
  private static DefaultHttpClient httpClient;
  private static HttpGet httpGet;
  private HttpPost httpPost;
  
  public static void main(String[] args)
    throws ClientProtocolException, IOException
  {
    httpClient = new DefaultHttpClient();
    HttpParams params = httpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(params, 60000);
    HttpConnectionParams.setSoTimeout(params, 60000);
    String index = "http://www.1ypg.com";
    httpGet = new HttpGet(index);
    ResponseHandler<String> responseHandler = new BasicResponseHandler();
    String content = (String)httpClient.execute(httpGet, responseHandler);
    Document document = Jsoup.parse(content);
    Elements elements = document.select("a");
    for (Element element : elements) {
      try
      {
        String url = element.attr("href");
        if (url.indexOf("http://www.1ypg.com") != -1)
        {
          System.err.println(url);
          go(url);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static void go(String url)
    throws ClientProtocolException, IOException
  {
    httpClient = new DefaultHttpClient();
    HttpParams params = httpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(params, 60000);
    HttpConnectionParams.setSoTimeout(params, 60000);
    httpGet = new HttpGet(url);
    ResponseHandler<String> responseHandler = new BasicResponseHandler();
    String content = (String)httpClient.execute(httpGet, responseHandler);
    System.err.println(content);
  }
}

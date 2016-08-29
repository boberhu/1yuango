package com.wangyin.wepaypc.util;

import java.io.PrintStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpsClientUtil
{
  public static String sendRequest(String url, String json)
  {
    int timeout = 5000;
    long responseLength = 0L;
    String responseContent = null;
    String strResult = "";
    HttpClient httpClient = new DefaultHttpClient();
    wrapClient(httpClient);
    try
    {
      HttpParams httpParams = httpClient.getParams();
      httpParams.setIntParameter("http.connection.timeout", timeout);
      httpParams.setIntParameter("http.socket.timeout", timeout);
      HttpPost httpPost = new HttpPost(url);
      StringEntity se = new StringEntity(json, "UTF-8");
      se.setContentType(new BasicHeader("Content-Type", "application/json"));
      se.setContentEncoding("UTF-8");
      httpPost.setEntity(se);
      HttpResponse response = httpClient.execute(httpPost);
      if (response.getStatusLine().getStatusCode() == 200) {
        strResult = EntityUtils.toString(response.getEntity());
      }
      System.out.println("请求地址: " + httpPost.getURI());
      System.out.println("响应状态: " + response.getStatusLine());
      System.out.println("响应长度: " + responseLength);
      System.out.println("响应内容: " + responseContent);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (httpClient != null) {
        httpClient.getConnectionManager().shutdown();
      }
    }
    return strResult;
  }
  
  public static void wrapClient(HttpClient httpClient)
  {
    try
    {
      X509TrustManager xtm = new X509TrustManager()
      {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException
        {}
        
        public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException
        {}
        
        public X509Certificate[] getAcceptedIssuers()
        {
          return null;
        }
      };
      SSLContext ctx = SSLContext.getInstance("TLS");
      
      ctx.init(null, new TrustManager[] { xtm }, null);
      
      SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
      
      httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}

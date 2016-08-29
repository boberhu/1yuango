package com.tech.weixin.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientCustomSSL
{
  static CloseableHttpClient httpclient = null;
  
  public static final CloseableHttpClient weixinClientCustomSSL()
    throws Exception
  {
    if (httpclient != null) {
      return httpclient;
    }
    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    FileInputStream instream = new FileInputStream(new File("D:/apiclient_cert.p12"));
    try
    {
      keyStore.load(instream, "10057251".toCharArray());
    }
    finally
    {
      instream.close();
    }
    SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "10057251".toCharArray()).build();
    
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
    return httpclient;
  }
  
  public static final void main(String[] args)
    throws Exception
  {
    weixinClientCustomSSL();
    HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
    System.out.println("executing request" + httpget.getRequestLine());
    CloseableHttpResponse response = httpclient.execute(httpget);
    HttpEntity entity = response.getEntity();
    System.out.println("----------------------------------------");
    System.out.println(response.getStatusLine());
    if (entity != null)
    {
      System.out.println("Response content length: " + entity.getContentLength());
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
      String text;
      while ((text = bufferedReader.readLine()) != null)
      {
        System.out.println(text);
      }
    }
    EntityUtils.consume(entity);
  }
}

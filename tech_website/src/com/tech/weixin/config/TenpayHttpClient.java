package com.tech.weixin.config;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class TenpayHttpClient
{
  private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";
  private static final String JKS_CA_FILENAME = "tenpay_cacert.jks";
  private static final String JKS_CA_ALIAS = "tenpay";
  private static final String JKS_CA_PASSWORD = "";
  private File caFile;
  private File certFile;
  private String certPasswd;
  private String reqContent;
  private String resContent;
  private String method;
  private String errInfo;
  private int timeOut;
  private int responseCode;
  private String charset;
  private InputStream inputStream;
  
  public TenpayHttpClient()
  {
    caFile = null;
    certFile = null;
    certPasswd = "";
    
    reqContent = "";
    resContent = "";
    method = "POST";
    errInfo = "";
    timeOut = 30;
    
    responseCode = 0;
    charset = "GBK";
    
    inputStream = null;
  }
  
  public void setCertInfo(File certFile, String certPasswd)
  {
    this.certFile = certFile;
    this.certPasswd = certPasswd;
  }
  
  public void setCaInfo(File caFile)
  {
    this.caFile = caFile;
  }
  
  public void setReqContent(String reqContent)
  {
    this.reqContent = reqContent;
  }
  
  public String getResContent()
  {
    try
    {
      doResponse();
    }
    catch (IOException e)
    {
      errInfo = e.getMessage();
    }
    return resContent;
  }
  
  public void setMethod(String method)
  {
    this.method = method;
  }
  
  public String getErrInfo()
  {
    return errInfo;
  }
  
  public void setTimeOut(int timeOut)
  {
    this.timeOut = timeOut;
  }
  
  public int getResponseCode()
  {
    return responseCode;
  }
  
  public boolean call()
  {
    boolean isRet = false;
    if ((caFile == null) && (certFile == null))
    {
      try
      {
        callHttp();
        isRet = true;
      }
      catch (IOException e)
      {
        errInfo = e.getMessage();
      }
      return isRet;
    }
    try
    {
      callHttps();
      isRet = true;
    }
    catch (UnrecoverableKeyException e)
    {
      errInfo = e.getMessage();
    }
    catch (KeyManagementException e)
    {
      errInfo = e.getMessage();
    }
    catch (CertificateException e)
    {
      errInfo = e.getMessage();
    }
    catch (KeyStoreException e)
    {
      errInfo = e.getMessage();
    }
    catch (NoSuchAlgorithmException e)
    {
      errInfo = e.getMessage();
    }
    catch (IOException e)
    {
      errInfo = e.getMessage();
    }
    return isRet;
  }
  
  protected void callHttp()
    throws IOException
  {
    if ("POST".equals(method.toUpperCase()))
    {
      String url = HttpClientUtil.getURL(reqContent);
      String queryString = HttpClientUtil.getQueryString(reqContent);
      byte[] postData = queryString.getBytes(charset);
      httpPostMethod(url, postData);
      
      return;
    }
    httpGetMethod(reqContent);
  }
  
  public boolean callHttpPost(String url, String postdata)
  {
    boolean flag = false;
    try
    {
      byte[] postData = postdata.getBytes(charset);
      httpPostMethod(url, postData);
      flag = true;
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
    return flag;
  }
  
  protected void callHttps()
    throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException
  {
    String caPath = caFile.getParent();
    
    File jksCAFile = new File(caPath + "/" + 
      "tenpay_cacert.jks");
    if (!jksCAFile.isFile())
    {
      X509Certificate cert = (X509Certificate)
        HttpClientUtil.getCertificate(caFile);
      
      FileOutputStream out = new FileOutputStream(jksCAFile);
      

      HttpClientUtil.storeCACert(cert, "tenpay", 
        "", out);
      
      out.close();
    }
    FileInputStream trustStream = new FileInputStream(jksCAFile);
    FileInputStream keyStream = new FileInputStream(certFile);
    
    SSLContext sslContext = HttpClientUtil.getSSLContext(trustStream, 
      "", keyStream, certPasswd);
    

    keyStream.close();
    trustStream.close();
    if ("POST".equals(method.toUpperCase()))
    {
      String url = HttpClientUtil.getURL(reqContent);
      String queryString = HttpClientUtil.getQueryString(reqContent);
      byte[] postData = queryString.getBytes(charset);
      
      httpsPostMethod(url, postData, sslContext);
      
      return;
    }
    httpsGetMethod(reqContent, sslContext);
  }
  
  protected void httpPostMethod(String url, byte[] postData)
    throws IOException
  {
    HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);
    
    doPost(conn, postData);
  }
  
  protected void httpGetMethod(String url)
    throws IOException
  {
    HttpURLConnection httpConnection = 
      HttpClientUtil.getHttpURLConnection(url);
    
    setHttpRequest(httpConnection);
    
    httpConnection.setRequestMethod("GET");
    
    responseCode = httpConnection.getResponseCode();
    
    inputStream = httpConnection.getInputStream();
  }
  
  protected void httpsGetMethod(String url, SSLContext sslContext)
    throws IOException
  {
    SSLSocketFactory sf = sslContext.getSocketFactory();
    
    HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);
    
    conn.setSSLSocketFactory(sf);
    
    doGet(conn);
  }
  
  protected void httpsPostMethod(String url, byte[] postData, SSLContext sslContext)
    throws IOException
  {
    SSLSocketFactory sf = sslContext.getSocketFactory();
    
    HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);
    
    conn.setSSLSocketFactory(sf);
    
    doPost(conn, postData);
  }
  
  protected void setHttpRequest(HttpURLConnection httpConnection)
  {
    httpConnection.setConnectTimeout(timeOut * 1000);
    

    httpConnection.setRequestProperty("User-Agent", 
      "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)");
    

    httpConnection.setUseCaches(false);
    

    httpConnection.setDoInput(true);
    httpConnection.setDoOutput(true);
  }
  
  protected void doResponse()
    throws IOException
  {
    if (inputStream == null) {
      return;
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(
      inputStream, charset));
    

    resContent = HttpClientUtil.bufferedReader2String(reader);
    

    reader.close();
    

    inputStream.close();
  }
  
  protected void doPost(HttpURLConnection conn, byte[] postData)
    throws IOException
  {
    conn.setRequestMethod("POST");
    

    setHttpRequest(conn);
    

    conn.setRequestProperty("Content-Type", 
      "application/x-www-form-urlencoded");
    
    BufferedOutputStream out = new BufferedOutputStream(
      conn.getOutputStream());
    
    int len = 1024;
    HttpClientUtil.doOutput(out, postData, 1024);
    

    out.close();
    

    responseCode = conn.getResponseCode();
    

    inputStream = conn.getInputStream();
  }
  
  protected void doGet(HttpURLConnection conn)
    throws IOException
  {
    conn.setRequestMethod("GET");
    

    setHttpRequest(conn);
    

    responseCode = conn.getResponseCode();
    

    inputStream = conn.getInputStream();
  }
}

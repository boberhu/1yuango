package com.alipay.api;

import com.alipay.api.internal.parser.json.ObjectJsonParser;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.internal.util.AlipayLogger;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.RequestParametersHolder;
import com.alipay.api.internal.util.StreamUtil;
import com.alipay.api.internal.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AlipayMobilePublicMultiMediaClient
  implements AlipayClient
{
  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final String METHOD_GET = "GET";
  private String serverUrl;
  private String appId;
  private String privateKey;
  private String prodCode;
  private String format = "json";
  private String sign_type = "RSA";
  private String charset;
  private int connectTimeout = 3000;
  private int readTimeout = 15000;
  
  private static class DefaultTrustManager
    implements X509TrustManager
  {
    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
    
    public void checkClientTrusted(X509Certificate[] chain, String authType)
      throws CertificateException
    {}
    
    public void checkServerTrusted(X509Certificate[] chain, String authType)
      throws CertificateException
    {}
  }
  
  static
  {
    Security.setProperty("jdk.certpath.disabledAlgorithms", "");
  }
  
  public AlipayMobilePublicMultiMediaClient(String serverUrl, String appId, String privateKey)
  {
    this.serverUrl = serverUrl;
    this.appId = appId;
    this.privateKey = privateKey;
  }
  
  public AlipayMobilePublicMultiMediaClient(String serverUrl, String appId, String privateKey, String format)
  {
    this(serverUrl, appId, privateKey);
    this.format = format;
  }
  
  public AlipayMobilePublicMultiMediaClient(String serverUrl, String appId, String privateKey, String format, String charset)
  {
    this(serverUrl, appId, privateKey);
    this.format = format;
    this.charset = charset;
  }
  
  public <T extends AlipayResponse> T execute(AlipayRequest<T> request)
    throws AlipayApiException
  {
    return execute(request, null);
  }
  
  public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken)
    throws AlipayApiException
  {
    return _execute(request, accessToken);
  }
  
  private <T extends AlipayResponse> T _execute(AlipayRequest<T> request, String authToken)
    throws AlipayApiException
  {
    return doGet(request);
  }
  
  public <T extends AlipayResponse> T doGet(AlipayRequest<T> request)
    throws AlipayApiException
  {
    if (StringUtils.isEmpty(charset)) {
      charset = DEFAULT_CHARSET;
    }
    RequestParametersHolder requestHolder = new RequestParametersHolder();
    AlipayHashMap appParams = new AlipayHashMap(request.getTextParams());
    requestHolder.setApplicationParams(appParams);
    
    AlipayHashMap protocalMustParams = new AlipayHashMap();
    protocalMustParams.put("method", request.getApiMethodName());
    protocalMustParams.put("version", request.getApiVersion());
    protocalMustParams.put("app_id", appId);
    protocalMustParams.put("sign_type", sign_type);
    protocalMustParams.put("charset", charset);
    
    Long timestamp = Long.valueOf(System.currentTimeMillis());
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    protocalMustParams.put("timestamp", df.format(new Date(timestamp.longValue())));
    protocalMustParams.put("format", format);
    requestHolder.setProtocalMustParams(protocalMustParams);
    
    String signContent = AlipaySignature.getSignatureContent(requestHolder);
    
    protocalMustParams.put("sign", 
      AlipaySignature.rsaSign(signContent, privateKey, charset));
    
    AlipayMobilePublicMultiMediaDownloadResponse rsp = null;
    try
    {
      if ((request instanceof AlipayMobilePublicMultiMediaDownloadRequest))
      {
        OutputStream outputStream = ((AlipayMobilePublicMultiMediaDownloadRequest)request)
          .getOutputStream();
        rsp = doGet(serverUrl, requestHolder, charset, connectTimeout, readTimeout, 
          outputStream);
      }
    }
    catch (IOException e)
    {
      throw new AlipayApiException(e);
    }
    return (T)rsp;
  }
  
  public static AlipayMobilePublicMultiMediaDownloadResponse doGet(String url, RequestParametersHolder requestHolder, String charset, int connectTimeout, int readTimeout, OutputStream output)
    throws AlipayApiException, IOException
  {
    HttpURLConnection conn = null;
    AlipayMobilePublicMultiMediaDownloadResponse response = null;
    try
    {
      Map<String, String> params = new TreeMap();
      AlipayHashMap appParams = requestHolder.getApplicationParams();
      if ((appParams != null) && (appParams.size() > 0)) {
        params.putAll(appParams);
      }
      AlipayHashMap protocalMustParams = requestHolder.getProtocalMustParams();
      if ((protocalMustParams != null) && (protocalMustParams.size() > 0)) {
        params.putAll(protocalMustParams);
      }
      AlipayHashMap protocalOptParams = requestHolder.getProtocalOptParams();
      if ((protocalOptParams != null) && (protocalOptParams.size() > 0)) {
        params.putAll(protocalOptParams);
      }
      String ctype = "application/x-www-form-urlencoded;charset=" + charset;
      String query = buildQuery(params, charset);
      try
      {
        conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype);
        conn.setConnectTimeout(connectTimeout);
        conn.setReadTimeout(readTimeout);
      }
      catch (IOException e)
      {
        Map<String, String> map = getParamsFromUrl(url);
        AlipayLogger.logCommError(e, url, (String)map.get("app_key"), (String)map.get("method"), params);
        throw e;
      }
      try
      {
        if (conn.getResponseCode() == 200)
        {
          if ((conn.getContentType() != null) && 
            (conn.getContentType().toLowerCase().contains("text/plain")))
          {
            String rsp = getResponseAsString(conn);
            ObjectJsonParser<AlipayMobilePublicMultiMediaDownloadResponse> parser = new ObjectJsonParser(
              AlipayMobilePublicMultiMediaDownloadResponse.class);
            response = (AlipayMobilePublicMultiMediaDownloadResponse)parser.parse(rsp);
            response.setBody(rsp);
            response.setParams(appParams);
            return response;
          }
          StreamUtil.io(conn.getInputStream(), output);
          response = new AlipayMobilePublicMultiMediaDownloadResponse();
          response.setCode("200");
          response.setMsg("成功");
          response
            .setBody("{\"alipay_mobile_public_multimedia_download_response\":{\"code\":200,\"msg\":\"成功\"}}");
          response.setParams(appParams);
        }
        else
        {
          response = new AlipayMobilePublicMultiMediaDownloadResponse();
          response.setCode(String.valueOf(conn.getResponseCode()));
          response.setMsg(conn.getResponseMessage());
          response.setParams(appParams);
        }
      }
      catch (IOException e)
      {
        Map<String, String> map = getParamsFromUrl(url);
        AlipayLogger.logCommError(e, conn, (String)map.get("app_key"), (String)map.get("method"), params);
        throw e;
      }
    }
    finally
    {
      if (conn != null) {
        conn.disconnect();
      }
      if (output != null) {
        output.close();
      }
    }
    return response;
  }
  
  private static HttpURLConnection getConnection(URL url, String method, String ctype)
    throws IOException
  {
    HttpURLConnection conn = null;
    if ("https".equals(url.getProtocol()))
    {
      SSLContext ctx = null;
      try
      {
        ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, 
          new SecureRandom());
      }
      catch (Exception e)
      {
        throw new IOException(e);
      }
      HttpsURLConnection connHttps = (HttpsURLConnection)url.openConnection();
      connHttps.setSSLSocketFactory(ctx.getSocketFactory());
      connHttps.setHostnameVerifier(new HostnameVerifier()
      {
        public boolean verify(String hostname, SSLSession session)
        {
          return false;
        }
      });
      conn = connHttps;
    }
    else
    {
      conn = (HttpURLConnection)url.openConnection();
    }
    conn.setRequestMethod(method);
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
    conn.setRequestProperty("User-Agent", "aop-sdk-java");
    conn.setRequestProperty("Content-Type", ctype);
    return conn;
  }
  
  protected static String getResponseAsString(HttpURLConnection conn)
    throws IOException
  {
    String charset = getResponseCharset(conn.getContentType());
    InputStream es = conn.getErrorStream();
    if (es == null) {
      return getStreamAsString(conn.getInputStream(), charset);
    }
    String msg = getStreamAsString(es, charset);
    if (StringUtils.isEmpty(msg)) {
      throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
    }
    throw new IOException(msg);
  }
  
  private static String getStreamAsString(InputStream stream, String charset)
    throws IOException
  {
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
      StringWriter writer = new StringWriter();
      
      char[] chars = new char[256];
      int count = 0;
      while ((count = reader.read(chars)) > 0) {
        writer.write(chars, 0, count);
      }
      return writer.toString();
    }
    finally
    {
      if (stream != null) {
        stream.close();
      }
    }
  }
  
  private static String getResponseCharset(String ctype)
  {
    String charset = DEFAULT_CHARSET;
    if (!StringUtils.isEmpty(ctype))
    {
      String[] params = ctype.split(";");
      for (String param : params)
      {
        param = param.trim();
        if (param.startsWith("charset"))
        {
          String[] pair = param.split("=", 2);
          if ((pair.length != 2) || 
            (StringUtils.isEmpty(pair[1]))) {
            break;
          }
          charset = pair[1].trim();
          

          break;
        }
      }
    }
    return charset;
  }
  
  private static Map<String, String> getParamsFromUrl(String url)
  {
    Map<String, String> map = null;
    if ((url != null) && (url.indexOf('?') != -1)) {
      map = splitUrlQuery(url.substring(url.indexOf('?') + 1));
    }
    if (map == null) {
      map = new HashMap();
    }
    return map;
  }
  
  private static URL buildGetUrl(String strUrl, String query)
    throws IOException
  {
    URL url = new URL(strUrl);
    if (StringUtils.isEmpty(query)) {
      return url;
    }
    if (StringUtils.isEmpty(url.getQuery()))
    {
      if (strUrl.endsWith("?")) {
        strUrl = strUrl + query;
      } else {
        strUrl = strUrl + "?" + query;
      }
    }
    else if (strUrl.endsWith("&")) {
      strUrl = strUrl + query;
    } else {
      strUrl = strUrl + "&" + query;
    }
    return new URL(strUrl);
  }
  
  public static String buildQuery(Map<String, String> params, String charset)
    throws IOException
  {
    if ((params == null) || (params.isEmpty())) {
      return null;
    }
    StringBuilder query = new StringBuilder();
    Set<Map.Entry<String, String>> entries = params.entrySet();
    boolean hasParam = false;
    for (Map.Entry<String, String> entry : entries)
    {
      String name = (String)entry.getKey();
      String value = (String)entry.getValue();
      if (StringUtils.areNotEmpty(new String[] { name, value }))
      {
        if (hasParam) {
          query.append("&");
        } else {
          hasParam = true;
        }
        query.append(name).append("=").append(URLEncoder.encode(value, charset));
      }
    }
    return query.toString();
  }
  
  public static Map<String, String> splitUrlQuery(String query)
  {
    Map<String, String> result = new HashMap();
    
    String[] pairs = query.split("&");
    if ((pairs != null) && (pairs.length > 0)) {
      for (String pair : pairs)
      {
        String[] param = pair.split("=", 2);
        if ((param != null) && (param.length == 2)) {
          result.put(param[0], param[1]);
        }
      }
    }
    return result;
  }
}

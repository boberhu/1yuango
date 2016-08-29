package com.tech.proto.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http request proxy.
 * 
 * @author pzp@maihesoft.com
 * @date 2015-05-25
 *
 */
public class Proxy {
	private final static Logger LOG = LoggerFactory.getLogger(Proxy.class);
	//const
	public final static int DEFAULT_CONNECT_TIMEOUT = 15000;
	public final static int DEFAULT_SO_TIMEOUT      = 30000;
	public final static int DEFAULT_BUFFER_SIZE     = 256;
	public final static int DEFAULT_MAX_CONNECTIONS = 200;
	
	private final static String CS_PREFIX  = "charset=";
	private final static int CS_PREFIX_LEN = CS_PREFIX.length();
	
    // 数据编码格式 
    private final String encoding;
	// client
    private final HttpClient client;
    // buffer
    private final int bufferSize;
    
    public Proxy(){
    	this("UTF-8", DEFAULT_CONNECT_TIMEOUT, DEFAULT_SO_TIMEOUT, 
    			DEFAULT_BUFFER_SIZE, DEFAULT_MAX_CONNECTIONS);
    }
    
    public Proxy(final String encoding){
    	this(encoding, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SO_TIMEOUT, 
    			DEFAULT_BUFFER_SIZE, DEFAULT_MAX_CONNECTIONS);
    }
    
    public Proxy(final int bufferSize){
    	this("UTF-8", DEFAULT_CONNECT_TIMEOUT, DEFAULT_SO_TIMEOUT, 
    			bufferSize, DEFAULT_MAX_CONNECTIONS);
    }
    
    public Proxy(final String encoding, final int connectTimeout, final int soTimeout){
    	this(encoding, connectTimeout, soTimeout, 
    			DEFAULT_BUFFER_SIZE, DEFAULT_MAX_CONNECTIONS);
    }
    
    public Proxy(final String encoding, final int connectTimeout, final int soTimeout,
    		final int bufferSize){
    	this(encoding, connectTimeout, soTimeout, 
    			bufferSize, DEFAULT_MAX_CONNECTIONS);
    }
    
    public Proxy(final String encoding, final int connectTimeout, final int soTimeout, 
    		final int bufferSize, final int maxConnections){
    	this.encoding = encoding;
    	// connect-parameters
    	final HttpConnectionManagerParams mp = new HttpConnectionManagerParams();
    	mp.setConnectionTimeout(connectTimeout);
    	mp.setSoTimeout(soTimeout);
    	mp.setStaleCheckingEnabled(true);
    	mp.setTcpNoDelay(true);
    	mp.setMaxTotalConnections(maxConnections);
    	final HttpConnectionManager mgr = new SimpleHttpConnectionManager();
    	mgr.setParams(mp);
    	// client-init
    	this.client = new HttpClient(mgr);
    	// client-parameters
    	final HttpClientParams cparams = new HttpClientParams();
    	// 设置httpClient的连接超时，对连接管理器设置的连接超时是无用的
    	cparams.setConnectionManagerTimeout(connectTimeout);
    	this.client.setParams(cparams);
    	this.bufferSize = bufferSize;
    }
    
    public HttpClient getClient(){
        return client;  
    } 
    
    public String getEncoding() {
		return encoding;
	}
    
    public String post(final String url, final Map<String, String> headers, final Map<String, String> data,
    		final String encoding) throws HttpException{
    	// post方式  
        final PostMethod post = new PostMethod(url);
        // headers
        setHeaders(post, headers);
        // content
        if(data != null){ 
        	Set<Map.Entry<String, String>> postset = data.entrySet();  
            final NameValuePair[] params = new NameValuePair[postset.size()];  
            int i = 0;  
            for(Iterator<Map.Entry<String, String>> it = postset.iterator(); it.hasNext();){  
            	Map.Entry<String, String> p = it.next();  
            	params[i++] = new NameValuePair(p.getKey(), p.getValue());  
            }  
            post.setRequestBody(params);
        } 
        try {  
        	return (execute(post, encoding));
        }finally{
        	post.releaseConnection();  
        }
    }
    
    public String post(final String url, final Map<String, String> data, final String encoding) 
    		throws HttpException{
    	return post(url, null, data, encoding);
    }
    
	public String post(final String url, final Map<String, String> headers, final String data,
    		final String encoding) throws HttpException{
    	// post方式  
        final PostMethod post = new PostMethod(url);
        // headers
        setHeaders(post, headers);
        // content
        if(data != null){
            try {
            	final RequestEntity entity =
            			new ByteArrayRequestEntity(data.getBytes(this.encoding));
				post.setRequestEntity(entity);
			} catch (final UnsupportedEncodingException e) {}
        } 
        try {  
        	return (execute(post, encoding));
        }finally{
        	post.releaseConnection();  
        }
    }
    
    public String post(final String url, final String data, final String encoding) 
    		throws HttpException{
    	// post方式 
    	return post(url, null, data, encoding);
    }
    
    public String get(final String url, final Map<String, String> headers) throws HttpException{
    	// get方式 
    	final GetMethod get = new GetMethod(url);
        try {
        	// headers
        	setHeaders(get, headers);
        	return (execute(get, encoding));
        }finally{  
        	get.releaseConnection();  
        }
    }
   
    private final HttpMethod setHeaders(final HttpMethod method, final Map<String, String> headers){
    	if(headers != null){
    		final Set<Map.Entry<String, String>> headset = headers.entrySet();  
            for(Iterator<Map.Entry<String, String>> it = headset.iterator(); it.hasNext();){
            	Map.Entry<String, String> header = it.next();
            	method.setRequestHeader(header.getKey(), header.getValue());  
            }
    	}
        return method;
    }
    
    private String execute(final HttpMethod method, final String encoding) throws HttpException{
    	  InputStream in = null;  
    	  BufferedReader reader = null; 
    	  try {
    		  client.executeMethod(method);
    		  // get-encoding
    		  String encode = encoding;
    		  final Header ctypeh = method.getResponseHeader("Content-Type");
    		  if(ctypeh != null){
    			  final String cv = ctypeh.getValue();
    			  final String ctype;
    			  if(cv == null){
    				  ctype = null;
    			  }else{
    				  ctype = cv.toLowerCase(Locale.ENGLISH);
    			  }
    			  final int i;
    			  if(ctype != null && (i=ctype.indexOf(CS_PREFIX))!=-1){
    				  encode = ctype.substring(i+CS_PREFIX_LEN).trim();
    				  // test encoding
    				  try{
    					  "a".getBytes(encode);
    				  }catch(java.io.UnsupportedEncodingException e){
    					  encode = encoding;
    				  }
    				  //
    			  }
    		  }
    		  //
    		  if(encode == null){
    			  return (method.getResponseBodyAsString());
    		  }
    		  in = method.getResponseBodyAsStream(); 
			  reader = new BufferedReader(new InputStreamReader(in, encode), bufferSize);
	          final StringBuffer sbuf = new StringBuffer(bufferSize>>>1);
	          for(String line = reader.readLine(); line != null; line = reader.readLine()){
	        	  sbuf.append(line).append("\r\n");  
	          }  
    		  return (sbuf.toString());
    	  }catch(IOException e){
    		  LOG.error("{}", e);
    		  throw new HttpException(e.getMessage());
    	  } finally{
    		  if(reader != null){
    			  try {
    				  reader.close();  
    			  }
    			  catch (IOException e) {}
    		  }
    		  if(in != null){
    			  try {  
    				  in.close();  
    			  } catch (IOException e) {}  
    		  }
    	  }
    }
}

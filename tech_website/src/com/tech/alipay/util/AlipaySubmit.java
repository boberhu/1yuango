package com.tech.alipay.util;

import com.tech.alipay.config.AlipayConfig;
import com.tech.alipay.sign.MD5;
import com.tech.alipay.util.httpClient.HttpProtocolHandler;
import com.tech.alipay.util.httpClient.HttpRequest;
import com.tech.alipay.util.httpClient.HttpResponse;
import com.tech.alipay.util.httpClient.HttpResultType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class AlipaySubmit
{
  private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
  
  public static String buildRequestMysign(Map<String, String> sPara)
  {
    String prestr = AlipayCore.createLinkString(sPara);
    String mysign = "";
    if (AlipayConfig.sign_type.equals("MD5")) {
      mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
    }
    return mysign;
  }
  
  private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp)
  {
    Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
    
    String mysign = buildRequestMysign(sPara);
    

    sPara.put("sign", mysign);
    sPara.put("sign_type", AlipayConfig.sign_type);
    
    return sPara;
  }
  
  public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName)
  {
    Map<String, String> sPara = buildRequestPara(sParaTemp);
    List<String> keys = new ArrayList<String>(sPara.keySet());
    
    StringBuffer sbHtml = new StringBuffer();
    
    sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\""+ALIPAY_GATEWAY_NEW+"_input_charset=" + 
      AlipayConfig.input_charset + "\" method=\"" + strMethod + 
      "\">");
    for (int i = 0; i < keys.size(); i++)
    {
      String name = (String)keys.get(i);
      String value = (String)sPara.get(name);
      
      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
    }
    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
    sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
    
    return sbHtml.toString();
  }
  
  public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName)
  {
    Map<String, String> sPara = buildRequestPara(sParaTemp);
    List<String> keys = new ArrayList<String>(sPara.keySet());
    
    StringBuffer sbHtml = new StringBuffer();
    
    sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\""+ALIPAY_GATEWAY_NEW+"_input_charset=" + 
      AlipayConfig.input_charset + "\" method=\"" + strMethod + 
      "\">");
    for (int i = 0; i < keys.size(); i++)
    {
      String name = (String)keys.get(i);
      String value = (String)sPara.get(name);
      
      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
    }
    sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");
    

    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
    
    return sbHtml.toString();
  }
  
  public static String buildRequest(String strParaFileName, String strFilePath, Map<String, String> sParaTemp)
    throws Exception
  {
    Map<String, String> sPara = buildRequestPara(sParaTemp);
    
    HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
    
    HttpRequest request = new HttpRequest(HttpResultType.BYTES);
    
    request.setCharset(AlipayConfig.input_charset);
    
    request.setParameters(generatNameValuePair(sPara));
    request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset=" + AlipayConfig.input_charset);
    
    HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
    if (response == null) {
      return null;
    }
    String strResult = response.getStringResult();
    
    return strResult;
  }
  
  private static NameValuePair[] generatNameValuePair(Map<String, String> properties)
  {
    NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
    int i = 0;
    for (Map.Entry<String, String> entry : properties.entrySet()) {
      nameValuePair[(i++)] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
    }
    return nameValuePair;
  }
  
  @SuppressWarnings("unchecked")
  public static String query_timestamp()
    throws MalformedURLException, DocumentException, IOException
  {
    String strUrl = ALIPAY_GATEWAY_NEW+"service=query_timestamp&partner=" + AlipayConfig.partner + "&_input_charset" + AlipayConfig.input_charset;
    StringBuffer result = new StringBuffer();
    
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new URL(strUrl).openStream());
    
    List<Node> nodeList = doc.selectNodes("//alipay/*");
    Iterator<Node> localIterator2;
    for (Iterator<Node> localIterator1 = nodeList.iterator();
    	localIterator1.hasNext(); localIterator2.hasNext())
    {
      Node node = (Node)localIterator1.next();
      if ((!node.getName().equals("is_success")) || (!node.getText().equals("T"))) {
        break;
      }
      List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
      localIterator2 = nodeList1.iterator();
      Node node1 = (Node)localIterator2.next();
      result.append(node1.getText());
    }
    return result.toString();
  }
}

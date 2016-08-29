package com.wangyin.wepaypc.action;

import com.wangyin.wepaypc.model.AsynNotificationReqDto;
import com.wangyin.wepaypc.util.BASE64;
import com.wangyin.wepaypc.util.DESUtil;
import com.wangyin.wepaypc.util.JsonUtil;
import com.wangyin.wepaypc.util.PropertyUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

@Controller
public class AsynNotifyAction
{
  @Resource
  private HttpServletRequest request;
  private static final Logger logger = Logger.getLogger(AsynNotifyAction.class);
  private final String SUCCESS_RETURN_STRING = "success";
  private final String FAIL_RETURN_STRING = "failure";
  
  @RequestMapping(value={"/asynNotify.htm"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String execute()
  {
    logger.info("**********接收异步通知开始。**********");
    
    String resp = request.getParameter("resp");
    
    logger.info("异步通知原始数据:" + resp);
    if (resp == null) {
      return "failure";
    }
    String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");
    String md5Key = PropertyUtils.getProperty("wepay.merchant.md5Key");
    logger.info("desKey:" + desKey);
    logger.info("md5Key:" + md5Key);
    try
    {
      byte[] decryptBASE64Arr = BASE64.decode(resp);
      
      AsynNotificationReqDto dto = parseXML(decryptBASE64Arr);
      logger.info("解析XML得到对象:" + JsonUtil.write2JsonStr(dto));
      
      String ownSign = generateSign(dto.getVersion(), dto.getMerchant(), dto.getTerminal(), dto.getData(), md5Key);
      logger.info("根据传输数据生成的签名:" + ownSign);
      if (!dto.getSign().equals(ownSign))
      {
        logger.info("签名验证错误!");
        throw new RuntimeException();
      }
      logger.info("签名验证正确!");
      


      byte[] rsaKey = decryptBASE64(desKey);
      String decryptArr = DESUtil.decrypt(dto.getData(), rsaKey, "utf-8");
      
      logger.info("对<DATA>进行解密得到的数据:" + decryptArr);
      dto.setData(decryptArr);
      logger.info("最终数据:" + JsonUtil.write2JsonStr(dto));
      logger.info("**********接收异步通知结束。**********");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.error(e);
    }
    return "success";
  }
  
  private static AsynNotificationReqDto parseXML(byte[] xmlString)
  {
    Document document = null;
    try
    {
      InputStream is = new ByteArrayInputStream(xmlString);
      SAXReader sax = new SAXReader(false);
      document = sax.read(is);
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
    }
    AsynNotificationReqDto dto = new AsynNotificationReqDto();
    Element rootElement = document.getRootElement();
    if (rootElement == null) {
      return dto;
    }
    Element versionEliment = rootElement.element("VERSION");
    if (versionEliment != null) {
      dto.setVersion(versionEliment.getText());
    }
    Element merchantEliment = rootElement.element("MERCHANT");
    if (merchantEliment != null) {
      dto.setMerchant(merchantEliment.getText());
    }
    Element terminalEliment = rootElement.element("TERMINAL");
    if (terminalEliment != null) {
      dto.setTerminal(terminalEliment.getText());
    }
    Element datalEliment = rootElement.element("DATA");
    if (datalEliment != null) {
      dto.setData(datalEliment.getText());
    }
    Element signEliment = rootElement.element("SIGN");
    if (signEliment != null) {
      dto.setSign(signEliment.getText());
    }
    return dto;
  }
  
  public static byte[] decryptBASE64(String key)
    throws Exception
  {
    return new BASE64Decoder().decodeBuffer(key);
  }
  
  public static String generateSign(String version, String merchant, String terminal, String data, String md5Key)
    throws Exception
  {
    StringBuilder sb = new StringBuilder();
    sb.append(version);
    sb.append(merchant);
    sb.append(terminal);
    sb.append(data);
    String sign = "";
    sign = md5(sb.toString(), md5Key);
    return sign;
  }
  
  public static String md5(String text, String salt)
    throws Exception
  {
    byte[] bytes = (text + salt).getBytes();
    
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update(bytes);
    bytes = messageDigest.digest();
    
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++)
    {
      if ((bytes[i] & 0xFF) < 16) {
        sb.append("0");
      }
      sb.append(Long.toString(bytes[i] & 0xFF, 16));
    }
    return sb.toString().toLowerCase();
  }
  
  private static String testString = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks+DQogICAg\nPFZFUlNJT04+MS4yLjA8L1ZFUlNJT04+DQogICAgPE1FUkNIQU5UPjIyMzEyNzgxPC9NRVJDSEFO\nVD4NCiAgICA8VEVSTUlOQUw+MDAwMDAwMDE8L1RFUk1JTkFMPg0KICAgIDxEQVRBPkJiQ2NzeVUx\nL3kyMjlIeXZ4RFQ1Rm1SVnVjSFRXUFJqaGhyWmViRW1wTVAvL2xjdW04cjBRdVhGcjZPeDY5NXdN\ndEYwblMwWHhjYVYKOVNoMWdrYU16dVJHQXorcytjOWhYREVnMUFXNVNUY3lRM0c3UTZKdzlBajJM\nV0ZpelQ5cUNRYXVqT1FQT3RPWjIyRWF2RzZzNVJYLwo4c3AzYlJKa3hKNnpDYlQ3ckw0anNJZm9G\nT05BdDBIV1VYUUpiazRBa3NlK1d3emNybU5QUDVzMVcyckRPUnA5Z3cwcVVhVW9DZmdNCk5LSGNY\nYmx2ZVZlVmNZeHlBMHJrRk9xNnFIV0tybEhqRGdqVWl0dFJZQU9CYlA0TDJDSTVvK3dMQzNpV1Z5\nNmVpTHd2QnNJeWM3amwKU2NhanNaTkgxeDlPbUhUVitXWFA1ejBlejdYb0U1SGJUaDBmckdaeERZ\nU2wwZlQvMnkvUDFpbnlrVUpwQktiVnA3c2w4NVVyZjVTcgpZZGM0VzA0QXdJajI0NnBpOW1KUHU2\nd0w2bG5VV24zdXpjT2xDRUxpWkJ6OTJueXI3anlYeXIzR05Ha0VwdFdudXlYenhXV3AzMW8zCmNm\nMFkwWTMrMGJjbm5BPT08L0RBVEE+DQogICAgPFNJR04+YzhhYTc1NGVmZjQ5MzIyNmYzNzU4NTJk\nMGFmNTlmMmU8L1NJR04+DQo8L0NISU5BQkFOSz4NCg0K";
  private static final String DES_KEY = "Z8KMT8cT4z5ruu89znxFhRP4DdDBqLUH";
  private static final String MD5_KEY = "test";
  
  public static void main(String[] args)
    throws Exception
  {
    byte[] decryptBASE64Arr = BASE64.decode(testString);
    String decryptBASE64ArrStr = new String(decryptBASE64Arr, "utf-8");
    System.out.println(decryptBASE64ArrStr);
    AsynNotificationReqDto dto = parseXML(decryptBASE64Arr);
    System.out.println("AsynNotificationReqDto:" + JsonUtil.write2JsonStr(dto));
    byte[] rsaKey = decryptBASE64("Z8KMT8cT4z5ruu89znxFhRP4DdDBqLUH");
    String decryptArr = DESUtil.decrypt(dto.getData(), rsaKey, "utf-8");
    System.out.println("decryptArr:" + decryptArr);
    
    String ownSign = generateSign(dto.getVersion(), dto.getMerchant(), dto.getTerminal(), dto.getData(), "test");
    System.out.println(ownSign);
  }
}

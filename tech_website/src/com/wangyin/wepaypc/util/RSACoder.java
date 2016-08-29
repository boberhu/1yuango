package com.wangyin.wepaypc.util;

import java.io.PrintStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public class RSACoder
  extends RSAUtil
{
  public static final String KEY_ALGORITHM = "RSA";
  public static final String KEY_ALGORITHM_DETAIL = "RSA/ECB/PKCS1Padding";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  public static final String PUBLIC_KEY = "RSAPublicKey";
  public static final String PRIVATE_KEY = "RSAPrivateKey";
  
  public static String sign(byte[] data, String privateKey)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(privateKey);
    

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    

    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
    

    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initSign(priKey);
    signature.update(data);
    
    return encryptBASE64(signature.sign());
  }
  
  public static boolean verify(byte[] data, String publicKey, String sign)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(publicKey);
    

    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    

    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    
    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initVerify(pubKey);
    signature.update(data);
    

    return signature.verify(decryptBASE64(sign));
  }
  
  public static byte[] decryptByPrivateKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);
    

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(2, privateKey);
    
    return cipher.doFinal(data);
  }
  
  public static byte[] decryptByPublicKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);
    

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(2, publicKey);
    
    return cipher.doFinal(data);
  }
  
  public static byte[] encryptByPublicKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);
    

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key publicKey = keyFactory.generatePublic(x509KeySpec);
    

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(1, publicKey);
    
    return cipher.doFinal(data);
  }
  
  public static byte[] encryptByPrivateKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);
    

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    

    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(1, privateKey);
    
    return cipher.doFinal(data);
  }
  
  public static String getPrivateKey(Map<String, Object> keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPrivateKey");
    
    return encryptBASE64(key.getEncoded());
  }
  
  public static String getPublicKey(Map<String, Object> keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPublicKey");
    
    return encryptBASE64(key.getEncoded());
  }
  
  public static Map<String, Object> initKey()
    throws Exception
  {
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    keyPairGen.initialize(1024);
    KeyPair keyPair = keyPairGen.generateKeyPair();
    
    RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
    
    RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
    Map<String, Object> keyMap = new HashMap(2);
    keyMap.put("RSAPublicKey", publicKey);
    keyMap.put("RSAPrivateKey", privateKey);
    return keyMap;
  }
  
  public static void main(String[] args)
  {
    try
    {
      Map<String, Object> map = initKey();
      
      RSAPublicKey publicKey = (RSAPublicKey)map.get("RSAPublicKey");
      
      RSAPrivateKey privateKey = (RSAPrivateKey)map.get("RSAPrivateKey");
      
      System.out.println("********************************");
      System.out.println("公钥加密算法：" + publicKey.getAlgorithm());
      System.out.println("公钥加密格式：" + publicKey.getFormat());
      
      System.out.println("私钥加密算法：" + privateKey.getAlgorithm());
      System.out.println("私钥加密格式：" + privateKey.getFormat());
      System.out.println("********************************");
      

      String pk = FormatUtil.stringBlank(getPublicKey(map));
      String sk = FormatUtil.stringBlank(getPrivateKey(map));
      
      System.out.println("公钥长度=：" + pk.length());
      
      System.out.println("公钥：" + pk);
      
      System.out.println("私钥长度=：" + sk.length());
      System.out.println("私钥：" + sk);
      
      System.out.println("********************************");
      

      String str = "网银在线";
      System.out.println("公钥需要加密的字符串：" + str);
      System.out.println("********************************");
      
      byte[] pks = encryptByPublicKey(str.getBytes("utf-8"), pk);
      String pkss = encryptBASE64(pks);
      
      pkss = FormatUtil.stringBlank(pkss);
      
      System.out.println("公钥加密后的数据：" + pkss);
      System.out.println("********************************");
      
      byte[] sks = decryptByPrivateKey(pks, sk);
      
      System.out.println("私钥解密后的数据：" + new String(sks));
      System.out.println("********************************");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}

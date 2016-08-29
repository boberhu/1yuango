package com.wangyin.wepaypc.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil
{
  public static String generateKey(String seed)
    throws Exception
  {
    byte[] seedBase64DecodeBytes = BASE64.decode(seed);
    
    SecureRandom secureRandom = new SecureRandom(seedBase64DecodeBytes);
    KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
    keyGenerator.init(secureRandom);
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] bytes = secretKey.getEncoded();
    
    String keyBase64EncodeString = BASE64.encode(bytes);
    
    return FormatUtil.stringBlank(keyBase64EncodeString);
  }
  
  public static String encrypt(String text, String key, String charset)
    throws Exception
  {
    byte[] keyBase64DecodeBytes = BASE64.decode(key);
    DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(1, secretKey);
    byte[] textBytes = text.getBytes(charset);
    byte[] bytes = cipher.doFinal(textBytes);
    
    String encryptBase64EncodeString = BASE64.encode(bytes);
    
    return encryptBase64EncodeString;
  }
  
  public static String encrypt(String text, byte[] key, String charset)
    throws Exception
  {
    DESKeySpec desKeySpec = new DESKeySpec(key);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(1, secretKey);
    byte[] textBytes = text.getBytes(charset);
    byte[] bytes = cipher.doFinal(textBytes);
    
    String encryptBase64EncodeString = BASE64.encode(bytes);
    
    return encryptBase64EncodeString;
  }
  
  public static String decrypt(String text, String key, String charset)
    throws Exception
  {
    byte[] keyBase64DecodeBytes = BASE64.decode(key);
    
    DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(2, secretKey);
    byte[] textBytes = BASE64.decode(text);
    byte[] bytes = cipher.doFinal(textBytes);
    
    String decryptString = new String(bytes, charset);
    
    return FormatUtil.stringBlank(decryptString);
  }
  
  public static String decrypt(String text, byte[] key, String charset)
    throws Exception
  {
    DESKeySpec desKeySpec = new DESKeySpec(key);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(2, secretKey);
    byte[] textBytes = BASE64.decode(text);
    byte[] bytes = cipher.doFinal(textBytes);
    
    String decryptString = new String(bytes, charset);
    
    return FormatUtil.stringBlank(decryptString);
  }
}

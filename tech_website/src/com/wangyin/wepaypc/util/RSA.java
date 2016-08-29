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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSA
{
  public static final String RSA = "RSA";
  public static final String KEY_ALGORITHM_DETAIL = "RSA/ECB/PKCS1Padding";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  public static final String RSA_PUBLIC_KEY = "RSAPublicKey";
  public static final String RSA_PRIVATE_KEY = "RSAPrivateKey";
  
  public static byte[] sign(byte[] data, String privateKey)
    throws Exception
  {
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey priKey = keyFactory
      .generatePrivate(pkcs8KeySpec);
    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initSign(priKey);
    signature.update(data);
    return signature.sign();
  }
  
  public static boolean verify(byte[] data, String publicKey, byte[] signByte)
    throws Exception
  {
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initVerify(pubKey);
    signature.update(data);
    return signature.verify(signByte);
  }
  
  public static byte[] decryptByPrivateKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
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
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
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
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
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
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(1, privateKey);
    return cipher.doFinal(data);
  }
  
  public static String getPrivateKey(Map keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPrivateKey");
    byte[] base64bts = new BASE64Encoder().encode(key.getEncoded())
      .getBytes("UTF-8");
    return new String(base64bts, "UTF-8");
  }
  
  public static String getPublicKey(Map keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPublicKey");
    byte[] base64bts = new BASE64Encoder().encode(key.getEncoded())
      .getBytes("UTF-8");
    return new String(base64bts, "UTF-8");
  }
  
  public static Map initKey()
    throws Exception
  {
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    keyPairGen.initialize(1024);
    KeyPair keyPair = keyPairGen.generateKeyPair();
    RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
    Map keyMap = new HashMap(2);
    keyMap.put("RSAPublicKey", publicKey);
    keyMap.put("RSAPrivateKey", privateKey);
    return keyMap;
  }
  
  public static void main(String[] args)
  {
    try
    {
      Map map = initKey();
      RSAPublicKey publicKey = (RSAPublicKey)map.get("RSAPublicKey");
      RSAPrivateKey privateKey = (RSAPrivateKey)map.get("RSAPrivateKey");
      System.out.println("********************************");
      System.out.println(
        "公钥加密算法：" + publicKey.getAlgorithm());
      System.out.println(
        "公钥加密格式：" + publicKey.getFormat());
      System.out.println(
        "私钥加密算法：" + privateKey.getAlgorithm());
      System.out.println(
        "私钥加密格式：" + privateKey.getFormat());
      System.out.println("********************************");
      String pk = getPublicKey(map);
      String sk = getPrivateKey(map);
      System.out.println(
        "公钥长度=：" + pk.length());
      System.out.println(
        "公钥：" + pk);
      System.out.println(
        "私钥长度=：" + sk.length());
      System.out.println(
        "私钥：" + sk);
      System.out.println("********************************");
      String str = MD5.md5("", "tet");
      System.out.println(
        "公钥需要加密的字符串：" + str);
      System.out.println("********************************");
      byte[] publicKeyEncryptBts = encryptByPublicKey(
        str.getBytes("UTF-8"), pk);
      byte[] base64EncodeBts = new BASE64Encoder().encode(
        publicKeyEncryptBts).getBytes("UTF-8");
      String base64EncodeStr = new String(base64EncodeBts, "UTF-8");
      System.out.println(
        "公钥加密后的数据：" + base64EncodeStr);
      System.out.println("********************************");
      byte[] base64DecodeBts = new BASE64Decoder()
        .decodeBuffer(base64EncodeStr);
      byte[] sks = decryptByPrivateKey(base64DecodeBts, sk);
      String skss = new String(sks, "UTF-8");
      System.out.println(
        "私钥解密后的数据：" + skss);
      System.out.println("********************************");
      byte[] privateKeyEncryptBts = str.getBytes("UTF-8");
      System.out.println(
        "私钥需要加密的字符串：" + str);
      System.out.println("********************************");
      byte[] newsks = encryptByPrivateKey(privateKeyEncryptBts, sk);
      String newskss = new BASE64Encoder().encode(newsks);
      System.out.println(
        "私钥加密后的数据：" + newskss);
      System.out.println("********************************");
      byte[] newpks = decryptByPublicKey(newsks, pk);
      String newpkss = new String(newpks, "UTF-8");
      System.out.println(
        "公钥对私钥数据解密：" + newpkss);
      byte[] result1 = sign(str.getBytes("UTF-8"), sk);
      System.out.println(
        "私钥对数据签名：" + Arrays.toString(result1));
      boolean flag = verify(str.getBytes("UTF-8"), pk, result1);
      System.out.println(
        "公钥对数据验签：" + flag);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}

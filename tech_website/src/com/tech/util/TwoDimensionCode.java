package com.tech.util;

import com.swetake.util.Qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

public class TwoDimensionCode
{
  private static final Logger LOG = LoggerFactory.getLogger(TwoDimensionCode.class);	
	
  public void encoderQRCode(String content, String imgPath)
  {
    encoderQRCode(content, imgPath, "png", 7);
  }
  
  public void encoderQRCode(String content, OutputStream output)
  {
    encoderQRCode(content, output, "png", 7);
  }
  
  public void encoderQRCode(String content, String imgPath, String imgType)
  {
    encoderQRCode(content, imgPath, imgType, 7);
  }
  
  public void encoderQRCode(String content, OutputStream output, String imgType)
  {
    encoderQRCode(content, output, imgType, 7);
  }
  
  public void encoderQRCode(String content, String imgPath, String imgType, int size)
  {
    try
    {
      BufferedImage bufImg = qRCodeCommon(content, imgType, size);
      final File imgFile = new File(imgPath);
      ImageIO.write(bufImg, imgType, imgFile);
    }
    catch (Exception e)
    {
      LOG.error("{}", e);
    }
  }
  
  public void encoderQRCode(String content, OutputStream output, String imgType, int size)
  {
    try
    {
      BufferedImage bufImg = qRCodeCommon(content, imgType, size);
      
      ImageIO.write(bufImg, imgType, output);
    }
    catch (Exception e)
    {
    	LOG.error("{}", e);
    }
  }
  
  private BufferedImage qRCodeCommon(String content, String imgType, int size)
  {
    BufferedImage bufImg = null;
    try
    {
      Qrcode qrcodeHandler = new Qrcode();
      qrcodeHandler.setQrcodeErrorCorrect('L');
      qrcodeHandler.setQrcodeEncodeMode('B');
      qrcodeHandler.setQrcodeVersion(size);
      
      byte[] contentBytes = content.getBytes("utf-8");
      int imgSize = 67 + 12 * (size - 1);
      bufImg = new BufferedImage(imgSize, imgSize, 4);
      Graphics2D gs = bufImg.createGraphics();
      try{
    	  gs.setBackground(Color.WHITE);
          gs.clearRect(0, 0, imgSize, imgSize);
          gs.setColor(Color.BLACK);
          
          int pixoff = 2;
          if ((contentBytes.length > 0) && (contentBytes.length < 800))
          {
            boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
            for (int i = 0; i < codeOut.length; i++) {
              for (int j = 0; j < codeOut.length; j++) {
                if (codeOut[j][i]) {
                  gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                }
              }
            }
          }
          else
          {
            throw new Exception("QRCode content bytes length = " + 
            		contentBytes.length + " not in [0, 800].");
          }
      }finally{
          gs.dispose();
      }
      bufImg.flush();
    }
    catch (Exception e)
    {
    	LOG.error("{}", e);
    }
    return bufImg;
  }
  
  public String decoderQRCode(String imgPath)
  {
    File imageFile = new File(imgPath);
    BufferedImage bufImg = null;
    String content = null;
    try
    {
      bufImg = ImageIO.read(imageFile);
      QRCodeDecoder decoder = new QRCodeDecoder();
      content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
    }
    catch (IOException e)
    {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    catch (DecodingFailedException dfe)
    {
      System.out.println("Error: " + dfe.getMessage());
      dfe.printStackTrace();
    }
    return content;
  }
  
  public String decoderQRCode(InputStream input)
  {
    BufferedImage bufImg = null;
    String content = null;
    try
    {
      bufImg = ImageIO.read(input);
      QRCodeDecoder decoder = new QRCodeDecoder();
      content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
    }
    catch (IOException e)
    {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    catch (DecodingFailedException dfe)
    {
      System.out.println("Error: " + dfe.getMessage());
      dfe.printStackTrace();
    }
    return content;
  }
  
  public static void main(String[] args)
  {
    String imgPath = "D:/Michael_QRCode.png";
    String encoderContent = "http://www.1ypg.com/1ypg1.0.apk";
    System.err.println(encoderContent.length());
    TwoDimensionCode handler = new TwoDimensionCode();
    handler.encoderQRCode(encoderContent, imgPath, "png", 5);
    





    System.out.println("========encoder success");
    
    String decoderContent = handler.decoderQRCode(imgPath);
    System.out.println("解析结果如下：");
    System.out.println(decoderContent);
    System.out.println("========decoder success!!!");
  }
}

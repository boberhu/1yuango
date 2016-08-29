package com.tech.util;

import com.sun.image.codec.jpeg.ImageFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class AbstractNetImage
{
  private String url = null;
  
  public AbstractNetImage(String url)
  {
    this.url = url;
  }
  
  public AbstractNetImage() {}
  
  public String getUrl()
  {
    return url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  protected abstract void encode(FileOutputStream paramFileOutputStream, BufferedImage paramBufferedImage)
    throws ImageFormatException, IOException;
  
  private void getImageImpl(String newFilePath, int sizeReduceRank)
    throws MalformedURLException, IOException
  {
    if (url == null) {
      return;
    }
    Image image = ImageIO.read(new URL(url));
    int width = image.getWidth(null) / sizeReduceRank;
    int height = image.getHeight(null) / sizeReduceRank;
    
    BufferedImage bufferedImage = new BufferedImage(width, height, 
      1);
    bufferedImage.getGraphics().drawImage(image, 0, 0, width, height, null);
    FileOutputStream out = new FileOutputStream(newFilePath);
    encode(out, bufferedImage);
    image.flush();
    bufferedImage.flush();
    out.close();
  }
  
  public final void getImage(String newFilePath, int sizeReduceRank)
    throws MalformedURLException, IOException
  {
    getImageImpl(newFilePath, sizeReduceRank);
  }
  
  public final void getImage(String newFilePath)
    throws MalformedURLException, IOException
  {
    getImageImpl(newFilePath, 1);
  }
  
  public void getImageFromUrl(String url, String newFilePath)
    throws MalformedURLException, IOException
  {
    setUrl(url);
    getImage(newFilePath);
  }
}

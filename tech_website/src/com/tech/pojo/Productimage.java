package com.tech.pojo;

import java.io.Serializable;

public class Productimage
  implements Serializable
{
  private Integer productImageId;
  private Integer piProductId;
  private String image;
  private String imageType;
  private String attribute75;
  
  public Productimage() {}
  
  public Productimage(Integer productImageId, Integer piProductId, String image, String imageType, String attribute75)
  {
    this.productImageId = productImageId;
    this.piProductId = piProductId;
    this.image = image;
    this.imageType = imageType;
    this.attribute75 = attribute75;
  }
  
  public Integer getProductImageId()
  {
    return productImageId;
  }
  
  public void setProductImageId(Integer productImageId)
  {
    this.productImageId = productImageId;
  }
  
  public Integer getPiProductId()
  {
    return piProductId;
  }
  
  public void setPiProductId(Integer piProductId)
  {
    this.piProductId = piProductId;
  }
  
  public String getImage()
  {
    return image;
  }
  
  public void setImage(String image)
  {
    this.image = image;
  }
  
  public String getAttribute75()
  {
    return attribute75;
  }
  
  public void setAttribute75(String attribute75)
  {
    this.attribute75 = attribute75;
  }
  
  public String getImageType()
  {
    return imageType;
  }
  
  public void setImageType(String imageType)
  {
    this.imageType = imageType;
  }
}

package com.tech.pojo;

import java.io.Serializable;

public class Shareimage
  implements Serializable
{
  private Integer uid;
  private Integer shareInfoId;
  private String images;
  
  public Shareimage() {}
  
  public Shareimage(Integer uid, Integer shareInfoId, String images)
  {
    this.uid = uid;
    this.shareInfoId = shareInfoId;
    this.images = images;
  }
  
  public Integer getUid()
  {
    return uid;
  }
  
  public void setUid(Integer uid)
  {
    this.uid = uid;
  }
  
  public Integer getShareInfoId()
  {
    return shareInfoId;
  }
  
  public void setShareInfoId(Integer shareInfoId)
  {
    this.shareInfoId = shareInfoId;
  }
  
  public String getImages()
  {
    return images;
  }
  
  public void setImages(String images)
  {
    this.images = images;
  }
}

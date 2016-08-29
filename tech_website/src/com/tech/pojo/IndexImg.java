package com.tech.pojo;

import java.io.Serializable;

public class IndexImg
  implements Serializable
{
  private Integer id;
  private String title;
  private String proUrl;
  private String proImg;
  private Integer status;
  
  public IndexImg() {}
  
  public IndexImg(String title, String proUrl, String proImg, Integer status)
  {
    this.title = title;
    this.proUrl = proUrl;
    this.proImg = proImg;
    this.status = status;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getProUrl()
  {
    return proUrl;
  }
  
  public void setProUrl(String proUrl)
  {
    this.proUrl = proUrl;
  }
  
  public String getProImg()
  {
    return proImg;
  }
  
  public void setProImg(String proImg)
  {
    this.proImg = proImg;
  }
  
  public Integer getStatus()
  {
    return status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
}

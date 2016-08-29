package com.tech.pojo;

import java.io.Serializable;

public class Latestlottery
  implements Serializable
{
  private static final long serialVersionUID = -9159929891012838918L;
  private Integer id;
  private Integer productId;
  private Integer productType;
  private String productName;
  private String productTitle;
  private Integer productPrice;
  private String productImg;
  private Integer productPeriod;
  private String userName;
  private String location;
  private String announcedTime;
  private Integer announcedType;
  private String buyTime;
  private Integer spellbuyRecordId;
  private Integer spellbuyProductId;
  private Integer randomNumber;
  private Long dateSum;
  private String sscNumber;
  private Long sscPeriod;
  private Integer buyNumberCount;
  private Integer userId;
  private String buyUser;
  private String userFace;
  private Integer status;
  private Integer shareStatus;
  private Integer shareId;
  private String dingdanhao;
  
  public Latestlottery() {}
  
  public Latestlottery(Integer productId, Integer productType, String productName, String productTitle, Integer productPrice, String productImg, Integer productPeriod, String userName, String location, String announcedTime, Integer announcedType, String buyTime, Integer spellbuyRecordId, Integer spellbuyProductId, Integer randomNumber, Long dateSum, String sscNumber, Long sscPeriod, Integer buyNumberCount, Integer userId, String buyUser, String userFace, Integer status, Integer shareStatus, Integer shareId)
  {
    this.productId = productId;
    this.productType = productType;
    this.productName = productName;
    this.productTitle = productTitle;
    this.productPrice = productPrice;
    this.productImg = productImg;
    this.productPeriod = productPeriod;
    this.userName = userName;
    this.location = location;
    this.announcedTime = announcedTime;
    this.announcedType = announcedType;
    this.buyTime = buyTime;
    this.spellbuyRecordId = spellbuyRecordId;
    this.spellbuyProductId = spellbuyProductId;
    this.randomNumber = randomNumber;
    this.dateSum = dateSum;
    this.sscNumber = sscNumber;
    this.sscPeriod = sscPeriod;
    this.buyNumberCount = buyNumberCount;
    this.userId = userId;
    this.buyUser = buyUser;
    this.userFace = userFace;
    this.status = status;
    this.shareStatus = shareStatus;
    this.shareId = shareId;
  }
  
  public String getUserFace()
  {
    return userFace;
  }
  
  public void setUserFace(String userFace)
  {
    this.userFace = userFace;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getProductId()
  {
    return productId;
  }
  
  public void setProductId(Integer productId)
  {
    this.productId = productId;
  }
  
  public Integer getProductType()
  {
    return productType;
  }
  
  public void setProductType(Integer productType)
  {
    this.productType = productType;
  }
  
  public String getProductName()
  {
    return productName;
  }
  
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  
  public String getProductTitle()
  {
    return productTitle;
  }
  
  public void setProductTitle(String productTitle)
  {
    this.productTitle = productTitle;
  }
  
  public Integer getProductPrice()
  {
    return productPrice;
  }
  
  public void setProductPrice(Integer productPrice)
  {
    this.productPrice = productPrice;
  }
  
  public String getProductImg()
  {
    return productImg;
  }
  
  public void setProductImg(String productImg)
  {
    this.productImg = productImg;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getLocation()
  {
    return location;
  }
  
  public void setLocation(String location)
  {
    this.location = location;
  }
  
  public String getAnnouncedTime()
  {
    return announcedTime;
  }
  
  public void setAnnouncedTime(String announcedTime)
  {
    this.announcedTime = announcedTime;
  }
  
  public String getBuyTime()
  {
    return buyTime;
  }
  
  public void setBuyTime(String buyTime)
  {
    this.buyTime = buyTime;
  }
  
  public Integer getRandomNumber()
  {
    return randomNumber;
  }
  
  public void setRandomNumber(Integer randomNumber)
  {
    this.randomNumber = randomNumber;
  }
  
  public Integer getBuyNumberCount()
  {
    return buyNumberCount;
  }
  
  public void setBuyNumberCount(Integer buyNumberCount)
  {
    this.buyNumberCount = buyNumberCount;
  }
  
  public String getBuyUser()
  {
    return buyUser;
  }
  
  public void setBuyUser(String buyUser)
  {
    this.buyUser = buyUser;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public Integer getStatus()
  {
    return status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
  
  public Integer getShareStatus()
  {
    return shareStatus;
  }
  
  public void setShareStatus(Integer shareStatus)
  {
    this.shareStatus = shareStatus;
  }
  
  public Integer getShareId()
  {
    return shareId;
  }
  
  public void setShareId(Integer shareId)
  {
    this.shareId = shareId;
  }
  
  public Integer getSpellbuyRecordId()
  {
    return spellbuyRecordId;
  }
  
  public void setSpellbuyRecordId(Integer spellbuyRecordId)
  {
    this.spellbuyRecordId = spellbuyRecordId;
  }
  
  public Integer getSpellbuyProductId()
  {
    return spellbuyProductId;
  }
  
  public void setSpellbuyProductId(Integer spellbuyProductId)
  {
    this.spellbuyProductId = spellbuyProductId;
  }
  
  public Long getDateSum()
  {
    return dateSum;
  }
  
  public void setDateSum(Long dateSum)
  {
    this.dateSum = dateSum;
  }
  
  public Integer getAnnouncedType()
  {
    return announcedType;
  }
  
  public void setAnnouncedType(Integer announcedType)
  {
    this.announcedType = announcedType;
  }
  
  public String getSscNumber()
  {
    return sscNumber;
  }
  
  public void setSscNumber(String sscNumber)
  {
    this.sscNumber = sscNumber;
  }
  
  public Long getSscPeriod()
  {
    return sscPeriod;
  }
  
  public void setSscPeriod(Long sscPeriod)
  {
    this.sscPeriod = sscPeriod;
  }

public String getDingdanhao() {
	return dingdanhao;
}

public void setDingdanhao(String dingdanhao) {
	this.dingdanhao = dingdanhao;
}
  
  
}

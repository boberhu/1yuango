package com.tech.pojo;

import java.io.Serializable;

import com.tech.util.ApplicationListenerImpl;
import com.tech.util.StringUtil;

public class ProductInfo
  implements Serializable
{
  private static final long serialVersionUID = 2984681206487401036L;
  private String productName;
  private String productTitle;
  private Integer productPrice;
  private Integer singlePrice;
  private Integer spellbuyLimit;
  private String headImage;
  private String productDetail;
  private Integer productId;
  private Integer spellbuyProductId;
  private Integer spellbuyCount;
  private Integer productPeriod;
  private Integer status;
  
  public ProductInfo() {}
  
  public ProductInfo(String productName, String productTitle, Integer productPrice, Integer singlePrice, Integer spellbuyLimit, String headImage, String productDetail, Integer productId, Integer spellbuyProductId, Integer spellbuyCount, Integer productPeriod, Integer status)
  {
    this.productName = productName;
    this.productTitle = productTitle;
    this.productPrice = productPrice;
    this.singlePrice = singlePrice;
    this.headImage = headImage;
    this.productDetail = productDetail;
    this.productId = productId;
    this.spellbuyProductId = spellbuyProductId;
    this.spellbuyCount = spellbuyCount;
    this.productPeriod = productPeriod;
    this.status = status;
  }
  
  public String getProductDetail2()
  {
	  if(StringUtil.isEmpty(productDetail)){
		  return productDetail;
	  }else{
		  return productDetail.replaceAll("/productImg", ApplicationListenerImpl.sysConfigureJson.getWwwUrl()+"/productImg");
	  }
  }
  
  public Integer getStatus()
  {
    return status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
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
  
  public String getHeadImage()
  {
    return headImage;
  }
  
  public void setHeadImage(String headImage)
  {
    this.headImage = headImage;
  }
  
  public String getProductDetail()
  {
    return productDetail;
  }
  
  public void setProductDetail(String productDetail)
  {
    this.productDetail = productDetail;
  }
  
  public Integer getSpellbuyProductId()
  {
    return spellbuyProductId;
  }
  
  public void setSpellbuyProductId(Integer spellbuyProductId)
  {
    this.spellbuyProductId = spellbuyProductId;
  }
  
  public Integer getSpellbuyCount()
  {
    return spellbuyCount;
  }
  
  public void setSpellbuyCount(Integer spellbuyCount)
  {
    this.spellbuyCount = spellbuyCount;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
  
  public static long getSerialVersionUID()
  {
    return 2984681206487401036L;
  }
  
  public Integer getSinglePrice()
  {
    return singlePrice;
  }
  
  public void setSinglePrice(Integer singlePrice)
  {
    this.singlePrice = singlePrice;
  }
  
  public Integer getSpellbuyLimit()
  {
    return spellbuyLimit;
  }
  
  public void setSpellbuyLimit(Integer spellbuyLimit)
  {
    this.spellbuyLimit = spellbuyLimit;
  }
  
  public Integer getProductId()
  {
    return productId;
  }
  
  public void setProductId(Integer productId)
  {
    this.productId = productId;
  }
}

package com.tech.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Producttype;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.service.ProducttypeService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Struts2Utils;

@Component("LimitBuyAction")
public class LimitBuyAction extends BaseAction
{
  private static final long serialVersionUID = 4512818570512026427L;
  @Autowired
  @Qualifier("spellbuyrecordService")
  private SpellbuyrecordService spellbuyrecordService;
  @Autowired
  @Qualifier("spellbuyproductService")
  private SpellbuyproductService spellbuyproductService;
  @Autowired
  ProducttypeService producttypeService;
  private List<ProductJSON> ProductList;
  private ProductJSON productJSON;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private List<Producttype> producttyList;
  private List<Producttype> brandList;
  private String id;
  private String typeId;
  private String tId;
  private String typeName;
  private String brandName;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private String brandId;
  HttpServletRequest request = null;
  HttpServletResponse response = null;
  
  public String index()
  {
    return "index";
  }
  
  public void getLimitGoodsPage()
  {
    Pagination hotPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("limitBuy_" + pageNo + "_" + pageSize);
    if (hotPage == null)
    {
      hotPage = spellbuyrecordService.ProductBylimitBuyList(pageNo, pageSize);
      AliyunOcsSampleHelp.getIMemcachedCache().set("limitBuy_" + pageNo + "_" + pageSize, 5, hotPage);
    }
    List<?> HotList = hotPage.getList();
    ProductList = new ArrayList();
    for (int i = 0; i < HotList.size(); i++)
    {
      productJSON = new ProductJSON();
      Object[] objects = (Object[])HotList.get(i);
    	if(objects[0] instanceof Spellbuyproduct){
    		spellbuyproduct = (Spellbuyproduct)objects[0];
    		product = (Product)objects[1];
    	}else{
    		spellbuyproduct = (Spellbuyproduct)objects[1];
    		product = (Product)objects[0];
    	}
      //product = ((Product)((Object[])HotList.get(i))[0]);
      //spellbuyproduct = ((Spellbuyproduct)((Object[])HotList.get(i))[1]);
      productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
      productJSON.setHeadImage(product.getHeadImage());
      productJSON.setProductId(product.getProductId());
      productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
      productJSON.setProductName(product.getProductName());
      productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
      productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
      productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
      productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
      productJSON.setProductTitle(product.getProductTitle());
      productJSON.setProductStyle(product.getStyle());
      ProductList.add(productJSON);
    }
    Struts2Utils.renderJson(ProductList, new String[0]);
  }
  
  public void getLimitNewestLottery() {}
  
  public Product getProduct()
  {
    return product;
  }
  
  public void setProduct(Product product)
  {
    this.product = product;
  }
  
  public Spellbuyproduct getSpellbuyproduct()
  {
    return spellbuyproduct;
  }
  
  public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct)
  {
    this.spellbuyproduct = spellbuyproduct;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public int getPageNo()
  {
    return pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public int getPageSize()
  {
    return pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getPageCount()
  {
    return pageCount;
  }
  
  public void setPageCount(int pageCount)
  {
    this.pageCount = pageCount;
  }
  
  public int getResultCount()
  {
    return resultCount;
  }
  
  public void setResultCount(int resultCount)
  {
    this.resultCount = resultCount;
  }
  
  public List<ProductJSON> getProductList()
  {
    return ProductList;
  }
  
  public void setProductList(List<ProductJSON> productList)
  {
    ProductList = productList;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
  public String getTypeId()
  {
    return typeId;
  }
  
  public void setTypeId(String typeId)
  {
    this.typeId = typeId;
  }
  
  public String getTypeName()
  {
    return typeName;
  }
  
  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }
  
  public String getPages()
  {
    return pages;
  }
  
  public void setPages(String pages)
  {
    this.pages = pages;
  }
  
  public String getPageString()
  {
    return pageString;
  }
  
  public void setPageString(String pageString)
  {
    this.pageString = pageString;
  }
  
  public List<Producttype> getProducttyList()
  {
    return producttyList;
  }
  
  public void setProducttyList(List<Producttype> producttyList)
  {
    this.producttyList = producttyList;
  }
  
  public List<Producttype> getBrandList()
  {
    return brandList;
  }
  
  public void setBrandList(List<Producttype> brandList)
  {
    this.brandList = brandList;
  }
  
  public String getBrandId()
  {
    return brandId;
  }
  
  public void setBrandId(String brandId)
  {
    this.brandId = brandId;
  }
  
  public String getTId()
  {
    return tId;
  }
  
  public void setTId(String id)
  {
    tId = id;
  }
  
  public String getBrandName()
  {
    return brandName;
  }
  
  public void setBrandName(String brandName)
  {
    this.brandName = brandName;
  }
}

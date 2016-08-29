package com.tech.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Producttype;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.ProductService;
import com.tech.service.ProducttypeService;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.PaginationUtil;
import com.tech.util.Struts2Utils;

@Component("ProductList")
public class ProductList extends BaseAction
{
  private static final long serialVersionUID = -8133635872882545829L;
  @Autowired
  ProducttypeService producttypeService;
  @Autowired
  ProductService productService;
  private List<Product> productList;
  private ProductJSON productJSON;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private String id;
  private String typeId;
  private String typeName;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  
  public String index()
  {
    if (!ApplicationListenerImpl.sysConfigureAuth)
    {
      Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>", new String[0]);
      

      return null;
    }
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (pages != null) {
      pageNo = Integer.parseInt(pages.split("_")[1]);
    }
    if ((typeId != null) && (!typeId.equals(""))) {
      typeName = ((Producttype)producttypeService.findById(typeId)).getTypeName();
    } else {
      typeName = ((Producttype)producttypeService.findById("1000")).getTypeName();
    }
    if (id.equals("hot20"))
    {
      Pagination page = productService.ProductListByTypeIdList(typeId, "hot", pageNo, pageSize);
      productList = (List<Product>)page.getList();
      
      resultCount = page.getResultCount();
      if ((typeId != null) && (!typeId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/productList/" + id + "/" + typeId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/productList/" + id + "/p_");
      }
    }
    return "index";
  }
  
  public List<Product> getProductList()
  {
    return productList;
  }
  
  public void setProductList(List<Product> productList)
  {
    this.productList = productList;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
  }
  
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
  
  public int getPageNo()
  {
    return pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
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
}

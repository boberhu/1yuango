package com.tech.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.PaginationUtil;
import com.tech.util.Struts2Utils;

@Component("SearchAction")
public class SearchAction extends BaseAction
{
  private static final long serialVersionUID = -6415908765367001524L;
  @Autowired
  @Qualifier("spellbuyrecordService")
  private SpellbuyrecordService spellbuyrecordService;
  private List<ProductJSON> ProductList;
  private Product product;
  private Spellbuyproduct spellbuyproduct;
  private ProductJSON productJSON;
  private String id;
  private int pageNo;
  private String pages;
  private String pageString;
  private int pageSize = 20;
  private int pageCount;
  private int resultCount;
  private String keyword;
  
  public String index()
  {
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (pages != null) {
      pageNo = Integer.parseInt(pages.split("_")[1]);
    }
    StringBuilder sbuf = new StringBuilder();
    if (id.equals("hot20"))
    {
      Pagination hotPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_hotPage_hot20_" + keyword + "_hot_" + pageNo + "_" + pageSize);
      if (hotPage == null)
      {
        hotPage = spellbuyrecordService.searchProduct(keyword, "hot", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_hotPage_hot20_" + keyword + "_hot_" + pageNo + "_" + pageSize, 10, hotPage);
      }
      List<?> HotList = hotPage.getList();
      ProductList = new ArrayList<ProductJSON>();
      for (int i = 0; i < HotList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])HotList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])HotList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = hotPage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    else if (id.equals("date20"))
    {
      Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_datePage_date20_" + keyword + "_date_" + pageNo + "_" + pageSize);
      if (datePage == null)
      {
        datePage = spellbuyrecordService.searchProduct(keyword, "date", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_datePage_date20_" + keyword + "_date_" + pageNo + "_" + pageSize, 10, datePage);
      }
      List<?> dateList = datePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = datePage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    else if (id.equals("price20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_pricePage_price20_" + keyword + "_price_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.searchProduct(keyword, "price", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_pricePage_price20_" + keyword + "_price_" + pageNo + "_" + pageSize, 10, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    else if (id.equals("priceAsc20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_pricePage_priceAsc20_" + keyword + "_priceAsc_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.searchProduct(keyword, "priceAsc", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_pricePage_priceAsc20_" + keyword + "_priceAsc_" + pageNo + "_" + pageSize, 10, pricePage);
      }
      List<?>  priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    else if (id.equals("about20"))
    {
      Pagination aboutPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_aboutPage_about20_" + keyword + "_about_" + pageNo + "_" + pageSize);
      if (aboutPage == null)
      {
        aboutPage = spellbuyrecordService.searchProduct(keyword, "about", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_aboutPage_about20_" + keyword + "_about_" + pageNo + "_" + pageSize, 10, aboutPage);
      }
      List<?>  priceList = aboutPage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = aboutPage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    else if (id.equals("surplus20"))
    {
      Pagination surplusPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("search_index_surplusPage_surplus20_" + keyword + "_surplus_" + pageNo + "_" + pageSize);
      if (surplusPage == null)
      {
        surplusPage = spellbuyrecordService.searchProduct(keyword, "surplus", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("search_index_surplusPage_surplus20_" + keyword + "_surplus_" + pageNo + "_" + pageSize, 10, surplusPage);
      }
      List<?>  priceList = surplusPage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = surplusPage.getResultCount();
      pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
    }
    return "index";
  }
  
  public String ajaxPage()
  {
    if (pageNo == 0) {
      pageNo = 1;
    } else {
      pageNo += 1;
    }
    if (id.equals("hot20"))
    {
      Pagination hotPage = spellbuyrecordService.searchProduct(keyword, "hot", pageNo, pageSize);
      List<?>  HotList = hotPage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < HotList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])HotList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])HotList.get(i))[1]);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(product.getProductName());
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      Struts2Utils.renderJson(ProductList, new String[0]);
    }
    else if (id.equals("date20"))
    {
      Pagination datePage = spellbuyrecordService.searchProduct(keyword, "date", pageNo, pageSize);
      List<?>  dateList = datePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])dateList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])dateList.get(i))[1]);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(product.getProductName());
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      Struts2Utils.renderJson(ProductList, new String[0]);
    }
    else if (id.equals("price20"))
    {
      Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "price", pageNo, pageSize);
      List<?>  priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(product.getProductName());
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      Struts2Utils.renderJson(ProductList, new String[0]);
    }
    else if (id.equals("priceAsc20"))
    {
      Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "priceAsc", pageNo, pageSize);
      List<?>  priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
        productJSON = new ProductJSON();
        product = ((Product)((Object[])priceList.get(i))[0]);
        spellbuyproduct = ((Spellbuyproduct)((Object[])priceList.get(i))[1]);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(spellbuyproduct.getFkProductId());
        productJSON.setProductName(product.getProductName());
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      Struts2Utils.renderJson(ProductList, new String[0]);
    }
    return null;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public List<ProductJSON> getProductList()
  {
    return ProductList;
  }
  
  public void setProductList(List<ProductJSON> productList)
  {
    ProductList = productList;
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
  
  public String getKeyword()
  {
    return keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public ProductJSON getProductJSON()
  {
    return productJSON;
  }
  
  public void setProductJSON(ProductJSON productJSON)
  {
    this.productJSON = productJSON;
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
}

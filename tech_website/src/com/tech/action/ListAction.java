package com.tech.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.dao.Pagination;
import com.tech.pojo.Product;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.Producttype;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.ProducttypeService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.PaginationUtil;
import com.tech.util.StringUtil;
import com.tech.util.Struts2Utils;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component("ListAction")
public class ListAction extends BaseAction
{
  private static final long serialVersionUID = 8452833122481904678L;
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
    if (pageNo == 0) {
      pageNo = 1;
    }
    if (pages != null) {
      pageNo = Integer.parseInt(pages.split("_")[1]);
    }
    if (StringUtil.isNotBlank(typeId))
    {
      if (typeId.indexOf("b") != -1)
      {
        brandId = typeId.split("b")[1];
        tId = typeId.split("b")[0];
        if (StringUtil.isNotBlank(tId)) {
          typeName = ((Producttype)producttypeService.findById(tId)).getTypeName();
        } else {
          typeName = ((Producttype)producttypeService.findById("1000")).getTypeName();
        }
      }
      else
      {
        tId = typeId;
        typeName = ((Producttype)producttypeService.findById(tId)).getTypeName();
      }
    }
    else {
      typeName = ((Producttype)producttypeService.findById("1000")).getTypeName();
    }
    producttyList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("list_producttyList"));
    if (producttyList == null)
    {
      producttyList = producttypeService.listByProductList();
      AliyunOcsSampleHelp.getIMemcachedCache().set("list_producttyList", 5, producttyList);
    }
    List<Producttype> tList = (List)AliyunOcsSampleHelp.getIMemcachedCache().get("index_tList_" + tId);
    if (tList == null)
    {
      tList = producttypeService.listByBrand(tId);
      AliyunOcsSampleHelp.getIMemcachedCache().set("index_tList_" + tId, 5, tList);
    }
    int j = 0;
    for (int i = 0; i < tList.size(); i++) {
      if ((StringUtil.isNotBlank(brandId)) && 
        (Integer.parseInt(brandId) == ((Producttype)tList.get(i)).getTypeId().intValue()))
      {
        j = i;
        brandName = ((Producttype)tList.get(i)).getTypeName();
      }
    }
    if (j > 22)
    {
      if (StringUtil.isNotBlank(brandId))
      {
        brandList = new ArrayList();
        for (int i = 0; i < tList.size(); i++) {
          if (Integer.parseInt(brandId) == ((Producttype)tList.get(i)).getTypeId().intValue()) {
            brandList.add((Producttype)tList.get(i));
          }
        }
        for (int i = 0; i < tList.size(); i++) {
          if (Integer.parseInt(brandId) != ((Producttype)tList.get(i)).getTypeId().intValue()) {
            brandList.add((Producttype)tList.get(i));
          }
        }
      }
      else
      {
        brandList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("list_brandList_" + tId));
        if (brandList == null)
        {
          brandList = producttypeService.listByBrand(tId);
          AliyunOcsSampleHelp.getIMemcachedCache().set("list_brandList_" + tId, 5, brandList);
        }
      }
    }
    else
    {
      brandList = ((List)AliyunOcsSampleHelp.getIMemcachedCache().get("list_brandList_" + tId));
      if (brandList == null)
      {
        brandList = producttypeService.listByBrand(tId);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_brandList_" + tId, 5, brandList);
      }
    }
    StringBuilder sbuf = new StringBuilder();
    if (id.equals("hot20"))
    {
      Pagination hotPage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_hot20_" + tId + "_" + brandId + "_" + "hot_" + pageNo + "_" + pageSize);
      if (hotPage == null)
      {
        hotPage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "hot", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_hot20_" + tId + "_" + brandId + "_" + "hot_" + pageNo + "_" + pageSize, 5, hotPage);
      }
      List<?> HotList = hotPage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < HotList.size(); i++)
      {
    	  Object[] objs = (Object[])HotList.get(i);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		spellbuyproduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		spellbuyproduct = (Spellbuyproduct)objs[0];
      	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        ProductList.add(productJSON);
      }
      resultCount = hotPage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("date20"))
    {
      Pagination datePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_date20_" + tId + "_" + brandId + "_" + "date_" + pageNo + "_" + pageSize);
      if (datePage == null)
      {
        datePage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "date", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_date20_" + tId + "_" + brandId + "_" + "date_" + pageNo + "_" + pageSize, 5, datePage);
      }
      List<?> dateList = datePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < dateList.size(); i++)
      {
    	  Object[] objs = (Object[])dateList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = datePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("price20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_price20_" + tId + "_" + brandId + "_" + "price_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "price", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_price20_" + tId + "_" + brandId + "_" + "price_" + pageNo + "_" + pageSize, 5, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
    	  Object[] objs = (Object[])priceList.get(i);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		spellbuyproduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		spellbuyproduct = (Spellbuyproduct)objs[0];
      	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("priceAsc20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_priceAsc20_" + tId + "_" + brandId + "_" + "priceAsc_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "priceAsc", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_priceAsc20_" + tId + "_" + brandId + "_" + "priceAsc_" + pageNo + "_" + pageSize, 5, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
    	  Object[] objs = (Object[])priceList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("about20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_about20_" + tId + "_" + brandId + "_" + "about_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "about", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_about20_" + tId + "_" + brandId + "_" + "about_" + pageNo + "_" + pageSize, 5, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
    	  Object[] objs = (Object[])priceList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("surplus20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_surplus20_" + tId + "_" + brandId + "_" + "surplus_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.ProductByTypeIdList(tId, brandId, "surplus", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_surplus20_" + tId + "_" + brandId + "_" + "surplus_" + pageNo + "_" + pageSize, 5, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
    	  Object[] objs = (Object[])priceList.get(i);
        	if(objs[0] instanceof Product){
        		product = (Product)objs[0];
        		spellbuyproduct = (Spellbuyproduct)objs[1];
        	}else{
        		product = (Product)objs[1];
        		spellbuyproduct = (Spellbuyproduct)objs[0];
        	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    else if (id.equals("limit20"))
    {
      Pagination pricePage = (Pagination)AliyunOcsSampleHelp.getIMemcachedCache().get("list_limit_" + tId + "_" + brandId + "_" + "limit_" + pageNo + "_" + pageSize);
      if (pricePage == null)
      {
        pricePage = spellbuyrecordService.ProductByTypeIdList(null, null, "limit", pageNo, pageSize);
        AliyunOcsSampleHelp.getIMemcachedCache().set("list_limit_" + tId + "_" + brandId + "_" + "limit_" + pageNo + "_" + pageSize, 5, pricePage);
      }
      List<?> priceList = pricePage.getList();
      ProductList = new ArrayList();
      for (int i = 0; i < priceList.size(); i++)
      {
    	  Object[] objs = (Object[])priceList.get(i);
      	if(objs[0] instanceof Product){
      		product = (Product)objs[0];
      		spellbuyproduct = (Spellbuyproduct)objs[1];
      	}else{
      		product = (Product)objs[1];
      		spellbuyproduct = (Spellbuyproduct)objs[0];
      	}
        productJSON = new ProductJSON();
        spellbuyproduct.setProduct(product);
        productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
        productJSON.setHeadImage(product.getHeadImage());
        productJSON.setProductId(product.getProductId());
        productJSON.setSpellbuyProductId(spellbuyproduct.getSpellbuyProductId());
        productJSON.setProductName(getPeriodName(sbuf, spellbuyproduct));
        productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
        productJSON.setSinglePrice(spellbuyproduct.getSpSinglePrice());
        productJSON.setProductTitle(product.getProductTitle());
        productJSON.setProductStyle(product.getStyle());
        productJSON.setSpellbuyLimit(spellbuyproduct.getSpellbuyLimit());
        ProductList.add(productJSON);
      }
      resultCount = pricePage.getResultCount();
      if ((tId != null) && (!tId.equals(""))) {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + tId + "/p_");
      } else {
        pageString = PaginationUtil.getPaginationHtml(Integer.valueOf(resultCount), Integer.valueOf(pageSize), Integer.valueOf(pageNo), Integer.valueOf(2), Integer.valueOf(5), ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
      }
    }
    return "index";
  }
  
  public void isStatus()
  {
    request = Struts2Utils.getRequest();
    Cookie[] cookies = request.getCookies();
    String userId = null;
    if (request.isRequestedSessionIdFromCookie()) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (cookie.getName().equals("userId")) {
          userId = cookie.getValue();
          break;
        }
      }
    }
    List<Object[]> proList = (List)AliyunOcsSampleHelp.getIMemcachedCache()
    		.get("index_spellbuyproduct_" + id);
    if (proList == null)
    {
      proList = spellbuyproductService.findByProductId(Integer.parseInt(id));
      AliyunOcsSampleHelp.getIMemcachedCache()
      	.set("index_spellbuyproduct_" + id, 2, proList);
    }
    Object[] objs = (Object[])proList.get(0);
    if(objs[0] instanceof Spellbuyproduct){
		spellbuyproduct = (Spellbuyproduct)objs[0];
	}else{
		spellbuyproduct = (Spellbuyproduct)objs[1];
	}
    //spellbuyproduct = ((Spellbuyproduct)((Object[])proList.get(0))[1]);
    if (spellbuyproduct.isBuyable() == false) 
    {
      Struts2Utils.renderText("false", StringUtil.EARR_STRING);
    } else if (spellbuyproduct.getSpellbuyLimit().intValue() > 0)
    {
      if ((userId != null) && (!userId.equals("")))
      {
        int limitNum = 0;
        try
        {
          List<Randomnumber> dataList = spellbuyrecordService
        		  .getRandomNumberList(spellbuyproduct.getSpellbuyProductId().toString(), userId);
          for (Randomnumber randomnumber : dataList) {
            try
            {
              String[] randoms = randomnumber.getRandomNumber().split(",");
              limitNum += randoms.length;
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        Struts2Utils.renderText(""+(spellbuyproduct.getSpellbuyLimit().intValue() 
        		- limitNum), StringUtil.EARR_STRING);
      }
      else
      {
        Struts2Utils.renderText(""+(spellbuyproduct.getSpellbuyLimit()), StringUtil.EARR_STRING);
      }
    }
    else if (spellbuyproduct.getSpellbuyPrice().intValue() 
    		- spellbuyproduct.getSpellbuyCount().intValue() == 0) 
    {
      Struts2Utils.renderText("false", StringUtil.EARR_STRING);
    } else 
    {
      Struts2Utils.renderText(""+(spellbuyproduct.getSpellbuyPrice().intValue() 
    		  - spellbuyproduct.getSpellbuyCount().intValue()), StringUtil.EARR_STRING);
    }
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

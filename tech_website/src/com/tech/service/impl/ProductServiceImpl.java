package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Product;
import com.tech.pojo.Producttype;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.ProductService;
import com.tech.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl
  implements ProductService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void add(Product t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Product.class, id);
  }
  
  public void update(String hql)
  {
    baseDao.update(hql);
  }
  
  public Product findById(String id)
  {
    final StringBuffer hql = new StringBuffer("from Product product where product.productId=")
    .append(Integer.parseInt(id));
    return (Product)baseDao.loadObject(hql.toString());
  }
  
  public List<Product> query(String hql)
  {
    return (List<Product>)baseDao.query(hql);
  }
  
  public Pagination searchSpellbuyproduct(String keyword, String orderName, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.*,st.* from product pt,spellbuyproduct st where st.fkProductId=pt.productId");
    StringBuffer sql = new StringBuffer("select count(DISTINCT(pt.productId)) from product pt,spellbuyproduct st where st.fkProductId=pt.productId ");
    if (StringUtil.isNotBlank(keyword))
    {
      hql.append(" and pt.productName like '%" + keyword + "%'");
      sql.append(" and pt.productName like '%" + keyword + "%'");
    }
    if (orderName.equals("hot"))
    {
      hql.append(" and (st.spellbuyCount > (pt.productPrice/2))  GROUP by st.spellbuyProductId order by st.spellbuyCount desc");
      sql.append(" and (st.spellbuyCount > (pt.productPrice/2))");
    }
    if (orderName.equals("date")) {
      hql.append(" GROUP by st.spellbuyProductId order by st.spellbuyStartDate desc");
    }
    if (orderName.equals("price")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice desc");
    }
    if (orderName.equals("priceAsc")) {
      hql.append(" GROUP by st.spellbuyProductId order by pt.productPrice asc");
    }
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    entityMap.put("st", Spellbuyproduct.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Pagination searchProduct(String typeId, String keyword, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select pt.* from product pt where 1=1");
    StringBuffer sql = new StringBuffer("select count(*) from product pt where 1=1");
    if (StringUtil.isNotBlank(typeId))
    {
      hql.append(" and pt.productType = '" + typeId + "'");
      sql.append(" and pt.productType = '" + typeId + "'");
      StringBuffer _hql = new StringBuffer("from Producttype p where p.ftypeId ='" + typeId + "' and p.attribute70='type'");
      List<Producttype> objList = (List<Producttype>)baseDao.query(_hql.toString());
      if ((objList != null) && (objList.size() > 0)) {
        for (Producttype producttype : objList)
        {
          hql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
          sql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
        }
      }
    }
    if (StringUtil.isNotBlank(keyword))
    {
      hql.append(" and pt.productName like '%" + keyword + "%'");
      sql.append(" and pt.productName like '%" + keyword + "%'");
    }
    hql.append(" order by pt.productId desc");
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
  
  public Product findProductByName(String productName)
  {
    StringBuffer hql = new StringBuffer("from Product product where 1=1");
    if (StringUtils.isNotBlank(productName)) {
      hql.append(" and product.productName='" + productName + "'");
    }
    return (Product)baseDao.loadObject(hql.toString());
  }
  
  public Pagination ProductListByTypeIdList(String typeId, String orderName, int pageNo, int pageSize)
  {
    StringBuffer hql = new StringBuffer("select * from product pt where pt.status = 1");
    StringBuffer sql = new StringBuffer("select count(*) from product pt where pt.status = 1");
    if ((typeId != null) && (!typeId.equals("")))
    {
      hql.append(" and (1=2");
      sql.append(" and (1=2");
      hql.append(" or (pt.productType= '" + typeId + "')");
      sql.append(" or (pt.productType= '" + typeId + "')");
      StringBuffer _hql = new StringBuffer("from Producttype p where p.ftypeId ='" + typeId + "' and p.attribute70='type'");
      List<Producttype> objList = (List<Producttype>)baseDao.query(_hql.toString());
      if ((objList != null) && (objList.size() > 0)) {
        for (Producttype producttype : objList)
        {
          hql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
          sql.append(" or (pt.productType= '" + producttype.getTypeId() + "')");
        }
      }
      hql.append(")");
      sql.append(")");
    }
    Pagination page = new Pagination();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    Map<String, Class> entityMap = new HashMap();
    entityMap.put("pt", Product.class);
    List list = baseDao.sqlQuery(hql, entityMap, page);
    int resultCount = baseDao.getAllNum(sql);
    page.setList(list);
    page.setResultCount(resultCount);
    return page;
  }
}

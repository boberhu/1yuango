package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Producttype;
import com.tech.service.ProducttypeService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
public class ProducttypeServiceImpl implements ProducttypeService
{
  @Autowired
  @Qualifier("baseDao")
  BaseDAO baseDao;
  
  public void add(Producttype t)
  {
    baseDao.saveOrUpdate(t);
  }
  
  public void delete(Integer id)
  {
    baseDao.delById(Producttype.class, id);
  }
  
  public Producttype findById(String id)
  {
    StringBuffer hql = new StringBuffer("from Producttype p where p.attribute70='type'");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and p.typeId='" + id + "'");
    }
    return (Producttype)baseDao.loadObject(hql.toString());
  }
  
  public List<Producttype> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
  
  public List<Producttype> queryAllProductType()
  {
    String hql = " from Producttype p where p.attribute70='type'";
    return (List<Producttype>)baseDao.query(hql);
  }
  
  public List<Producttype> listByProductList()
  {
    String hql = " from Producttype p where p.ftypeId = '1000' and p.attribute70='type'";
    return (List<Producttype>)baseDao.query(hql);
  }
  
  public List<Producttype> listByProductListBybrand(String id)
  {
    String hql = " from Producttype p where p.ftypeId = '" + id + "' and p.attribute70='type'";
    return (List<Producttype>)baseDao.query(hql);
  }
  
  public List<Producttype> listByBrand(String id)
  {
    StringBuffer hql = new StringBuffer("from Producttype p where p.attribute70='brand'");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and p.ftypeId='" + id + "' ");
    }
    return (List<Producttype>)baseDao.query(hql.toString());
  }
  
  public Producttype findBrandById(String id)
  {
    StringBuffer hql = new StringBuffer("from Producttype p where p.attribute70='brand'");
    if (StringUtils.isNotBlank(id)) {
      hql.append(" and p.typeId='" + id + "'");
    }
    return (Producttype)baseDao.loadObject(hql.toString());
  }
  
  public Producttype findBrandByName(String name)
  {
    StringBuffer hql = new StringBuffer("from Producttype p where p.typeName='" + name + "' and p.attribute70='brand'");
    return (Producttype)baseDao.loadObject(hql.toString());
  }
  
  public Producttype findTypeByName(String name)
  {
    StringBuffer hql = new StringBuffer("from Producttype p where p.typeName='" + name + "' and p.attribute70='type'");
    return (Producttype)baseDao.loadObject(hql.toString());
  }

  @Override
  public List<Producttype> listSubs(final String ftypeId) {
	final List<Producttype> alls = new ArrayList<Producttype>();
	final StringBuffer hql = new StringBuffer();
	hql.append("from Producttype p where p.ftypeId='").append(ftypeId).append('\'');
	final List<Producttype> subs = (List<Producttype>)baseDao.query(hql.toString());
	alls.addAll(subs);
	return listSubs(alls, subs, hql);
  }
  
  private List<Producttype> listSubs(final List<Producttype> alls, final List<Producttype> parents, 
		  final StringBuffer hql) {
	  for(final Producttype type: parents){
		  hql.setLength(0);
		  hql.append("from Producttype p where p.ftypeId='").append(type.getTypeId()).append('\'');
		  final List<Producttype> subs = (List<Producttype>)baseDao.query(hql.toString());
		  if(subs.size() == 0){
			  continue;
		  }
		  alls.addAll(subs);
		  listSubs(alls, subs, hql);
	  }
	  return alls;
  }
  
}

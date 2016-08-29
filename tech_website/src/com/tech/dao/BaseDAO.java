package com.tech.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public abstract interface BaseDAO
{
  public abstract Session getSessions();
  
  public abstract Object loadById(Class paramClass, Serializable paramSerializable);
  
  public abstract Object loadObject(String paramString);
  
  Object loadObject(String hql, final String alias, final boolean lock);
  
  public abstract void delById(Class<?> paramClass, Serializable paramSerializable);
  
  public abstract void saveOrUpdate(Object paramObject);
  
  public abstract Object saveOrUpdateSQL(String paramString);
  
  public abstract List<?> listAll(String paramString);
  
  public abstract List<?> listAll(String paramString, int paramInt1, int paramInt2);
  
  public abstract int countAll(String paramString);
  
  public abstract List<?> query(String paramString);
  
  public abstract List<?> querySQL(String paramString);
  
  public abstract List<?> query(String hql, int pageNo, int pageSize);
  
  List<Object> query(String hql, int pageNo, int pageSize,
		  final String alias, boolean lock);
  
  public abstract List<?> query(String hql, String alias, boolean lock);
  
  public abstract int countQuery(String paramString);
  
  public abstract Object sumQuery(String hql);
  
  public abstract int update(String paramString);
  
  public abstract Pagination pageQuery(Pagination paramPagination, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2);
  
  public abstract List<?> callProc(String paramString, List<String> paramList);
  
  public abstract List sqlQuery(StringBuffer paramStringBuffer, Map<String, Class> paramMap, Pagination paramPagination);
  
  List sqlQuery(StringBuffer sql, Map<String, Class> entityMap, Pagination page, final boolean lock);
  
  public abstract List sqlQueryBean(StringBuffer paramStringBuffer, Map<String, Class> paramMap, Pagination paramPagination);
  
  public abstract int getAllNum(StringBuffer paramStringBuffer);
  
  public abstract BigDecimal getSelectSum(StringBuffer paramStringBuffer);
  
  public abstract List sqlQueryEntity(StringBuffer paramStringBuffer, Map<String, Class> paramMap);
  
  public abstract List sqlQueryEntityBean(StringBuffer paramStringBuffer, Map<String, Class> paramMap);
  
  public abstract List find(String paramString);
}

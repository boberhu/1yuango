package com.tech.dao;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tech.util.StringUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
@Repository("baseDao")
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO
{
  @Autowired
  @Qualifier("sessionFactory")
  public void setSessionBuilder(SessionFactory sessionFactory)
  {
    super.setSessionFactory(sessionFactory);
  }
  
  public int countAll(String clazz)
  {
    final String hql = "select count(*) from " + clazz + " as a";
    Query query = getSession().createQuery(hql);
    query.setMaxResults(1);
    Long count = (Long)query.uniqueResult();
    return count.intValue();
  }
  
  public int countQuery(final String hql)
  {
    final Query query = getSession().createQuery(hql);
    query.setMaxResults(1);
    final Long count = (Long)query.uniqueResult();
    if(count == null){
    	return 0;
    }
    return count.intValue();
  }
  
  public Object sumQuery(String hql)
  {
    Session session = getSession();
    Query query = session.createSQLQuery(hql);
    List list = query.list();
    if((null != list) && list.size()>0){
    	return list.get(0);
    }
    return null;
  }
  
  public List querySQL(String hql)
  {
    final String hql1 = hql;
    Query query = getSession().createSQLQuery(hql1);
    return query.list();
  }
  
  public void delById(Class clazz, Serializable id)
  {
	final Session session = getSession();
	session.delete(session.load(clazz, id));
  }
  
  public List listAll(String clazz)
  {
    return getSession().createQuery("from " + clazz + " as a order by a.id desc").list();
  }
  
  public List listAll(String clazz, int pageNo, int pageSize)
  {
    final int pNo = pageNo;
    final int pSize = pageSize;
    final String hql = "from " + clazz + " as a order by a.id desc";
    Query query = getSession().createQuery(hql);
    query.setMaxResults(pSize);
    query.setFirstResult((pNo - 1) * pSize);
    List<Object> result = query.list();
    return result;
  }
  
  public Object loadById(Class clazz, Serializable id)
  {
    return getSession().get(clazz, id);
  }
  
  public Object loadObject(String hql)
  {
    final String hql1 = hql;
    Object obj = null;
    Query query = getSession().createQuery(hql1);
    List list = query.list();
    if (list.size() > 0) {
      obj = list.get(0);
    }
    return obj;
  }
  
  public Object loadObject(String hql, final String alias, final boolean lock)
  {
	if(lock == false){
		return loadObject(hql);
	}
    final Query query = getSession().createQuery(hql);
    query.setLockMode(alias, LockMode.UPGRADE);
    final List list = query.list();
    if (list.size() > 0) {
      return list.get(0);
    }
    return null;
  }
  
  public List query(final String hql)
  {
    final Query query = getSession().createQuery(hql);
    return query.list();
  }
  
  public List query(final String hql, final String alias, boolean lock)
  {
	if(lock == false){
		return query(hql);
	}
    final Query query = getSession().createQuery(hql);
    query.setLockMode(alias, LockMode.UPGRADE);
    return query.list();
  }
  
  public List<Object> query(String hql, int pageNo, int pageSize)
  {
    final int pNo = pageNo;
    final int pSize = pageSize;
    final String hql1 = hql;
    Query query = getSession().createQuery(hql1);
    query.setMaxResults(pSize);
    query.setFirstResult((pNo - 1) * pSize);
    List<Object> result = query.list();
    return result;
  }
  
  @Override
  public List<Object> query(final String hql, final int pageNo, final int pageSize,
		  final String alias, boolean lock)
  {
	if(lock == false){
		return query(hql, pageNo, pageSize);
	}
    final Query query = getSession().createQuery(hql);
    query.setMaxResults(pageSize);
    query.setFirstResult((pageNo - 1) * pageSize);
    query.setLockMode(alias, LockMode.UPGRADE);
    return query.list();
  }
  
  @Transactional
  public void saveOrUpdate(Object obj)
  {
    getSession().saveOrUpdate(obj);
  }
  
  public Object saveOrUpdateSQL(String hql)
  {
    final String hql1 = hql;
    Object obj = null;
    Query query = getSession().createQuery(hql1);
    List<Object> list = query.list();
    if (list.size() > 0) {
      obj = list.get(0);
    }
    return obj;
  }
  
  public int update(String hql)
  {
    final String hql1 = hql;
    Query query = getSession().createQuery(hql1);
    return query.executeUpdate();
  }
  
  public Pagination pageQuery(Pagination pagination, StringBuffer hql, StringBuffer sql)
  {
    List<?> list = null;
    Session session = getSession();
    pagination.setResultCount(getAllNum(sql));
    Query query = session.createQuery(hql.toString());
    final int pageSize = pagination.getPageSize();
    query.setFirstResult(pagination.getFirstResult()).setMaxResults(pageSize);
    list = query.list();
    pagination.setList(list);
    
    return pagination;
  }
  
  public int getAllNum(StringBuffer sql)
  {
    Session session = getSession();
    BigInteger t = (BigInteger)session.createSQLQuery(sql.toString()).uniqueResult();
    if (t != null) {
      return t.intValue();
    }
    return 0;
  }
  
  public Session getSessions()
  {
    return getSession();
  }
  
  public List<?> callProc(String proName, List<String> paramList)
  {
    if (StringUtil.isBlank(proName)) {
      return null;
    }
    Query query = getSession().getNamedQuery(proName);
    if ((paramList != null) && (paramList.size() > 0))
    {
      int i = 0;
      for (String str : paramList)
      {
        query.setString(i, str);
        i++;
      }
    }
    return query.list();
  }
  
  public List sqlQuery(StringBuffer sql, Map<String, Class> entityMap, Pagination page)
  {
    if (StringUtil.isBlank(sql.toString())) {
      return null;
    }
    Session session = getSession();
    SQLQuery query = session.createSQLQuery(sql.toString());
    if ((entityMap != null) && (entityMap.size() > 0)) {
      for (String key : entityMap.keySet())
      {
        Class<?> value = (Class<?>)entityMap.get(key);
        query.addEntity(key, value);
      }
    }
    final int pageSize = page.getPageSize();
    query.setFirstResult(page.getFirstResult()).setMaxResults(pageSize);
    return query.list();
  }
  
  @Override
  public List sqlQuery(StringBuffer sql, Map<String, Class> entityMap, Pagination page, 
		  final boolean lock)
  {
    if (lock == false) {
      return sqlQuery(sql, entityMap, page);
    }
    final int pageSize = page.getPageSize();
    sql.append(" limit ").append(page.getFirstResult()).append(',').append(pageSize)
    .append(" for update");
    Session session = getSession();
    SQLQuery query = session.createSQLQuery(sql.toString());
    if ((entityMap != null) && (entityMap.size() > 0)) {
      for (String key : entityMap.keySet())
      {
        Class<?> value = (Class<?>)entityMap.get(key);
        query.addEntity(key, value);
      }
    }
    return query.list();
  }
  
  public List sqlQueryBean(StringBuffer sql, Map<String, Class> entityMap, Pagination page)
  {
    if (StringUtil.isBlank(sql.toString())) {
      return null;
    }
    Session session = null;
    try
    {
      session = getSession();
      SQLQuery query = session.createSQLQuery(sql.toString());
      if ((entityMap != null) && (entityMap.size() > 0)) {
        for (String key : entityMap.keySet())
        {
          Class<?> value = (Class<?>)entityMap.get(key);
          query.setResultTransformer(Transformers.aliasToBean(value));
        }
      }
      final int pageSize = page.getPageSize();
      query.setFirstResult(page.getFirstResult()).setMaxResults(pageSize);
      return query.list();
    }
    catch (DataAccessResourceFailureException e)
    {
      e.printStackTrace();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    catch (IllegalStateException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public BigDecimal getSelectSum(StringBuffer sql)
  {
    Session session = getSession();
    try
    {
      BigDecimal t = (BigDecimal)session.createSQLQuery(sql.toString()).uniqueResult();
      if (t != null) {
        return t;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public List sqlQueryEntity(StringBuffer sql, Map<String, Class> entityMap)
  {
    if (StringUtil.isBlank(sql.toString())) {
      return null;
    }
    Session session = null;
    try
    {
      session = getSession();
      SQLQuery query = session.createSQLQuery(sql.toString());
      if ((entityMap != null) && (entityMap.size() > 0)) {
        for (String key : entityMap.keySet())
        {
          Class value = (Class)entityMap.get(key);
          query.addEntity(key, value);
        }
      }
      return query.list();
    }
    catch (DataAccessResourceFailureException e)
    {
      e.printStackTrace();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    catch (IllegalStateException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public List sqlQueryEntityBean(StringBuffer sql, Map<String, Class> entityMap)
  {
    if (StringUtil.isBlank(sql.toString())) {
      return null;
    }
    Session session = null;
    try
    {
      session = getSession();
      SQLQuery query = session.createSQLQuery(sql.toString());
      if ((entityMap != null) && (entityMap.size() > 0)) {
        for (String key : entityMap.keySet())
        {
          Class value = (Class)entityMap.get(key);
          
          query.setResultTransformer(Transformers.aliasToBean(value));
        }
      }
      return query.list();
    }
    catch (DataAccessResourceFailureException e)
    {
      e.printStackTrace();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    catch (IllegalStateException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
 public List find(String hql)
  {
    try
    {
      return getSession().createQuery(hql).list();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
 
}

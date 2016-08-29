package com.tech.generics.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateGenericsDao<T, ID extends Serializable>
  extends HibernateDaoSupport
{
  private Class<T> pojoClass;
  
  public HibernateGenericsDao()
  {
    pojoClass = GenericsUtils.getSuperClassGenricType(getClass());
  }
  
  public Class<T> getPojoClass()
  {
    return pojoClass;
  }
  
  public String getPojoClassName()
  {
    return getPojoClass().getName();
  }
  
  public List<T> loadAll()
  {
    return getHibernateTemplate().loadAll(getPojoClass());
  }
  
  public List find(String hql, Object values)
  {
    return getHibernateTemplate().find(hql, values);
  }
  
  public List<T> findByCriteria(Criteria criteria)
  {
    List list = criteria.list();
    return transformResults(list);
  }
  
  public List<T> findByCriteria(final DetachedCriteria detachedCriteria)
  {
    return (List)getHibernateTemplate().execute(new HibernateCallback()
    {
      public Object doInHibernate(Session session)
        throws HibernateException
      {
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        List list = criteria.list();
        return HibernateGenericsDao.this.transformResults(list);
      }
    });
  }
  
  public List<T> findByExample(T instance)
  {
    List<T> results = getHibernateTemplate().findByExample(instance);
    return results;
  }
  
  public T findById(ID id)
  {
    return (T)getHibernateTemplate().get(getPojoClassName(), id);
  }
  
  public List<T> findByProperty(String propertyName, Object value)
  {
    String queryString = "from " + getPojoClassName() + " as model where model." + 
      propertyName + "= ?";
    return getHibernateTemplate().find(queryString, value);
  }
  
  public ID save(T transientInstance)
  {
    return (ID)getHibernateTemplate().save(transientInstance);
  }
  
  public void update(T transientInstance)
  {
    getHibernateTemplate().update(transientInstance);
  }
  
  public void delete(ID id)
  {
    T instance = findById(id);
    if (instance != null) {
      getHibernateTemplate().delete(instance);
    }
  }
  
  public void delete(T persistentInstance)
  {
    getHibernateTemplate().delete(persistentInstance);
  }
  
  public PaginationSupport findPageByCriteria(Criteria criteria, int pageSize, int startIndex)
  {
    int totalCount = getCountByCriteria(criteria);
    criteria.setProjection(null);
    List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
    items = transformResults(items);
    PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
    return ps;
  }
  
  public PaginationSupport findPageByCriteria(Criteria criteria)
  {
    return findPageByCriteria(criteria, 10, 0);
  }
  
  public PaginationSupport findPageByCriteria(Criteria criteria, int startIndex)
  {
    return findPageByCriteria(criteria, 10, startIndex);
  }
  
  public int getCountByCriteria(Criteria criteria)
  {
    Integer count = (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
    return count.intValue();
  }
  
  public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex)
  {
    return (PaginationSupport)getHibernateTemplate().execute(new HibernateCallback()
    {
      public Object doInHibernate(Session session)
        throws HibernateException
      {
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        int totalCount = ((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);
        List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        items = HibernateGenericsDao.this.transformResults(items);
        PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
        return ps;
      }
    });
  }
  
  public PaginationSupport findPageByCriteria(DetachedCriteria detachedCriteria)
  {
    return findPageByCriteria(detachedCriteria, 10, 0);
  }
  
  public PaginationSupport findPageByCriteria(DetachedCriteria detachedCriteria, int startIndex)
  {
    return findPageByCriteria(detachedCriteria, 10, startIndex);
  }
  
  public int getCountByCriteria(final DetachedCriteria detachedCriteria)
  {
    Integer count = (Integer)getHibernateTemplate().execute(new HibernateCallback()
    {
      public Object doInHibernate(Session session)
        throws HibernateException
      {
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        return criteria.setProjection(Projections.rowCount()).uniqueResult();
      }
    });
    return count.intValue();
  }
  
  public PaginationSupport findPageByQuery(String hql, int pageSize, int startIndex, Object values)
  {
    int totalCount = getCountByQuery(hql, values);
    if (totalCount < 1) {
      return new PaginationSupport(new ArrayList(0), 0);
    }
    Query query = createQuery(hql, new Object[] { values });
    List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
    PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
    return ps;
  }
  
  public PaginationSupport findPageByQuery(String hql, Object values)
  {
    return findPageByQuery(hql, 10, 0, values);
  }
  
  public PaginationSupport findPageByQuery(String hql, int startIndex, Object values)
  {
    return findPageByQuery(hql, 10, startIndex, values);
  }
  
  public int getCountByQuery(String hql, Object values)
  {
    String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
    List countlist = getHibernateTemplate().find(countQueryString, values);
    return ((Integer)countlist.get(0)).intValue();
  }
  
  public Criteria createCriteria(Criterion... criterions)
  {
    Criteria criteria = getSession().createCriteria(getPojoClass());
    for (Criterion c : criterions) {
      criteria.add(c);
    }
    return criteria;
  }
  
  public Criteria createCriteria(String orderBy, boolean isAsc, Criterion criterions)
  {
    Criteria criteria = createCriteria(new Criterion[] { criterions });
    if (isAsc) {
      criteria.addOrder(Order.asc(orderBy));
    } else {
      criteria.addOrder(Order.desc(orderBy));
    }
    return criteria;
  }
  
  public Query createQuery(String hql, Object... values)
  {
    Query query = getSession().createQuery(hql);
    for (int i = 0; i < values.length; i++) {
      query.setParameter(i, values[i]);
    }
    return query;
  }
  
  private static String removeSelect(String hql)
  {
    int beginPos = hql.toLowerCase().indexOf("from");
    return hql.substring(beginPos);
  }
  
  private static String removeOrders(String hql)
  {
    Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
    Matcher m = p.matcher(hql);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, "");
    }
    m.appendTail(sb);
    return sb.toString();
  }
  
  private List transformResults(List items)
  {
    if (items.size() > 0)
    {
      if ((items.get(0) instanceof Map))
      {
        ArrayList list = new ArrayList(items.size());
        for (int i = 0; i < items.size(); i++)
        {
          Map map = (Map)items.get(i);
          list.add(map.get("this"));
        }
        return list;
      }
      if ((items.get(0) instanceof Object[]))
      {
        ArrayList list = new ArrayList(items.size());
        int pos = 0;
        for (int i = 0; i < ((Object[])items.get(0)).length; i++) {
          if (((Object[])items.get(0))[i].getClass() == getPojoClass())
          {
            pos = i;
            break;
          }
        }
        for (int i = 0; i < items.size(); i++) {
          list.add(((Object[])items.get(i))[pos]);
        }
        return list;
      }
      return items;
    }
    return items;
  }
}

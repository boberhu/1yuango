package com.tech.service.impl;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Orderdetail;
import com.tech.service.OrderdetailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderdetailServiceImpl
  implements OrderdetailService
{
  @Autowired
  BaseDAO baseDAO;
  
  public void add(Orderdetail t)
  {
    baseDAO.saveOrUpdate(t);
  }
  
  public void delete(Integer id) {}
  
  public Orderdetail findById(String id)
  {
    return null;
  }
  
  public List<Orderdetail> query(String hql)
  {
    return null;
  }
  
  public void update(String hql) {}
}

package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.Orderdetailaddress;

public abstract interface OrderdetailaddressService
  extends TService<Orderdetailaddress, Integer>
{
  public abstract Pagination orderdetailaddressList(int paramInt1, int paramInt2);
}

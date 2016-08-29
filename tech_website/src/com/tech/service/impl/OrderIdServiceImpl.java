package com.tech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dao.BaseDAO;
import com.tech.pojo.OrderId;
import com.tech.service.OrderIdService;

@Service
public class OrderIdServiceImpl implements OrderIdService{
	@Autowired
	BaseDAO baseDAO;

	@Override
	public String addOxid() {
		final OrderId oid = new OrderId(System.currentTimeMillis());
		baseDAO.saveOrUpdate(oid);
		return oid.getOxid();
	}
	
}

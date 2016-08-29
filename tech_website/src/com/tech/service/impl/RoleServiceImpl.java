package com.tech.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.pojo.Role;
import com.tech.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	BaseDAO baseDao;
	
	public Role findById(String id) {
		StringBuffer hql = new StringBuffer("from Role where id=").append(Integer.parseInt(id));
		return (Role)baseDao.loadObject(hql.toString());
	}

	public void add(Role t)
	{
		baseDao.saveOrUpdate(t);
	}
	
	public void delete(Integer id) {
		baseDao.delById(Role.class, id);
	}
	
	public void update(String hql) {
		baseDao.update(hql);
	};
	
	public List<Role> query(String hql)
	{
		return (List<Role>)baseDao.query(hql);
	}
	
	public Pagination roleList(int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer(
				"select * from core_role role where 1=1");
		StringBuffer sql = new StringBuffer(
				"select count(*) from core_role");
		Pagination page = new Pagination();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Class> entityMap = new HashMap();
		entityMap.put("role", Role.class);
		List list = baseDao.sqlQuery(hql, entityMap, page);
		int resultCount = baseDao.getAllNum(sql);
		page.setList(list);
		page.setResultCount(resultCount);
		return page;
	}
}

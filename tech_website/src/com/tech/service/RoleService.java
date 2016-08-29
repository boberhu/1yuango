package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.Role;

public interface RoleService extends TService<Role, Integer> {

	public Pagination roleList(int pageNo, int pageSize);
	
}

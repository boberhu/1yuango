package com.tech.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.tech.dao.Pagination;
import com.tech.exception.AccountStatusException;
import com.tech.exception.BadCredentialsException;
import com.tech.exception.UsernameNotFoundException;
import com.tech.pojo.Admin;
import com.tech.pojo.Function;
import com.tech.pojo.Role;
import com.tech.pojo.User;

public interface AdminService extends TService<Admin, Integer>{
	/**
	 * 通过统一用户ID获得管理员
	 * 
	 * @param userId
	 * @return
	 */
	public Admin getByUserId(Integer userId);
	
	public Admin login(String userName, String password, String ip) 
			throws UsernameNotFoundException, BadCredentialsException, AccountStatusException;

	/**
	 * 通过登录名查找管理员
	 * 
	 * @param loginName
	 * @return
	 */
	public Admin getByLoginName(String loginName);

	/**
	 * 获得某站点管理员
	 * 
	 * @param webId
	 * @return
	 */
	public Pagination getAll(int page, int countPerPage);

	/**
	 * 获得登录状态的管理员
	 * 
	 * @param webId
	 * @param adminId
	 * @param userId
	 * @return
	 */
	//public Admin getLoginAdmin(Long adminId, Long userId);

	/**
	 * 获得登录状态的管理员
	 * 
	 * @param adminId
	 * @param userId
	 * @return
	 */
	//public Admin getLoginAdmin(Long adminId, Long userId, HttpSession session);

	/**
	 * 注册管理员
	 * 
	 * @param user
	 * @param admin
	 * @return
	 * @throws UserRegisterException
	 */
	public Admin register(User user, Set<Role> roleSet, Set<Function> funcSet);
	
	public Admin update(Admin admin, User user, Set<Role> roleSet, Set<Function> funcSet);
	
	public void deleteById(Serializable id);
}

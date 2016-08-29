package com.tech.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tech.action.RegisterAction;
import com.tech.dao.BaseDAO;
import com.tech.dao.Pagination;
import com.tech.exception.AccountStatusException;
import com.tech.exception.BadCredentialsException;
import com.tech.exception.UsernameNotFoundException;
import com.tech.pojo.Admin;
import com.tech.pojo.Function;
import com.tech.pojo.Product;
import com.tech.pojo.Role;
import com.tech.pojo.User;
import com.tech.service.AdminService;
import com.tech.service.FunctionService;
import com.tech.service.UserService;
import com.tech.util.DateUtil;
import com.tech.util.StringUtil;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	BaseDAO baseDao;
	
	public Pagination getAll(int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("select * from admin an order by adminId desc");
	    
	    Pagination page = new Pagination();
	    page.setPageNo(pageNo);
	    page.setPageSize(pageSize);
	    Map<String, Class> entityMap = new HashMap();
	    entityMap.put("an", Admin.class);
	    List list = baseDao.sqlQueryBean(hql, entityMap, page);
	    page.setList(list);
	    return page;
	}

	public Admin getByLoginName(String loginName) {
		User user = userService.adminUserByUserName(loginName);
		if (user == null) {
			return null;
		}
		return getByUserId(user.getUserId());
	}

	/*private Admin getLoginAdmin(Long adminId, Long userId,
			HttpSession session) {
		if (adminId == null || userId == null) {
			return null;
		}
		Admin admin = findById(adminId);
		admin = getByUserId(userId);
		// 设置session
		if (session != null) {
			session.setAttribute(Admin.ADMIN_KEY, admin.getId());
		} else {
			contextPvd.setSessionAttr(Admin.ADMIN_KEY, admin.getId());
		}
		Set<String> fiSet = functionService.getFunctionItems(admin.getId());
		if (session != null) {
			session.setAttribute(Admin.RIGHTS_KEY, fiSet);
		} else {
			contextPvd.setSessionAttr(Admin.RIGHTS_KEY, fiSet);
		}
		if (admin != null && admin.getAdminDisabled()) {
			throw new AdminDisabledException("管理员'" + admin.getLoginName()
					+ "'已经被禁用！");
		}
		return admin;
	}*/

	/*public Admin getLoginAdmin(Integer adminId, Integer userId) {
		return getLoginAdmin(adminId, userId, null);
	}*/

	public Admin getByUserId(Integer userId) {
		StringBuffer hql = new StringBuffer("from Admin a where a.user.userId=").append(userId);
	    return (Admin)baseDao.loadObject(hql.toString());
	}
	
	public Admin login(String userName, String password, String ip) 
			throws UsernameNotFoundException, BadCredentialsException, AccountStatusException{
		User user = userService.userByName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "+ userName);
		}
		String pwd = user.getUserPwd();
		if ((StringUtil.isNotBlank(password)) && (password.equals(pwd))){
			String date = DateUtil.DateTimeToStr(new Date());
			user.setIpAddress(ip);
			String ipLocation = RegisterAction.seeker.getAddress(ip);
			user.setIpLocation(ipLocation);
			user.setOldIpAddress(user.getIpAddress() + "(" + user.getIpLocation() + ")");
			user.setOldDate(user.getNewDate());
			user.setNewDate(date);
			userService.add(user);
		}else{
			throw new BadCredentialsException("password invalid");
		}
		if (user.getUserType() == null || !"1".equals(user.getUserType())){
			throw new AccountStatusException("account not activated");
		}
		
		Admin admin  = getByUserId(user.getUserId());
		if(admin==null || admin.getDisabled()==null || admin.getDisabled()){
			throw new AccountStatusException("account not activated");
		}
		return admin;
	}

	public Admin register(User user, Set<Role> roleSet, Set<Function> funcSet){
		Assert.notNull(user);
		userService.add(user);
		Admin oadmin = getByUserId(user.getUserId());
		if (oadmin != null) {
			return oadmin;
		} else {
			Admin admin = new Admin();
			initAdmin(admin);
			admin.setUser(user);
			admin.setRoles(roleSet);
			admin.setFunctions(funcSet);
			add(admin);
			return admin;
		}
	}
	
	public Admin update(Admin admin, User user, Set<Role> roleSet, Set<Function> funcSet){
		Assert.notNull(user);
		userService.add(user);
		Admin oadmin = getByUserId(user.getUserId());
		if (oadmin != null) {
			if(admin!=null && admin.getDisabled()!=null){
				oadmin.setDisabled(admin.getDisabled());
			}
			oadmin.setRoles(roleSet);
			oadmin.setFunctions(funcSet);
			add(oadmin);
		}
		return oadmin;
	}

	/**
	 * 初始化管理员
	 * 
	 * @param admin
	 */
	private void initAdmin(Admin admin) {
		admin.setLoginCount(0);
		admin.setDisabled(false);
		admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
	}

	public Admin findById(String id) {
		StringBuffer hql = new StringBuffer("from Admin where id=").append(Integer.parseInt(id));
		return (Admin)baseDao.loadObject(hql.toString());
	}

	public void deleteById(Serializable id) {
		baseDao.delById(Admin.class, id);
	}
	
	public void add(Admin t)
	{
		baseDao.saveOrUpdate(t);
	}
	
	public void delete(Integer id) {}
	
	public void update(String hql) {};
	
	public List<Admin> query(String hql)
	{
		return (List<Admin>)baseDao.query(hql);
	}
	
	@Autowired
	private UserService userService;
	@Autowired
	private FunctionService functionService;
}

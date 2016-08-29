package com.tech.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 在session的保存的key。
	 */
	public static final String ADMIN_KEY = "_admin_key";
	public static final String RIGHTS_KEY = "_rights_key";
	
	public static String REF = "Admin";
	public static String PROP_WEBSITE = "website";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_USER = "user";
	public static String PROP_ID = "id";
	
	// constructors
	public Admin() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public Admin (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public Admin (
		java.lang.Long id,
		User user,
		java.util.Date createTime,
		java.lang.Boolean disabled) {

		this.setId(id);
		this.setUser(user);
		this.setCreateTime(createTime);
		this.setDisabled(disabled);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.util.Date createTime;
	private java.util.Date loginTime;
    private java.lang.Integer loginCount;
	private java.lang.Boolean disabled;

	// many to one
	private User user;

	// collections
	private java.util.Set<Role> roles;
	private java.util.Set<Function> functions;
	/**
	 * 功能列表失效时间
	 */
	private static long FUNCTION_EXPIRED = 0;

	private long functionExpired = 0;

	/**
	 * 功能列表是否过期
	 * 
	 * @return
	 */
	public boolean isFuncExpired() {
		if (FUNCTION_EXPIRED > this.functionExpired) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新过期时间
	 */
	public void updateFuncExpired() {
		this.functionExpired = System.currentTimeMillis();
	}

	public static void funcChange() {
		FUNCTION_EXPIRED = System.currentTimeMillis();
	}

	/**
	 * 权限URL数组
	 */
	private Set<String> rights;

	public void setRights(List<Function> funcList) {
		if (rights != null) {
			rights.clear();
		} else {
			rights = new HashSet<String>();
		}
		for (Function f : funcList) {
			String url = f.getUrl();
			if (url != null && !url.trim().equals("")) {
				rights.add(url);
			}
			String urls = f.getFuncs();
			if (urls != null && !urls.trim().equals("")) {
				String[] urlArr = urls.split("@");
				for (String s : urlArr) {
					rights.add(s);
				}
			}
		}
	}

	/**
	 * 功能菜单
	 */
	private List<Function> menuFunctions;

	/**
	 * 是否通过权限验证
	 * 
	 * @param url
	 *            待验证的URL
	 * @return
	 */
	public boolean isPass(String url) {
		if (rights != null && url != null) {
			for (String s : rights) {
				if (s.equals(url)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isPass(String url, boolean openSuper) {
		// 超级管理员
		if (openSuper && getUser().getUserId().equals(1L)) {
			return true;
		}
		if (rights != null && url != null) {
			for (String s : rights) {
				if (s.equals(url)) {
					return true;
				}
			}
		}
		return false;
	}

	public void setMenuFunctions(List<Function> allFuncsList) {
		if (this.menuFunctions == null) {
			this.menuFunctions = new ArrayList<Function>();
		} else {
			this.menuFunctions.clear();
		}
		for (Function f : allFuncsList) {
			if (f.getMenu() && !this.menuFunctions.contains(f)) {
				this.menuFunctions.add(f);
			}
		}
	}

	/**
	 * 获得用户(登录)名
	 * 
	 * @return
	 */
	public String getUserName() {
		if (getUser() != null) {
			return getUser().getUserName();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得登录名
	 * 
	 * @return
	 */
	public String getLoginName() {
		if (getUser() != null) {
			return getUser().getUserName();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得真实名字
	 * 
	 * @return
	 */
	public String getRealName() {
		if (getUser() != null) {
			return getUser().getRealName();
		} else {
			return null;
		}
	}


	/* [CONSTRUCTOR MARKER END] */
	public List<Function> getMenuFunctions() {
		return menuFunctions;
	}

	public Set<String> getRights() {
		return this.rights;
	}

	private List<Function> allFunctions;

	public List<Function> getAllFunctions() {
		return allFunctions;
	}

	public void setAllFunctions(List<Function> allFunctions) {
		this.allFunctions = allFunctions;
	}
	
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ADMIN_ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public java.lang.Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(java.lang.Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * Return the value associated with the column: IS_DISABLED
	 */
	public java.lang.Boolean getDisabled () {
		return disabled;
	}

	/**
	 * Set the value related to the column: IS_DISABLED
	 * @param disabled the IS_DISABLED value
	 */
	public void setDisabled (java.lang.Boolean disabled) {
		this.disabled = disabled;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
	public User getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (User user) {
		this.user = user;
	}



	/**
	 * Return the value associated with the column: roles
	 */
	public java.util.Set<Role> getRoles () {
		return roles;
	}

	/**
	 * Set the value related to the column: roles
	 * @param roles the roles value
	 */
	public void setRoles (java.util.Set<Role> roles) {
		this.roles = roles;
	}

	public void addToroles (Role role) {
		if (null == getRoles()) setRoles(new java.util.TreeSet<Role>());
		getRoles().add(role);
	}



	/**
	 * Return the value associated with the column: functions
	 */
	public java.util.Set<Function> getFunctions () {
		return functions;
	}

	/**
	 * Set the value related to the column: functions
	 * @param functions the functions value
	 */
	public void setFunctions (java.util.Set<Function> functions) {
		this.functions = functions;
	}

	public void addTofunctions (Function function) {
		if (null == getFunctions()) setFunctions(new java.util.TreeSet<Function>());
		getFunctions().add(function);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Admin)) return false;
		else {
			Admin admin = (Admin) obj;
			if (null == this.getId() || null == admin.getId()) return false;
			else return (this.getId().equals(admin.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}
}
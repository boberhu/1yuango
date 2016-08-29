package com.tech.service.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dao.BaseDAO;
import com.tech.pojo.Function;
import com.tech.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	BaseDAO baseDao;
	
	public List<Function> getFunctions(Long adminId) {
		String hql = "select func from Function func where func.id in"
				+ " (select f1.id from Admin admin join admin.roles role join role.functions f1 where admin.id = "+adminId+") or func.id in "
				+ " (select f2.id from Admin admin join admin.functions f2 where admin.id = "+adminId+")"
				+ " order by func.priority asc";
		return (List<Function>)baseDao.query(hql);
	}

	public Set<String> getFunctionItems(Long adminId) {
		List<Function> funcList = getFunctions(adminId);
		Set<String> funcItemSet = new HashSet<String>();
		String f = null;
		String fs = null;
		String[] fa = null;
		for (Function function : funcList) {
			f = function.getUrl();
			if (!StringUtils.isBlank(f)) {
				funcItemSet.add(f);
			}
			fs = function.getFuncs();
			if (!StringUtils.isBlank(fs)) {
				fa = fs.split(Function.FUNC_SPLIT);
				for (String fas : fa) {
					funcItemSet.add(fas);
				}
			}
		}
		return funcItemSet;
	}

	public List<Function> getRoots() {
		String hql = "select func from Function func where func.parent.id is null order by func.priority asc";
		return (List<Function>)baseDao.query(hql);
	}
	
	public List<Function> getRoots(Long adminId,boolean fillChild) {
		String hql = "select func from Function func where func.parent.id is null and func.active=true and (func.id in"
				//+ " (select f1.id from Admin admin join admin.roles role join role.functions f1 where admin.id = "+adminId+") "
				+ " (select f1.id from Role role join role.functions f1 where role.id in (select r.id from Admin admin join admin.roles r where admin.id = "+adminId+") ) "
				+ "  or func.id in (select f2.id from Admin admin join admin.functions f2 where admin.id = "+adminId+"))"
				+ " order by func.priority asc";
		List<Function> roots = (List<Function>)baseDao.query(hql);
		if(fillChild)
			fillChildren(roots);
		return roots;
	}

	public List<Function> getChild(Long pid) {
		String hql = "select func from Function func where func.parent.id = "+pid+" order by func.priority asc";
		return (List<Function>)baseDao.query(hql);
	}
	
	public List<Function> getRoots(boolean fillChild) {
		List<Function> roots = getRoots();
		if(fillChild)
			fillChildren(roots);
		return roots;
	}
	
	private void fillChildren(List<Function> funcs) {
		if(funcs==null)
			return;
		for(Function func:funcs){
			List<Function> children = getChildren(func.getId(),false);
			fillChildren(children);
			func.setChild(new LinkedHashSet<Function>(children));
		}
	}
	
	public List<Function> getChildren(Long pid, boolean fillChild) {
		List<Function> children = getChild(pid);
		if(fillChild)
			fillChildren(children);
		return children;
	}

	/*public Object updateByUpdater(Updater updater) {
		Function bean = (Function) updater.getBean();
		Function f = findById(bean.getId().toString());
		Function pf = f.getParent();
		Function pbean = bean.getParent();
		// pbean!=null代表需要更新父节点。父节点不能等于自身。
		if (pbean != null && !f.getId().equals(pbean.getId())) {
			// pf!=null原父节点存在，处理原父节点的child
			if (pf != null && !pf.getId().equals(pbean.getId())) {
				pf.getChild().remove(f);
			}
			// pbean.getId()!=null新父节点存在，处理新父节点child
			if (pbean.getId() != null && !pbean.getId().equals(pf.getId())) {
				Function np = findById(pbean.getId().toString());
				np.addTochild(f);
			}
		}
		Function func = (Function) updateByUpdater(updater);
		return func;
	}*/

	public Function save(Function func) {
		Function p = func.getParent();
		if (p != null) {
			Long pid = p.getId();
			// 如果父节点ID为null则将父节点对象设置为null，以免hibernate发生错误。并直接保存对象。
			if (pid == null) {
				func.setParent(null);
				add(func);
			} else {
				func.setParent(p);
				findById(pid.toString()).addTochild(func);
			}
		}
		return func;
	}

	public Function findById(String id) {
		StringBuffer hql = new StringBuffer("from Function where id=").append(Integer.parseInt(id));
		return (Function)baseDao.loadObject(hql.toString());
	}

	public void add(Function t)
	{
		baseDao.saveOrUpdate(t);
	}
	
	public void delete(Integer id) {
		baseDao.delById(Function.class, id);
	}
	
	public void update(String hql) {
		baseDao.update(hql);
	};
	
	public List<Function> query(String hql)
	{
		return (List<Function>)baseDao.query(hql);
	}
	
}

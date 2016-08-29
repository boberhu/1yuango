package com.tech.service;

import java.util.List;
import java.util.Set;

import com.tech.pojo.Function;

public interface FunctionService {

	public List<Function> getFunctions(Long adminId);
	
	public Set<String> getFunctionItems(Long adminId);
	
	public List<Function> getRoots();
	
	public List<Function> getRoots(boolean fillChild);
	
	public List<Function> getRoots(Long adminId,boolean fillChild);
	
	public List<Function> getChild(Long pid);
	
	public Function save(Function func);
	
	
}

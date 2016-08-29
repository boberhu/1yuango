package com.tech.service;

import java.util.List;

public abstract interface TService<T, Serializable>
{
  public abstract void add(T paramT);
  
  public abstract void delete(Integer paramInteger);
  
  public abstract void update(String paramString);
  
  public abstract T findById(String paramString);
  
  public abstract List<T> query(String paramString);
  
}

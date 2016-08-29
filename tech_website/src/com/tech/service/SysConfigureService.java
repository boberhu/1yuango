package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.IndexImg;
import com.tech.pojo.Suggestion;
import com.tech.pojo.SysConfigure;

import java.util.List;

public abstract interface SysConfigureService
  extends TService<SysConfigure, Integer>
{
  public abstract List initializationIndexImgAll();
  
  public abstract List IndexImgAll();
  
  public abstract void addIndexImg(IndexImg paramIndexImg);
  
  public abstract IndexImg findByIndexImgId(String paramString);
  
  public abstract void delIndexImg(Integer paramInteger);
  
  public abstract void doSuggestion(Suggestion paramSuggestion);
  
  public abstract Pagination suggestionList(int paramInt1, int paramInt2);
  
  public abstract void delSuggestion(Integer paramInteger);
}

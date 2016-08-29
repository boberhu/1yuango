package com.tech.dao;

import java.io.Serializable;
import java.util.List;

public class Pagination
  implements Serializable
{
  public int pageSize = 10;
  private int pageNo = 0;
  private int pageCount = 0;
  private int resultCount = 0;
  private boolean isHaveNext = false;
  private boolean isHavePrevious = false;
  private int currentCount;
  private List<?> list = null;
  
  public Pagination() {}
  
  public Pagination(int pageNo, int pageSize, int resultCount, List<?> list)
  {
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    setResultCount(resultCount);
    this.list = list;
    setCurrentCount();
  }
  
  public void setResultCount(int resultCount)
  {
    if (resultCount <= 0) {
      return;
    }
    this.resultCount = resultCount;
    if (resultCount % pageSize == 0) {
      pageCount = (resultCount / pageSize);
    } else {
      pageCount = (resultCount / pageSize + 1);
    }
    if ((pageNo < pageCount) && (pageNo > 0)) {
      isHaveNext = true;
    }
    if ((pageNo > 1) && (pageNo <= pageCount)) {
      isHavePrevious = true;
    }
  }
  
  public int getFirstResult()
  {
    int firstNo = 1;
    firstNo = (pageNo - 1) * pageSize;
    return firstNo;
  }
  
  public int getEndResult()
  {
    int endNo = 1;
    if (pageNo == pageCount) {
      endNo = resultCount;
    } else if ((pageNo < pageCount) && (pageNo > 0)) {
      endNo = pageNo * pageSize;
    }
    return endNo;
  }
  
  public void setCurrentCount()
  {
    if (list != null) {
      currentCount = list.size();
    }
  }
  
  public int getPageSize()
  {
    return pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getPageNo()
  {
    return pageNo;
  }
  
  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public int getPageCount()
  {
    return pageCount;
  }
  
  public void setPageCount(int pageCount)
  {
    this.pageCount = pageCount;
  }
  
  public boolean isHaveNext()
  {
    return isHaveNext;
  }
  
  public void setHaveNext(boolean isHaveNext)
  {
    this.isHaveNext = isHaveNext;
  }
  
  public boolean isHavePrevious()
  {
    return isHavePrevious;
  }
  
  public void setHavePrevious(boolean isHavePrevious)
  {
    this.isHavePrevious = isHavePrevious;
  }
  
  public List<?> getList()
  {
    return list;
  }
  
  public void setList(List<?> list)
  {
    this.list = list;
  }
  
  public int getResultCount()
  {
    return resultCount;
  }
  
  public int getCurrentCount()
  {
    return currentCount;
  }
}

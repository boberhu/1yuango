package com.tech.generics.dao;

import java.util.List;

public class PaginationSupport
{
  public static final int PAGESIZE = 10;
  private int pageSize = 10;
  private List items;
  private int totalCount;
  private int[] indexes = new int[0];
  private int startIndex = 0;
  
  public PaginationSupport(List items, int totalCount)
  {
    setPageSize(10);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(0);
  }
  
  public PaginationSupport(List items, int totalCount, int startIndex)
  {
    setPageSize(10);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }
  
  public PaginationSupport(List items, int totalCount, int pageSize, int startIndex)
  {
    setPageSize(pageSize);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }
  
  public static int convertFromPageToStartIndex(int pageNo)
  {
    return (pageNo - 1) * 10;
  }
  
  public static int convertFromPageToStartIndex(int pageNo, int pageSize)
  {
    return (pageNo - 1) * pageSize;
  }
  
  public List getItems()
  {
    return items;
  }
  
  public void setItems(List items)
  {
    this.items = items;
  }
  
  public int getPageSize()
  {
    return pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getTotalCount()
  {
    return totalCount;
  }
  
  public void setTotalCount(int totalCount)
  {
    if (totalCount > 0)
    {
      this.totalCount = totalCount;
      int count = totalCount / pageSize;
      if (totalCount % pageSize > 0) {
        count++;
      }
      indexes = new int[count];
      for (int i = 0; i < count; i++) {
        indexes[i] = (pageSize * i);
      }
    }
    else
    {
      this.totalCount = 0;
    }
  }
  
  public int[] getIndexes()
  {
    return indexes;
  }
  
  public void setIndexes(int[] indexes)
  {
    this.indexes = indexes;
  }
  
  public int getStartIndex()
  {
    return startIndex;
  }
  
  public void setStartIndex(int startIndex)
  {
    if (totalCount <= 0) {
      this.startIndex = 0;
    } else if (startIndex >= totalCount) {
      this.startIndex = indexes[(indexes.length - 1)];
    } else if (startIndex < 0) {
      this.startIndex = 0;
    } else {
      this.startIndex = indexes[(startIndex / pageSize)];
    }
  }
  
  public int getNextIndex()
  {
    int nextIndex = getStartIndex() + pageSize;
    if (nextIndex >= totalCount) {
      return getStartIndex();
    }
    return nextIndex;
  }
  
  public int getPreviousIndex()
  {
    int previousIndex = getStartIndex() - pageSize;
    if (previousIndex < 0) {
      return 0;
    }
    return previousIndex;
  }
  
  public long getTotalPageCount()
  {
    if (totalCount % pageSize == 0) {
      return totalCount / pageSize;
    }
    return totalCount / pageSize + 1;
  }
  
  public long getCurrentPageNo()
  {
    return startIndex / pageSize + 1;
  }
  
  public boolean hasNextPage()
  {
    return getCurrentPageNo() < getTotalPageCount() - 1L;
  }
  
  public boolean hasPreviousPage()
  {
    return getCurrentPageNo() > 1L;
  }
}

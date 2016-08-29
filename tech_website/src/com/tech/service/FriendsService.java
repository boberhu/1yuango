package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.Friends;

public abstract interface FriendsService
  extends TService<Friends, Integer>
{
  public abstract Pagination getFriends(String paramString, int paramInt1, int paramInt2);
}

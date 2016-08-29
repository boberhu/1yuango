package com.tech.service;

import com.tech.pojo.Recommend;

import java.util.List;

public abstract interface RecommendService
  extends TService<Recommend, Integer>
{
  public abstract List getRecommend();
}

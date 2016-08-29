package com.tech.service;

import com.tech.pojo.Productimage;

import java.util.List;

public abstract interface ProductImageService
  extends TService<Productimage, Integer>
{
  public abstract List findByProductId(String paramString1, String paramString2);
}

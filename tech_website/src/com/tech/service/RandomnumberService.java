package com.tech.service;

import com.tech.pojo.Randomnumber;

import java.math.BigDecimal;
import java.util.List;

public abstract interface RandomnumberService
  extends TService<Randomnumber, Integer>
{
  public abstract List LotteryDetailByRandomnumber(Integer paramInteger1, Integer paramInteger2);
  
  public abstract BigDecimal RandomNumberByUserBuyCount(String userId, Integer spellbuyProductId);
  
  public abstract Randomnumber findRandomnumberByspellbuyrecordId(Integer spellbuyrecordId);
  
  public abstract Randomnumber getUserBuyCodeByBuyid(String paramString1, String paramString2);
  
  public abstract Randomnumber getUserBuyCodeByBuyid(String spellbuyrecordId);
  
  List<Randomnumber> query(String hql, String alias, boolean lock);
  
}

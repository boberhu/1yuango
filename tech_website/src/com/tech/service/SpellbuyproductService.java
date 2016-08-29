package com.tech.service;

import java.util.List;

import com.tech.dao.Pagination;
import com.tech.pojo.Collectproduct;
import com.tech.pojo.Spellbuyproduct;

@SuppressWarnings("rawtypes")
public abstract interface SpellbuyproductService
  extends TService<Spellbuyproduct, Integer>
{
  public abstract List findByProductId(int paramInt);
  
  public abstract List findBySpellbuyProductId(int paramInt);
  
  public abstract List findBylotteryDetailByProductId(int paramInt);
  
  public abstract Pagination upcomingAnnounced(int paramInt1, int paramInt2);
  
  public abstract Pagination upcomingAnnouncedByTop(int paramInt1, int paramInt2);
  
  public abstract Pagination productPeriodList(Integer paramInteger, int paramInt1, int paramInt2);
  
  public abstract Integer productPeriodListByCount(Integer paramInteger);
  
  public abstract Pagination getCollectGoodsList(String paramString, int paramInt1, int paramInt2);
  
  public abstract void addCollectGoods(Collectproduct paramCollectproduct);
  
  public abstract void delCollectGoods(Integer paramInteger);
  
  public abstract List checkCollectGoods(Integer paramInteger, String paramString);
  
  public abstract Spellbuyproduct findSpellbuyproductLastPeriod(Integer paramInteger);
  
  public abstract Spellbuyproduct findSpellbuyproductByStatus(Integer paramInteger);
  
  public abstract Pagination announcedProduct(int paramInt1, int paramInt2);
  
  public abstract List loadAllByType();
  
  public abstract List loadAll();
  
  public abstract List UpdateLatestlotteryGetList();
  
  public abstract List findSpellbuyproductByProductIdIsStatus(Integer paramInteger);
  
  public abstract List getIndexNewProduct();
  
  public abstract List getGoodsPeriodInfo(Integer paramInteger1, Integer paramInteger2);
 
  List findByProductId(int productId, boolean lock);
  
  Spellbuyproduct getByProductId(int productId);
  
  Spellbuyproduct getByProductId(int productId, boolean lock);
  
  public abstract  List<Object[]> mayfullBuyProducts(int pageNo, int pageSize, boolean lock);

  public abstract void generateNextPeriods(int nr);
  
}

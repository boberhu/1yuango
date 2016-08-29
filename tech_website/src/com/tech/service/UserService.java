package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.Consumetable;
import com.tech.pojo.ProductCart;
import com.tech.pojo.ProductJSON;
import com.tech.pojo.User;
import com.tech.pojo.Userbyaddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes"})
public abstract interface UserService extends TService<User, Integer>
{
  public abstract User phoneLogin(String paramString1, String paramString2);
  
  public abstract User mailLogin(String paramString1, String paramString2);
  
  public abstract User userByName(String paramString);
  
  public abstract User userByWeixinOpenId(String weixinOpenId);
  
  public abstract User userByWebWxOpenId(String webwxOpenId);
  
  public abstract User userByWeixinUnionId(String weixinUnionId);
  
  public abstract User isCheckName(String paramString);
  
  public abstract User userByIsUserName(String paramString);
  
  public abstract User adminUserByUserName(String userName);
  
  public abstract User isUserName(String paramString1, String paramString2);
  
  public abstract List getUserbyaddress(String paramString);
  
  public abstract void addAddress(Userbyaddress paramUserbyaddress);
  
  public abstract void delAddress(Integer paramInteger);
  
  public abstract Userbyaddress findAddressById(Integer paramInteger);
  
  public abstract void setDefaultAddress(String paramString, Integer paramInteger);
  
  public abstract void defaultAddress(String paramString, Integer paramInteger);
  
  public abstract List loadAll();
  
  public abstract Pagination getCollectGoodsList(String paramString, int paramInt1, int paramInt2);
  
  public abstract void delCollectGoods(Integer paramInteger);
  
  public abstract Pagination getInvitedList(String paramString, int paramInt1, int paramInt2);
  
  public abstract List getInvitedListByData(String paramString);
  
  public abstract Integer getInvitedListByCount(String paramString);
  
  public abstract Pagination adminUserList(String paramString1, String paramString2, String paramString3, 
		  int paramInt1, int paramInt2);
  
  public abstract Integer getCountUser();
  
  public abstract User isNotOpenId(String paramString);
  
  public BigDecimal totalBalance();
  
  public BigDecimal totalIntegral();
  
  /**
   * Add an order.
   * 
   * @param userId
   * @param products
   * @param cartList
   * @param consumetable
   * @param integral
   * 
   * @return a consume-table object
   * @since 2015-09-09
   * @throws com.tech.exception.RuleViolationException
   */
  public abstract Consumetable  addOrder(String userId, Map<String, Integer> products,
		  List<ProductCart> cartList, Consumetable consumetable, String integral);
  
  /**
   * A service that pays by balance-integral or handles after paying by the 3rd part.
   * 
   * @param userId
   * @param productCartList
   * @param successCartList
   * @param userPayType
   * @param integral
   * @param bankMoney
   * @param useBalance
   * @param orderno
   * 
   * @return the pay-user
   * @throws com.tech.exception.RuleViolationException
   */
  User payOrPaid(String userId, List<ProductCart> productCartList, List<ProductJSON> successCartList,
		  Integer userPayType, String integral, Integer bankMoney, String useBalance, String orderno);
 
  User buyVirtually(User user, Integer productId, int buyCount);
  
  User findById(String id, final boolean lock);
  
}

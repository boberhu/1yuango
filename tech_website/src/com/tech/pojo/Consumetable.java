package com.tech.pojo;

import java.io.Serializable;

public class Consumetable implements Serializable
{
  private static final long serialVersionUID = -6016333280612241403L;
  // const
  public static final Integer UPAY_TYPE_BALANCE = Integer.valueOf(1);
  public static final Integer UPAY_TYPE_WELFARE = Integer.valueOf(2);
  public static final Integer UPAY_TYPE_BALWELF = Integer.valueOf(3);
  public static final Integer UPAY_TYPE_CYBBANK = Integer.valueOf(4);
  /** 支付状态 - 未支付。*/
  public static final Integer PAY_STAT_NPAID  = Integer.valueOf(0);
  /** 支付状态 - 已支付。*/
  public static final Integer PAY_STAT_PAID   = Integer.valueOf(1);
  /** 支付状态 - 支付超时。*/
  public static final Integer PAY_STAT_TMO    = Integer.valueOf(2);
  /** 支付状态 - 已支付但超时。*/
  public static final Integer PAY_STAT_TMO_PAID = Integer.valueOf(3);
  
  // props
  private Integer id;
  private Double money;
  private String date;
  private Integer buyCount;
  private Integer userId;
  private String transactionId;
  private String outTradeNo;
  private String interfaceType;
  private Integer userPayType;
  private String  withold;       // 预扣明细
  private Integer payStatus;     // 支付状态
  // 网银支付时记录买家IP - 在收到支付成功通知时设置到“购买记录”中
  private String  buyIp;
  // ------------------------------------------------ 支付分布
  private Integer balance  = Integer.valueOf(0); // 余额支付额
  private Integer integral = Integer.valueOf(0); // 积分抵扣额
  private Integer bankMoney= Integer.valueOf(0); // 网银支付额
  
  public Consumetable() {}
  
  public Consumetable(Double money, String date, Integer userId)
  {
    this.money = money;
    this.date = date;
    this.userId = userId;
  }
  
  public Consumetable(Double money, String date, Integer buyCount, 
		  Integer userId, String transactionId, String outTradeNo, String interfaceType)
  {
    this.money = money;
    this.date = date;
    this.buyCount = buyCount;
    this.userId = userId;
    this.transactionId = transactionId;
    this.outTradeNo = outTradeNo;
    this.interfaceType = interfaceType;
  }
  
  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Double getMoney()
  {
    return money;
  }
  
  public void setMoney(Double money)
  {
    this.money = money;
  }
  
  public String getDate()
  {
    return date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public Integer getBuyCount()
  {
    return buyCount;
  }
  
  public void setBuyCount(Integer buyCount)
  {
    this.buyCount = buyCount;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String getTransactionId()
  {
    return transactionId;
  }
  
  public void setTransactionId(String transactionId)
  {
    this.transactionId = transactionId;
  }
  
  public String getOutTradeNo()
  {
    return outTradeNo;
  }
  
  public void setOutTradeNo(String outTradeNo)
  {
    this.outTradeNo = outTradeNo;
  }
  
  public String getInterfaceType()
  {
    return interfaceType;
  }
  
  public void setInterfaceType(String interfaceType)
  {
    this.interfaceType = interfaceType;
  }
  
  public Integer getUserPayType() {
    return userPayType;
  }

  public void setUserPayType(Integer userPayType) {
    this.userPayType = userPayType;
  }

  public void setWithold(String withold) {
	this.withold = withold;
  }
  
  public String getWithold(){
	return this.withold;
  }
  
  public Integer getPayStatus() {
	return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
	this.payStatus = payStatus;
  }
  
  public Integer getBalance() {
    return balance;
  }

  public void setBalance(Integer balance) {
    this.balance = balance;
  }

  public Integer getIntegral() {
    return integral;
  }

  public void setIntegral(Integer integral) {
    this.integral = integral;
  }

  public Integer getBankMoney() {
    return bankMoney;
  }

  public void setBankMoney(Integer bankMoney) {
    this.bankMoney = bankMoney;
  }
  
  public String getBuyIp() {
	return buyIp;
  }

  public void setBuyIp(String buyIp) {
	this.buyIp = buyIp;
  }

  public boolean isPayByT3rdPart() {
	return UPAY_TYPE_CYBBANK.equals(getUserPayType());
  }
  
  public final static boolean isPayByBalance(final Integer payType){
	  return UPAY_TYPE_BALANCE.equals(payType);
  }
  
  public final static boolean isPayByWelfare(final Integer payType){
	  return UPAY_TYPE_WELFARE.equals(payType);
  }
  
  public final static boolean isPayByBalWelf(final Integer payType){
	  return UPAY_TYPE_BALWELF.equals(payType);
  }
  
  public final static boolean isPayByCybBank(final Integer payType){
	  return UPAY_TYPE_CYBBANK.equals(payType);
  }

  public boolean isNotPaid() {
	return PAY_STAT_NPAID.equals(getPayStatus());
  }
  
  public boolean isPaid() {
	return PAY_STAT_PAID.equals(getPayStatus());
  }
  
  public boolean isTimeo() {
	return PAY_STAT_TMO.equals(getPayStatus());
  }
  
  public boolean isTimeoPaid() {
	return PAY_STAT_TMO_PAID.equals(getPayStatus());
  }
  
}

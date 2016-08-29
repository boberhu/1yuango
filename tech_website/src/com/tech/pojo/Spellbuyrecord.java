package com.tech.pojo;

import java.io.Serializable;

public class Spellbuyrecord implements Serializable
{
  private static final long serialVersionUID = -2813575554151965676L;
  // const
  public static final Integer BUYSRC_PC   = Integer.valueOf(0);
  /** 手机触屏。*/
  public static final Integer BUYSRC_TSMB = Integer.valueOf(1);
  public static final Integer BUYSRC_ANDROID = Integer.valueOf(2);
  public static final Integer BUYSRC_IOS     = Integer.valueOf(3);
  public static final Integer BUYSRC_WECHAT  = Integer.valueOf(4);
  
  /** 拼购 状态 - 未完成。*/
  public static final Integer BUYSTAT_UNFINISHED = Integer.valueOf(0);
  /** 拼购 状态 - 完成。*/
  public static final Integer BUYSTAT_FINISHED   = Integer.valueOf(1);
  
  public static final Integer WINSTAT_NOT_WON       = Integer.valueOf(0);
  public static final Integer WINSTAT_WON           = Integer.valueOf(1);
  /** 未中奖差价购买。*/
  public static final Integer WINSTAT_POST_PURCHASE = Integer.valueOf(2);
  
  // props
  private Integer spellbuyRecordId;
  private Integer fkSpellbuyProductId;
  private Integer buyer;
  private Integer buyPrice;
  private String buyDate;
  private String spRandomNo;
  private String spWinningStatus;
  private String buyStatus;
  private String buyIp;
  private String buyLocal;
  private Integer buySource;
  private String attribute66;
  private String attribute67;
  
  public Spellbuyrecord() {}
  
  public Spellbuyrecord(Integer fkSpellbuyProductId, Integer buyer, Integer buyPrice, 
		  String buyDate, String spRandomNo, String spWinningStatus, String buyStatus)
  {
    this.fkSpellbuyProductId = fkSpellbuyProductId;
    this.buyer = buyer;
    this.buyPrice = buyPrice;
    this.buyDate = buyDate;
    this.spRandomNo = spRandomNo;
    this.spWinningStatus = spWinningStatus;
    this.buyStatus = buyStatus;
  }
  
  public Spellbuyrecord(Integer fkSpellbuyProductId, Integer buyer, Integer buyPrice, String buyDate, String spRandomNo, String spWinningStatus, String buyStatus, String buyIp, String buyLocal, Integer buySource, String attribute66, String attribute67)
  {
    this.fkSpellbuyProductId = fkSpellbuyProductId;
    this.buyer = buyer;
    this.buyPrice = buyPrice;
    this.buyDate = buyDate;
    this.spRandomNo = spRandomNo;
    this.spWinningStatus = spWinningStatus;
    this.buyStatus = buyStatus;
    this.buyIp = buyIp;
    this.buyLocal = buyLocal;
    this.buySource = buySource;
    this.attribute66 = attribute66;
    this.attribute67 = attribute67;
  }
  
  public Integer getSpellbuyRecordId()
  {
    return spellbuyRecordId;
  }
  
  public void setSpellbuyRecordId(Integer spellbuyRecordId)
  {
    this.spellbuyRecordId = spellbuyRecordId;
  }
  
  public Integer getFkSpellbuyProductId()
  {
    return fkSpellbuyProductId;
  }
  
  public void setFkSpellbuyProductId(Integer fkSpellbuyProductId)
  {
    this.fkSpellbuyProductId = fkSpellbuyProductId;
  }
  
  public Integer getBuyer()
  {
    return buyer;
  }
  
  public void setBuyer(Integer buyer)
  {
    this.buyer = buyer;
  }
  
  public Integer getBuyPrice()
  {
    return buyPrice;
  }
  
  public void setBuyPrice(Integer buyPrice)
  {
    this.buyPrice = buyPrice;
  }
  
  public String getBuyDate()
  {
    return buyDate;
  }
  
  public void setBuyDate(String buyDate)
  {
    this.buyDate = buyDate;
  }
  
  public String getSpRandomNo()
  {
    return spRandomNo;
  }
  
  public void setSpRandomNo(String spRandomNo)
  {
    this.spRandomNo = spRandomNo;
  }
  
  public String getSpWinningStatus()
  {
    return spWinningStatus;
  }
  
  public void setSpWinningStatus(String spWinningStatus)
  {
    this.spWinningStatus = spWinningStatus;
  }
  
  public String getBuyStatus()
  {
    return buyStatus;
  }
  
  public void setBuyStatus(String buyStatus)
  {
    this.buyStatus = buyStatus;
  }
  
  public String getBuyIp()
  {
    return buyIp;
  }
  
  public void setBuyIp(String buyIp)
  {
    this.buyIp = buyIp;
  }
  
  public String getBuyLocal()
  {
    return buyLocal;
  }
  
  public void setBuyLocal(String buyLocal)
  {
    this.buyLocal = buyLocal;
  }
  
  public Integer getBuySource()
  {
    return buySource;
  }
  
  public void setBuySource(Integer buySource)
  {
    this.buySource = buySource;
  }
  
  public String getAttribute66()
  {
    return attribute66;
  }
  
  public void setAttribute66(String attribute66)
  {
    this.attribute66 = attribute66;
  }
  
  public String getAttribute67()
  {
    return attribute67;
  }
  
  public void setAttribute67(String attribute67)
  {
    this.attribute67 = attribute67;
  }
}

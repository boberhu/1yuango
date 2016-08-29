package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class ProductProvider
  extends AlipayObject
{
  private static final long serialVersionUID = 2265116749253232755L;
  @ApiField("agency")
  private String agency;
  @ApiField("agency_name")
  private String agencyName;
  @ApiField("biz_type")
  private String bizType;
  @ApiField("id")
  private String id;
  @ApiField("sub_biz_type")
  private String subBizType;
  @ApiField("sub_operator")
  private String subOperator;
  @ApiField("sub_operator_name")
  private String subOperatorName;
  
  public String getAgency()
  {
    return agency;
  }
  
  public void setAgency(String agency)
  {
    this.agency = agency;
  }
  
  public String getAgencyName()
  {
    return agencyName;
  }
  
  public void setAgencyName(String agencyName)
  {
    this.agencyName = agencyName;
  }
  
  public String getBizType()
  {
    return bizType;
  }
  
  public void setBizType(String bizType)
  {
    this.bizType = bizType;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getSubBizType()
  {
    return subBizType;
  }
  
  public void setSubBizType(String subBizType)
  {
    this.subBizType = subBizType;
  }
  
  public String getSubOperator()
  {
    return subOperator;
  }
  
  public void setSubOperator(String subOperator)
  {
    this.subOperator = subOperator;
  }
  
  public String getSubOperatorName()
  {
    return subOperatorName;
  }
  
  public void setSubOperatorName(String subOperatorName)
  {
    this.subOperatorName = subOperatorName;
  }
}

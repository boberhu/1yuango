package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class QueryInstBillDetail
  extends AlipayObject
{
  private static final long serialVersionUID = 1843531865943841451L;
  @ApiField("key")
  private String key;
  @ApiField("name")
  private String name;
  @ApiField("value")
  private String value;
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getValue()
  {
    return value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
}

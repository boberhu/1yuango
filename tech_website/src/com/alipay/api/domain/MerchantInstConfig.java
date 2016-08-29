package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class MerchantInstConfig
  extends AlipayObject
{
  private static final long serialVersionUID = 2214895235421719246L;
  @ApiField("en_name")
  private String enName;
  @ApiField("order_type")
  private String orderType;
  @ApiField("scene")
  private String scene;
  @ApiField("zh_name")
  private String zhName;
  
  public String getEnName()
  {
    return enName;
  }
  
  public void setEnName(String enName)
  {
    this.enName = enName;
  }
  
  public String getOrderType()
  {
    return orderType;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getScene()
  {
    return scene;
  }
  
  public void setScene(String scene)
  {
    this.scene = scene;
  }
  
  public String getZhName()
  {
    return zhName;
  }
  
  public void setZhName(String zhName)
  {
    this.zhName = zhName;
  }
}

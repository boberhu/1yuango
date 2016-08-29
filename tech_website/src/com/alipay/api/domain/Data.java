package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class Data
  extends AlipayObject
{
  private static final long serialVersionUID = 7178396717935949573L;
  @ApiListField("user_id_list")
  @ApiField("string")
  private List<String> userIdList;
  
  public List<String> getUserIdList()
  {
    return userIdList;
  }
  
  public void setUserIdList(List<String> userIdList)
  {
    this.userIdList = userIdList;
  }
}

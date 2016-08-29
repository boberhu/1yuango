package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayMobilePublicContactFollowListResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8666885566781237133L;
  @ApiField("code")
  private Long code;
  @ApiListField("contact_follow_list")
  @ApiField("string")
  private List<String> contactFollowList;
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
  {
    return code;
  }
  
  public void setContactFollowList(List<String> contactFollowList)
  {
    this.contactFollowList = contactFollowList;
  }
  
  public List<String> getContactFollowList()
  {
    return contactFollowList;
  }
}

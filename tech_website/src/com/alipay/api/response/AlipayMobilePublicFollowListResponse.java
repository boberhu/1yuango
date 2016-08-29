package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.Data;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicFollowListResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6771366722589834319L;
  @ApiField("code")
  private Long code;
  @ApiField("count")
  private String count;
  @ApiField("data")
  private Data data;
  @ApiField("next_user_id")
  private String nextUserId;
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
  {
    return code;
  }
  
  public void setCount(String count)
  {
    this.count = count;
  }
  
  public String getCount()
  {
    return count;
  }
  
  public void setData(Data data)
  {
    this.data = data;
  }
  
  public Data getData()
  {
    return data;
  }
  
  public void setNextUserId(String nextUserId)
  {
    this.nextUserId = nextUserId;
  }
  
  public String getNextUserId()
  {
    return nextUserId;
  }
}

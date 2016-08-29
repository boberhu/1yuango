package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AccountFreeze;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayUserAccountFreezeGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5425996434761356652L;
  @ApiListField("freeze_items")
  @ApiField("account_freeze")
  private List<AccountFreeze> freezeItems;
  @ApiField("total_results")
  private String totalResults;
  
  public void setFreezeItems(List<AccountFreeze> freezeItems)
  {
    this.freezeItems = freezeItems;
  }
  
  public List<AccountFreeze> getFreezeItems()
  {
    return freezeItems;
  }
  
  public void setTotalResults(String totalResults)
  {
    this.totalResults = totalResults;
  }
  
  public String getTotalResults()
  {
    return totalResults;
  }
}

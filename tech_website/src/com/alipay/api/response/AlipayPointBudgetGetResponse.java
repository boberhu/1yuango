package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayPointBudgetGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1245352925252721759L;
  @ApiField("budget_amount")
  private Long budgetAmount;
  
  public void setBudgetAmount(Long budgetAmount)
  {
    this.budgetAmount = budgetAmount;
  }
  
  public Long getBudgetAmount()
  {
    return budgetAmount;
  }
}

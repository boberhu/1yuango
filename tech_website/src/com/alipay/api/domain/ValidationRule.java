package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class ValidationRule
  extends AlipayObject
{
  private static final long serialVersionUID = 4418579194263571165L;
  @ApiField("error_msg")
  private String errorMsg;
  @ApiField("rule_text")
  private String ruleText;
  
  public String getErrorMsg()
  {
    return errorMsg;
  }
  
  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }
  
  public String getRuleText()
  {
    return ruleText;
  }
  
  public void setRuleText(String ruleText)
  {
    this.ruleText = ruleText;
  }
}

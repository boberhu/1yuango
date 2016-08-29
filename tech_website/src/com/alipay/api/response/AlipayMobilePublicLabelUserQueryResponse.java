package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicLabelUserQueryResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4763717517693781962L;
  @ApiField("code")
  private String code;
  @ApiField("label_ids")
  private String labelIds;
  @ApiField("msg")
  private String msg;
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public void setLabelIds(String labelIds)
  {
    this.labelIds = labelIds;
  }
  
  public String getLabelIds()
  {
    return labelIds;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
}

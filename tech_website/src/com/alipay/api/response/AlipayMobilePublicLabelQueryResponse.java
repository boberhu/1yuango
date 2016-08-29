package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayMobilePublicLabelQueryResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5763325959436892126L;
  @ApiField("code")
  private String code;
  @ApiListField("labels")
  @ApiField("string")
  private List<String> labels;
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
  
  public void setLabels(List<String> labels)
  {
    this.labels = labels;
  }
  
  public List<String> getLabels()
  {
    return labels;
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

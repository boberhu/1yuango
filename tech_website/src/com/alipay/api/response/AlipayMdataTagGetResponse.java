package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMdataTagGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3196359763334938598L;
  @ApiField("tag_values")
  private String tagValues;
  
  public void setTagValues(String tagValues)
  {
    this.tagValues = tagValues;
  }
  
  public String getTagValues()
  {
    return tagValues;
  }
}

package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.QueryInstBillInfo;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayEbppBillSearchResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6452969235986168114L;
  @ApiField("cachekey")
  private String cachekey;
  @ApiListField("inst_bill_info_list")
  @ApiField("query_inst_bill_info")
  private List<QueryInstBillInfo> instBillInfoList;
  
  public void setCachekey(String cachekey)
  {
    this.cachekey = cachekey;
  }
  
  public String getCachekey()
  {
    return cachekey;
  }
  
  public void setInstBillInfoList(List<QueryInstBillInfo> instBillInfoList)
  {
    this.instBillInfoList = instBillInfoList;
  }
  
  public List<QueryInstBillInfo> getInstBillInfoList()
  {
    return instBillInfoList;
  }
}

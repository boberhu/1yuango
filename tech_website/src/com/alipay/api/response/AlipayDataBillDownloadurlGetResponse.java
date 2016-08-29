package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayDataBillDownloadurlGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5533964845957123917L;
  @ApiField("bill_download_url")
  private String billDownloadUrl;
  
  public void setBillDownloadUrl(String billDownloadUrl)
  {
    this.billDownloadUrl = billDownloadUrl;
  }
  
  public String getBillDownloadUrl()
  {
    return billDownloadUrl;
  }
}

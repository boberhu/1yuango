package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AliTrustBasicInfo;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayTrustUserBasicinfoGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1385552719727115751L;
  @ApiField("ali_trust_basicinfo")
  private AliTrustBasicInfo aliTrustBasicinfo;
  @ApiField("ali_trust_user_basic_info")
  private AliTrustBasicInfo aliTrustUserBasicInfo;
  
  public void setAliTrustBasicinfo(AliTrustBasicInfo aliTrustBasicinfo)
  {
    this.aliTrustBasicinfo = aliTrustBasicinfo;
  }
  
  public AliTrustBasicInfo getAliTrustBasicinfo()
  {
    return aliTrustBasicinfo;
  }
  
  public void setAliTrustUserBasicInfo(AliTrustBasicInfo aliTrustUserBasicInfo)
  {
    this.aliTrustUserBasicInfo = aliTrustUserBasicInfo;
  }
  
  public AliTrustBasicInfo getAliTrustUserBasicInfo()
  {
    return aliTrustUserBasicInfo;
  }
}

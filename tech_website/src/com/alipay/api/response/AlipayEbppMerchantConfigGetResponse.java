package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.MerchantInstConfig;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayEbppMerchantConfigGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 4323281683328954485L;
  @ApiListField("inst_configs")
  @ApiField("merchant_inst_config")
  private List<MerchantInstConfig> instConfigs;
  @ApiField("merchant_user_id")
  private String merchantUserId;
  
  public void setInstConfigs(List<MerchantInstConfig> instConfigs)
  {
    this.instConfigs = instConfigs;
  }
  
  public List<MerchantInstConfig> getInstConfigs()
  {
    return instConfigs;
  }
  
  public void setMerchantUserId(String merchantUserId)
  {
    this.merchantUserId = merchantUserId;
  }
  
  public String getMerchantUserId()
  {
    return merchantUserId;
  }
}

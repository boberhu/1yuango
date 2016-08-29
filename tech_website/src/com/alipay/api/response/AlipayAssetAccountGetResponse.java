package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AssetAccountResult;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayAssetAccountGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3591666385544944164L;
  @ApiListField("asset_list")
  @ApiField("asset_account_result")
  private List<AssetAccountResult> assetList;
  
  public void setAssetList(List<AssetAccountResult> assetList)
  {
    this.assetList = assetList;
  }
  
  public List<AssetAccountResult> getAssetList()
  {
    return assetList;
  }
}

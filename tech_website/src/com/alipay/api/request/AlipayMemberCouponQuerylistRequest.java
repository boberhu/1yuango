package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMemberCouponQuerylistResponse;
import java.util.Map;

public class AlipayMemberCouponQuerylistRequest
  implements AlipayRequest<AlipayMemberCouponQuerylistResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String merchantInfo;
  private String pageNo;
  private String pageSize;
  private String status;
  private String userInfo;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setMerchantInfo(String merchantInfo)
  {
    this.merchantInfo = merchantInfo;
  }
  
  public String getMerchantInfo()
  {
    return merchantInfo;
  }
  
  public void setPageNo(String pageNo)
  {
    this.pageNo = pageNo;
  }
  
  public String getPageNo()
  {
    return pageNo;
  }
  
  public void setPageSize(String pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public String getPageSize()
  {
    return pageSize;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setUserInfo(String userInfo)
  {
    this.userInfo = userInfo;
  }
  
  public String getUserInfo()
  {
    return userInfo;
  }
  
  public String getApiVersion()
  {
    return apiVersion;
  }
  
  public void setApiVersion(String apiVersion)
  {
    this.apiVersion = apiVersion;
  }
  
  public void setTerminalType(String terminalType)
  {
    this.terminalType = terminalType;
  }
  
  public String getTerminalType()
  {
    return terminalType;
  }
  
  public void setTerminalInfo(String terminalInfo)
  {
    this.terminalInfo = terminalInfo;
  }
  
  public String getTerminalInfo()
  {
    return terminalInfo;
  }
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.member.coupon.querylist";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("merchant_info", merchantInfo);
    txtParams.put("page_no", pageNo);
    txtParams.put("page_size", pageSize);
    txtParams.put("status", status);
    txtParams.put("user_info", userInfo);
    if (udfParams != null) {
      txtParams.putAll(udfParams);
    }
    return txtParams;
  }
  
  public void putOtherTextParam(String key, String value)
  {
    if (udfParams == null) {
      udfParams = new AlipayHashMap();
    }
    udfParams.put(key, value);
  }
  
  public Class<AlipayMemberCouponQuerylistResponse> getResponseClass()
  {
    return AlipayMemberCouponQuerylistResponse.class;
  }
}

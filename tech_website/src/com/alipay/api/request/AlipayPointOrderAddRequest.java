package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPointOrderAddResponse;
import java.util.Date;
import java.util.Map;

public class AlipayPointOrderAddRequest
  implements AlipayRequest<AlipayPointOrderAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String memo;
  private String merchantOrderNo;
  private Date orderTime;
  private Long pointCount;
  private String userSymbol;
  private String userSymbolType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setOrderTime(Date orderTime)
  {
    this.orderTime = orderTime;
  }
  
  public Date getOrderTime()
  {
    return orderTime;
  }
  
  public void setPointCount(Long pointCount)
  {
    this.pointCount = pointCount;
  }
  
  public Long getPointCount()
  {
    return pointCount;
  }
  
  public void setUserSymbol(String userSymbol)
  {
    this.userSymbol = userSymbol;
  }
  
  public String getUserSymbol()
  {
    return userSymbol;
  }
  
  public void setUserSymbolType(String userSymbolType)
  {
    this.userSymbolType = userSymbolType;
  }
  
  public String getUserSymbolType()
  {
    return userSymbolType;
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
    return "alipay.point.order.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("memo", memo);
    txtParams.put("merchant_order_no", merchantOrderNo);
    txtParams.put("order_time", orderTime);
    txtParams.put("point_count", pointCount);
    txtParams.put("user_symbol", userSymbol);
    txtParams.put("user_symbol_type", userSymbolType);
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
  
  public Class<AlipayPointOrderAddResponse> getResponseClass()
  {
    return AlipayPointOrderAddResponse.class;
  }
}

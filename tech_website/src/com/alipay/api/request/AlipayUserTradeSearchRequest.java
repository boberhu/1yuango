package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayUserTradeSearchResponse;
import java.util.Map;

public class AlipayUserTradeSearchRequest
  implements AlipayRequest<AlipayUserTradeSearchResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String alipayOrderNo;
  private String endTime;
  private String merchantOrderNo;
  private String orderFrom;
  private String orderStatus;
  private String orderType;
  private String pageNo;
  private String pageSize;
  private String startTime;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setEndTime(String endTime)
  {
    this.endTime = endTime;
  }
  
  public String getEndTime()
  {
    return endTime;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setOrderFrom(String orderFrom)
  {
    this.orderFrom = orderFrom;
  }
  
  public String getOrderFrom()
  {
    return orderFrom;
  }
  
  public void setOrderStatus(String orderStatus)
  {
    this.orderStatus = orderStatus;
  }
  
  public String getOrderStatus()
  {
    return orderStatus;
  }
  
  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }
  
  public String getOrderType()
  {
    return orderType;
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
  
  public void setStartTime(String startTime)
  {
    this.startTime = startTime;
  }
  
  public String getStartTime()
  {
    return startTime;
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
    return "alipay.user.trade.search";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("alipay_order_no", alipayOrderNo);
    txtParams.put("end_time", endTime);
    txtParams.put("merchant_order_no", merchantOrderNo);
    txtParams.put("order_from", orderFrom);
    txtParams.put("order_status", orderStatus);
    txtParams.put("order_type", orderType);
    txtParams.put("page_no", pageNo);
    txtParams.put("page_size", pageSize);
    txtParams.put("start_time", startTime);
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
  
  public Class<AlipayUserTradeSearchResponse> getResponseClass()
  {
    return AlipayUserTradeSearchResponse.class;
  }
}

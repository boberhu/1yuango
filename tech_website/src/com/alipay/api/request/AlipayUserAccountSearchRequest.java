package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayUserAccountSearchResponse;
import java.util.Map;

public class AlipayUserAccountSearchRequest
  implements AlipayRequest<AlipayUserAccountSearchResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String endTime;
  private String fields;
  private String pageNo;
  private String pageSize;
  private String startTime;
  private String type;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setEndTime(String endTime)
  {
    this.endTime = endTime;
  }
  
  public String getEndTime()
  {
    return endTime;
  }
  
  public void setFields(String fields)
  {
    this.fields = fields;
  }
  
  public String getFields()
  {
    return fields;
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
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getType()
  {
    return type;
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
    return "alipay.user.account.search";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("end_time", endTime);
    txtParams.put("fields", fields);
    txtParams.put("page_no", pageNo);
    txtParams.put("page_size", pageSize);
    txtParams.put("start_time", startTime);
    txtParams.put("type", type);
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
  
  public Class<AlipayUserAccountSearchResponse> getResponseClass()
  {
    return AlipayUserAccountSearchResponse.class;
  }
}

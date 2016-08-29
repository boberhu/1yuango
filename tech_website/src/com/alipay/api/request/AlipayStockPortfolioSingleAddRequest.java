package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayStockPortfolioSingleAddResponse;
import java.util.Map;

public class AlipayStockPortfolioSingleAddRequest
  implements AlipayRequest<AlipayStockPortfolioSingleAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String symbol;
  private String userId;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setSymbol(String symbol)
  {
    this.symbol = symbol;
  }
  
  public String getSymbol()
  {
    return symbol;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public String getUserId()
  {
    return userId;
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
    return "alipay.stock.portfolio.single.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("symbol", symbol);
    txtParams.put("user_id", userId);
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
  
  public Class<AlipayStockPortfolioSingleAddResponse> getResponseClass()
  {
    return AlipayStockPortfolioSingleAddResponse.class;
  }
}

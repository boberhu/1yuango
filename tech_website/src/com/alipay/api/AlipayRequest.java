package com.alipay.api;

import java.util.Map;

public abstract interface AlipayRequest<T extends AlipayResponse>
{
  public abstract String getApiMethodName();
  
  public abstract Map<String, String> getTextParams();
  
  public abstract String getApiVersion();
  
  public abstract void setApiVersion(String paramString);
  
  public abstract String getTerminalType();
  
  public abstract void setTerminalType(String paramString);
  
  public abstract String getTerminalInfo();
  
  public abstract void setTerminalInfo(String paramString);
  
  public abstract String getProdCode();
  
  public abstract void setProdCode(String paramString);
  
  public abstract Class<T> getResponseClass();
}

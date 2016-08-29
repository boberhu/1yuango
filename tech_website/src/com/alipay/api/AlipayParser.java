package com.alipay.api;

public abstract interface AlipayParser<T extends AlipayResponse>
{
  public abstract T parse(String paramString)
    throws AlipayApiException;
  
  public abstract Class<T> getResponseClass()
    throws AlipayApiException;
}

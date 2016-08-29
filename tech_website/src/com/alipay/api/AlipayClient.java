package com.alipay.api;

public abstract interface AlipayClient
{
  public abstract <T extends AlipayResponse> T execute(AlipayRequest<T> paramAlipayRequest)
    throws AlipayApiException;
  
  public abstract <T extends AlipayResponse> T execute(AlipayRequest<T> paramAlipayRequest, String paramString)
    throws AlipayApiException;
}

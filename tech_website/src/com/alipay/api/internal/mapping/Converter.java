package com.alipay.api.internal.mapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;

public abstract interface Converter
{
  public abstract <T extends AlipayResponse> T toResponse(String paramString, Class<T> paramClass)
    throws AlipayApiException;
}

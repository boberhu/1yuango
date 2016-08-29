package com.alipay.api.internal.parser.json;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayParser;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.Converter;

public class ObjectJsonParser<T extends AlipayResponse>
  implements AlipayParser<T>
{
  private Class<T> clazz;
  
  public ObjectJsonParser(Class<T> clazz)
  {
    this.clazz = clazz;
  }
  
  public T parse(String rsp)
    throws AlipayApiException
  {
    Converter converter = new JsonConverter();
    return converter.toResponse(rsp, clazz);
  }
  
  public Class<T> getResponseClass()
  {
    return clazz;
  }
}

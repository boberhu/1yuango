package com.alipay.api.internal.parser.xml;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayParser;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.Converter;

public class ObjectXmlParser<T extends AlipayResponse>
  implements AlipayParser<T>
{
  private Class<T> clazz;
  
  public ObjectXmlParser(Class<T> clazz)
  {
    this.clazz = clazz;
  }
  
  public T parse(String rsp)
    throws AlipayApiException
  {
    Converter converter = new XmlConverter();
    return converter.toResponse(rsp, clazz);
  }
  
  public Class<T> getResponseClass()
  {
    return clazz;
  }
}

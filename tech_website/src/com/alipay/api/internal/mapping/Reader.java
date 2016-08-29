package com.alipay.api.internal.mapping;

import com.alipay.api.AlipayApiException;
import java.util.List;

public abstract interface Reader
{
  public abstract boolean hasReturnField(Object paramObject);
  
  public abstract Object getPrimitiveObject(Object paramObject);
  
  public abstract Object getObject(Object paramObject, Class<?> paramClass)
    throws AlipayApiException;
  
  public abstract List<?> getListObjects(Object paramObject1, Object paramObject2, Class<?> paramClass)
    throws AlipayApiException;
}

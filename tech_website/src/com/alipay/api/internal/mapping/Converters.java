package com.alipay.api.internal.mapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.util.StringUtils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class Converters
{
  public static boolean isCheckJsonType = false;
  private static final Set<String> baseFields = new HashSet<String>();
  
  static
  {
    baseFields.add("errorCode");
    baseFields.add("msg");
    baseFields.add("subCode");
    baseFields.add("subMsg");
    baseFields.add("body");
    baseFields.add("params");
    baseFields.add("success");
  }
  
  public static <T> T convert(Class<T> clazz, Reader reader)
    throws AlipayApiException
  {
    T rsp = null;
    try
    {
      rsp = clazz.newInstance();
      BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
      PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor pd : pds)
      {
        Method method = pd.getWriteMethod();
        if (method != null)
        {
          String itemName = pd.getName();
          String listName = null;
          Field field;
          if ((baseFields.contains(itemName)) && (AlipayResponse.class.isAssignableFrom(clazz))) {
            field = AlipayResponse.class.getDeclaredField(itemName);
          } else {
            field = clazz.getDeclaredField(itemName);
          }
          ApiField jsonField = (ApiField)field.getAnnotation(ApiField.class);
          if (jsonField != null) {
            itemName = jsonField.value();
          }
          ApiListField jsonListField = (ApiListField)field.getAnnotation(ApiListField.class);
          if (jsonListField != null) {
            listName = jsonListField.value();
          }
          if ((reader.hasReturnField(itemName)) || (
            (listName != null) && (reader.hasReturnField(listName))))
          {
            Class<?> typeClass = field.getType();
            if (String.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof String))
              {
                method.invoke(rsp, new Object[] { value.toString() });
              }
              else
              {
                if ((isCheckJsonType) && (value != null)) {
                  throw new AlipayApiException(itemName + " is not a String");
                }
                if (value != null) {
                  method.invoke(rsp, new Object[] { value.toString() });
                } else {
                  method.invoke(rsp, new Object[] { "" });
                }
              }
            }
            else if (Long.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof Long))
              {
                method.invoke(rsp, new Object[] { (Long)value });
              }
              else
              {
                if ((isCheckJsonType) && (value != null)) {
                  throw new AlipayApiException(itemName + " is not a Number(Long)");
                }
                if (StringUtils.isNumeric(value)) {
                  method.invoke(rsp, new Object[] { Long.valueOf(value.toString()) });
                }
              }
            }
            else if (Integer.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof Integer))
              {
                method.invoke(rsp, new Object[] { (Integer)value });
              }
              else
              {
                if ((isCheckJsonType) && (value != null)) {
                  throw new AlipayApiException(itemName + " is not a Number(Integer)");
                }
                if (StringUtils.isNumeric(value)) {
                  method.invoke(rsp, new Object[] { Integer.valueOf(value.toString()) });
                }
              }
            }
            else if (Boolean.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof Boolean))
              {
                method.invoke(rsp, new Object[] { (Boolean)value });
              }
              else
              {
                if ((isCheckJsonType) && (value != null)) {
                  throw new AlipayApiException(itemName + " is not a Boolean");
                }
                if (value != null) {
                  method.invoke(rsp, new Object[] { Boolean.valueOf(value.toString()) });
                }
              }
            }
            else if (Double.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof Double)) {
                method.invoke(rsp, new Object[] { (Double)value });
              } else if ((isCheckJsonType) && (value != null)) {
                throw new AlipayApiException(itemName + " is not a Double");
              }
            }
            else if (Number.class.isAssignableFrom(typeClass))
            {
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof Number)) {
                method.invoke(rsp, new Object[] { (Number)value });
              } else if ((isCheckJsonType) && (value != null)) {
                throw new AlipayApiException(itemName + " is not a Number");
              }
            }
            else if (Date.class.isAssignableFrom(typeClass))
            {
              DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
              Object value = reader.getPrimitiveObject(itemName);
              if ((value instanceof String)) {
                method.invoke(rsp, new Object[] { format.parse(value.toString()) });
              }
            }
            else if (List.class.isAssignableFrom(typeClass))
            {
              Type fieldType = field.getGenericType();
              if ((fieldType instanceof ParameterizedType))
              {
                ParameterizedType paramType = (ParameterizedType)fieldType;
                Type[] genericTypes = paramType.getActualTypeArguments();
                if ((genericTypes != null) && (genericTypes.length > 0) && 
                  ((genericTypes[0] instanceof Class)))
                {
                  Class<?> subType = (Class<?>)genericTypes[0];
                  List<?> listObjs = reader.getListObjects(listName, itemName, 
                    subType);
                  if (listObjs != null) {
                    method.invoke(rsp, new Object[] { listObjs });
                  }
                }
              }
            }
            else
            {
              Object obj = reader.getObject(itemName, typeClass);
              if (obj != null) {
                method.invoke(rsp, new Object[] { obj });
              }
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      throw new AlipayApiException(e);
    }
    return rsp;
  }
}

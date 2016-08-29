package com.alipay.api.internal.parser.json;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.Converter;
import com.alipay.api.internal.mapping.Converters;
import com.alipay.api.internal.mapping.Reader;
import com.alipay.api.internal.util.json.ExceptionErrorListener;
import com.alipay.api.internal.util.json.JSONReader;
import com.alipay.api.internal.util.json.JSONValidatingReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsonConverter
  implements Converter
{
  public <T extends AlipayResponse> T toResponse(String rsp, Class<T> clazz)
    throws AlipayApiException
  {
    JSONReader reader = new JSONValidatingReader(new ExceptionErrorListener());
    Object rootObj = reader.read(rsp);
    if ((rootObj instanceof Map))
    {
      Map<?, ?> rootJson = (Map)rootObj;
      Collection<?> values = rootJson.values();
      for (Object rspObj : values) {
        if ((rspObj instanceof Map))
        {
          Map<?, ?> rspJson = (Map)rspObj;
          return fromJson(rspJson, clazz);
        }
      }
    }
    return null;
  }
  
  public <T> T fromJson(final Map<?, ?> json, Class<T> clazz)
    throws AlipayApiException
  {
    return Converters.convert(clazz, new Reader()
    {
      public boolean hasReturnField(Object name)
      {
        return json.containsKey(name);
      }
      
      public Object getPrimitiveObject(Object name)
      {
        return json.get(name);
      }
      
      public Object getObject(Object name, Class<?> type)
        throws AlipayApiException
      {
        Object tmp = json.get(name);
        if ((tmp instanceof Map))
        {
          Map<?, ?> map = (Map)tmp;
          return fromJson(map, type);
        }
        return null;
      }
      
      public List<?> getListObjects(Object listName, Object itemName, Class<?> subType)
        throws AlipayApiException
      {
        List<Object> listObjs = null;
        
        Object listTmp = json.get(listName);
        if ((listTmp instanceof Map))
        {
          Map<?, ?> jsonMap = (Map)listTmp;
          Object itemTmp = jsonMap.get(itemName);
          if ((itemTmp == null) && (listName != null))
          {
            String listNameStr = listName.toString();
            itemTmp = jsonMap.get(listNameStr.substring(0, listNameStr.length() - 1));
          }
          if ((itemTmp instanceof List))
          {
            listObjs = new ArrayList();
            List<?> tmpList = (List)itemTmp;
            for (Object subTmp : tmpList) {
              if ((subTmp instanceof Map))
              {
                Map<?, ?> subMap = (Map)subTmp;
                Object subObj = fromJson(subMap, subType);
                if (subObj != null) {
                  listObjs.add(subObj);
                }
              }
              else if (!(subTmp instanceof List))
              {
                listObjs.add(subTmp);
              }
            }
          }
        }
        return listObjs;
      }
    });
  }
}

package com.wangyin.wepaypc.util;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class SignUtil
{
  private static final String charSet = "UTF-8";
  
  public static String signString(Object object, List<String> unSignKeyList)
    throws IllegalArgumentException, IllegalAccessException
  {
    TreeMap<String, Object> map = objectToMap(object);
    

    StringBuilder sb = new StringBuilder();
    for (String str : unSignKeyList) {
      map.remove(str);
    }
    Iterator iterator = map.entrySet().iterator();
    while (iterator.hasNext())
    {
      Map.Entry entry = (Map.Entry)iterator.next();
      sb.append(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue()) + "&");
    }
    String result = sb.toString();
    if (result.endsWith("&")) {
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }
  
  public static TreeMap<String, Object> objectToMap(Object object)
    throws IllegalArgumentException, IllegalAccessException
  {
    TreeMap<String, Object> map = new TreeMap();
    for (Class<?> cls = object.getClass(); cls != Object.class; cls = cls.getSuperclass())
    {
      Field[] fields = cls.getDeclaredFields();
      for (Field f : fields)
      {
        f.setAccessible(true);
        map.put(f.getName(), f.get(object));
      }
    }
    return map;
  }
}

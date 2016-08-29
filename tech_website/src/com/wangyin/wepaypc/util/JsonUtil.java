package com.wangyin.wepaypc.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;

public class JsonUtil
{
  private static final Logger logger = Logger.getLogger(JsonUtil.class);
  private static ObjectMapper mapper = new ObjectMapper();
  
  static
  {
    mapper.setSerializationInclusion(Include.NON_NULL);
  }
  
  public static String write2JsonStr(Object o)
    throws JsonProcessingException
  {
    String jsonStr = "";
    try
    {
      jsonStr = mapper.writeValueAsString(o);
    }
    catch (Exception e)
    {
      logger.error("|JsonProcessingException|", e);
    }
    return jsonStr;
  }
  
  public static Object json2Object(String json, Class<?> clazz)
  {
    try
    {
      return mapper.readValue(json, clazz);
    }
    catch (JsonParseException e)
    {
      logger.error("|JsonParseException|异常字符串|" + json, e);
    }
    catch (JsonMappingException e)
    {
      logger.error("|JsonMappingException|异常字符串|" + json, e);
    }
    catch (IOException e)
    {
      logger.error("|IOException|异常字符串|" + json, e);
    }
    return null;
  }
  
  public static Map<String, Object> json2Map(String json)
  {
    try
    {
      if ((json == null) || (json.length() == 0)) {
        return new HashMap();
      }
      return (Map)mapper.readValue(json, Map.class);
    }
    catch (JsonParseException e)
    {
      logger.error("|JsonParseException|异常字符串|" + json, e);
    }
    catch (JsonMappingException e)
    {
      logger.error("|JsonMappingException|异常字符串|" + json, e);
    }
    catch (IOException e)
    {
      logger.error("|IOException|异常字符串|" + json, e);
    }
    return new HashMap();
  }
  
  public static List<Map<String, Object>> jsonArray2List(String jsonArray)
  {
    try
    {
      return (List)mapper.readValue(jsonArray, List.class);
    }
    catch (JsonParseException e)
    {
      logger.error("|JsonParseException|异常字符串|" + jsonArray, e);
    }
    catch (JsonMappingException e)
    {
      logger.error("|JsonMappingException|异常字符串|" + jsonArray, e);
    }
    catch (IOException e)
    {
      logger.error("|IOException|异常字符串|" + jsonArray, e);
    }
    return new ArrayList();
  }
}

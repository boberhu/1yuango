package com.alipay.api.internal.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.FileItem;
import java.io.IOException;

public class RequestCheckUtils
{
  public static final String ERROR_CODE_ARGUMENTS_MISS = "40001";
  public static final String ERROR_CODE_ARGUMENTS_INVALID = "40002";
  
  public static void checkNotEmpty(Object value, String fieldName)
    throws AlipayApiException
  {
    if (value == null) {
      throw new AlipayApiException("40001", 
        "client-error:Missing Required Arguments:" + fieldName);
    }
    if (((value instanceof String)) && 
      (((String)value).trim().length() == 0)) {
      throw new AlipayApiException("40001", 
        "client-error:Missing Required Arguments:" + fieldName);
    }
  }
  
  public static void checkMaxLength(String value, int maxLength, String fieldName)
    throws AlipayApiException
  {
    if ((value != null) && 
      (value.length() > maxLength)) {
      throw new AlipayApiException("40002", 
        "client-error:Invalid Arguments:the length of " + fieldName + 
        " can not be larger than " + maxLength + ".");
    }
  }
  
  public static void checkMaxLength(FileItem fileItem, int maxLength, String fieldName)
    throws AlipayApiException
  {
    try
    {
      if ((fileItem != null) && (fileItem.getContent() != null)) {
        if (fileItem.getContent().length > maxLength) {
          throw new AlipayApiException("40002", 
            "client-error:Invalid Arguments:the length of " + fieldName + 
            " can not be larger than " + maxLength + ".");
        }
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static void checkMaxListSize(String value, int maxSize, String fieldName)
    throws AlipayApiException
  {
    if (value != null)
    {
      String[] list = value.split(",");
      if ((list != null) && (list.length > maxSize)) {
        throw new AlipayApiException("40002", 
          "client-error:Invalid Arguments:the listsize(the string split by \",\") of " + 
          fieldName + " must be less than " + maxSize + ".");
      }
    }
  }
  
  public static void checkMaxValue(Long value, long maxValue, String fieldName)
    throws AlipayApiException
  {
    if ((value != null) && 
      (value.longValue() > maxValue)) {
      throw new AlipayApiException("40002", 
        "client-error:Invalid Arguments:the value of " + fieldName + 
        " can not be larger than " + maxValue + ".");
    }
  }
  
  public static void checkMinValue(Long value, long minValue, String fieldName)
    throws AlipayApiException
  {
    if ((value != null) && 
      (value.longValue() < minValue)) {
      throw new AlipayApiException("40002", 
        "client-error:Invalid Arguments:the value of " + fieldName + 
        " can not be less than " + minValue + ".");
    }
  }
}

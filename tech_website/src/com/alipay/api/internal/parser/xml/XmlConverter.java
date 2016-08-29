package com.alipay.api.internal.parser.xml;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.Converter;
import com.alipay.api.internal.mapping.Converters;
import com.alipay.api.internal.mapping.Reader;
import com.alipay.api.internal.util.XmlUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Element;

public class XmlConverter
  implements Converter
{
  public <T extends AlipayResponse> T toResponse(String rsp, Class<T> clazz)
    throws AlipayApiException
  {
    Element root = XmlUtils.getRootElementFromString(rsp);
    return (T)getModelFromXML(root, clazz);
  }
  
  private <T> T getModelFromXML(final Element element, Class<T> clazz)
    throws AlipayApiException
  {
    if (element == null) {
      return null;
    }
    return Converters.convert(clazz, new Reader()
    {
      public boolean hasReturnField(Object name)
      {
        Element childE = XmlUtils.getChildElement(element, (String)name);
        return childE != null;
      }
      
      public Object getPrimitiveObject(Object name)
      {
        return XmlUtils.getElementValue(element, (String)name);
      }
      
      public Object getObject(Object name, Class<?> type)
        throws AlipayApiException
      {
        Element childE = XmlUtils.getChildElement(element, (String)name);
        if (childE != null) {
          return XmlConverter.this.getModelFromXML(childE, type);
        }
        return null;
      }
      
      public List<?> getListObjects(Object listName, Object itemName, Class<?> subType)
        throws AlipayApiException
      {
        List<Object> list = null;
        Element listE = XmlUtils.getChildElement(element, (String)listName);
        if (listE != null)
        {
          list = new ArrayList();
          List<Element> itemEs = XmlUtils.getChildElements(listE, (String)itemName);
          for (Element itemE : itemEs)
          {
            Object obj = null;
            String value = XmlUtils.getElementValue(itemE);
            if (String.class.isAssignableFrom(subType))
            {
              obj = value;
            }
            else if (Long.class.isAssignableFrom(subType))
            {
              obj = Long.valueOf(value);
            }
            else if (Integer.class.isAssignableFrom(subType))
            {
              obj = Integer.valueOf(value);
            }
            else if (Boolean.class.isAssignableFrom(subType))
            {
              obj = Boolean.valueOf(value);
            }
            else if (Date.class.isAssignableFrom(subType))
            {
              DateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
              try
              {
                obj = format.parse(value);
              }
              catch (ParseException e)
              {
                throw new AlipayApiException(e);
              }
            }
            else
            {
              obj = XmlConverter.this.getModelFromXML(itemE, subType);
            }
            if (obj != null) {
              list.add(obj);
            }
          }
        }
        return list;
      }
    });
  }
}

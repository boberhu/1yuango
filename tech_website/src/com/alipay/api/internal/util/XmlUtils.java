package com.alipay.api.internal.util;

import com.alipay.api.AlipayApiException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public final class XmlUtils
{
  private static final String XMLNS_XSI = "xmlns:xsi";
  private static final String XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
  private static final String LOGIC_YES = "yes";
  private static final String DEFAULT_ENCODE = "UTF-8";
  private static final String REG_INVALID_CHARS = "&#\\d+;";
  
  public static Document newDocument()
    throws AlipayApiException
  {
    Document doc = null;
    try
    {
      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }
    catch (ParserConfigurationException e)
    {
      throw new AlipayApiException(e);
    }
    return doc;
  }
  
  public static Document getDocument(File file)
    throws AlipayApiException
  {
    InputStream in = getInputStream(file);
    return getDocument(in);
  }
  
  public static Document getDocument(InputStream in)
    throws AlipayApiException
  {
    Document doc = null;
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder();
      doc = builder.parse(in);
    }
    catch (ParserConfigurationException e)
    {
      throw new AlipayApiException(e);
    }
    catch (SAXException e)
    {
      throw new AlipayApiException("XML_PARSE_ERROR", e);
    }
    catch (IOException e)
    {
      throw new AlipayApiException("XML_READ_ERROR", e);
    }
    finally
    {
      if (in != null) {
        try
        {
          in.close();
        }
        catch (IOException localIOException1) {}
      }
    }
    return doc;
  }
  
  public static Element createRootElement(String tagName)
    throws AlipayApiException
  {
    Document doc = newDocument();
    Element root = doc.createElement(tagName);
    doc.appendChild(root);
    return root;
  }
  
  public static Element getRootElementFromStream(InputStream in)
    throws AlipayApiException
  {
    return getDocument(in).getDocumentElement();
  }
  
  public static Element getRootElementFromFile(File file)
    throws AlipayApiException
  {
    return getDocument(file).getDocumentElement();
  }
  
  private static String getEncoding(String text)
  {
    String result = "UTF-8";
    
    String xml = text.trim();
    if (xml.startsWith("<?xml"))
    {
      int end = xml.indexOf("?>");
      String sub = xml.substring(0, end);
      StringTokenizer tokens = new StringTokenizer(sub, " =\"'");
      while (tokens.hasMoreTokens())
      {
        String token = tokens.nextToken();
        if ("encoding".equals(token))
        {
          if (!tokens.hasMoreTokens()) {
            break;
          }
          result = tokens.nextToken();
          

          break;
        }
      }
    }
    return result;
  }
  
  public static Element getRootElementFromString(String payload)
    throws AlipayApiException
  {
    if ((payload == null) || (payload.trim().length() < 1)) {
      throw new AlipayApiException("XML_PAYLOAD_EMPTY");
    }
    byte[] bytes = (byte[])null;
    try
    {
      payload = StringUtils.stripNonValidXMLCharacters(payload);
      String encodeString = getEncoding(payload);
      bytes = payload.getBytes(encodeString);
    }
    catch (UnsupportedEncodingException e)
    {
      throw new AlipayApiException("XML_ENCODE_ERROR", e);
    }
    InputStream in = new ByteArrayInputStream(bytes);
    return getDocument(in).getDocumentElement();
  }
  
  public static List<Element> getElements(Element parent, String tagName)
  {
    NodeList nodes = parent.getElementsByTagName(tagName);
    List<Element> elements = new ArrayList();
    for (int i = 0; i < nodes.getLength(); i++)
    {
      Node node = nodes.item(i);
      if ((node instanceof Element)) {
        elements.add((Element)node);
      }
    }
    return elements;
  }
  
  public static Element getElement(Element parent, String tagName)
  {
    List<Element> children = getElements(parent, tagName);
    if (children.isEmpty()) {
      return null;
    }
    return (Element)children.get(0);
  }
  
  public static List<Element> getChildElements(Element parent, String tagName)
  {
    NodeList nodes = parent.getElementsByTagName(tagName);
    List<Element> elements = new ArrayList();
    for (int i = 0; i < nodes.getLength(); i++)
    {
      Node node = nodes.item(i);
      if (((node instanceof Element)) && (node.getParentNode() == parent)) {
        elements.add((Element)node);
      }
    }
    return elements;
  }
  
  public static Element getChildElement(Element parent, String tagName)
  {
    List<Element> children = getChildElements(parent, tagName);
    if (children.isEmpty()) {
      return null;
    }
    return (Element)children.get(0);
  }
  
  public static String getElementValue(Element parent, String tagName)
  {
    Element element = getChildElement(parent, tagName);
    if (element != null)
    {
      NodeList nodes = element.getChildNodes();
      if ((nodes != null) && (nodes.getLength() > 0)) {
        for (int i = 0; i < nodes.getLength(); i++)
        {
          Node node = nodes.item(i);
          if ((node instanceof Text)) {
            return ((Text)node).getData();
          }
        }
      }
    }
    return null;
  }
  
  public static String getElementValue(Element element)
  {
    if (element != null)
    {
      NodeList nodes = element.getChildNodes();
      if ((nodes != null) && (nodes.getLength() > 0)) {
        for (int i = 0; i < nodes.getLength(); i++)
        {
          Node node = nodes.item(i);
          if ((node instanceof Text)) {
            return ((Text)node).getData();
          }
        }
      }
    }
    return null;
  }
  
  public static Element appendElement(Element parent, String tagName)
  {
    Element child = parent.getOwnerDocument().createElement(tagName);
    parent.appendChild(child);
    return child;
  }
  
  public static Element appendElement(Element parent, String tagName, String value)
  {
    Element child = appendElement(parent, tagName);
    child.appendChild(child.getOwnerDocument().createTextNode(value));
    return child;
  }
  
  public static void appendElement(Element parent, Element child)
  {
    Node tmp = parent.getOwnerDocument().importNode(child, true);
    parent.appendChild(tmp);
  }
  
  public static Element appendCDATAElement(Element parent, String tagName, String value)
  {
    Element child = appendElement(parent, tagName);
    if (value == null) {
      value = "";
    }
    Node cdata = child.getOwnerDocument().createCDATASection(value);
    child.appendChild(cdata);
    return child;
  }
  
  public static String childNodeToString(Node node)
    throws AlipayApiException
  {
    String payload = null;
    try
    {
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      
      Properties props = tf.getOutputProperties();
      props.setProperty("omit-xml-declaration", "yes");
      tf.setOutputProperties(props);
      
      StringWriter writer = new StringWriter();
      tf.transform(new DOMSource(node), new StreamResult(writer));
      payload = writer.toString();
      payload = payload.replaceAll("&#\\d+;", " ");
    }
    catch (TransformerException e)
    {
      throw new AlipayApiException("XML_TRANSFORM_ERROR", e);
    }
    return payload;
  }
  
  public static String nodeToString(Node node)
    throws AlipayApiException
  {
    String payload = null;
    try
    {
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      
      Properties props = tf.getOutputProperties();
      props.setProperty("indent", "yes");
      props.setProperty("encoding", "UTF-8");
      tf.setOutputProperties(props);
      
      StringWriter writer = new StringWriter();
      tf.transform(new DOMSource(node), new StreamResult(writer));
      payload = writer.toString();
      payload = payload.replaceAll("&#\\d+;", " ");
    }
    catch (TransformerException e)
    {
      throw new AlipayApiException("XML_TRANSFORM_ERROR", e);
    }
    return payload;
  }
  
  public static String xmlToString(File file)
    throws AlipayApiException
  {
    Element root = getRootElementFromFile(file);
    return nodeToString(root);
  }
  
  public static String xmlToString(InputStream in)
    throws AlipayApiException
  {
    Element root = getRootElementFromStream(in);
    return nodeToString(root);
  }
  
  public static void saveToXml(Node doc, File file)
    throws AlipayApiException
  {
    OutputStream out = null;
    try
    {
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      
      Properties props = tf.getOutputProperties();
      props.setProperty("method", "xml");
      props.setProperty("indent", "yes");
      tf.setOutputProperties(props);
      
      DOMSource dom = new DOMSource(doc);
      out = getOutputStream(file);
      Result result = new StreamResult(out);
      tf.transform(dom, result);
    }
    catch (TransformerException e)
    {
      throw new AlipayApiException("XML_TRANSFORM_ERROR", e);
    }
    finally
    {
      if (out != null) {
        try
        {
          out.close();
        }
        catch (IOException localIOException) {}
      }
    }
  }
  
  public static void validateXml(Node doc, File schemaFile)
    throws AlipayApiException
  {
    validateXml(doc, getInputStream(schemaFile));
  }
  
  public static void validateXml(Node doc, InputStream schemaStream)
    throws AlipayApiException
  {
    try
    {
      Source source = new StreamSource(schemaStream);
      Schema schema = SchemaFactory.newInstance(
        "http://www.w3.org/2001/XMLSchema").newSchema(source);
      
      Validator validator = schema.newValidator();
      validator.validate(new DOMSource(doc));
    }
    catch (SAXException e)
    {
      throw new AlipayApiException("XML_VALIDATE_ERROR", e);
    }
    catch (IOException e)
    {
      throw new AlipayApiException("XML_READ_ERROR", e);
    }
    finally
    {
      if (schemaStream != null) {
        try
        {
          schemaStream.close();
        }
        catch (IOException localIOException1) {}
      }
    }
  }
  
  public static String xmlToHtml(String payload, File xsltFile)
    throws AlipayApiException
  {
    String result = null;
    try
    {
      Source template = new StreamSource(xsltFile);
      Transformer transformer = TransformerFactory.newInstance()
        .newTransformer(template);
      
      Properties props = transformer.getOutputProperties();
      props.setProperty("omit-xml-declaration", "yes");
      transformer.setOutputProperties(props);
      
      StreamSource source = new StreamSource(new StringReader(payload));
      StreamResult sr = new StreamResult(new StringWriter());
      transformer.transform(source, sr);
      
      result = sr.getWriter().toString();
    }
    catch (TransformerException e)
    {
      throw new AlipayApiException("XML_TRANSFORM_ERROR", e);
    }
    return result;
  }
  
  public static void setNamespace(Element element, String namespace, String schemaLocation)
  {
    element.setAttributeNS("http://www.w3.org/2000/xmlns/", 
      "xmlns", namespace);
    element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", 
      "http://www.w3.org/2001/XMLSchema-instance");
    element.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", 
      "xsi:schemaLocation", schemaLocation);
  }
  
  public static String encodeXml(String payload)
    throws AlipayApiException
  {
    Element root = createRootElement("xml");
    root.appendChild(root.getOwnerDocument().createTextNode(payload));
    return childNodeToString(root.getFirstChild());
  }
  
  private static InputStream getInputStream(File file)
    throws AlipayApiException
  {
    InputStream in = null;
    try
    {
      in = new FileInputStream(file);
    }
    catch (FileNotFoundException e)
    {
      throw new AlipayApiException("FILE_NOT_FOUND", e);
    }
    return in;
  }
  
  private static OutputStream getOutputStream(File file)
    throws AlipayApiException
  {
    OutputStream in = null;
    try
    {
      in = new FileOutputStream(file);
    }
    catch (FileNotFoundException e)
    {
      throw new AlipayApiException("FILE_NOT_FOUND", e);
    }
    return in;
  }
}

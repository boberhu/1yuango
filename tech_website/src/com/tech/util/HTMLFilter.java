package com.tech.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HTMLFilter
{
  private static final int REGEX_FLAGS_SI = 34;
  private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", 32);
  private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", 34);
  private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", 32);
  private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", 34);
  private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", 34);
  private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", 34);
  private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", 34);
  private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", 34);
  private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
  private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
  private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
  private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
  private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", 32);
  private static final Pattern P_END_ARROW = Pattern.compile("^>");
  private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
  private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
  private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
  private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
  private static final Pattern P_AMP = Pattern.compile("&");
  private static final Pattern P_QUOTE = Pattern.compile("\"");
  private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
  private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
  private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");
  private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap();
  private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap();
  private final Map<String, List<String>> vAllowed;
  private final Map<String, Integer> vTagCounts = new HashMap();
  private final String[] vSelfClosingTags;
  private final String[] vNeedClosingTags;
  private final String[] vDisallowed;
  private final String[] vProtocolAtts;
  private final String[] vAllowedProtocols;
  private final String[] vRemoveBlanks;
  private final String[] vAllowedEntities;
  private final boolean stripComment;
  private final boolean encodeQuotes;
  private boolean vDebug = false;
  private final boolean alwaysMakeTags;
  
  public HTMLFilter()
  {
    vAllowed = new HashMap();
    
    ArrayList<String> a_atts = new ArrayList();
    a_atts.add("href");
    a_atts.add("target");
    vAllowed.put("a", a_atts);
    
    ArrayList<String> img_atts = new ArrayList();
    img_atts.add("src");
    img_atts.add("width");
    img_atts.add("height");
    img_atts.add("alt");
    vAllowed.put("img", img_atts);
    
    ArrayList<String> no_atts = new ArrayList();
    vAllowed.put("b", no_atts);
    vAllowed.put("strong", no_atts);
    vAllowed.put("i", no_atts);
    vAllowed.put("em", no_atts);
    
    vSelfClosingTags = new String[] { "img" };
    vNeedClosingTags = new String[] { "a", "b", "strong", "i", "em" };
    vDisallowed = new String[0];
    vAllowedProtocols = new String[] { "http", "mailto" };
    vProtocolAtts = new String[] { "src", "href" };
    vRemoveBlanks = new String[] { "a", "b", "strong", "i", "em" };
    vAllowedEntities = new String[] { "amp", "gt", "lt", "quot" };
    stripComment = true;
    encodeQuotes = true;
    alwaysMakeTags = true;
  }
  
  public HTMLFilter(boolean debug)
  {
    this();
    vDebug = debug;
  }
  
  public HTMLFilter(Map<String, Object> conf)
  {
    assert (conf.containsKey("vAllowed")) : "configuration requires vAllowed";
    assert (conf.containsKey("vSelfClosingTags")) : "configuration requires vSelfClosingTags";
    assert (conf.containsKey("vNeedClosingTags")) : "configuration requires vNeedClosingTags";
    assert (conf.containsKey("vDisallowed")) : "configuration requires vDisallowed";
    assert (conf.containsKey("vAllowedProtocols")) : "configuration requires vAllowedProtocols";
    assert (conf.containsKey("vProtocolAtts")) : "configuration requires vProtocolAtts";
    assert (conf.containsKey("vRemoveBlanks")) : "configuration requires vRemoveBlanks";
    assert (conf.containsKey("vAllowedEntities")) : "configuration requires vAllowedEntities";
    
    vAllowed = Collections.unmodifiableMap((HashMap)conf.get("vAllowed"));
    vSelfClosingTags = ((String[])conf.get("vSelfClosingTags"));
    vNeedClosingTags = ((String[])conf.get("vNeedClosingTags"));
    vDisallowed = ((String[])conf.get("vDisallowed"));
    vAllowedProtocols = ((String[])conf.get("vAllowedProtocols"));
    vProtocolAtts = ((String[])conf.get("vProtocolAtts"));
    vRemoveBlanks = ((String[])conf.get("vRemoveBlanks"));
    vAllowedEntities = ((String[])conf.get("vAllowedEntities"));
    stripComment = (conf.containsKey("stripComment") ? ((Boolean)conf.get("stripComment")).booleanValue() : true);
    encodeQuotes = (conf.containsKey("encodeQuotes") ? ((Boolean)conf.get("encodeQuotes")).booleanValue() : true);
    alwaysMakeTags = (conf.containsKey("alwaysMakeTags") ? ((Boolean)conf.get("alwaysMakeTags")).booleanValue() : true);
  }
  
  private void reset()
  {
    vTagCounts.clear();
  }
  
  private void debug(String msg)
  {
    if (vDebug) {
      Logger.getAnonymousLogger().info(msg);
    }
  }
  
  public static String chr(int decimal)
  {
    return String.valueOf((char)decimal);
  }
  
  public static String htmlSpecialChars(String s)
  {
    String result = s;
    result = regexReplace(P_AMP, "&amp;", result);
    result = regexReplace(P_QUOTE, "&quot;", result);
    result = regexReplace(P_LEFT_ARROW, "&lt;", result);
    result = regexReplace(P_RIGHT_ARROW, "&gt;", result);
    return result;
  }
  
  public String filter(String input)
  {
    reset();
    String s = input;
    
    debug("************************************************");
    debug("              INPUT: " + input);
    
    s = escapeComments(s);
    debug("     escapeComments: " + s);
    
    s = balanceHTML(s);
    debug("        balanceHTML: " + s);
    
    s = checkTags(s);
    debug("          checkTags: " + s);
    
    s = processRemoveBlanks(s);
    debug("processRemoveBlanks: " + s);
    
    s = validateEntities(s);
    debug("    validateEntites: " + s);
    
    debug("************************************************\n\n");
    return s;
  }
  
  public boolean isAlwaysMakeTags()
  {
    return alwaysMakeTags;
  }
  
  public boolean isStripComments()
  {
    return stripComment;
  }
  
  private String escapeComments(String s)
  {
    Matcher m = P_COMMENTS.matcher(s);
    StringBuffer buf = new StringBuffer();
    if (m.find())
    {
      String match = m.group(1);
      m.appendReplacement(buf, Matcher.quoteReplacement("<!--" + htmlSpecialChars(match) + "-->"));
    }
    m.appendTail(buf);
    
    return buf.toString();
  }
  
  private String balanceHTML(String s)
  {
    if (alwaysMakeTags)
    {
      s = regexReplace(P_END_ARROW, "", s);
      s = regexReplace(P_BODY_TO_END, "<$1>", s);
      s = regexReplace(P_XML_CONTENT, "$1<$2", s);
    }
    else
    {
      s = regexReplace(P_STRAY_LEFT_ARROW, "&lt;$1", s);
      s = regexReplace(P_STRAY_RIGHT_ARROW, "$1$2&gt;<", s);
      





      s = regexReplace(P_BOTH_ARROWS, "", s);
    }
    return s;
  }
  
  private String checkTags(String s)
  {
    Matcher m = P_TAGS.matcher(s);
    
    StringBuffer buf = new StringBuffer();
    while (m.find())
    {
      String replaceStr = m.group(1);
      replaceStr = processTag(replaceStr);
      m.appendReplacement(buf, Matcher.quoteReplacement(replaceStr));
    }
    m.appendTail(buf);
    
    s = buf.toString();
    String key;
    for (Iterator<String> itr = vTagCounts.keySet().iterator(); itr.hasNext();)
    {
      key = (String)itr.next();
      s = s + "</" + key + ">";
    }
    return s;
  }
  
  private String processRemoveBlanks(String s)
  {
    String result = s;
    for (String tag : vRemoveBlanks)
    {
      if (!P_REMOVE_PAIR_BLANKS.containsKey(tag)) {
        P_REMOVE_PAIR_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?></" + tag + ">"));
      }
      result = regexReplace((Pattern)P_REMOVE_PAIR_BLANKS.get(tag), "", result);
      if (!P_REMOVE_SELF_BLANKS.containsKey(tag)) {
        P_REMOVE_SELF_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?/>"));
      }
      result = regexReplace((Pattern)P_REMOVE_SELF_BLANKS.get(tag), "", result);
    }
    return result;
  }
  
  private static String regexReplace(Pattern regex_pattern, String replacement, String s)
  {
    Matcher m = regex_pattern.matcher(s);
    return m.replaceAll(replacement);
  }
  
  private String processTag(String s)
  {
    Matcher m = P_END_TAG.matcher(s);
    if (m.find())
    {
      String name = m.group(1).toLowerCase();
      if ((allowed(name)) && 
        (!inArray(name, vSelfClosingTags)) && 
        (vTagCounts.containsKey(name)))
      {
        vTagCounts.put(name, Integer.valueOf(((Integer)vTagCounts.get(name)).intValue() - 1));
        return "</" + name + ">";
      }
    }
    m = P_START_TAG.matcher(s);
    if (m.find())
    {
      String name = m.group(1).toLowerCase();
      String body = m.group(2);
      String ending = m.group(3);
      if (allowed(name))
      {
        String params = "";
        
        Matcher m2 = P_QUOTED_ATTRIBUTES.matcher(body);
        Matcher m3 = P_UNQUOTED_ATTRIBUTES.matcher(body);
        List<String> paramNames = new ArrayList();
        List<String> paramValues = new ArrayList();
        while (m2.find())
        {
          paramNames.add(m2.group(1));
          paramValues.add(m2.group(3));
        }
        while (m3.find())
        {
          paramNames.add(m3.group(1));
          paramValues.add(m3.group(3));
        }
        for (int ii = 0; ii < paramNames.size(); ii++)
        {
          String paramName = ((String)paramNames.get(ii)).toLowerCase();
          String paramValue = (String)paramValues.get(ii);
          if (allowedAttribute(name, paramName))
          {
            if (inArray(paramName, vProtocolAtts)) {
              paramValue = processParamProtocol(paramValue);
            }
            params = params + " " + paramName + "=\"" + paramValue + "\"";
          }
        }
        if (inArray(name, vSelfClosingTags)) {
          ending = " /";
        }
        if (inArray(name, vNeedClosingTags)) {
          ending = "";
        }
        if ((ending == null) || (ending.length() < 1))
        {
          if (vTagCounts.containsKey(name)) {
            vTagCounts.put(name, Integer.valueOf(((Integer)vTagCounts.get(name)).intValue() + 1));
          } else {
            vTagCounts.put(name, Integer.valueOf(1));
          }
        }
        else {
          ending = " /";
        }
        return "<" + name + params + ending + ">";
      }
      return "";
    }
    m = P_COMMENT.matcher(s);
    if ((!stripComment) && (m.find())) {
      return "<" + m.group() + ">";
    }
    return "";
  }
  
  private String processParamProtocol(String s)
  {
    s = decodeEntities(s);
    Matcher m = P_PROTOCOL.matcher(s);
    if (m.find())
    {
      String protocol = m.group(1);
      if (!inArray(protocol, vAllowedProtocols))
      {
        s = "#" + s.substring(protocol.length() + 1, s.length());
        if (s.startsWith("#//")) {
          s = "#" + s.substring(3, s.length());
        }
      }
    }
    return s;
  }
  
  private String decodeEntities(String s)
  {
    StringBuffer buf = new StringBuffer();
    
    Matcher m = P_ENTITY.matcher(s);
    while (m.find())
    {
      String match = m.group(1);
      int decimal = Integer.decode(match).intValue();
      m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
    }
    m.appendTail(buf);
    s = buf.toString();
    
    buf = new StringBuffer();
    m = P_ENTITY_UNICODE.matcher(s);
    while (m.find())
    {
      String match = m.group(1);
      int decimal = Integer.valueOf(match, 16).intValue();
      m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
    }
    m.appendTail(buf);
    s = buf.toString();
    
    buf = new StringBuffer();
    m = P_ENCODE.matcher(s);
    while (m.find())
    {
      String match = m.group(1);
      int decimal = Integer.valueOf(match, 16).intValue();
      m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
    }
    m.appendTail(buf);
    s = buf.toString();
    
    s = validateEntities(s);
    return s;
  }
  
  private String validateEntities(String s)
  {
    StringBuffer buf = new StringBuffer();
    

    Matcher m = P_VALID_ENTITIES.matcher(s);
    while (m.find())
    {
      String one = m.group(1);
      String two = m.group(2);
      m.appendReplacement(buf, Matcher.quoteReplacement(checkEntity(one, two)));
    }
    m.appendTail(buf);
    
    return encodeQuotes(buf.toString());
  }
  
  private String encodeQuotes(String s)
  {
    if (encodeQuotes)
    {
      StringBuffer buf = new StringBuffer();
      Matcher m = P_VALID_QUOTES.matcher(s);
      while (m.find())
      {
        String one = m.group(1);
        String two = m.group(2);
        String three = m.group(3);
        m.appendReplacement(buf, Matcher.quoteReplacement(one + regexReplace(P_QUOTE, "&quot;", two) + three));
      }
      m.appendTail(buf);
      return buf.toString();
    }
    return s;
  }
  
  private String checkEntity(String preamble, String term)
  {
    return 
    
      "&amp;" + preamble;
  }
  
  private boolean isValidEntity(String entity)
  {
    return inArray(entity, vAllowedEntities);
  }
  
  private static boolean inArray(String s, String[] array)
  {
    for (String item : array) {
      if ((item != null) && (item.equals(s))) {
        return true;
      }
    }
    return false;
  }
  
  private boolean allowed(String name)
  {
    return ((vAllowed.isEmpty()) || (vAllowed.containsKey(name))) && (!inArray(name, vDisallowed));
  }
  
  private boolean allowedAttribute(String name, String paramName)
  {
    return (allowed(name)) && ((vAllowed.isEmpty()) || (((List)vAllowed.get(name)).contains(paramName)));
  }
}

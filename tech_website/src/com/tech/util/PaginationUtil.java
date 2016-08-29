package com.tech.util;

import java.io.PrintStream;

public class PaginationUtil
{
  public static void main(String[] args)
  {
    System.out.println(getPaginationHtml(Integer.valueOf(600), Integer.valueOf(30), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5), "/xxx/ddd/{page}/sss"));
  }
  
  public static String getPaginationHtml(Integer allTotal, Integer currentRows, Integer currentPage, Integer edgeEntries, Integer displayEntries, String link_to)
  {
    StringBuffer sb = new StringBuffer();
    
    int total = 0;
    if (allTotal.intValue() % currentRows.intValue() > 0) {
      total = (int)Math.floor(allTotal.intValue() / currentRows.intValue() + 1);
    } else {
      total = (int)Math.floor(allTotal.intValue() / currentRows.intValue());
    }
    if (total < currentPage.intValue()) {
      currentPage = Integer.valueOf(total);
    }
    sb.append("<div class=\"pages\"><ul id=\"pagination\" class=\"pageULEx\">");
    if (allTotal.intValue() > 0) {
      sb.append("<li class=\"total_page\">页次<i>" + currentPage + "/" + total + "</i>&nbsp;&nbsp;共<i>" + allTotal + "</i>条记录</li>");
    }
    if (currentPage.intValue() == 1) {
      sb.append("<li class=\"prev_page page_curgray\"><a><i>&lt;</i>上一页</a></li>");
    } else if (allTotal.intValue() > 0) {
      sb.append("<li class=\"prev_page\"><a href=\"" + link_to + (currentPage.intValue() - 1) + ".html" + "\">上一页</a></li>");
    }
    if (edgeEntries.intValue() * 2 + displayEntries.intValue() > total)
    {
      for (int i = 1; i <= total; i++) {
        if (i == currentPage.intValue()) {
          sb.append("<li class=\"curr_page\">" + i + "</li>");
        } else {
          sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
        }
      }
    }
    else if (currentPage.intValue() <= 5)
    {
      Integer currentTotal = Integer.valueOf(1);
      if (currentPage.intValue() < 4) {
        currentTotal = Integer.valueOf(4);
      } else {
        currentTotal = Integer.valueOf(currentPage.intValue() + 1);
      }
      for (int i = 1; i <= currentTotal.intValue(); i++) {
        if (i == currentPage.intValue()) {
          sb.append("<li class=\"curr_page\">" + i + "</li>");
        } else {
          sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
        }
      }
      sb.append("<li>...</li>");
      
      sb.append("<li><a href=\"" + link_to + total + ".html" + "\">" + total + "</a></li>");
    }
    else if (total - 4 <= currentPage.intValue())
    {
      sb.append("<li><a href=\"" + link_to + 1 + ".html" + "\">1</a></li>");
      
      sb.append("<li>...</li>");
      
      Integer currentTotal = Integer.valueOf(0);
      if (total - 1 > currentPage.intValue()) {
        currentTotal = Integer.valueOf(currentPage.intValue() - 1);
      } else {
        currentTotal = Integer.valueOf(total - 2);
      }
      for (int i = currentTotal.intValue(); i <= total; i++) {
        if (i == currentPage.intValue()) {
          sb.append("<li class=\"curr_page\">" + i + "</li>");
        } else {
          sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
        }
      }
    }
    else
    {
      sb.append("<li><a href=\"" + link_to + 1 + ".html" + "\">1</a></li>");
      
      sb.append("<li>...</li>");
      

      sb.append("<li><a href=\"" + link_to + (currentPage.intValue() - 2) + ".html" + "\">" + (currentPage.intValue() - 2) + "</a></li>");
      sb.append("<li><a href=\"" + link_to + (currentPage.intValue() - 1) + ".html" + "\">" + (currentPage.intValue() - 1) + "</a></li>");
      
      sb.append("<li class=\"curr_page\">" + currentPage + "</li>");
      
      sb.append("<li><a href=\"" + link_to + (currentPage.intValue() + 1) + ".html" + "\">" + (currentPage.intValue() + 1) + "</a></li>");
      sb.append("<li><a href=\"" + link_to + (currentPage.intValue() + 2) + ".html" + "\">" + (currentPage.intValue() + 2) + "</a></li>");
      
      sb.append("<li>...</li>");
      
      sb.append("<li><a href=\"" + link_to + total + ".html" + "\">" + total + "</a></li>");
    }
    if (currentPage.intValue() == total) {
      sb.append("<li class=\"prev_page page_curgray\"><a>下一页<i>&gt;</i></a></li>");
    } else {
      sb.append("<li class=\"next_page\"><a href=\"" + link_to + (currentPage.intValue() + 1) + ".html" + "\">下一页</a></li>");
    }
    sb.append("</ul></div>");
    return sb.toString();
  }
}

package com.tech.util;

import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

class nsICharsetDetectionObserverImp
  implements nsICharsetDetectionObserver
{
  String encod = "";
  
  public void Notify(String charset)
  {
    org.mozilla.intl.chardet.HtmlCharsetDetector.found = true;
    encod = charset;
  }
  
  public String getEncoding()
  {
    return encod;
  }
}

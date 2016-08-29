package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class AliTrustCert
  extends AlipayObject
{
  private static final long serialVersionUID = 1772642129233966277L;
  @ApiField("forward_url")
  private String forwardUrl;
  @ApiField("icon_url")
  private String iconUrl;
  @ApiField("is_certified")
  private String isCertified;
  @ApiField("level")
  private String level;
  @ApiField("message")
  private String message;
  
  public String getForwardUrl()
  {
    return forwardUrl;
  }
  
  public void setForwardUrl(String forwardUrl)
  {
    this.forwardUrl = forwardUrl;
  }
  
  public String getIconUrl()
  {
    return iconUrl;
  }
  
  public void setIconUrl(String iconUrl)
  {
    this.iconUrl = iconUrl;
  }
  
  public String getIsCertified()
  {
    return isCertified;
  }
  
  public void setIsCertified(String isCertified)
  {
    this.isCertified = isCertified;
  }
  
  public String getLevel()
  {
    return level;
  }
  
  public void setLevel(String level)
  {
    this.level = level;
  }
  
  public String getMessage()
  {
    return message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
}

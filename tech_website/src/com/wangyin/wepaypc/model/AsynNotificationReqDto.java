package com.wangyin.wepaypc.model;

public class AsynNotificationReqDto
{
  private String version;
  private String merchant;
  private String terminal;
  private String data;
  private String sign;
  
  public String getVersion()
  {
    return version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getMerchant()
  {
    return merchant;
  }
  
  public void setMerchant(String merchant)
  {
    this.merchant = merchant;
  }
  
  public String getTerminal()
  {
    return terminal;
  }
  
  public void setTerminal(String terminal)
  {
    this.terminal = terminal;
  }
  
  public String getData()
  {
    return data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getSign()
  {
    return sign;
  }
  
  public void setSign(String sign)
  {
    this.sign = sign;
  }
}

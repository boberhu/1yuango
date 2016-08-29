package com.tech.pojo;

import java.io.Serializable;

public class SCity
  implements Serializable
{
  private Integer cid;
  private String cname;
  private String cpostcode;
  private Integer pid;
  
  public SCity() {}
  
  public SCity(String cname, String cpostcode, Integer pid)
  {
    this.cname = cname;
    this.cpostcode = cpostcode;
    this.pid = pid;
  }
  
  public Integer getCid()
  {
    return cid;
  }
  
  public void setCid(Integer cid)
  {
    this.cid = cid;
  }
  
  public String getCname()
  {
    return cname;
  }
  
  public void setCname(String cname)
  {
    this.cname = cname;
  }
  
  public String getCpostcode()
  {
    return cpostcode;
  }
  
  public void setCpostcode(String cpostcode)
  {
    this.cpostcode = cpostcode;
  }
  
  public Integer getPid()
  {
    return pid;
  }
  
  public void setPid(Integer pid)
  {
    this.pid = pid;
  }
}

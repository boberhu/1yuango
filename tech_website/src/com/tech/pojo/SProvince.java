package com.tech.pojo;

import java.io.Serializable;

public class SProvince
  implements Serializable
{
  private Integer pid;
  private String pname;
  
  public SProvince() {}
  
  public SProvince(String pname)
  {
    this.pname = pname;
  }
  
  public Integer getPid()
  {
    return pid;
  }
  
  public void setPid(Integer pid)
  {
    this.pid = pid;
  }
  
  public String getPname()
  {
    return pname;
  }
  
  public void setPname(String pname)
  {
    this.pname = pname;
  }
}

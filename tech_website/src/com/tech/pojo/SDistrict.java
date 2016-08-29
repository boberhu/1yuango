package com.tech.pojo;

import java.io.Serializable;

public class SDistrict
  implements Serializable
{
  private Integer did;
  private String dname;
  private Integer cid;
  
  public SDistrict() {}
  
  public SDistrict(String dname, Integer cid)
  {
    this.dname = dname;
    this.cid = cid;
  }
  
  public Integer getDid()
  {
    return did;
  }
  
  public void setDid(Integer did)
  {
    this.did = did;
  }
  
  public String getDname()
  {
    return dname;
  }
  
  public void setDname(String dname)
  {
    this.dname = dname;
  }
  
  public Integer getCid()
  {
    return cid;
  }
  
  public void setCid(Integer cid)
  {
    this.cid = cid;
  }
}

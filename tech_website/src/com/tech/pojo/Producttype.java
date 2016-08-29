package com.tech.pojo;

import java.io.Serializable;
import java.util.List;

public class Producttype implements Serializable
{
  private static final long serialVersionUID = 4089489541052914077L;
  
  private Integer typeId;
  private String typeName;
  private String ftypeId;
  private String stypeId;
  private String attribute70;
  private List<Producttype> subtypes;
  
  public Producttype() {}
  
  public Producttype(String typeName, String ftypeId, String stypeId)
  {
    this.typeName = typeName;
    this.ftypeId = ftypeId;
    this.stypeId = stypeId;
  }
  
  public Producttype(String typeName, String ftypeId, String stypeId, String attribute70)
  {
    this.typeName = typeName;
    this.ftypeId = ftypeId;
    this.stypeId = stypeId;
    this.attribute70 = attribute70;
  }
  
  public Integer getTypeId()
  {
    return typeId;
  }
  
  public void setTypeId(Integer typeId)
  {
    this.typeId = typeId;
  }
  
  public String getTypeName()
  {
    return typeName;
  }
  
  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }
  
  public String getFtypeId()
  {
    return ftypeId;
  }
  
  public void setFtypeId(String ftypeId)
  {
    this.ftypeId = ftypeId;
  }
  
  public String getStypeId()
  {
    return stypeId;
  }
  
  public void setStypeId(String stypeId)
  {
    this.stypeId = stypeId;
  }
  
  public String getAttribute70()
  {
    return attribute70;
  }
  
  public void setAttribute70(String attribute70)
  {
    this.attribute70 = attribute70;
  }
  
  public List<Producttype> getSubtypes() {
	return subtypes;
  }

  public void setSubtypes(List<Producttype> subtypes) {
	this.subtypes = subtypes;
  }
  
}

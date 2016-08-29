package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class DishonestyDetailInfo
  extends AlipayObject
{
  private static final long serialVersionUID = 2265724422783959368L;
  @ApiField("behavior")
  private String behavior;
  @ApiField("case_code")
  private String caseCode;
  @ApiField("enforce_court")
  private String enforceCourt;
  @ApiField("id_number")
  private String idNumber;
  @ApiField("name")
  private String name;
  @ApiField("performance")
  private String performance;
  @ApiField("publish_date")
  private String publishDate;
  @ApiField("region")
  private String region;
  
  public String getBehavior()
  {
    return behavior;
  }
  
  public void setBehavior(String behavior)
  {
    this.behavior = behavior;
  }
  
  public String getCaseCode()
  {
    return caseCode;
  }
  
  public void setCaseCode(String caseCode)
  {
    this.caseCode = caseCode;
  }
  
  public String getEnforceCourt()
  {
    return enforceCourt;
  }
  
  public void setEnforceCourt(String enforceCourt)
  {
    this.enforceCourt = enforceCourt;
  }
  
  public String getIdNumber()
  {
    return idNumber;
  }
  
  public void setIdNumber(String idNumber)
  {
    this.idNumber = idNumber;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getPerformance()
  {
    return performance;
  }
  
  public void setPerformance(String performance)
  {
    this.performance = performance;
  }
  
  public String getPublishDate()
  {
    return publishDate;
  }
  
  public void setPublishDate(String publishDate)
  {
    this.publishDate = publishDate;
  }
  
  public String getRegion()
  {
    return region;
  }
  
  public void setRegion(String region)
  {
    this.region = region;
  }
}

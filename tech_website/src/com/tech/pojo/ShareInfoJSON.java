package com.tech.pojo;

import java.io.Serializable;
import java.util.List;

public class ShareInfoJSON
  implements Serializable
{
  private static final long serialVersionUID = -8825464421098017421L;
  private Integer uid;
  private String shareTitle;
  private String shareContent;
  private List<Shareimage> shareimageList;
  private Integer upCount;
  private Integer replyCount;
  private Integer reward;
  private String shareDate;
  private String announcedTime;
  private String userName;
  private String userFace;
  private String userId;
  private Integer status;
  private Integer productPeriod;
  
  public ShareInfoJSON() {}
  
  public ShareInfoJSON(Integer uid, String shareTitle, String shareContent, List<Shareimage> shareimageList, Integer upCount, Integer replyCount, Integer reward, String shareDate, String announcedTime, String userName, String userFace, String userId, Integer status, Integer productPeriod)
  {
    this.uid = uid;
    this.shareTitle = shareTitle;
    this.shareContent = shareContent;
    this.shareimageList = shareimageList;
    this.upCount = upCount;
    this.replyCount = replyCount;
    this.reward = reward;
    this.shareDate = shareDate;
    this.announcedTime = announcedTime;
    this.userName = userName;
    this.userFace = userFace;
    this.userId = userId;
    this.status = status;
    this.productPeriod = productPeriod;
  }
  
  public Integer getUid()
  {
    return uid;
  }
  
  public void setUid(Integer uid)
  {
    this.uid = uid;
  }
  
  public String getShareTitle()
  {
    return shareTitle;
  }
  
  public void setShareTitle(String shareTitle)
  {
    this.shareTitle = shareTitle;
  }
  
  public String getShareContent()
  {
    return shareContent;
  }
  
  public void setShareContent(String shareContent)
  {
    this.shareContent = shareContent;
  }
  
  public List<Shareimage> getShareimageList()
  {
    return shareimageList;
  }
  
  public void setShareimageList(List<Shareimage> shareimageList)
  {
    this.shareimageList = shareimageList;
  }
  
  public Integer getUpCount()
  {
    return upCount;
  }
  
  public void setUpCount(Integer upCount)
  {
    this.upCount = upCount;
  }
  
  public Integer getReplyCount()
  {
    return replyCount;
  }
  
  public void setReplyCount(Integer replyCount)
  {
    this.replyCount = replyCount;
  }
  
  public Integer getReward()
  {
    return reward;
  }
  
  public void setReward(Integer reward)
  {
    this.reward = reward;
  }
  
  public String getShareDate()
  {
    return shareDate;
  }
  
  public void setShareDate(String shareDate)
  {
    this.shareDate = shareDate;
  }
  
  public String getAnnouncedTime()
  {
    return announcedTime;
  }
  
  public void setAnnouncedTime(String announcedTime)
  {
    this.announcedTime = announcedTime;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getUserFace()
  {
    return userFace;
  }
  
  public void setUserFace(String userFace)
  {
    this.userFace = userFace;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public Integer getStatus()
  {
    return status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
  
  public static long getSerialVersionUID()
  {
    return -8825464421098017421L;
  }
  
  public Integer getProductPeriod()
  {
    return productPeriod;
  }
  
  public void setProductPeriod(Integer productPeriod)
  {
    this.productPeriod = productPeriod;
  }
}

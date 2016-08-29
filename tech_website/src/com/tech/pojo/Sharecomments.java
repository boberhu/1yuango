package com.tech.pojo;

import java.io.Serializable;

public class Sharecomments
  implements Serializable
{
  private Integer uid;
  private Integer shareInfoId;
  private Integer reCommentId;
  private Integer userId;
  private String content;
  private String createDate;
  private Integer reCount;
  
  public Sharecomments(Integer uid, Integer shareInfoId, Integer reCommentId, Integer userId, String content, String createDate, Integer reCount)
  {
    this.uid = uid;
    this.shareInfoId = shareInfoId;
    this.reCommentId = reCommentId;
    this.userId = userId;
    this.content = content;
    this.createDate = createDate;
    this.reCount = reCount;
  }
  
  public Sharecomments() {}
  
  public Integer getUid()
  {
    return uid;
  }
  
  public void setUid(Integer uid)
  {
    this.uid = uid;
  }
  
  public Integer getShareInfoId()
  {
    return shareInfoId;
  }
  
  public void setShareInfoId(Integer shareInfoId)
  {
    this.shareInfoId = shareInfoId;
  }
  
  public Integer getUserId()
  {
    return userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public String getContent()
  {
    return content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getCreateDate()
  {
    return createDate;
  }
  
  public void setCreateDate(String createDate)
  {
    this.createDate = createDate;
  }
  
  public Integer getReCommentId()
  {
    return reCommentId;
  }
  
  public void setReCommentId(Integer reCommentId)
  {
    this.reCommentId = reCommentId;
  }
  
  public Integer getReCount()
  {
    return reCount;
  }
  
  public void setReCount(Integer reCount)
  {
    this.reCount = reCount;
  }
}

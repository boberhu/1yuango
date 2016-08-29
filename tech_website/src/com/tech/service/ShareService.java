package com.tech.service;

import com.tech.dao.Pagination;
import com.tech.pojo.Sharecomments;
import com.tech.pojo.Shareimage;
import com.tech.pojo.Shareinfo;

import java.util.List;

public abstract interface ShareService
  extends TService<Shareinfo, Integer>
{
  public abstract Pagination loadPageShare(String paramString, int paramInt1, int paramInt2);
  
  public abstract Pagination loadShareInfoByNew(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  public abstract Integer loadShareInfoByNewByCount(String paramString);
  
  public abstract Pagination adminShareList(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  public abstract List shareShow(int paramInt);
  
  public abstract Pagination shareByUser(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
  
  public abstract Pagination UserInfoShareByUser(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);
  
  public abstract Integer getShareByUserByCount(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  public abstract List getShareimage(String paramString);
  
  public abstract void addShareImage(Shareimage paramShareimage);
  
  public abstract void delShareImageByShareId(Integer paramInteger);
  
  public abstract Pagination shareByComment(String paramString, int paramInt1, int paramInt2);
  
  public abstract List getReCommentList(String paramString);
  
  public abstract void createComment(Sharecomments paramSharecomments);
  
  public abstract Sharecomments findBySharecommentsId(String paramString);
  
  public abstract List getIndexSharecommentsList();
  
  public abstract Shareinfo findByProductId(String paramString);
}

package com.tech.pojo;

public class UserJSON
{
  private String userId;
  private String userName;
  private String userFace;
  private int userExperience;
  private double userBalance;
  private int userLevel;
  private String userLevelName;
  
  public UserJSON(String userId, String userName, String userFace, int userExperience, double userBalance, int userLevel, String userLevelName)
  {
    this.userId = userId;
    this.userName = userName;
    this.userFace = userFace;
    this.userExperience = userExperience;
    this.userBalance = userBalance;
    this.userLevel = userLevel;
    this.userLevelName = userLevelName;
  }
  
  public UserJSON() {}
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
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
  
  public int getUserExperience()
  {
    return userExperience;
  }
  
  public void setUserExperience(int userExperience)
  {
    this.userExperience = userExperience;
  }
  
  public double getUserBalance()
  {
    return userBalance;
  }
  
  public void setUserBalance(double userBalance)
  {
    this.userBalance = userBalance;
  }
  
  public int getUserLevel()
  {
    return userLevel;
  }
  
  public void setUserLevel(int userLevel)
  {
    this.userLevel = userLevel;
  }
  
  public String getUserLevelName()
  {
    return userLevelName;
  }
  
  public void setUserLevelName(String userLevelName)
  {
    this.userLevelName = userLevelName;
  }
}

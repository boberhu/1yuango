package com.tech.test;

import com.tech.pojo.User;
import com.tech.service.UserService;
import com.tech.util.ReadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class UpdateUserFaceImg
{
  @Autowired
  private UserService userService;
  private User user;
  private static DefaultHttpClient httpClient;
  private static HttpGet httpGet;
  private HttpPost httpPost;
  
  @Test
  public void go()
    throws Exception
  {
    List<User> userList = userService.loadAll();
    Collections.shuffle(userList);
    
    ReadFile readFile = new ReadFile();
    List<String> strList = ReadFile.readFile("c:\\imgFile2.txt");
    System.err.println(strList.size());
    int i = 0;
    for (String string : strList) {
      try
      {
        user = ((User)userList.get(i));
        if (user.getFaceImg().equals("/Images/defaultUserFace.png"))
        {
          System.err.println(string.split("-")[0]);
          
          Long time = Long.valueOf(System.currentTimeMillis());
          String path = "/faceImages/" + user.getUserId() + "_" + time + string.split("-")[1];
          System.err.println(path);
          String localPath = "c:\\UserImg\\" + user.getUserId() + "_" + time + string.split("-")[1];
          WeiboImg.getImage(string.split("-")[0], localPath);
          System.err.println(localPath);
          user.setFaceImg(path);
          userService.add(user);
          i++;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static List<String> imgList(String filepath)
    throws FileNotFoundException, IOException
  {
    List<String> imgList = new ArrayList();
    File file = new File(filepath);
    if (!file.isDirectory())
    {
      System.err.println("文件夹不存在！");
    }
    else if (file.isDirectory())
    {
      String[] filelist = file.list();
      for (int i = 0; i < filelist.length; i++)
      {
        File readfile = new File(filepath + "\\" + filelist[i]);
        if (!readfile.isDirectory()) {
          imgList.add(readfile.getName());
        } else if (readfile.isDirectory()) {
          readfile(filepath + "\\" + filelist[i]);
        }
      }
    }
    return imgList;
  }
  
  public static boolean readfile(String filepath)
    throws FileNotFoundException, IOException
  {
    try
    {
      File file = new File(filepath);
      if (!file.isDirectory())
      {
        System.out.println("文件");
        

        System.out.println("name1=" + file.getName());
      }
      else if (file.isDirectory())
      {
        System.out.println("文件夹");
        String[] filelist = file.list();
        for (int i = 0; i < filelist.length; i++)
        {
          File readfile = new File(filepath + "\\" + filelist[i]);
          if (!readfile.isDirectory()) {
            System.out.println("name2=" + readfile.getName());
          } else if (readfile.isDirectory()) {
            readfile(filepath + "\\" + filelist[i]);
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("readfile()   Exception:" + e.getMessage());
    }
    return true;
  }
}

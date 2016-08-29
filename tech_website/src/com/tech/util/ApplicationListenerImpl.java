package com.tech.util;

import com.tech.pojo.IndexImg;
import com.tech.pojo.SysConfigure;
import com.tech.service.SysConfigureService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerImpl implements ApplicationListener<ApplicationEvent>
{
  private final static Logger LOG = LoggerFactory.getLogger(ApplicationListenerImpl.class);
  @Autowired
  private SysConfigureService sysConfigureService;
  public static SysConfigure sysConfigureJson;
  public static final boolean sysConfigureAuth = true;
  public static List<IndexImg> indexImgAll;
  
  public SysConfigureService getSysConfigureService()
  {
    return sysConfigureService;
  }
  
  public void setSysConfigureService(SysConfigureService sysConfigureService)
  {
  	this.sysConfigureService = sysConfigureService;
  }
  
  @Override
  public void onApplicationEvent(ApplicationEvent event)
  {
	  sysConfigureJson = sysConfigureService.findById("1");
      indexImgAll = sysConfigureService.initializationIndexImgAll();
      LOG.info("sys-configure sait name: {}", sysConfigureJson.getSaitName());
  }
  
}

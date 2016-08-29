package com.tech.sms.chuangming;

import java.util.List;

import com.shcm.bean.BalanceResultBean;
import com.shcm.bean.SendResultBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;
import com.tech.sms.SmsSender;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Base64;
import com.tech.util.Sampler;
import com.tech.util.Struts2Utils;

public class CmSender extends SmsSender {

	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	  private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
	  //private static final String account = ApplicationListenerImpl.sysConfigureJson.getMessagePartner();
	  //private static final String authkey = ApplicationListenerImpl.sysConfigureJson.getMessageKey();
	  //private static final int cgid = Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel());
	  //private static final int csid = Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign());
	  
	  public boolean send(String mobile, String content){
		  String channel = ApplicationListenerImpl.sysConfigureJson.getMessageChannel();
		  String sign = ApplicationListenerImpl.sysConfigureJson.getMessageSign();
		  int intChannel =0;
		  int intSign = 0;
		  try {
			intChannel = Integer.parseInt(channel);
			intSign = Integer.parseInt(sign);
		} catch (Exception e) {
		}
	    OpenApi.initialzeAccount(sOpenUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey(), intChannel, intSign);
	    
	    DataApi.initialzeAccount(sDataUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey());

	    List<SendResultBean> sendList = OpenApi.sendOnce(new String[] { mobile }, content, Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageChannel()), Integer.parseInt(ApplicationListenerImpl.sysConfigureJson.getMessageSign()), null);
        if (sendList == null) {
        	return false;
        }else{
        	for (SendResultBean t : sendList)
            {
              if (t.getResult() < 1)
              {
                return false;
              }
            }
        }
	    return true;
	  }
	  
	  public String getMesBalance()
	  {
	    String balance = "";
	    String channel = ApplicationListenerImpl.sysConfigureJson.getMessageChannel();
		  String sign = ApplicationListenerImpl.sysConfigureJson.getMessageSign();
		  int intChannel =0;
		  int intSign = 0;
		  try {
			intChannel = Integer.parseInt(channel);
			intSign = Integer.parseInt(sign);
		} catch (Exception e) {
		}
	    OpenApi.initialzeAccount(sOpenUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey(), intChannel, intSign);
	    
	    DataApi.initialzeAccount(sDataUrl, ApplicationListenerImpl.sysConfigureJson.getMessagePartner(), ApplicationListenerImpl.sysConfigureJson.getMessageKey());
	    
	    BalanceResultBean br = OpenApi.getBalance();
	    if (br.getResult() < 1) {
	      balance = "获取可用余额失败" + br.getErrMsg();
	    } else {
	      balance = "可用条数: " + br.getRemain();
	    }
	    return balance;
	  }
	  
	  public static void main(String[] args)
	    throws Exception
	  {
	    OpenApi.initialzeAccount(sOpenUrl, "1001@501212860001", "48B24DE080D29F826256887586BBEE10", 5362 , 0);
	    
	    DataApi.initialzeAccount(sDataUrl, "1001@501212860001", "48B24DE080D29F826256887586BBEE10");
	    
	    List<SendResultBean> sendList = OpenApi.sendOnce(new String[] { "13758241122" }, "您本次的手机验证码是：1235，请即时填写，祝您好运。", 52, 5285, null);
	    if (sendList == null) {
	    	System.out.println("sendList = null");
	    }else{
	    	for (SendResultBean t : sendList)
	        {
	    		System.out.println("t.getResult()="+t.getResult());
	    		System.out.println("t.getErrMsg()="+t.getErrMsg());
	    		System.out.println("t.getRemain()="+t.getRemain());
	        }
	    }
	    /*BalanceResultBean br = OpenApi.getBalance();
	    if (br.getResult() < 1)
	    {
	      System.out.println("获取可用余额失败: " + br.getErrMsg());
	      return;
	    }
	    System.out.println("可用条数: " + br.getRemain());
	    */
	  }
	  
}

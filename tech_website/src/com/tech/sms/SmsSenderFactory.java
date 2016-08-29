package com.tech.sms;

import com.tech.sms.chuangming.CmSender;
import com.tech.sms.smsbao.SmsbaoSender;
import com.tech.util.ApplicationListenerImpl;
import com.tech.util.StringUtil;

public class SmsSenderFactory {

	public static SmsSender create() {
		String smsType = ApplicationListenerImpl.sysConfigureJson.getSmsType();
		if(StringUtil.equals(smsType, "chuangming"))
			return new CmSender();
		else if(StringUtil.equals(smsType, "smsbao"))
			return new SmsbaoSender();
		return new CmSender();
	}
	
	public static SmsSender cmsender() {
		return new CmSender();
	}
	
	public static SmsSender smsbaosender() {
		return new SmsbaoSender();
	}
	
}

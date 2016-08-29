package com.tech.sms;

import java.util.List;

public abstract class SmsSender {
	
	private String mobile;
	private String content;

	public SmsSender(){
		
	}
	
	public abstract boolean send(String mobile, String content);
	
	public abstract String getMesBalance();

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

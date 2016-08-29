package com.tech.sms.smsbao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tech.sms.SmsSender;
import com.tech.util.ApplicationListenerImpl;

public class SmsbaoSender extends SmsSender {

	public boolean send(String phone, String content) {
		String encodeText=null;
		try {
			encodeText = URLEncoder.encode(content, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String username = ApplicationListenerImpl.sysConfigureJson.getSmsbaoUsername();//短信宝帐户名
		String pass = new MD5().Md5(ApplicationListenerImpl.sysConfigureJson.getSmsbaoPass());//短信宝帐户密码，md5加密后的字符串
		//content = java.net.URLEncoder.encode(content);//发送内容
		
		HttpSend send = new HttpSend("http://www.smsbao.com/sms?u="+username+"&p="+pass+"&m="+phone+"&c="+encodeText);
		try {
			return send.send();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getMesBalance() {
		String balance = "";
		StringBuffer sb = new StringBuffer("http://www.smsbao.com/query?");
		sb.append("u=").append(ApplicationListenerImpl.sysConfigureJson.getSmsbaoUsername());
		sb.append("&p=").append(new MD5().Md5(ApplicationListenerImpl.sysConfigureJson.getSmsbaoPass()));
		
		URL url=null;
		try {
			url = new URL(sb.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String state = in.readLine();
			/**
			 * 30：密码错误
				40：账号不存在
				41：余额不足
				42：帐号过期
				43：IP地址限制
				50：内容含有敏感词
				51：手机号码不正确
			 */
			if("0".equals(state)){
				//如果第一行返回成功，则第二行返回 '发送条数,剩余条数'
				balance= in.readLine();
			}else{
				balance = "获取可用余额失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return balance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "test";///短信宝帐户名
		String pass = new MD5().Md5("111111");//短信宝帐户密码，md5加密后的字符串
		String phone = "test";//电话号码
		String content = java.net.URLEncoder.encode("aaa");//发送内容
		HttpSend send = new HttpSend("http://www.smsbao.com/sms?u="+username+"&p="+pass+"&m="+phone+"&c="+content);
		try {
		send.send();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

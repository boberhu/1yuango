package com.tech.sms.zucp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.tech.sms.SmsSender;
import com.tech.util.ApplicationListenerImpl;

public class ZucpSender extends SmsSender {

	public boolean send(String mobile, String content) {
		String encodeText=null;
		Client client=null;
		try {
			encodeText = URLEncoder.encode(content, "utf8");
			client = new Client(ApplicationListenerImpl.sysConfigureJson.getMessagePartner(),
					ApplicationListenerImpl.sysConfigureJson.getMessageKey());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		String result_mt = client.mdSmsSend_u(mobile, encodeText, "", "", "");
		if(result_mt.startsWith("-")||result_mt.equals("")){
			return false;
		}else{
			return true;
		}
	}

	public String getMesBalance() {
		String balance = "";
		StringBuffer sb = new StringBuffer("http://sdk2.zucp.net/z_balance.aspx?");
		sb.append("sn=").append(ApplicationListenerImpl.sysConfigureJson.getMessagePartner());
		sb.append("&pwd=").append(ApplicationListenerImpl.sysConfigureJson.getMessageKey());
		
		URL url=null;
		try {
			url = new URL(sb.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			balance= in.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (balance!=null && balance.length()>0) {
			balance = "可用条数: " + balance;
		} else {
			balance = "获取可用余额失败" + balance;
		}
		return balance;
	}

	public static void main(String[] args) throws Exception {
		new ZucpSender().getMesBalance();
	
	}
}

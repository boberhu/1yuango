package com.tech.sms.ihuyi;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.tech.sms.SmsSender;
import com.tech.util.ApplicationListenerImpl;

public class HuyiSender extends SmsSender {

	private static String Send_Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static String Query_Url = "http://106.ihuyi.cn/webservice/sms.php?method=GetNum";

	public boolean send(String mobile, String content) {
		
		String code =null;
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Send_Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
		//int mobile_code = (int)((Math.random()*9+1)*100000);
		//System.out.println(mobile);
		//String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {//提交短信
				new NameValuePair("account", ApplicationListenerImpl.sysConfigureJson.getMessagePartner()), 
			    new NameValuePair("password", ApplicationListenerImpl.sysConfigureJson.getMessageKey()), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();

			code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			//System.out.println(code);
			//System.out.println(msg);
			//System.out.println(smsid);
						
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				return true;
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}

	public String getMesBalance() {
		String balance = "";
		String code =null;
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Query_Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		NameValuePair[] data = {//提交短信
				new NameValuePair("account", "cf_33634420"), 
			    new NameValuePair("password", "ihuyi.com"),
				//new NameValuePair("account", ApplicationListenerImpl.sysConfigureJson.getMessagePartner()), 
			    //new NameValuePair("password", ApplicationListenerImpl.sysConfigureJson.getMessageKey()), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();

			code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String num = root.elementText("num");	
			
			
			//System.out.println(code);
			//System.out.println(msg);
			//System.out.println(smsid);
			if ("2".equals(code)) {
				balance = "可用条数: " + num;
			} else {
				balance = "获取可用余额失败:" + msg;
			}
			
			if("2".equals(code)){
				System.out.println("查询成功");
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*StringBuffer sb = new StringBuffer("http://sdk2.zucp.net/z_balance.aspx?");
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
		}*/
		return balance;
	}

	public static void main(String[] args) throws Exception {
		String balance = new HuyiSender().getMesBalance();
		System.out.println(balance);
		
		//String code = sendOnce("13758249979", "test111");
		//System.out.println(code);
	}
	
}

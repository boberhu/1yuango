package com.tech.util.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpException;
import org.quartz.jobs.ee.jms.SendDestinationMessageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.tech.action.WeixinPayAction;
import com.tech.pojo.SysConfigure;
import com.tech.proto.http.Proxy;
import com.tech.util.AliyunOcsSampleHelp;
import com.tech.util.ApplicationListenerImpl;

public class WxUtil {
	
	protected static final Logger log = LoggerFactory.getLogger(WeixinPayAction.class);
	
	private static final String ENCODING	= "UTF-8";
	private static Proxy httpProxy = new Proxy("UTF-8",60000,30000,1024,200);
	
	public static AccessToken getAccessToken() {
		AccessToken token = (AccessToken)AliyunOcsSampleHelp.getIMemcachedCache().get("wx_access_token");
		if(token!=null){
			return token;
		}
		SysConfigure conf = ApplicationListenerImpl.sysConfigureJson;
		//token = getAccessToken(conf.getWeixinAppId(),conf.getWeixinAppSecret());
		token = getAccessToken("wx58dcdc94eefe2702","d63a1b9dbc851aed762adfc17b4f0f9d");
		AliyunOcsSampleHelp.getIMemcachedCache().set("wx_access_token", token.getExpiresIn()-10, token);
		return token;
	}

	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appid + "&secret=" + appsecret;
		JSONObject jsonObject=null;
		try {
			jsonObject = JSONObject.fromObject(httpProxy.get(requestUrl, null));
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				accessToken.setCreateTime(System.currentTimeMillis());
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	public static String send(String params) {
		StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/message/template/send");
		sb.append("?access_token="+WxUtil.getAccessToken().getToken());
		try {
			final String result = httpProxy.post(sb.toString(), params, ENCODING);
			return result;
		} catch (HttpException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException{
		/*AccessToken token = getAccessToken();
		System.out.println(token.getToken());
		System.out.println(token.getExpiresIn());
		System.out.println(token.getCreateTime());*/
		
		JSONObject json = new JSONObject();
		json.put("touser", "ooGOKs9WLigobep_7nyOelxM5JtM");
		json.put("template_id", "X3i2Kn2qoEt-Oqtjj-j51rUV6Nxm5AiMvkbVCouu1DI");
		json.put("url", "http://www.163.com");
		
		JSONObject data = new JSONObject();
		
		JSONObject resultItem = new JSONObject();
		resultItem.put("value", "恭喜您，中奖啦！");
		resultItem.put("color", "#173177");
		data.put("result", resultItem);
		
		JSONObject totalWinMoney = new JSONObject();
		totalWinMoney.put("value", "中奖320元");
		totalWinMoney.put("color", "#173177");
		data.put("totalWinMoney", totalWinMoney);
		
		json.put("data", data);
		final String result = WxUtil.send(json.toString());
		System.out.println("result="+result);
	}
	
}

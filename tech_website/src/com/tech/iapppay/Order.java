package com.tech.iapppay;

import java.util.Map;

import net.sf.json.JSONObject;

import com.tech.iapppay.config.IAppPaySDKConfig;
import com.tech.iapppay.sign.SignHelper;
import com.tech.iapppay.util.HttpUtils;
import com.tech.iapppay.util.SignUtils;
public class Order {
	/**
	 * 类名：demo 功能 服务器端签名与验签Demo 版本：1.0 日期：2014-06-26 '说明：
	 * '以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己的需要，按照技术文档编写,并非一定要使用该代码。
	 * '该代码仅供学习和研究爱贝云计费接口使用，只是提供一个参考。
	 * 
	 */
	//

	public static void main(String[] argv) {
		CheckSign( IAppPaySDKConfig.APP_ID, IAppPaySDKConfig.WARES_ID_1,"", "adfasdffdeasdfweasdfeff", 10, "aaaa", "AAADDDFFFDDDSASDF", "");
	}
	/*
	 * CP服务端组装请求参数,请求下单url，得到 返回的  transid。把 transid 传到客户端进行支付。
	 * 服务端组装下单请求参数：transdata={"appid":"500000185","waresid":2,"cporderid":"1421206966472","price":1,"currency":"RMB","appuserid":"A100003A832D40","cpprivateinfo":"cpprivateinfo123456","notifyurl":"http://192.168.0.140:8094/monizhuang/api?type=100"}&sign=PNkLyWO5dxzZJrGNRJhSQGJ1oRMpvNDOHmQJntCt7OP3faT6oyL3Jc4Ne6r4IyJMxm3CAk1rxiQBoSuuAf06zsoEWbT4pNIkgqyafP4ai7zKfkJxeX7gsiG6wycT3PqRlwtmF0L7W4RDicrnAGrOQ3ynUxsrGW4oJ+7dKdHM4ZA=&signtype=RSA
	 * 请求地址：以文档给出的为准
	 * 再此请格外注意  每个参数值的 数据类型
	 * 可选参数 ：waresname   price  cpprivateinfo  notifyurl
	 */
	    	/**
	 * 组装请求参数
	 * 
	 * @param appid
	 *          应用编号
	 * @param waresid
	 *          商品编号
         * @param price
	 *          商品价格
         * @param waresname
	 *          商品名称
	 * @param cporderid
	 *          商户订单号
         * @param appuserid
	 *          用户编号
	 * @param cpprivateinfo
	 *          商户私有信息
         * @param notifyurl
	 *          支付结果通知地址
	 * @return 返回组装好的用于post的请求数据
	 * .................
	 */
	public static String ReqData(String appid, int waresid,String cporderid,String waresname,float price,String appuserid,String cpprivateinfo,String notifyurl) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appid", appid);
		jsonObject.put("waresid", waresid);
		jsonObject.put("cporderid", cporderid);
		jsonObject.put("currency", "RMB");
		jsonObject.put("appuserid", appuserid);
		//以下是参数列表中的可选参数
		if(!waresname.isEmpty()){
			jsonObject.put("waresname", waresname);
		}
			/*
			 * 当使用的是 开放价格策略的时候 price的值是 程序自己 设定的价格，使用其他的计费策略的时候
			 * price 不用传值
			 * */ 
			jsonObject.put("price", price);
		if(!cpprivateinfo.isEmpty()){
			jsonObject.put("cpprivateinfo", cpprivateinfo);
		}
		if(!notifyurl.isEmpty()){
			/*
			 * 如果此处不传同步地址，则是以后台传的为准。
			 * */
			jsonObject.put("notifyurl", notifyurl);
		}
		String content = jsonObject.toString();// 组装成 json格式数据
		// 调用签名函数      重点注意： 请一定要阅读  sdk 包中的爱贝AndroidSDK3.4.4\03-接入必看-服务端接口说明及范例\爱贝服务端接入指南及示例0311\IApppayCpSyncForJava \接入必看.txt 
		String sign = SignHelper.sign(content, IAppPaySDKConfig.APPV_KEY);
		String data = "transdata=" + content + "&sign=" + sign+ "&signtype=RSA";// 组装请求参数
		//System.out.println("data:"+data);
		return data;
	}
	// 数据验签
	public static boolean CheckSign(String appid, int waresid,String waresname,String cporderid,float price,String appuserid,String cpprivateinfo,String notifyurl) {
		String reqData = ReqData( appid,  waresid, waresname, cporderid, price, appuserid, cpprivateinfo, notifyurl);
		String respData = HttpUtils.sentPost("http://ipay.iapppay.com:9999/payapi/order", reqData,"UTF-8"); // 请求验证服务端
		
		/*---------------------------------------------如果得到成功响应的结果-----------------------------------------------------------*/
		// 解析结果 得到的 数据为一个以&分割的字符串，需要分成三个部分transdata，sign，signtype。
		// 成功示例：respData == "transdata={"transid":"32011501141440430237"}&sign=NJ1qphncrBZX8nLjonKk2tDIKRKc7vHNej3e/jZaXV7Gn/m1IfJv4lNDmDzy88Vd5Ui1PGMGvfXzbv8zpuc1m1i7lMvelWLGsaGghoXi0Rk7eqCe6tpZmciqj1dCojZoi0/PnuL2Cpcb/aMmgpt8LVIuebYcaFVEmvngLIQXwvE=&signtype=RSA"
		
		Map<String, String> reslutMap = SignUtils.getParmters(respData);
                String transdata = reslutMap.get("transdata"); // "{\"loginname\":\"18701637882\",\"userid\":\"14382295\"}";
                String sign = reslutMap.get("sign"); // "HU6L6dZNR0PJEgsINI5Dlt2L2WfCsN8WDAUP+i/mLNIIwMVCHBBB6GKSrLvz10B5w5LGnX0PQf74oJx8O7JBOMJyQ7oQWoIs4NcpRi73BSxqdnt8XUTIBjfg33sfuGCCQO6GEW6gFHnocsXzNq8MIWk9mvCOFRL3pp/GmKdbbhQ=";
                String signtype = reslutMap.get("signtype"); // "RSA";

		/*
		 * 调用验签接口
		 * 
		 * 主要 目的 确定 收到的数据是我们 发的数据，是没有被非法改动的
		 */
		if (SignHelper.verify(transdata, sign, IAppPaySDKConfig.PLATP_KEY)) {
			System.out.println("verify ok");
			return true;
		} else {
			System.out.println("verify fail");
			return false;
		}

	}


}

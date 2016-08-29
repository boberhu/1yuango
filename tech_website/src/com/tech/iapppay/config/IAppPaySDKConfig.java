package com.tech.iapppay.config;

import com.tech.util.ApplicationListenerImpl;

/**
 *应用接入iAppPay云支付平台sdk集成信息 
 */
public class IAppPaySDKConfig{

	/**
	 * 应用名称：
	 * 应用在iAppPay云支付平台注册的名称
	 */
	public final static  String APP_NAME = "货币战争";

	/**
	 * 应用编号：
	 * 应用在iAppPay云支付平台的编号，此编号用于应用与iAppPay云支付平台的sdk集成 
	 */
	//public final static  String APP_ID = "3001781960";
	public final static  String APP_ID = ApplicationListenerImpl.sysConfigureJson.getIapppayAppId();

	/**
	 * 商品编号：
	 * 应用的商品在iAppPay云支付平台的编号，此编号用于iAppPay云支付平台的sdk到iAppPay云支付平台查找商品详细信息（商品名称、商品销售方式、商品价格）
	 * 编号对应商品名称为：材料1
	 */
	public final static  int WARES_ID_1=1;

	/**
	 * 应用私钥：
	 * 用于对商户应用发送到平台的数据进行加密
	 */
	public final static String APPV_KEY= ApplicationListenerImpl.sysConfigureJson.getIapppayAppKey();
	//public final static String APPV_KEY="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALY+tJ/oZqqohBr/HulcWV4HuV5Emjwiz517BbUSIQRHVXzOzLQaCaSMIC2Y9xz3RbqLC2zonuRr9XRD3F5LxdkhWnuaLbOWPLPHSjSPUGSQEI5/xPWNuunbXteE7wb+q16cW4fAJvXaTtcDRf4IgSo4XEC7dZ48nLQbb/kKhyJxAgMBAAECgYAmzDKn2FIDAt0c4jMyevaLjhloeDJdmwuX+xi786ATyRvp9hyeOuzSmwI+stHo2Lt/expzlRI+jr0ygz/m0zrLIdXKwWeFUwVX2WT1x8c43dkYOsM1re9kh9YyLSS7DebYB/nVWSD58YzefqXznoAmXXdjFjTTR0z7dSXdtaKYAQJBAPpeUqXzzhlHp5Bx1EPSz2FykpzQmDPF6tzMDdsKaW0PoNuN0f+cG0B3mY1oQhMwi5h/bnNGJz/AjYNlODYdBYUCQQC6WBzJB0cOYEw8xnuoUPJyZcyMue89F+L4zHBayzemH74RgNd1kGAh75sxrKpDCz72Nzu4ewisajEfppc5Zlb9AkEAs/MXdC/H8mOODEPETPs4EtJGjm9e9ddK92eyjbzuCXUyKJvGU/HfpHZR51EILMK3kuQEftXtX+UF0Wru+/XgAQJBALC46au5DClVKFgnAJUzF4togeIgYAQ3iBjegduJiKAy0GX3I98p6hGlFDC7CMoGe1K0NR4TCLnDCA11R0u3g90CQQCzsbfgN/PvNeXXa0M+H1iHeOiuOkQK4ntU+n9lvzfm/WEzn8Y95t0hd2IVRshckCsat2TjLpDU0f/HRHmxihmG";
	//public final static String APPV_KEY = "MIICXgIBAAKBgQC2PrSf6GaqqIQa/x7pXFleB7leRJo8Is+dewW1EiEER1V8zsy0GgmkjCAtmPcc90W6iwts6J7ka/V0Q9xeS8XZIVp7mi2zljyzx0o0j1BkkBCOf8T1jbrp217XhO8G/qtenFuHwCb12k7XA0X+CIEqOFxAu3WePJy0G2/5CocicQIDAQABAoGAJswyp9hSAwLdHOIzMnr2i44ZaHgyXZsLl/sYu/OgE8kb6fYcnjrs0psCPrLR6Ni7f3sac5USPo69MoM/5tM6yyHVysFnhVMFV9lk9cfHON3ZGDrDNa3vZIfWMi0kuw3m2Af51Vkg+fGM3n6l856AJl13YxY000dM+3Ul3bWimAECQQD6XlKl884ZR6eQcdRD0s9hcpKc0JgzxerczA3bCmltD6DbjdH/nBtAd5mNaEITMIuYf25zRic/wI2DZTg2HQWFAkEAulgcyQdHDmBMPMZ7qFDycmXMjLnvPRfi+MxwWss3ph++EYDXdZBgIe+bMayqQws+9jc7uHsIrGoxH6aXOWZW/QJBALPzF3Qvx/JjjgxDxEz7OBLSRo5vXvXXSvdnso287gl1MiibxlPx36R2UedRCCzCt5LkBH7V7V/lBdFq7vv14AECQQCwuOmruQwpVShYJwCVMxeLaIHiIGAEN4gY3oHbiYigMtBl9yPfKeoRpRQwuwjKBntStDUeEwi5wwgNdUdLt4PdAkEAs7G34Dfz7zXl12tDPh9Yh3jorjpECuJ7VPp/Zb835v1hM5/GPebdIXdiFUbIXJArGrdk4y6Q1NH/x0R5sYoZhg==";

	/**
	 * 平台公钥：
	 * 用于商户应用对接收平台的数据进行解密
	
	 */
	public final static String PLATP_KEY = ApplicationListenerImpl.sysConfigureJson.getIapppayPlatKey();
	//public final static String PLATP_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1pSaN9ygFfKXePtSzGOVZRxRFfb32mSCTwXumcjTNQ4v0NrvHVn25V7IiG0xSpemZYDLNtY6a3GVpVt3hH+EQjJJqhFr2mh459fRojo6ylIZttnAXeX0J9stpnPtixnhdASnZW87cXpFfMhPQH3W0Tdb7HSgiyYx1FjGhocDO2QIDAQAB";

}
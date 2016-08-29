package com.tech.pojo;

import java.io.Serializable;

public class SysConfigure implements Serializable {
	private static final long serialVersionUID = 3398073894934400022L;

	private Integer id;
	private String imgUrl;
	private String skinUrl;
	private String wwwUrl;
	private String weixinUrl;
	private String domain;
	private String saitName;
	private String shortName;
	private String saitTitle;
	private String saitLogo;
	private String keyword;
	private String description;
	private String mailName;
	private String mailPwd;
	private String mailsmtp;
	private String tenpayPartner;
	private String tenpayKey;
	private Integer tenpayStatus;
	private String alipayPartner;
	private String alipayKey;
	private String alipayMail;
	private Integer alipayStatus;
	private String yeepayKey;
	private String yeepayPartner;
	private Integer yeepayStatus;
	private String chinabankKey;
	private String chinabankPartner;
	private Integer chinabankStatus;
	private String billKey;
	private String billPartner;
	private Integer billStatus;
	private String unionpayKey;
	private String unionpayPartner;
	private Integer unionpayStatus;
	private String weixinPayPartner;
	private String weixinPayKey;
	private String weixinAppId;
	private String weixinAppSecret;
	private String weixinAppKey;
	private Integer weixinStatus;
	private String jdPayPartner;
	private String jdPayKey;
	private Integer jdPayStatus;
	private String qqPayPartner;
	private String qqPayKey;
	private String qqVerify;
	private Integer qqPayStatus;
	private String aliPayUser;
	private String aliPaySignId;
	private String aliPaySignKey;
	private Integer aliPayUserStatus;
	private String tenPayUser;
	private String tenPaySignId;
	private String tenPaySignKye;
	private Integer tenPayUserStatus;
	private String yunPayPartner;
	private String yunPayKey;
	private String yunPayMail;
	private Integer yunPayStatus;
	private String iapppayAppId;
	private String iapppayAppKey;
	private String iapppayPlatKey;
	private Integer iapppayStatus;
	private Integer jubaoPayStatus;
	private String jubaoPayPartner;
	private String icp;
	private String serviceQq;
	private String serviceTel;
	private String qqAppId;
	private String qqAppKey;
	private Integer qqAppStatus;
	private String wxAppID;
	private String wxAppSecret;
	private Integer wxAppStatus;
	private String smsType; 
	private String messagePartner;
	private String messageKey;
	private String messageChannel;
	private String messageSign;
	private String smsbaoUsername;
	private String smsbaoPass;
	private String verifyMsgTpl;
	private String lotteryMsgTpl;
	private Double regBalance;
	private Double recMoney;
	private Double recBalance;
	private Double commission;
	private Integer invite;
	private Integer userData;
	private Integer buyProduct;
	private String authorization;
	private Integer gyjjStatus;
	private Integer gyjjNumber;
	private Integer robots = Integer.valueOf(50);

	public Integer getRobots() {
		return robots;
	}

	public void setRobots(Integer robots) {
		this.robots = robots;
	}

	public SysConfigure() {
	}

	public SysConfigure(String imgUrl, String skinUrl, String wwwUrl, String weixinUrl,
			String domain, String saitName, String shortName, String saitTitle,
			String saitLogo, String keyword, String description,
			String mailName, String mailPwd, String mailsmtp,
			String tenpayPartner, String tenpayKey, Integer tenpayStatus,
			String alipayPartner, String alipayKey, String alipayMail,
			Integer alipayStatus, String yeepayKey, String yeepayPartner,
			Integer yeepayStatus, String chinabankKey, String chinabankPartner,
			Integer chinabankStatus, String billKey, String billPartner,
			Integer billStatus, String unionpayKey, String unionpayPartner,
			Integer unionpayStatus, String weixinPayPartner,
			String weixinPayKey, String weixinAppId, String weixinAppSecret,
			String weixinAppKey, Integer weixinStatus, String jdPayPartner,
			String jdPayKey, Integer jdPayStatus, String qqPayPartner,
			String qqPayKey, String qqVerify, Integer qqPayStatus, String aliPayUser,
			String aliPaySignId, String aliPaySignKey,
			Integer aliPayUserStatus, String tenPayUser, String tenPaySignId,
			String tenPaySignKye, Integer tenPayUserStatus, String icp,
			String serviceQq, String serviceTel, String qqAppId,
			String qqAppKey, Integer qqAppStatus, String messagePartner,
			String messageKey, String messageChannel, String messageSign,
			Double regBalance, Double recMoney, Double recBalance,
			Double commission, Integer invite, Integer userData,
			Integer buyProduct, String authorization, Integer gyjjNumber) {
		this.imgUrl = imgUrl;
		this.skinUrl = skinUrl;
		this.wwwUrl = wwwUrl;
		this.weixinUrl = weixinUrl;
		this.domain = domain;
		this.saitName = saitName;
		this.shortName = shortName;
		this.saitTitle = saitTitle;
		this.saitLogo = saitLogo;
		this.keyword = keyword;
		this.description = description;
		this.mailName = mailName;
		this.mailPwd = mailPwd;
		this.mailsmtp = mailsmtp;
		this.tenpayPartner = tenpayPartner;
		this.tenpayKey = tenpayKey;
		this.tenpayStatus = tenpayStatus;
		this.alipayPartner = alipayPartner;
		this.alipayKey = alipayKey;
		this.alipayMail = alipayMail;
		this.alipayStatus = alipayStatus;
		this.yeepayKey = yeepayKey;
		this.yeepayPartner = yeepayPartner;
		this.yeepayStatus = yeepayStatus;
		this.chinabankKey = chinabankKey;
		this.chinabankPartner = chinabankPartner;
		this.chinabankStatus = chinabankStatus;
		this.billKey = billKey;
		this.billPartner = billPartner;
		this.billStatus = billStatus;
		this.unionpayKey = unionpayKey;
		this.unionpayPartner = unionpayPartner;
		this.unionpayStatus = unionpayStatus;
		this.weixinPayPartner = weixinPayPartner;
		this.weixinPayKey = weixinPayKey;
		this.weixinAppId = weixinAppId;
		this.weixinAppSecret = weixinAppSecret;
		this.weixinAppKey = weixinAppKey;
		this.weixinStatus = weixinStatus;
		this.jdPayPartner = jdPayPartner;
		this.jdPayKey = jdPayKey;
		this.jdPayStatus = jdPayStatus;
		this.qqPayPartner = qqPayPartner;
		this.qqPayKey = qqPayKey;
		this.qqVerify = qqVerify;
		this.qqPayStatus = qqPayStatus;
		this.aliPayUser = aliPayUser;
		this.aliPaySignId = aliPaySignId;
		this.aliPaySignKey = aliPaySignKey;
		this.aliPayUserStatus = aliPayUserStatus;
		this.tenPayUser = tenPayUser;
		this.tenPaySignId = tenPaySignId;
		this.tenPaySignKye = tenPaySignKye;
		this.tenPayUserStatus = tenPayUserStatus;
		this.icp = icp;
		this.serviceQq = serviceQq;
		this.serviceTel = serviceTel;
		this.qqAppId = qqAppId;
		this.qqAppKey = qqAppKey;
		this.qqAppStatus = qqAppStatus;
		this.messagePartner = messagePartner;
		this.messageKey = messageKey;
		this.messageChannel = messageChannel;
		this.messageSign = messageSign;
		this.regBalance = regBalance;
		this.recMoney = recMoney;
		this.recBalance = recBalance;
		this.commission = commission;
		this.invite = invite;
		this.userData = userData;
		this.buyProduct = buyProduct;
		this.authorization = authorization;
		this.gyjjNumber = gyjjNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSkinUrl() {
		return skinUrl;
	}

	public void setSkinUrl(String skinUrl) {
		this.skinUrl = skinUrl;
	}

	public String getWwwUrl() {
		return wwwUrl;
	}

	public void setWwwUrl(String wwwUrl) {
		this.wwwUrl = wwwUrl;
	}

	public String getWeixinUrl() {
		return weixinUrl;
	}

	public void setWeixinUrl(String weixinUrl) {
		this.weixinUrl = weixinUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSaitName() {
		return saitName;
	}

	public void setSaitName(String saitName) {
		this.saitName = saitName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSaitTitle() {
		return saitTitle;
	}

	public void setSaitTitle(String saitTitle) {
		this.saitTitle = saitTitle;
	}

	public String getSaitLogo() {
		return saitLogo;
	}

	public void setSaitLogo(String saitLogo) {
		this.saitLogo = saitLogo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailPwd() {
		return mailPwd;
	}

	public void setMailPwd(String mailPwd) {
		this.mailPwd = mailPwd;
	}

	public String getMailsmtp() {
		return mailsmtp;
	}

	public void setMailsmtp(String mailsmtp) {
		this.mailsmtp = mailsmtp;
	}

	public String getTenpayPartner() {
		return tenpayPartner;
	}

	public void setTenpayPartner(String tenpayPartner) {
		this.tenpayPartner = tenpayPartner;
	}

	public String getTenpayKey() {
		return tenpayKey;
	}

	public void setTenpayKey(String tenpayKey) {
		this.tenpayKey = tenpayKey;
	}

	public Integer getTenpayStatus() {
		return tenpayStatus;
	}

	public void setTenpayStatus(Integer tenpayStatus) {
		this.tenpayStatus = tenpayStatus;
	}

	public String getAlipayPartner() {
		return alipayPartner;
	}

	public void setAlipayPartner(String alipayPartner) {
		this.alipayPartner = alipayPartner;
	}

	public String getAlipayKey() {
		return alipayKey;
	}

	public void setAlipayKey(String alipayKey) {
		this.alipayKey = alipayKey;
	}

	public String getAlipayMail() {
		return alipayMail;
	}

	public void setAlipayMail(String alipayMail) {
		this.alipayMail = alipayMail;
	}

	public Integer getAlipayStatus() {
		return alipayStatus;
	}

	public void setAlipayStatus(Integer alipayStatus) {
		this.alipayStatus = alipayStatus;
	}

	public String getYeepayKey() {
		return yeepayKey;
	}

	public void setYeepayKey(String yeepayKey) {
		this.yeepayKey = yeepayKey;
	}

	public String getYeepayPartner() {
		return yeepayPartner;
	}

	public void setYeepayPartner(String yeepayPartner) {
		this.yeepayPartner = yeepayPartner;
	}

	public Integer getYeepayStatus() {
		return yeepayStatus;
	}

	public void setYeepayStatus(Integer yeepayStatus) {
		this.yeepayStatus = yeepayStatus;
	}

	public String getChinabankKey() {
		return chinabankKey;
	}

	public void setChinabankKey(String chinabankKey) {
		this.chinabankKey = chinabankKey;
	}

	public String getChinabankPartner() {
		return chinabankPartner;
	}

	public void setChinabankPartner(String chinabankPartner) {
		this.chinabankPartner = chinabankPartner;
	}

	public Integer getChinabankStatus() {
		return chinabankStatus;
	}

	public void setChinabankStatus(Integer chinabankStatus) {
		this.chinabankStatus = chinabankStatus;
	}

	public String getBillKey() {
		return billKey;
	}

	public void setBillKey(String billKey) {
		this.billKey = billKey;
	}

	public String getBillPartner() {
		return billPartner;
	}

	public void setBillPartner(String billPartner) {
		this.billPartner = billPartner;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public String getUnionpayKey() {
		return unionpayKey;
	}

	public void setUnionpayKey(String unionpayKey) {
		this.unionpayKey = unionpayKey;
	}

	public String getUnionpayPartner() {
		return unionpayPartner;
	}

	public void setUnionpayPartner(String unionpayPartner) {
		this.unionpayPartner = unionpayPartner;
	}

	public Integer getUnionpayStatus() {
		return unionpayStatus;
	}

	public void setUnionpayStatus(Integer unionpayStatus) {
		this.unionpayStatus = unionpayStatus;
	}

	public String getWeixinPayPartner() {
		return weixinPayPartner;
	}

	public void setWeixinPayPartner(String weixinPayPartner) {
		this.weixinPayPartner = weixinPayPartner;
	}

	public String getWeixinPayKey() {
		return weixinPayKey;
	}

	public void setWeixinPayKey(String weixinPayKey) {
		this.weixinPayKey = weixinPayKey;
	}

	public String getWeixinAppId() {
		return weixinAppId;
	}

	public void setWeixinAppId(String weixinAppId) {
		this.weixinAppId = weixinAppId;
	}

	public String getWeixinAppSecret() {
		return weixinAppSecret;
	}

	public void setWeixinAppSecret(String weixinAppSecret) {
		this.weixinAppSecret = weixinAppSecret;
	}

	public String getWeixinAppKey() {
		return weixinAppKey;
	}

	public void setWeixinAppKey(String weixinAppKey) {
		this.weixinAppKey = weixinAppKey;
	}

	public Integer getWeixinStatus() {
		return weixinStatus;
	}

	public void setWeixinStatus(Integer weixinStatus) {
		this.weixinStatus = weixinStatus;
	}

	public String getJdPayPartner() {
		return jdPayPartner;
	}

	public void setJdPayPartner(String jdPayPartner) {
		this.jdPayPartner = jdPayPartner;
	}

	public String getJdPayKey() {
		return jdPayKey;
	}

	public void setJdPayKey(String jdPayKey) {
		this.jdPayKey = jdPayKey;
	}

	public Integer getJdPayStatus() {
		return jdPayStatus;
	}

	public void setJdPayStatus(Integer jdPayStatus) {
		this.jdPayStatus = jdPayStatus;
	}

	public String getQqPayPartner() {
		return qqPayPartner;
	}

	public void setQqPayPartner(String qqPayPartner) {
		this.qqPayPartner = qqPayPartner;
	}

	public String getQqPayKey() {
		return qqPayKey;
	}

	public void setQqPayKey(String qqPayKey) {
		this.qqPayKey = qqPayKey;
	}

	public Integer getQqPayStatus() {
		return qqPayStatus;
	}

	public void setQqPayStatus(Integer qqPayStatus) {
		this.qqPayStatus = qqPayStatus;
	}

	public String getAliPayUser() {
		return aliPayUser;
	}

	public void setAliPayUser(String aliPayUser) {
		this.aliPayUser = aliPayUser;
	}

	public String getAliPaySignId() {
		return aliPaySignId;
	}

	public void setAliPaySignId(String aliPaySignId) {
		this.aliPaySignId = aliPaySignId;
	}

	public String getAliPaySignKey() {
		return aliPaySignKey;
	}

	public void setAliPaySignKey(String aliPaySignKey) {
		this.aliPaySignKey = aliPaySignKey;
	}

	public Integer getAliPayUserStatus() {
		return aliPayUserStatus;
	}

	public void setAliPayUserStatus(Integer aliPayUserStatus) {
		this.aliPayUserStatus = aliPayUserStatus;
	}

	public String getTenPayUser() {
		return tenPayUser;
	}

	public void setTenPayUser(String tenPayUser) {
		this.tenPayUser = tenPayUser;
	}

	public String getTenPaySignId() {
		return tenPaySignId;
	}

	public void setTenPaySignId(String tenPaySignId) {
		this.tenPaySignId = tenPaySignId;
	}

	public String getTenPaySignKye() {
		return tenPaySignKye;
	}

	public void setTenPaySignKye(String tenPaySignKye) {
		this.tenPaySignKye = tenPaySignKye;
	}

	public Integer getTenPayUserStatus() {
		return tenPayUserStatus;
	}

	public void setTenPayUserStatus(Integer tenPayUserStatus) {
		this.tenPayUserStatus = tenPayUserStatus;
	}

	public String getYunPayPartner() {
		return yunPayPartner;
	}

	public void setYunPayPartner(String yunPayPartner) {
		this.yunPayPartner = yunPayPartner;
	}

	public String getYunPayKey() {
		return yunPayKey;
	}

	public void setYunPayKey(String yunPayKey) {
		this.yunPayKey = yunPayKey;
	}

	public String getYunPayMail() {
		return yunPayMail;
	}

	public void setYunPayMail(String yunPayMail) {
		this.yunPayMail = yunPayMail;
	}

	public Integer getYunPayStatus() {
		return yunPayStatus;
	}

	public void setYunPayStatus(Integer yunPayStatus) {
		this.yunPayStatus = yunPayStatus;
	}

	public String getIapppayAppId() {
		return iapppayAppId;
	}

	public void setIapppayAppId(String iapppayAppId) {
		this.iapppayAppId = iapppayAppId;
	}

	public String getIapppayAppKey() {
		return iapppayAppKey;
	}

	public void setIapppayAppKey(String iapppayAppKey) {
		this.iapppayAppKey = iapppayAppKey;
	}

	public String getIapppayPlatKey() {
		return iapppayPlatKey;
	}

	public void setIapppayPlatKey(String iapppayPlatKey) {
		this.iapppayPlatKey = iapppayPlatKey;
	}

	public Integer getIapppayStatus() {
		return iapppayStatus;
	}

	public void setIapppayStatus(Integer iapppayStatus) {
		this.iapppayStatus = iapppayStatus;
	}

	public Integer getJubaoPayStatus() {
		return jubaoPayStatus;
	}

	public void setJubaoPayStatus(Integer jubaoPayStatus) {
		this.jubaoPayStatus = jubaoPayStatus;
	}

	public String getJubaoPayPartner() {
		return jubaoPayPartner;
	}

	public void setJubaoPayPartner(String jubaoPayPartner) {
		this.jubaoPayPartner = jubaoPayPartner;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getServiceQq() {
		return serviceQq;
	}

	public void setServiceQq(String serviceQq) {
		this.serviceQq = serviceQq;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public String getQqAppId() {
		return qqAppId;
	}

	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}

	public String getQqAppKey() {
		return qqAppKey;
	}

	public void setQqAppKey(String qqAppKey) {
		this.qqAppKey = qqAppKey;
	}

	public String getQqVerify() {
		return qqVerify;
	}

	public void setQqVerify(String qqVerify) {
		this.qqVerify = qqVerify;
	}

	public Integer getQqAppStatus() {
		return qqAppStatus;
	}

	public void setQqAppStatus(Integer qqAppStatus) {
		this.qqAppStatus = qqAppStatus;
	}
	
	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getMessagePartner() {
		return messagePartner;
	}

	public void setMessagePartner(String messagePartner) {
		this.messagePartner = messagePartner;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageChannel() {
		return messageChannel;
	}

	public void setMessageChannel(String messageChannel) {
		this.messageChannel = messageChannel;
	}

	public String getMessageSign() {
		return messageSign;
	}

	public void setMessageSign(String messageSign) {
		this.messageSign = messageSign;
	}
	

	public String getVerifyMsgTpl() {
		return verifyMsgTpl;
	}

	public void setVerifyMsgTpl(String verifyMsgTpl) {
		this.verifyMsgTpl = verifyMsgTpl;
	}

	public String getLotteryMsgTpl() {
		return lotteryMsgTpl;
	}

	public void setLotteryMsgTpl(String lotteryMsgTpl) {
		this.lotteryMsgTpl = lotteryMsgTpl;
	}

	public Double getRegBalance() {
		return regBalance;
	}

	public void setRegBalance(Double regBalance) {
		this.regBalance = regBalance;
	}

	public Double getRecMoney() {
		return recMoney;
	}

	public void setRecMoney(Double recMoney) {
		this.recMoney = recMoney;
	}

	public Double getRecBalance() {
		return recBalance;
	}

	public void setRecBalance(Double recBalance) {
		this.recBalance = recBalance;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getInvite() {
		return invite;
	}

	public void setInvite(Integer invite) {
		this.invite = invite;
	}

	public Integer getUserData() {
		return userData;
	}

	public void setUserData(Integer userData) {
		this.userData = userData;
	}

	public Integer getBuyProduct() {
		return buyProduct;
	}

	public void setBuyProduct(Integer buyProduct) {
		this.buyProduct = buyProduct;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Integer getGyjjNumber() {
		return gyjjNumber;
	}

	public void setGyjjNumber(Integer gyjjNumber) {
		this.gyjjNumber = gyjjNumber;
	}
	
	public String getSmsbaoUsername() {
		return smsbaoUsername;
	}

	public void setSmsbaoUsername(String smsbaoUsername) {
		this.smsbaoUsername = smsbaoUsername;
	}

	public String getSmsbaoPass() {
		return smsbaoPass;
	}

	public void setSmsbaoPass(String smsbaoPass) {
		this.smsbaoPass = smsbaoPass;
	}

	public Integer getGyjjStatus() {
		return gyjjStatus;
	}

	public void setGyjjStatus(Integer gyjjStatus) {
		this.gyjjStatus = gyjjStatus;
	}

	public String getWxAppID() {
		return wxAppID;
	}

	public void setWxAppID(String wxAppID) {
		this.wxAppID = wxAppID;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	public Integer getWxAppStatus() {
		return wxAppStatus;
	}

	public void setWxAppStatus(Integer wxAppStatus) {
		this.wxAppStatus = wxAppStatus;
	}
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipaySecurityRiskDetectResponse;
import java.util.Map;

public class AlipaySecurityRiskDetectRequest
  implements AlipayRequest<AlipaySecurityRiskDetectResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String buyerAccountNo;
  private String buyerBindBankcard;
  private String buyerBindBankcardType;
  private String buyerBindMobile;
  private String buyerGrade;
  private String buyerIdentityNo;
  private String buyerIdentityType;
  private String buyerRealName;
  private String buyerRegDate;
  private String buyerRegEmail;
  private String buyerRegMobile;
  private String buyerSceneBankcard;
  private String buyerSceneBankcardType;
  private String buyerSceneEmail;
  private String buyerSceneMobile;
  private String currency;
  private String envClientBaseBand;
  private String envClientBaseStation;
  private String envClientCoordinates;
  private String envClientImei;
  private String envClientImsi;
  private String envClientIosUdid;
  private String envClientIp;
  private String envClientMac;
  private String envClientScreen;
  private String envClientUuid;
  private String itemQuantity;
  private String itemUnitPrice;
  private String jsTokenId;
  private String orderAmount;
  private String orderCategory;
  private String orderCredateTime;
  private String orderItemCity;
  private String orderItemName;
  private String orderNo;
  private String partnerId;
  private String receiverAddress;
  private String receiverCity;
  private String receiverDistrict;
  private String receiverEmail;
  private String receiverMobile;
  private String receiverName;
  private String receiverState;
  private String receiverZip;
  private String sceneCode;
  private String sellerAccountNo;
  private String sellerBindBankcard;
  private String sellerBindBankcardType;
  private String sellerBindMobile;
  private String sellerIdentityNo;
  private String sellerIdentityType;
  private String sellerRealName;
  private String sellerRegDate;
  private String sellerRegEmail;
  private String sellerRegMoile;
  private String transportType;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setBuyerAccountNo(String buyerAccountNo)
  {
    this.buyerAccountNo = buyerAccountNo;
  }
  
  public String getBuyerAccountNo()
  {
    return buyerAccountNo;
  }
  
  public void setBuyerBindBankcard(String buyerBindBankcard)
  {
    this.buyerBindBankcard = buyerBindBankcard;
  }
  
  public String getBuyerBindBankcard()
  {
    return buyerBindBankcard;
  }
  
  public void setBuyerBindBankcardType(String buyerBindBankcardType)
  {
    this.buyerBindBankcardType = buyerBindBankcardType;
  }
  
  public String getBuyerBindBankcardType()
  {
    return buyerBindBankcardType;
  }
  
  public void setBuyerBindMobile(String buyerBindMobile)
  {
    this.buyerBindMobile = buyerBindMobile;
  }
  
  public String getBuyerBindMobile()
  {
    return buyerBindMobile;
  }
  
  public void setBuyerGrade(String buyerGrade)
  {
    this.buyerGrade = buyerGrade;
  }
  
  public String getBuyerGrade()
  {
    return buyerGrade;
  }
  
  public void setBuyerIdentityNo(String buyerIdentityNo)
  {
    this.buyerIdentityNo = buyerIdentityNo;
  }
  
  public String getBuyerIdentityNo()
  {
    return buyerIdentityNo;
  }
  
  public void setBuyerIdentityType(String buyerIdentityType)
  {
    this.buyerIdentityType = buyerIdentityType;
  }
  
  public String getBuyerIdentityType()
  {
    return buyerIdentityType;
  }
  
  public void setBuyerRealName(String buyerRealName)
  {
    this.buyerRealName = buyerRealName;
  }
  
  public String getBuyerRealName()
  {
    return buyerRealName;
  }
  
  public void setBuyerRegDate(String buyerRegDate)
  {
    this.buyerRegDate = buyerRegDate;
  }
  
  public String getBuyerRegDate()
  {
    return buyerRegDate;
  }
  
  public void setBuyerRegEmail(String buyerRegEmail)
  {
    this.buyerRegEmail = buyerRegEmail;
  }
  
  public String getBuyerRegEmail()
  {
    return buyerRegEmail;
  }
  
  public void setBuyerRegMobile(String buyerRegMobile)
  {
    this.buyerRegMobile = buyerRegMobile;
  }
  
  public String getBuyerRegMobile()
  {
    return buyerRegMobile;
  }
  
  public void setBuyerSceneBankcard(String buyerSceneBankcard)
  {
    this.buyerSceneBankcard = buyerSceneBankcard;
  }
  
  public String getBuyerSceneBankcard()
  {
    return buyerSceneBankcard;
  }
  
  public void setBuyerSceneBankcardType(String buyerSceneBankcardType)
  {
    this.buyerSceneBankcardType = buyerSceneBankcardType;
  }
  
  public String getBuyerSceneBankcardType()
  {
    return buyerSceneBankcardType;
  }
  
  public void setBuyerSceneEmail(String buyerSceneEmail)
  {
    this.buyerSceneEmail = buyerSceneEmail;
  }
  
  public String getBuyerSceneEmail()
  {
    return buyerSceneEmail;
  }
  
  public void setBuyerSceneMobile(String buyerSceneMobile)
  {
    this.buyerSceneMobile = buyerSceneMobile;
  }
  
  public String getBuyerSceneMobile()
  {
    return buyerSceneMobile;
  }
  
  public void setCurrency(String currency)
  {
    this.currency = currency;
  }
  
  public String getCurrency()
  {
    return currency;
  }
  
  public void setEnvClientBaseBand(String envClientBaseBand)
  {
    this.envClientBaseBand = envClientBaseBand;
  }
  
  public String getEnvClientBaseBand()
  {
    return envClientBaseBand;
  }
  
  public void setEnvClientBaseStation(String envClientBaseStation)
  {
    this.envClientBaseStation = envClientBaseStation;
  }
  
  public String getEnvClientBaseStation()
  {
    return envClientBaseStation;
  }
  
  public void setEnvClientCoordinates(String envClientCoordinates)
  {
    this.envClientCoordinates = envClientCoordinates;
  }
  
  public String getEnvClientCoordinates()
  {
    return envClientCoordinates;
  }
  
  public void setEnvClientImei(String envClientImei)
  {
    this.envClientImei = envClientImei;
  }
  
  public String getEnvClientImei()
  {
    return envClientImei;
  }
  
  public void setEnvClientImsi(String envClientImsi)
  {
    this.envClientImsi = envClientImsi;
  }
  
  public String getEnvClientImsi()
  {
    return envClientImsi;
  }
  
  public void setEnvClientIosUdid(String envClientIosUdid)
  {
    this.envClientIosUdid = envClientIosUdid;
  }
  
  public String getEnvClientIosUdid()
  {
    return envClientIosUdid;
  }
  
  public void setEnvClientIp(String envClientIp)
  {
    this.envClientIp = envClientIp;
  }
  
  public String getEnvClientIp()
  {
    return envClientIp;
  }
  
  public void setEnvClientMac(String envClientMac)
  {
    this.envClientMac = envClientMac;
  }
  
  public String getEnvClientMac()
  {
    return envClientMac;
  }
  
  public void setEnvClientScreen(String envClientScreen)
  {
    this.envClientScreen = envClientScreen;
  }
  
  public String getEnvClientScreen()
  {
    return envClientScreen;
  }
  
  public void setEnvClientUuid(String envClientUuid)
  {
    this.envClientUuid = envClientUuid;
  }
  
  public String getEnvClientUuid()
  {
    return envClientUuid;
  }
  
  public void setItemQuantity(String itemQuantity)
  {
    this.itemQuantity = itemQuantity;
  }
  
  public String getItemQuantity()
  {
    return itemQuantity;
  }
  
  public void setItemUnitPrice(String itemUnitPrice)
  {
    this.itemUnitPrice = itemUnitPrice;
  }
  
  public String getItemUnitPrice()
  {
    return itemUnitPrice;
  }
  
  public void setJsTokenId(String jsTokenId)
  {
    this.jsTokenId = jsTokenId;
  }
  
  public String getJsTokenId()
  {
    return jsTokenId;
  }
  
  public void setOrderAmount(String orderAmount)
  {
    this.orderAmount = orderAmount;
  }
  
  public String getOrderAmount()
  {
    return orderAmount;
  }
  
  public void setOrderCategory(String orderCategory)
  {
    this.orderCategory = orderCategory;
  }
  
  public String getOrderCategory()
  {
    return orderCategory;
  }
  
  public void setOrderCredateTime(String orderCredateTime)
  {
    this.orderCredateTime = orderCredateTime;
  }
  
  public String getOrderCredateTime()
  {
    return orderCredateTime;
  }
  
  public void setOrderItemCity(String orderItemCity)
  {
    this.orderItemCity = orderItemCity;
  }
  
  public String getOrderItemCity()
  {
    return orderItemCity;
  }
  
  public void setOrderItemName(String orderItemName)
  {
    this.orderItemName = orderItemName;
  }
  
  public String getOrderItemName()
  {
    return orderItemName;
  }
  
  public void setOrderNo(String orderNo)
  {
    this.orderNo = orderNo;
  }
  
  public String getOrderNo()
  {
    return orderNo;
  }
  
  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }
  
  public String getPartnerId()
  {
    return partnerId;
  }
  
  public void setReceiverAddress(String receiverAddress)
  {
    this.receiverAddress = receiverAddress;
  }
  
  public String getReceiverAddress()
  {
    return receiverAddress;
  }
  
  public void setReceiverCity(String receiverCity)
  {
    this.receiverCity = receiverCity;
  }
  
  public String getReceiverCity()
  {
    return receiverCity;
  }
  
  public void setReceiverDistrict(String receiverDistrict)
  {
    this.receiverDistrict = receiverDistrict;
  }
  
  public String getReceiverDistrict()
  {
    return receiverDistrict;
  }
  
  public void setReceiverEmail(String receiverEmail)
  {
    this.receiverEmail = receiverEmail;
  }
  
  public String getReceiverEmail()
  {
    return receiverEmail;
  }
  
  public void setReceiverMobile(String receiverMobile)
  {
    this.receiverMobile = receiverMobile;
  }
  
  public String getReceiverMobile()
  {
    return receiverMobile;
  }
  
  public void setReceiverName(String receiverName)
  {
    this.receiverName = receiverName;
  }
  
  public String getReceiverName()
  {
    return receiverName;
  }
  
  public void setReceiverState(String receiverState)
  {
    this.receiverState = receiverState;
  }
  
  public String getReceiverState()
  {
    return receiverState;
  }
  
  public void setReceiverZip(String receiverZip)
  {
    this.receiverZip = receiverZip;
  }
  
  public String getReceiverZip()
  {
    return receiverZip;
  }
  
  public void setSceneCode(String sceneCode)
  {
    this.sceneCode = sceneCode;
  }
  
  public String getSceneCode()
  {
    return sceneCode;
  }
  
  public void setSellerAccountNo(String sellerAccountNo)
  {
    this.sellerAccountNo = sellerAccountNo;
  }
  
  public String getSellerAccountNo()
  {
    return sellerAccountNo;
  }
  
  public void setSellerBindBankcard(String sellerBindBankcard)
  {
    this.sellerBindBankcard = sellerBindBankcard;
  }
  
  public String getSellerBindBankcard()
  {
    return sellerBindBankcard;
  }
  
  public void setSellerBindBankcardType(String sellerBindBankcardType)
  {
    this.sellerBindBankcardType = sellerBindBankcardType;
  }
  
  public String getSellerBindBankcardType()
  {
    return sellerBindBankcardType;
  }
  
  public void setSellerBindMobile(String sellerBindMobile)
  {
    this.sellerBindMobile = sellerBindMobile;
  }
  
  public String getSellerBindMobile()
  {
    return sellerBindMobile;
  }
  
  public void setSellerIdentityNo(String sellerIdentityNo)
  {
    this.sellerIdentityNo = sellerIdentityNo;
  }
  
  public String getSellerIdentityNo()
  {
    return sellerIdentityNo;
  }
  
  public void setSellerIdentityType(String sellerIdentityType)
  {
    this.sellerIdentityType = sellerIdentityType;
  }
  
  public String getSellerIdentityType()
  {
    return sellerIdentityType;
  }
  
  public void setSellerRealName(String sellerRealName)
  {
    this.sellerRealName = sellerRealName;
  }
  
  public String getSellerRealName()
  {
    return sellerRealName;
  }
  
  public void setSellerRegDate(String sellerRegDate)
  {
    this.sellerRegDate = sellerRegDate;
  }
  
  public String getSellerRegDate()
  {
    return sellerRegDate;
  }
  
  public void setSellerRegEmail(String sellerRegEmail)
  {
    this.sellerRegEmail = sellerRegEmail;
  }
  
  public String getSellerRegEmail()
  {
    return sellerRegEmail;
  }
  
  public void setSellerRegMoile(String sellerRegMoile)
  {
    this.sellerRegMoile = sellerRegMoile;
  }
  
  public String getSellerRegMoile()
  {
    return sellerRegMoile;
  }
  
  public void setTransportType(String transportType)
  {
    this.transportType = transportType;
  }
  
  public String getTransportType()
  {
    return transportType;
  }
  
  public String getApiVersion()
  {
    return apiVersion;
  }
  
  public void setApiVersion(String apiVersion)
  {
    this.apiVersion = apiVersion;
  }
  
  public void setTerminalType(String terminalType)
  {
    this.terminalType = terminalType;
  }
  
  public String getTerminalType()
  {
    return terminalType;
  }
  
  public void setTerminalInfo(String terminalInfo)
  {
    this.terminalInfo = terminalInfo;
  }
  
  public String getTerminalInfo()
  {
    return terminalInfo;
  }
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.security.risk.detect";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("buyer_account_no", buyerAccountNo);
    txtParams.put("buyer_bind_bankcard", buyerBindBankcard);
    txtParams.put("buyer_bind_bankcard_type", buyerBindBankcardType);
    txtParams.put("buyer_bind_mobile", buyerBindMobile);
    txtParams.put("buyer_grade", buyerGrade);
    txtParams.put("buyer_identity_no", buyerIdentityNo);
    txtParams.put("buyer_identity_type", buyerIdentityType);
    txtParams.put("buyer_real_name", buyerRealName);
    txtParams.put("buyer_reg_date", buyerRegDate);
    txtParams.put("buyer_reg_email", buyerRegEmail);
    txtParams.put("buyer_reg_mobile", buyerRegMobile);
    txtParams.put("buyer_scene_bankcard", buyerSceneBankcard);
    txtParams.put("buyer_scene_bankcard_type", buyerSceneBankcardType);
    txtParams.put("buyer_scene_email", buyerSceneEmail);
    txtParams.put("buyer_scene_mobile", buyerSceneMobile);
    txtParams.put("currency", currency);
    txtParams.put("env_client_base_band", envClientBaseBand);
    txtParams.put("env_client_base_station", envClientBaseStation);
    txtParams.put("env_client_coordinates", envClientCoordinates);
    txtParams.put("env_client_imei", envClientImei);
    txtParams.put("env_client_imsi", envClientImsi);
    txtParams.put("env_client_ios_udid", envClientIosUdid);
    txtParams.put("env_client_ip", envClientIp);
    txtParams.put("env_client_mac", envClientMac);
    txtParams.put("env_client_screen", envClientScreen);
    txtParams.put("env_client_uuid", envClientUuid);
    txtParams.put("item_quantity", itemQuantity);
    txtParams.put("item_unit_price", itemUnitPrice);
    txtParams.put("js_token_id", jsTokenId);
    txtParams.put("order_amount", orderAmount);
    txtParams.put("order_category", orderCategory);
    txtParams.put("order_credate_time", orderCredateTime);
    txtParams.put("order_item_city", orderItemCity);
    txtParams.put("order_item_name", orderItemName);
    txtParams.put("order_no", orderNo);
    txtParams.put("partner_id", partnerId);
    txtParams.put("receiver_address", receiverAddress);
    txtParams.put("receiver_city", receiverCity);
    txtParams.put("receiver_district", receiverDistrict);
    txtParams.put("receiver_email", receiverEmail);
    txtParams.put("receiver_mobile", receiverMobile);
    txtParams.put("receiver_name", receiverName);
    txtParams.put("receiver_state", receiverState);
    txtParams.put("receiver_zip", receiverZip);
    txtParams.put("scene_code", sceneCode);
    txtParams.put("seller_account_no", sellerAccountNo);
    txtParams.put("seller_bind_bankcard", sellerBindBankcard);
    txtParams.put("seller_bind_bankcard_type", sellerBindBankcardType);
    txtParams.put("seller_bind_mobile", sellerBindMobile);
    txtParams.put("seller_identity_no", sellerIdentityNo);
    txtParams.put("seller_identity_type", sellerIdentityType);
    txtParams.put("seller_real_name", sellerRealName);
    txtParams.put("seller_reg_date", sellerRegDate);
    txtParams.put("seller_reg_email", sellerRegEmail);
    txtParams.put("seller_reg_moile", sellerRegMoile);
    txtParams.put("transport_type", transportType);
    if (udfParams != null) {
      txtParams.putAll(udfParams);
    }
    return txtParams;
  }
  
  public void putOtherTextParam(String key, String value)
  {
    if (udfParams == null) {
      udfParams = new AlipayHashMap();
    }
    udfParams.put(key, value);
  }
  
  public Class<AlipaySecurityRiskDetectResponse> getResponseClass()
  {
    return AlipaySecurityRiskDetectResponse.class;
  }
}

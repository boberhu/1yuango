package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayAcquireCreateandpayResponse;
import java.util.Map;

public class AlipayAcquireCreateandpayRequest
  implements AlipayRequest<AlipayAcquireCreateandpayResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String alipayCaRequest;
  private String body;
  private String buyerEmail;
  private String buyerId;
  private String channelParameters;
  private String currency;
  private String dynamicId;
  private String dynamicIdType;
  private String extendParams;
  private String formatType;
  private String goodsDetail;
  private String itBPay;
  private String mcardParameters;
  private String operatorId;
  private String operatorType;
  private String outTradeNo;
  private String price;
  private String quantity;
  private String refIds;
  private String royaltyParameters;
  private String royaltyType;
  private String sellerEmail;
  private String sellerId;
  private String showUrl;
  private String subject;
  private String totalFee;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setAlipayCaRequest(String alipayCaRequest)
  {
    this.alipayCaRequest = alipayCaRequest;
  }
  
  public String getAlipayCaRequest()
  {
    return alipayCaRequest;
  }
  
  public void setBody(String body)
  {
    this.body = body;
  }
  
  public String getBody()
  {
    return body;
  }
  
  public void setBuyerEmail(String buyerEmail)
  {
    this.buyerEmail = buyerEmail;
  }
  
  public String getBuyerEmail()
  {
    return buyerEmail;
  }
  
  public void setBuyerId(String buyerId)
  {
    this.buyerId = buyerId;
  }
  
  public String getBuyerId()
  {
    return buyerId;
  }
  
  public void setChannelParameters(String channelParameters)
  {
    this.channelParameters = channelParameters;
  }
  
  public String getChannelParameters()
  {
    return channelParameters;
  }
  
  public void setCurrency(String currency)
  {
    this.currency = currency;
  }
  
  public String getCurrency()
  {
    return currency;
  }
  
  public void setDynamicId(String dynamicId)
  {
    this.dynamicId = dynamicId;
  }
  
  public String getDynamicId()
  {
    return dynamicId;
  }
  
  public void setDynamicIdType(String dynamicIdType)
  {
    this.dynamicIdType = dynamicIdType;
  }
  
  public String getDynamicIdType()
  {
    return dynamicIdType;
  }
  
  public void setExtendParams(String extendParams)
  {
    this.extendParams = extendParams;
  }
  
  public String getExtendParams()
  {
    return extendParams;
  }
  
  public void setFormatType(String formatType)
  {
    this.formatType = formatType;
  }
  
  public String getFormatType()
  {
    return formatType;
  }
  
  public void setGoodsDetail(String goodsDetail)
  {
    this.goodsDetail = goodsDetail;
  }
  
  public String getGoodsDetail()
  {
    return goodsDetail;
  }
  
  public void setItBPay(String itBPay)
  {
    this.itBPay = itBPay;
  }
  
  public String getItBPay()
  {
    return itBPay;
  }
  
  public void setMcardParameters(String mcardParameters)
  {
    this.mcardParameters = mcardParameters;
  }
  
  public String getMcardParameters()
  {
    return mcardParameters;
  }
  
  public void setOperatorId(String operatorId)
  {
    this.operatorId = operatorId;
  }
  
  public String getOperatorId()
  {
    return operatorId;
  }
  
  public void setOperatorType(String operatorType)
  {
    this.operatorType = operatorType;
  }
  
  public String getOperatorType()
  {
    return operatorType;
  }
  
  public void setOutTradeNo(String outTradeNo)
  {
    this.outTradeNo = outTradeNo;
  }
  
  public String getOutTradeNo()
  {
    return outTradeNo;
  }
  
  public void setPrice(String price)
  {
    this.price = price;
  }
  
  public String getPrice()
  {
    return price;
  }
  
  public void setQuantity(String quantity)
  {
    this.quantity = quantity;
  }
  
  public String getQuantity()
  {
    return quantity;
  }
  
  public void setRefIds(String refIds)
  {
    this.refIds = refIds;
  }
  
  public String getRefIds()
  {
    return refIds;
  }
  
  public void setRoyaltyParameters(String royaltyParameters)
  {
    this.royaltyParameters = royaltyParameters;
  }
  
  public String getRoyaltyParameters()
  {
    return royaltyParameters;
  }
  
  public void setRoyaltyType(String royaltyType)
  {
    this.royaltyType = royaltyType;
  }
  
  public String getRoyaltyType()
  {
    return royaltyType;
  }
  
  public void setSellerEmail(String sellerEmail)
  {
    this.sellerEmail = sellerEmail;
  }
  
  public String getSellerEmail()
  {
    return sellerEmail;
  }
  
  public void setSellerId(String sellerId)
  {
    this.sellerId = sellerId;
  }
  
  public String getSellerId()
  {
    return sellerId;
  }
  
  public void setShowUrl(String showUrl)
  {
    this.showUrl = showUrl;
  }
  
  public String getShowUrl()
  {
    return showUrl;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getSubject()
  {
    return subject;
  }
  
  public void setTotalFee(String totalFee)
  {
    this.totalFee = totalFee;
  }
  
  public String getTotalFee()
  {
    return totalFee;
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
    return "alipay.acquire.createandpay";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("alipay_ca_request", alipayCaRequest);
    txtParams.put("body", body);
    txtParams.put("buyer_email", buyerEmail);
    txtParams.put("buyer_id", buyerId);
    txtParams.put("channel_parameters", channelParameters);
    txtParams.put("currency", currency);
    txtParams.put("dynamic_id", dynamicId);
    txtParams.put("dynamic_id_type", dynamicIdType);
    txtParams.put("extend_params", extendParams);
    txtParams.put("format_type", formatType);
    txtParams.put("goods_detail", goodsDetail);
    txtParams.put("it_b_pay", itBPay);
    txtParams.put("mcard_parameters", mcardParameters);
    txtParams.put("operator_id", operatorId);
    txtParams.put("operator_type", operatorType);
    txtParams.put("out_trade_no", outTradeNo);
    txtParams.put("price", price);
    txtParams.put("quantity", quantity);
    txtParams.put("ref_ids", refIds);
    txtParams.put("royalty_parameters", royaltyParameters);
    txtParams.put("royalty_type", royaltyType);
    txtParams.put("seller_email", sellerEmail);
    txtParams.put("seller_id", sellerId);
    txtParams.put("show_url", showUrl);
    txtParams.put("subject", subject);
    txtParams.put("total_fee", totalFee);
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
  
  public Class<AlipayAcquireCreateandpayResponse> getResponseClass()
  {
    return AlipayAcquireCreateandpayResponse.class;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayMicropayOrderFreezeResponse;
import java.util.Date;
import java.util.Map;

public class AlipayMicropayOrderFreezeRequest
  implements AlipayRequest<AlipayMicropayOrderFreezeResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String amount;
  private Date expireTime;
  private String memo;
  private String merchantOrderNo;
  private String payConfirm;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
  
  public String getAmount()
  {
    return amount;
  }
  
  public void setExpireTime(Date expireTime)
  {
    this.expireTime = expireTime;
  }
  
  public Date getExpireTime()
  {
    return expireTime;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo)
  {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo()
  {
    return merchantOrderNo;
  }
  
  public void setPayConfirm(String payConfirm)
  {
    this.payConfirm = payConfirm;
  }
  
  public String getPayConfirm()
  {
    return payConfirm;
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
    return "alipay.micropay.order.freeze";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("amount", amount);
    txtParams.put("expire_time", expireTime);
    txtParams.put("memo", memo);
    txtParams.put("merchant_order_no", merchantOrderNo);
    txtParams.put("pay_confirm", payConfirm);
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
  
  public Class<AlipayMicropayOrderFreezeResponse> getResponseClass()
  {
    return AlipayMicropayOrderFreezeResponse.class;
  }
}

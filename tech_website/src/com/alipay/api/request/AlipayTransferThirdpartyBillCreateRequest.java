package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayTransferThirdpartyBillCreateResponse;
import java.util.Map;

public class AlipayTransferThirdpartyBillCreateRequest
  implements AlipayRequest<AlipayTransferThirdpartyBillCreateResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String amount;
  private String currency;
  private String extParam;
  private String memo;
  private String partnerId;
  private String payeeAccount;
  private String payeeType;
  private String payerAccount;
  private String payerType;
  private String paymentId;
  private String paymentSource;
  private String title;
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
  
  public void setCurrency(String currency)
  {
    this.currency = currency;
  }
  
  public String getCurrency()
  {
    return currency;
  }
  
  public void setExtParam(String extParam)
  {
    this.extParam = extParam;
  }
  
  public String getExtParam()
  {
    return extParam;
  }
  
  public void setMemo(String memo)
  {
    this.memo = memo;
  }
  
  public String getMemo()
  {
    return memo;
  }
  
  public void setPartnerId(String partnerId)
  {
    this.partnerId = partnerId;
  }
  
  public String getPartnerId()
  {
    return partnerId;
  }
  
  public void setPayeeAccount(String payeeAccount)
  {
    this.payeeAccount = payeeAccount;
  }
  
  public String getPayeeAccount()
  {
    return payeeAccount;
  }
  
  public void setPayeeType(String payeeType)
  {
    this.payeeType = payeeType;
  }
  
  public String getPayeeType()
  {
    return payeeType;
  }
  
  public void setPayerAccount(String payerAccount)
  {
    this.payerAccount = payerAccount;
  }
  
  public String getPayerAccount()
  {
    return payerAccount;
  }
  
  public void setPayerType(String payerType)
  {
    this.payerType = payerType;
  }
  
  public String getPayerType()
  {
    return payerType;
  }
  
  public void setPaymentId(String paymentId)
  {
    this.paymentId = paymentId;
  }
  
  public String getPaymentId()
  {
    return paymentId;
  }
  
  public void setPaymentSource(String paymentSource)
  {
    this.paymentSource = paymentSource;
  }
  
  public String getPaymentSource()
  {
    return paymentSource;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getTitle()
  {
    return title;
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
    return "alipay.transfer.thirdparty.bill.create";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("amount", amount);
    txtParams.put("currency", currency);
    txtParams.put("ext_param", extParam);
    txtParams.put("memo", memo);
    txtParams.put("partner_id", partnerId);
    txtParams.put("payee_account", payeeAccount);
    txtParams.put("payee_type", payeeType);
    txtParams.put("payer_account", payerAccount);
    txtParams.put("payer_type", payerType);
    txtParams.put("payment_id", paymentId);
    txtParams.put("payment_source", paymentSource);
    txtParams.put("title", title);
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
  
  public Class<AlipayTransferThirdpartyBillCreateResponse> getResponseClass()
  {
    return AlipayTransferThirdpartyBillCreateResponse.class;
  }
}

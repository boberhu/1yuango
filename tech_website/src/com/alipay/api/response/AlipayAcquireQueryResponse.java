package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAcquireQueryResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3124438155257991639L;
  @ApiField("buyer_logon_id")
  private String buyerLogonId;
  @ApiField("buyer_user_id")
  private String buyerUserId;
  @ApiField("detail_error_code")
  private String detailErrorCode;
  @ApiField("detail_error_des")
  private String detailErrorDes;
  @ApiField("out_trade_no")
  private String outTradeNo;
  @ApiField("partner")
  private String partner;
  @ApiField("result_code")
  private String resultCode;
  @ApiField("trade_no")
  private String tradeNo;
  
  public void setBuyerLogonId(String buyerLogonId)
  {
    this.buyerLogonId = buyerLogonId;
  }
  
  public String getBuyerLogonId()
  {
    return buyerLogonId;
  }
  
  public void setBuyerUserId(String buyerUserId)
  {
    this.buyerUserId = buyerUserId;
  }
  
  public String getBuyerUserId()
  {
    return buyerUserId;
  }
  
  public void setDetailErrorCode(String detailErrorCode)
  {
    this.detailErrorCode = detailErrorCode;
  }
  
  public String getDetailErrorCode()
  {
    return detailErrorCode;
  }
  
  public void setDetailErrorDes(String detailErrorDes)
  {
    this.detailErrorDes = detailErrorDes;
  }
  
  public String getDetailErrorDes()
  {
    return detailErrorDes;
  }
  
  public void setOutTradeNo(String outTradeNo)
  {
    this.outTradeNo = outTradeNo;
  }
  
  public String getOutTradeNo()
  {
    return outTradeNo;
  }
  
  public void setPartner(String partner)
  {
    this.partner = partner;
  }
  
  public String getPartner()
  {
    return partner;
  }
  
  public void setResultCode(String resultCode)
  {
    this.resultCode = resultCode;
  }
  
  public String getResultCode()
  {
    return resultCode;
  }
  
  public void setTradeNo(String tradeNo)
  {
    this.tradeNo = tradeNo;
  }
  
  public String getTradeNo()
  {
    return tradeNo;
  }
}

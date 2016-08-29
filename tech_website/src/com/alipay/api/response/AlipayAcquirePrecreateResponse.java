package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAcquirePrecreateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8467877576336887763L;
  @ApiField("detail_error_code")
  private String detailErrorCode;
  @ApiField("detail_error_des")
  private String detailErrorDes;
  @ApiField("error")
  private String error;
  @ApiField("is_success")
  private String isSuccess;
  @ApiField("out_trade_no")
  private String outTradeNo;
  @ApiField("pic_url")
  private String picUrl;
  @ApiField("qr_code")
  private String qrCode;
  @ApiField("result_code")
  private String resultCode;
  @ApiField("trade_no")
  private String tradeNo;
  @ApiField("voucher_type")
  private String voucherType;
  
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
  
  public void setError(String error)
  {
    this.error = error;
  }
  
  public String getError()
  {
    return error;
  }
  
  public void setIsSuccess(String isSuccess)
  {
    this.isSuccess = isSuccess;
  }
  
  public String getIsSuccess()
  {
    return isSuccess;
  }
  
  public void setOutTradeNo(String outTradeNo)
  {
    this.outTradeNo = outTradeNo;
  }
  
  public String getOutTradeNo()
  {
    return outTradeNo;
  }
  
  public void setPicUrl(String picUrl)
  {
    this.picUrl = picUrl;
  }
  
  public String getPicUrl()
  {
    return picUrl;
  }
  
  public void setQrCode(String qrCode)
  {
    this.qrCode = qrCode;
  }
  
  public String getQrCode()
  {
    return qrCode;
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
  
  public void setVoucherType(String voucherType)
  {
    this.voucherType = voucherType;
  }
  
  public String getVoucherType()
  {
    return voucherType;
  }
}

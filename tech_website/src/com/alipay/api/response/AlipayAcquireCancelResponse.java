package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAcquireCancelResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 6513776554698358721L;
  @ApiField("detail_error_code")
  private String detailErrorCode;
  @ApiField("detail_error_des")
  private String detailErrorDes;
  @ApiField("out_trade_no")
  private String outTradeNo;
  @ApiField("result_code")
  private String resultCode;
  @ApiField("retry_flag")
  private String retryFlag;
  @ApiField("trade_no")
  private String tradeNo;
  
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
  
  public void setResultCode(String resultCode)
  {
    this.resultCode = resultCode;
  }
  
  public String getResultCode()
  {
    return resultCode;
  }
  
  public void setRetryFlag(String retryFlag)
  {
    this.retryFlag = retryFlag;
  }
  
  public String getRetryFlag()
  {
    return retryFlag;
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

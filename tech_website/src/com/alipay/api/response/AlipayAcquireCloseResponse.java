package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayAcquireCloseResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2121313527595117597L;
  @ApiField("detail_error_code")
  private String detailErrorCode;
  @ApiField("detail_error_des")
  private String detailErrorDes;
  @ApiField("is_success")
  private String isSuccess;
  @ApiField("out_trade_no")
  private String outTradeNo;
  @ApiField("result_code")
  private String resultCode;
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

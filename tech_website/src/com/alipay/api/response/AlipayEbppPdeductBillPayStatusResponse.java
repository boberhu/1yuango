package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEbppPdeductBillPayStatusResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3595863265993185685L;
  @ApiField("agreement_id")
  private String agreementId;
  @ApiField("out_order_no")
  private String outOrderNo;
  @ApiField("status")
  private String status;
  
  public void setAgreementId(String agreementId)
  {
    this.agreementId = agreementId;
  }
  
  public String getAgreementId()
  {
    return agreementId;
  }
  
  public void setOutOrderNo(String outOrderNo)
  {
    this.outOrderNo = outOrderNo;
  }
  
  public String getOutOrderNo()
  {
    return outOrderNo;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getStatus()
  {
    return status;
  }
}

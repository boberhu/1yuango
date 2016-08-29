package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayUserMemberCardUpdateResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 2622152866166991316L;
  @ApiField("card")
  private String card;
  @ApiField("result_code")
  private String resultCode;
  @ApiField("result_msg")
  private String resultMsg;
  
  public void setCard(String card)
  {
    this.card = card;
  }
  
  public String getCard()
  {
    return card;
  }
  
  public void setResultCode(String resultCode)
  {
    this.resultCode = resultCode;
  }
  
  public String getResultCode()
  {
    return resultCode;
  }
  
  public void setResultMsg(String resultMsg)
  {
    this.resultMsg = resultMsg;
  }
  
  public String getResultMsg()
  {
    return resultMsg;
  }
}

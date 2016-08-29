package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayEcardEduPublicBindResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3713731687254272995L;
  @ApiField("agent_code")
  private String agentCode;
  @ApiField("card_no")
  private String cardNo;
  @ApiField("return_code")
  private String returnCode;
  
  public void setAgentCode(String agentCode)
  {
    this.agentCode = agentCode;
  }
  
  public String getAgentCode()
  {
    return agentCode;
  }
  
  public void setCardNo(String cardNo)
  {
    this.cardNo = cardNo;
  }
  
  public String getCardNo()
  {
    return cardNo;
  }
  
  public void setReturnCode(String returnCode)
  {
    this.returnCode = returnCode;
  }
  
  public String getReturnCode()
  {
    return returnCode;
  }
}

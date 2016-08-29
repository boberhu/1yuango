package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipayMobilePublicMessageLabelSendResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8685718249311754921L;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  @ApiField("msg_id")
  private String msgId;
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
  
  public void setMsgId(String msgId)
  {
    this.msgId = msgId;
  }
  
  public String getMsgId()
  {
    return msgId;
  }
}

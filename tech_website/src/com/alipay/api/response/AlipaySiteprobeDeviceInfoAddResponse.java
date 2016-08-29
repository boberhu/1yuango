package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeDeviceInfoAddResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1115961159376727361L;
  @ApiField("code")
  private Long code;
  @ApiField("device_id")
  private String deviceId;
  @ApiField("msg")
  private String msg;
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
  {
    return code;
  }
  
  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }
  
  public String getDeviceId()
  {
    return deviceId;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
}

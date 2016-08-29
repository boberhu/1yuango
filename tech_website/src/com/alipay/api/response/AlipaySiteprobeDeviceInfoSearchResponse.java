package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;

public class AlipaySiteprobeDeviceInfoSearchResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 7785325459869667229L;
  @ApiField("code")
  private String code;
  @ApiField("devices")
  private String devices;
  @ApiField("msg")
  private String msg;
  @ApiField("total")
  private Long total;
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getCode()
  {
    return code;
  }
  
  public void setDevices(String devices)
  {
    this.devices = devices;
  }
  
  public String getDevices()
  {
    return devices;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getMsg()
  {
    return msg;
  }
  
  public void setTotal(Long total)
  {
    this.total = total;
  }
  
  public Long getTotal()
  {
    return total;
  }
}

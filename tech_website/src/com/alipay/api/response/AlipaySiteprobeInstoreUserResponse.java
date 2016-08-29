package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.InstoreUser;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipaySiteprobeInstoreUserResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 5525761628213473698L;
  @ApiField("code")
  private Long code;
  @ApiField("msg")
  private String msg;
  @ApiListField("users")
  @ApiField("instore_user")
  private List<InstoreUser> users;
  
  public void setCode(Long code)
  {
    this.code = code;
  }
  
  public Long getCode()
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
  
  public void setUsers(List<InstoreUser> users)
  {
    this.users = users;
  }
  
  public List<InstoreUser> getUsers()
  {
    return users;
  }
}

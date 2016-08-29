package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayStockPortfolioAllGetResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 3462754588785463882L;
  @ApiField("code")
  private String code;
  @ApiField("msg")
  private String msg;
  @ApiListField("symbol_list")
  @ApiField("string")
  private List<String> symbolList;
  
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
  
  public void setSymbolList(List<String> symbolList)
  {
    this.symbolList = symbolList;
  }
  
  public List<String> getSymbolList()
  {
    return symbolList;
  }
}

package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPassTplAddResponse;
import java.util.Map;

public class AlipayPassTplAddRequest
  implements AlipayRequest<AlipayPassTplAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String tplContent;
  private String uniqueId;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setTplContent(String tplContent)
  {
    this.tplContent = tplContent;
  }
  
  public String getTplContent()
  {
    return tplContent;
  }
  
  public void setUniqueId(String uniqueId)
  {
    this.uniqueId = uniqueId;
  }
  
  public String getUniqueId()
  {
    return uniqueId;
  }
  
  public String getApiVersion()
  {
    return apiVersion;
  }
  
  public void setApiVersion(String apiVersion)
  {
    this.apiVersion = apiVersion;
  }
  
  public void setTerminalType(String terminalType)
  {
    this.terminalType = terminalType;
  }
  
  public String getTerminalType()
  {
    return terminalType;
  }
  
  public void setTerminalInfo(String terminalInfo)
  {
    this.terminalInfo = terminalInfo;
  }
  
  public String getTerminalInfo()
  {
    return terminalInfo;
  }
  
  public void setProdCode(String prodCode)
  {
    this.prodCode = prodCode;
  }
  
  public String getProdCode()
  {
    return prodCode;
  }
  
  public String getApiMethodName()
  {
    return "alipay.pass.tpl.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("tpl_content", tplContent);
    txtParams.put("unique_id", uniqueId);
    if (udfParams != null) {
      txtParams.putAll(udfParams);
    }
    return txtParams;
  }
  
  public void putOtherTextParam(String key, String value)
  {
    if (udfParams == null) {
      udfParams = new AlipayHashMap();
    }
    udfParams.put(key, value);
  }
  
  public Class<AlipayPassTplAddResponse> getResponseClass()
  {
    return AlipayPassTplAddResponse.class;
  }
}

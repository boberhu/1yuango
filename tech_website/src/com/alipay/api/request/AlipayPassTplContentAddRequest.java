package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayPassTplContentAddResponse;
import java.util.Map;

public class AlipayPassTplContentAddRequest
  implements AlipayRequest<AlipayPassTplContentAddResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String recognitionInfo;
  private String recognitionType;
  private String tplId;
  private String tplParams;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setRecognitionInfo(String recognitionInfo)
  {
    this.recognitionInfo = recognitionInfo;
  }
  
  public String getRecognitionInfo()
  {
    return recognitionInfo;
  }
  
  public void setRecognitionType(String recognitionType)
  {
    this.recognitionType = recognitionType;
  }
  
  public String getRecognitionType()
  {
    return recognitionType;
  }
  
  public void setTplId(String tplId)
  {
    this.tplId = tplId;
  }
  
  public String getTplId()
  {
    return tplId;
  }
  
  public void setTplParams(String tplParams)
  {
    this.tplParams = tplParams;
  }
  
  public String getTplParams()
  {
    return tplParams;
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
    return "alipay.pass.tpl.content.add";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("recognition_info", recognitionInfo);
    txtParams.put("recognition_type", recognitionType);
    txtParams.put("tpl_id", tplId);
    txtParams.put("tpl_params", tplParams);
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
  
  public Class<AlipayPassTplContentAddResponse> getResponseClass()
  {
    return AlipayPassTplContentAddResponse.class;
  }
}

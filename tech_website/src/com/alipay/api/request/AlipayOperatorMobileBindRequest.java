package com.alipay.api.request;

import com.alipay.api.AlipayRequest;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.response.AlipayOperatorMobileBindResponse;
import java.util.Map;

public class AlipayOperatorMobileBindRequest
  implements AlipayRequest<AlipayOperatorMobileBindResponse>
{
  private AlipayHashMap udfParams;
  private String apiVersion = "1.0";
  private String checkSigncard;
  private String fReturnUrl;
  private String hasSpi;
  private String operatorName;
  private String provinceName;
  private String sReturnUrl;
  private String terminalType;
  private String terminalInfo;
  private String prodCode;
  
  public void setCheckSigncard(String checkSigncard)
  {
    this.checkSigncard = checkSigncard;
  }
  
  public String getCheckSigncard()
  {
    return checkSigncard;
  }
  
  public void setfReturnUrl(String fReturnUrl)
  {
    this.fReturnUrl = fReturnUrl;
  }
  
  public String getfReturnUrl()
  {
    return fReturnUrl;
  }
  
  public void setHasSpi(String hasSpi)
  {
    this.hasSpi = hasSpi;
  }
  
  public String getHasSpi()
  {
    return hasSpi;
  }
  
  public void setOperatorName(String operatorName)
  {
    this.operatorName = operatorName;
  }
  
  public String getOperatorName()
  {
    return operatorName;
  }
  
  public void setProvinceName(String provinceName)
  {
    this.provinceName = provinceName;
  }
  
  public String getProvinceName()
  {
    return provinceName;
  }
  
  public void setsReturnUrl(String sReturnUrl)
  {
    this.sReturnUrl = sReturnUrl;
  }
  
  public String getsReturnUrl()
  {
    return sReturnUrl;
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
    return "alipay.operator.mobile.bind";
  }
  
  public Map<String, String> getTextParams()
  {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("check_signcard", checkSigncard);
    txtParams.put("f_return_url", fReturnUrl);
    txtParams.put("has_spi", hasSpi);
    txtParams.put("operator_name", operatorName);
    txtParams.put("province_name", provinceName);
    txtParams.put("s_return_url", sReturnUrl);
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
  
  public Class<AlipayOperatorMobileBindResponse> getResponseClass()
  {
    return AlipayOperatorMobileBindResponse.class;
  }
}

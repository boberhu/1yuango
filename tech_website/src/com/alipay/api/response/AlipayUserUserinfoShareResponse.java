package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.DeliverAddress;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayUserUserinfoShareResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 1648169547373481119L;
  @ApiField("address")
  private String address;
  @ApiField("address_code")
  private String addressCode;
  @ApiField("area")
  private String area;
  @ApiField("avatar")
  private String avatar;
  @ApiField("cert_no")
  private String certNo;
  @ApiField("cert_type_value")
  private String certTypeValue;
  @ApiField("city")
  private String city;
  @ApiField("default_deliver_address")
  private String defaultDeliverAddress;
  @ApiListField("deliver_address_list")
  @ApiField("deliver_address")
  private List<DeliverAddress> deliverAddressList;
  @ApiField("deliver_area")
  private String deliverArea;
  @ApiField("deliver_city")
  private String deliverCity;
  @ApiField("deliver_fullname")
  private String deliverFullname;
  @ApiField("deliver_mobile")
  private String deliverMobile;
  @ApiField("deliver_phone")
  private String deliverPhone;
  @ApiField("deliver_province")
  private String deliverProvince;
  @ApiField("email")
  private String email;
  @ApiField("firm_name")
  private String firmName;
  @ApiField("gender")
  private String gender;
  @ApiField("is_bank_auth")
  private String isBankAuth;
  @ApiField("is_certified")
  private String isCertified;
  @ApiField("is_id_auth")
  private String isIdAuth;
  @ApiField("is_licence_auth")
  private String isLicenceAuth;
  @ApiField("is_mobile_auth")
  private String isMobileAuth;
  @ApiField("is_student_certified")
  private String isStudentCertified;
  @ApiField("mobile")
  private String mobile;
  @ApiField("phone")
  private String phone;
  @ApiField("province")
  private String province;
  @ApiField("real_name")
  private String realName;
  @ApiField("user_id")
  private String userId;
  @ApiField("user_status")
  private String userStatus;
  @ApiField("user_type_value")
  private String userTypeValue;
  @ApiField("zip")
  private String zip;
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getAddress()
  {
    return address;
  }
  
  public void setAddressCode(String addressCode)
  {
    this.addressCode = addressCode;
  }
  
  public String getAddressCode()
  {
    return addressCode;
  }
  
  public void setArea(String area)
  {
    this.area = area;
  }
  
  public String getArea()
  {
    return area;
  }
  
  public void setAvatar(String avatar)
  {
    this.avatar = avatar;
  }
  
  public String getAvatar()
  {
    return avatar;
  }
  
  public void setCertNo(String certNo)
  {
    this.certNo = certNo;
  }
  
  public String getCertNo()
  {
    return certNo;
  }
  
  public void setCertTypeValue(String certTypeValue)
  {
    this.certTypeValue = certTypeValue;
  }
  
  public String getCertTypeValue()
  {
    return certTypeValue;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCity()
  {
    return city;
  }
  
  public void setDefaultDeliverAddress(String defaultDeliverAddress)
  {
    this.defaultDeliverAddress = defaultDeliverAddress;
  }
  
  public String getDefaultDeliverAddress()
  {
    return defaultDeliverAddress;
  }
  
  public void setDeliverAddressList(List<DeliverAddress> deliverAddressList)
  {
    this.deliverAddressList = deliverAddressList;
  }
  
  public List<DeliverAddress> getDeliverAddressList()
  {
    return deliverAddressList;
  }
  
  public void setDeliverArea(String deliverArea)
  {
    this.deliverArea = deliverArea;
  }
  
  public String getDeliverArea()
  {
    return deliverArea;
  }
  
  public void setDeliverCity(String deliverCity)
  {
    this.deliverCity = deliverCity;
  }
  
  public String getDeliverCity()
  {
    return deliverCity;
  }
  
  public void setDeliverFullname(String deliverFullname)
  {
    this.deliverFullname = deliverFullname;
  }
  
  public String getDeliverFullname()
  {
    return deliverFullname;
  }
  
  public void setDeliverMobile(String deliverMobile)
  {
    this.deliverMobile = deliverMobile;
  }
  
  public String getDeliverMobile()
  {
    return deliverMobile;
  }
  
  public void setDeliverPhone(String deliverPhone)
  {
    this.deliverPhone = deliverPhone;
  }
  
  public String getDeliverPhone()
  {
    return deliverPhone;
  }
  
  public void setDeliverProvince(String deliverProvince)
  {
    this.deliverProvince = deliverProvince;
  }
  
  public String getDeliverProvince()
  {
    return deliverProvince;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getEmail()
  {
    return email;
  }
  
  public void setFirmName(String firmName)
  {
    this.firmName = firmName;
  }
  
  public String getFirmName()
  {
    return firmName;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getGender()
  {
    return gender;
  }
  
  public void setIsBankAuth(String isBankAuth)
  {
    this.isBankAuth = isBankAuth;
  }
  
  public String getIsBankAuth()
  {
    return isBankAuth;
  }
  
  public void setIsCertified(String isCertified)
  {
    this.isCertified = isCertified;
  }
  
  public String getIsCertified()
  {
    return isCertified;
  }
  
  public void setIsIdAuth(String isIdAuth)
  {
    this.isIdAuth = isIdAuth;
  }
  
  public String getIsIdAuth()
  {
    return isIdAuth;
  }
  
  public void setIsLicenceAuth(String isLicenceAuth)
  {
    this.isLicenceAuth = isLicenceAuth;
  }
  
  public String getIsLicenceAuth()
  {
    return isLicenceAuth;
  }
  
  public void setIsMobileAuth(String isMobileAuth)
  {
    this.isMobileAuth = isMobileAuth;
  }
  
  public String getIsMobileAuth()
  {
    return isMobileAuth;
  }
  
  public void setIsStudentCertified(String isStudentCertified)
  {
    this.isStudentCertified = isStudentCertified;
  }
  
  public String getIsStudentCertified()
  {
    return isStudentCertified;
  }
  
  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }
  
  public String getMobile()
  {
    return mobile;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getPhone()
  {
    return phone;
  }
  
  public void setProvince(String province)
  {
    this.province = province;
  }
  
  public String getProvince()
  {
    return province;
  }
  
  public void setRealName(String realName)
  {
    this.realName = realName;
  }
  
  public String getRealName()
  {
    return realName;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void setUserStatus(String userStatus)
  {
    this.userStatus = userStatus;
  }
  
  public String getUserStatus()
  {
    return userStatus;
  }
  
  public void setUserTypeValue(String userTypeValue)
  {
    this.userTypeValue = userTypeValue;
  }
  
  public String getUserTypeValue()
  {
    return userTypeValue;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public String getZip()
  {
    return zip;
  }
}

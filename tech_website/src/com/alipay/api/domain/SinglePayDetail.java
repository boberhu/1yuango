package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import java.util.Date;

public class SinglePayDetail
  extends AlipayObject
{
  private static final long serialVersionUID = 5225873747792866265L;
  @ApiField("alipay_order_no")
  private String alipayOrderNo;
  @ApiField("amount")
  private String amount;
  @ApiField("create_time")
  private Date createTime;
  @ApiField("modified_time")
  private Date modifiedTime;
  @ApiField("pay_url")
  private String payUrl;
  @ApiField("receive_user_id")
  private String receiveUserId;
  @ApiField("transfer_order_no")
  private String transferOrderNo;
  @ApiField("transfer_out_order_no")
  private String transferOutOrderNo;
  
  public String getAlipayOrderNo()
  {
    return alipayOrderNo;
  }
  
  public void setAlipayOrderNo(String alipayOrderNo)
  {
    this.alipayOrderNo = alipayOrderNo;
  }
  
  public String getAmount()
  {
    return amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
  
  public Date getCreateTime()
  {
    return createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public Date getModifiedTime()
  {
    return modifiedTime;
  }
  
  public void setModifiedTime(Date modifiedTime)
  {
    this.modifiedTime = modifiedTime;
  }
  
  public String getPayUrl()
  {
    return payUrl;
  }
  
  public void setPayUrl(String payUrl)
  {
    this.payUrl = payUrl;
  }
  
  public String getReceiveUserId()
  {
    return receiveUserId;
  }
  
  public void setReceiveUserId(String receiveUserId)
  {
    this.receiveUserId = receiveUserId;
  }
  
  public String getTransferOrderNo()
  {
    return transferOrderNo;
  }
  
  public void setTransferOrderNo(String transferOrderNo)
  {
    this.transferOrderNo = transferOrderNo;
  }
  
  public String getTransferOutOrderNo()
  {
    return transferOutOrderNo;
  }
  
  public void setTransferOutOrderNo(String transferOutOrderNo)
  {
    this.transferOutOrderNo = transferOutOrderNo;
  }
}

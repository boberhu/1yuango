package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AccountRecord;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayUserAccountSearchResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8454691245347782457L;
  @ApiListField("account_records")
  @ApiField("account_record")
  private List<AccountRecord> accountRecords;
  @ApiField("total_pages")
  private Long totalPages;
  @ApiField("total_results")
  private Long totalResults;
  
  public void setAccountRecords(List<AccountRecord> accountRecords)
  {
    this.accountRecords = accountRecords;
  }
  
  public List<AccountRecord> getAccountRecords()
  {
    return accountRecords;
  }
  
  public void setTotalPages(Long totalPages)
  {
    this.totalPages = totalPages;
  }
  
  public Long getTotalPages()
  {
    return totalPages;
  }
  
  public void setTotalResults(Long totalResults)
  {
    this.totalResults = totalResults;
  }
  
  public Long getTotalResults()
  {
    return totalResults;
  }
}

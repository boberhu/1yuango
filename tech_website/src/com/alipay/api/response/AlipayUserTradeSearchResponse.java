package com.alipay.api.response;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeRecord;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import java.util.List;

public class AlipayUserTradeSearchResponse
  extends AlipayResponse
{
  private static final long serialVersionUID = 8319713737274455399L;
  @ApiField("total_pages")
  private String totalPages;
  @ApiField("total_results")
  private String totalResults;
  @ApiListField("trade_records")
  @ApiField("trade_record")
  private List<TradeRecord> tradeRecords;
  
  public void setTotalPages(String totalPages)
  {
    this.totalPages = totalPages;
  }
  
  public String getTotalPages()
  {
    return totalPages;
  }
  
  public void setTotalResults(String totalResults)
  {
    this.totalResults = totalResults;
  }
  
  public String getTotalResults()
  {
    return totalResults;
  }
  
  public void setTradeRecords(List<TradeRecord> tradeRecords)
  {
    this.tradeRecords = tradeRecords;
  }
  
  public List<TradeRecord> getTradeRecords()
  {
    return tradeRecords;
  }
}

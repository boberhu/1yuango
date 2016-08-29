package com.alipay.api;

import java.util.Map;

public abstract interface AlipayUploadRequest<T extends AlipayResponse>
  extends AlipayRequest<T>
{
  public abstract Map<String, FileItem> getFileParams();
}

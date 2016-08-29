package com.alipay.api.internal.util.codec;

public abstract interface Encoder
{
  public abstract Object encode(Object paramObject)
    throws EncoderException;
}

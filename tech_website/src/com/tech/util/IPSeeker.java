package com.tech.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class IPSeeker
{
  private class IPLocation
  {
    public String country = area = "";
    public String area;
    
    public IPLocation() {}
    
    public IPLocation getCopy()
    {
      IPLocation ret = new IPLocation();
      ret.country = country;
      ret.area = area;
      return ret;
    }
  }
  
  private static final String IP_FILE = IPSeeker.class.getResource("/qqwry.dat").toString().substring(5);
  private static final int IP_RECORD_LENGTH = 7;
  private static final byte AREA_FOLLOWED = 1;
  private static final byte NO_AREA = 2;
  private Hashtable ipCache;
  private RandomAccessFile ipFile;
  private MappedByteBuffer mbb;
  private static IPSeeker instance = new IPSeeker();
  private long ipBegin;
  private long ipEnd;
  private IPLocation loc;
  private byte[] buf;
  private byte[] b4;
  private byte[] b3;
  
  public IPSeeker()
  {
    ipCache = new Hashtable();
    loc = new IPLocation();
    buf = new byte[100];
    b4 = new byte[4];
    b3 = new byte[3];
    try
    {
      ipFile = new RandomAccessFile(IP_FILE, "r");
    }
    catch (FileNotFoundException e)
    {
      System.out.println(IPSeeker.class.getResource("/qqwry.dat").toString());
      System.out.println(IP_FILE);
      System.out.println("IP地址信息文件没有找到，IP显示功能将无法使用");
      ipFile = null;
    }
    if (ipFile != null) {
      try
      {
        ipBegin = readLong4(0L);
        ipEnd = readLong4(4L);
        if ((ipBegin == -1L) || (ipEnd == -1L))
        {
          ipFile.close();
          ipFile = null;
        }
      }
      catch (IOException e)
      {
        System.out.println("IP地址信息文件格式有错误，IP显示功能将无法使用");
        ipFile = null;
      }
    }
  }
  
  public static IPSeeker getInstance()
  {
    return instance;
  }
  
  public List getIPEntriesDebug(String s)
  {
    List ret = new ArrayList();
    long endOffset = ipEnd + 4L;
    for (long offset = ipBegin + 4L; offset <= endOffset; offset += 7L)
    {
      long temp = readLong3(offset);
      if (temp != -1L)
      {
        IPLocation loc = getIPLocation(temp);
        String country = loc.country;
        String area    = loc.area;
        if ((country.indexOf(s) != -1) || (area.indexOf(s) != -1))
        {
          IPEntry entry = new IPEntry();
          entry.area = area;
          entry.country = country;
          
          readIP(offset - 4L, b4);
          entry.beginIp = IPUtils.getIpStringFromBytes(b4);
          
          readIP(temp, b4);
          entry.endIp = IPUtils.getIpStringFromBytes(b4);
          
          ret.add(entry);
        }
      }
    }
    return ret;
  }
  
  public List getIPEntries(String s)
  {
    List ret = new ArrayList();
    try
    {
      if (mbb == null)
      {
        FileChannel fc = ipFile.getChannel();
        mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0L, ipFile.length());
        mbb.order(ByteOrder.LITTLE_ENDIAN);
      }
      int endOffset = (int)ipEnd;
      for (int offset = (int)ipBegin + 4; offset <= endOffset; offset += 7)
      {
        int temp = readInt3(offset);
        if (temp != -1)
        {
          IPLocation loc = getIPLocation(temp);
          final String country = loc.country;
          final String area = loc.area;
          if ((country.indexOf(s) != -1) || 
            (area.indexOf(s) != -1))
          {
            IPEntry entry = new IPEntry();
            entry.country = country;
            entry.area = area;
            
            readIP(offset - 4, b4);
            entry.beginIp = IPUtils.getIpStringFromBytes(b4);
            
            readIP(temp, b4);
            entry.endIp = IPUtils.getIpStringFromBytes(b4);
            
            ret.add(entry);
          }
        }
      }
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
    return ret;
  }
  
  private int readInt3(int offset)
  {
    mbb.position(offset);
    return mbb.getInt() & 0xFFFFFF;
  }
  
  private int readInt3()
  {
    return mbb.getInt() & 0xFFFFFF;
  }
  
  public String getCountry(byte[] ip)
  {
    if (ipFile == null) {
      return "错误的IP数据库文件";
    }
    String ipStr = IPUtils.getIpStringFromBytes(ip);
    if (ipCache.containsKey(ipStr))
    {
      IPLocation loc = (IPLocation)ipCache.get(ipStr);
      return loc.country;
    }
    IPLocation loc = getIPLocation(ip);
    ipCache.put(ipStr, loc.getCopy());
    return loc.country;
  }
  
  public String getCountry(String ip)
  {
    return getCountry(IPUtils.getIpByteArrayFromString(ip));
  }
  
  public String getArea(byte[] ip)
  {
    if (ipFile == null) {
      return "错误的IP数据库文件";
    }
    String ipStr = IPUtils.getIpStringFromBytes(ip);
    if (ipCache.containsKey(ipStr))
    {
      IPLocation loc = (IPLocation)ipCache.get(ipStr);
      return loc.area;
    }
    IPLocation loc = getIPLocation(ip);
    ipCache.put(ipStr, loc.getCopy());
    return loc.area;
  }
  
  public String getArea(String ip)
  {
    return getArea(IPUtils.getIpByteArrayFromString(ip));
  }
  
  private IPLocation getIPLocation(byte[] ip)
  {
    IPLocation info = null;
    long offset = locateIP(ip);
    if (offset != -1L) {
      info = getIPLocation(offset);
    }
    if (info == null)
    {
      info = new IPLocation();
      info.country = "未知国家";
      info.area = "未知地区";
    }
    return info;
  }
  
  private long readLong4(long offset)
  {
    long ret = 0L;
    try
    {
      ipFile.seek(offset);
      ret |= ipFile.readByte() & 0xFF;
      ret |= ipFile.readByte() << 8 & 0xFF00;
      ret |= ipFile.readByte() << 16 & 0xFF0000;
      return ret | ipFile.readByte() << 24 & 0xFF000000;
    }
    catch (IOException e) {}
    return -1L;
  }
  
  private long readLong3(long offset)
  {
    long ret = 0L;
    try
    {
      ipFile.seek(offset);
      ipFile.readFully(b3);
      ret |= b3[0] & 0xFF;
      ret |= b3[1] << 8 & 0xFF00;
      return ret | b3[2] << 16 & 0xFF0000;
    }
    catch (IOException e) {}
    return -1L;
  }
  
  private long readLong3()
  {
    long ret = 0L;
    try
    {
      ipFile.readFully(b3);
      ret |= b3[0] & 0xFF;
      ret |= b3[1] << 8 & 0xFF00;
      return ret | b3[2] << 16 & 0xFF0000;
    }
    catch (IOException e) {}
    return -1L;
  }
  
  private void readIP(long offset, byte[] ip)
  {
    try
    {
      ipFile.seek(offset);
      ipFile.readFully(ip);
      byte temp = ip[0];
      ip[0] = ip[3];
      ip[3] = temp;
      temp = ip[1];
      ip[1] = ip[2];
      ip[2] = temp;
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }
  
  private void readIP(int offset, byte[] ip)
  {
    mbb.position(offset);
    mbb.get(ip);
    byte temp = ip[0];
    ip[0] = ip[3];
    ip[3] = temp;
    temp = ip[1];
    ip[1] = ip[2];
    ip[2] = temp;
  }
  
  private int compareIP(byte[] ip, byte[] beginIp)
  {
    for (int i = 0; i < 4; i++)
    {
      int r = compareByte(ip[i], beginIp[i]);
      if (r != 0) {
        return r;
      }
    }
    return 0;
  }
  
  private int compareByte(byte b1, byte b2)
  {
    if ((b1 & 0xFF) > (b2 & 0xFF)) {
      return 1;
    }
    if ((b1 ^ b2) == 0) {
      return 0;
    }
    return -1;
  }
  
  private long locateIP(byte[] ip)
  {
    long m = 0L;
    

    readIP(ipBegin, b4);
    int r = compareIP(ip, b4);
    if (r == 0) {
      return ipBegin;
    }
    if (r < 0) {
      return -1L;
    }
    long i = ipBegin;
    for (long j = ipEnd; i < j;)
    {
      m = getMiddleOffset(i, j);
      readIP(m, b4);
      r = compareIP(ip, b4);
      if (r > 0) {
        i = m;
      } else if (r < 0)
      {
        if (m == j)
        {
          j -= 7L;
          m = j;
        }
        else
        {
          j = m;
        }
      }
      else {
        return readLong3(m + 4L);
      }
    }
    m = readLong3(m + 4L);
    readIP(m, b4);
    r = compareIP(ip, b4);
    if (r <= 0) {
      return m;
    }
    return -1L;
  }
  
  private long getMiddleOffset(long begin, long end)
  {
    long records = (end - begin) / 7L;
    records >>= 1;
    if (records == 0L) {
      records = 1L;
    }
    return begin + records * 7L;
  }
  
  private IPLocation getIPLocation(long offset)
  {
    try
    {
      ipFile.seek(offset + 4L);
      
      byte b = ipFile.readByte();
      if (b == 1)
      {
        long countryOffset = readLong3();
        
        ipFile.seek(countryOffset);
        
        b = ipFile.readByte();
        if (b == 2)
        {
          loc.country = readString(readLong3());
          ipFile.seek(countryOffset + 4L);
        }
        else
        {
          loc.country = readString(countryOffset);
        }
        loc.area = readArea(ipFile.getFilePointer());
      }
      else if (b == 2)
      {
        loc.country = readString(readLong3());
        loc.area = readArea(offset + 8L);
      }
      else
      {
        loc.country = readString(ipFile.getFilePointer() - 1L);
        loc.area = readArea(ipFile.getFilePointer());
      }
      return loc;
    }
    catch (IOException e) {}
    return null;
  }
  
  private IPLocation getIPLocation(int offset)
  {
    mbb.position(offset + 4);
    
    byte b = mbb.get();
    if (b == 1)
    {
      int countryOffset = readInt3();
      
      mbb.position(countryOffset);
      
      b = mbb.get();
      if (b == 2)
      {
        loc.country = readString(readInt3());
        mbb.position(countryOffset + 4);
      }
      else
      {
        loc.country = readString(countryOffset);
      }
      loc.area = readArea(mbb.position());
    }
    else if (b == 2)
    {
      loc.country = readString(readInt3());
      loc.area = readArea(offset + 8);
    }
    else
    {
      loc.country = readString(mbb.position() - 1);
      loc.area = readArea(mbb.position());
    }
    return loc;
  }
  
  private String readArea(long offset)
    throws IOException
  {
    ipFile.seek(offset);
    byte b = ipFile.readByte();
    if ((b == 1) || (b == 2))
    {
      long areaOffset = readLong3(offset + 1L);
      if (areaOffset == 0L) {
        return "未知地区";
      }
      return readString(areaOffset);
    }
    return readString(offset);
  }
  
  private String readArea(int offset)
  {
    mbb.position(offset);
    byte b = mbb.get();
    if ((b == 1) || (b == 2))
    {
      int areaOffset = readInt3();
      if (areaOffset == 0) {
        return "未知地区";
      }
      return readString(areaOffset);
    }
    return readString(offset);
  }
  
  private String readString(long offset)
  {
    try
    {
      ipFile.seek(offset);
      
      int i = 0;
      for (buf[i] = ipFile.readByte(); buf[i] != 0;) {
        buf[(++i)] = ipFile.readByte();
      }
      if (i != 0) {
        return IPUtils.getString(buf, 0, i, "GBK");
      }
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
    return "";
  }
  
  private String readString(int offset)
  {
    try
    {
      mbb.position(offset);
      
      int i = 0;
      for (buf[i] = mbb.get(); buf[i] != 0; buf[(++i)] = mbb.get()) {}
      if (i != 0) {
        return IPUtils.getString(buf, 0, i, "GBK");
      }
    }
    catch (IllegalArgumentException e)
    {
      System.out.println(e.getMessage());
    }
    return "";
  }
  
  public String getAddress(String ip)
  {
    String country = getCountry(ip).equals(" CZ88.NET") ? "" : getCountry(ip);
    String address = country;
    return address.trim();
  }
  
  public static void main(String[] args)
  {
    IPSeeker seeker = new IPSeeker();
    System.err.println(seeker.getAddress("110.152.104.103"));
  }
}

package com.alipay.api.internal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.alipay.api.AlipayApiException;

public abstract class AtsUtils
{
  private static final Pattern REGEX_FILE_NAME = Pattern.compile("attachment;filename=\"([\\w\\-]+)\"");
  
  public static File ungzip(File gzip, File toDir)
    throws IOException
  {
    toDir.mkdirs();
    File out = new File(toDir, gzip.getName());
    GZIPInputStream gin = null;
    FileOutputStream fout = null;
    try
    {
      FileInputStream fin = new FileInputStream(gzip);
      gin = new GZIPInputStream(fin);
      fout = new FileOutputStream(out);
      copy(gin, fout);
      gin.close();
      fout.close();
    }
    finally
    {
      closeQuietly(gin);
      closeQuietly(fout);
    }
    return out;
  }
  
  public static List<File> unzip(File zip, File toDir)
    throws IOException
  {
    ZipFile zf = null;
    List<File> files = null;
    try
    {
      zf = new ZipFile(zip);
      files = new ArrayList<File>();
      Enumeration<?> entries = zf.entries();
      while (entries.hasMoreElements())
      {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        if (entry.isDirectory())
        {
          new File(toDir, entry.getName()).mkdirs();
        }
        else
        {
          InputStream input = null;
          OutputStream output = null;
          try
          {
            File f = new File(toDir, entry.getName());
            input = zf.getInputStream(entry);
            output = new FileOutputStream(f);
            copy(input, output);
            files.add(f);
          }
          finally
          {
            closeQuietly(output);
            closeQuietly(input);
          }
        }
      }
    }
    finally
    {
      if (zf != null) {
        zf.close();
      }
    }
    return files;
  }
  
  /* Error */
  public static File download(String url, File toDir)
    throws com.alipay.api.AlipayApiException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 37	java/io/File:mkdirs	()Z
    //   4: pop
    //   5: aconst_null
    //   6: astore_2
    //   7: aconst_null
    //   8: astore_3
    //   9: aconst_null
    //   10: astore 4
    //   12: new 154	java/net/URL
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 156	java/net/URL:<init>	(Ljava/lang/String;)V
    //   20: invokestatic 159	com/alipay/api/internal/util/AtsUtils:getConnection	(Ljava/net/URL;)Ljava/net/HttpURLConnection;
    //   23: astore_2
    //   24: aload_2
    //   25: invokevirtual 163	java/net/HttpURLConnection:getContentType	()Ljava/lang/String;
    //   28: astore 5
    //   30: ldc 8
    //   32: aload 5
    //   34: invokevirtual 168	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifeq +43 -> 80
    //   40: aload_2
    //   41: invokestatic 173	com/alipay/api/internal/util/AtsUtils:getFileName	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   44: astore 6
    //   46: new 38	java/io/File
    //   49: dup
    //   50: aload_1
    //   51: aload 6
    //   53: invokespecial 47	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   56: astore 4
    //   58: new 60	java/io/FileOutputStream
    //   61: dup
    //   62: aload 4
    //   64: invokespecial 62	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   67: astore_3
    //   68: aload_2
    //   69: invokevirtual 177	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   72: aload_3
    //   73: invokestatic 63	com/alipay/api/internal/util/AtsUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   76: pop
    //   77: goto +51 -> 128
    //   80: aload_2
    //   81: invokestatic 180	com/alipay/api/internal/util/WebUtils:getResponseAsString	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   84: astore 6
    //   86: new 152	com/alipay/api/AlipayApiException
    //   89: dup
    //   90: aload 6
    //   92: invokespecial 185	com/alipay/api/AlipayApiException:<init>	(Ljava/lang/String;)V
    //   95: athrow
    //   96: astore 5
    //   98: new 152	com/alipay/api/AlipayApiException
    //   101: dup
    //   102: aload 5
    //   104: invokevirtual 186	java/io/IOException:getMessage	()Ljava/lang/String;
    //   107: invokespecial 185	com/alipay/api/AlipayApiException:<init>	(Ljava/lang/String;)V
    //   110: athrow
    //   111: astore 7
    //   113: aload_3
    //   114: invokestatic 74	com/alipay/api/internal/util/AtsUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   117: aload_2
    //   118: ifnull +7 -> 125
    //   121: aload_2
    //   122: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   125: aload 7
    //   127: athrow
    //   128: aload_3
    //   129: invokestatic 74	com/alipay/api/internal/util/AtsUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   132: aload_2
    //   133: ifnull +7 -> 140
    //   136: aload_2
    //   137: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   140: aload 4
    //   142: areturn
    // Line number table:
    //   Java source line #115	-> byte code offset #0
    //   Java source line #116	-> byte code offset #5
    //   Java source line #117	-> byte code offset #7
    //   Java source line #118	-> byte code offset #9
    //   Java source line #120	-> byte code offset #12
    //   Java source line #121	-> byte code offset #24
    //   Java source line #122	-> byte code offset #30
    //   Java source line #123	-> byte code offset #40
    //   Java source line #124	-> byte code offset #46
    //   Java source line #125	-> byte code offset #58
    //   Java source line #126	-> byte code offset #68
    //   Java source line #128	-> byte code offset #80
    //   Java source line #129	-> byte code offset #86
    //   Java source line #131	-> byte code offset #96
    //   Java source line #132	-> byte code offset #98
    //   Java source line #133	-> byte code offset #111
    //   Java source line #134	-> byte code offset #113
    //   Java source line #135	-> byte code offset #117
    //   Java source line #136	-> byte code offset #121
    //   Java source line #138	-> byte code offset #125
    //   Java source line #134	-> byte code offset #128
    //   Java source line #135	-> byte code offset #132
    //   Java source line #136	-> byte code offset #136
    //   Java source line #139	-> byte code offset #140
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	143	0	url	String
    //   0	143	1	toDir	File
    //   6	131	2	conn	HttpURLConnection
    //   8	121	3	output	OutputStream
    //   10	131	4	file	File
    //   28	5	5	ctype	String
    //   96	7	5	e	IOException
    //   44	8	6	fileName	String
    //   84	7	6	rsp	String
    //   111	15	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	96	96	java/io/IOException
    //   12	111	111	finally
	  throw new AlipayApiException("Unsupported");
  }
  
  public static boolean checkMd5sum(File file, String checkCode)
    throws IOException
  {
    DigestInputStream dInput = null;
    try
    {
      FileInputStream fInput = new FileInputStream(file);
      dInput = new DigestInputStream(fInput, getMd5Instance());
      byte[] buf = new byte[8192];
      while (dInput.read(buf) > 0) {}
      byte[] bytes = dInput.getMessageDigest().digest();
      return bytes2hex(bytes).equals(checkCode);
    }
    finally
    {
      closeQuietly(dInput);
    }
  }
  
  private static String bytes2hex(byte[] bytes)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++)
    {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        sb.append("0").append(hex);
      } else {
        sb.append(hex);
      }
    }
    return sb.toString();
  }
  
  private static MessageDigest getMd5Instance()
  {
    try
    {
      return MessageDigest.getInstance("md5");
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static String getFileName(HttpURLConnection conn)
  {
    String fileName = conn.getHeaderField("Content-Disposition");
    Matcher matcher = REGEX_FILE_NAME.matcher(fileName);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }
  
  private static HttpURLConnection getConnection(URL url)
    throws IOException
  {
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept", "application/zip;text/html");
    return conn;
  }
  
  private static int copy(InputStream input, OutputStream output)
    throws IOException
  {
    long count = copyStream(input, output);
    if (count > 2147483647L) {
      return -1;
    }
    return (int)count;
  }
  
  private static long copyStream(InputStream input, OutputStream output)
    throws IOException
  {
    byte[] buffer = new byte[1024];
    long count = 0L;
    int n = 0;
    while (-1 != (n = input.read(buffer)))
    {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }
  
  private static void closeQuietly(OutputStream output)
  {
    try
    {
      if (output != null) {
        output.close();
      }
    }
    catch (IOException localIOException) {}
  }
  
  private static void closeQuietly(InputStream input)
  {
    try
    {
      if (input != null) {
        input.close();
      }
    }
    catch (IOException localIOException) {}
  }
}

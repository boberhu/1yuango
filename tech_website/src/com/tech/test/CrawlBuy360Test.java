package com.tech.test;

import com.tech.pojo.Product;
import com.tech.pojo.Productimage;
import com.tech.service.ProductImageService;
import com.tech.service.ProductService;
import com.tech.util.CutImages;
import com.tech.util.UploadImagesUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext*.xml"})
@Repository
public class CrawlBuy360Test
{
  @Autowired
  ProductService productService;
  @Autowired
  ProductImageService productImageService;
  private static final int BUFFER_SIZE = 102400;
  private DefaultHttpClient httpClient;
  private HttpGet httpGet;
  private HttpPost httpPost;
  
  private static void copy(File src, File dst)
  {
    try
    {
      InputStream in = null;
      OutputStream out = null;
      try
      {
        in = new BufferedInputStream(new FileInputStream(src), 102400);
        out = new BufferedOutputStream(new FileOutputStream(dst), 102400);
        byte[] buffer = new byte[102400];
        while (in.read(buffer) > 0) {
          out.write(buffer);
        }
      }
      finally
      {
        if (in != null) {
          in.close();
        }
        if (out != null) {
          out.close();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  @Test
  public void go()
    throws Exception
  {
    String url = "http://www.360buy.com/products/652-653-655.html";
    
    httpClient = new DefaultHttpClient();
    HttpParams params = httpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(params, 60000);
    HttpConnectionParams.setSoTimeout(params, 60000);
    httpGet = new HttpGet(url);
    ResponseHandler<String> responseHandler = new BasicResponseHandler();
    String content = (String)httpClient.execute(httpGet, responseHandler);
    Document document = Jsoup.parse(new URL(url), 60000);
    Elements elements = document.select("div.plist > ul > li");
    Iterator localIterator1 = elements.iterator();
    if (localIterator1.hasNext())
    {
      Element element = (Element)localIterator1.next();
      String productImg = "";
      String productName = "";
      String productTitle = "";
      int productType = 1000;
      int productPrice = 0;
      int productRealPrice = 0;
      String productDetail = "";
      String headImage = "";
      String Attribute_71 = "1";
      String strImg = element.select("div.p-img > a > img").first().attr("src");
      if (strImg.indexOf("360buyimg.com") != -1)
      {
        productImg = strImg;
      }
      else
      {
        String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
        productImg = strImg2;
      }
      productName = element.select("div.p-name > a").text();
      
      String proUrl = element.select("div.p-name > a").attr("href");
      System.err.println(proUrl);
      Product product = new Product();
      File myFile = new File(productImg);
      String myFileFileName = myFile.getName();
      product.setAttribute71(String.valueOf(1));
      product.setProductDetail(proUrl);
      product.setProductName(productName);
      product.setProductPrice(Integer.valueOf(0));
      product.setProductRealPrice(String.valueOf(0));
      product.setProductTitle(productName);
      product.setProductType(Integer.valueOf(1001));
      productService.add(product);
      String productImgPath = "/productImg/show";
      if (myFile != null)
      {
        myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
        String imageFileName = new Date().getTime() + myFileFileName;
        File imageFile = new File(UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath))).append("/").toString(), String.valueOf(product.getProductId())) + "/" + imageFileName);
        copy(myFile, imageFile);
        CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
        product.setHeadImage(productImgPath + "/" + String.valueOf(product.getProductId()) + "/" + imageFileName);
      }
      productService.add(product);
      
      Document document2 = Jsoup.parse(new URL(proUrl), 60000);
      
      Elements element2 = document2.select("div.spec-items > ul > li");
      for (Element element3 : element2)
      {
        String img = element3.select("img").attr("src");
        System.err.println(img);
        Productimage productimage = new Productimage();
        String productImgPath2 = "/productImg/imagezoom";
        long fileDateName = 0L;
        String imagePath = null;
        File myFile2 = new File(new URI(img));
        String myFileFileName2 = myFile2.getName();
        if (myFile2 != null)
        {
          myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
          fileDateName = new Date().getTime();
          String imageFileName = fileDateName + myFileFileName;
          imagePath = UploadImagesUtil.getFolder(new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath(productImgPath2))).append("/").toString(), String.valueOf(product.getProductId())) + "/";
          File imageFile = new File(imagePath + imageFileName);
          copy(myFile, imageFile);
          CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath + fileDateName + "_mid" + myFileFileName));
          CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath + fileDateName + "_small" + myFileFileName));
          productimage = new Productimage();
          productimage.setPiProductId(product.getProductId());
          productimage.setImage(String.valueOf(fileDateName));
          productimage.setImageType(myFileFileName);
          productimage.setAttribute75("show");
          productImageService.add(productimage);
        }
      }
    }
  }
  
  public static void main(String[] args)
    throws ClientProtocolException, IOException, InterruptedException
  {
    try
    {
      for (;;)
      {
        Document document = Jsoup.parse(new URL("http://www.yungouos.cn/getAllBuyRecord.html?startDate=2011-01-01%2000:00:00&endDate=2015-12-12%2000:00:00&pageNo=1&pageSize=1000000"), 1000);
        System.err.println(document);
        Thread.sleep(10L);
      }
    }
    catch (Exception localException) {}
  }
}

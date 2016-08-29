package com.tech.pojo;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = -6079720734007445595L;
	// const
	public static final Integer STATUS_UP = Integer.valueOf(1);
	public static final Integer STATUS_DN = Integer.valueOf(0);
	// props
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer singlePrice;
	private Integer productLimit;
	private String productRealPrice;
	private String productTitle;
	private String productDetail;
	private Integer productType;
	private Integer productBrand;
	private String headImage;
	private Integer status;
	private String style;
	private String winner;
	private String attribute71;

	public Product(Integer productId, String productName, Integer productPrice,
			Integer singlePrice, Integer productLimit, String productRealPrice,
			String productTitle, String productDetail, Integer productType,
			Integer productBrand, String headImage, Integer status,
			String style, String winner, String attribute71) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.singlePrice = singlePrice;
		this.productLimit = productLimit;
		this.productRealPrice = productRealPrice;
		this.productTitle = productTitle;
		this.productDetail = productDetail;
		this.productType = productType;
		this.productBrand = productBrand;
		this.headImage = headImage;
		this.status = status;
		this.style = style;
		this.winner = winner;
		this.attribute71 = attribute71;
	}

	public Product() {
	}

	public Product(String productName, Integer productPrice,
			Integer singlePrice, String productTitle, String productDetail,
			Integer productType, String headImage) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.singlePrice = singlePrice;
		this.productTitle = productTitle;
		this.productDetail = productDetail;
		this.productType = productType;
		this.headImage = headImage;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductRealPrice() {
		return productRealPrice;
	}

	public void setProductRealPrice(String productRealPrice) {
		this.productRealPrice = productRealPrice;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getAttribute71() {
		return attribute71;
	}

	public void setAttribute71(String attribute71) {
		this.attribute71 = attribute71;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Integer singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Integer getProductLimit() {
		return productLimit;
	}

	public void setProductLimit(Integer productLimit) {
		this.productLimit = productLimit;
	}

	public Integer getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(Integer productBrand) {
		this.productBrand = productBrand;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public boolean isUp() {
		return (STATUS_UP.equals(getStatus()));
	}

	public boolean isDown() {
		return (STATUS_DN.equals(getStatus()));
	}

}

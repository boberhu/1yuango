package com.tech.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Order id entity.
 * 
 * @author pzp
 * @since 2015-09-08
 */
public class OrderId implements Serializable{
	private static final long serialVersionUID = -4220062876686388284L;
	// props
	private Long id;
	private Long tm;
	
	public OrderId(){
		
	}
	
	public OrderId(final Long tm){
		this.tm = tm;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTm() {
		return tm;
	}

	public void setTm(Long tm) {
		this.tm = tm;
	}
	
	/** 获取订单号。*/
	public String getOxid(){
		return (new SimpleDateFormat("yyyyMMddHHmm")
		.format(new Date(getTm()))+getId());
	}
	
}

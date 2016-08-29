package com.tech.service;

/**
 * Order id service.
 * 
 * @author pzp
 * @since 2015-09-08
 */
public interface OrderIdService {

	/** Generating a order id that has the current time 'yyyyMMddHHmm' part 
	 * appending a auto-increment id.*/
	String addOxid();
	
}

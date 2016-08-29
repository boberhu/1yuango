package com.tech.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tech.service.ConsumetableService;
import com.tech.service.SpellbuyproductService;

/**
 * Order monitor that manages the spell-buy product's withhold, and restores
 *it after timeout, also generates next period product if necessary.
 * 
 * @author pzp
 * @since 2015-09-10
 */
@Component
@Scope("singleton")
public class OrderMonitor implements Runnable{
	// const
	private final static Logger LOG = LoggerFactory.getLogger(OrderMonitor.class);
	// props
	@Autowired
	ConsumetableService consService;
	@Autowired
	SpellbuyproductService buyProductService;

	public OrderMonitor(){
		
	}
	
	public void run(){
		LOG.info("Order monitor: {}", "started");
		final long timeout = 80000L;
		final int batch = 1024, nr = 10;
		try{
			for(int i = batch; i > 0; --i){
				int osize = -1;
				try{
					osize = consService.restoreTimeoutOrders(timeout, nr);
				}catch(final RuntimeException e){
					LOG.error("{}", e);
				}
				Thread.sleep(100L);
				try{
					buyProductService.generateNextPeriods(nr);
				}catch(final RuntimeException e){
					LOG.error("{}", e);
				}
				if(osize == 0){
					break;
				}
			}
		}catch(final InterruptedException e){
			LOG.info("Order monitor: {}", "inted");
		}catch(final RuntimeException e){
			LOG.error("{}", e);
		}
	}
	
}

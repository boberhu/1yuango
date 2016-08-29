package com.tech.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tech.exception.RuleViolationException;
import com.tech.pojo.Product;
import com.tech.pojo.Spellbuyproduct;
import com.tech.service.ProductService;
import com.tech.service.SpellbuyproductService;
import com.tech.util.Struts2Utils;

@Component("ProductAction")
public class ProductAction extends BaseAction{

	private static final long serialVersionUID = -6371958301213944344L;

	@Autowired
	@Qualifier("spellbuyproductService")
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private ProductService productService;
	
	private Product product;
	private Spellbuyproduct spellbuyproduct;
	
	private String id;
	
	
	public String index() throws IOException {
		List<Object[]> proList = spellbuyproductService.findByProductId(Integer.parseInt(id));
		if(proList==null || proList.size()==0)
			return null;
		Object[] objs = (Object[])proList.get(0);
		if(objs[0] instanceof Product){
			product = (Product)objs[0];
			spellbuyproduct = (Spellbuyproduct)objs[1];
		}else{
			product = (Product)objs[1];
			spellbuyproduct = (Spellbuyproduct)objs[0];
		}
		if(product.getStatus()!=1){
			throw new RuleViolationException("产品已经下架！");
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.sendRedirect("/products/" + spellbuyproduct.getSpellbuyProductId() + ".html");
		return null;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}

package com.onebill.productbilling.servicebuilder;

import com.onebill.productbilling.service.ProductService;
import com.onebill.productbilling.service.ProductServiceImpl;

public class ProductServiceFactory {

	public static ProductService createProductService() {
		return new ProductServiceImpl();
	}
}

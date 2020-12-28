package com.onebill.productbilling.exception;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}
}

package com.onebill.productbilling.exception;

@SuppressWarnings("serial")
public class BillingException extends RuntimeException{

	public BillingException(String message) {
		super(message);
	}
}

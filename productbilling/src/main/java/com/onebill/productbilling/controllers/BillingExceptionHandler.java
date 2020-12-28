package com.onebill.productbilling.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onebill.productbilling.dto.ResponseDto;
import com.onebill.productbilling.exception.BillingException;

@RestControllerAdvice
public class BillingExceptionHandler {

	@ExceptionHandler(BillingException.class)
	public ResponseEntity<ResponseDto> handler(BillingException e1) {
		ResponseDto dto = new ResponseDto();
		dto.setError(true);
		dto.setData(e1.getLocalizedMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
package com.onebill.productbilling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.productbilling.dto.ProductDto;
import com.onebill.productbilling.dto.ResponseDto;
import com.onebill.productbilling.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseDto addProduct(@RequestBody ProductDto pro) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.addProduct(pro));
		return dto;
	}

	@GetMapping("/product/{productName}")
	public ResponseDto getProduct(@PathVariable String productName) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getProduct(productName));
		return dto;
	}

	@GetMapping("/product/{productId}/plan")
	public ResponseDto getProductPlan(@PathVariable int productId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getProductPlan(productId));
		return dto;
	}

	@GetMapping("/product/{productId}/plan/{planId}/detail")
	public ResponseDto getPlanDetail(@PathVariable int productId, @PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlanDetail(productId, planId));
		return dto;
	}

	@GetMapping("/product/{productId}/plan/{planId}/charge")
	public ResponseDto getPlanCharge(@PathVariable int productId, @PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlanCharge(productId, planId));
		return dto;
	}

	@GetMapping("/product/{productId}/plan/{planId}/overdue")
	public ResponseDto getPlanOverdue(@PathVariable int productId, @PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlanOverdue(productId, planId));
		return dto;
	}

	@GetMapping("/{productId}")
	public ResponseDto getProductWithId(@PathVariable int productId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getProductWithId(productId));
		return dto;
	}

	@GetMapping
	public ResponseDto getAllProducts() {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getAllProducts());
		return dto;
	}

	@PutMapping
	public ResponseDto updateproduct(@RequestBody ProductDto pro) {
		System.out.println(pro);
		ResponseDto dto = new ResponseDto();
		dto.setData(service.updateProduct(pro));
		return dto;
	}

	@DeleteMapping("/{productId}")
	public ResponseDto removeProduct(@PathVariable int productId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.removeproduct(productId));
		return dto;
	}

}

package com.onebill.productbilling.ControllersTest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestProductController {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddProduct() {
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("productName", "VLLAZ");
		requestParams.put("productType", "Rental House");

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/productbilling/products");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGetProduct() {
		Response response = get("/productbilling/products/product/jio");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGetProductPlan() {
		Response response = get("/productbilling/products/product/72/plan");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGetPlanDetail() {
		Response response = get("/productbilling/products/product/72/plan/76/detail");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGetPlanCharge() {
		Response response = get("/productbilling/products/product/72/plan/76/charge");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGetPlanOverdue() {
		Response response = get("/productbilling/products/product/72/plan/76/overdue");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateproduct() {

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("productId", 72);
		requestParams.put("productName", "JIO");
		requestParams.put("productType", "SIM");

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		Response response = request.put("/productbilling/products");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void testRemoveProduct() {
		Response response = delete("/productbilling/products/118");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	

}
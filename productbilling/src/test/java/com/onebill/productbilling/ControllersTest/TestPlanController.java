package com.onebill.productbilling.ControllersTest;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestPlanController {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost/productbilling/plans";
		RestAssured.port = 8080;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddPlan() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("productId", "72");
		pro.put("productName", "JIO");
		pro.put("productType", "Broadband");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("planAmount", "199");
		requestParams.put("planFrequency", 54);
		requestParams.put("type", "SMS");
		requestParams.put("product", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		System.out.println("RESPONSE: " + response.body().asString());
	}
	
	@Test
	public void testGetAllPlans() {
		Response response = get("");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("RESPONSE: " + response.body().asString());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePlan() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("productId", "72");
		pro.put("productName", "JIO");
		pro.put("productType", "Broadband");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("planId",76);
		requestParams.put("planAmount", "599");
		requestParams.put("planFrequency", 58);
		requestParams.put("type", "Bundle");
		requestParams.put("product", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.put("");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		System.out.println("RESPONSE: " + response.body().asString());
	}
	
	@Test
	public void testRemovePlan() {
		Response response = delete("/92");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Response body: " + response.body().asString());
	}

}

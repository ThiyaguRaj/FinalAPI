package com.onebill.productbilling.ControllersTest;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestPlanBundle {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost/productbilling/plans";
		RestAssured.port = 8080;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddPlanCharge() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("chargeType", "OneTime");
		requestParams.put("charge", 130);
		requestParams.put("document", null);
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/charge");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePlanCharge() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("chargeType", "Activation");
		requestParams.put("charge", 120);
		requestParams.put("document", "ADHAR");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.put("/charge");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRemovePlanCharge() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("chargeType", "Service");
		requestParams.put("charge", 130);
		requestParams.put("document", null);
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.delete("/charge");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}


	@SuppressWarnings("unchecked")
	@Test
	public void testAddPlanDetail() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("serviceType", "Voice");
		requestParams.put("detail", 3000);
		requestParams.put("unit", "min");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/detail");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePlanDetail() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("serviceType", "Data");
		requestParams.put("detail", 77);
		requestParams.put("unit", "GB");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.put("/detail");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRemovePlanDetail() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("serviceType", "SMS");
		requestParams.put("detail", 1000);
		requestParams.put("unit", "sms");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.delete("/detail");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}



	@SuppressWarnings("unchecked")
	@Test
	public void testAddPlanOverdue() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("overageType", "SMS");
		requestParams.put("overageService", 1);
		requestParams.put("serviceCost", 1.36);
		requestParams.put("unit", "message");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/overdue");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePlanOverdue() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("overageType", "Data");
		requestParams.put("overageService", 1);
		requestParams.put("serviceCost", 39);
		requestParams.put("unit", "GB");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.put("/overdue");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRemovePlanOverdue() {
		RequestSpecification request = RestAssured.given();
		JSONObject pro = new JSONObject();
		pro.put("planId", 76);

		JSONObject requestParams = new JSONObject();
		requestParams.put("overageType", "Voice");
		requestParams.put("overageService", 1);
		requestParams.put("serviceCost", 1.36);
		requestParams.put("unit", "min");
		requestParams.put("plan", pro);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.delete("/overdue");
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

}

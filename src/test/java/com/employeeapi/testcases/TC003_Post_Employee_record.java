/******************************************************
Test Name:Create new record in database 
URI: http://dummy.restapiexample.com/api/v1/create
Request Type: POST
Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}
********* Validations **********
Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/


package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.employeeapi.testbase.testbase;
import com.employeeapi.utilities.RestUtils;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_record extends testbase{

	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empsal=RestUtils.empSal();
	String empage=RestUtils.empAge();
	
	@BeforeTest
	void createEmployee() throws InterruptedException
	{
		//logger.info("*********Started TC003_Post_Employee_Record **********");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		
		//{"name":"Peter","salary":"123","age":"23"}
		JSONObject requestParams  = new JSONObject();
		requestParams.put("name",empName);
		requestParams.put("salary",empsal);
		requestParams.put("age",empage);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());
		
		response = httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(5);
		
		
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empsal), true);
		Assert.assertEquals(responseBody.contains(empage), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		//Assert.assertEquals(serverType, "nginx/1.14.1");
	}
    
	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

	
}

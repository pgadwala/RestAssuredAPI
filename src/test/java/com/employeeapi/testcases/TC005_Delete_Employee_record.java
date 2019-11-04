/******************************************************
Test Name: Delete an employee record
URI: http://dummy.restapiexample.com/api/v1/delete/{id}
Request Type: DELETE
Request Payload(Body): NA
********* Validations **********
Response Payload(Body) : {"success":{"text":"successfully! deleted Records"}}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.employeeapi.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.testbase.testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_record extends testbase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		//Logger.info("*********Started TC005_Delete_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		// First get the JsonPath object instance from the Response interface
	    JsonPath jsonPathEvaluator = response.jsonPath();
					 
		//Capture id
		String empID=jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/"+empID); //Pass ID to delete record
				
		Thread.sleep(3000);
		}
		
	@Test
	void checkResponseBody()
	{
		logger.info("***********  Checking Respose Body **********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);

	}
		
	@Test
	void checkStatusCode()
	{   logger.info("***********  Checking Status Code **********");
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		logger.info("***********  Checking Status Line **********");
		String statusLine = response.getStatusLine(); // Gettng status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{   
		logger.info("***********  Checking Content Type **********");
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType()
	{   
		logger.info("***********  Checking Server Type **********");
		String serverType = response.header("Server");
		logger.info("Server Type is =>" +serverType); 
		//Assert.assertEquals(serverType, "nginx/1.16.1");
	}

	@Test
	void checkcontentEncoding()
	{   
		logger.info("***********  Checking Content Encoding**********");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" +contentEncoding); 
		Assert.assertEquals(contentEncoding, "gzip");

	}

	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC005_Delete_Employee_Record **********");
	}
}
		



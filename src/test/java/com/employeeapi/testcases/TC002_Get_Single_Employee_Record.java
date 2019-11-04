/******************************************************
Test Name:Get a single employee data
URI: http://dummy.restapiexample.com/api/v1/employee/{id}
Request Type: GET
Request Payload(Body): NA
********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800
 *********************************************************/


package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.testbase.testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends testbase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_Employee_Record **********");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
		httpRequest = RestAssured.given();
		response=httpRequest.request(Method.GET, "/employee/" +empID);
		
		Thread.sleep(5);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		//Assert.assertEquals(responseBody.contains(empID), true);
	
	}
	
    @Test
    void checkStatusCode()
    {
    	int StatusCode=response.getStatusCode();
    	Assert.assertEquals(StatusCode, 200);
    }
    
    @Test
    void checkResponseTime()
    {
    	long responseTime=response.getTime();
    	Assert.assertTrue(responseTime<6000);
    }
    
    @Test
    void checkStatusLine()
    {
    	String statusline = response.getStatusLine();
    	Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
    }
    
    @Test
    void checkContentType()
    {
    	String contenttype=response.getContentType();
    	Assert.assertEquals(contenttype, "text/html; charset=UTF-8");
    }
    
    @Test
    void checkServerType()
    {
    	String servertype = response.header("Server");
    }
    
    @Test
    void checkContentLength()
    {
    	String contentlength = response.getHeader("Content-Length");
    	Assert.assertTrue(Integer.parseInt(contentlength)<1500);
    }
    
    @AfterClass
    void teardown()
    {
    	logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
    }
}

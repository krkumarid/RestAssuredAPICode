package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETNONBDDAPI
{
    /*
     * In NON BDD approach 1. Prepare the request 2. Hit the API 3.Get the response
     * 4.Fetch the values from response
     */
    @Test
    public void getUser_Non_Bdd_Test()
    {
        System.out.println("getUser_Non_Bdd_Test()");
        RestAssured.baseURI = "https://gorest.co.in";
        // 1. Create the request( given() will retuen RequestSpecification)
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        // 2.Response [ get() will return response Object]
        Response response = request.get("/public/v2/users");

        // Fetch the values from the response

        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());
        System.out.println(response.getHeader("Server"));
    }

    @Test
    public void getUser_Non_Bdd_with_QueryParams_Test()
    {
        System.out.println("getUser_Non_Bdd_with_QueryParams_Test()");

        RestAssured.baseURI = "https://gorest.co.in";
        // 1. Create the request( given() will retuen RequestSpecification)
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");

        request.queryParams("name", "Kapoor");
        request.queryParam("gender", "male");

        // 2.Response [ get() will return response Object]
        Response response = request.get("/public/v2/users");

        // Fetch the values from the response

        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());
        System.out.println(response.getHeader("Server"));
        System.out.println(response.getContentType());
        // System.out.println(response.getTimeIn(null));
        System.out.println(response.getStatusLine());

        // JSON Path

        JsonPath js = response.jsonPath();

        System.out.println(js.getString("name"));
        System.out.println(js.getString("gender"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getHeader("Server"), "cloudflare");

        // Assert.assertEquals(js.getString("name").indexOf(1), "Ahilya Kapoor");

    }
    
    @Test
    public void getUser_Non_Bdd_HashMap_QueryParams_Test()
    {
        System.out.println("getUser_Non_Bdd_HashMap_QueryParams_Test()");
        
        RestAssured.baseURI = "https://gorest.co.in";
        // 1. Create the request( given() will retuen RequestSpecification)
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Kapoor");
        params.put("gender", "male");
        
        request.queryParams(params);
        
        // 2.Response [ get() will return response Object]
        Response response = request.get("/public/v2/users");

        // Fetch the values from the response

        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());
        System.out.println(response.getHeader("Server"));
        
    }
    
}

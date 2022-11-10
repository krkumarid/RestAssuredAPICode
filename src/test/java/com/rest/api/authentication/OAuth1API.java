package com.rest.api.authentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth1API
{
    @Test
    public void TwitterStatusAPI_OAuth1_Test()
    {
        // For OAuth1 we need to pass the four parameters
        // 1.Consumer Key
        // 2. Consumer secret key
        // 3. Access token
        // 4. Token secret
        RequestSpecification request = RestAssured.given().auth().oauth("nToTrMmI1RXiFGsoV57u2viOJ",
                "POaAmY2cdJ7foi8vnvi44SBEtSqBiTlND4z0H7mUAQskFvnZQc",
                "66744190-kJRLtbdKYXKGQMlMAI3OykdlknR2GxL9M63AQUfDp", "CeJNuJSaZW3SORl6XSnGeLRMNOflGhrqanR8P50NVaDc0");

        Response response = request.post(
                "https://api.twitter.com/1.1/statuses/update.json?status=hey, This is from my  RestAssured Code twitter API");
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
    
}

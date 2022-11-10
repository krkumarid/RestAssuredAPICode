package com.rest.api.authentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2APITest
{
    @Test
    public void checkOAuth2_API_Test()
    {
        RequestSpecification request = RestAssured.given().auth().oauth2("4910b883ccfa34954c9c714cff7b63f816ee3b3d");
        Response respose = request.post("http://coop.apps.symfonycasts.com/api/3488/chickens-feed");
        System.out.println(respose.statusCode());
        System.out.println(respose.prettyPrint());
    }
    
    @Test
    public void getOAuth2TokenTest()
    {
        RequestSpecification request = RestAssured.given().formParam("client_id", "RajeevAPI")
                .formParam("client_secret", "7a28217388aa69e7c189fb75ef009fbd")
                .formParam("grant_type", "client_credentials");
        Response response = request.post("http://coop.apps.symfonycasts.com/token");
        
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
        String tokenID = response.jsonPath().getString("access_token");
        
        // Feed the chicken API with the runtime generated token.
        
        RequestSpecification request1 = RestAssured.given().auth().oauth2(tokenID);
        Response respose1 = request1.post("http://coop.apps.symfonycasts.com/api/3488/chickens-feed");
        System.out.println(respose1.statusCode());
        System.out.println(respose1.prettyPrint());

    }
}

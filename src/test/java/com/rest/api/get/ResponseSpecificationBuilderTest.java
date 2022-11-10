package com.rest.api.get;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationBuilderTest
{
    ResponseSpecBuilder resSpeBuil = new ResponseSpecBuilder();

    ResponseSpecification resSpec_200_OK = resSpeBuil.expectContentType(ContentType.JSON).expectStatusCode(200)
            .expectHeader("Server", "cloudflare").build();

    ResponseSpecification resSpec_400_BAD_REQUEST = resSpeBuil.expectContentType(ContentType.JSON).expectStatusCode(400)
            .expectHeader("Server", "cloudflare").build();

    ResponseSpecification resSpec_401_AUTH_FAIL = resSpeBuil.expectContentType(ContentType.JSON).expectStatusCode(401)
            .expectHeader("Server", "cloudflare").build();

    @Test
    public void ResposeSpecTest()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().header("Authorization", "Bearer _12103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().get("/public/v2/users").then().assertThat().spec(resSpec_401_AUTH_FAIL);
    }
    
}

package com.rest.api.authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;

public class AuthApis
{
    // basic with preemitive authentication ( we need to pass the username and
    // password)
    
    @Test
    public void basic_preemitive_Auth_Api_Test()
    {
        given().log().all().auth().preemptive().basic("admin", "admin").when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth").then().log().all().assertThat().statusCode(200);
    }

    // basic authentication ( we need to pass the username and password)
    @Test
    public void basic_Auth_Api_Test()
    {
        given().log().all().auth().preemptive().basic("admin", "admin").when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth").then().log().all().assertThat().statusCode(200);
    }
    
    // basic digest authentication ( we need to pass the username and password)
    @Test
    public void basic_digest_Auth_Api_Test()
    {
        given().log().all().auth().digest("admin", "admin").when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth").then().log().all().assertThat().statusCode(200);
    }
    
    @Test
    public void basic_form_auth_API_Test()
    {
        given().log().all().auth()
                .form("rkachari", "KasiAppu1*",
                        new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm", "username",
                                "password"))
                .when().log().all().get("https://classic.freecrm.com/system/authenticate.cfm").then().log().all()
                .assertThat().statusCode(200);
    }
    
    // OAuth2.0
    // We have to pass the acces tocken.
    @Test
    public void OAuth_API_Test()
    {
        given().log().all().auth().oauth2("_2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d").when()
                .log().all().get("http://md5.jsontest.com?text=test").then().log().all().assertThat().statusCode(200);
    }
    
    @Test
    public void OAuth_API_Test_With_AuthHeader()
    {
        RestAssured.baseURI = "http://md5.jsontest.com";
        
        given().log().all().contentType("application/json")
                .header("Authorization", "Bearer _2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().get("text=test").then().log().all().statusCode(200).and()
                .header("Server", "Google Frontend");
    }

    @Test
    public void OAuth_API_WithTwoQueryParams_API_Test()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all().contentType("application/json")
                .header("Authorization", "Bearer _2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .queryParam("name", "Shobhana").queryParam("gender", "male").when().log().all().get("/public/v2/users")
                .then().log().all().assertThat().statusCode(200).and().header("Server", "cloudflare");
        
    }
}

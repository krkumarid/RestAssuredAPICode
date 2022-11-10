package com.rest.api.post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIBDD
{
    @Test
    public void tokenPostAPI_BDD_Test()
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all().contentType(ContentType.JSON)
                .body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
                .when().log().all().post("/auth").then().log().all().assertThat().statusCode(200);
    }
    
    @Test
    public void tokenPostAPI_BDD_FILE_Test()
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all().contentType(ContentType.JSON)
                .body(new File("D:\\JavaXps\\RESTAssuredAPI\\src\\test\\java\\DataFile\\credentials.json")).when().log()
                .all().post("/auth").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void tokenPostAPI_BDD_FILE_Token_Extract_Method_Test()
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String tokenID = given().log().all().contentType(ContentType.JSON)
                .body(new File("D:\\JavaXps\\RESTAssuredAPI\\src\\test\\java\\DataFile\\credentials.json")).when().log()
                .all().post("/auth").then().log().all().extract().path("token");
        
        System.out.println("Token:" + tokenID);
        Assert.assertNotNull(tokenID);
    }

    @Test
    public void createUser_Post_API_JSON_String_Test()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .body("{\"name\":\"Asha Devi N\", \"gender\":\"female\", \"email\":\"ashadevin@15ce.com\", \"status\":\"active\"}")
                .when().log().all().post("/public/v2/users").then().log().all().assertThat()
                .body("email", equalTo("ashadevin@15ce.com")).and().body("status", equalTo("active"));
    }
    
    @Test
    public void createUser_Post_API_JSON_File_Test()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .body(new File("D:\\JavaXps\\RESTAssuredAPI\\src\\test\\java\\DataFile\\user.json")).when().log().all()
                .post("/public/v2/users").then().log().all().assertThat().body("email", equalTo("smitha@15ce.com"))
                .and().body("status", equalTo("active"));
    }
}

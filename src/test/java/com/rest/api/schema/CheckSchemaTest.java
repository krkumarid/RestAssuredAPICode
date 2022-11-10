package com.rest.api.schema;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CheckSchemaTest
{
    @Test
    public void bookings_Schema_Test()
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all().contentType(ContentType.JSON)
                .body(new File("D:\\JavaXps\\RESTAssuredAPI\\src\\test\\resources\\Bookings.json ")).when().log().all()
                .post("/booking").then().log().all().assertThat().statusCode(200).and()
                .body(matchesJsonSchemaInClasspath("BookingSchema.json"));
        
    }

    @Test
    public void get_User_API_Schema_Test()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer _2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().get("/public/v2/users?name=Sho&gender=male").then().log().all().assertThat()
                .statusCode(200).and().body(matchesJsonSchemaInClasspath("getUsersSchema.json"));
    }
}

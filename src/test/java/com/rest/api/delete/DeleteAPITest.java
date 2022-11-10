package com.rest.api.delete;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPITest
{
    @Test
    public void delete_user_API_Test()
    {
        // POST -->GET-->DELETE-->GET
        // 1. Create the POST request with payload

        User user = new User("Kasi", "KasiKarthi05@gmail.com", "male", "active");

        // 2. Convert the POJO to JSON using JACKSON API - ObjectMapper

        ObjectMapper mapper = new ObjectMapper();
        String userJsonPost = null;

        try
        {
            userJsonPost = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(userJsonPost);

        RestAssured.baseURI = "https://gorest.co.in";

        int ID = given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .body(userJsonPost).when().log().all().post("/public/v2/users").then().log().all().and().assertThat()
                .contentType(ContentType.JSON).and().extract().body().path("id");

        System.out.println("The POST user ID :::>" + ID);
        String userID = String.valueOf(ID);
        System.out.println("The POST user ID :::>" + userID);

        // Verifying the data using GET API
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().get("/public/v2/users/" + userID).then().log().all().contentType(ContentType.JSON)
                .assertThat().body("id", equalTo(ID));
        
        // DELETE the user using DELETE API Call
        
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().delete("/public/v2/users/" + userID).then().assertThat().statusCode(204);

        // Verifying the data using GET API
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().get("/public/v2/users/" + userID).then().log().all().assertThat()
                .contentType(ContentType.JSON).and().body("message", equalTo("Resource not found")).and()
                .statusCode(404);

    }
}

package com.rest.api.put;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PUTApiTest
{
    /*
     * Create a ser with POST call Get the user info Update the inof with the PUT
     * call Check whether the other attributes changed or not with this updation
     */
    @Test
    public void updateUser_POST_Api_Test()
    {
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
        // 3. Call PUT Api

        user.setName("KasinathanRajeev");

        System.out.println(user.getName()); // 4.
        // Convert the POJO to JSON using JACKSON API - ObjectMapper
        String userUpdateData = null;
        try
        {
            userUpdateData = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .body(userUpdateData).when().put("/public/v2/users/" + userID).then().log().all().assertThat()
                .contentType(ContentType.JSON).and().body("name", equalTo(user.getName())).and()
                .body("status", equalTo(user.getStatus()));
        
        // Verifying the data using GET API
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .when().log().all().get("/public/v2/users/" + userID).then().log().all().contentType(ContentType.JSON)
                .assertThat().body("id", equalTo(ID));

    }

}

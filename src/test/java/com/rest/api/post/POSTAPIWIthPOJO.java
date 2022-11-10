package com.rest.api.post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIWIthPOJO
{
    @Test
    public void create_With_Pojo_Test()
    {
        User user = new User("Pravi", "pravi@gmail.com", "male", "active");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;
        try
        {
            userJson = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(userJson);
        
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all().contentType(ContentType.JSON)
                .header("Authorization", "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .body(userJson).when().log().all().post("/public/v2/users").then().log().all().assertThat()
                .contentType(ContentType.JSON).and().body("name", equalTo(user.getName()))
                .body("gender", equalTo(user.getGender()));
        
    }
}

package com.rest.api.get;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserResponseXml
{
    @Test
    public void gerUser_Response_XML_Test()
    {
        RestAssured.baseURI = "https://gorest.co.in";

        Response response = given().log().all().accept(ContentType.XML)
                .header("Authorization", "Bearer _2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d")
                .header("Content-Type", "text/xml").header("Accept", "application/xml").when().log().all()
                .get("/public/v2/users?name=Puneet Jha&gender=male");
        
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

        // XmlPath xmlPath = response.xmlPath();
        // System.out.println(xmlPath.getString("name"));
        
    }
}

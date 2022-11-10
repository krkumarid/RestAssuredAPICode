package com.rest.api.get;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetBDDApi
{

    /*
     * given() when() then() and()
     */
    @Test
    public void getAPITest_1()
    {
        given().log().all().when().log().all().get("https://ergast.com/api/f1/2017/circuits.json").then().log().all()
                .assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
    }

    @Test
    public void getAPITest_2()
    {
        Response response = given().log().all().when().log().all().get("https://ergast.com/api/f1/2017/circuits.json");
        int statusCode = response.getStatusCode();
        System.out.println("Api status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getAPICiruitTest_3()
    {
        RestAssured.baseURI = "https://ergast.com";

        given().log().all().when().log().all().get("/api/f1/2017/circuits.json").then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON).and().header("Content-Length", Matchers.equalTo("4552"));

    }
    
    @Test
    public void getJSONAPI_VerifyMD5Value()
    {
        given().log().all().when().log().all().get("http://md5.jsontest.com?text=test").then().log().all().assertThat()
                .body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
    }
    
    @Test
    public void getJSONAPI_VerifyMD5ValueWithParam()
    {
        given().log().all().param("text", "test").when().log().all().get("http://md5.jsontest.com").then().log().all()
                .assertThat().body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
    }

    /*
     * Get BDD approach with Dataprovider
     */
    @DataProvider(name = "getCircuitYearDate")
    public Object[][] getCircuitYearInfo()
    {
        return new Object[][] { { "2017", 20 }, { "2016", 21 }, { "1966", 9 }, };
    }
    
    @Test(dataProvider = "getCircuitYearDate")
    public void numberOfCircuitYearTest(String seasonYear, int circuitNumber)
    {
        given().pathParam("raceSeason", seasonYear).when().get("/api/f1/{raceSeason}/circuits.json").then().assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitNumber));
    }
    
}

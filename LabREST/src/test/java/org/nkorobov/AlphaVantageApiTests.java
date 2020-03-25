package org.nkorobov;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AlphaVantageApiTests {
    private static final String url = "https://www.alphavantage.co";
    private static final String apiKey = /*to be filled*/;

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri(url + "/query").
                setAccept(ContentType.JSON).
                addQueryParam("apikey", apiKey).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).build();
    }

    @Test
    public void getGlobalQuoteTest()
    {
        JsonPath path =
                given().
                    param("function", "GLOBAL_QUOTE").
                    param("symbol", "SPY").
                when().
                    get().
                then().
                    body(matchesJsonSchemaInClasspath("globalQuoteJsonSchema.json")).
                extract().jsonPath();

        Assert.assertEquals("SPY", path.getString("'Global Quote'.'01. symbol'"));
    }

    @Test
    public void symbolSearchTest() {
        JsonPath path =
                given().
                    param("function", "SYMBOL_SEARCH").
                    param("keywords", "tesla").
                    param("datatype", "json").
                when().
                    get().
                then().
                    body(matchesJsonSchemaInClasspath("symbolSearchJsonSchema.json")).
                extract().jsonPath();

        Assert.assertEquals("TSLA", path.getString("bestMatches[0].'1. symbol'"));
        Assert.assertEquals("Tesla Inc.", path.getString("bestMatches[0].'2. name'"));
        Assert.assertEquals("Equity", path.getString("bestMatches[0].'3. type'"));
        Assert.assertEquals("United States", path.getString("bestMatches[0].'4. region'"));
        Assert.assertEquals("USD", path.getString("bestMatches[0].'8. currency'"));
    }
}

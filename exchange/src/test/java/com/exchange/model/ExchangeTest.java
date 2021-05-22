package com.exchange.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;

public class ExchangeTest {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://api.currencyscoop.com";
            get("/v1/latest?api_key=bcffc7604b5e599479ff34bfa797903b").then().statusCode(200);
    }

    @Test
    public void testConsumeExchangeSuccess(){
            String usd = get("/v1/latest?api_key=bcffc7604b5e599479ff34bfa797903b")
                .then()
                .statusCode(200)
                .extract()
                .path("response.base");
            assertEquals("USD", usd);
    }

    @Test
    public void testConsumeExchangeFail(){
            Integer statusCode = get("/v1/latest?api_key=bcffc7604b5e599479ff34bfa797903b")
                .then()
                .statusCode(401)
                .extract()
                .path("meta.code");
            assertNotEquals(200, statusCode);
    }
}

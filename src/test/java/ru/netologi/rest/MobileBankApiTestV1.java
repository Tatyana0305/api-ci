package ru.netologi.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        //Given-When-Then

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("", hasSize(3))
                .body("[0].id", equalTo(1))
                .body("[2].currency", equalTo("RUB"))
                .body("[2].balance", greaterThan(0))
                .header("Content-Type", "application/json; charset=UTF-8")
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .body("every{ it.balance >=0}", is(true));

    }
}

package org.acme.hibernate.reactive;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FlowersEndpointTest {

    @Test
    public void testListAllFlowers() {
        given()
				.when()
				.get("/flowers")
				.then()
				.statusCode(200)
				.contentType("application/json")
				.body(is("[{\"seed\":{\"id\":1},\"name\":\"Rose\"},{\"seed\":{\"id\":2},\"name\":\"Sunflower\"},{\"seed\":{\"id\":3},\"name\":\"Chrysanthemum\"}]"));
    }

}

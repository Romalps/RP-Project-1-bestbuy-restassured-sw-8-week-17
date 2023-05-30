package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoreCURDTest extends TestBase {

    static String name = "Budgens" + TestUtils.getRandomValue();
    static String type = "Groceries" + TestUtils.getRandomValue();
    static String address = "1458" + TestUtils.getRandomValue() + "High Road";
    static String address2 = TestUtils.getRandomValue() + "Station Road";
    static String city = "London";
    static String state = "Middlesex";
    static String zip = "WE9 6LG";
    static double lat = 65.5689123;
    static double lng = -89.215466;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5";
    static int storeId;

    @Test
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        response.then().log().all().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void getTheStoreDetails() {

        Response response = given()
                .when()
                .get("/stores/8921");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void updateTheStoreDetails(){
        StorePojo storePojo = new StorePojo();
        storePojo.setName("BestWay");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity("Birmingham");
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .put("/stores/8921");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteTheStoreDetail(){
        Response response = given()
                .pathParam("id",8921)
                .when()
                .delete("/stores/{id}");
        response.then().log().all().statusCode(200);

        Response response1 = given()
                .pathParam("id",8921)
                .when()
                .get("/stores/{id}");
        response1.then().statusCode(404);
        response1.prettyPrint();

    }

}

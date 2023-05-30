package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 21. Extract the limit
    @Test
    public void extractTheLimit() {
        int limit = response.extract().path("limit");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //22. Extract the total
    @Test
    public void extractTheTotal() {
        int total = response.extract().path("total");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The total is : " + total);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void extractTheNameOf5thProduct() {
        String productName = response.extract().path("data[4].name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The name of the 5th product is : " + productName);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void extractTheNameOfAllTheProducts() {
        List<String> allProductsNames = response.extract().path("data.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The names of the all products : " + allProductsNames);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void extractTheProductIdOfAllTheProducts() {
        List<Integer> productIdList = response.extract().path("data.id");

        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The productId of all the products : " + productIdList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //26. Print the size of the data list
    @Test
    public void printTheSizeOfTheDataList() {
        int sizeList = response.extract().path("data.size");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The size of the data list is : " + sizeList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void getAllTheValueOfTheProductWhereProductNameisEnergizerMaxBatteries() {
        List<HashMap<String, ?>> allTheValuesOfProduct = response.extract().path("data.findAll{it.name== 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the value of the product where product name = Energizer - MAX Batteries AA (4-Pack) is : " + allTheValuesOfProduct);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void getTheModelOfTheProductWhereProductNameIsEnergizerNCellE90() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)is : " + model);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void getAllTheCategoriesOf8thProducts() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the categories of 8th products : " + categories);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void getCategoriesOfTheStoreWhereProductId150115() {
        List<HashMap<String, ?>> categoriesOfTheStore = response.extract().path("data.findAll{it.id==150115}.categories");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The categories of the store where product id is 150115  : " + categoriesOfTheStore);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void getAllTheDescriptionsOfAllTheProducts() {
        List<HashMap<String, ?>> description = response.extract().path("data.description");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the descriptions of all the products : " + description);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void getIdOfAllCategoriesOfAllTheProducts() {
        List<String> idOfAllCategories = response.extract().path("data.categories.id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The id of all categories of all the products : " + idOfAllCategories);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void findTheProductNamesWhereTypeIsHardGood() {
        List<String> productName = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The product names Where type is HardGood : " + productName);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void findTheTotalNumberOfCategoriesForTheProductWhereProductNameIsDuracell() {
        List<HashMap<String, ?>> totalCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The Total number of categories for the product where product name is Duracell - AA1.5V CopperTop Batteries (4-Pack) : " + totalCategories);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void findTheCreatedAtForAllProductsWhosePrice() {

        List<String> createdAt = response.extract().path("data.findAll{it.price< 5.49}.createdAt");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is : " + createdAt);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void findTheNameOfAllCategoriesWhereProductNameIsEnergizerMaxBatteriesAA() {

        List<String> namesOfAllCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The name of all categories Where product name is Energizer - MAX Batteries AA (4-Pack) : " + namesOfAllCategories);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void findTheManufacturerOfAllTheProducts() {
        List<String> manufacturerList = response.extract().path("data.manufacturer");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The manufacturer of all the products : " + manufacturerList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void findTheImageOfProductsWhoseManufacturerIsEnergizer() {
        List<String> imageList = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The image of products whose manufacturer is Energizer : " + imageList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void findTheCreatedAtForAllCategoriesProductsWhosePrice() {
        List<HashMap<String, ?>> createdAtForAllCategories = response.extract().path("data.findAll{it.price>5.99}.categories.createdAt");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The createdAt for all categories products whose price > 5.99 : " + createdAtForAllCategories);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //40. Find the URL of all the product
    @Test
    public void findTheURLOfAllTheProduct() {
        List<String> urlList = response.extract().path("data.url");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The URL of all the product : " + urlList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

}

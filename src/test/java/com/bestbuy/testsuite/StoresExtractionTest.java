package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.codehaus.groovy.util.ListHashMap;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


    // 1. Extract the limit
    @Test
    public void extractTheLimit() {
        int limit = response.extract().path("limit");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //2. Extract the total
    @Test
    public void extractTheTotal() {
        int total = response.extract().path("total");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The value of Total is : " + total);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void extractTheNameOf5thStore() {
        String name = response.extract().path("data[4].name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The name of the 5th store is : " + name);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void extractTheNamesOfAllTheStore() {
        List<String> storeNamesList = response.extract().path("data.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("Names of all the store are : " + storeNamesList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void extractTheStoreIdOfAllTheStore() {
        List<Integer> storeIdList = response.extract().path("data.id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("StoreId of all the store are : " + storeIdList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //6. Print the size of the data list
    @Test
    public void printTheSizeOfTheDataList() {
        //List<Integer> sizeList = response.extract().path("data");
        int sizeList = response.extract().path("data.size");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The size of the data list is : " + sizeList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void getAllTheValueOfTheStoreWhereStoreNameStCloud() {
        List<HashMap<String, ?>> allTheValues = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the value of the store where store name is St Cloud : " + allTheValues);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void getTheAddressOfTheStoreWhereStoreNameRochester() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The address of the store where store name is Rochester : " + address);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void GetAllTheServicesOf8thStore() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the services of 8th store  : " + services);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServicesOfTheStoreWhereServiceNameWindowsStore() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name== 'Windows Store'}}.services.storeservices");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The store services of the store where service name = Windows store  : " + storeServices);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void getAllTheStoreIdOfAllTheStore() {
        List<Integer> storeIdList = response.extract().path("data.services.storeservices.storeId");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the storeId of all the store   : " + storeIdList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //12. Get id of all the store
    @Test
    public void getIdOfAllTheStore() {
        List<Integer> idList = response.extract().path("data.id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the Id of all the store   : " + idList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void findTheStoreNamesWhereStateND() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        //String  storeName = response.extract().path("data[7].name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The store names where state is ND   : " + storeName);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void findTheTotalNumberOfServicesForTheStoreWhereStoreNameIsRochester() {

        // List<Integer> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        List<Integer> totalServices = response.extract().path("data[8].services");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The total number of services for the store where store name is Rochester   : " + totalServices.size());
        System.out.println("-------------------- End Of The Test ----------------------");


    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void findTheCreateAtForAllServicesWhoseNameIsWindowsStore() {
        List<?> createdAtAllServicesList = response.extract().path("data.find{it.services}.services.findAll{it.name='Windows Store'}.storeservices.createdAt");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The createdAt for all services whose name is Windows Store   : " + createdAtAllServicesList.size());
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void findTheNameOfAllServiceswhereStoreNameIsFargo() {
        List<String> nameOfAllServicesList = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The name of all services Where store name is Fargo   : " + nameOfAllServicesList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void findTheZipOfAllTheStore() {
        List<String> zipList = response.extract().path("data.zip");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The zip of all the store  : " + zipList);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void findTheZipOfStoreNameIsRoseville() {
        List<String> zipName = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The zip of store name is Roseville  : " + zipName);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void findTheStoreServicesDetailsOfTheServiceNameMagnoliaHomeTheater() {
//        List<HashMap<String, ?>> storeServicesOfServiceName = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        List<HashMap<String,?>>storeServicesOfServiceName = response.extract().path("data.findAll{it.services.find{it.name=='Magnolia Home Theater'}!=null}.services.storeservices");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The store services details of the service name is Magnolia Home Theater  : " + storeServicesOfServiceName);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void findTheLatOfAllTheStores() {
        List<Double> latOfALlStores = response.extract().path("data.lat");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The lat of all the stores  : " + latOfALlStores);
        System.out.println("The size of lat of all the stores  : " + latOfALlStores.size());
        System.out.println("-------------------- End Of The Test ----------------------");
    }

}

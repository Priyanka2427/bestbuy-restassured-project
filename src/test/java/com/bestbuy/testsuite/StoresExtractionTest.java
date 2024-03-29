package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public static void storeTest() {

        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);

        // 1. Extract the limit

        int limit = response.extract().path("limit");
        System.out.println("1. Extract the limit = " + limit);

        // 2. Extract the total
        int total = response.extract().path("total");
        System.out.println("2. Extract the total = " + total);

        // 3. Extract the name of 5th store
        String storeName = response.extract().path("data[4].name");
        System.out.println("3. Extract the name of 5th store = " + storeName);

        // 4. Extract the names of all the store
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("4. Extract the names of all the store = " + allStoreName);

        //5. Extract the storeId of all the store
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("5. Extract the Id of all the store = " + ids);

        //6. Print the size of the data list
        List<String> allData = response.extract().path("data");
        System.out.println("6. Print the size of the data list = " + allData.size());

        //7. Get all the value of the store where store name = St Cloud
        List<HashMap<String, Object>> storeInfo = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("7. Get all the value of the store where store name (St Cloud) = " + storeInfo);

        //8. Get the address of the store where store name = Rochester
        List<String> addressOfStore = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("8. Get the address of the store where store name (Rochester) = " + addressOfStore.get(0));

        //9. Get all the services of 8th store
        List<List<HashMap<String, Object>>> services = response.extract().path("data[7].services");
        System.out.println("9. Get all the services of 8th store = " + services);

        //10. Get store services of the store where service name = Windows Store
        List<HashMap<String, Object>> storeServices = response.extract().path("data.collectMany{store->store.services.findAll{it.name == 'Windows Store'}.collect{ it.storeservices}}");
        System.out.println("10. Get store services of the store where service name (Windows Store) = " + storeServices);

        //11. Get all the storeId of all the store
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("11. Get all the storeId of all the store = " + storeIds);

        //12. Get id of all the store
        List<Integer> id = response.extract().path("data.id");
        System.out.println("12. Extract the Id of all the store = " + id);

        //13. Find the store names Where state = ND
        List<String> listOfStoreName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("13. Find the store names Where state (ND) = " + listOfStoreName);

        //14. Find the Total number of services for the store where store name = Rochester
        List<List<HashMap<String, Object>>> numberOfServices = response.extract().path("data.find{it.name == 'Rochester'}.services");
        System.out.println("14. Find the Total number of services for the store where store name (Rochester) = " + numberOfServices.size());


        //15. Find the createdAt for all services whose name = “Windows Store”
        List<HashMap<String, Object>> createdAT = response.extract().path("data.collectMany{store->store.services.findAll{it.name == 'Windows Store'}.collect{ it.createdAt }}");
        System.out.println("15. Find the createdAt for all services whose name (Windows Store) = " + createdAT);

        //16. Find the name of all services Where store name = “Fargo”
        List<String> nameOfServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");

        //17. Find the zip of all the store
        List<Integer> zips = response.extract().path("data.zip");
        System.out.println("17. Find the zip of all the store = " + zips);

        //18. Find the zip of store name = Roseville
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("18. Find the zip of store name (Roseville) = " + zip.get(0));

        //19. Find the store services details of the service name = Magnolia Home Theater
        List<HashMap<String, Object>> ss = response.extract().path("data.collectMany{store->store.services.findAll{it.name == 'Magnolia Home Theater'}.collect{ it.storeservices }}");
        System.out.println("19. Find the storeservices details of the service name (Magnolia Home Theater) = " + ss);

        //20. Find the lat of all the stores
        List<Double> latitudes = response.extract().path("data.lat");
        System.out.println("20. Find the lat of all the stores = " + latitudes);


    }
}

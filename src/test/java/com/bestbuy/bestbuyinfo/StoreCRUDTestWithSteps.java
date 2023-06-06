package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCRUDTestWithSteps extends TestBase {
    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() + " , Random Street";
    static String address2 = "Roaming Street";
    static String City = "London";
    static String state = "England";
    static String zip = "123456";
    static double lat = 54.23569;
    static double lng = -94.563214;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;
    static int allStoresIdSize;

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001(){
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, City, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId = response.log().all().extract().path("id");
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(productMap, hasValue(storeId));

    }
    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        storeSteps. updateStore(name, type, address, address2,  City,  state,  zip, lat,  lng, hours,  storeId).statusCode(200);
        HashMap<String, Object> storeMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
    }
    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {
        storeSteps.deleteStore(storeId).statusCode(200);
        storeSteps.getStoreByID(storeId).statusCode(404);

    }
    @Title("Getting all Stores info")
    @Test
    public void test005() {
      storeSteps.getAllProductInfo().statusCode(200);

    }







}

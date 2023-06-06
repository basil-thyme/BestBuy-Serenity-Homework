package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {

    @Step("Creating store with name : {0}, type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}, lat : {7}, lng : {8}, hours : {9}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String City, String state, String zip, double lat, double lng, String hours) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, City, state, zip, lat, lng, hours);

        return SerenityRest. given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_SINGLE_STORE)
                .then();
    }
    @Step("Getting the store information with storeID : {0}")
    public HashMap<String, Object> getStoreInfoById(int storeId) {

        HashMap<String, Object> storeMap = SerenityRest.given()
                .when()
                .pathParam("storeId", storeId)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return storeMap;
    }
    /*@Step("Getting the Store information with firstName : {0}")
    public   HashMap<String, Object> getStoreInfoByName(String firstName) {
        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(1)";
        HashMap<String, Object> value = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200)
                .extract()
                .path(s1 + firstName + s2);
        return value;
    }
*/
    @Step("Update the store with name : {0}, type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}, lat : {7}, lng : {8}, hours : {9}, storeId : {10}")
    public ValidatableResponse updateStore(String name, String type, String address, String address2, String City, String state, String zip, double lat, double lng, String hours, int storeId) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, City, state, zip, lat, lng, hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storeId", storeId)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all().statusCode(200);
    }
    @Step("Delete the store with information storeId: {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store information with storeId : {0}!")
    public ValidatableResponse getStoreByID(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

    @Step("Getting all the Store information")
    public ValidatableResponse getAllProductInfo() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then();
    }
    }




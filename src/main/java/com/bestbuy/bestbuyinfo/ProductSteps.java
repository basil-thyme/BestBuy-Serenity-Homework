package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating product with name : {0}, type : {1}, price : {2}, shipping : {3}, upc : {4}, description : {5}, manufacturer : {6}, mode : {7}, url : {8}, image : {9}")
    public ValidatableResponse CreateNewProduct(String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_PRODUCT)
                .then();

    }

    @Step("Getting the Product information with productID : {0}")
    public HashMap<String, Object> getProductInfoById(int productId) {

        HashMap<String, Object> productMap = SerenityRest.given()
                .when()
                .pathParam("productId", productId)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return productMap;
    }


    @Step("Update the product with name : {0}, type : {1}, price : {2}, shipping : {3}, upc : {4}, description : {5}, manufacturer : {6}, mode : {7}, url : {8}, image : {9}, productId : {10}")
    public ValidatableResponse updateProduct(String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image, int productId) {
        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Delete the product with information productId : {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information with productId : {0}!")
    public ValidatableResponse getProductByID(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting all the Product information")
    public ValidatableResponse getAllProductInfo() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }


}

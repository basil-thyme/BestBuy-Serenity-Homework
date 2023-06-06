package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps  extends TestBase {

    static String name = "My Electronics " + TestUtils.getRandomName();
    static String type = "Gadgets" + TestUtils.getRandomName();
    static Double price = 555.99;
    static Integer shipping = 5;
    static String upc = "039393943943";
    static String description = "Electronics Gadgets under one roof " + TestUtils.getRandomValue();
    static String manufacturer = "Google";
    static String model = TestUtils.getRandomName();
    static String url = "https://www.bestbuy.com/site/apple-macbook-pro-13-display-with-touch-bar-intel-core-i5-16gb-memory-512gb-ssd-space-gray/6287726.p?skuId=6287726";
    static String image = "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6287/6287726_sd.jpg;maxHeight=640;maxWidth=550";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @WithTag("productfeature: SMOKE")

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.CreateNewProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productSteps.updateProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image, productId).statusCode(200);
        HashMap<String, Object> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
    }

    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductByID(productId).statusCode(404);

    }
    @Title("Getting all products info")
    @Test
    public void test005() {
        productSteps.getAllProductInfo().statusCode(200);

    }
}

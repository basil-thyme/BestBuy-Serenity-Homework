package com.bestbuy.constants;

public class EndPoints {

    /**
     * These are products end points
     */

    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_SINGLE_PRODUCT = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productId}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productId}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productId}";

    /**
     * These are Stores end points
     */

    public static final String GET_ALL_STORES= "/stores";
    public static final String CREATE_SINGLE_STORE = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeId}";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeId}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeId}";


}

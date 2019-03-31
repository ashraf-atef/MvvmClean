package com.example.mvvmclean.common.constant;

public class Constant {
    public static final String API_URL_KEY = "APP_URL";
    public static final String API_URL = "https://eazyalleazy.eazymobility.com/";
    public static final String REFRESH_TOKEN_ENDPOINT = API_URL+"refresh";
    public static final String AUTH_TOKEN_KEY = "auth_token";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";
    public static final String USER_ID = "customer_id";
    public static final String HAS_PIN = "has_pin";
    public static final String EXPIRY_CHECK_AUTH_TOKEN = "expiry_check_" + AUTH_TOKEN_KEY;
    public static final String EXPIRY_DATE_AUTH_TOKEN = "expiry_date_" + AUTH_TOKEN_KEY;
    public static final String EXPIRY_CHECK_REFRESH_TOKEN = "expiry_check_" + REFRESH_TOKEN_KEY;
    public static final String EXPIRY_DATE_REFRESH_TOKEN = "expiry_date_" + REFRESH_TOKEN_KEY;
}

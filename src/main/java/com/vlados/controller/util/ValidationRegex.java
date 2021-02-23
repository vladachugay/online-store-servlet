package com.vlados.controller.util;

public class ValidationRegex {
    public static final String FULL_NAME_REGEX = "^[^\\d]{2,60}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    public static final String USERNAME_REGEX = "^[^\\s]{4,20}$";
    public static final String PASSWORD_REGEX = "^[-=+_a-zA-Z\\d]{4,}$";
    public static final String PHONE_REGEX = "[-+_()\\d]{9,20}";


    public static final String PRODUCT_NAME_REGEX = "^[\\s\\S]+$";

}

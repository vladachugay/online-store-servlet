package com.vlados.controller.util;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.util.ExceptionKeys;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Validator {

    public static Map<String, String> validateUser(UserDTO userDTO) {
        Map<String, String> errors = new HashMap<>();

        if (!userDTO.getFullName().matches(ValidationRegex.FULL_NAME_REGEX)) {
            //TODO log
            errors.put("full_name", ExceptionKeys.WRONG_FULL_NAME);
        }

        if (!userDTO.getEmail().matches(ValidationRegex.EMAIL_REGEX)) {
            //TODO log
            errors.put("email", ExceptionKeys.WRONG_EMAIL);
        }

        if (!userDTO.getUsername().matches(ValidationRegex.USERNAME_REGEX)) {
            //TODO log
            errors.put("username", ExceptionKeys.WRONG_USERNAME);
        }

        if (!userDTO.getPassword().matches(ValidationRegex.PASSWORD_REGEX)) {
            //TODO log
            errors.put("password", ExceptionKeys.WRONG_PASSWORD_FOR_REGISTRATION);
        }

        if (!userDTO.getPhoneNumber().matches(ValidationRegex.PHONE_REGEX)) {
            //TODO log
            errors.put("phone_number", ExceptionKeys.WRONG_PHONE);
        }

        return errors;
    }

    public static Map<String, String> validateProducts(ProductDTO productDTO) {
        Map<String, String> errors = new HashMap<>();

        if (productDTO.getName().trim().isEmpty()) {
            //TODO log
            errors.put("name", ExceptionKeys.WRONG_PRODUCT_NAME);
        }

        if (productDTO.getAmount() < 0) {
            //TODO log
            errors.put("amount", ExceptionKeys.WRONG_PRODUCT_AMOUNT);
        }

        if (productDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0
                || productDTO.getPrice().compareTo(BigDecimal.valueOf(100000)) > 0) {
            //TODO log
            errors.put("price", ExceptionKeys.WRONG_PRODUCT_PRICE);
        }

        if (!Arrays.asList(Product.Category.values()).contains(productDTO.getCategory())) {
            //TODO log
            errors.put("category", ExceptionKeys.WRONG_PRODUCT_CATEGORY);
        }

        if (!Arrays.asList(Product.Material.values()).contains(productDTO.getMaterial())) {
            //TODO log
            errors.put("material", ExceptionKeys.WRONG_PRODUCT_MATERIAL);
        }

        return errors;
    }
}

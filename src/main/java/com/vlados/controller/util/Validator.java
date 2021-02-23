package com.vlados.controller.util;

import com.vlados.model.dto.UserDTO;
import com.vlados.model.util.ExceptionKeys;

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
}

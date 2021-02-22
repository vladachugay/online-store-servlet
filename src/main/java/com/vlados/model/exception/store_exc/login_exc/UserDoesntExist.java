package com.vlados.model.exception.store_exc.login_exc;

import com.vlados.model.exception.store_exc.LoginException;

public class UserDoesntExist extends LoginException {
    public UserDoesntExist(String message) {
        super(message);
    }
}

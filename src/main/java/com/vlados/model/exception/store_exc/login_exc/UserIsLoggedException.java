package com.vlados.model.exception.store_exc.login_exc;

import com.vlados.model.exception.store_exc.LoginException;

public class UserIsLoggedException extends LoginException {
    public UserIsLoggedException(String message) {
        super(message);
    }
}

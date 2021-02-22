package com.vlados.model.exception.store_exc.login_exc;

import com.vlados.model.exception.store_exc.LoginException;

public class UserIsLockedException extends LoginException {
    public UserIsLockedException(String message) {
        super(message);
    }
}

package com.vlados.model.exception.store_exc.login_exc;

import com.vlados.model.exception.store_exc.LoginException;

public class WrongPasswordException  extends LoginException {
    public WrongPasswordException(String message) {
        super(message);
    }
}

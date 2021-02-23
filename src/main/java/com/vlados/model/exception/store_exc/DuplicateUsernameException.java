package com.vlados.model.exception.store_exc;

import com.vlados.model.exception.StoreException;

public class DuplicateUsernameException extends StoreException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}

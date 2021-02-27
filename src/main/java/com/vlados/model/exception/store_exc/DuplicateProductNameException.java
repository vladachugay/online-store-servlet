package com.vlados.model.exception.store_exc;

import com.vlados.model.exception.StoreException;

public class DuplicateProductNameException extends StoreException {
    public DuplicateProductNameException(String message) {
        super(message);
    }
}

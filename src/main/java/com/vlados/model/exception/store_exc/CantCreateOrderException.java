package com.vlados.model.exception.store_exc;

import com.vlados.model.exception.StoreException;

public class CantCreateOrderException extends StoreException {
    public CantCreateOrderException(String message) {
        super(message);
    }
}

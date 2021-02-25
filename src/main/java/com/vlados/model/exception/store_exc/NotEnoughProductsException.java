package com.vlados.model.exception.store_exc;

import com.vlados.model.exception.StoreException;

public class NotEnoughProductsException extends StoreException {
    public NotEnoughProductsException(String msg) {
        super(msg);
    }
}

package com.vlados.model.exception.store_exc;

import com.vlados.model.exception.StoreException;

public class CantDeleteBecauseOfOrderException extends StoreException {
    public CantDeleteBecauseOfOrderException(String message) {
        super(message);
    }
}

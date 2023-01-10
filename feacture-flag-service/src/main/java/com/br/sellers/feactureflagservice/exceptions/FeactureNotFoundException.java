package com.br.sellers.feactureflagservice.exceptions;

public class FeactureNotFoundException extends RuntimeException{
    public FeactureNotFoundException() {
        super("Feature not found.");
    }

    public FeactureNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

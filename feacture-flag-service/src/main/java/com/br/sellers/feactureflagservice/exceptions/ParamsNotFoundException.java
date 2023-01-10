package com.br.sellers.feactureflagservice.exceptions;

public class ParamsNotFoundException extends RuntimeException{
    public ParamsNotFoundException() {
        super("Parameters not matching.");
    }

    public ParamsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

package com.fass.estacionamentoapi.exceptions;

public class EntityNotFoundExceptionCustom extends RuntimeException {
    public EntityNotFoundExceptionCustom(String msg) {
     super(msg);
    }
}

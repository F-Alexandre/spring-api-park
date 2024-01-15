package com.fass.estacionamentoapi.exceptions;

public class PasswordNotEqualsExceptions extends RuntimeException {
    public PasswordNotEqualsExceptions(String senhasNaoSaoIguais) {
     super(senhasNaoSaoIguais);
    }
}

package com.braincustom.clientes.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    public List<String> errors;

    //construtor que recebe uma lista como paramentros
    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }
}

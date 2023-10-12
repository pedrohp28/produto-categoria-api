package com.example.ac2.exceptions;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrorDto {
    @Getter
    private List<String> errors;
    public ApiErrorDto(String mensagem){
        this.errors = Arrays.asList(mensagem);
    }
}

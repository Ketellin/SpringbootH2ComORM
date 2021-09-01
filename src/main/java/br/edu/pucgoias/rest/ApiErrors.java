package br.edu.pucgoias.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.lang.annotation.Annotation;

/**
 * Classe com a responsabilidade de padronizar um ou vários erros em um array
 */
@Data
public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList((mensagemErro)); //asList é um método estático que transformará
                                                     //mensagemErro em um ArrayList
    }

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }
}

package br.com.masterclass.superpecas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.masterclass.superpecas.exception.ErroException;
import br.com.masterclass.superpecas.exception.RecordNotFoundException;

@RestControllerAdvice

public class ApplicationControllerAdvice {

    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public String handleRecordNotFoundException(RecordNotFoundException ex){             
        return ex.getMessage();
    } 
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ErroException.class)
    public String handleErroException(ErroException ex){             
        return ex.getMessage();
    }    
}

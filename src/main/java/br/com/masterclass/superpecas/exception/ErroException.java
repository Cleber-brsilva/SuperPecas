package br.com.masterclass.superpecas.exception;


public class ErroException extends RuntimeException {
    public ErroException(String message) {
        super(message);
    }
}
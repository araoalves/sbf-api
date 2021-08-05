package com.desafio.sbf.exception;

@SuppressWarnings("serial")
public class ConsumerException extends Exception {

    public ConsumerException(String message){super(message);}
    public ConsumerException(Throwable t, String message) {
        super(message,t);
    }
}

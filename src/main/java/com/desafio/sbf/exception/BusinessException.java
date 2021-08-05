package com.desafio.sbf.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

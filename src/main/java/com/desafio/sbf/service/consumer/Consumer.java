package com.desafio.sbf.service.consumer;

import java.util.Map;

import org.springframework.http.MediaType;

import com.desafio.sbf.exception.ConsumerException;

public interface Consumer {

	@SuppressWarnings("rawtypes")
	Object get(String url, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
    
    @SuppressWarnings("rawtypes")
	Object post(String url, Object objToSend, Class typeReturnExpected, MediaType mediaType, Map<String, String> headers) throws ConsumerException;
    
}

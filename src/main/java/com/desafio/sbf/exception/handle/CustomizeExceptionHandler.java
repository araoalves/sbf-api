package com.desafio.sbf.exception.handle;

import com.desafio.sbf.exception.BusinessException;
import com.desafio.sbf.model.ExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleAllException(BusinessException ex, WebRequest request) throws Exception {
    	
        ExceptionModel exceptionModel =  ExceptionModel
                .builder()
                .datestamp(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Object> handleAllException(BadCredentialsException ex, WebRequest request) throws Exception {
    	
    	String retornoErro = null;
    	
    	if(ex.getMessage().contains("Bad credentials")) {
    		retornoErro = "Usuário ou senha inválidos!";
    	}
    	
        ExceptionModel exceptionModel =  ExceptionModel
                .builder()
                .datestamp(new Date())
                .message((retornoErro != null)? retornoErro: ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .build();
        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionModel exceptionModel =  ExceptionModel
                .builder()
                .datestamp(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionModel exceptionModel =  ExceptionModel
                .builder()
                .datestamp(new Date())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }
}

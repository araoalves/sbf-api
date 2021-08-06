package com.desafio.sbf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.sbf.exception.BusinessException;
import com.desafio.sbf.model.payload.request.LoginRequest;
import com.desafio.sbf.model.payload.request.SignupRequest;
import com.desafio.sbf.service.jwt.JwtUserDetailsService;


@RestController
@CrossOrigin
@RequestMapping(value = "/authentication")
public class AuthenticationController {	
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {				
		try {
			return ResponseEntity.ok(userDetailsService.login(loginRequest));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		try {
			return new ResponseEntity<>(userDetailsService.save(signUpRequest), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}	
	}

}
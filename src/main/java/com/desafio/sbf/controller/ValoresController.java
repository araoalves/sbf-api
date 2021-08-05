package com.desafio.sbf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.sbf.business.ValoresBO;
import com.desafio.sbf.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/valores")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValoresController {
	
	private final ValoresBO valoresBO;
	
	@RequestMapping(value = "/recuperarCotacaoExterna/{valor}", method = RequestMethod.GET)
	public ResponseEntity<?> recuperarCotacaoExterna(@PathVariable("valor") Double valor) throws BusinessException {		
		try {
			return new ResponseEntity<>(valoresBO.recuperar(valor), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}

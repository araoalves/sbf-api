package com.desafio.sbf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.sbf.business.ValoresBO;
import com.desafio.sbf.exception.BusinessException;
import com.desafio.sbf.model.CotacaoInterna;
import com.desafio.sbf.model.Valor;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/valores")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValoresController {
	
	private final ValoresBO valoresBO;
	
	@RequestMapping(value = "/recuperarCotacaoExterna/{valor}", method = RequestMethod.GET)
	public ResponseEntity<List<Valor>> recuperarCotacaoExterna(@PathVariable("valor") Double valor) throws BusinessException {		
		try {
			return new ResponseEntity<>(valoresBO.recuperar(valor), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/recuperarCotacaoInterna/{valor}", method = RequestMethod.GET)
	public ResponseEntity<List<Valor>> recuperarCotacaoInterna(@PathVariable("valor") Double valor) throws BusinessException {		
		try {
			return new ResponseEntity<>(valoresBO.recuperarCotacaoInterna(valor), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value = "/cotacaoAutenticada/{valor}", method = RequestMethod.GET)
	public ResponseEntity<List<Valor>> cotacaoAutenticada(@PathVariable("valor") Double valor) throws BusinessException {		
		try {
			return new ResponseEntity<>(valoresBO.recuperarCotacaoInterna(valor), HttpStatus.OK);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value = "/cadastrarCotacao", method = RequestMethod.POST)
	public ResponseEntity<CotacaoInterna> cadastrarCotacao(@RequestBody CotacaoInterna cotacaoInterna) throws Exception {				
		try {
			return ResponseEntity.ok(valoresBO.cadastrarCotacao(cotacaoInterna));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}

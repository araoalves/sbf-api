package com.desafio.sbf.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.desafio.sbf.exception.BusinessException;
import com.desafio.sbf.exception.ConsumerException;
import com.desafio.sbf.model.CotacaoInterna;
import com.desafio.sbf.model.Valor;
import com.desafio.sbf.model.cotacao.CotacaoExterna;
import com.desafio.sbf.repository.CotacaoRepository;
import com.desafio.sbf.service.consumer.Consumer;
import com.desafio.sbf.service.util.Utilitarios;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValoresBO {
	
	private final Consumer consumer;
	private final Utilitarios utilitarios;
	private final CotacaoRepository cotacaoRepository;
	
	public List<Valor> recuperar(Double valor) throws ConsumerException, Exception{
		
		CotacaoExterna cotacao = (CotacaoExterna) consumer.get("https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,INR-BRL", CotacaoExterna.class, MediaType.APPLICATION_JSON, utilitarios.criarCabecalhoRequisicao());						
		
		List<Valor> valores = new ArrayList<>();
		valores.add(new Valor("USD", (utilitarios.calcularValor(valor, cotacao.getUSDBRL().getHigh()))));
		valores.add(new Valor("EUR", (utilitarios.calcularValor(valor, cotacao.getEURBRL().getHigh()))));
		valores.add(new Valor("INR", (utilitarios.calcularValor(valor, cotacao.getINRBRL().getHigh()))));
	
		return valores;
	}	
	
	public List<Valor> recuperarCotacaoInterna(Double valor) throws BusinessException{
		
		List<CotacaoInterna> cotacao = cotacaoRepository.findAll();
		List<Valor> valores = new ArrayList<>();
		
		cotacao.forEach( c ->{
			valores.add(new Valor(c.getMoeda(), (utilitarios.calcularValor(valor, c.getValor().toString()))));
		});
		
		return valores;
	}

	public CotacaoInterna cadastrarCotacao(CotacaoInterna cotacaoInterna) {
		return cotacaoRepository.save(cotacaoInterna);
	}

}

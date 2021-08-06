package com.desafio.sbf.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;

import com.desafio.sbf.model.CotacaoInterna;
import com.desafio.sbf.model.cotacao.CotacaoExterna;
import com.desafio.sbf.repository.CotacaoRepository;
import com.desafio.sbf.service.consumer.Consumer;
import com.desafio.sbf.service.util.Utilitarios;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtualizarContacao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private final Consumer consumer;
	private final Utilitarios utilitarios;
	private final CotacaoRepository cotacaoRepository;
	
	@Scheduled(fixedRate=1*60*1000) // rodar de 1 em 1 munuto
	public void atualizarCotacao() {
		this.logger.info("Rodando Job poara atualizar cotação: "+ dateFormat.format(new Date()));
		
		try {
			CotacaoExterna cotacaoExterna = (CotacaoExterna) consumer.get("https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,INR-BRL", CotacaoExterna.class, MediaType.APPLICATION_JSON, utilitarios.criarCabecalhoRequisicao());
			
			CotacaoInterna cataocaoUsd = cotacaoRepository.getByMoeda("USD");
			cataocaoUsd.setValor(Double.parseDouble(cotacaoExterna.getUSDBRL().getHigh()));
			cotacaoRepository.save(cataocaoUsd);
			
			CotacaoInterna cataocaoEuro = cotacaoRepository.getByMoeda("EUR");
			cataocaoEuro.setValor(Double.parseDouble(cotacaoExterna.getEURBRL().getHigh()));
			cotacaoRepository.save(cataocaoEuro);
			
			CotacaoInterna cataocaoInr = cotacaoRepository.getByMoeda("INR");
			cataocaoInr.setValor(Double.parseDouble(cotacaoExterna.getINRBRL().getHigh()));
			cotacaoRepository.save(cataocaoInr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

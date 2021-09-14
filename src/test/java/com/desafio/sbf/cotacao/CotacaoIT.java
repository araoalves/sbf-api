package com.desafio.sbf.cotacao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafio.sbf.model.CotacaoInterna;
import com.desafio.sbf.repository.CotacaoRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CotacaoIT {
    
	private final CotacaoRepository cotacaoRepository;
	
    @Test
    public void deveVerificarSeExisteCotacoesNaBase() throws Exception {    	
    	List<CotacaoInterna> cotacoes = cotacaoRepository.findAll();    
    	Assertions.assertTrue(cotacoes.size() > 0);
    }
}


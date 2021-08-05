package com.desafio.sbf.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.desafio.sbf.model.cotacao.CotacaoExterna;
import com.desafio.sbf.service.consumer.Consumer;
import com.desafio.sbf.service.util.Utilitarios;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumerTest {

	private final Utilitarios utilitarios;
    private final Consumer consumer;

    @Test
    public void deveConsumirApiExterna() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("header1","value1");
        headers.put("header2", "value2");

        TestJson o = (TestJson) consumer.get("http://echo.jsontest.com/key/value/one/two", TestJson.class, MediaType.APPLICATION_JSON, headers);
        Assertions.assertNotNull(o);
    }
    
    @Test
    public void deveConsumirCotacaoApiExterna() throws Exception {
    	CotacaoExterna cotacao = (CotacaoExterna) consumer.get("https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,INR-BRL", CotacaoExterna.class, MediaType.APPLICATION_JSON, utilitarios.criarCabecalhoRequisicao());
        Assertions.assertNotNull(cotacao);
    }
}

@Data
@ToString
class TestJson{
    private String one;
    private String key;
}


package com.desafio.sbf.service.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Utilitarios {

	public Map<String, String> criarCabecalhoRequisicao() throws Exception{
		try {
			Map<String, String> headers = new HashMap<>();
			return headers;
		}catch (Exception ex){
			throw new Exception(ex.getMessage(), ex);
		}
	}
	
}

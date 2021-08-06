package com.desafio.sbf.service.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Utilitarios {

	public String formatarValores(Double pValor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(pValor);
    }	
	
	public Map<String, String> criarCabecalhoRequisicao() throws Exception{
		try {
			Map<String, String> headers = new HashMap<>();
			return headers;
		}catch (Exception ex){
			throw new Exception(ex.getMessage(), ex);
		}
	}

	public String calcularValor(Double valor, String cotacao) {		
		return this.formatarValores(valor / Double.parseDouble(cotacao));
	}
	
}

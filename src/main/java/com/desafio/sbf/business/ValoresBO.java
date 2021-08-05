package com.desafio.sbf.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.sbf.exception.ConsumerException;
import com.desafio.sbf.model.Valor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValoresBO {
	
	public List<Valor> recuperar(Double valor) throws ConsumerException, Exception{
	
		return null;
	}	

}

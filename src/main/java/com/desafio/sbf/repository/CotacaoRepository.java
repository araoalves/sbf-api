package com.desafio.sbf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.sbf.model.CotacaoInterna;

public interface CotacaoRepository extends JpaRepository<CotacaoInterna, Long>{

	CotacaoInterna getByMoeda(String moeda) throws Exception;

}

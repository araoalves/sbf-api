package com.desafio.sbf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cotacao")
public class CotacaoInterna {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cotacao_gen")
	@SequenceGenerator(name = "sq_cotacao_gen", sequenceName = "sq_cotacao",initialValue = 1, allocationSize = 1)
	@Column(name = "cotacao_id", nullable = false)
	private Long id;
	
	@Column(name = "moeda", length=3)
	private String moeda;
	
	@Column(name = "valor")
	private Double valor;
	
}

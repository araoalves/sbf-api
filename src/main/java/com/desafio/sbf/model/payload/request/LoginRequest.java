package com.desafio.sbf.model.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank
	private String usuario;

	@NotBlank
	private String password;

}

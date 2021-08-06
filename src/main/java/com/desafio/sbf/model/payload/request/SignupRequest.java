package com.desafio.sbf.model.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupRequest {	
	
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank
	@Size(max = 100)
	private String descricao;
    
    @NotBlank
	@Size(max = 1)
	private String status;
    
    private Set<String> role;    
 
}

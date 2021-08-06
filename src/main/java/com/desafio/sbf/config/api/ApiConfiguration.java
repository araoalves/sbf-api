package com.desafio.sbf.config.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.desafio.sbf.enums.ERole;
import com.desafio.sbf.model.Role;
import com.desafio.sbf.repository.RoleRepository;

@Configuration
public class ApiConfiguration {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }	
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	roleRepository.save(new Role(1,ERole.ROLE_ADMIN));
	    	roleRepository.save(new Role(2,ERole.ROLE_MODERATOR));
	    	roleRepository.save(new Role(3,ERole.ROLE_USER));
	      };
	 }	
}

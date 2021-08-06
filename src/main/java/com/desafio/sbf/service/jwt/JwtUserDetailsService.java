package com.desafio.sbf.service.jwt;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.sbf.enums.ERole;
import com.desafio.sbf.model.Role;
import com.desafio.sbf.model.Usuario;
import com.desafio.sbf.model.payload.request.SignupRequest;
import com.desafio.sbf.repository.RoleRepository;
import com.desafio.sbf.repository.UserRepository;

@Service
public class JwtUserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;	

	public Usuario save(@Valid SignupRequest signUpRequest) throws Exception {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new Exception("Usuário já existente!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new Exception("E-mail já existente!");
		}

		return createdUser(signUpRequest);
	}
	
	public void loadUser(@Valid SignupRequest signUpRequest) {
		if (!(userRepository.existsByUsername(signUpRequest.getUsername()) || userRepository.existsByEmail(signUpRequest.getEmail()))) {
			createdUser(signUpRequest);
		}
	}
	
	public Usuario createdUser(@Valid SignupRequest signUpRequest) {
		
				Calendar calendar = Calendar.getInstance();
		
				Usuario user = new Usuario();
				user.setUsername(signUpRequest.getUsername());
				user.setEmail(signUpRequest.getEmail());
				user.setPassword(encoder.encode(signUpRequest.getPassword()));
				user.setDescricao(signUpRequest.getDescricao());
				user.setStatus(signUpRequest.getStatus());
				user.setCreatedAt(calendar.getTime());
				

				Set<String> strRoles = signUpRequest.getRole();
				Set<Role> roles = new HashSet<>();

				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "ROLE_ADMIN":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						case "ROLE_MODERATOR":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
						}
					});
				}

				user.setRoles(roles);				
				Usuario usuario = userRepository.save(user);				
				
				return usuario;
	}

}

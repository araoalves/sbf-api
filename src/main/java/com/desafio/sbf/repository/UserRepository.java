package com.desafio.sbf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.sbf.model.Usuario;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM public.minha_conta_usuario where username = :usuario", nativeQuery = true)
	Usuario listarDadosMinhaConta(@Param("usuario") String usuario);
}

package com.desafio.sbf.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(	name = "usuario", 
		uniqueConstraints = {
			@UniqueConstraint(name = "idx_username", columnNames = "username"),
			@UniqueConstraint(name = "idx_minha_conta_usuario_Email", columnNames = "email") 
		})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario_gen")
	@SequenceGenerator(name = "sq_usuario_gen", sequenceName = "sq_usuario",initialValue = 1, allocationSize = 1)
	@Column(name = "id_usuario", nullable = false)
	private Long id;

	@Column(name = "descricao", length=100, nullable = false)
	private String descricao;
	
	@Column(name = "username", length=100, nullable = false)
	private String username;

	@Column(name = "password")
	private String password;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name = "status", length=1, nullable = false)
	private String status;
	
	@Email
	@Column(name = "email", length=50)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}

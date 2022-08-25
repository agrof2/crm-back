package com.crm.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa implements EntityBasic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@NotNull
	private String nome;
	private Sexo sexo;
	private String email;
	private LocalDate dataNascimento;
	private String naturalidade;
	private String nacionalidade;
	@NotNull
	@Column(unique = true)
	private String cpf;
	@Column(columnDefinition = "boolean null default false")
	private boolean deleted;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
}

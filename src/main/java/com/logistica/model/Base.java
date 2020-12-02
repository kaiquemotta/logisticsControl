package com.logistica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

/**
 * Base
 * 
 * @see
 */
@Getter
@Setter
@Entity
@Table(name = "base")
@JsonInclude(Include.NON_NULL)
public class Base implements Serializable, Registro {
  
	private static final long serialVersionUID = 54581584231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String nomeBase;
	private Long codBase;
	private String cidade;
	private String UF;
	private String longitude;
	private String latitude;
	private String elevacao;
	private String endereco;
	private String bairro;
	private String numero;
	private String placaId;
 	@OneToMany(mappedBy = "base")
	private List<Motorista>motoristas;
	@OneToMany(mappedBy = "base",fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Veiculo> veiculos;
	private String cep;
	private LocalDateTime alteradoEm;
	private String alteradoPor;
	private LocalDateTime criandoEm;
	private String criadoPor;



	@Override
	public String getCriadoPor() {
		return this.criadoPor;
	}

	@Override
	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	@Override
	public String getAlteradoPor() {
		return this.alteradoPor;
	}

	@Override
	public void setAlteradoPor(String alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	@Override
	public LocalDateTime getCriadoEm() {
		return this.criandoEm;
	}

	@Override
	public void setCriadoEm(LocalDateTime date) {
		this.criandoEm = date;
	}

	@Override
	public void setAlteradoEm(LocalDateTime date) {
		this.alteradoEm = date;
	}

	@Override
	public LocalDateTime getAlteradoEm() {
		return this.alteradoEm;
	}

//
//	@Override
//	public String toString() {
//		return "Base [idBase=" + idBase + ", nomeBase=" + nomeBase + ", codBase=" + codBase + ", cidade=" + cidade
//				+ ", UF=" + UF + ", criandoEm=" + criandoEm + "]";
//	}

}

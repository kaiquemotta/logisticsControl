package com.logistica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

/**
 * Motorista
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "motorista", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf" }))
public class Motorista implements Serializable, Registro {

	private static final long serialVersionUID = 31231317251L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String categoriaCnh;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	@NotBlank
	private String celular;
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "id_base")
	private Base base;
	@OneToOne
	private Viagem viagem;
//	@OneToMany(mappedBy = "motorista")
//	private List<Tarefa> tarefas;
	private String vinculoMotorista;
	private String senhaExpirada;
	private StatusMotorista statusMotorista;
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

	@Override
	public String toString() {
		return "Motorista [id=" + id + ", base=" + base + ", nome=" + nome + ", cpf=" + cpf + ", categoriaCnh="
				+ categoriaCnh + ", dataNascimento=" + dataNascimento + ", celular=" + celular + ", criandoEm="
				+ criandoEm + "]";
	}

}

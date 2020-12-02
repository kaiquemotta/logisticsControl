package com.logistica.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TarefaOcorrencia
 */
@Getter
@Setter
public class TarefaOcorrencia implements Serializable, Registro {

	private static final long serialVersionUID = 1L;
	private Tarefa tarefa;
	private Ocorrencia ocorrencia;
	private byte[] foto;
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

}

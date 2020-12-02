package com.logistica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * CheckPoint
 */
public class CheckPoint implements Serializable, Registro {

	private static final long serialVersionUID = 1L;
	private Integer idCheckPoint;
	private String descricao;
	private DescricaoCheckPoint descricaoCheckPoint;
	private TipoOcorrencia tipoOCOrrencia;
	private LocalDateTime alteradoEm;
	private String alteradoPor;
	private LocalDateTime criandoEm;
	private String criadoPor;

	public Integer getIdCheckPoint() {
		return idCheckPoint;
	}

	public void setIdCheckPoint(Integer idCheckPoint) {
		this.idCheckPoint = idCheckPoint;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public DescricaoCheckPoint getDescricaoCheckPoint() {
		return descricaoCheckPoint;
	}

	public void setDescricaoCheckPoint(DescricaoCheckPoint descricaoCheckPoint) {
		this.descricaoCheckPoint = descricaoCheckPoint;
	}

	public TipoOcorrencia getTipoOCOrrencia() {
		return tipoOCOrrencia;
	}

	public void setTipoOCOrrencia(TipoOcorrencia tipoOCOrrencia) {
		this.tipoOCOrrencia = tipoOCOrrencia;
	}

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

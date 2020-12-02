package com.logistica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Carga
 */
@Getter
@Setter
@Entity
@Table(name = "carga")
@JsonInclude(Include.NON_NULL)
public class Carga implements Serializable, Registro {

    private static final long serialVersionUID = 5451234181584231L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarga;
    private String notaFiscal;
    private Integer volumeCarga;
    private Double peso;
    @JsonIgnore
    @OneToOne(mappedBy = "carga")
    private Tarefa tarefa;
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

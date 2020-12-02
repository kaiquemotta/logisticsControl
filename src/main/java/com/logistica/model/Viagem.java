package com.logistica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

/**
 * Viagem
 */
@Entity
@Table(name = "viagem")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Viagem implements Serializable, Registro {

    private static final long serialVersionUID = 13112314131541L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private StatusViagem statusViagem;
    @OneToMany(mappedBy = "viagem")
    private List<Tarefa> tarefas;
    @OneToOne
    private Motorista motorista;
    private LocalDateTime alteradoEm;
    private String alteradoPor;
    private LocalDateTime criandoEm;
    private String criadoPor;
    @OneToOne
    private Veiculo veiculo;


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

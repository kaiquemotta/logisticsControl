package com.logistica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

/**
 * Tarefa
 */
@Getter
@Setter
@Entity
@Table(name = "tarefa")
@JsonInclude(Include.NON_NULL)
public class Tarefa implements Serializable, Registro {

    private static final long serialVersionUID = 13131541L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    @JoinColumn(name = "id_carga")
    private Carga carga;
    @OneToOne()
    @JoinColumn(name = "id_destinatario")
    private Destinatario destinatario;
    @OneToOne()
    @JoinColumn(name = "id_remetente")
    private Remetente remetente;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_viagem")
    private Viagem viagem;
    //	@JsonBackReference
//	@ManyToOne()
//	@JoinColumn(name = "id_motorista")
//	private Motorista motorista;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime previsaoEntrega;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime alteradoEm;
    private String alteradoPor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criandoEm;
    private String criadoPor;
    private String tempoEntrega;
    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;
    private boolean status = false;

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

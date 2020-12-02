//package com.logistica.model;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "rota")
//@JsonInclude(Include.NON_NULL)
//public class Rota implements Serializable, Registro {
//
//    private static final long serialVersionUID = 54581584231L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @OneToOne
//    @JoinColumn(name ="id_tarefa")
//    private Tarefa  tarefa;
//    @OneToOne()
//    @JoinColumn(name = "id_remente")
//    private Remetente remetente;
//    @OneToOne()
//    @JoinColumn(name = "id_destinatario")
//    private Destinatario destinatario;
//
//
//
//    @Override
//    public String getCriadoPor() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setCriadoPor(String criadoPor) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public String getAlteradoPor() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setAlteradoPor(String alteradoPor) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public LocalDate getCriadoEm() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setCriadoEm(LocalDate date) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void setAlteradoEm(LocalDate date) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public LocalDate getAlteradoEm() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//}

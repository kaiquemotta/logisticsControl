package com.logistica.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.swing.text.html.Option;
import javax.validation.Valid;

import com.logistica.model.StatusTarefa;
import com.logistica.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistica.model.Viagem;
import com.logistica.repository.ViagemRepository;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private VeiculoService veiculoService;


    public void save(Viagem viagem) {
        Viagem opViagem = viagemRepository.save(viagem);
        viagem.getTarefas().stream().forEach(a -> {
            a.setViagem(opViagem);
            a.setStatusTarefa(StatusTarefa.ALOCADA);
            tarefaService.update(a,opViagem.getId());
        });
    }

}

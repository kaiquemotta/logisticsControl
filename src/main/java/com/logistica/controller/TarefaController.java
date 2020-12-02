package com.logistica.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.logistica.model.Base;
import com.logistica.model.Motorista;
import com.logistica.service.MotoristaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logistica.Response.Responser;
import com.logistica.model.Tarefa;
import com.logistica.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
@CrossOrigin(origins = "*")
public class TarefaController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private MotoristaService motoristaService;

    @RequestMapping("/")
    public String index() {

        return ("Hello World");
    }

    @GetMapping("/getTarefaMotorista/{cpf}")
    public ResponseEntity<List<Tarefa>> getOne(@PathVariable String cpf) throws IOException {
        if (cpf != null) {
            Optional<Motorista> opMotorista = motoristaService.findbYCpfCnpj(cpf);
            if (opMotorista.isPresent()) {
                List<Tarefa> listTarefa = tarefaService.listTarefaMotorista(opMotorista.get().getId());
                return ResponseEntity.ok(listTarefa);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Responser<Tarefa>> register(@Valid @RequestBody Tarefa tarefa) {
        Responser<Tarefa> response = new Responser<>();
        try {
            tarefaService.save(tarefa);
            response.setData(tarefa);
            LOG.info("A Tarefa foi salva com sucesso: " + tarefaService.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.getErrors().add(e.toString());
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tarefa>> list() {
        LOG.debug("list()");
        final List<Tarefa> list = tarefaService.list();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/emAberto")
    public ResponseEntity<List<Tarefa>> listEmAberto() {
        LOG.debug("list()");
        final List<Tarefa> list = tarefaService.listEmAberto();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/concluda")
    public ResponseEntity<List<Tarefa>> listConcluidas() {
        LOG.debug("list()");
        final List<Tarefa> list = tarefaService.listConcluidas();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/alocada")
    public ResponseEntity<List<Tarefa>> listAlocada() {
        LOG.debug("list()");
        final List<Tarefa> list = tarefaService.listAlocadas();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Responser<Tarefa>> alterar(@Valid @RequestBody Tarefa tarefa) {
        Responser<Tarefa> response = new Responser<>();
        Tarefa opTare = tarefaService.update(tarefa,0);
        if (opTare == null) {
            response.getErrors().add("Erro");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.setData(opTare);
        LOG.info("A tarefa: " + opTare);
        return ResponseEntity.ok(response);
    }
}

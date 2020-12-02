package com.logistica.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.logistica.model.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logistica.Response.Responser;
import com.logistica.model.Motorista;
import com.logistica.service.MotoristaService;

/**
 * MotoristaController
 */
@RestController
@RequestMapping("/motorista")
@CrossOrigin(origins = "*")
public class MotoristaController {


    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MotoristaService motoristaService;


    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Motorista>> list() {
        LOG.debug("list()");

        final List<Motorista> list = motoristaService.list();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("/register")
    public ResponseEntity<Responser<Motorista>> register(@RequestBody Motorista motorista) {
        LOG.debug("salvando({})", motorista);
        Responser<Motorista> response = new Responser<>();
        Motorista opMotorista = null;
        if (motorista.getBase() == null) {
            response.getErrors().add("É necessario uma base cadastrada");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        try {
            opMotorista = motoristaService.save(motorista);
            if (opMotorista == null) {
                response.getErrors().add("Problema ao inserir motorista");
                StringBuilder errosBuilder = new StringBuilder();
                response.getErrors().stream().forEach(s -> {
                    errosBuilder.append("Erros: ").append(s).append(" ");
                });
                LOG.info(errosBuilder.toString());
                return ResponseEntity.noContent().build();
            }

            response.setData(opMotorista);
            LOG.info("O motorista  foi registrado com sucesso: " + opMotorista.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Não essa Base cadstrada");
            response.getErrors().add("Não Existe essa base cadastrada");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

    }

    @GetMapping("/getCpfCnpj/{id}")
    public ResponseEntity<Motorista> getCpfCnpj(@PathVariable String id) {
        Optional<Motorista> opMotorista = motoristaService.findbYCpfCnpj(id);
        if (opMotorista.isPresent()) {
            return ResponseEntity.ok(opMotorista.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Motorista> getOne(@PathVariable long id) {
        Optional<Motorista> opMotorista = motoristaService.findByIdMotorista(id);
        if (opMotorista.isPresent()) {
            return ResponseEntity.ok(opMotorista.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Responser<Motorista>> alterar(@Valid @RequestBody Motorista Motorista) {
        LOG.debug("alterando{}", Motorista.toString());
        Responser<Motorista> response = new Responser<>();

        Optional<Motorista> opMotorista = motoristaService.update(Motorista);
        if (!opMotorista.isPresent()) {
            response.getErrors().add("Não existe esse Motorotista:");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.setData(opMotorista.get());
        LOG.info("O motorista foi alterada com sucesso: " + opMotorista.get().toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete/{idmotorista}")
    public ResponseEntity<Responser<Motorista>> delete(@PathVariable Long idmotorista) {
        Optional<Motorista> opMotorista = null;
        Responser<Motorista> response = new Responser<>();

        try {
            opMotorista = motoristaService.findByIdMotorista(idmotorista);
            LOG.debug("deleting({" + opMotorista.get().toString() + "})");

        } catch (Exception e) {
            response.getErrors().add("Não foi possivel achar esse registro em nossa base de Dados");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        try {
            response.setMensagens("Registro excluido com Sucesso.");
            motoristaService.delete(opMotorista);
            LOG.info("Regristro excluido!", opMotorista.get().toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } catch (Exception e) {

            response.getErrors().add("Registro invalido");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }


}

package com.logistica.controller;

import javax.validation.Valid;

import com.logistica.model.StatusViagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logistica.Response.Responser;
import com.logistica.model.Viagem;
import com.logistica.service.ViagemService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/viagem")
@CrossOrigin("*")
public class ViagemController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private ViagemService viagemService;

    @RequestMapping("/")
    public String index() {

        return ("Hello World");
    }

    @PostMapping("/register")
    public ResponseEntity<Responser<Viagem>> register(@Valid @RequestBody Viagem viagem) {
        Responser<Viagem> response = new Responser<>();

        try {
            viagem.setCriadoPor("Kaique");
            viagem.setCriadoEm(LocalDateTime.now());
            viagem.setStatusViagem(StatusViagem.ABERTA);
            viagemService.save(viagem);
            response.setData(viagem);
            LOG.info("A Viagem foi registrada com sucesso: " + viagem.toString());
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
}

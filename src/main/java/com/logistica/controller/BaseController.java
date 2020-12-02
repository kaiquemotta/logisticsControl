package com.logistica.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.logistica.Response.Responser;
import com.logistica.model.Base;
import com.logistica.service.BaseService;

/**
 * BaseController
 */
@RestController
@RequestMapping("/base")
@CrossOrigin(origins = "*")
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private BaseService baseService;

    @RequestMapping("/")
    public String index() {
        return ("Hello World");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Base>> list() {
        LOG.debug("list()");
        final List<Base> list = baseService.list();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getOne/{idBase}")
    public ResponseEntity<Base> getOne(@PathVariable long idBase) {
        Optional<Base> opBase = baseService.findByIdBase(idBase);
        if (opBase.isPresent()) {
            return ResponseEntity.ok(opBase.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{idBase}")
    public ResponseEntity<Responser<Void>> delete(@PathVariable String idBase) {

        Optional<Base> opBase = null;
        Responser<Base> response = new Responser<>();
        try {
            opBase = baseService.findByIdBase(Long.parseLong(idBase));
            LOG.debug("deleting({" + opBase.get().toString() + "})");
            if (opBase.isPresent()) {
                baseService.delete(opBase);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            response.getErrors().add("Não foi possivel achar esse registro em nossa base de Dados");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Responser<Base>> register(@Valid @RequestBody Base base) throws IOException {
        Responser<Base> response = new Responser<>();
        baseService.save(base);
        response.setData(base);
        LOG.info("A base foi salva com sucesso: " + base.toString());
        return ResponseEntity.ok(response);

    }

    @PutMapping(path = "/update")
    public ResponseEntity<Responser<Base>> alterar(@Valid @RequestBody Base base) {
        LOG.debug("alterando{}", base.toString());
        Responser<Base> response = new Responser<>();

        Optional<Base> opBase = baseService.update(base);
        if (!opBase.isPresent()) {
            response.getErrors().add("Não existe essa base:");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.setData(opBase.get());
        LOG.info("A Base foi alterada com sucesso: " + opBase.get().toString());
        return ResponseEntity.ok(response);
    }

}

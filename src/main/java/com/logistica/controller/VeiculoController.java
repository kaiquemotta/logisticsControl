package com.logistica.controller;

import com.logistica.Response.ApiUtil;
import com.logistica.Response.Responser;
import com.logistica.model.Motorista;
import com.logistica.model.Veiculo;
import com.logistica.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins = "http://localhost:4200")
public class VeiculoController {

    private static final Logger LOG = LoggerFactory.getLogger(VeiculoController.class);

    @Autowired
    private VeiculoService veiculoService;


    @RequestMapping("/")
    public String index() {
        return ("Hello World");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Veiculo>> list() {
        LOG.debug("list()");
        final List<Veiculo> list = veiculoService.list();
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Veiculo> getOne(@PathVariable long id) {
        Optional<Veiculo> opVeiculo = veiculoService.findById(id);
        if (opVeiculo.isPresent()) {
            return ResponseEntity.ok(opVeiculo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping(path = "/delete/{idVeiculo}")
    public ResponseEntity<Responser<Veiculo>> delete(@PathVariable Long idVeiculo) {
        Optional<Veiculo> opVeiculo = null;
        Responser<Veiculo> response = new Responser<>();
        try {
            opVeiculo = veiculoService.findById(idVeiculo);
            LOG.debug("deleting({" + opVeiculo.get().toString() + "})");
        } catch (Exception e) {
            response.getErrors().add("Não foi possivel achar esse registro em nossa base de Dados");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.ok(response);
        }

        try {
            veiculoService.delete(opVeiculo);
            LOG.info("Regristro excluido!", opVeiculo.get().toString());
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

    @PostMapping("/register")
    public ResponseEntity<Responser<Veiculo>> register(@Valid @RequestBody Veiculo veiculo) {
        if (veiculo != null) {
            Veiculo veiculoo = veiculoService.save(veiculo);
            if (veiculoo != null) {
                return ResponseEntity.ok(ApiUtil.criaResponseBody(veiculoo));
            }
        }
        return ResponseEntity.badRequest().body(ApiUtil.criarResponseDeErro("objeto null"));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Responser<Veiculo>> alterar(@Valid @RequestBody Veiculo veiculo) {
        LOG.debug("alterando{}", veiculo.toString());
        Responser<Veiculo> response = new Responser<>();
        Optional<Veiculo> opVeiculo = veiculoService.update(veiculo);
        if (!opVeiculo.isPresent()) {
            response.getErrors().add("Não existe essa base:");
            StringBuilder errosBuilder = new StringBuilder();
            response.getErrors().stream().forEach(s -> {
                errosBuilder.append("Erros: ").append(s).append(" ");
            });
            LOG.info(errosBuilder.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        response.setData(opVeiculo.get());
        LOG.info("A Base foi alterada com sucesso: " + opVeiculo.get().toString());
        return ResponseEntity.ok(response);
    }
}

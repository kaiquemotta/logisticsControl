package com.logistica.controller;

import com.logistica.model.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.logistica.service.UtilRotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/util")
@CrossOrigin(origins = "*")
public class UtilRequestRota {
    @Autowired
    private UtilRotaService utilRotaService;

    @GetMapping("/getAddress/{cep}")
    public ResponseEntity<?> getAdrressWithCep(@PathVariable String cep) throws IOException {
        if (cep.length() == 8) {
            Data data = new Data();
//            Northeast n = new Northeast();
//            n.setLat("-23.5268154");
//            n.setLng("-47.1534693");
//            Bounds b = new Bounds();
//            Geometry g = new Geometry();
//            b.setNortheast(n);
//            g.setBounds(b);
//            List<Results> r = new ArrayList<>();
//            Results rr = new Results();
//            rr.setGeometry(g);
//            r.add(rr);
//            data.setResults(r);
//            Base base = new Base();
//            base.setCep(cep);
//            base.setLatitude(n.getLat());
//            base.setLongitude(n.getLng());
//            base.setCidade("São Roque");
//            base.setUF("SP");
//            base.setEndereco("Jardim Marieta");
//            base.setBairro("Jardim Marieta");

            data = utilRotaService.consultaLatitudeLongitude(cep);
            Base base1 = new Base();
            base1.setCep(data.getResults().get(0).getAddress_components().get(0).getLong_name());
            base1.setLatitude(data.getResults().get(0).getGeometry().getLocation().getLat());
            base1.setLongitude(data.getResults().get(0).getGeometry().getLocation().getLng());
            base1.setCidade(data.getResults().get(0).getAddress_components().get(2).getLong_name());
            base1.setUF(data.getResults().get(0).getAddress_components().get(3).getShort_name());
            base1.setEndereco(data.getResults().get(0).getAddress_components().get(1).getLong_name());
                 base1.setBairro(data.getResults().get(0).getAddress_components().get(1).getLong_name());
            if (base1 != null) {
                return ResponseEntity.ok(base1);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            System.out.println("Nao tem 8 de tamanho");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getIdRout")
    public ResponseEntity<String> getIdRout(List<Tarefa> tarefas) throws IOException {
        String data = utilRotaService.getIdRoute(tarefas);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getJobId/{id}")
    public ResponseEntity<RootRetorno> getJobId(String id) throws IOException {
        RootRetorno data = utilRotaService.getJobId(id);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allRout")
    public ResponseEntity<RootRetorno> getAllRout(List<Tarefa>tarefas) throws IOException {
        RootRetorno data = utilRotaService.getAllRout(tarefas);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

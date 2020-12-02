package com.logistica.service;

import com.logistica.controller.HelpApiControllerRota;
import com.logistica.model.Data;
import com.logistica.model.RootRetorno;
import com.logistica.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UtilRotaService {

    @Autowired
    private HelpApiControllerRota helpApiControllerRota;


    public Data consultaLatitudeLongitude(String cep) throws IOException {
        Data data = helpApiControllerRota.get(cep);
        return data;
    }

    public String getIdRoute(List<Tarefa> tarefas) throws IOException {
        return helpApiControllerRota.getRetornaJobId(tarefas);
    }

    public RootRetorno getJobId(String id) throws IOException {
        return helpApiControllerRota.getRetornaRepostaJobId(id);
    }

    public RootRetorno getAllRout(List<Tarefa>tarefas) throws IOException {
        return helpApiControllerRota.getAllRout(tarefas);
    }
}

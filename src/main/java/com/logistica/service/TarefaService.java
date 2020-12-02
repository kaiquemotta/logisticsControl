package com.logistica.service;

import com.logistica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logistica.repository.TarefaRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private RemetenteService remetenteService;
    @Autowired
    private DestinatarioService destinatarioService;
    @Autowired
    private CargaService cargaService;

    @Autowired
    private UtilRotaService utilRotaService;


    public Tarefa save(Tarefa tarefa) {
        if (tarefa.getCarga() != null) {
            Remetente remetente = remetenteService.findByIdOrCreate(tarefa.getRemetente());
            if (remetente != null) {
                Destinatario destinatario = destinatarioService.findByOrCreate(tarefa.getDestinatario());
                if (destinatario != null) {
                    Carga c = cargaService.findByIdOrCreate(tarefa.getCarga());
                    tarefa.setDestinatario(destinatario);
                    tarefa.setRemetente(remetente);
                    tarefa.setCarga(c);
                    tarefa.setAlteradoPor("TESTE-KAIQUE1223");
                    tarefa.setCriadoPor("TESTE-KAIQUE1223");
                    tarefa.setStatusTarefa(StatusTarefa.ABERTA);
                    tarefa.setStatus(false);
                    tarefa.setCriandoEm(LocalDateTime.now());

                }
            } else {
                System.err.println("SEM REMETENTE!");
            }
        }
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> findByIdBase(Long id) {
        Optional<Tarefa> opBase = tarefaRepository.findById(id);
        return opBase;
    }

    public void delete(Optional<Tarefa> b) {
        tarefaRepository.deleteById(b.get().getId());
    }


    public Tarefa update(Tarefa tarefa, int id) {
        if (id != 0) {
            tarefa.setStatusTarefa(StatusTarefa.ALOCADA);
            Tarefa opBase = tarefaRepository.save(tarefa);
            return opBase;
        } else {
            Optional<Tarefa> opBase = findByIdBase(tarefa.getId());
            if (opBase.isPresent()) {
                opBase.get().setStatusTarefa(StatusTarefa.FINALIZADA);
                opBase.get().setStatus(true);
                opBase.get().setAlteradoEm(LocalDateTime.now());
                opBase.get().setAlteradoPor("1Kaique-TESTE");
                opBase.get().setTempoEntrega(trataData(opBase.get()));
                tarefaRepository.save(opBase.get());
            }
            return opBase.get();
        }
    }

    private String trataData(Tarefa tarefa) {
        LocalDateTime fromDateTime = tarefa.getCriadoEm();
        LocalDateTime toDateTime = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        String val =
                days + " Dias : " +
                        hours + " Horas : " +
                        minutes + " Minutos :" +
                        seconds + " Segundos";

        return val;
    }

    public List<Tarefa> list() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listTarefaMotorista(long id) throws IOException {
        List<Tarefa> listTarefa = tarefaRepository.findByMotoristtaId(id);
        Viagem v = new Viagem();
        Tarefa t =  new Tarefa();
        Destinatario d = new Destinatario();
        RootRetorno root = utilRotaService.getAllRout(listTarefa);
//        List<Tarefa> opTarefa = new ArrayList<>();
//        List<Route> roots = root.getSolution().getRoutes();
//        List<Activity> activities = roots.stream().filter(c -> !c.getActivities().isEmpty()).findFirst().get().getActivities().stream().collect(Collectors.toList());
//        List<Tarefa> listTarefasNovas = new ArrayList<>();
//        int i = 0;
//        List<Activity> atividades = new ArrayList<>();
//
//        activities.stream().filter(a -> a.getType().equals("service")).forEach(b -> {
//            atividades.add(b);
//        });
//        Integer[] numbers = new Integer[atividades.size()];
//
//        for (Activity act : atividades) {
//
//            Integer text = Integer.parseInt(act.getAddress().getLocation_id());
//            numbers[i++] = text;
//        }
//        for (int j = 0; j < numbers.length; j++) {
//           Tarefa t = new Tarefa();
//            if (listTarefa.get(j).getId() == numbers[j]) {
//            }
//            listTarefasNovas.add(taf);
//        }
//        return listTarefasNovas;
        return listTarefa;
    }

    public List<Tarefa> listEmAberto() {
        List<Tarefa> tarefaList = tarefaRepository.findByStatus();
        return tarefaList;
    }

    public List<Tarefa> listConcluidas() {
        List<Tarefa> tarefaList = tarefaRepository.findByConcluida();
        return tarefaList;
    }

    public List<Tarefa> listAlocadas() {
        List<Tarefa> tarefaList = tarefaRepository.findByAlocada();
        return tarefaList;
    }
}

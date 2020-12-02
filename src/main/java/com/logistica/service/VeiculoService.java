package com.logistica.service;

import com.logistica.model.*;
import com.logistica.repository.BaseRepository;
import com.logistica.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {


    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private BaseRepository baseRepository;
    List<Veiculo> bases = new ArrayList<Veiculo>();

    public List<Veiculo> list() {
        return veiculoRepository.findAll();
    }


    public Veiculo save(Veiculo veiculo) {
        if (veiculo.getBase().getId() != null) {
            for (EstadoSigla es : EstadoSigla.values()) {
                if (veiculo.getUf().indexOf(es.name()) > -1) {
                    veiculo.setCriadoEm(LocalDateTime.now());
                //    veiculo.setCriadoPor(UserUtil.as().getNomeCompleto());
                }
            }
            Optional<Base> opBase = baseRepository.findById(veiculo.getBase().getId());
            if (opBase.isPresent()) {
                veiculo.setBase(opBase.get());
                return veiculoRepository.save(veiculo);
            }
        }
        return null;
    }



    public Optional<Veiculo> findById(Long id) {
        Optional<Veiculo> opVeiculo = veiculoRepository.findById(id);
        return opVeiculo;
    }

    public Optional<Veiculo> findByName(Veiculo veiculo) {
        return veiculoRepository.findByPlaca(veiculo.getPlaca());
    }

    public void delete(Optional<Veiculo> b) {
        veiculoRepository.deleteById(b.get().getIdVeiculo());
    }

    public Optional<Veiculo> update(@Valid Veiculo veiculo) {
        Optional<Veiculo> opBase = findById(veiculo.getIdVeiculo());
        if (opBase.isPresent()) {
            veiculoRepository.save(veiculo);
        }
        return opBase;
    }
}

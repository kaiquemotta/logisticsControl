package com.logistica.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.logistica.model.Base;
import com.logistica.model.EstadoSigla;
import com.logistica.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BaseService
 */
@Service
public class BaseService {

    @Autowired
    private BaseRepository baseRepository;

    List<Base> bases = new ArrayList<Base>();

    public List<Base> list() {

        return baseRepository.findAll();
    }

    public Base save(Base base) {
        base.setCriadoEm(LocalDateTime.now());
        base.setCriadoPor("Fase de Teste!");
        baseRepository.save(base);
        return base;
    }

    public Optional<Base> findByIdBase(Long id) {
        Optional<Base> opBase = baseRepository.findById(id);
        return opBase;
    }

    public Optional<Base> findByName(Base base) {
        return baseRepository.findByName(base.getNomeBase());
    }

    public void delete(Optional<Base> b) {
        baseRepository.deleteById(b.get().getId());
    }

    public Optional<Base> update(@Valid Base base) {
        Optional<Base> opBase = findByIdBase(base.getId());
        if (opBase.isPresent()) {
            baseRepository.save(base);
            //	base.setBase(opBase.get().getBase());
        }
        return opBase;
    }
}

package com.logistica.service;

import com.logistica.model.Carga;
import com.logistica.model.Remetente;
import com.logistica.repository.CargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargaService {
    @Autowired
    CargaRepository cargaRepository;

    public Carga findByIdOrCreate(Carga carga) {
        boolean existId = carga.getIdCarga() != null ? true : false;
        if (existId) {
            Optional<Carga> opCarga = cargaRepository.findById(carga.getIdCarga());
            if (opCarga.isPresent()) {
                return opCarga.get();
            }
        } else {
            return cargaRepository.save(carga);
        }
        return null;
    }
}

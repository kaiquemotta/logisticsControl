package com.logistica.service;

import com.logistica.model.Remetente;
import com.logistica.repository.RemetenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemetenteService {

    @Autowired
    RemetenteRepository remetenteRepository;

    public Remetente save(Remetente remetente) {
        return remetenteRepository.save(remetente);
    }

    public Remetente findByIdOrCreate(Remetente remetente) {
        boolean existId = remetente.getIdRemetente() != null ? true : false;
        if (existId) {
            Optional<Remetente> opRemente = remetenteRepository.findById(remetente.getIdRemetente());
            if (opRemente.isPresent()) {
                return opRemente.get();
            }
        } else {
            return remetenteRepository.save(remetente);
        }
        return null;
    }
}

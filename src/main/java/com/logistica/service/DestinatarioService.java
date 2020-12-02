package com.logistica.service;

import com.logistica.model.Destinatario;
import com.logistica.repository.DestinatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DestinatarioService {
    @Autowired
    private DestinatarioRepository destinatarioRepository;

    public Destinatario findByOrCreate(Destinatario destinatario) {
        boolean existId = destinatario.getId() != null ? true : false;
        if (existId) {
            Optional<Destinatario> opDest = destinatarioRepository.findById(destinatario.getId());
            if (opDest.isPresent()) {
                return opDest.get();
            }
        } else {
            return destinatarioRepository.save(destinatario);
        }
        return null;
    }
}


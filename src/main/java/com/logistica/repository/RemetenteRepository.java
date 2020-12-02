package com.logistica.repository;

import com.logistica.model.Remetente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemetenteRepository extends JpaRepository<Remetente, Long> {
}

package com.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem ,Long>{

}

package com.logistica.repository;

import com.logistica.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logistica.model.Motorista;

import java.util.List;
import java.util.Optional;

/**
 * MotoristaRepository
 */
@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    @Query(value = "SELECT * FROM motorista  WHERE  cpf  =  ?", nativeQuery = true)
    Optional<Motorista> findByCpf(String cpf);
	

}

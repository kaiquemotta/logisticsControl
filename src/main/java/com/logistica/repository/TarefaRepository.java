package com.logistica.repository;

import com.logistica.model.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logistica.model.Tarefa;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(value = "SELECT * FROM logistics.tarefa t inner join viagem v on v.id= t.id_viagem where v.motorista_id = ?", nativeQuery = true)
    List<Tarefa> findByMotoristtaId(Long id_motorista);

    @Query(value = "SELECT * FROM Tarefa  WHERE status_tarefa = 'ABERTA'", nativeQuery = true)
    List<Tarefa> findByStatus();

    @Query(value = "SELECT * FROM Tarefa  WHERE status_tarefa = 'FINALIZADA'", nativeQuery = true)
    List<Tarefa> findByConcluida();


    @Query(value = "SELECT * FROM Tarefa  WHERE status_tarefa = 'ALOCADA'", nativeQuery = true)
    List<Tarefa> findByAlocada();
}

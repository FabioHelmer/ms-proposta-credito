package com.fh.propostaapp.repository;

import com.fh.propostaapp.entity.Proposta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {


    List<Proposta> findAllByIntegradaIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE p SET aprovada = :aprovada, observacao= :observacao WHERE id = :id", nativeQuery = true)
    void atualizarProposta(Long id, boolean aprovada, String observacao);
}

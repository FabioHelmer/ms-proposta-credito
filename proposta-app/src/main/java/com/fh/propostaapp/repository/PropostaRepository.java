package com.fh.propostaapp.repository;

import com.fh.propostaapp.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {


    List<Proposta> findAllByIntegradaIsFalse();


}

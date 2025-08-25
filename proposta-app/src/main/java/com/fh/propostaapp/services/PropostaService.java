package com.fh.propostaapp.services;

import com.fh.propostaapp.repository.PropostaRepository;
import com.fh.propostaapp.dtos.PropostaRequest;
import com.fh.propostaapp.dtos.PropostaResponse;
import com.fh.propostaapp.entity.Proposta;
import com.fh.propostaapp.mapper.PropostaMapper;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private final PropostaRepository repository;

    public PropostaService(PropostaRepository repository) {
        this.repository = repository;
    }

    public PropostaResponse createProposta(PropostaRequest request) {

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        repository.save(proposta);

        PropostaResponse propostaResponse = PropostaMapper.INSTANCE.convertEntityToDto(proposta);

        return propostaResponse;
    }

}

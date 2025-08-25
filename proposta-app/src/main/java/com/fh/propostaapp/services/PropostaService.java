package com.fh.propostaapp.services;

import com.fh.propostaapp.repository.PropostaRepository;
import com.fh.propostaapp.dtos.PropostaRequest;
import com.fh.propostaapp.dtos.PropostaResponse;
import com.fh.propostaapp.entity.Proposta;
import com.fh.propostaapp.mapper.PropostaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository repository;
    private final NotificacaoService notificacaoService;

    public PropostaService(PropostaRepository repository, NotificacaoService notificacaoService) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
    }

    public PropostaResponse createProposta(PropostaRequest request) {

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        repository.save(proposta);

        PropostaResponse propostaResponse = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        notificacaoService.notificar(propostaResponse);

        return propostaResponse;
    }


    public List<PropostaResponse> getAll() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }


}

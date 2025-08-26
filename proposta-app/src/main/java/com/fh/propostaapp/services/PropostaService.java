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
    private final NotificacaoRabbitMQService notificacaoService;

    public PropostaService(PropostaRepository repository, NotificacaoRabbitMQService notificacaoService) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
    }

    public PropostaResponse createProposta(PropostaRequest request) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        repository.save(proposta);
        notificarRabbitMQ(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public List<PropostaResponse> getAll() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }

    public List<Proposta> getAllByIntegradaIsFalse() {
        return repository.findAllByIntegradaIsFalse();
    }

    public void notificarRabbitMQ(Proposta proposta) {
        try{
            notificacaoService.notificar(proposta);
            proposta.setIntegrada(true);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
        }
        repository.save(proposta);
    }

}

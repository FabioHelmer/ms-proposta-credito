package com.fh.propostaapp.listeners;

import com.fh.propostaapp.entity.Proposta;
import com.fh.propostaapp.mapper.PropostaMapper;
import com.fh.propostaapp.repository.PropostaRepository;
import com.fh.propostaapp.services.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final  PropostaRepository propostaRepository;
    private final WebSocketService webSocketService;

    public PropostaConcluidaListener(PropostaRepository propostaRepository, WebSocketService webSocketService) {
        this.propostaRepository = propostaRepository;
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        atualizarProposta(proposta);
        webSocketService.notificar(PropostaMapper.INSTANCE.convertEntityToDto(proposta));
    }

    private void atualizarProposta(Proposta proposta) {
        propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
    }

}
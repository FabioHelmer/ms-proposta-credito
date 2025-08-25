package com.fh.propostaapp.services;

import com.fh.propostaapp.dtos.PropostaResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.fh.propostaapp.configs.RabbitMQConfig.EXCHANGE_PROPOSTA_PENDENTE;

@Service
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificar(PropostaResponse proposta) {
        rabbitTemplate.convertAndSend(EXCHANGE_PROPOSTA_PENDENTE, "", proposta);
    }

}

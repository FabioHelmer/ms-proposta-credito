package com.fh.propostaapp.services;

import com.fh.propostaapp.entity.Proposta;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.fh.propostaapp.configs.RabbitMQConfig.EXCHANGE_PROPOSTA_PENDENTE;

@Service
public class NotificacaoRabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoRabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificar(Proposta proposta) {
        rabbitTemplate.convertAndSend(EXCHANGE_PROPOSTA_PENDENTE, "", proposta);
    }

}

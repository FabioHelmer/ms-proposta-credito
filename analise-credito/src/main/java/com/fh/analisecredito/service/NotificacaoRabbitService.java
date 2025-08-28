package com.fh.analisecredito.service;

import com.fh.analisecredito.domain.Proposta;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fh.analisecredito.config.RabbitMQConfig.EXCHANGE_PROPOSTA_CONCLUIDA;

@Service
public class NotificacaoRabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta) {
        rabbitTemplate.convertAndSend(EXCHANGE_PROPOSTA_CONCLUIDA, "", proposta);
    }
}
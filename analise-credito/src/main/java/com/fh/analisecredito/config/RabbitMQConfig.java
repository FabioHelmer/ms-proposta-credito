package com.fh.analisecredito.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static String EXCHANGE_PROPOSTA_CONCLUIDA;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    public void setExchangePropostaConcluida(String exchange) {
        EXCHANGE_PROPOSTA_CONCLUIDA = exchange;
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
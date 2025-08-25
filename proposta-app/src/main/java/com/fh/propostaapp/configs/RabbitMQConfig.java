package com.fh.propostaapp.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static String EXCHANGE_PROPOSTA_PENDENTE;

    @Value("${rabbitmq.proposta-pendente.exchange}")
    public void setExchangePropostaPendente(String exchange) {
        EXCHANGE_PROPOSTA_PENDENTE = exchange;
    }

    @Bean
    public Queue queuePropostaPedenteAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }


    @Bean
    public Queue queuePropostaPendenteNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }


    @Bean
    public Queue queuePropostaConcluidaProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }


    @Bean
    public Queue queuePropostaConcluidaNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize(

        );
    }

    @Bean
    public FanoutExchange fanoutExchangePropostaPendente(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_PROPOSTA_PENDENTE).build();
    }


    @Bean
    public Binding bindingPropostaPedenteAnaliseCredito(){
        return BindingBuilder.bind(queuePropostaPedenteAnaliseCredito())
                .to(fanoutExchangePropostaPendente());
    }

    @Bean
    public Binding bindingPropostaPendenteNotificacao(){
        return BindingBuilder.bind(queuePropostaPendenteNotificacao())
                .to(fanoutExchangePropostaPendente());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }



}

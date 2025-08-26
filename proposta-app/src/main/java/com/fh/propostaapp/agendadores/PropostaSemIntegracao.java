package com.fh.propostaapp.agendadores;

import ch.qos.logback.core.util.FixedDelay;
import com.fh.propostaapp.entity.Proposta;
import com.fh.propostaapp.repository.PropostaRepository;
import com.fh.propostaapp.services.NotificacaoRabbitMQService;
import com.fh.propostaapp.services.PropostaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class PropostaSemIntegracao {

    private final PropostaRepository repository;
    private final NotificacaoRabbitMQService notificacaoRabbitMQService;

    public PropostaSemIntegracao(PropostaRepository repository, PropostaService propostaService, NotificacaoRabbitMQService notificacaoRabbitMQService) {
        this.repository = repository;
        this.notificacaoRabbitMQService = notificacaoRabbitMQService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void buscarPropostasSemIntegracao(){

        repository.findAllByIntegradaIsFalse().forEach(proposta->{
            try{
                notificacaoRabbitMQService.notificar(proposta);
                atualizarPropostaParaIntegrada(proposta);
            } catch (RuntimeException e) {
                e.printStackTrace();
                log.error("Falha em buscarPropostasSemIntegracao");
            }
        });
    }


    private void atualizarPropostaParaIntegrada(Proposta proposta) {
        try{
            if(proposta == null) return;
            proposta.setIntegrada(true);
            repository.save(proposta);
        } catch (Exception e) {
            log.error("Falha em atualizarPropostaParaIntegrada");
            e.printStackTrace();
        }
    }

}

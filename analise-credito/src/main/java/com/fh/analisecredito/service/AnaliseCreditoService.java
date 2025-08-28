package com.fh.analisecredito.service;

import com.fh.analisecredito.domain.Proposta;
import com.fh.analisecredito.exceptions.StrategyException;
import com.fh.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private final List<CalculoPonto> calculoPontoList;
    private final NotificacaoRabbitService notificacaoRabbitService;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList, NotificacaoRabbitService notificacaoRabbitService) {
        this.calculoPontoList = calculoPontoList;
        this.notificacaoRabbitService = notificacaoRabbitService;
    }

    public void analisar(Proposta proposta) {
        try {
            int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos > 350);
        } catch (StrategyException ex) {
            proposta.setAprovada(false);
            proposta.setObservacao(ex.getMessage());
        }
        notificacaoRabbitService.notificar(proposta);
    }
}
package com.fh.notificao.listener;

import com.fh.notificao.constantes.MensagemConstante;
import com.fh.notificao.domain.Proposta;
import com.fh.notificao.services.NotificacaoSNSService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    private final NotificacaoSNSService notificacaoSNSService;

    public PropostaPendenteListener(NotificacaoSNSService notificacaoSNSService) {
        this.notificacaoSNSService = notificacaoSNSService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificacaoSNSService.notificar(proposta.getUsuario().getTelefone(), mensagem);
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta){
        String mensagem = "";
        if(proposta.getAprovada()){
            mensagem = String.format(MensagemConstante.PROPOSTA_APROVADA, proposta.getUsuario().getNome());
        }else{
           mensagem = String.format(MensagemConstante.PROPOSTA_NEGADA, proposta.getUsuario().getNome(), proposta.getObservacao());
        }
        notificacaoSNSService.notificar(proposta.getUsuario().getTelefone(), mensagem);

    }

}

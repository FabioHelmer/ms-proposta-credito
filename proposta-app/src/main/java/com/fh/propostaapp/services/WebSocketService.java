package com.fh.propostaapp.services;

import com.fh.propostaapp.dtos.PropostaResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notificar(PropostaResponse response){
        simpMessagingTemplate.convertAndSend("/propostas", response);
    }


}

package com.fh.propostaapp.controllers;

import com.fh.propostaapp.dtos.PropostaRequest;
import com.fh.propostaapp.dtos.PropostaResponse;
import com.fh.propostaapp.services.PropostaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    private final PropostaService service;

    public PropostaController(PropostaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PropostaResponse> createProposta(@Valid @RequestBody PropostaRequest request) {
        PropostaResponse proposta = service.createProposta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposta);
    }
}

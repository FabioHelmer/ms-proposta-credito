package com.fh.propostaapp.dtos;

import lombok.*;

@Builder
public record PropostaResponse(

        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        Double renda,
        Double valorSolicitado,
        int prazoPagamento,
        Boolean aprovado,
        String observacao
) {}

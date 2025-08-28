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
        String valorSolicitadoFmt,
        int prazoPagamento,
        Boolean aprovada,
        String observacao
) {}

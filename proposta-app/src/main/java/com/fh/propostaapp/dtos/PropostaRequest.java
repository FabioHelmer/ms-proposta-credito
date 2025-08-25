package com.fh.propostaapp.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record PropostaRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Sobrenome é obrigatório")
        String sobrenome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "Renda é obrigatória")
        @PositiveOrZero(message = "Renda não pode ser negativa")
        Double renda,

        @NotNull(message = "Valor solicitado é obrigatória")
        @PositiveOrZero(message = "Valor solicitado não pode ser negativa")
        Double valorSolicitado,

        int prazoPagamento

) {}

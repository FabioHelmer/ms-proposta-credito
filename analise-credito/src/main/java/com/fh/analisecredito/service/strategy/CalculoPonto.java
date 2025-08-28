package com.fh.analisecredito.service.strategy;

import com.fh.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}

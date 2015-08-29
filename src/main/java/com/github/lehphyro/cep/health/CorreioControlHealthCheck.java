package com.github.lehphyro.cep.health;

import com.codahale.metrics.health.HealthCheck;
import com.github.lehphyro.cep.core.Endereco;
import com.github.lehphyro.cep.service.CepService;

public class CorreioControlHealthCheck extends HealthCheck {

    private static final String CEP_CONHECIDO = "01330000";

    private final CepService cepService;

    public CorreioControlHealthCheck(CepService cepService) {
        this.cepService = cepService;
    }

    @Override
    protected Result check() throws Exception {
        Endereco endereco = cepService.buscarPorNumero(CEP_CONHECIDO);
        if (endereco == null) {
            return Result.unhealthy("Nao foi possivel buscar um cep conhecido");
        }
        return Result.healthy();
    }
}

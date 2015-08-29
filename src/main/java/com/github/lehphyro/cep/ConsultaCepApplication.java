package com.github.lehphyro.cep;

import com.github.lehphyro.cep.health.CorreioControlHealthCheck;
import com.github.lehphyro.cep.resources.CepResource;
import com.github.lehphyro.cep.service.CepService;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class ConsultaCepApplication extends Application<ConsultaCepConfiguration> {

    public static void main(String[] args) throws Exception {
        new ConsultaCepApplication().run(args);
    }

    @Override
    public String getName() {
        return "consulta-cep";
    }

    @Override
    public void run(ConsultaCepConfiguration consultaCepConfiguration, Environment environment) throws Exception {
        Client client = new JerseyClientBuilder(environment).using(consultaCepConfiguration.getHttpClient()).build(getName());
        CepService cepService = new CepService(consultaCepConfiguration.getTarget(), client, environment.getObjectMapper());

        CepResource cepResource = new CepResource(cepService);
        environment.jersey().register(cepResource);

        CorreioControlHealthCheck correioControlHealthCheck = new CorreioControlHealthCheck(cepService);
        environment.healthChecks().register("correiocontrol", correioControlHealthCheck);
    }
}

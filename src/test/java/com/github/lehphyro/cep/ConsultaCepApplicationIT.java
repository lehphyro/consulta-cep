package com.github.lehphyro.cep;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ConsultaCepApplicationIT {

    @ClassRule
    public static final DropwizardAppRule<ConsultaCepConfiguration> RULE =
            new DropwizardAppRule<>(ConsultaCepApplication.class, "src/etc/config/config.yml");

    @Test
    public void testBuscaCepConhecido() throws Exception {
        Client client = new JerseyClientBuilder(RULE.getEnvironment())
                .using(RULE.getConfiguration().getHttpClient())
                .build("IT client");

        Response response = client.target(String.format("http://localhost:%d/cep/01330000", RULE.getLocalPort()))
                .request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}

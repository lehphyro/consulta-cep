package com.github.lehphyro.cep.correiocontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class CepRepresentationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        CepRepresentation rep = new CepRepresentation();
        rep.setBairro("Bela Vista");
        rep.setLogradouro("Rua Rocha");
        rep.setCep("01330000");
        rep.setUf("SP");
        rep.setLocalidade("São Paulo");

        CepRepresentation repLido = MAPPER.readValue(fixture("fixtures/cep-representation.json"), CepRepresentation.class);
        assertEquals(rep, repLido);
    }
}

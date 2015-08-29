package com.github.lehphyro.cep.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class EnderecoTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        Endereco endereco = new Endereco("Rua Rocha", "Bela Vista", "São Paulo", "SP");

        String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/endereco.json"), Endereco.class));
        String actual = MAPPER.writeValueAsString(endereco);

        assertEquals(expected, actual);
    }
}

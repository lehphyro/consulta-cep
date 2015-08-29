package com.github.lehphyro.cep.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lehphyro.cep.core.Endereco;
import com.github.lehphyro.cep.correiocontrol.CepRepresentation;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.*;

public class CepServiceTest {

    private static final String BAIRRO = "Bela Vista";

    private static final String LOGRADOURO = "Rua Rocha";

    private static final String CEP = "01330000";

    private static final String UF = "SP";

    private static final String LOCALIDADE = "São Paulo";

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void testBuscaPorNumeroOk() throws Exception {
        CepService cepService = new CepService(null, null, MAPPER) {
            @Override
            protected String buscar(String numero) {
                try {
                    return MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/cep-representation.json"), CepRepresentation.class));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Endereco endereco = cepService.buscarPorNumero("01330000");
        assertNotNull(endereco);
    }

    @Test
    public void testBuscaPorNumeroNok() throws Exception {
        CepService cepService = new CepService(null, null, null) {
            @Override
            protected String buscar(String numero) {
                return null;
            }
        };

        Endereco endereco = cepService.buscarPorNumero("01330000");
        assertNull(endereco);
    }

    @Test
    public void testConversao() throws Exception {
        CepRepresentation cepRepresentation = new CepRepresentation();
        cepRepresentation.setBairro(BAIRRO);
        cepRepresentation.setLogradouro(LOGRADOURO);
        cepRepresentation.setCep(CEP);
        cepRepresentation.setUf(UF);
        cepRepresentation.setLocalidade(LOCALIDADE);

        Endereco endereco = new CepService(null, null, null).converter(cepRepresentation);
        assertEquals(BAIRRO, endereco.getBairro());
        assertEquals(LOGRADOURO, endereco.getLogradouro());
        assertEquals(UF, endereco.getEstado());
        assertEquals(LOCALIDADE, endereco.getCidade());
    }
}

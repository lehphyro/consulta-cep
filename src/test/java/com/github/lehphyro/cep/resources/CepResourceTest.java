package com.github.lehphyro.cep.resources;

import com.github.lehphyro.cep.core.Endereco;
import com.github.lehphyro.cep.service.CepService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CepResourceTest {

    private static final String CEP_VALIDO = "01330000";

    private static final CepService SERVICE = new CepService(null, null, null) {
        @Override
        public Endereco buscarPorNumero(String numero) {
            if (CEP_VALIDO.equals(numero)) {
                return new Endereco();
            }
            return null;
        }
    };

    @ClassRule
    public static final ResourceTestRule RESOURCES = ResourceTestRule.builder()
            .addResource(new CepResource(SERVICE))
            .build();

    @Test
    public void testCepMuitoGrande() throws Exception {
        Response response = RESOURCES.client().target("/cep/013300001233").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepMuitoPequeno() throws Exception {
        Response response = RESOURCES.client().target("/cep/1").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepNaoNumerico() throws Exception {
        Response response = RESOURCES.client().target("/cep/0133000a").request().get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepOk() throws Exception {
        Response response = RESOURCES.client().target("/cep/" + CEP_VALIDO).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCepNaoExistente() throws Exception {
        Response response = RESOURCES.client().target("/cep/11111111").request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}

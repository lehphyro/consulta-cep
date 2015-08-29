package com.github.lehphyro.cep.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.lehphyro.cep.core.Endereco;
import com.github.lehphyro.cep.service.CepService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/cep")
@SuppressWarnings("RSReferenceInspection") // charset definido dessa forma é permitido no jersey
@Produces("application/json; charset=UTF-8")
public class CepResource {

    private final CepService cepService;

    public CepResource(CepService cepService) {
        this.cepService = cepService;
    }

    @GET
    @Path("/{numero}")
    @Timed
    public Response buscarPorNumero(@Length(min = 8, max = 8, message = "CEP inválido")
                                    @Digits(integer = 8, fraction = 0, message = "CEP inválido")
                                    @PathParam("numero") String numero) {
        Endereco endereco = cepService.buscarPorNumero(numero);
        if (endereco == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(endereco).build();
    }
}

package com.github.lehphyro.cep.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lehphyro.cep.core.Endereco;
import com.github.lehphyro.cep.correiocontrol.CepRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.containsOnly;

public class CepService {

    private static final Logger logger = LoggerFactory.getLogger(CepService.class);

    private static final char ZERO = '0';
    private static final String RESPOSTA_ERRO = "{\"erro\":true}";

    private final String target;
    private final Client client;
    private final ObjectMapper objectMapper;

    public CepService(String target, Client client, ObjectMapper objectMapper) {
        this.target = target;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Endereco buscarPorNumero(String numero) {
        StringBuilder atual = new StringBuilder(numero);
        String response = buscar(atual.toString());
        int indiceSubstituido = atual.length();
        while (response == null && !containsOnly(atual, ZERO)) {
            logger.debug("Recebido erro para cep [{}]", atual);
            atual.setCharAt(--indiceSubstituido, ZERO);
            response = buscar(atual.toString());
        }

        if (response == null) {
            return null;
        }

        try {
            return converter(objectMapper.readValue(response, CepRepresentation.class));
        } catch (IOException e) {
            throw new RuntimeException("Nunca vai acontecer pois nao ocorre IO nesse bloco", e);
        }
    }

    protected String buscar(String numero) {
        logger.debug("Buscando cep [{}] em correiocontrol", numero);
        Response response = client.target(target)
                .path(numero + ".json")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            return null;
        }

        String body = response.readEntity(String.class);
        if (body.contains(RESPOSTA_ERRO)) {
            return null;
        }

        return body;
    }

    protected Endereco converter(CepRepresentation cepRepresentation) {
        return new Endereco(cepRepresentation.getLogradouro(),
                            cepRepresentation.getBairro(),
                            cepRepresentation.getLocalidade(),
                            cepRepresentation.getUf());
    }
}

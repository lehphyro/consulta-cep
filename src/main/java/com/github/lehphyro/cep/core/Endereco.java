package com.github.lehphyro.cep.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
        // para o jackson poder criar instancias
    }

    public Endereco(String logradouro, String bairro, String cidade, String estado) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @JsonProperty
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty
    public String getBairro() {
        return bairro;
    }

    @JsonProperty
    public String getCidade() {
        return cidade;
    }

    @JsonProperty
    public String getEstado() {
        return estado;
    }
}

package com.github.lehphyro.cep.correiocontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CepRepresentation {

    private String bairro;
    private String logradouro;
    private String cep;
    private String uf;
    private String localidade;

    @JsonProperty
    public String getBairro() {
        return bairro;
    }

    @JsonProperty
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty
    public String getCep() {
        return cep;
    }

    @JsonProperty
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty
    public String getUf() {
        return uf;
    }

    @JsonProperty
    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty
    public String getLocalidade() {
        return localidade;
    }

    @JsonProperty
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}

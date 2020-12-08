package br.com.elisscherer.reamp.model;

public class Cidade {

    private String nome;
    private Double temperatura;
    private Double sensacaoTermica;
    private String descricao;
    private Integer umidade;

    public Cidade() {
    }

    public Cidade(String nome, Double temperatura, Double sensacaoTermica, String descricao, Integer umidade) {
        this.nome = nome;
        this.temperatura = temperatura;
        this.sensacaoTermica = sensacaoTermica;
        this.descricao = descricao;
        this.umidade = umidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(Double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getUmidade() {
        return umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

}

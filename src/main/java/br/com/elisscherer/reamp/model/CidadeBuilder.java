package br.com.elisscherer.reamp.model;

public final class CidadeBuilder {
    private String nome;
    private Double temperatura;
    private Double sensacaoTermica;
    private String descricao;
    private Integer umidade;

    private CidadeBuilder() {
    }

    public static CidadeBuilder aCidade() {
        return new CidadeBuilder();
    }

    public CidadeBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CidadeBuilder withTemperatura(Double temperatura) {
        this.temperatura = temperatura;
        return this;
    }

    public CidadeBuilder withSensacaoTermica(Double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
        return this;
    }

    public CidadeBuilder withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public CidadeBuilder withUmidade(Integer umidade) {
        this.umidade = umidade;
        return this;
    }

    public Cidade build() {
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setTemperatura(temperatura);
        cidade.setSensacaoTermica(sensacaoTermica);
        cidade.setDescricao(descricao);
        cidade.setUmidade(umidade);
        return cidade;
    }
}

package br.com.elisscherer.reamp.service;

import br.com.elisscherer.reamp.entity.PrevisaoCidade;
import br.com.elisscherer.reamp.model.Cidade;
import br.com.elisscherer.reamp.model.CidadeBuilder;
import br.com.elisscherer.reamp.repository.PrevisaoRepository;
import br.com.elisscherer.reamp.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PrevisaoService {
    @Autowired
    PrevisaoRepository previsaoRepository;

    @Autowired
    WeatherRepository weatherRepository;


    public Optional<Cidade> verificaCache(String nomeCidade) {

        Optional<PrevisaoCidade> previsaoCidadeOptional = previsaoRepository.findByNomeCidadeAndHora(nomeCidade, LocalDateTime.now().minusMinutes(15));
        Cidade cidade = previsaoCidadeOptional.map(this::criarCidade)
                .orElseGet(() -> {
                    Optional<Cidade> opCidadeAPI = weatherRepository.findPrevisaoByCidade(nomeCidade);
                    return opCidadeAPI.map(c -> {
                        salvarCidade(c);
                        return c;
                    }).orElse(null);
                });
        return Optional.ofNullable(cidade);
    }
    public void salvarCidade(Cidade cidade) {
        Optional<PrevisaoCidade> previsaoCidadeOptional = previsaoRepository.findByNomeCidade(cidade.getNome());
        if (previsaoCidadeOptional.isEmpty()) {
            PrevisaoCidade previsaoCidade = new PrevisaoCidade();
            previsaoCidade.setNomeCidade(cidade.getNome());
            previsaoCidade.setTemperatura(cidade.getTemperatura());
            previsaoCidade.setUmidade(cidade.getUmidade());
            previsaoCidade.setDescricao(cidade.getDescricao());
            previsaoCidade.setSensacaoTermica(cidade.getSensacaoTermica());
            previsaoCidade.setHora(LocalDateTime.now());
            previsaoRepository.save(previsaoCidade);
        } else {
            PrevisaoCidade previsaoCidade = previsaoCidadeOptional.get();
            previsaoCidade.setHora(LocalDateTime.now());
            previsaoRepository.save(previsaoCidade);
        }
    }

    private Cidade criarCidade(PrevisaoCidade previsaoCidade) {
        return CidadeBuilder.aCidade()
                .withNome(previsaoCidade.getNomeCidade())
                .withUmidade(previsaoCidade.getUmidade())
                .withDescricao(previsaoCidade.getDescricao())
                .withSensacaoTermica(previsaoCidade.getSensacaoTermica())
                .withTemperatura(previsaoCidade.getTemperatura())
                .build();
    }
}

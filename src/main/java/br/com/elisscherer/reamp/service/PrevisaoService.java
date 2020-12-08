package br.com.elisscherer.reamp.service;

import br.com.elisscherer.reamp.entity.PrevisaoCidade;
import br.com.elisscherer.reamp.model.Cidade;
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


    public Optional<Cidade> verificaCache(String nomeCidade){

        Optional<PrevisaoCidade> opPrevCidadeCache = previsaoRepository.findByNomeCidade(nomeCidade);
        if(opPrevCidadeCache.isEmpty()){
          Optional<Cidade> opCidadeAPI = weatherRepository.findPrevisaoByCidade(nomeCidade);
            System.out.println("estava vazio");
          if(opCidadeAPI.isEmpty()){
              return opCidadeAPI;
          }else{
              savaEmCache(opCidadeAPI);
              return opCidadeAPI;
          }
        }else{
            System.out.println("buscou e trouse");
            Optional<Cidade> opCidade;
            PrevisaoCidade previsaoCidade = opPrevCidadeCache.get();
            if(previsaoCidade.getHora().plusMinutes(15).isAfter(LocalDateTime.now())){
                opCidade= colocaDadosCache(previsaoCidade);
                System.out.println("pegou da cache");
                return opCidade;
            }else{
              opCidade = weatherRepository.findPrevisaoByCidade(nomeCidade);
              atualizaDadosCache(opCidade);
                return opCidade;
            }
        }
    }

    public void savaEmCache(Optional<Cidade> opCidadeApi){
        Cidade cidadeAPI = opCidadeApi.get();
        PrevisaoCidade previsaoCidade= new PrevisaoCidade();
        previsaoCidade.setNomeCidade(cidadeAPI.getNome());
        previsaoCidade.setTemperatura(cidadeAPI.getTemperatura());
        previsaoCidade.setUmidade(cidadeAPI.getUmidade());
        previsaoCidade.setDescricao(cidadeAPI.getDescricao());
        previsaoCidade.setSensacaoTermica(cidadeAPI.getSensacaoTermica());
        previsaoCidade.setHora(LocalDateTime.now());
        previsaoRepository.save(previsaoCidade);
        System.out.println("salvou na cache");
    }

    private Optional<Cidade> colocaDadosCache(PrevisaoCidade previsaoCidade){
        Cidade cidade = new Cidade();
        cidade.setNome(previsaoCidade.getNomeCidade());
        cidade.setUmidade(previsaoCidade.getUmidade());
        cidade.setSensacaoTermica(previsaoCidade.getSensacaoTermica());
        cidade.setTemperatura(previsaoCidade.getTemperatura());
        cidade.setDescricao(previsaoCidade.getDescricao());
        Optional<Cidade> optionalCidade = Optional.ofNullable(cidade);
        return optionalCidade;
    }

    public void atualizaDadosCache(Optional<Cidade> optionalCidade){
        Cidade cidade= optionalCidade.get();
        Optional<PrevisaoCidade> optPreviCidade = previsaoRepository.findByNomeCidade(cidade.getNome());
        PrevisaoCidade previsaoCidade = optPreviCidade.get();
        previsaoCidade.setHora(LocalDateTime.now());
        previsaoRepository.save(previsaoCidade);
        System.out.println("atualizou hora cache");
    }
}

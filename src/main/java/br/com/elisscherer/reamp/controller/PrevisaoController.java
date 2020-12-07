package br.com.elisscherer.reamp.controller;

import br.com.elisscherer.reamp.model.Cidade;
import br.com.elisscherer.reamp.repository.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/previsao")
public class PrevisaoController {

    @Autowired
    PrevisaoRepository previsaoRepository;

    @PostMapping("/cidade")
    public Cidade buscaPrevisao(@RequestBody String cit){
        Cidade cidade=new Cidade();
        cidade.setNome(cit);
        cidade = previsaoRepository.findPrevisaoByCidade(cidade);
        return cidade;
    }
}

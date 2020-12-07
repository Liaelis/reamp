package br.com.elisscherer.reamp.controller;

import br.com.elisscherer.reamp.model.Cidade;
import br.com.elisscherer.reamp.repository.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/previsao")
public class PrevisaoController {

    @Autowired
    PrevisaoRepository previsaoRepository;

    @GetMapping("/cidade")
    public ResponseEntity<Cidade> buscaPrevisao(@RequestBody String cit){


        Optional<Cidade> optionalCidade = previsaoRepository.findPrevisaoByCidade(cit);

            return ResponseEntity.of(optionalCidade);


    }
}

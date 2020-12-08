package br.com.elisscherer.reamp.controller;

import br.com.elisscherer.reamp.model.Cidade;
import br.com.elisscherer.reamp.repository.WeatherRepository;
import br.com.elisscherer.reamp.service.PrevisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/previsao")
public class PrevisaoController {

    @Autowired
    PrevisaoService previsaoService;

    @GetMapping("/cidade")
    public ResponseEntity<Cidade> buscaPrevisao(@RequestBody String cit){


        Optional<Cidade> optionalCidade = previsaoService.verificaCache(cit);

            return ResponseEntity.of(optionalCidade);


    }
}

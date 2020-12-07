package br.com.elisscherer.reamp.repository;
import br.com.elisscherer.reamp.openweathermap.List;
import br.com.elisscherer.reamp.openweathermap.WeatherResponse;
import br.com.elisscherer.reamp.openweathermap.Weather;
import br.com.elisscherer.reamp.model.Cidade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class PrevisaoRepository {

    public Optional<Cidade> findPrevisaoByCidade(String nomecidade){
        RestTemplate restTemplate = new RestTemplate();
        String prefixUrl ="http://api.openweathermap.org/data/2.5/find?q=";
        String sufixUrl ="&units=metric&appid={key}lang=pt_br";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(prefixUrl+nomecidade+sufixUrl, WeatherResponse.class);
        if(response.getStatusCode() == HttpStatus.OK){

            WeatherResponse resp = response.getBody();
            Optional<Cidade> optionalCidade = objetctPopulation(resp);
            return  optionalCidade;
        }else{

            return  Optional.empty();
        }

    }

    public Optional<Cidade> objetctPopulation(WeatherResponse resp){

        if(resp==null || resp.getList().isEmpty()) {
            return Optional.empty();
        }
        Cidade cidade = new Cidade();
        for(List li : resp.getList()){
            cidade.setTemperatura(li.getMain().getTemp());
            cidade.setSensacaoTermica(li.getMain().getFeelsLike());
            cidade.setUmidade(li.getMain().getHumidity());
            cidade.setNome(li.getName());
            for(Weather weather : li.getWeather()){
                cidade.setDescricao(weather.getDescription());
            }
        }

        Optional<Cidade> optionalCidade = Optional.ofNullable(cidade);
        return optionalCidade;

    }

}

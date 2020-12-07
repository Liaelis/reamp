package br.com.elisscherer.reamp.repository;
import br.com.elisscherer.reamp.jsondto.List;
import br.com.elisscherer.reamp.jsondto.Response;
import br.com.elisscherer.reamp.jsondto.Weather;
import br.com.elisscherer.reamp.jsondto.Wind;
import br.com.elisscherer.reamp.model.Cidade;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PrevisaoRepository {

    public Cidade findPrevisaoByCidade(Cidade cidade){
        RestTemplate restTemplate = new RestTemplate();
        String prefixUrl ="http://api.openweathermap.org/data/2.5/find?q=";
        String sufixUrl ="&units=metric&appid=0a89c0f6c40b7c151176d0b464f7e146&lang=pt_br";
        ResponseEntity<Response> response = restTemplate.getForEntity(prefixUrl+cidade.getNome()+sufixUrl, Response.class);
        Response list = response.getBody();
        cidade = objetctPopulation(list,cidade);
        return cidade;
    }

    public Cidade objetctPopulation(Response resp, Cidade cidade){

        for(List li : resp.getList()){
            cidade.setTemperatura(li.getMain().getTemp());
            cidade.setSensacaoTermica(li.getMain().getFeelsLike());
            cidade.setUmidade(li.getMain().getHumidity());
            cidade.setVelocidadeVento(li.getWind().getSpeed());
            for(Weather weather : li.getWeather()){
                cidade.setDescricao(weather.getDescription());
            }
        }
        return cidade;
    }

}

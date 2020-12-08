<h1 align="center">
     💻 <a href="https://www.reamp.com.br/" alt=""> Desafio Java - Reamp + Jellyfish </a> 🎲
</h1>
<h2 align="center">
     🌦️️ <a href="https://openweathermap.org/" alt="programa de previsão do tempo"> Previsão do Tempo - Open Weather Maps </a> 🌈
</h2>


<h3>Implementações realizadas - API REST </h3>
- Foi utilizado Spring Retry na chamada
do serviço, com essa implementação é possível
re-invocar automaticamente a operação 4 vezes
em caso de falha na invocação. Achei importante
implementar pois previne uma falha por instabilidade
momentânea na rede.
- Foi implementado também a utilização de um banco
para "cache" dos dados da API consumida, o ideal
seria utilização de um banco a parte da aplicação,
contudo para facilitar os testes da aplicação foi
utilizado o banco de dados embarcado Apache Derby,
ainda levando os testes em conta foi definido período
de atualização dos dados de 15min no banco.
Eu considerei importante a utilização de banco para
diminuir o consumo do serviço(em casos de serviço pago),
além de suprir as consultas em caso de instabilidade
momentânea.
- Para comunicação com API foi utilizado RestTemplate
do SpringRestClient.




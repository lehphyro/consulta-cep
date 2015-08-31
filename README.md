# consulta-cep
Serviço para consulta de dados de CEP da API [AvisoBrasil](http://avisobrasil.com.br/correio-control/api-de-consulta-de-cep/)

## Execução
Para executar, utilize:
 * Main Class: com.github.lehphyro.cep.ConsultaCepApplication
 * Working Directory: diretório raiz do projeto (que possui pom.xml)
 * Program Arguments: server src/etc/config/config.yml
 * No browser acesse: http://localhost:9090/cep/01424001

## Arquitetura
Este serviço é implementado utilizando o framework [Dropwizard](http://www.dropwizard.io/). Este framework é útil para
dar o suporte a execução de um servidor HTTP embutido e muitas configurações com valores default razoáveis para um
ambiente de produção.

Existe um Resource REST que recebe o número do CEP e faz uma chamada a um Serviço
que utiliza a API da AvisoBrasil via REST.

Considerações foram feitas na configuração para levar em conta o tempo de resposta da API da AvisoBrasil,
que pode ser um tanto alto.

### Testes
Existem testes unitários da serialização de desserialização para JSON em ambas as pontas, testes da validação
do número do CEP e um teste de integração que roda a aplicação, busca um CEP conhecido e para a aplicação em seguida.

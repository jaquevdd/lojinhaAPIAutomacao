# Lojinha API Automação
Esse é um repositório que contém a automação de alguns testes de API Rest de um software denominado Lojinha. Os sub-tópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.
## Tecnologias Utilizadas

- Java
  https://www.oracle.com/br/java/technologies/downloads/

- JUnit
  https://mvnrepository.com/artifact/junit/junit

- RestAssured
  https://mvnrepository.com/artifact/io.rest-assured/rest-assured

- Maven
  https://maven.apache.org/

## Testes Automatizados

Testes para validar as partições de equivalência relacionadas ao valor de venda do produto na Lojinha, que deve seguir a regra de negócio onde o valor mínimo é de R$ 0,01 e o valor máximo é de R$ 7.000,00.

### Notas Gerais:

- Sempre utilizamos a anotação Before Each para capturar o token de
  autenticação que será usado posteriormente nos métodos de teste.
- Armazenamos os dados que são enviados para a API através do uso de classes POJO.
- Criamos dados iniciais através do uso de classes Data Factory para facilitar a criação e controle dos mesmos.
- Nesse projeto fazemos uso do JUnit 5, permitindo o uso da anotação DisplayName para dar descrições em português para nossos testes.



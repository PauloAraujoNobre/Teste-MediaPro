# Prova Tecnica Backend MediaPro

Uma aplicação de um microserviço com o intuito desafiador de uma prova qualificatória

## Considerações da Prova
- Optei por não utilizar dados em memória, por conseguinte, os dados não apresentam "id" como um atributo dentro do escopo da API, logo, substituí  a chave primária inicialmente relacionada ao `"id"` pelo campo `"competition"`, que representa o nome da competição.

## Pré-requisitos
- [Java 1.8](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
- [Gradle 6.7+](https://gradle.org/releases/)

## Get Start
Após baixar os pré-requisitos e o projeto em sua máquina, caso esteja utilizando o Eclipse importe o projeto como `"Existing Gradle Project"`

Para efetuar testes, recomendo a utilização de uma REST Client como [Postman](https://www.postman.com/downloads/) ou [Insomnia](https://insomnia.rest/download)

Repare também que há uma collection disponível para testes no root do projeto, basta importa na sua ferramente REST Client

## Rotas
- {BASE_URL}/v1/foot/list-all-data
  - Question: Rota auxiliar
  - Method: GET
  - Response: Um objeto com todos os dados da API Scorebat
- {BASE_URL}/v1/foot/list
  - Question: 1
  - Method: GET
  - Response: Uma lista com todos as competições da API Scorebat
  - Details: Como meus dados não apresentam "id", o endpoint retornará apenas uma lista com o dado "competition" da API

- {BASE_URL}/v1/foot/highlight/:competition
  - Question: 2
  - Method: GET
  - PathVariables: 
    - competition: Nome da competição
  - Response: Uma lista com todos os dados da API Scorebat representados apenas com os campos de "title" e "competition"
  - Details: Substituí o campo de "id" por "competition", decorrente a ausência do campo "id" nos dados

- {BASE_URL}/v1/foot/highlight/:competition/:highlightIndex
  - Question: 3
  - Method: GET
  - PathVariables: 
    - competition: Nome da competição
    - highlightIndex: Index do highlight
  - Response: Uma lista com todos os dados da API Scorebat representados apenas com os campos de "competition", "highlight_title", "thumbnail_url" e "highlight_embed"
  - Details: O campo `"highlight_embed"` representa o "embed" de index "highlightIndex" do campo "videos" da API
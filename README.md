# Aplicação Kotlin com Ktor, MongoDB CRUD e H2 CRUD

## Sobre o Projeto

Este projeto é uma aplicação Kotlin que utiliza o framework Ktor para criar um servidor web. A aplicação implementa operações CRUD (Create, Read, Update, Delete) tanto para MongoDB quanto para H2. O projeto segue as melhores práticas de desenvolvimento, incluindo o uso da arquitetura limpa (Clean Architecture) e o desenvolvimento orientado a testes (TDD).

## Pré-requisitos

- JDK 11 ou superior
- MongoDB instalado e em execução
- H2 Database instalado e em execução
- MySQL Database instalado e em execução
- PostgreSQL Database instalado e em execução

## Como Executar

1. Clone o repositório para sua máquina local.
2. Navegue até o diretório do projeto.
3. Execute o comando `./gradlew run` para iniciar o servidor.

## Endpoints

A aplicação expõe os seguintes endpoints:

- `GET /users`: Retorna uma lista de todos os usuários.
- `POST /users`: Cria um novo usuário.
- `GET /users/{id}`: Retorna os detalhes de um usuário específico.
- `PUT /users/{id}`: Atualiza os detalhes de um usuário específico.
- `DELETE /users/{id}`: Exclui um usuário específico.

## Testes

A aplicação inclui testes unitários e de integração. Para executar os testes, use o comando `./gradlew test`.

## Arquitetura Limpa

A arquitetura limpa é um padrão de design que separa as preocupações do software em camadas distintas. Isso facilita o entendimento, o desenvolvimento e a manutenção do código. Nesse projeto, procuro seguir e aprendendo a arquitetura limpa, onde as camadas mais internas (como a lógica de negócios) não dependem das camadas mais externas (como a interface do usuário ou o banco de dados).

## Desenvolvimento Orientado a Testes (TDD)

O TDD é uma prática de desenvolvimento que envolve a escrita de testes antes de escrever o código de produção. Isso ajuda a garantir que o código seja de alta qualidade e funcione como esperado. A partidr desse projeto, vou procurar seguir o TDD, escrevendo testes para todas as funcionalidades antes de implementá-las.

## Contribuição

Contribuições são bem-vindas. Por favor, siga as diretrizes de contribuição do projeto.

## Licença

Este projeto é licenciado sob a licença MIT.

## Contato

Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato.
https://www.linkedin.com/in/jairo-soares/

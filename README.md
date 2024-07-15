# Forum Hub

Forum Hub é uma aplicação que permite aos usuários se cadastrarem, criarem tópicos, responderem a tópicos e gerenciarem suas interações em um ambiente de cursos. A aplicação é desenvolvida em Java utilizando o Spring Boot, com autenticação baseada em JWT e integração com um banco de dados MySQL.

## Funcionalidades

- **Autenticação**: Registro e login de usuários com JWT.
- **Gerenciamento de Tópicos**: CRUD de tópicos, associação de tópicos a cursos e usuários.
- **Gerenciamento de Respostas**: CRUD de respostas, associação de respostas a tópicos e usuários.
- **Mudança de Status de Tópicos**: Alteração do status de um tópico (aberto, fechado, resolvido).

## Estrutura do Projeto

### Pacotes

- `com.forum_hub.application.controller`: Contém os controllers que gerenciam as requisições HTTP.
- `com.forum_hub.application.dto`: Contém os Data Transfer Objects (DTOs) para entrada e saída de dados.
- `com.forum_hub.application.service`: Contém as classes de serviço que implementam a lógica de negócios.
- `com.forum_hub.domain.enums`: Contém os enumeradores usados na aplicação.
- `com.forum_hub.domain.model`: Contém as entidades JPA.
- `com.forum_hub.infra.repository`: Contém as interfaces de repositório que gerenciam a persistência dos dados.

### Tabelas do Banco de Dados

#### courses

| Coluna    | Tipo       | Descrição                   |
|-----------|------------|-----------------------------|
| id        | BIGINT     | Chave primária              |
| name      | VARCHAR(255)| Nome do curso               |
| category  | VARCHAR(50)| Categoria do curso          |

#### topics

| Coluna    | Tipo       | Descrição                   |
|-----------|------------|-----------------------------|
| id        | BIGINT     | Chave primária              |
| title     | VARCHAR(255)| Título do tópico            |
| message   | TEXT       | Mensagem do tópico          |
| status    | VARCHAR(50)| Status do tópico            |
| course_id | BIGINT     | Referência para o curso     |
| author_id | BIGINT     | Referência para o autor     |
| created_at| TIMESTAMP  | Data de criação             |

#### answers

| Coluna    | Tipo       | Descrição                   |
|-----------|------------|-----------------------------|
| id        | BIGINT     | Chave primária              |
| message   | TEXT       | Mensagem da resposta        |
| solution  | TEXT       | Solução da resposta         |
| topic_id  | BIGINT     | Referência para o tópico    |
| author_id | BIGINT     | Referência para o autor     |
| created_at| TIMESTAMP  | Data de criação             |

## Configuração do Projeto

### Dependências

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- MySQL
- Flyway

### Configuração do Banco de Dados

Configure o banco de dados MySQL no arquivo `application.properties`:

```properties
spring.application.name=forum-hub

spring.mvc.locale=en_US

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

api.security.token.secret=${JWT_SECRET}
```
Substitua ${DB_HOST}, ${DB_PORT}, ${DB_NAME}, ${DB_USERNAME}, ${DB_PASSWORD} e ${JWT_SECRET} pelos valores apropriados.

## Executando a Aplicação
1. Clone o repositório:
  ```sh
  https://github.com/LeeYooBin/Forum-hub.git
  cd Forum-hub
  ```
2. Compile e execute a aplicação:
  ```sh
  ./mvnw spring-boot:run
  ```
## Endpoints da API
#### Autenticação
- `POST /auth/register`: Registrar um novo usuário.
- `POST /auth/login`: Fazer login e obter o token JWT.
#### Tópicos
- `POST /topics`: Criar um novo tópico.
- `GET /topics`: Listar todos os tópicos de um usuário.
- `GET /topics/{id}`: Obter detalhes de um tópico.
- `PUT /topics/{id}`: Atualizar um tópico.
- `DELETE /topics/{id}`: Deletar um tópico.
- `PATCH /topics/{id}/status`: Atualizar o status de um tópico.
#### Respostas
- `POST /answers`: Criar uma nova resposta.
- `GET /answers/topic/{topicId}`: Listar todas as respostas de um tópico.
- `GET /answers/{id}`: Obter detalhes de uma resposta.
- `PUT /answers/{id}`: Atualizar uma resposta.
- `DELETE /answers/{id}`: Deletar uma resposta.

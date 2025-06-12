Formação Java 2024 pela Empresa HumanIT

# Sistema de Gerenciamento de Salários
Desenvolvimento de um sistema de gerenciamento de salários com uma arquitetura composta por 4 microsserviços orquestrados via Docker. APIs protegidas com Spring Security e JWT, auditoria de dados com Hibernate Envers e versionamento de banco de dados com Liquibase. Comunicação assíncrona via Apache Kafka para envio de relatórios Excel ao usuário autenticado.

## Visão Geral dos Microsserviços
**Authentication API**: Fornece mecanismos robustos de login e registro com autenticação baseada em token JWT para garantir acesso seguro aos serviços.

**Salary Management API**: Permite a criação, leitura, atualização e exclusão de salários e componentes salariais, com atualizações automáticas para propostas de salário aceitas.

**Report Excel API**: Sistema que permite o envio de relatórios Excel via e-mail ao usuário autenticado.

**Portal API**: Microsserviço que acessa os outros 3 microsserviços para autenticação e autorização de usuários, gerenciando o acesso seguro com base no papel do usuário.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- JSON Web Token (JWT)
- PostgreSQL
- Apache Kafka
- Spring Cloud OpenFeign
- Java Mail Sender
- Hibernate Envers
- Liquibase
- Docker
- JUnit 5
- Mockito
- Gradlew
- Lombok
- Swagger
- MapStruct

## Arquitetura
O Sistema de Gerenciamento de Salários segue uma arquitetura de microsserviços, com serviços independentes se comunicando via Kafka para comunicação assíncrona ou chamadas FeignClient para comunicação síncrona. Cada serviço é projetado para escalabilidade, confiabilidade e modularidade.

![Imagem 1](/images/UML.jpg)

## Documentação das APIs
The ``Portal API`` interage com as APIs ``Authentication API``, ``Salary Management API`` e ``Email API`` para lidar com autenticação, gerenciamento de salários e funcionalidades de e-mail.

## Authentication API
``POST /sign-in``: Autentica um usuário e retorna um token JWT.

``POST /refreshToken``: Atualiza o token JWT para um usuário autenticado.

## Salary Management
#### Salary
``POST /api/v1/portal/salary``: Cria um novo salário com os detalhes fornecidos.

``GET /api/v1/portal/salary``: Recupera uma lista paginada de todos os salários.

``GET /api/v1/portal/salary/{id}``: Recupera um salário pelo seu identificador único.

``GET /api/v1/portal/salary/search``: Recupera todos os salários com base no status e em um intervalo de datas.

``POST /api/v1/portal/salary/proposed/salaryId={id}``: Permite que um colaborador aceite uma proposta de salário, fornecendo sua decisão e o ID do salário associado.

``POST /api/v1/portal/salary/my-salaries``: Recupera uma lista paginada de salários associados ao colaborador autenticado. Apenas o colaborador pode visualizar seus próprios salários.

``GET /api/v1/portal/salary/collaboratorId={id}``: Recupera uma lista paginada de todos os salários associados a um colaborador específico.

``PUT /api/v1/portal/salary/{id}``: Atualiza um salário pelo seu ID.

``DELETE /api/v1/portal/salary/{id}``: Exclui um salário pelo seu ID.

#### Salary Components

``POST /api/v1/portal/salary/components``: Cria um novo componente salarial.

``GET /api/v1/portal/salary/components``: Recupera todos os componentes salariais.

``GET /api/v1/portal/salary/components/{id}``: Recupera um componente salarial pelo seu ID.

``GET /api/v1/portal/salary/components/salaryId={id}``: Recupera todos os componentes salariais com base no ID do salário.

``PUT /api/v1/portal/salary/components/{id}``: Atualiza um componente salarial pelo seu ID.

``DELETE /api/v1/portal/salary/components/{id}``: Exclui um componente salarial pelo seu ID.

## Export Report

``POST /api/v1/portal/export/all/salaries``: Exporta todos os salários para um arquivo externo, acionado por uma solicitação autorizada.

``POST /api/v1/portal/export/dates``: Exporta todos os salários entre datas para um arquivo externo, acionado por uma solicitação autorizada.

``POST /api/v1/portal/export/status``: Exporta todos os salários por status para um arquivo externo, acionado por uma solicitação autorizada.

``POST /api/v1/portal/export/collaboratorId={id}``: Exporta salários por ID do colaborador para um arquivo externo, acionado por uma solicitação autorizada.

``POST /api/v1/portal/export/my-salaries``: Exporta os próprios salários do usuário autenticado para um arquivo externo, acionado por uma solicitação autorizada.

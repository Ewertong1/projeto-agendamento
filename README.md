Projeto de Agendamento

Este projeto foi desenvolvido para fornecer um sistema de agendamento com cadastro de vagas, solicitantes e agendamentos. A aplicação foi construída utilizando tecnologias como Java, JSF (JavaServer Faces) com PrimeFaces, e Spring Boot. A base de dados utiliza o Hibernate para o mapeamento objeto-relacional.<br/>
Arquitetura do Projeto

A arquitetura adotada para este projeto é baseada nos seguintes componentes:
1. Camada de Apresentação (Frontend):
Utiliza JSF com PrimeFaces para renderização de páginas web.<br/>
As páginas foram desenvolvidas em XHTML e são responsáveis por interagir com os usuários e fornecer uma interface rica e dinâmica.<br/>
PrimeFaces foi usado para fornecer componentes avançados como tabelas, formulários e mensagens de validação.<br/>
2. Camada de Serviço (Backend):
Spring Boot é usado como o framework principal para fornecer a camada de serviço.
A lógica de negócio está encapsulada em Services, que são componentes que contêm métodos para tratar as regras de negócio do projeto.
Beans gerenciados são utilizados para intermediar a comunicação entre a camada de apresentação e a camada de serviço.
3. Camada de Persistência:
JPA/Hibernate é utilizado para persistir os dados no banco de dados.
As entidades representam as tabelas do banco de dados e possuem as anotações necessárias para o mapeamento.
Utiliza-se o padrão DAO/Repository para gerenciar o acesso aos dados.
4. Banco de Dados:
O projeto utiliza um banco de dados HSQLDB configurado para fins de teste, mas pode ser adaptado para outros bancos de dados relacionais.
As tabelas são geradas automaticamente a partir das entidades mapeadas.


Funcionalidades
Cadastro de Vagas: Permite cadastrar vagas disponíveis para agendamento.
Cadastro de Solicitantes: Permite cadastrar os solicitantes que desejam realizar agendamentos.
Cadastro de Agendamentos: Permite cadastrar agendamentos com base nas vagas e solicitantes cadastrados.
Consulta de Agendamentos: Exibe os agendamentos realizados com opções de filtro por período e solicitante.
Busca Avançada por Solicitante: Fornece uma busca detalhada dos agendamentos realizados por um solicitante específico.

Tecnologias Utilizadas
Java 17
Spring Boot 2.7.2
JSF 2.3 com PrimeFaces 13.0.0
Hibernate / JPA
HSQLDB como banco de dados

Pré-requisitos
Java 17 instalado
Maven instalado

Como Executar a Aplicação

1-Clone o repositório:
git clone https://github.com/seu-usuario/projeto-agendamento.git

2-Navegue até o diretório do projeto:
cd projeto-agendamento

3-Execute o comando Maven para construir o projeto:
mvn clean install

4-Inicie a aplicação:
mvn spring-boot:run

5 - A aplicação estará disponível em: http://localhost:9292

Como Acessar o Sistema
Acesse http://localhost:9292/index.xhtml para visualizar a página inicial e acessar as funcionalidades do sistema.






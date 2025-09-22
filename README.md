# Projeto Tarefas - FJ21

Este é um projeto de exemplo de um sistema de gerenciamento de tarefas, desenvolvido como parte do curso FJ-21 da Caelum.

## Descrição

O projeto consiste em uma aplicação web para cadastrar, listar, alterar e remover tarefas. As tarefas possuem uma descrição, um status (finalizada ou não) e uma data de finalização.

## Tecnologias Utilizadas

*   **Java**
*   **Spring MVC**
*   **JSP (JavaServer Pages)**
*   **JSTL (JavaServer Pages Standard Tag Library)**
*   **MySQL**
*   **JDBC (Java Database Connectivity)**
*   **Apache Tomcat (ou outro container de servlets)**

## Como Executar o Projeto

1.  **Banco de Dados:**
    *   Crie um banco de dados MySQL chamado `tarefas`.
    *   A tabela de tarefas será criada automaticamente.
    *   Configure as credenciais de acesso ao banco de dados no arquivo `fj21-tarefas/WebContent/WEB-INF/spring-context.xml`.

2.  **Build e Deploy:**
    *   Importe o projeto em sua IDE de preferência (Eclipse, IntelliJ, etc.).
    *   Configure um servidor de aplicação (como o Apache Tomcat).
    *   Faça o deploy da aplicação no servidor.

3.  **Acesso:**
    *   Acesse a aplicação em seu navegador através da URL `http://localhost:8080/fj21-tarefas/`.

## Estrutura do Projeto

*   `src/`: Contém o código-fonte da aplicação.
    *   `br.com.caelum.tarefas.controller`: Controladores Spring MVC.
    *   `br.com.caelum.tarefas.dao`: Classes de acesso a dados (DAO).
    *   `br.com.caelum.tarefas.factory`: Fábrica de conexões com o banco de dados.
    *   `br.com.caelum.tarefas.interceptor`: Interceptadores de requisições.
*   `WebContent/`: Contém as páginas web, imagens, CSS e as bibliotecas da aplicação.
    *   `WEB-INF/views/`: Páginas JSP.
    *   `WEB-INF/lib/`: Bibliotecas (JARs) do projeto.
    *   `resources/`: Arquivos de CSS e JavaScript.
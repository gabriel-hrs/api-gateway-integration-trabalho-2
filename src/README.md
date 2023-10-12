Projeto implementado com Java V20 e Spring Boot V3.1.4.
Foi utilizada a dependencia JPA do framework para utilizar o banco de dados de memória H2.

# Os dados para acesar o banco de dados são:
URL:    http://localhost:8080/h2-console

# Dados de conexão com o banco H2
spring.datasource.url=jdbc:h2:mem:bancoapi
spring.datasource.username=root
spring.datasource.password=root

Dentro da pasta resources existe o arquivo import.sql onde existe um script para povoar o 
banco de dados ao se iniciar a aplicação.

# Dentro da pasta testes existe a pasta:
-Postman: Contém arquivo JSON de uma coleção para o Postman para execução de testes.